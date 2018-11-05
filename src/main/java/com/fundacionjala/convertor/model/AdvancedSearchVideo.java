package com.fundacionjala.convertor.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.Map;

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

    /**
     * @param pathFile
     * @return
     * @throws IOException
     */
    public String searchByLastModifiedTime(String pathFile) throws IOException {
        Path path = Paths.get(pathFile);
        String attribList = "basic:size,lastModifiedTime";
        Map<String, Object> attribs = Files.readAttributes(path, attribList);
        return (String) attribs.get("lastModifiedTime");
    }

    /**
     * @param creationTimeFile
     * @return
     * @throws IOException
     */
    public String creationFileTime(String creationTimeFile) throws IOException {
        Path path = Paths.get(creationTimeFile);
        BasicFileAttributeView bfv = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        BasicFileAttributes bfa = bfv.readAttributes();

        String creationT = String.valueOf(bfa.creationTime());
        return creationT;
    }

    /**
     * @param pathFile
     * @return
     * @throws IOException
     */
    public String fileOwnerAttributeView(String pathFile) throws IOException {
        Path path = Paths.get(pathFile);
        FileOwnerAttributeView foav = Files.getFileAttributeView(path, FileOwnerAttributeView.class);

        UserPrincipal owner = foav.getOwner();
        String ownerName = String.valueOf(owner.getName());
        return ownerName;
    }

    /**
     * @param resultList List of Files
     * @param criteria   sompare criteria
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

                if (criteria.getVideoType() != "None") {
                    if (criteria.getVideoType() != file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1))
                        correct = false;
                }

                if (correct && criteria.getFps() != "None") {
                    if ((int) Double.parseDouble(criteria.getFps()) != ffprobeResult.getStreams().get(0).avg_frame_rate.getNumerator())
                        correct = false;
                }

                if (correct && criteria.getResolution() != "None") {
                    //extract values
                    int width = 0;
                    int height = 0;
                    String[] values = criteria.getResolution().split("x");
                    width = Integer.parseInt(values[0]);
                    height = Integer.parseInt(values[1]);

                    if ((width != ffprobeResult.getStreams().get(0).width)
                            ||
                            (height != ffprobeResult.getStreams().get(0).height)
                    )
                        correct = false;
                }

                if (correct && criteria.getAspectRatio() != "None") {
                    if (criteria.getAspectRatio() != ffprobeResult.getStreams().get(0).display_aspect_ratio)
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
