package com.fundacionjala.convertor.model;

import com.fundacionjala.convertor.controller.SearchCriteria;
import com.fundacionjala.convertor.utils.SingleLogger;
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
    private static SingleLogger sL = SingleLogger.getInstanceLogger();

    ArrayList<String> audioTypes = new ArrayList<>();

    public AdvancedSearchAudio() {
        sL.register(null, "INFO", "Successful - AdvancedSearchAudio - start");
        audioTypes.add("mp3");
        audioTypes.add("wav");
        audioTypes.add("ogg");
        audioTypes.add("m4a");
        audioTypes.add("wma");
        audioTypes.add("aac");
        audioTypes.add("flac");
        sL.register(null, "INFO", "Successful - AdvancedSearchAudio - finished");
    }

    public boolean isAudioType(File file) {
        sL.register(null, "INFO", "Successful - isAudioType - start");
        String ext = (file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1)).toLowerCase();
        return audioTypes.contains(ext)? true : false;
    }

    public Asset fillAudioFeatures(File file) {
        sL.register(null, "INFO", "Successful - fillAudioFeatures - start");
        AudioAsset asset = new AudioAsset();
        BasicFileAttributes attrib = null;
        Path path = Paths.get(file.getAbsolutePath());
        try {
            sL.register(null, "INFO", "Successful - fillAudioFeatures - readAttributes- start");
            attrib = Files.readAttributes(path, BasicFileAttributes.class);
        } catch (IOException e1) {
            sL.register(e1, "SEVERE", "Successful - fillAudioFeatures - readAttributes - Failed");
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
            sL.register(null, "INFO", "Successful - fillAudioFeatures - readAttributes- start");
            ffprobe = new FFprobe(PATH_TO_FFMPEG_BIN_FFPROBE);
            FFmpegProbeResult ffprobeResult = ffprobe.probe(asset.getPath());
            //Audio channels.
            String ch_Audio = ffprobeResult.getStreams().get(0).channel_layout;
            asset.setChannels("Channels: ".concat(ch_Audio==null? "Unknown" : ch_Audio));
            //Audio codec
            String codec = ffprobeResult.getStreams().get(0).codec_name;
            asset.setAudioCodec("Audio codec: ".concat(codec));
        } catch (IOException e) {
            sL.register(e, "SEVERE", "Successful - fillAudioFeatures - FFprobe - Failed");
        }
        return asset;
    }

    public  ArrayList<Asset> FilterCrit(ArrayList<Asset> resultList, SearchCriteria criteria) {
        sL.register(null, "INFO", "Successful - FilterCrit - start");
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
}
