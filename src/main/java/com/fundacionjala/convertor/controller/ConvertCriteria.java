package com.fundacionjala.convertor.controller;

/**
 * Module View MainView.
 *
 * @author Melvi Caballero.
 * @version 1.0.
 */
public class ConvertCriteria {
    private String fileName = null; //example.mp4
    private String formatFrom = null; // mp4
    private String formatTo = null; // avi
    private String pathFrom = null; //c:users
    private String pathTo = null;  //c:usersDocuments

    //Advanced Video
    private String cnvVideoType = null;
    private String cnvFps = null; //24
    private String cnvAspectRatio = null;

    private String cnvResolutionWidth = null;
    private String cnvResolutionHeight = null;
    private String cnvVideoCodec = null;
    private String cnvVideoAudioCodec = null;
    //Advanced Audio
    private String cnvAudioType = null;
    private String cnvChannels = null;
    private String cnvAudioCodec = null;

    /**
     * @return file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return format from convert
     */
    public String getFormatFrom() {
        return formatFrom;
    }

    /**
     * @param formatFrom format from convert
     */
    public void setFormatFrom(String formatFrom) {
        this.formatFrom = formatFrom;
    }

    /**
     * @return format to convert
     */
    public String getFormatTo() {
        return formatTo;
    }

    /**
     * @param formatTo format to convert
     */
    public void setFormatTo(String formatTo) {
        this.formatTo = formatTo;
    }

    /**
     * @return pathFrom
     */
    public String getPathFrom() {
        return pathFrom;
    }

    /**
     * @param pathFrom pathFrom
     */
    public void setPathFrom(String pathFrom) {
        this.pathFrom = pathFrom;
    }

    /**
     * @return pathTo
     */
    public String getPathTo() {
        return pathTo;
    }

    /**
     * @param pathTo pathTo
     */
    public void setPathTo(String pathTo) {
        this.pathTo = pathTo;
    }

    /**
     * @return cnvVideoType
     */
    public String getCnvVideoType() {
        return cnvVideoType;
    }

    /**
     * @param cnvVideoType cnvVideoType
     */
    public void setCnvVideoType(String cnvVideoType) {
        this.cnvVideoType = cnvVideoType;
    }

    /**
     * @return cnvFps
     */
    public String getCnvFps() {
        return cnvFps;
    }

    /**
     * @param cnvFps cnvFps
     */
    public void setCnvFps(String cnvFps) {
        this.cnvFps = cnvFps;
    }

    /**
     * @return cnvAspectRatio
     */
    public String getCnvAspectRatio() {
        return cnvAspectRatio;
    }

    /**
     * @param cnvAspectRatio cnvAspectRatio
     */
    public void setCnvAspectRatio(String cnvAspectRatio) {
        this.cnvAspectRatio = cnvAspectRatio;
    }

    /**
     * @return cnvVideoCodec
     */
    public String getCnvVideoCodec() {
        return cnvVideoCodec;
    }

    /**
     * @param cnvVideoCodec cnvVideoCodec
     */
    public void setCnvVideoCodec(String cnvVideoCodec) {
        this.cnvVideoCodec = cnvVideoCodec;
    }

    /**
     * @return cnvVideoAudioCodec
     */
    public String getCnvVideoAudioCodec() {
        return cnvVideoAudioCodec;
    }

    /**
     * @param cnvVideoAudioCodec cnvVideoAudioCodec
     */
    public void setCnvVideoAudioCodec(String cnvVideoAudioCodec) {
        this.cnvVideoAudioCodec = cnvVideoAudioCodec;
    }

    /**
     * @return cnvAudioType
     */
    public String getCnvAudioType() {
        return cnvAudioType;
    }

    /**
     * @param cnvAudioType cnvAudioType
     */
    public void setCnvAudioType(String cnvAudioType) {
        this.cnvAudioType = cnvAudioType;
    }

    /**
     * @return cnvChannels
     */
    public String getCnvChannels() {
        if (cnvChannels == "Mono") {
            return "1";

        }else if(cnvChannels == "Stereo") {
            return "2";
        }
        return cnvChannels;
    }

    /**
     * @param cnvChannels cnvChannels
     */
    public void setCnvChannels(String cnvChannels) {
        this.cnvChannels = cnvChannels;
    }

    /**
     * @return cnvAudioCodec
     */
    public String getCnvAudioCodec() {
        return cnvAudioCodec;
    }

    /**
     * @param cnvAudioCodec cnvAudioCodec
     */
    public void setCnvAudioCodec(String cnvAudioCodec) {
        this.cnvAudioCodec = cnvAudioCodec;
    }

    public String getCnvResolutionWidth() {
        return cnvResolutionWidth;
    }

    public void setCnvResolutionWidth(String cnvResolutionWidth) {
        this.cnvResolutionWidth = cnvResolutionWidth;
    }

    public String getCnvResolutionHeight() {
        return cnvResolutionHeight;
    }

    public void setCnvResolutionHeight(String cnvResolutionHeight) {
        this.cnvResolutionHeight = cnvResolutionHeight;
    }
}