package com.fundacionjala.convertor;

import com.fundacionjala.convertor.controller.SearchController;
import com.fundacionjala.convertor.utils.SingleLogger;


/**
 * Main Class.
 */
public class Main {

    public static final String PATH_TO_FFMPEG_BIN_FFPROBE = "lib\\filesff\\ffprobe";
    private static SingleLogger sL = SingleLogger.getInstanceLogger();
    /**
     * @param args arguments
     */
    public static void main(final String[] args) {
            sL.setLogger(Main.class.getName());
            sL.register(null,"INFO","Successful - SearchController - start");
            SearchController searchController = new SearchController();
            sL.register(null,"INFO","Successful - SearchController - finished");
    }
}