package com.fundacionjala.convertor.view;

import javax.swing.*;
import java.awt.*;

public class DataFiles extends JDialog {
    JPanel dataPanel;
    JTextArea data;

    public DataFiles() {
        dataPanel = new JPanel(new BorderLayout());
        data = new JTextArea();
        data.setText("TextField");
        data.setEditable(false);
        data.setBorder(BorderFactory.createEmptyBorder(0,0,150,350));
        data.setBackground(Color.white);
        dataPanel.add(data, BorderLayout.CENTER);
        dataPanel.setBackground(Color.green);
        this.add(dataPanel);
        this.setResizable(false);
    }
}
