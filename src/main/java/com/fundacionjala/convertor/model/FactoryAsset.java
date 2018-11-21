package com.fundacionjala.convertor.model;

public class FactoryAsset {

    public static Asset getAsset(String assetKind) {
        if (assetKind.equals("Video asset")) {
            return new VideoAsset();
        } else if (assetKind.equals("Audio asset")) {
            return new AudioAsset();
        } else {
            return new CommonAsset();
        }
    }
}
