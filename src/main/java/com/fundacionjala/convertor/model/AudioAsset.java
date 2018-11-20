package com.fundacionjala.convertor.model;

import com.fundacionjala.convertor.utils.SingleLogger;

public class AudioAsset extends Asset {
    private String channels;
    private String audioCodec;
    private static SingleLogger sL = SingleLogger.getInstanceLogger();

    public AudioAsset() {
        super();
        sL.setLogger(AudioAsset.class.getName());
        sL.register(null, "INFO", "Successful - AudioAsset - start");
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
