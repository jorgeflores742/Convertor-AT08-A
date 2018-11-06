package com.fundacionjala.convertor.model;

import com.fundacionjala.convertor.controller.SearchCriteria;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;

import java.io.File;
import java.util.ArrayList;

import static com.fundacionjala.convertor.Main.PATH_TO_FFMPEG_BIN_FFPROBE;

/**
 * Module Model AdvancedSearchAudio.
 *
 * @author Melvi Caballero.
 * @version 1.0.
 */
public class AdvancedSearchAudio {

    /**
     * @param resultList list of Files
     * @param criteria   compare criteria
     * @return filtered list
     */
    public ArrayList<File> FilterCriteria(ArrayList<File> resultList, SearchCriteria criteria) {
        ArrayList<File> result = new ArrayList<File>();

        FFprobe ffprobe;

        try {
            ffprobe = new FFprobe(PATH_TO_FFMPEG_BIN_FFPROBE);

            //Para cada file en la lista
            FFmpegProbeResult ffprobeResult = null;
            for (File file : resultList) {
                Boolean correct = true;
                try {
                    ffprobeResult = ffprobe.probe(file.getAbsolutePath());
                } catch (Exception e) {
                    correct = false;
                }

                if (criteria.getAudioType() != "All") {
                    if (!criteria.getAudioType().equals((file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1)).toLowerCase()))
                        correct = false;
                }

                if (correct && criteria.getChannels() != "All") {
                    System.out.println(file.getName()+" "+ffprobeResult.getStreams().get(0).channel_layout);
                    if (!criteria.getChannels().equals(ffprobeResult.getStreams().get(0).channel_layout))
                        correct = false;
                }

                if (correct)
                    result.add(file);
            }

        } catch (Exception e) {
            //add to logger when available
            System.out.println("Fail in advanced audio search.");
        }

        return result;
    }

}
