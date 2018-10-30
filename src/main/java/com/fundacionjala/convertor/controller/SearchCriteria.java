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
    //Advanced Video
    private String fps;
    private String aspectRatio;
    private String resolution;
    //Advanced Audio
    private String channels;

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

    /**
     *
     * @return fps
     */
    public String getFps() {
        return fps;
    }

    /**
     *
     * @param fps fps
     */
    public void setFps(String fps) {
        this.fps = fps;
    }

    /**
     *
     * @return aspect ratio
     */
    public String getAspectRatio() {
        return aspectRatio;
    }

    /**
     *
     * @param aspectRatio aspect ratio
     */
    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    /**
     *
     * @return resolution
     */
    public String getResolution() {
        return resolution;
    }

    /**
     *
     * @param resolution resolution
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    /**
     *
     * @return channels
     */
    public String getChannels() {
        return channels;
    }

    /**
     *
     * @param channels channels
     */
    public void setChannels(String channels) {
        this.channels = channels;
    }
}
