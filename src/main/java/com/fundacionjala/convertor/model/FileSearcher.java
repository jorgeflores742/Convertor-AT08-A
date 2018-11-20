/*
 * @Controller.java Copyright (c) 2018 Fundacion Jala. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * Please contact Fundacion Jala, 2643 Av Melchor Perez de Olguin, Colquiri
 * Sud, Cochabamba, Bolivia. www.fundacion-jala.org if you need additional
 * information or have any questions.
 */

package com.fundacionjala.convertor.model;

import com.fundacionjala.convertor.controller.SearchCriteria;
import com.fundacionjala.convertor.utils.SingleLogger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Dennis Misael Montaño Vargas
 */
public class FileSearcher {

    private static final long MINUS = 102400000L;
    private static final long LITTLE = 512000000L;
    private static final long MEDIUM = 1024000000L;
    private static final long BIG = 10240000000L;
    private AdvancedSearchVideo advancedSearchVideo = new AdvancedSearchVideo();
    private AdvancedSearchAudio advancedSearchAudio = new AdvancedSearchAudio();
    private static SingleLogger sL = SingleLogger.getInstanceLogger();


    /**
     * This program searchs files using criteria of name,
     * extension, size, but the path is important.
     * There will be error if there is no a real path.
     */
    public FileSearcher() {
        sL.register(null, "INFO", "Successful - FileSearcher - start");
    }

    /**
     * @param searchCriteria Object that contains the criteria for search files
     * @return This main method diference between files and directories.
     */

    public ArrayList<Asset> search(SearchCriteria searchCriteria) {
        sL.register(null, "INFO", "Successful - search - start");
        ArrayList<Asset> fileListAsset = new ArrayList<>(1);
        File dir = new File(searchCriteria.getPath());
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                searchCriteria.setPath(file.getPath());
                fileListAsset.addAll(search(searchCriteria));
            } else if (meetCriteria(file, searchCriteria.getName(), searchCriteria.getExt(), searchCriteria.getSize())) {
                fileListAsset.add(fillAsset(file));
            }
        }
        ArrayList<Asset> finalResult;
        if (searchCriteria.getAdvancedType().equals("Video")) {
            finalResult = advancedSearchVideo.FilterCrit(fileListAsset, searchCriteria);
        } else if (searchCriteria.getAdvancedType().equals("Audio")) {
            finalResult = advancedSearchAudio.FilterCrit(fileListAsset, searchCriteria);
        } else {
            finalResult = fileListAsset;
        }
        sL.register(null, "INFO", "Successful - search - finished");

        return finalResult;
    }

    private Asset fillAsset(File file) {
        sL.register(null, "INFO", "Successful - fillAsset - start");
        if (advancedSearchVideo.isVideoType(file)) {
            sL.register(null, "INFO", "Successful - fillAsset - finished");
            return advancedSearchVideo.fillVideoFeatures(file);
        } else if (advancedSearchAudio.isAudioType(file)) {
            sL.register(null, "INFO", "Successful - fillAsset - finished");
            return advancedSearchAudio.fillAudioFeatures(file);
        } else {
            sL.register(null, "INFO", "Successful - fillAsset - finished");
            return fillCommonFeatures(file);
        }
    }

    private CommonAsset fillCommonFeatures(File file) {
        sL.register(null, "INFO", "Successful - fillCommonFeatures - start");
        CommonAsset asset = new CommonAsset();

        BasicFileAttributes attrib = null;
        Path path = Paths.get(file.getAbsolutePath());
        try {
            sL.register(null, "INFO", "Successful - fillCommonFeatures - readAttributes - start");
            attrib = Files.readAttributes(path, BasicFileAttributes.class);
        } catch (IOException e1) {
            sL.register(e1, "SEVERE", "Successful - fillCommonFeatures - readAttributes - start");
        }

        asset.setNameFile("Name: ".concat(file.getName()));
        String extentionFile = (file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1)).toLowerCase();
        asset.setTypeFile("File: ".concat(extentionFile));
        asset.setSizeFile("Size: ".concat(Long.toString(attrib.size())).concat(" bytes"));
        FileTime fileTime = attrib.creationTime();
        asset.setCreationFile("Creation time: ".concat(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(fileTime.toMillis())));
        asset.setFile(file);
        asset.setPath(file.getAbsolutePath());
        sL.register(null, "INFO", "Successful - fillCommonFeatures - finished");
        return asset;
    }

    /**
     * @param file real specific file.
     * @param name name of the file criteria.
     * @param ext  criteria of extention.
     * @param size criteria about size.
     * @return true if file meets the criteria.
     * <p>
     * this method compares the file with the criteria data.
     */
    private boolean meetCriteria(File file, String name, String ext, String size) {
        sL.register(null, "INFO", "Successful - meetCriteria - start");
        boolean criteria;
        criteria = (name == null || name.equals("")) || file.getName().toLowerCase().contains(name.toLowerCase());
        criteria = criteria && isSearchSize(file, size);
        sL.register(null, "INFO", "Successful - meetCriteria - finished");
        return criteria;
    }

    /**
     * @param file Fil for meets the criteria.
     * @param size size criteria.
     * @return true if file meets criteria.
     */
    private boolean isSearchSize(File file, String size) {
        sL.register(null, "INFO", "Successful - isSearchSize - start");
        return (size == null || size.equals("") || size.equals("All")) || isSize(file, size);
    }

    /**
     * @param file file.
     * @param siz  size.
     * @return true if file is in range of size.
     */
    private boolean isSize(File file, String siz) {
        sL.register(null, "INFO", "Successful - isSize - start");
        long sizeFile = file.length();
        if (sizeFile <= MINUS && siz.equals("minus")) {
            return true;
        }
        if (sizeFile <= LITTLE && sizeFile > MINUS && siz.equals("little")) {
            return true;
        }
        if (sizeFile <= MEDIUM && sizeFile > LITTLE && siz.equals("medium")) {
            return true;
        }
        if (sizeFile <= BIG && sizeFile > MEDIUM && siz.equals("big")) {
            return true;
        }
        sL.register(null, "INFO", "Successful - isSize - finished");

        return sizeFile > BIG && siz.equals("very big");

    }
}