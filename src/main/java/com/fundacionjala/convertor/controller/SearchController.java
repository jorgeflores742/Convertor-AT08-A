package com.fundacionjala.convertor.controller;

import com.fundacionjala.convertor.model.AdvancedSearchAudio;
import com.fundacionjala.convertor.model.AdvancedSearchVideo;
import com.fundacionjala.convertor.model.FileSearcher;
import com.fundacionjala.convertor.view.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Module Controller SearchController.
 *
 * @author Melvi Caballero.
 * @version 1.0.
 */
public class SearchController implements ActionListener, ListSelectionListener {
    //private SearchView searchView;
    private SearchViewer searchViewer;
    private AdvancedSearchVideoView advancedSearchVideo;
    private AdvancedSearchAudioView advancedSearchAudio;
    private ListFileView listFileView;
    private FileSearcher fileSearcher = new FileSearcher();
    private SearchCriteria searchCriteria;
    private DataFiles dataFiles;
    private int lock = -1;
    private ArrayList<File> advanceResult = new ArrayList<>(1);


    public SearchController() {

        ViewConverter v = new ViewConverter();
        v.setVisible(true);
        this.searchViewer = v.getSearchviewer();
        this.searchViewer.getBtnSearch().addActionListener(this);
        this.searchViewer.getBtnClearList().addActionListener(this);
        this.advancedSearchVideo = v.getAdvSearchVideoView();
        this.advancedSearchAudio = v.getAdvSearchAudioView();
        this.listFileView = v.getListFile();
        this.listFileView.getLstSearchResult().addListSelectionListener(this);
        this.dataFiles = v.getData();
        searchCriteria = new SearchCriteria();
    }

    /**
     *
     */

    public void loadCriteria() {
        searchCriteria.setName(searchViewer.getTxtName().getText());
        System.out.println(searchViewer.getTxtPath().getText());
        searchCriteria.setPath(searchViewer.getTxtPath().getText());
        searchCriteria.setSize(searchViewer.getCmbSize().getSelectedItem().toString());
        String type = searchViewer.getCmbFileType().getSelectedItem().toString();
        searchCriteria.setAdvancedType(type);
        if (type.equals("All")) {
            searchCriteria.setExt("");
            searchCriteria.setVideoType("All");
            searchCriteria.setFps("All");
            searchCriteria.setAspectRatio("All");
            searchCriteria.setResolution("All");
            searchCriteria.setAudioType("All");
            searchCriteria.setChannels("All");
            searchCriteria.setVideoCodec("All");
            searchCriteria.setVideoAudioCodec("All");
            searchCriteria.setAudioCodec("All");
        } else if (type.equals("Video")) {
            searchCriteria.setVideoType(advancedSearchVideo.getCmbType().getSelectedItem().toString());
            searchCriteria.setFps(advancedSearchVideo.getCmbFps().getSelectedItem().toString());
            searchCriteria.setAspectRatio(advancedSearchVideo.getCmbAspectRatio().getSelectedItem().toString());
            searchCriteria.setResolution(advancedSearchVideo.getCmbResolution().getSelectedItem().toString());
            //searchCriteria.setVideoCodec(advancedSearchVideo);
            //searchCriteria.setVideoAudioCodec(advancedSearchVideo);
            //searchCriteria.setAudioCodec(advancedSearchAudio);
            searchCriteria.setAudioType("All");
            searchCriteria.setChannels("All");
            searchCriteria.setAudioCodec("All");
        } else if (type.equals("Audio")) {
            searchCriteria.setAudioType(advancedSearchAudio.getCmbType().getSelectedItem().toString());
            searchCriteria.setChannels(advancedSearchAudio.getCmbChannels().getSelectedItem().toString());
            //searchCriteria.setAudioCodec(advancedSearchAudio);
            searchCriteria.setVideoType("All");
            searchCriteria.setFps("All");
            searchCriteria.setAspectRatio("All");
            searchCriteria.setResolution("All");
            searchCriteria.setVideoCodec("All");
            searchCriteria.setVideoAudioCodec("All");
        }

    }

    /**
     * @param e action btnConvert
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == searchViewer.getBtnSearch() && !searchViewer.getTxtPath().getText().equals("")) {
            listFileView.getListModel().clear();
            loadCriteria();
            advanceResult.clear();
            advanceResult.addAll(fileSearcher.search(searchCriteria));

            for (File resu : advanceResult) {
                listFileView.getListModel().addElement(resu.getAbsolutePath());
            }

        } else if (e.getSource() == searchViewer.getBtnClearList()) {
            listFileView.getListModel().clear();
            listFileView.getLstSearchResult().updateUI();
            dataFiles.getDefaultList().clear();
        } else if (searchViewer.getTxtPath().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please, define a valid directory");
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        lock = (lock * (-1));
        if (lock == 1) {
            dataFiles.getDefaultList().clear();
            String value = listFileView.getLstSearchResult().getSelectedValue().toString();
            System.out.println(value);
            dataFiles.getDefaultList().addElement(value);
            File selectedFile = getFile(value);
            Path path = Paths.get(selectedFile.getAbsolutePath());
            BasicFileAttributes attrib = null;
            try {
                attrib = Files.readAttributes(path, BasicFileAttributes.class);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            FileTime fileTime = attrib.creationTime();
            dataFiles.getDefaultList().addElement("Name: ".concat(selectedFile.getName()));
            dataFiles.getDefaultList().addElement("Size: ".concat(Long.toString(attrib.size())).concat(" bytes"));
            dataFiles.getDefaultList().addElement("Creation time: ".concat(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(fileTime.toMillis())));
            try {
                String typeFile = Files.probeContentType(path);
                dataFiles.getDefaultList().addElement("Type: ".concat(typeFile==null? "Unknown" : typeFile));
            } catch (IOException e1) {
                System.out.println("Tipo no reconocido");
            }
        }
    }


    public File getFile(String value) {
        for (File f : advanceResult) {
            if (f.getAbsolutePath().equals(value)) {
                return f;
            }
        }
        return null;
    }
}
