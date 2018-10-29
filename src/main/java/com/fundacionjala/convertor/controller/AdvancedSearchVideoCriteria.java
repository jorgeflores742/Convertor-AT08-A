package com.fundacionjala.convertor.controller;

/**
 *
 */
public class AdvancedSearchVideoCriteria {
    private int fps;
    private String aspectRatio;
    private String resolution;

    /**
     *
     * @return fps
     */
    public int getFps() {
        return fps;
    }

    /**
     *
     * @param fps fps
     */
    public void setFps(int fps) {
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
}
