package com.fundacionjala.convertor.controller;

import com.fundacionjala.convertor.model.AdvancedSearchAudio;
import com.fundacionjala.convertor.model.AdvancedSearchVideo;
import com.fundacionjala.convertor.model.FileSearcher;
import com.fundacionjala.convertor.view.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Module Controller SearchController.
 *
 * @author Melvi Caballero.
 * @version 1.0.
 */
public class SearchController implements ActionListener,ListSelectionListener {
    //private SearchView searchView;
    private SearchViewer searchViewer;
    private AdvancedSearchVideoView advancedSearchVideo;
    private AdvancedSearchAudioView advancedSearchAudio;
    private ListFileView listFileView;
    private FileSearcher fileSearcher;
    private SearchCriteria searchCriteria;
    private DataFiles dataFiles;
    private AdvancedSearchAudio audio;
    private AdvancedSearchVideo video;
    private int lock = -1;
    private ArrayList<File> advanceResult = new ArrayList<>(1);

    /**
     *
     * @param searchViewer searchViewer
     * @param advancedSearchVideo advancedSearchVideo
     * @param advancedSearchAudio advancedSearchAudio
     * @param listFileView listFileView
     * @param fileSearcher fileSearcher
     */
    public SearchController(final SearchViewer searchViewer, final AdvancedSearchVideoView advancedSearchVideo, final AdvancedSearchAudioView advancedSearchAudio, final ListFileView listFileView, final FileSearcher fileSearcher, final DataFiles dataFiles, final AdvancedSearchAudio audio, final AdvancedSearchVideo video) {
        //this.searchView = searchView;
        this.searchViewer= searchViewer;
        this.advancedSearchVideo = advancedSearchVideo;
        this.advancedSearchAudio = advancedSearchAudio;
        this.listFileView=listFileView;
        this.listFileView.getLstSearchResult().addListSelectionListener(this);
        this.fileSearcher = fileSearcher;
        this.searchViewer.getBtnSearch().addActionListener(this);
        this.searchViewer.getBtnClearList().addActionListener(this);
        this.dataFiles = dataFiles;
        this.audio=audio;
        this.video=video;
        searchCriteria = new SearchCriteria();
    }

    /**
     *
     */

    public void loadCriteria() {
        searchCriteria.setName(searchViewer.getTxtName().getText());
        searchCriteria.setPath(searchViewer.getTxtPath().getText());
        searchCriteria.setSize(searchViewer.getCmbSize().getSelectedItem().toString());
        String type = searchViewer.getCmbFileType().getSelectedItem().toString();
        if (type.equals("All")) {
            searchCriteria.setExt("");
        } else if (type.equals("Video")) {
            searchCriteria.setVideoType(advancedSearchVideo.getCmbType().getSelectedItem().toString());
            searchCriteria.setFps(advancedSearchVideo.getCmbFps().getSelectedItem().toString());
            searchCriteria.setAspectRatio(advancedSearchVideo.getCmbAspectRatio().getSelectedItem().toString());
            searchCriteria.setResolution(advancedSearchVideo.getCmbResolution().getSelectedItem().toString());
        } else if (type.equals("Audio")) {
            searchCriteria.setAudioType(advancedSearchAudio.getCmbType().getSelectedItem().toString());
            searchCriteria.setChannels(advancedSearchAudio.getCmbChannels().getSelectedItem().toString());
        }
        //searchCriteria.setExt(searchViewer.getCmbFileType().getSelectedItem().toString());

    }

    /**
     * @param e action btnConvert
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == searchViewer.getBtnSearch()) {
            String type = searchViewer.getCmbFileType().getSelectedItem().toString();
            loadCriteria();
            ArrayList<File> resultList = fileSearcher.search(searchCriteria.getPath(), searchCriteria.getName(),
                    searchCriteria.getExt(), searchCriteria.getSize());
            advanceResult.clear();
            if (type.equals("Audio")) {
                advanceResult = audio.FilterCriteria(resultList, searchCriteria);
            } else if (type.equals("Video")) {
                advanceResult = video.FilterCriteria(resultList, searchCriteria);
            } else {
                advanceResult = resultList;
            }
            for (File resu : advanceResult) {
              listFileView.getListModel().addElement(resu.getAbsolutePath());
            }

        }
        else if(e.getSource() == searchViewer.getBtnClearList()) {
            listFileView.setListModel(listFileView.getListModel());
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        lock = (lock*(-1));
        if (lock == 1) {
            dataFiles.getDefaultList().clear();
            String value = listFileView.getLstSearchResult().getSelectedValue().toString();
            System.out.println(value);
            dataFiles.getDefaultList().addElement(value);
            File selectedFile = getFile(value);
            dataFiles.getDefaultList().addElement(selectedFile.getName());
            dataFiles.getDefaultList().addElement(selectedFile.length());
            try {
                System.out.println(Files.probeContentType(selectedFile.toPath()));
                dataFiles.getDefaultList().addElement(Files.probeContentType(selectedFile.toPath()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public File getFile(String value) {
        for (File f: advanceResult) {
            if (f.getAbsolutePath().equals(value)) {
                return f;
            }
        }
        return null;
    }
}
