package com.fundacionjala.convertor.controller;

import com.fundacionjala.convertor.model.ConvertFileAudio;
import com.fundacionjala.convertor.model.ConvertFileVideo;
import com.fundacionjala.convertor.model.IConvertFile;
import com.fundacionjala.convertor.view.Converter;
import com.fundacionjala.convertor.view.ViewConverter;

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
    private static int progress;


    /**
     *
     */
    public ConvertController(ViewConverter vc) {
        this.converter = vc.getConverting();
        converter.getBtnConvert().addActionListener(this);
        convertCriteria = new ConvertCriteria();
    }

    public void loadConvertCriteria() {
        convertCriteria.setFileName(converter.getTxtName().getText());
        convertCriteria.setFormatFrom(converter.getTxtPathSave().getText());
        convertCriteria.setFormatTo(converter.getCmbType().getSelectedItem().toString());
        convertCriteria.setPathFrom("falta");
        convertCriteria.setPathTo(converter.getTxtPathSave().getText());

        convertCriteria.setCnvVideoType(converter.getCmbType().getSelectedItem().toString());
        convertCriteria.setCnvFps(converter.getCmbFps().getSelectedItem().toString());
        convertCriteria.setCnvAspectRatio(converter.getCmbAspectRatio().getSelectedItem().toString());
        String[] resolution = converter.getCmbResolution().getSelectedItem().toString().split("x");
        convertCriteria.setCnvResolutionWidth(resolution[0]);
        convertCriteria.setCnvResolutionHeight(resolution[1]);
        convertCriteria.setCnvVideoCodec(converter.getCmbVideoVC().getSelectedItem().toString());
        convertCriteria.setCnvVideoAudioCodec(converter.getCmbVideoAC().getSelectedItem().toString());

        convertCriteria.setCnvAudioType(converter.getCmbTypeAudio().getSelectedItem().toString());
        convertCriteria.setCnvChannels(converter.getCmbChannels().getSelectedItem().toString());

        convertCriteria.setCnvAudioCodec(converter.getCmbAudioAC().getSelectedItem().toString());
    }

    /**
     * @param e actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == converter.getBtnConvert()) {
            loadConvertCriteria();
//            System.out.println(convertCriteria.getCnvAspectRatio());
//            System.out.println(convertCriteria.getCnvAudioCodec());
//            System.out.println(convertCriteria.getCnvAudioType());
//            System.out.println(convertCriteria.getCnvChannels());
//            System.out.println(convertCriteria.getCnvFps());
//            System.out.println(convertCriteria.getCnvResolutionWidth());
//            System.out.println(convertCriteria.getCnvResolutionHeight());
//            System.out.println(convertCriteria.getCnvVideoAudioCodec());
//            System.out.println(convertCriteria.getCnvVideoCodec());
//            System.out.println(convertCriteria.getCnvVideoType());
//            System.out.println(convertCriteria.getFileName());
//            System.out.println(convertCriteria.getFormatFrom());
//            System.out.println(convertCriteria.getFormatTo());
//            System.out.println(convertCriteria.getPathFrom());
//            System.out.println(convertCriteria.getPathTo());

            if(converter.getCmbConvertTo().equals("Audio")) {
                System.out.println("Es audio");
                convertFile = new ConvertFileAudio();
                progress = convertFile.convert(convertCriteria);

            } else if(converter.getCmbConvertTo().equals("Video")){
                System.out.println("Es video");
                convertFile = new ConvertFileVideo();
                progress = convertFile.convert(convertCriteria);
            }
        }
    }

    public static int getProgress() {
        return progress;
    }

}

