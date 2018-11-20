package com.fundacionjala.convertor.controller;

import com.fundacionjala.convertor.view.FileTypeEnum;
import com.fundacionjala.convertor.view.NewSearchViewer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchControllerTest {
    SearchController searchController;
    NewSearchViewer searchViewer;

    @Before
    public void setUp() throws Exception {
        searchController = new SearchController();
        searchViewer = searchController.getSearchViewer();
        searchViewer.getTxtPath().setText("testfiles");
        searchViewer.getTxtName().setText("");
        searchViewer.getCmbFileType().setSelectedItem("All");
        searchViewer.getCmbSize().setSelectedItem("All");
    }

    @Test
    public void testActionPerformedEmptyPath() {
        searchViewer.getTxtPath().setText("");
        searchViewer.getBtnSearch().doClick();
        int flag = searchController.getEmptyPathFlag();
        assertEquals(1, flag);
    }

    @Test
    public void testActionPerformedClearList() {
        searchViewer.getBtnSearch().doClick();
        searchViewer.getBtnClearList().doClick();
        int numberElements = searchController.getListFileView().getListModel().getSize();
        assertEquals(0, numberElements);
    }

    @Test
    public void testActionPerformedAll() {
        searchViewer.getBtnSearch().doClick();
        int numberElements = searchController.getListFileView().getListModel().getSize();
        assertEquals(12, numberElements);
    }

    @Test
    public void testActionPerformedVideo() {
        searchViewer.getTxtPath().setText("testfiles");
        searchViewer.getCmbFileType().setSelectedItem(FileTypeEnum.Video);
        searchViewer.getCmbType().setSelectedItem("mkv");
        searchViewer.getCmbFps().setSelectedItem("24.0");
        searchViewer.getCmbAspectRatio().setSelectedItem("16:9");
        searchViewer.getCmbVideoVC().setSelectedItem("h264");
        searchViewer.getCmbVideoAC().setSelectedItem("vorbis");
        searchViewer.getBtnSearch().doClick();
        int numberElements = searchController.getListFileView().getListModel().getSize();
        assertEquals(1, numberElements);
    }

    @Test
    public void testActionPerformedAudio() {
        searchViewer.getTxtPath().setText("testfiles");
        searchViewer.getCmbFileType().setSelectedItem(FileTypeEnum.Audio);
        searchViewer.getCmbTypeAudio().setSelectedItem("mp3");
        searchViewer.getCmbChannels().setSelectedItem("stereo");
        searchViewer.getCmbAudioAC().setSelectedItem("mp3");
        searchViewer.getBtnSearch().doClick();
        int numberElements = searchController.getListFileView().getListModel().getSize();
        assertEquals(3, numberElements);
    }

    @Test
    public void testValueChangedVideo() {
        searchViewer.getBtnSearch().doClick();
        searchController.setLock(-1);
        searchController.getListFileView().getLstSearchResult().setSelectedIndex(3);
        String nameFile = searchController.getDataFilesName();
        assertEquals("Name: Count.avi", nameFile);
    }

    @Test
    public void testValueChangedAudio() {
        searchViewer.getBtnSearch().doClick();
        searchController.setLock(-1);
        searchController.getListFileView().getLstSearchResult().setSelectedIndex(1);
        String nameFile = searchController.getDataFilesName();
        assertEquals("Name: Big Man - John Mayall.ogg", nameFile);
    }


}