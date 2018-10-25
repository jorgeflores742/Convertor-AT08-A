package com.fundacionjala.convertor.controller;

/**
 * Module View MainView.
 *
 * @author Melvi Caballero.
 * @version 1.0.
 */
public class SearchCriteria {
    private String path;
    private String name;
    private String ext;
    private String size;

    /**
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return name file name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name file name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return extension
     */
    public String getExt() {
        return ext;
    }

    /**
     * @param ext extension
     */
    public void setExt(String ext) {
        this.ext = ext;
    }

    /**
     * @return size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size size file
     */
    public void setSize(String size) {
        this.size = size;
    }
}
