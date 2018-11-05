package com.fundacionjala.convertor.view;

import javax.swing.*;
import java.awt.*;

public class DataFiles extends JDialog {
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private int listHeight = (int) (dim.getHeight()*10)/100;

    JPanel dataPanel;
    DefaultListModel defaultList = new DefaultListModel();
    JList dataList;
    JScrollPane scrData;

    public DataFiles() {
        dataPanel = new JPanel(new GridBagLayout());
        defaultList.addElement("Informacion de archivo seleccionado.");
        dataList = new JList(defaultList);
        scrData = new JScrollPane(dataList);
        initializeControls();
        initializeFrame();
    }

    private void initializeControls() {
        scrData.setPreferredSize(new Dimension(((int) dim.getWidth())/3, listHeight));
    }

    private void initializeFrame() {

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2, 2, 2, 2);

        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.ipady = 80;
        c.weightx = 1;
        c.weighty = 1.0;
        dataPanel.add(scrData, c);

        //dataPanel.setBackground(Color.green);
        this.add(dataPanel);
        this.setResizable(false);

    }
}
