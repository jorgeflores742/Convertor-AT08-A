package com.fundacionjala.convertor;

import com.fundacionjala.convertor.controller.SearchController;
import com.fundacionjala.convertor.model.FileSearcher;
import com.fundacionjala.convertor.view.*;

/**
 * Main Class.
 */
public class Main {
    /**
     * @param args arguments
     */
    public static void main(final String[] args) {

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
