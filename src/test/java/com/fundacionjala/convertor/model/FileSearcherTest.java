package com.fundacionjala.convertor.model;

import com.fundacionjala.convertor.controller.SearchCriteria;
import com.fundacionjala.convertor.utils.SingleLogger;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FileSearcherTest {
    SearchCriteria searchCriteria;
    ArrayList<Asset> result;
    FileSearcher fSearcher;
    ArrayList<Asset> List;
    int sizeList;
    static SingleLogger sL = SingleLogger.getInstanceLogger();

    @Before
    public void initialize () {
        searchCriteria = new SearchCriteria();
        searchCriteria.setName("");
        searchCriteria.setPath("testfiles");
        searchCriteria.setSize("All");
        searchCriteria.setAdvancedType("All");
        searchCriteria.setVideoType("All");
        searchCriteria.setFps("All");
        searchCriteria.setAspectRatio("All");
        searchCriteria.setResolution("All");
        searchCriteria.setVideoCodec("All");
        searchCriteria.setVideoAudioCodec("All");
        searchCriteria.setAudioType("All");
        searchCriteria.setChannels("All");
        searchCriteria.setAudioCodec("All");
        List = new ArrayList<>(1);
        fSearcher = new FileSearcher();
        List = fSearcher.search(searchCriteria);
        sizeList = List.size();
    }

    @Test
    public void testSearchByName() {
        searchCriteria.setName("Cover");
        searchCriteria.setPath("testfiles");
        result = fSearcher.search(searchCriteria);
        assertEquals(1, result.size());
    }

    @Test
    public void testSearchByVideo() {
        searchCriteria.setPath("testfiles");
        searchCriteria.setAdvancedType("Video");
        searchCriteria.setVideoType("avi");
        result = fSearcher.search(searchCriteria);
        assertEquals(2,result.size());
    }

    @Test
    public void testSearchBySizeMinus() {
        searchCriteria.setPath("testfiles");
        searchCriteria.setAdvancedType("All");
        searchCriteria.setVideoType("All");
        searchCriteria.setSize("minus");
        result = fSearcher.search(searchCriteria);
        assertEquals(sizeList, result.size());
    }

    @Test
    public void testSearchBySizeLittle() {
        searchCriteria.setPath("testfiles");
        searchCriteria.setAdvancedType("All");
        searchCriteria.setVideoType("All");
        searchCriteria.setSize("little");
        result = fSearcher.search(searchCriteria);
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchByAudio() {
        searchCriteria.setPath("testfiles");
        searchCriteria.setAdvancedType("Audio");
        searchCriteria.setVideoType("All");
        result = fSearcher.search(searchCriteria);
        assertEquals(6,result.size());
    }

    @Test
    public void testSearchByAudioTypeChannelCodec() {
        searchCriteria.setPath("testfiles");
        searchCriteria.setAdvancedType("Audio");
        searchCriteria.setVideoType("mp3");
        searchCriteria.setChannels("stereo");
        searchCriteria.setAudioCodec("mp3");
        result = fSearcher.search(searchCriteria);
        assertEquals(3,result.size());
    }

    @Test
    public void testSearchByVideoTypeFramesAspectratioVideoAudioCodec() {
        searchCriteria.setPath("testfiles");
        searchCriteria.setAdvancedType("Video");
        searchCriteria.setVideoType("avi");
        searchCriteria.setFps("24.0");
        searchCriteria.setVideoCodec("h264");
        searchCriteria.setVideoAudioCodec("aac");
        result = fSearcher.search(searchCriteria);
        assertEquals(1,result.size());
    }

    @Test
    public void testAssetNameFile() {
        searchCriteria.setPath("testfiles");
        searchCriteria.setAdvancedType("Video");
        searchCriteria.setVideoAudioCodec("vorbis");
        result = fSearcher.search(searchCriteria);
        assertEquals("Name: HumanCover.MKV", result.get(0).getNameFile());
    }

    @Test
    public void testAssetSize() {
        searchCriteria.setPath("testfiles");
        searchCriteria.setAdvancedType("Video");
        searchCriteria.setVideoAudioCodec("vorbis");
        result = fSearcher.search(searchCriteria);
        assertEquals("Size: 12096023 bytes", result.get(0).getSizeFile());
    }

    @Test
    public void testAssetFile() {
        searchCriteria.setPath("testfiles");
        searchCriteria.setAdvancedType("Video");
        searchCriteria.setVideoAudioCodec("vorbis");
        result = fSearcher.search(searchCriteria);
        File fileTest = new File(searchCriteria.getPath().concat("\\HumanCover.MKV"));
        try {
            assertTrue("File comparison", FileUtils.contentEquals(fileTest, result.get(0).getFile()));
        } catch (IOException e) {
            System.out.println("Something Fail with File comparison");
        }
    }

    @Test
    public void testAssetCreationFile() {
        searchCriteria.setPath("testfiles");
        searchCriteria.setAdvancedType("Video");
        searchCriteria.setVideoAudioCodec("vorbis");
        result = fSearcher.search(searchCriteria);
        assertEquals("Creation time: 19/11/2018 17:42:29", result.get(0).getCreationFile());
    }

    @Test
    public void testAssetVideoResolution() {
        searchCriteria.setPath("testfiles");
        searchCriteria.setAdvancedType("Video");
        searchCriteria.setVideoAudioCodec("vorbis");
        result = fSearcher.search(searchCriteria);
        VideoAsset Vas = new VideoAsset();
        Vas = (VideoAsset) result.get(0);
        assertEquals("Resolution: 640x360", Vas.getResolution());
    }

    @Test
    public void testAssetVideoAspectRatio() {
        searchCriteria.setPath("testfiles");
        searchCriteria.setAdvancedType("Video");
        searchCriteria.setVideoAudioCodec("vorbis");
        result = fSearcher.search(searchCriteria);
        VideoAsset Vas = new VideoAsset();
        Vas = (VideoAsset) result.get(0);
        assertEquals("Aspect ratio: 16:9", Vas.getAspectRatio());
    }

    @Test
    public void testAssetVideoDuration() {
        searchCriteria.setPath("testfiles");
        searchCriteria.setAdvancedType("Video");
        searchCriteria.setVideoCodec("mpeg4");
        result = fSearcher.search(searchCriteria);
        VideoAsset Vas = new VideoAsset();
        Vas = (VideoAsset) result.get(0);
        assertEquals("Duration: 42.2 seconds", Vas.getDuration());
    }
}