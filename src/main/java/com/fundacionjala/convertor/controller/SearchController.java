package com.fundacionjala.convertor.controller;

import com.fundacionjala.convertor.model.FileSearcher;
import com.fundacionjala.convertor.view.SearchView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Module Controller SearchController.
 *
 * @author Melvi Caballero.
 * @version 1.0.
 */
public class SearchController implements ActionListener {
    private SearchView searchView;
    private FileSearcher fileSearcher;
    private SearchCriteria searchCriteria;


    /**
     * @param searchView   searchView
     * @param fileSearcher fileSearcher
     */
    public SearchController(final SearchView searchView, final FileSearcher fileSearcher) {
        this.searchView = searchView;
        this.fileSearcher = fileSearcher;
        //this.searchView.btnSearch.addActionListener(this);
        searchCriteria = new SearchCriteria();
    }

    /**
     *
     */

    public void loadCriteria() {
        searchCriteria.setName(searchView.getTxtName().getText());
        searchCriteria.setPath(searchView.getTxtPath().getText());
        searchCriteria.setExt(searchView.getCmbFileFormat().getSelectedItem().toString());
        searchCriteria.setSize(searchView.getCmbSize().getSelectedItem().toString());

    }

    /**
     * @param e action btnConvert
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        /*if (e.getSource() == searchView.btnSearch) {
            ArrayList<File> resultList = fileSearcher.searchFiles(searchCriteria.getPath(), searchCriteria.getName(),
                    searchCriteria.getExt(), searchCriteria.getSize());

            for (File resu : resultList) {
                searchView.listModel.addElement(resu.getAbsolutePath());
            }

        }*/

    }
}
