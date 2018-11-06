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

    public ArrayList<File> search(SearchCriteria searchCriteria) {
        ArrayList<File> fileList = new ArrayList<>(1);
        File dir = new File(searchCriteria.getPath());
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                searchCriteria.setPath(file.getPath());
                fileList.addAll(search(searchCriteria));
            } else if (meetCriteria(file, searchCriteria.getName(), searchCriteria.getExt(), searchCriteria.getSize())) {
                fileList.add(file);
            }
        }
        ArrayList<File> finalResult;
        if (searchCriteria.getAdvancedType().equals("Video")) {
            AdvancedSearchVideo advancedSearchVideo = new AdvancedSearchVideo();
            finalResult = advancedSearchVideo.FilterCriteria(fileList, searchCriteria);
        } else if (searchCriteria.getAdvancedType().equals("Audio")) {
            AdvancedSearchAudio advancedSearchAudio = new AdvancedSearchAudio();
            finalResult = advancedSearchAudio.FilterCriteria(fileList, searchCriteria);
        } else {
            finalResult = fileList;
        }
        return finalResult;
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