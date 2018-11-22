/*
 * @SearchController.java Copyright (c) 2018 Fundacion Jala. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * Please contact Fundacion Jala, 2643 Av Melchor Perez de Olguin, Colquiri
 * Sud, Cochabamba, Bolivia. www.fundacion-jala.org if you need additional
 * information or have any questions.
 */
package com.fundacionjala.convertor.controller;

import com.fundacionjala.convertor.model.*;
import com.fundacionjala.convertor.view.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import com.fundacionjala.convertor.utils.SingleLogger;

/**
 * Module Controller SearchController.
 * Controller obtains search data set by user in UI.
 * Then, controller sends data to search model.
 * @author Melvi Caballero and Dennis Montaño.
 * @version 1.0.
 */
public class SearchController implements ActionListener, ListSelectionListener {
    private NewSearchViewer searchViewer;
    private ListFileView listFileView;
    private FileSearcher fileSearcher = new FileSearcher();
    private SearchCriteria searchCriteria;
    private DataFiles dataFiles;
    private int lock = -1;
    private ArrayList<Asset> advanceResult = new ArrayList<>(1);
    private NewWindows v;
    private ConvertController convertController;
    private ListConverting listConverting = new ListConverting();
    private int emptyPathFlag = 0;
    private StringBuilder dataFilesMessage = new StringBuilder("message for valued changed");
    private static SingleLogger sL = SingleLogger.getInstanceLogger();

    public SearchController() {
        sL.setLogger(SearchController.class.getName());
        sL.register(null, "INFO", "Successful - SearchController - start");
        v = new NewWindows();
        v.setVisible(true);
        this.searchViewer = v.getsView();
        playervlcj("img\\chamaleonDefault.png","mp4");
        this.searchViewer.getBtnSearch().addActionListener(this);
        this.searchViewer.getBtnClearList().addActionListener(this);
        this.listFileView = v.getListFile();
        this.listFileView.getLstSearchResult().addListSelectionListener(this);
        this.dataFiles = v.getData();
        searchCriteria = new SearchCriteria();
        convertController = new ConvertController(v);
        sL.register(null, "INFO", "Successful - SearchController - finished");
    }

    /**
     * Fill criteria instance with UI data.
     */
    private void loadCriteria() {
        sL.register(null, "INFO", "Successful - loadCriteria - start");
        searchCriteria.setName(searchViewer.getTxtName().getText().toLowerCase());
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
            searchCriteria.setVideoCodec("All");
            searchCriteria.setVideoAudioCodec("All");
            searchCriteria.setAudioType("All");
            searchCriteria.setChannels("All");
            searchCriteria.setAudioCodec("All");
        } else if (type.equals("Video")) {
            searchCriteria.setVideoType(searchViewer.getCmbType().getSelectedItem().toString());
            searchCriteria.setFps(searchViewer.getCmbFps().getSelectedItem().toString());
            searchCriteria.setAspectRatio(searchViewer.getCmbAspectRatio().getSelectedItem().toString());
            searchCriteria.setResolution(searchViewer.getCmbResolution().getSelectedItem().toString());
            searchCriteria.setVideoCodec(searchViewer.getCmbVideoVC().getSelectedItem().toString());
            searchCriteria.setVideoAudioCodec(searchViewer.getCmbVideoAC().getSelectedItem().toString());
            searchCriteria.setAudioType("All");
            searchCriteria.setChannels("All");
            searchCriteria.setAudioCodec("All");
        } else if (type.equals("Audio")) {
            searchCriteria.setAudioType(searchViewer.getCmbTypeAudio().getSelectedItem().toString());
            searchCriteria.setChannels(searchViewer.getCmbChannels().getSelectedItem().toString());
            searchCriteria.setAudioCodec(searchViewer.getCmbAudioAC().getSelectedItem().toString());
            searchCriteria.setVideoType("All");
            searchCriteria.setFps("All");
            searchCriteria.setAspectRatio("All");
            searchCriteria.setResolution("All");
            searchCriteria.setVideoCodec("All");
            searchCriteria.setVideoAudioCodec("All");
        }
        sL.register(null, "INFO", "Successful - loadCriteria - finished");
    }

    /**
     * When button search is pressed.
     * @param e action btnSearch.
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        sL.register(null, "INFO", "Successful - actionPerformed - start");
        if (e.getSource() == searchViewer.getBtnSearch() && !searchViewer.getTxtPath().getText().equals("")) {
            emptyPathFlag = 0;
            listFileView.getListModel().clear();
            loadCriteria();
            advanceResult.clear();
            advanceResult.addAll(fileSearcher.search(searchCriteria));

            for (Asset resu : advanceResult) {
                listFileView.getListModel().addElement(resu.getPath());
            }
        } else if (e.getSource() == searchViewer.getBtnClearList()) {
            emptyPathFlag = 0;
            listFileView.getListModel().clear();
            listFileView.getLstSearchResult().updateUI();
            dataFiles.getDefaultList().clear();
        } else if (searchViewer.getTxtPath().getText().equals("")) {
            emptyPathFlag = 1;
            JOptionPane.showMessageDialog(null, "Please, define a valid directory");
        }
        sL.register(null, "INFO", "Successful - actionPerformed finished");
    }

    /**
     * Method runs when
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        sL.register(null, "INFO", "Successful - valueChanged - start");

        lock = (lock * (-1));
        if (lock == 1 && listFileView.getLstSearchResult().getSelectedIndex() != (-1)) {
            dataFiles.getDefaultList().clear();
            String value = listFileView.getLstSearchResult().getSelectedValue().toString();
            Asset infoAsset = new Asset();
            infoAsset = getInfoAsset(value);
            convertController.getConvertCriteria().setPathFrom(value);
            String ext = infoAsset.getNameFile().substring(infoAsset.getNameFile().lastIndexOf('.') + 1);
            playervlcj(value,ext.toLowerCase());
            dataFiles.getDefaultList().addElement(infoAsset.getPath());
            dataFiles.getDefaultList().addElement(infoAsset.getNameFile());
            dataFiles.getDefaultList().addElement(infoAsset.getSizeFile());
            dataFiles.getDefaultList().addElement(infoAsset.getCreationFile());
            if (infoAsset.getTypeFile() == null) {

            } else if (infoAsset.getTypeFile().contains("Video")) {
                v.getConverting().setTxtName(
                        infoAsset.getNameFile().substring(infoAsset.getNameFile().lastIndexOf(':') + 2,
                                infoAsset.getNameFile().lastIndexOf('.'))
                );
                dataFiles.getDefaultList().addElement(infoAsset.getTypeFile());
                VideoAsset video = new VideoAsset();
                video = (VideoAsset) infoAsset;
                dataFiles.getDefaultList().addElement(video.getFps());
                dataFiles.getDefaultList().addElement(video.getAspectRatio());
                dataFiles.getDefaultList().addElement(video.getResolution());
                dataFiles.getDefaultList().addElement(video.getDuration());
                dataFiles.getDefaultList().addElement(video.getVideoCodec());
                dataFiles.getDefaultList().addElement(video.getAudioCodec());
                dataFilesMessage.delete(0, dataFilesMessage.length());
                dataFilesMessage.append("video");
            } else if (infoAsset.getTypeFile().contains("Audio")) {
                v.getConverting().setTxtName(
                        infoAsset.getNameFile().substring(infoAsset.getNameFile().lastIndexOf(':') + 2,
                                infoAsset.getNameFile().lastIndexOf('.'))
                );
                dataFiles.getDefaultList().addElement(infoAsset.getTypeFile());
                AudioAsset audio = new AudioAsset();
                audio = (AudioAsset) infoAsset;
                dataFiles.getDefaultList().addElement(audio.getChannels());
                dataFiles.getDefaultList().addElement(audio.getAudioCodec());
                dataFilesMessage.delete(0, dataFilesMessage.length());
                dataFilesMessage.append("audio");
            }
        }
        sL.register(null, "INFO", "Successful - valueChanged - finished");
    }


    private Asset getInfoAsset(String value) {
        Asset assetSelected = new Asset();
        sL.register(null, "INFO", "Successful - getInfoAsset - start");

        for (Asset as : advanceResult) {
            if (as.getPath().equals(value)) {
                assetSelected = as;
            }
        }
        sL.register(null, "INFO", "Successful - getInfoAsset - finished");
        return assetSelected;
    }

    private void playervlcj(String value, String ext) {
        ArrayList<String> conteinerAV= new ArrayList<String>();
        for(int i = 1; i < searchViewer.getCmbType().getItemCount(); i++)
        {
            conteinerAV.add(searchViewer.getCmbType().getItemAt(i).toString());
        }
        for(int i = 1; i < searchViewer.getCmbTypeAudio().getItemCount(); i++)
        {
            conteinerAV.add(searchViewer.getCmbTypeAudio().getItemAt(i).toString());
        }
        if(conteinerAV.contains(ext)) {
            v.getPlayerM().setRouteFile(value,true);
        }else if(conteinerAV.contains(ext)) {
            v.getPlayerM().setRouteFile(value,true);
        }else{
            v.getPlayerM().setRouteFile(value,false);
            JOptionPane.showMessageDialog(null, "File is not multimedia, is not posible to play. \n Last file multimedia wil be played again", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public NewSearchViewer getSearchViewer() {
        return searchViewer;
    }

    public ListFileView getListFileView() {
        return listFileView;
    }

    public int getEmptyPathFlag() {
        return emptyPathFlag;
    }

    public void setLock(int lock) {
        this.lock = lock;
    }

    public String getDataFilesName() {
        return dataFiles.getDefaultList().getElementAt(1).toString();
    }
}
