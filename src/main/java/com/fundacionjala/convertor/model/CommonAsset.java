package com.fundacionjala.convertor.model;

import com.fundacionjala.convertor.utils.SingleLogger;

public class CommonAsset extends Asset {
    private static SingleLogger sL = SingleLogger.getInstanceLogger();

    public CommonAsset () {
        super();
        sL.register(null, "INFO", "Successful - CommonAsset - start");
    }
}