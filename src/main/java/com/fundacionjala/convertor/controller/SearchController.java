package com.fundacionjala.convertor.controller;

import com.fundacionjala.convertor.model.*;
import com.fundacionjala.convertor.view.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Module Controller SearchController.
 *
 * @author Melvi Caballero.
 * @version 1.0.
 */
public class SearchController implements ActionListener, ListSelectionListener {
    //private SearchView searchView;
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


    public SearchController() {

        v = new NewWindows();
        v.setVisible(true);
        this.searchViewer = v.getsView();
        this.searchViewer.getBtnSearch().addActionListener(this);
        this.searchViewer.getBtnClearList().addActionListener(this);
        this.listFileView = v.getListFile();
        this.listFileView.getLstSearchResult().addListSelectionListener(this);
        this.dataFiles = v.getData();
        this.listConverting = v.getListConv();
        searchCriteria = new SearchCriteria();
        ConvertList convertList = new ConvertList();
        String[] listConv = convertList.convertLis();
        convertController = new ConvertController(v);
        convertController.showList(convertList.convertLis());
    }

    /**
     *
     */

    public void loadCriteria() {
        searchCriteria.setName(searchViewer.getTxtName().getText().toLowerCase());
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

            for (Asset resu : advanceResult) {
                listFileView.getListModel().addElement(resu.getPath());
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
        if (lock == 1 && listFileView.getLstSearchResult().getSelectedIndex() != (-1)) {
            dataFiles.getDefaultList().clear();
            String value = listFileView.getLstSearchResult().getSelectedValue().toString();
            System.out.println(value);

            Asset infoAsset = new Asset();

            infoAsset = getInfoAsset(value);

            convertController.getConvertCriteria().setPathFrom(value);

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
            }
        }
    }


    private Asset getInfoAsset(String value) {
        Asset assetSelected = new Asset();
        for (Asset as : advanceResult) {
            if (as.getPath().equals(value)) {
                assetSelected = as;
            }
        }
        return assetSelected;
    }
}
