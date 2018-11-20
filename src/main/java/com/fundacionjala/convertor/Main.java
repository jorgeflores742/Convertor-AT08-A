package com.fundacionjala.convertor;

import com.fundacionjala.convertor.controller.SearchController;
import com.fundacionjala.convertor.utils.SingleLogger;


/**
 * Main Class.
 */
public class Main {

    //static SingleLogger sL = SingleLogger.getInstance();
    public static final String PATH_TO_FFMPEG_BIN_FFPROBE = "lib\\filesff\\ffprobe";

    /**
     * @param args arguments
     */
    public static void main(final String[] args) {
        //sL.setLogger(Main.class.getName());
        try {
            SearchController searchController = new SearchController();
            //sL.register(null,"FINE","Successful search controller");
        } catch (Exception e) {
            //sL.register(e,"SEVERE","Failed search controller");
        }

    }
}