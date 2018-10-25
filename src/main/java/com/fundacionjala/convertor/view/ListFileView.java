package com.fundacionjala.convertor.view;

import javax.swing.*;
import java.awt.*;

public class ListFileView extends JDialog{

    private JScrollPane scrlSearchResult;
    private DefaultListModel listModel = new DefaultListModel();
    private JList lstSearchResult;
    private JPanel pnlMain;

    public ListFileView() {
        pnlMain = new JPanel(new GridBagLayout());
        lstSearchResult = new JList(listModel);
        scrlSearchResult = new JScrollPane(lstSearchResult);
        initializeControls();
        initializeFrame();
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
        pnlMain.add(scrlSearchResult, c);
        this.add(pnlMain);

    }

    private void initializeControls() {
        //lstSearchResult.setPreferredSize(new Dimension(350, 250));
        //scrlSearchResult.setPreferredSize(new Dimension(350, 250));
    }
}
