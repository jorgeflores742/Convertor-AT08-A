package com.fundacionjala.convertor.controller;

import com.fundacionjala.convertor.model.FileSearcher;
import com.fundacionjala.convertor.view.AdvancedSearchVideo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class AdvancedSearchVideoController implements ActionListener {
    private AdvancedSearchVideo advancedSearchVideo;
    private FileSearcher fileSearcher;
    private AdvancedSearchVideoCriteria advancedSearchVideoCriteria;


    /**
     * @param advancedSearchVideo   searchView
     * @param fileSearcher fileSearcher
     */
    public AdvancedSearchVideoController(final AdvancedSearchVideo advancedSearchVideo, final FileSearcher fileSearcher) {
        this.advancedSearchVideo = advancedSearchVideo;
        this.fileSearcher = fileSearcher;
        //this.advancedSearchVideo.btnSearch.addActionListener(this);
        advancedSearchVideoCriteria = new AdvancedSearchVideoCriteria();
    }

    /**
     *
     */

    public void loadCriteria() {
        advancedSearchVideoCriteria.setFps( Integer.parseInt(advancedSearchVideo.getCmbFps().getSelectedItem().toString()));
        advancedSearchVideoCriteria.setAspectRatio(advancedSearchVideo.getCmbAspectRatio().getSelectedItem().toString());
        advancedSearchVideoCriteria.setResolution(advancedSearchVideo.getCmbResolution().getSelectedItem().toString());
    }

    /**
     * @param e action btnConvert
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
       /* if (e.getSource() == advancedSearchVideoCriteria.btnSearch) {
            loadCriteria();
            ArrayList<File> resultList = fileSearcher.searchFiles(advancedSearchVideoCriteria.getPath(), advancedSearchVideoCriteria.getName(),
                    advancedSearchVideoCriteria.getExt(), advancedSearchVideoCriteria.getSize());

            for (File resu : resultList) {
                searchView.listModel.addElement(resu.getAbsolutePath());
            }

        }*/

    }
}
