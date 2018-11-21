package com.fundacionjala.convertor.model;

import com.fundacionjala.convertor.controller.ConvertCriteria;
import com.fundacionjala.convertor.utils.SingleLogger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConvertFileVideoTest {
    ConvertCriteria convertCriteria;
    static final int FINISHED = 100;
    ConvertFileVideo convertFileVideo;
    static SingleLogger sL = SingleLogger.getInstanceLogger();

    @Before
    public void initialize(){
        convertCriteria = new ConvertCriteria();
        convertCriteria.setFileName("NewFormatCount");
        convertCriteria.setFormatTo("mkv");
        convertCriteria.setCnvVideoType("mkv");
        convertCriteria.setPathFrom("testfiles\\Count.avi");
        convertCriteria.setPathTo("C:\\Users\\Admin\\Videos");
        convertCriteria.setCnvFps("29.9");
        convertCriteria.setCnvResolutionWidth("640");
        convertCriteria.setCnvResolutionHeight("360");
        convertCriteria.setCnvVideoCodec("mp3");
        convertCriteria.setCnvVideoAudioCodec("mpeg4");
        convertFileVideo = new ConvertFileVideo();
    }

    @Test
    public void testConvertAvitoMkv() {
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }

    @Test
    public void testConvertAvitoMkvWithoutFps() {
        convertCriteria.setCnvFps(null);
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }

    @Test
    public void testConvertAvitoMkvFps60() {
        convertCriteria.setCnvFps("60.0");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }

    @Test
    public void testConvertAvitoMp4Fps30() {
        convertCriteria.setFormatTo("mp4");
        convertCriteria.setCnvVideoType("mp4");
        convertCriteria.setCnvFps("30.0");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }

    @Test
    public void testConvertAvitoMp4WithoutResolution() {
        convertCriteria.setFormatTo("mp4");
        convertCriteria.setCnvResolutionWidth(null);
        convertCriteria.setCnvResolutionHeight(null);
        convertCriteria.setCnvFps("30.0");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }

    @Test
    public void testConvertMkvtoMp4WithoutAudioCodecs() {
        convertCriteria.setFileName("JA human cover");
        convertCriteria.setFormatTo("mp4");
        convertCriteria.setCnvVideoType("mp4");
        convertCriteria.setPathFrom("testfiles\\Folder2\\HumanCover.MKV");
        convertCriteria.setPathTo("C:\\Users\\Admin\\Videos");
        convertCriteria.setCnvFps("25.0");
        //convertCriteria.setCnvVideoCodec(null);
        convertCriteria.setCnvVideoAudioCodec(null);
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }

    @Test
    public void testConvertMkvtoMp4WithoutVideoAudioCodecs() {
        convertCriteria.setFileName("NewItStones");
        convertCriteria.setFormatTo("mp4");
        convertCriteria.setCnvVideoType("mp4");
        convertCriteria.setPathFrom("testfiles\\It stones.avi");
        convertCriteria.setPathTo("C:\\Users\\Admin\\Videos");
        convertCriteria.setCnvFps("27.0");
        convertCriteria.setCnvVideoCodec(null);
        convertCriteria.setCnvVideoAudioCodec(null);
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
}