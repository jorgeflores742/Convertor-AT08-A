package com.fundacionjala.convertor.controller;

import com.fundacionjala.convertor.model.ConvertFileAudio;
import com.fundacionjala.convertor.model.ConvertFileVideo;
import com.fundacionjala.convertor.model.ConvertList;
import com.fundacionjala.convertor.model.IConvertFile;
import com.fundacionjala.convertor.view.Converter;
import com.fundacionjala.convertor.view.ListConverting;
import com.fundacionjala.convertor.view.NewWindows;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Module View MainView.
 *
 * @author Melvi Caballero.
 * @version 1.0.
 */
public class ConvertController implements ActionListener {
    private Converter converter;
    private IConvertFile convertFile;
    private ConvertCriteria convertCriteria;
    private ListConverting listConverting;
    private static int progress;


    /**
     *
     */
    public ConvertController(NewWindows nW) {
        this.converter = nW.getConverting();
        this.listConverting = nW.getListConv();
        converter.getBtnConvert().addActionListener(this);
        convertCriteria = new ConvertCriteria();
        ConvertList convertList = new ConvertList();
        String[] listConv = convertList.convertLis();
        showList(listConv);
    }

    public void loadConvertCriteria(ConvertCriteria convertCriteria) {
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

            if(!converter.getCmbTypeAudio().getSelectedItem().toString().equals("Select")) {convertCriteria.setCnvAudioType(converter.getCmbTypeAudio().getSelectedItem().toString());}

            if(!converter.getCmbChannels().getSelectedItem().toString().equals("Select")) {
                if (converter.getCmbChannels().getSelectedItem().toString().equals("Stereo")) {
                    convertCriteria.setCnvChannels("2");
                } else {
                    convertCriteria.setCnvChannels("1");
                }
            }

            if(!converter.getCmbAudioAC().getSelectedItem().toString().equals("Select")) {convertCriteria.setCnvAudioCodec(converter.getCmbAudioAC().getSelectedItem().toString());}
        }
    }

    /**
     * @param e actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == converter.getBtnConvert()) {
            loadConvertCriteria(convertCriteria);
            if(converter.getCmbConvertTo().getSelectedItem().toString().equals("Audio")) {
                System.out.println("Es audio");
                convertFile = new ConvertFileAudio();
                progress = convertFile.convert(convertCriteria);
                converter.setProgressBar(progress);

            } else if(converter.getCmbConvertTo().getSelectedItem().toString().equals("Video")){
                System.out.println("Es video");

                convertFile = new ConvertFileVideo();
                progress = convertFile.convert(convertCriteria);
                converter.setProgressBar(progress);
            }
            if(progress == 100) {
                ConvertList convertList = new ConvertList();
                convertList.writeConvert(converter.getTxtPathSave().getText()
                        .concat("\\")
                        .concat(converter.getTxtName().getText())
                        .concat(".")
                        .concat(converter.getCmbType().getSelectedItem().toString())
                );
                listConverting.getListModel().clear();
                showList(convertList.convertLis());
            }
        }
    }

    public static int getProgress() {
        return progress;
    }

    public ConvertCriteria getConvertCriteria() {
        return convertCriteria;
    }

    protected void showList(String[] convertLis) {
        for (int j = convertLis.length - 1; j >= 0; j-- ) {
            listConverting.getListModel().addElement(convertLis[j]);
        }
    }
}

