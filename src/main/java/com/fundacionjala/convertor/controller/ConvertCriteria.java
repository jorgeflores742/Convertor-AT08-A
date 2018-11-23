/*
 * @ConvertCriteria.java Copyright (c) 2018 Fundacion Jala. All rights reserved.
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
package com.fundacionjala.convertor.controller;

/**
 * Convert criteria.
 * This class has only variables that will be filled with criteria UI set by user.
 *
 * @author Jorge Flores.
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
     * Get name of file.
     * @return name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Set name of file.
     * @param fileName file name.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     *
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
     * Set output format.
     * @param formatTo format to convert.
     */
    public void setFormatTo(String formatTo) {
        this.formatTo = formatTo;
    }

    /**
     * Obtain path source.
     * @return pathFrom.
     */
    public String getPathFrom() {
        return pathFrom;
    }

    /**
     * Set path source.
     * @param pathFrom pathFrom.
     */
    public void setPathFrom(String pathFrom) {
        this.pathFrom = pathFrom;
    }

    /**
     * Obtain output path.
     * @return pathTo.
     */
    public String getPathTo() {
        return pathTo;
    }

    /**
     * Set output path.
     * @param pathTo pathTo.
     */
    public void setPathTo(String pathTo) {
        this.pathTo = pathTo;
    }

    /**
     * Obtain type video.
     * @return cnvVideoType.
     */
    public String getCnvVideoType() {
        return cnvVideoType;
    }

    /**
     * Set type video.
     * @param cnvVideoType cnvVideoType
     */
    public void setCnvVideoType(String cnvVideoType) {
        this.cnvVideoType = cnvVideoType;
    }

    /**
     * Obtain frames per second set.
     * @return cnvFps.
     */
    public String getCnvFps() {
        return cnvFps;
    }

    /**
     * Set Frames per second UI set.
     * @param cnvFps cnvFps.
     */
    public void setCnvFps(String cnvFps) {
        this.cnvFps = cnvFps;
    }

    /**
     * Set Asoect Ratio from UI.
     * @param cnvAspectRatio cnvAspectRatio.
     */
    public void setCnvAspectRatio(String cnvAspectRatio) {
        this.cnvAspectRatio = cnvAspectRatio;
    }

    /**
     * Obtain Video Codec set by user UI.
     * @return cnvVideoCodec
     */
    public String getCnvVideoCodec() {
        return cnvVideoCodec;
    }

    /**
     * Set Video Codec.
     * @param cnvVideoCodec cnvVideoCodec.
     */
    public void setCnvVideoCodec(String cnvVideoCodec) {
        this.cnvVideoCodec = cnvVideoCodec;
    }

    /**
     * Obtain Audio codec from a video.
     * @return cnvVideoAudioCodec.
     */
    public String getCnvVideoAudioCodec() {
        return cnvVideoAudioCodec;
    }

    /**
     * Set Audio codec for a video.
     * @param cnvVideoAudioCodec cnvVideoAudioCodec.
     */
    public void setCnvVideoAudioCodec(String cnvVideoAudioCodec) {
        this.cnvVideoAudioCodec = cnvVideoAudioCodec;
    }

    /**
     * Obtain Audio codec from an audio.
     * @return cnvAudioType.
     */
    public String getCnvAudioType() {
        return cnvAudioType;
    }

    /**
     * Set Audio codec for an audio.
     * @param cnvAudioType cnvAudioType.
     */
    public void setCnvAudioType(String cnvAudioType) {
        this.cnvAudioType = cnvAudioType;
    }

    /**
     * Obtain channels of UI set.
     * @return cnvChannels.
     */
    public String getCnvChannels() {
        return cnvChannels;
    }

    /**
     *Set Channels for audio conversion.
     * @param cnvChannels cnvChannels.
     */
    public void setCnvChannels(String cnvChannels) {
        this.cnvChannels = cnvChannels;
    }

    /**
     * Obtain Audio codec.
     * @return cnvAudioCodec.
     */
    public String getCnvAudioCodec() {
        return cnvAudioCodec;
    }

    /**
     * Set Audio codec.
     * @param cnvAudioCodec cnvAudioCodec.
     */
    public void setCnvAudioCodec(String cnvAudioCodec) {
        this.cnvAudioCodec = cnvAudioCodec;
    }

    /**
     * Obtain Width resolution.
     * @return Width resolution.
     */
    public String getCnvResolutionWidth() {
        return cnvResolutionWidth;
    }

    /**
     * Set Width resolution.
     * @param cnvResolutionWidth Width resolution.
     */
    public void setCnvResolutionWidth(String cnvResolutionWidth) {
        this.cnvResolutionWidth = cnvResolutionWidth;
    }

    /**
     * Obtain Height resolution.
     * @return Height resolution.
     */
    public String getCnvResolutionHeight() {
        return cnvResolutionHeight;
    }

    /**
     * Obtain Height resolution.
     * @param cnvResolutionHeight Height resolution.
     */
    public void setCnvResolutionHeight(String cnvResolutionHeight) {
        this.cnvResolutionHeight = cnvResolutionHeight;
    }
}