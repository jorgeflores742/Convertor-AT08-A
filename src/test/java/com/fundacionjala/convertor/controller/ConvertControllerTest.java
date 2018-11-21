package com.fundacionjala.convertor.controller;

import com.fundacionjala.convertor.utils.SingleLogger;
import com.fundacionjala.convertor.view.Converter;
import com.fundacionjala.convertor.view.NewWindows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConvertControllerTest {
    ConvertController convertController;
    Converter converter;
    NewWindows newWindows;
    static SingleLogger sL = SingleLogger.getInstanceLogger();

    @Before
    public void setUp() throws Exception {
        newWindows = new NewWindows();
        convertController = new ConvertController(newWindows);
        convertController.getConvertCriteria().setPathFrom("testfiles\\Count.avi");
        convertController.getConvertCriteria().setFileName("");
        converter = convertController.getConverter();
        converter.getTxtName().setText("newFile");
        converter.getCmbConvertTo().setSelectedItem("Video");
        converter.getTxtPathSave().setText("C:\\Users\\Admin\\Videos");
        converter.getCmbType().setSelectedItem("mkv");
        converter.getCmbFps().setSelectedItem("29.9");
        converter.getCmbVideoVC().setSelectedItem("mp3");
        converter.getCmbVideoAC().setSelectedItem("mpeg4");
    }

    @Test
    public void testConvertAviToMkv() {
        converter.getBtnConvert().doClick();
        String result = convertController.getListConverting().getListModel().getElementAt(0).toString();
        assertEquals("C:\\Users\\Admin\\Videos\\newFile.mkv", result);
    }

    @Test
    public void testConvert640() {
        converter.getCmbResolution().setSelectedItem("640x360");
        converter.getTxtName().setText("count640");
        converter.getBtnConvert().doClick();
        String result = convertController.getListConverting().getListModel().getElementAt(0).toString();
        assertEquals("C:\\Users\\Admin\\Videos\\count640.mkv", result);
    }

    @Test
    public void testConvertAudioOggToMp3() {
        convertController.getConvertCriteria().setPathFrom("testfiles\\Big Man - John Mayall.ogg");
        converter.getTxtName().setText("BigMan");
        converter.getCmbConvertTo().setSelectedItem("Audio");
        converter.getTxtPathSave().setText("C:\\Users\\Admin\\Music");
        converter.getCmbTypeAudio().setSelectedItem("mp3");
        converter.getCmbChannels().setSelectedItem("Stereo");
        converter.getCmbAudioAC().setSelectedItem("mp3");
        converter.getBtnConvert().doClick();
        String result = convertController.getListConverting().getListModel().getElementAt(0).toString();
        assertEquals("C:\\Users\\Admin\\Music\\BigMan.mp3", result);
    }
}