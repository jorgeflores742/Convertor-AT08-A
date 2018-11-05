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
            FFmpegProbeResult ffprobeResult;
            for (File file : resultList) {
                Boolean correct = true;
                ffprobeResult = ffprobe.probe(file.getAbsolutePath());

                if (criteria.getAudioType() != "All") {
                    if (criteria.getAudioType() != file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1))
                        correct = false;
                }

                if (correct && criteria.getChannels() != "All") {
                    if (criteria.getChannels() != ffprobeResult.getStreams().get(0).channel_layout)
                        correct = false;
                }

                if (correct)
                    result.add(file);
            }

        } catch (Exception e) {
            //add to logger when available
        }

        return result;
    }

}
