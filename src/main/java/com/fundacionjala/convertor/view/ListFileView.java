/*
 * @Controller.java Copyright (c) 2018 Fundacion Jala. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * Please contact Fundacion Jala, 2643 Av Melchor Perez de Olguin, Colquiri
 * Sud, Cochabamba, Bolivia. www.fundacion-jala.org if you need additional
 * information or have any questions.
 */
package com.fundacionjala.convertor.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Dennis Montaño
 * List of results.
 */
public class ListFileView extends JDialog{

    private JScrollPane scrlSearchResult;
    private DefaultListModel listModel = new DefaultListModel();
    private JList lstSearchResult;
    private JPanel pnlMain;
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * List of data received.
     */
    public ListFileView() {
        pnlMain = new JPanel(new GridBagLayout());
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
        lstSearchResult.setPreferredSize(new Dimension(((int) dim.getWidth())/3, ((int) dim.getHeight())-440));
        scrlSearchResult.setPreferredSize(new Dimension(((int) dim.getWidth())/3, ((int) dim.getHeight())-440));
    }
}
