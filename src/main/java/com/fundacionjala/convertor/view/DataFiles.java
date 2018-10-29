package com.fundacionjala.convertor.view;

import javax.swing.*;
import java.awt.*;

public class DataFiles extends JDialog {
    JPanel dataPanel;
    JTextField data;

    public DataFiles() {
        dataPanel = new JPanel(new GridBagLayout());
        data = new JTextField();
        dataPanel.add(data);

        this.add(dataPanel);
        this.setResizable(false);
    }
}
