/*
 * @ConvertController.java Copyright (c) 2018 Fundacion Jala. All rights reserved.
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

import com.fundacionjala.convertor.model.ConvertFileAudio;
import com.fundacionjala.convertor.model.ConvertFileVideo;
import com.fundacionjala.convertor.model.ConvertList;
import com.fundacionjala.convertor.model.IConvertFile;
import com.fundacionjala.convertor.utils.SingleLogger;
import com.fundacionjala.convertor.view.Converter;
import com.fundacionjala.convertor.view.ListConverting;
import com.fundacionjala.convertor.view.NewWindows;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Module Controller to conversion.
 * Controller obtains data setting in UI for conversion and then,
 *  sends those data to convert model.
 *
 * @author Jorge Flores.
 * @version 1.0.
 */
public class ConvertController implements ActionListener {
    private Converter converter;
    private IConvertFile convertFile;
    private ConvertCriteria convertCriteria;
    private ListConverting listConverting;
    private static int progress;
    private static SingleLogger sL = SingleLogger.getInstanceLogger();

    /**
     * Constructor receives the main window.
     * @param nW main class of View part.
     */
    public ConvertController(NewWindows nW) {
        sL.setLogger(ConvertController.class.getName());
        sL.register(null, "INFO", "Successful - ConvertController - start");
        this.converter = nW.getConverting();
        this.listConverting = nW.getListConv();
        converter.getBtnConvert().addActionListener(this);
        convertCriteria = new ConvertCriteria();
        ConvertList convertList = new ConvertList();
        String[] listConv = convertList.convertLis();
        showList(listConv);
        sL.register(null, "INFO", "Successful - ConvertController - finished");
    }

    /**
     * Criteria for conversion is loaded from UI.
     * @param convertCriteria class to fill criteria conversion.
     */
    private void loadConvertCriteria(ConvertCriteria convertCriteria) {
        sL.register(null, "INFO", "Successful - loadConvertCriteria - start");
        convertCriteria.setFileName(converter.getTxtName().getText());
        convertCriteria.setFormatTo(converter.getCmbType().getSelectedItem().toString());
        convertCriteria.setPathTo(converter.getTxtPathSave().getText());
        if(converter.getCmbConvertTo().getSelectedItem().toString().equals("Video")) {
            if(!converter.getCmbType().getSelectedItem().toString().equals("Select")) {convertCriteria.setCnvVideoType(converter.getCmbType().getSelectedItem().toString());}
            if(!converter.getCmbFps().getSelectedItem().toString().equals("Select")) {convertCriteria.setCnvFps(converter.getCmbFps().getSelectedItem().toString());}
            if(!converter.getCmbAspectRatio().getSelectedItem().toString().equals("Select")) {convertCriteria.setCnvAspectRatio(converter.getCmbAspectRatio().getSelectedItem().toString());}
            if(!converter.getCmbResolution().getSelectedItem().toString().equals("Select")) {
                String[] resolution = converter.getCmbResolution().getSelectedItem().toString().split("x");
                convertCriteria.setCnvResolutionWidth(resolution[0]);
                convertCriteria.setCnvResolutionHeight(resolution[1]);
            }
            if(!converter.getCmbVideoVC().getSelectedItem().toString().equals("Select")) {convertCriteria.setCnvVideoCodec(converter.getCmbVideoVC().getSelectedItem().toString());}
            if(!converter.getCmbVideoAC().getSelectedItem().toString().equals("Select")) {convertCriteria.setCnvVideoAudioCodec(converter.getCmbVideoAC().getSelectedItem().toString());}
        }else if(converter.getCmbConvertTo().getSelectedItem().toString().equals("Audio")){
            converter.getCmbType().setSelectedItem("Select");
            if(!converter.getCmbTypeAudio().getSelectedItem().toString().equals("Select")) {
                convertCriteria.setCnvAudioType(converter.getCmbTypeAudio().getSelectedItem().toString());
                convertCriteria.setFormatTo(converter.getCmbTypeAudio().getSelectedItem().toString());
            }
            if(!converter.getCmbChannels().getSelectedItem().toString().equals("Select")) {convertCriteria.setCnvChannels(converter.getCmbChannels().getSelectedItem().toString());}
            if(!converter.getCmbAudioAC().getSelectedItem().toString().equals("Select")) {convertCriteria.setCnvAudioCodec(converter.getCmbAudioAC().getSelectedItem().toString());}
        }
        sL.register(null, "INFO", "Successful - loadConvertCriteria - finished");
    }


    /**
     * Method runs when convert button is pressed.
     * @param e action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        sL.register(null, "INFO", "Successful - actionPerformed - start");
        if (converter.getTxtName().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please, define a name file.");
        } else if (converter.getCmbConvertTo().getSelectedItem().toString().equals("Select")) {
            JOptionPane.showMessageDialog(null, "Please, define a Video or Audio conversion.");
        } else if (converter.getTxtPathSave().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please, define a valid path to save.");
        } else if (converter.getCmbType().getSelectedItem().toString().equals("Select") && converter.getCmbTypeAudio().getSelectedItem().toString().equals("Select")) {
            JOptionPane.showMessageDialog(null, "Please, define a type to conversion.");
        } else if (e.getSource() == converter.getBtnConvert()) {
            loadConvertCriteria(convertCriteria);
            if(converter.getCmbConvertTo().getSelectedItem().toString().equals("Audio")) {
                convertFile = new ConvertFileAudio();
                progress = convertFile.convert(convertCriteria);
                converter.setProgressBar(progress);
            } else if(converter.getCmbConvertTo().getSelectedItem().toString().equals("Video")){
                convertFile = new ConvertFileVideo();
                progress = convertFile.convert(convertCriteria);
                converter.setProgressBar(progress);
            }
            if(progress == 100) {
                String type = converter.getCmbType()
                        .getSelectedItem().toString()=="Select"?
                        converter.getCmbTypeAudio().getSelectedItem().toString()
                        :converter.getCmbType().getSelectedItem().toString();
                ConvertList convertList = new ConvertList();
                convertList.writeConvert(converter.getTxtPathSave().getText()
                        .concat("\\")
                        .concat(converter.getTxtName().getText())
                        .concat(".")
                        .concat(type)
                );
                listConverting.getListModel().clear();
                showList(convertList.convertLis());
                JOptionPane.showMessageDialog(null, "File was converted successfully!", "Information message", JOptionPane.INFORMATION_MESSAGE);
                converter.setProgressBar(0);
            }
        }
        sL.register(null, "INFO", "Successful - actionPerformed - finished");
    }

    public static int getProgress() {
        return progress;
    }

    /**
     * Class to obtain criteria instance.
     * @return criteria for conversion.
     */
    public ConvertCriteria getConvertCriteria() {
        return convertCriteria;
    }

    /**
     * Class for fill list of converted files.
     * @param convertLis list of converted files.
     */
    protected void showList(String[] convertLis) {
        sL.register(null, "INFO", "Successful - showList - start");
        for (int j = convertLis.length - 1; j >= 0; j-- ) {
            listConverting.getListModel().addElement(convertLis[j]);
        }
        sL.register(null, "INFO", "Successful - showList - finished");
    }

    /**
     * Class for obtain converter instance.
     * @return converter instance.
     */
    public Converter getConverter() {
        return converter;
    }

    /**
     * Class for obtain ListConverting instance.
     * @return ListConverting instance.
     */
    public ListConverting getListConverting() {
        return listConverting;
    }
}
