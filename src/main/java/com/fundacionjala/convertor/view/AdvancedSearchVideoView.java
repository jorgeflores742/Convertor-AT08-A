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
public class AdvancedSearchVideoView extends JDialog implements ActionListener  {

    private JPanel pnlVideoMain;

    JLabel lblType;
    JComboBox cmbType;
    ArrayList<String> typeList = new ArrayList<>(1);

    JLabel lblFps;
    JComboBox cmbFps;
    ArrayList<Float> fpsList = new ArrayList(1);


    JLabel lblAspectRatio;
    JComboBox cmbAspectRatio;
    ArrayList<String> aspectRatioList = new ArrayList<>(1);

    JLabel lblResolution;
    JComboBox cmbResolution;
    ArrayList<String> resolutionList = new ArrayList<>(1);

    /**
     * Search for videos with specific data.
     */
    public AdvancedSearchVideoView() {
        pnlVideoMain = new JPanel(new GridBagLayout());

        lblType = new JLabel();
        cmbType = new JComboBox();
        cmbType.addItem("All");
        cmbType.addItem("avi");
        cmbType.addItem("mpg");
        cmbType.addItem("mp4");
        cmbType.addItem("flv");
        cmbType.addItem("wmv");

        lblFps = new JLabel();
        cmbFps = new JComboBox();
        cmbFps.addItem("All");
        cmbFps.addItem(24f);
        cmbFps.addItem(25f);
        cmbFps.addItem(27f);
        cmbFps.addItem(29.9f);
        cmbFps.addItem(30f);
        cmbFps.addItem(60f);
        cmbFps.addItem(120f);
        cmbFps.addItem(144f);
        cmbFps.addItem(240f);

        lblAspectRatio = new JLabel();
        cmbAspectRatio = new JComboBox();
        cmbAspectRatio.addItem("All");
        cmbAspectRatio.addItem("4:3");
        cmbAspectRatio.addItem("12:5");
        cmbAspectRatio.addItem("16:9");
        cmbAspectRatio.addItem("17:9");

        lblResolution = new JLabel();
        cmbResolution = new JComboBox();
        cmbResolution.addItem("All");
        cmbResolution.addItem("640x360");
        cmbResolution.addItem("740x480");
        cmbResolution.addItem("1280x720");
        cmbResolution.addItem("1920x1080");
        cmbResolution.addItem("2048x1080");
        cmbResolution.addItem("3840x2160");
        cmbResolution.addItem("4096x2160");
        cmbResolution.addItem("7680x4320");
        cmbResolution.addItem("2560x1440");

        initializeControls();
        initializeFrame();
    }

    /**
     *
     */
    private void initializeControls() {
        lblType.setText("Video type: ");
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
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlVideoMain.add(lblType, gridPane);


        gridPane.gridx = 1;
        gridPane.gridy = 0;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlVideoMain.add(cmbType, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 1;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        pnlVideoMain.add(lblFps, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 1;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlVideoMain.add(cmbFps, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 2;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlVideoMain.add(lblAspectRatio, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 2;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlVideoMain.add(cmbAspectRatio, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 3;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlVideoMain.add(lblResolution, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 3;
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
    public JComboBox getCmbType() {
        return cmbType;
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
