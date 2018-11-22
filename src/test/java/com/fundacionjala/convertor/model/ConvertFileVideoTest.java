package com.fundacionjala.convertor.model;

import com.fundacionjala.convertor.controller.ConvertController;
import com.fundacionjala.convertor.controller.ConvertCriteria;
import com.fundacionjala.convertor.utils.SingleLogger;
import com.fundacionjala.convertor.view.Converter;
import com.fundacionjala.convertor.view.NewWindows;
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

    //codec mpeg4
    @Test
    public void testConvertToVideoCodec_Mpeg4AudioCodec_Aac() {
        convertCriteria.setCnvVideoCodec("aac");
        convertCriteria.setCnvVideoAudioCodec("mpeg4");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
    @Test
    public void testConvertToVideoCodec_wmv1AudioCodec_Mp3() {
        convertCriteria.setCnvVideoCodec("mp3");
        convertCriteria.setCnvVideoAudioCodec("mpeg4");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
    //codec wmv1
    @Test
    public void testConvertToVideoCodec_wmv1AudioCodec_aac() {
        convertCriteria.setCnvVideoCodec("aac");
        convertCriteria.setCnvVideoAudioCodec("wmv1");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
    @Test
    public void testConvertToVideoCodec_wmv1AudioCodec_mp3() {
        convertCriteria.setCnvVideoCodec("mp3");
        convertCriteria.setCnvVideoAudioCodec("wmv1");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
    //codec vp9
    @Test
    public void testConvertToVideoCodec_vp9AudioCodec_Mp3() {
        convertCriteria.setCnvVideoCodec("mp3");
        convertCriteria.setCnvVideoAudioCodec("vp9");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
    @Test
    public void testConvertToVideoCodec_vp9AudioCodec_aac() {
        convertCriteria.setCnvVideoCodec("aac");
        convertCriteria.setCnvVideoAudioCodec("vp9");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
    //codec msmpeg4v3
    @Test
    public void testConvertToVideoCodec_msmpeg4v3AudioCodec_aac() {
        convertCriteria.setCnvVideoCodec("aac");
        convertCriteria.setCnvVideoAudioCodec("msmpeg4v3");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
    @Test
    public void testConvertToVideoCodec_msmpeg4v3AudioCodec_Mp3() {
        convertCriteria.setCnvVideoCodec("mp3");
        convertCriteria.setCnvVideoAudioCodec("msmpeg4v3");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
    //codec flv1
    @Test
    public void testConvertToVideoCodec_flv1AudioCodec_aac() {
        convertCriteria.setCnvVideoCodec("aac");
        convertCriteria.setCnvVideoAudioCodec("flv1");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
    @Test
    public void testConvertToVideoCodec_flv1AudioCodec_Mp3() {
        convertCriteria.setCnvVideoCodec("mp3");
        convertCriteria.setCnvVideoAudioCodec("flv1");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
    //codec mpg1
    @Test
    public void testConvertToVideoCodec_mpeg1videoAudioCodec_aac() {
        convertCriteria.setFileName("MkvtoAvi");
        convertCriteria.setCnvVideoCodec("aac");
        convertCriteria.setCnvVideoAudioCodec("mpeg1video");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
    @Test
    public void testConvertToVideoCodec_mpeg1videoAudioCodec_Mp3() {
        convertCriteria.setFileName("MkvtoAvi");
        convertCriteria.setCnvVideoCodec("mp3");
        convertCriteria.setCnvVideoAudioCodec("mpeg1video");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
    //codec libx264
    @Test
    public void testConvertToVideoCodec_libx264videoAudioCodec_aac() {
        convertCriteria.setFileName("MkvtoAvi");
        convertCriteria.setCnvVideoCodec("aac");
        convertCriteria.setCnvVideoAudioCodec("libx264");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
    @Test
    public void testConvertToVideoCodec_libx264videoAudioCodec_Mp3() {
        convertCriteria.setFileName("MkvtoAvi");
        convertCriteria.setCnvVideoCodec("mp3");
        convertCriteria.setCnvVideoAudioCodec("libx264");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
    //codec vp8
    @Test
    public void testConvertToVideoCodec_vp8videoAudioCodec_aac() {
        convertCriteria.setFileName("MkvtoAvi");
        convertCriteria.setCnvVideoCodec("aac");
        convertCriteria.setCnvVideoAudioCodec("vp8");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
    @Test
    public void testConvertToVideoCodec_vp8videoAudioCodec_Mp3() {
        convertCriteria.setFileName("MkvtoAvi");
        convertCriteria.setCnvVideoCodec("mp3");
        convertCriteria.setCnvVideoAudioCodec("vp8");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
    //codec mpeg2
    @Test
    public void testConvertToVideoCodec_mpeg2videovideoAudioCodec_aac() {
        convertCriteria.setFileName("MkvtoAvi");
        convertCriteria.setCnvVideoCodec("aac");
        convertCriteria.setCnvVideoAudioCodec("mpeg2video");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }
    @Test
    public void testConvertToVideoCodec_mpeg2videovideoAudioCodec_Mp3() {
        convertCriteria.setFileName("MkvtoAvi");
        convertCriteria.setCnvVideoCodec("mp3");
        convertCriteria.setCnvVideoAudioCodec("mpeg2video");
        int result = convertFileVideo.convert(convertCriteria);
        assertEquals(FINISHED, result);
    }

    @Test
    public void testConvertList() {
        NewWindows newWindows = new NewWindows();
        ConvertController convertController = new ConvertController(newWindows);
        convertController.getConvertCriteria().setPathFrom("testfiles\\Count.avi");
        convertController.getConvertCriteria().setFileName("");
        Converter converter = convertController.getConverter();
        converter.getTxtName().setText("newFile");
        converter.getCmbConvertTo().setSelectedItem("Video");
        converter.getTxtPathSave().setText("C:\\Users\\Admin\\Videos");
        converter.getCmbType().setSelectedItem("mkv");
        converter.getCmbFps().setSelectedItem("29.9");
        converter.getCmbVideoVC().setSelectedItem("mp3");
        converter.getCmbVideoAC().setSelectedItem("mpeg4");
        ConvertList list = new ConvertList();
        converter.getBtnConvert().doClick();
        String[] resultString = list.convertLis();
        assertEquals("C:\\Users\\Admin\\Videos\\newFile.mkv", resultString[0]);
    }
}
