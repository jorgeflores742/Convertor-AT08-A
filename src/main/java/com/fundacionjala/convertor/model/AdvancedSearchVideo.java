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
    private String searchByLastModifiedTime(String pathFile) throws IOException {
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
    private String creationFileTime(String creationTimeFile) throws IOException {
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
    private String fileOwnerAttributeView(String pathFile) throws IOException {
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
            FFmpegProbeResult ffprobeResult = null;
            for (File file : resultList) {
                Boolean correct = true;
                try{
                    ffprobeResult = ffprobe.probe(file.getAbsolutePath());
                } catch (Exception e) {
                    correct = false;
                }
                if (!criteria.getVideoType().equals("All")) {
                    if (!criteria.getVideoType().equals((file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1)).toLowerCase()))
                        correct = false;
                }
                if (correct && (!criteria.getFps().equals("All"))) {
                    System.out.println((int)Float.parseFloat(criteria.getFps()));
                    System.out.println(ffprobeResult.getStreams().get(0).avg_frame_rate.getNumerator());
                    if ((int)Float.parseFloat(criteria.getFps())*1000 != ffprobeResult.getStreams().get(0).avg_frame_rate.getNumerator())
                        correct = false;
                }

                if (correct && (!criteria.getResolution().equals("All"))) {
                    System.out.println(ffprobeResult.getStreams().get(0).width+" "+ffprobeResult.getStreams().get(0).height);
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

                if (correct && (!criteria.getAspectRatio().equals("All"))) {
                    System.out.println(ffprobeResult.getStreams().get(0).display_aspect_ratio);
                    if (!criteria.getAspectRatio().equals(ffprobeResult.getStreams().get(0).display_aspect_ratio))
                        correct = false;
                }

                if (correct)
                    result.add(file);
            }

        } catch (Exception e) {
            //add to logger when available
            System.out.println("There is a fail");
        }

        return result;
    }
}
