package com.fundacionjala.convertor.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Module View MainView.
 *
 * @author Melvi Caballero.
 * @version 1.0.
 */
public class ConvertController implements ActionListener {
    /// private ConvertView convertView;
    /// private ConvertModel convertModel;
    private ConvertCriteria convertCriteria;

    /**
     *
     */
  /*  public ConvertController(final ConvertView convertView, final ConvertModel convertModel) {
        this.convertView = convertView;
        this.convertModel = convertModel;
       /// this.convertView.btnConvert.addActionListener(this);
        convertCriteria=new ConvertCriteria();
    }*/

    /**
     *
     */
    public void loadConvertCriteria() {
        ///  convertCriteria.setFormatFrom(convertView.txtFormatFrom.getText());
        ///  convertCriteria.setFormatTo(convertView.cmbFormatTo.getSelectedItem().toString());
        /// convertCriteria.setFileName(convertView.txtFileName.getTest());
    }

    /**
     * @param e actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ///  if (e.getSource() == convertView.btnConvert) {
            /*

            ArrayList<File> ConvertList = fileSearcher.searchFiles(convertCriteria.getFormatFrom(),convertCriteria.getFormatTo(),
                    convertCriteria.getFileName());
            for (File resu : resultList) {
                searchView.listModel.addElement(resu.getAbsolutePath());

            }*/
    }
}
}