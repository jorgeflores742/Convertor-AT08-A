package com.fundacionjala.convertor.controller;

import com.fundacionjala.convertor.model.FileSearcher;
import com.fundacionjala.convertor.view.AdvancedSearchVideo;
import com.fundacionjala.convertor.view.AdvancedSearchAudio;
import com.fundacionjala.convertor.view.ListFileView;
import com.fundacionjala.convertor.view.SearchViewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Module Controller SearchController.
 *
 * @author Melvi Caballero.
 * @version 1.0.
 */
public class SearchController implements ActionListener {
    //private SearchView searchView;
    private SearchViewer searchViewer;
    private AdvancedSearchVideo advancedSearchVideo;
    private AdvancedSearchAudio advancedSearchAudio;
    private ListFileView listFileView;
    private FileSearcher fileSearcher;
    private SearchCriteria searchCriteria;

    /**
     *
     * @param searchViewer searchViewer
     * @param advancedSearchVideo advancedSearchVideo
     * @param advancedSearchAudio advancedSearchAudio
     * @param listFileView listFileView
     * @param fileSearcher fileSearcher
     */
    public SearchController(final SearchViewer searchViewer, final AdvancedSearchVideo advancedSearchVideo,final AdvancedSearchAudio advancedSearchAudio, final ListFileView listFileView, final FileSearcher fileSearcher) {
        //this.searchView = searchView;
        this.searchViewer= searchViewer;
        this.advancedSearchVideo = advancedSearchVideo;
        this.advancedSearchAudio = advancedSearchAudio;
        this.listFileView=listFileView;
        this.fileSearcher = fileSearcher;
        this.searchViewer.getBtnSearch().addActionListener(this);
        searchCriteria = new SearchCriteria();
    }

    /**
     *
     */

    public void loadCriteria() {
        searchCriteria.setName(searchViewer.getTxtName());
        searchCriteria.setPath(searchViewer.getTxtPath());
        searchCriteria.setExt(searchViewer.getCmbFileType());
        searchCriteria.setSize(searchViewer.getCmbSize());
        searchCriteria.setFps(advancedSearchVideo.getCmbFps());
        searchCriteria.setAspectRatio(advancedSearchVideo.getCmbAspectRatio());
        searchCriteria.setResolution(advancedSearchVideo.getCmbResolution());
        searchCriteria.setChannels(advancedSearchVideo.getCmbChannels());

    }

    /**
     * @param e action btnConvert
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == searchViewer.getBtnSearch()) {
            ArrayList<File> resultList = fileSearcher.search(searchCriteria.getPath(), searchCriteria.getName(),
                    searchCriteria.getExt(), searchCriteria.getSize());

            for (File resu : resultList) {
               // listFileView.getListModel().addElement(resu.getAbsolutePath());
            }

        }

    }
}
