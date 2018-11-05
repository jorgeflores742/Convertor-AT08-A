package com.fundacionjala.convertor;

import com.fundacionjala.convertor.controller.SearchController;
import com.fundacionjala.convertor.model.FileSearcher;
import com.fundacionjala.convertor.view.*;
import com.sun.jna.NativeLibrary;

/**
 * Main Class.
 */
public class Main {


    public static final String PATH_TO_VIDEO_LAN_VLC = "c:\\Program Files (x86)\\VideoLAN\\VLC\\";
    public static final String PATH_TO_FFMPEG_BIN_FFPROBE = "c:\\otros\\ffmpeg\\bin\\ffprobe";

    /**
     * @param args arguments
     */
    public static void main(final String[] args) {
        NativeLibrary.addSearchPath("libvlc", PATH_TO_VIDEO_LAN_VLC);

        SearchViewer searchViewer = new SearchViewer();
        AdvancedSearchVideo advancedSearchVideo = new AdvancedSearchVideo();
        AdvancedSearchAudio advancedSearchAudio = new AdvancedSearchAudio();
        ListFileView listFileView = new ListFileView();
        FileSearcher fileSearcher = new FileSearcher();
        SearchController searchController = new SearchController(searchViewer, advancedSearchVideo, advancedSearchAudio,listFileView, fileSearcher);
        ViewConverter v = new ViewConverter(searchViewer ,advancedSearchAudio, advancedSearchVideo,listFileView);
        v.setVisible(true);
    }
}
