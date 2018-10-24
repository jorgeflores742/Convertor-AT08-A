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

    /**
     * @param searchView   searchView
     * @param fileSearcher fileSearcher
     */
    public SearchController(final SearchView searchView, final FileSearcher fileSearcher) {
        this.searchView = searchView;
        this.fileSearcher = fileSearcher;
        this.searchView.btnSearch.addActionListener(this);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == searchView.btnSearch) {
            ArrayList<File> resultList = fileSearcher.searchByName(searchView.txtName.getText(),
                    searchView.txtPath.getText());

            for (File resu : resultList) {
                searchView.listModel.addElement(resu.getAbsolutePath());
            }

        }

    }
}
