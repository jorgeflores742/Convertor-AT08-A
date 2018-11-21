package com.fundacionjala.convertor.model;

import com.fundacionjala.convertor.controller.ConvertCriteria;
import com.fundacionjala.convertor.utils.SingleLogger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConvertFileAudioTest {
    ConvertCriteria convertCriteria;
    static final int FINISHED = 100;
    ConvertFileAudio convertFileAudio;
    static SingleLogger sL = SingleLogger.getInstanceLogger();

    @Before
    public void setUp() throws Exception {
        convertCriteria = new ConvertCriteria();
        convertCriteria.setFileName("NenaBoba");
        convertCriteria.setFormatTo("Select");
        convertCriteria.setPathFrom("testfiles\\Nena Boba - Pescado Rabioso.m4a");
        convertCriteria.setPathTo("C:\\Users\\Admin\\Music");
        convertCriteria.setCnvAudioType("mp3");
        convertCriteria.setCnvChannels("Stereo");
        convertCriteria.setCnvAudioCodec("mp3");
        convertFileAudio = new ConvertFileAudio();
    }

    @Test
    public void convertM4aToMp3() {
        int result = convertFileAudio.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }

    @Test
    public void convertM4aToMp3WithoutChannels() {
        convertCriteria.setCnvChannels(null);
        int result = convertFileAudio.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
}