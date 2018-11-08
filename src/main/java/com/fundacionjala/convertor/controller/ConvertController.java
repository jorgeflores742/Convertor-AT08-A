package com.fundacionjala.convertor.controller;

import com.fundacionjala.convertor.model.IConvertFile;
import com.fundacionjala.convertor.view.Converter;

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

    /**
     *
     */
    public ConvertController(final Converter converter, final IConvertFile convertFile) {
        this.converter = converter;
        this.convertFile = convertFile;
        this.converter.getBtnConvert().addActionListener(this);
        convertCriteria = new ConvertCriteria();
    }

    public void loadConvertCriteria() {
        convertCriteria.setFormatFrom(converter.getTxtFormatFrom().getText());
        convertCriteria.setFormatTo(converter.getCmbFormatTo().getSelectedItem().toString());
        convertCriteria.setFormatFrom(converter.getTxtPathFrom().getText());
        convertCriteria.setFormatTo(converter.getTxtPathTo().getSelectedItem().toString());
        convertCriteria.setFileName(converter.getTxtFileName().getTest());
        convertCriteria.setCnvVideoType(converter.getAsv().getCmbType().getSelectedItem().toString());
        convertCriteria.setCnvFps(converter.getAsv().getCmbFps().getSelectedItem().toString());
        convertCriteria.setCnvAspectRatio(converter.getAsv().getCmbAspectRatio().getSelectedItem().toString());
        convertCriteria.setCnvResolution(converter.getAsv().getCmbResolution().getSelectedItem().toString());
        //convertCriteria.setCnvAudioType(converter.getAsv().getCmbType().getSelectedItem().toString());
        //convertCriteria.setCnvChannels(converter.getAsv().getCmbChannels().getSelectedItem().toString());
        //       convertCriteria.setCnvVideoType(converter);
    }

    /**
     * @param e actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == converter.getBtnConvert()) {
            loadConvertCriteria();
            convertFile.Convert(convertCriteria);
        }
    }
}

