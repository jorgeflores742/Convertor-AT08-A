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

    public static final long MINUS = 102400000L;
    public static final long LITTLE = 512000000L;
    public static final long MEDIUM = 1024000000L;
    private static final long BIG = 10240000000L;
    Asset fileAsset;
    AdvancedSearchVideo advancedSearchVideo = new AdvancedSearchVideo();
    AdvancedSearchAudio advancedSearchAudio = new AdvancedSearchAudio();

    /**
     * This program searchs files using criteria of name,
     * extension, size, but the path is important.
     * There will be error if there is no a real path.
     */
    public FileSearcher() {

    }

    /**
     * @param searchCriteria
     * @return This main method diference between files and directories.
     */

    public ArrayList<Asset> search(SearchCriteria searchCriteria) {
        ArrayList<Asset> fileListAsset = new ArrayList<>(1);
        ArrayList<File> fileList = new ArrayList<>(1);
        File dir = new File(searchCriteria.getPath());
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                searchCriteria.setPath(file.getPath());
                fileListAsset.addAll(search(searchCriteria));
            } else if (meetCriteria(file, searchCriteria.getName(), searchCriteria.getExt(), searchCriteria.getSize())) {
                fileAsset = new Asset();
                fileAsset = fillAsset(fileAsset, file);
                fileListAsset.add(fileAsset);
                fileList.add(file);
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
        return finalResult;
    }

    private Asset fillAsset(Asset fAsset, File file) {
        fAsset = fillFileAsset(file);
        if (advancedSearchVideo.isVideoType(file)) {
            String extentionFile = (file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1)).toLowerCase();
            fAsset.setTypeFile("Video: ".concat(extentionFile));
            fAsset = advancedSearchVideo.fillVideoFeatures(fAsset);
        } else if (advancedSearchAudio.isAudioType(file)) {
            String extentionFile = (file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1)).toLowerCase();
            fAsset.setTypeFile("Audio: ".concat(extentionFile));
            fAsset = advancedSearchAudio.fillAudioFeatures(fAsset);
        }

        return fAsset;
    }

    private Asset fillFileAsset(File file) {
        Asset asset = new Asset();
        BasicFileAttributes attrib = null;
        Path path = Paths.get(file.getAbsolutePath());
        try {
            attrib = Files.readAttributes(path, BasicFileAttributes.class);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        asset.setNameFile("Name: ".concat(file.getName()));
        String extentionFile = (file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1)).toLowerCase();
        asset.setTypeFile("File: ".concat(extentionFile));
        asset.setSizeFile("Size: ".concat(Long.toString(attrib.size())).concat(" bytes"));
        FileTime fileTime = attrib.creationTime();
        asset.setCreationFile("Creation time: ".concat(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(fileTime.toMillis())));
        asset.setFile(file);
        asset.setPath(file.getAbsolutePath());
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
    public boolean meetCriteria(File file, String name, String ext, String size) {
        boolean criteria;
        criteria = (name == null || name.equals("")) || file.getName().contains(name);
        criteria = criteria && isSearchExtention(file, ext);
        criteria = criteria && isSearchSize(file, size);
        return criteria;
    }

    /**
     * @param file File for meets criteria.
     * @param ext  extension criteria.
     * @return true if file meets the criteria.
     */
    public boolean isSearchExtention(File file, String ext) {
        return (ext == null || ext.equals("") || ext.equals("All")) || hasExtention(file, ext);
    }

    /**
     * @param file Fil for meets the criteria.
     * @param size size criteria.
     * @return true if file meets criteria.
     */
    public boolean isSearchSize(File file, String size) {
        return (size == null || size.equals("") || size.equals("All")) || isSize(file, size);
    }

    /**
     * @param file file.
     * @param ext  extention.
     * @return true if the extention meets criteria.
     */
    public boolean hasExtention(File file, String ext) {
        String[] parts = file.getName().split("[.]");
        return parts[parts.length - 1].equals(ext);
    }

    /**
     * @param file file.
     * @param siz  size.
     * @return true if file is in range of size.
     */
    public boolean isSize(File file, String siz) {
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
        if (sizeFile > BIG && siz.equals("very big")) {
            return true;
        }
        return false;

    }
}