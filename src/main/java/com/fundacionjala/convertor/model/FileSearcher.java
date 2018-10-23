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

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 */
public class FileSearcher {

    public static final long MINUS = 10240;
    public static final long LITTLE = 102400;
    public static final long MEDIUM = 1024000;
    public static final long BIG = 10240000;

    /**
     *
     */
    public FileSearcher() {

    }

    /**
     *
     * @param path path for searching.
     * @param name name of file.
     * @param ext extension for searching.
     * @param siz size for searching.
     * @return list of files that meet the criteria searching.
     */
    public ArrayList<File> searchFiles(String path, String name, String ext, String siz) {
        ArrayList<File> containerFiles = new ArrayList<>(1);
        ArrayList<File> returnListFiles = new ArrayList<>(1);
        int nCriteria = 0;
        if (name != null && !name.equals("")) {
            containerFiles.addAll(searchByName(name, path));
            ++nCriteria;
        }
        if (ext != null && !ext.equals("")) {
            containerFiles.addAll(searchByExt(ext, path));
            ++nCriteria;
        }
        if (siz != null && !siz.equals("")) {
            containerFiles.addAll(searchBySize(siz, path));
            ++nCriteria;
        }
        returnListFiles.addAll(nCriteria == 0 ? searchAll(path) : definitiveList(containerFiles, nCriteria));
        return returnListFiles;
    }

    /**
     *
     * @param nameFile name of file.
     * @param address path.
     * @return list of files.
     */
    public ArrayList<File> searchByName(String nameFile, String address) {
        ArrayList<File> fileList = new ArrayList<>(1);
        File dir = new File(address);
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                fileList.addAll(searchByName(nameFile, file.getPath()));
            } else if (file.getName().contains(nameFile)) {
                fileList.add(file);
            }
        }
        return fileList;
    }

    /**
     *
     * @param ext extention.
     * @param address path.
     * @return list of files.
     */
    public ArrayList<File> searchByExt(String ext, String address) {
        ArrayList<File> fileList = new ArrayList<>(1);
        File dir = new File(address);
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                fileList.addAll(searchByName(ext, file.getPath()));
            } else if (hasExtention(file, ext)){
                fileList.add(file);
            }
        }
        return fileList;
    }

    /**
     *
     * @param file file.
     * @param ext extention.
     * @return true if the extentios is the same.
     */
    public boolean hasExtention(File file, String ext) {
        String[] parts = file.getName().split("[.]");
        return parts[parts.length - 1].equals(ext);
    }

    /**
     *
     * @param siz size.
     * @param address path.
     * @return list of files.
     */
    public ArrayList<File> searchBySize(String siz, String address) {
        ArrayList<File> fileList = new ArrayList<>(1);
        File dir = new File(address);
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                fileList.addAll(searchByName(siz, file.getPath()));
            } else if (isSize(file, siz)){
                fileList.add(file);
            }
        }
        return fileList;
    }

    /**
     *
     * @param file file.
     * @param siz size.
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

    /**
     *
     * @param address path.
     * @return list of files.
     */
    public ArrayList<File> searchAll(String address) {
        ArrayList<File> fileList = new ArrayList<>(1);
        File dir = new File(address);
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                fileList.addAll(searchAll(file.getPath()));
            } else {
                fileList.add(file);
            }
        }
        return fileList;
    }

    /**
     *
     * @param container list of files.
     * @param nCriteria number of criteria for searching.
     * @return list of files that match the search criteria.
     */
    public ArrayList<File> definitiveList(ArrayList<File> container, int nCriteria) {
        ArrayList<File> lastList = new ArrayList<>();
        for (File file : container) {
            if (Collections.frequency(container, file) == nCriteria && !lastList.contains(file)) {
                lastList.add(file);
            }
        }
        return lastList;
    }
}
