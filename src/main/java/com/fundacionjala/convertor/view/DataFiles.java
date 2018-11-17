package com.fundacionjala.convertor.view;

import javax.swing.*;
import java.awt.*;

public class DataFiles extends JDialog {

    JPanel dataPanel;
    DefaultListModel defaultList = new DefaultListModel();
    JList dataList;
    JScrollPane scrData;

    public DataFiles() {
        dataPanel = new JPanel(new GridBagLayout());
        defaultList.addElement("Selected file Info");
        dataList = new JList(defaultList);
        scrData = new JScrollPane(dataList);
        dataList.setBackground(new Colors().cmbColor);
        initializeControls();
        initializeFrame();
    }

    private void initializeControls() {

        dataPanel.setPreferredSize(new Dimension(470, 110));

    }

    private void initializeFrame() {
        dataPanel.setBackground(new Colors().cmbColor);
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

    public DefaultListModel getDefaultList() {
        return defaultList;
    }
}
