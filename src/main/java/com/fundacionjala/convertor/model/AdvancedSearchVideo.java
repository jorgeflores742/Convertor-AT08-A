package com.fundacionjala.convertor.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.fundacionjala.convertor.controller.SearchCriteria;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;

import static com.fundacionjala.convertor.Main.PATH_TO_FFMPEG_BIN_FFPROBE;

/**
 * Module Model AdvancedSearchVideo.
 *
 * @author Melvi Caballero.
 * @version 1.0.
 */

public class AdvancedSearchVideo {
    ArrayList<String> videoTypes = new ArrayList<>();

    public AdvancedSearchVideo() {
        videoTypes.add("avi");
        videoTypes.add("mpg");
        videoTypes.add("mp4");
        videoTypes.add("flv");
        videoTypes.add("wmv");
        videoTypes.add("mkv");
        videoTypes.add("mov");
        videoTypes.add("webm");
    }

    public boolean isVideoType(File file) {
        String ext = (file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1)).toLowerCase();
        return videoTypes.contains(ext)? true : false;
    }

    public Asset fillVideoFeatures(File file) {
        VideoAsset asset = new VideoAsset();
        BasicFileAttributes attrib = null;
        Path path = Paths.get(file.getAbsolutePath());
        try {
            attrib = Files.readAttributes(path, BasicFileAttributes.class);
        } catch (IOException e1) {
        }

        asset.setNameFile("Name: ".concat(file.getName()));
        String extentionFile = (file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1)).toLowerCase();
        asset.setTypeFile("Video: ".concat(extentionFile));
        asset.setSizeFile("Size: ".concat(Long.toString(attrib.size())).concat(" bytes"));
        FileTime fileTime = attrib.creationTime();
        asset.setCreationFile("Creation time: ".concat(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(fileTime.toMillis())));
        asset.setFile(file);
        asset.setPath(file.getAbsolutePath());

        FFprobe ffprobe;
        try {
            ffprobe = new FFprobe(PATH_TO_FFMPEG_BIN_FFPROBE);
            FFmpegProbeResult ffprobeResult = ffprobe.probe(asset.getPath());

            //Resolucion
            String wVideo = Integer.toString(ffprobeResult.getStreams().get(0).width);
            String hVideo = Integer.toString(ffprobeResult.getStreams().get(0).height);
            asset.setResolution("Resolution: ".concat(wVideo).concat("x").concat(hVideo));

            //Duration
            String duration = Double.toString(ffprobeResult.getStreams().get(0).duration);
            asset.setDuration("Duration: ".concat(duration).concat(" seconds"));

            //Aspect ratio
            String aspectRatio = ffprobeResult.getStreams().get(0).display_aspect_ratio;
            asset.setAspectRatio(aspectRatio==null?
                    "Aspect ratio: ".concat("Unknown"):"Aspect ratio: ".concat(aspectRatio));

            //fps
            int fps = ffprobeResult.getStreams().get(0).avg_frame_rate.getNumerator();
            float fpsFloat = fps;
            fpsFloat = fpsFloat >= 1000 ? (fpsFloat / 1000) : fpsFloat;
            asset.setFps("Frames Per Second: ".concat(Float.toString(fpsFloat)));
            System.out.println(file.getName().concat(" ").concat(Integer.toString(fps)));

            //Video codec
            String codec = ffprobeResult.getStreams().get(0).codec_name;
            asset.setVideoCodec("Video codec: ".concat(codec));

            //Audio codec
            try {
                String acodec = ffprobeResult.getStreams().get(1).codec_name;
                asset.setAudioCodec("Audio codec: ".concat(acodec));
            } catch (IndexOutOfBoundsException e) {
                asset.setAudioCodec("Audio codec: NonSpecified");
            }

        } catch (IOException e) {
        }
        return asset;
    }

    public  ArrayList<Asset> FilterCrit(ArrayList<Asset> resultList, SearchCriteria criteria) {
        ArrayList<Asset> listAssetResult = new ArrayList<>(1);
        for (Asset asset : resultList) {
            if (asset.getTypeFile().contains("Video")) {
                boolean right = false;
                VideoAsset vAsset = new VideoAsset();
                vAsset = (VideoAsset) asset;

                right = criteria.getVideoType().equals("All")
                        || vAsset.getTypeFile().contains(criteria.getVideoType());

                //FPS
                right = right &&
                        criteria.getFps().equals("All")
                        || vAsset.getFps().contains(criteria.getFps());

                //Resolution
                right = right &&
                        (criteria.getResolution().equals("All")
                                || vAsset.getResolution().contains(criteria.getResolution()));
                //Aspect ratio
                right = right &&
                        (criteria.getAspectRatio().equals("All") ||
                                vAsset.getAspectRatio().contains(criteria.getAspectRatio()));

                //Video Codec
                right = right &&
                        (criteria.getVideoCodec().equals("All") ||
                                vAsset.getVideoCodec().contains(criteria.getVideoCodec()));

                //Audio Codec
                right = right &&
                        (criteria.getVideoAudioCodec().equals("All") ||
                                vAsset.getAudioCodec().contains(criteria.getVideoAudioCodec()));

                if (right) listAssetResult.add(vAsset);
            }
        }
        return listAssetResult;
    }
}
