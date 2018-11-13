package com.fundacionjala.convertor.model;

public class AudioAsset extends Asset {
    private String channels;

    public AudioAsset() {
        super();
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }
}
