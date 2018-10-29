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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Module View Searchview.
 *
 * @author Dennis Montaño.
 * @version 1.0
 */
public class AdvancedSearchVideo extends JDialog implements ActionListener {

    private JPanel pnlVideoMain;

    JLabel lblFps;
    JComboBox cmbFps;
    ArrayList<Integer> fpsList = new ArrayList(1);


    JLabel lblAspectRatio;
    JComboBox cmbAspectRatio;
    ArrayList<String> aspectRatioList = new ArrayList<>(1);

    JLabel lblResolution;
    JComboBox cmbResolution;
    ArrayList<String> resolutionList = new ArrayList<>(1);

    /**
     * Search for videos with specific data.
     */
    public AdvancedSearchVideo() {
        pnlVideoMain = new JPanel(new GridBagLayout());
        lblFps = new JLabel();
        cmbFps = new JComboBox();
        fpsList.add(24);
        fpsList.add(25);
        fpsList.add(30);
        fpsList.add(48);
        fpsList.add(60);
        fpsList.add(120);
        fpsList.add(144);
        fpsList.add(240);
        cmbFps.setModel(new DefaultComboBoxModel(fpsList.toArray()));

        lblAspectRatio = new JLabel();
        cmbAspectRatio = new JComboBox();
        aspectRatioList.add("4:3");
        aspectRatioList.add("12:5");
        aspectRatioList.add("16:9");
        aspectRatioList.add("17:9");
        cmbAspectRatio.setModel(new DefaultComboBoxModel(aspectRatioList.toArray()));

        lblResolution = new JLabel();
        cmbResolution = new JComboBox();
        resolutionList.add("740 x 480 DVD");
        resolutionList.add("1280 x 720 DVD-HD");
        resolutionList.add("1280 x 720 720p HD Ready");
        resolutionList.add("1920 x 1080 1080p Full HD");
        resolutionList.add("2048 x 1080 2K");
        resolutionList.add("3840 x 2160 4k");
        resolutionList.add("4096 x 2160 Full 4K");
        resolutionList.add("7680 x 4320 8K");
        resolutionList.add("2560 x 1440 1440p Quad HD");
        cmbResolution.setModel(new DefaultComboBoxModel(resolutionList.toArray()));

        initializeControls();
        initializeFrame();
    }

    /**
     *
     */
    private void initializeControls() {
        lblFps.setText("Frames Per Second:");
        lblAspectRatio.setText("Aspect Ratio: ");
        lblResolution.setText("Resolution: ");
    }

    /**
     * Frames in position.
     */
    private void initializeFrame() {
        GridBagConstraints gridPane = new GridBagConstraints();
        gridPane.insets = new Insets(2, 2, 2, 2);

        gridPane.gridx = 0;
        gridPane.gridy = 0;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        pnlVideoMain.add(lblFps, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 0;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlVideoMain.add(cmbFps, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 1;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlVideoMain.add(lblAspectRatio, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 1;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlVideoMain.add(cmbAspectRatio, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 2;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlVideoMain.add(lblResolution, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 2;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlVideoMain.add(cmbResolution, gridPane);
        this.add(pnlVideoMain);

        this.setSize(350, 550);
        this.setResizable(false);
    }

    /**
     *
     * @return ComboBox.
     */
    public JComboBox getCmbFps() {
        return cmbFps;
    }

    /**
     *
     * @return ComboBox.
     */
    public JComboBox getCmbAspectRatio() {
        return cmbAspectRatio;
    }

    /**
     *
     * @return ComboBox.
     */
    public JComboBox getCmbResolution() {
        return cmbResolution;
    }

    /**
     * @param e event
     */
    @Override
    public void actionPerformed(final ActionEvent e) {

    }

}
