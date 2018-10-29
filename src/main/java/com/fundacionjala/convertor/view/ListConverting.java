package com.fundacionjala.convertor.view;

import javax.swing.*;
import java.awt.*;

public class ListConverting extends JDialog {
    private JScrollPane scrlSearchResult;
    private DefaultListModel listModel = new DefaultListModel();
    private JList lstSearchResult;
    private JPanel pnlMain;
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public ListConverting() {
        pnlMain = new JPanel(new GridBagLayout());
        listModel.addElement("Lista de archivos convertidos y en proceso de conversion");
        lstSearchResult = new JList(listModel);
        scrlSearchResult = new JScrollPane(lstSearchResult);
        initializeControls();
        initializeFrame();
    }

    /**
     * initialize elements.
     */
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

    /**
     *  Set of size.
     */
    private void initializeControls() {
        lstSearchResult.setPreferredSize(new Dimension(250, 100));
        scrlSearchResult.setPreferredSize(new Dimension(250, 100));
    }
}
