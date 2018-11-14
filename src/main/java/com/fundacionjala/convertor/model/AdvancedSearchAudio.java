package com.fundacionjala.convertor.model;

import com.fundacionjala.convertor.controller.SearchCriteria;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.fundacionjala.convertor.Main.PATH_TO_FFMPEG_BIN_FFPROBE;

/**
 * Module Model AdvancedSearchAudio.
 *
 * @author Melvi Caballero.
 * @version 1.0.
 */
public class AdvancedSearchAudio {
    ArrayList<String> audioTypes = new ArrayList<>();

    public AdvancedSearchAudio() {
        audioTypes.add("mp3");
        audioTypes.add("wav");
        audioTypes.add("ogg");
        audioTypes.add("m4a");
        audioTypes.add("wma");
        audioTypes.add("aac");
        audioTypes.add("flac");
    }

    public boolean isAudioType(File file) {
        String ext = (file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1)).toLowerCase();
        return audioTypes.contains(ext)? true : false;
    }

    public Asset fillAudioFeatures(File file) {
        AudioAsset asset = new AudioAsset();

        BasicFileAttributes attrib = null;
        Path path = Paths.get(file.getAbsolutePath());
        try {
            attrib = Files.readAttributes(path, BasicFileAttributes.class);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        asset.setNameFile("Name: ".concat(file.getName()));
        String extentionFile = (file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1)).toLowerCase();
        asset.setTypeFile("Audio: ".concat(extentionFile));
        asset.setSizeFile("Size: ".concat(Long.toString(attrib.size())).concat(" bytes"));
        FileTime fileTime = attrib.creationTime();
        asset.setCreationFile("Creation time: ".concat(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(fileTime.toMillis())));
        asset.setFile(file);
        asset.setPath(file.getAbsolutePath());

        FFprobe ffprobe;
        try {
            ffprobe = new FFprobe(PATH_TO_FFMPEG_BIN_FFPROBE);
            FFmpegProbeResult ffprobeResult = ffprobe.probe(asset.getPath());

            //Audio channels.
            String ch_Audio = ffprobeResult.getStreams().get(0).channel_layout;
            asset.setChannels("Channels: ".concat(ch_Audio==null? "Unknown" : ch_Audio));

            //Audio codec
            String codec = ffprobeResult.getStreams().get(0).codec_name;
            asset.setAudioCodec("Audio codec: ".concat(codec));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return asset;
    }

    public  ArrayList<Asset> FilterCrit(ArrayList<Asset> resultList, SearchCriteria criteria) {
        ArrayList<Asset> listAssetResult = new ArrayList<>(1);
        for (Asset asset : resultList) {
            if (asset.getTypeFile().contains("Audio")) {
                boolean right = false;
                AudioAsset audioAsset = new AudioAsset();
                audioAsset = (AudioAsset) asset;

                right = criteria.getAudioType().equals("All")
                        || audioAsset.getTypeFile().contains(criteria.getAudioType());

                //Channels
                right = right &&
                        criteria.getChannels().equals("All")
                        || audioAsset.getChannels().contains(criteria.getChannels());

                //Audio codec
                right = right &&
                        criteria.getAudioCodec().equals("All")
                        || audioAsset.getAudioCodec().contains(criteria.getAudioCodec());

                if (right) listAssetResult.add(audioAsset);
            }
        }
        return listAssetResult;
    }

    /**
     * @param resultList list of Files
     * @param criteria   compare criteria
     * @return filtered list
     */
    public ArrayList<Asset> FilterCriteria(ArrayList<File> resultList, SearchCriteria criteria) {
        ArrayList<Asset> result = new ArrayList<Asset>();

        FFprobe ffprobe;

        try {
            ffprobe = new FFprobe(PATH_TO_FFMPEG_BIN_FFPROBE);
            //Para cada file en la lista
            FFmpegProbeResult ffprobeResult = null;
            for (File file : resultList) {
                Boolean correct = true;
                AudioAsset audAsset = new AudioAsset();
                String extentionFile = (file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1)).toLowerCase();

                try {
                    ffprobeResult = ffprobe.probe(file.getAbsolutePath());
                } catch (Exception e) {
                    correct = false;
                }

                //Audio Type.
                if (criteria.getAudioType() != "All") {
                    if (!criteria.getAudioType().equals(extentionFile)) {
                        correct = false;
                    } else {
                        audAsset.setTypeFile("Audio Type: ".concat(extentionFile));
                    }
                } else {
                    audAsset.setTypeFile("Audio Type: ".concat(extentionFile));
                }

                //Audio channels.
                String ch_Audio = ffprobeResult.getStreams().get(0).channel_layout;
                if (correct && criteria.getChannels() != "All") {
                    System.out.println(file.getName()+" "+ffprobeResult.getStreams().get(0).channel_layout);
                    if (!criteria.getChannels().equals(ch_Audio)) {
                        correct = false;
                    } else {
                        audAsset.setChannels(ch_Audio);
                    }
                } else {
                    audAsset.setChannels(ch_Audio);
                }

                if (correct && audioTypes.contains(extentionFile))
                    result.add(audAsset);
            }

        } catch (Exception e) {
            //add to logger when available
            System.out.println("Fail in advanced audio search.");
        }

        return result;
    }

}
