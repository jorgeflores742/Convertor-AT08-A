package com.fundacionjala.convertor.model;

public class AudioAsset extends Asset {
    private String channels;
    private String audioCodec;

    public AudioAsset() {
        super();
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    public void setAudioCodec(String audioCodec) {
        this.audioCodec = audioCodec;
    }

    public String getAudioCodec() {
        return audioCodec;
    }
}
