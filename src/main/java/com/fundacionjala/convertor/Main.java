package com.fundacionjala.convertor;

import com.fundacionjala.convertor.controller.SearchController;
import com.fundacionjala.convertor.model.AdvancedSearchAudio;
import com.fundacionjala.convertor.model.AdvancedSearchVideo;
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
        AdvancedSearchVideoView advancedSearchVideo = new AdvancedSearchVideoView();
        AdvancedSearchAudioView advancedSearchAudio = new AdvancedSearchAudioView();
        ListFileView listFileView = new ListFileView();
        FileSearcher fileSearcher = new FileSearcher();
        DataFiles dataFiles = new DataFiles();
        AdvancedSearchAudio audio = new AdvancedSearchAudio();
        AdvancedSearchVideo video = new AdvancedSearchVideo();
        SearchController searchController = new SearchController(searchViewer, advancedSearchVideo, advancedSearchAudio,listFileView, fileSearcher, dataFiles, audio, video);
        ViewConverter v = new ViewConverter(searchViewer ,advancedSearchAudio, advancedSearchVideo,listFileView,dataFiles);
        v.setVisible(true);
    }
}
