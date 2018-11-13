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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Module View Searchview.
 *
 * @author Melvi Caballero
 * @version 1.0
 */
public class NewSearchViewer extends JDialog implements ActionListener {

    private JPanel pnlMain;

    private JLabel lblPath;
    private JTextField txtPath;
    private JButton btnPath;

    private JLabel lblName;
    private JTextField txtName;

    private JLabel lblFileType;
    private JComboBox cmbFileType;

    private JLabel lblSize;
    private JComboBox cmbSize;

    private JButton btnSearch;
    private JButton btnClearList;

    private JLabel lblType;
    private JComboBox cmbType;
    ArrayList<String> typeList = new ArrayList<>(1);

    private JLabel lblFps;
    private JComboBox cmbFps;
    ArrayList<Float> fpsList = new ArrayList(1);


    private JLabel lblAspectRatio;
    private JComboBox cmbAspectRatio;
    ArrayList<String> aspectRatioList = new ArrayList<>(1);

    private JLabel lblResolution;
    private JComboBox cmbResolution;
    ArrayList<String> resolutionList = new ArrayList<>(1);

    private JLabel lblVideo;
    private JLabel lblAudio;


    private JLabel lblTypeAudio;
    private JComboBox cmbTypeAudio;

    private JLabel lblChannels;
    private JComboBox cmbChannels;


    private JLabel lblVideoAC;
    private JComboBox cmbVideoAC;

    private JLabel lblVideoVC;
    private JComboBox cmbVideoVC;

    private JLabel lblAudioAC;
    private JComboBox cmbAudioAC;

    /**
     * Pane for default search.
     */
    public NewSearchViewer() {
        pnlMain = new JPanel(new GridBagLayout());
        lblPath = new JLabel();
        txtPath = new JTextField();
        btnPath = new JButton();
        lblName = new JLabel();
        txtName = new JTextField();
        lblSize = new JLabel();
        cmbSize = new JComboBox(FileSizeEnum.values());
        lblFileType = new JLabel();
        cmbFileType = new JComboBox(FileTypeEnum.values());
        btnSearch = new JButton();
        btnClearList = new JButton();

        lblVideo = new JLabel();
        lblAudio = new JLabel();



        lblType = new JLabel();
        cmbType = new JComboBox();

        lblFps = new JLabel();
        cmbFps = new JComboBox();

        lblAspectRatio = new JLabel();
        cmbAspectRatio = new JComboBox();

        lblResolution = new JLabel();
        cmbResolution = new JComboBox();

        lblVideo = new JLabel();
        lblAudio = new JLabel();

        lblTypeAudio = new JLabel();
        cmbTypeAudio = new JComboBox();

        lblChannels = new JLabel();
        cmbChannels = new JComboBox();

        lblVideoAC = new JLabel();
        cmbVideoAC = new JComboBox();

        lblVideoVC = new JLabel();
        cmbVideoVC = new JComboBox();

        lblAudioAC = new JLabel();
        cmbAudioAC = new JComboBox();

        initializeControls();
        initializeFrame();


    }

    /**
     * Initialize elements.
     */
    private void initializeControls() {
        //pnlMain.setBackground(Color.orange);
        lblPath.setText("Path :");
        txtPath.setColumns(10);
        txtPath.setEditable(false);

        btnPath.setIcon(new ImageIcon("img\\folder.png"));
        btnPath.setPreferredSize(new Dimension(35, 25));
        //btnPath.setBackground(Color.orange);
        //btnPath.setBorder(null);


        btnPath.addActionListener(this);
        lblName.setText("File Name :");
        txtName.setColumns(10);
        lblSize.setText("Size :");
        lblFileType.setText("File Type :");

        btnSearch.setIcon(new ImageIcon("img\\searchBlack.png"));
        //btnSearch.setBackground(Color.orange);
        //btnSearch.setBorder(null);

        btnClearList.setIcon(new ImageIcon("img\\deleteBlack.png"));
        //btnClearList.setBackground(Color.orange);
        //btnClearList.setBorder(null);

        lblVideo.setIcon(new ImageIcon("img\\video.png"));
        lblAudio.setIcon(new ImageIcon("img\\music.png"));

        lblType.setText("Type:");
        cmbType.addItem("All");
        cmbType.addItem("avi");
        cmbType.addItem("mpg");
        cmbType.addItem("mp4");
        cmbType.addItem("flv");
        cmbType.addItem("wmv");
        cmbType.addItem("mkv");

        lblFps.setText("Frames:");
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

        lblAspectRatio.setText("Aspect Ratio:");
        cmbAspectRatio.addItem("All");
        cmbAspectRatio.addItem("4:3");
        cmbAspectRatio.addItem("12:5");
        cmbAspectRatio.addItem("16:9");
        cmbAspectRatio.addItem("17:9");

        lblResolution.setText("Resolution:");
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

        lblTypeAudio.setText("Type audio:");
        cmbTypeAudio.addItem("All");
        cmbTypeAudio.addItem("mp3");
        cmbTypeAudio.addItem("wav");
        cmbTypeAudio.addItem("ogg");
        cmbTypeAudio.addItem("m4a");
        cmbTypeAudio.addItem("wma");
        cmbTypeAudio.addItem("aac");
        cmbTypeAudio.addItem("flac");

        lblChannels.setText("Channels");
        cmbChannels.addItem("All");
        cmbChannels.addItem("Mono");
        cmbChannels.addItem("Stereo");
        cmbChannels.addItem("2.1");
        cmbChannels.addItem("3.0");
        cmbChannels.addItem("3.1");
        cmbChannels.addItem("4.0");
        cmbChannels.addItem("4.1");
        cmbChannels.addItem("5.0");
        cmbChannels.addItem("5.1");
        cmbChannels.addItem("6.1");
        cmbChannels.addItem("7.1");
        cmbChannels.addItem("7.2");
        cmbChannels.addItem("8.1");
        cmbChannels.addItem("9.1");

        lblVideoAC.setText("Video Codec:");
        cmbVideoAC.addItem("All");
        cmbVideoAC.addItem("mpeg4");
        cmbVideoAC.addItem("wmv1");
        cmbVideoAC.addItem("vp9");
        cmbVideoAC.addItem("msmpeg4v3");
        cmbVideoAC.addItem("flv1");
        cmbVideoAC.addItem("mpeg1video");
        cmbVideoAC.addItem("h264");
        cmbVideoAC.addItem("vp8");
        cmbVideoAC.addItem("mpeg2video");

        lblVideoVC.setText("Audio Codec:");
        cmbVideoVC.addItem("All");
        cmbVideoVC.addItem("mp3");
        cmbVideoVC.addItem("aac");

        lblAudioAC.setText("Audio Codec:");
        cmbAudioAC.addItem("All");
        cmbAudioAC.addItem("mp3");
        cmbAudioAC.addItem("aac");
    }

    /**
     * initialize panes.
     */
    private void initializeFrame() {
        GridBagConstraints gridPane = new GridBagConstraints();
        gridPane.insets = new Insets(1, 1, 1, 1);

        gridPane.gridx = 0;
        gridPane.gridy = 0;
        gridPane.anchor = GridBagConstraints.LINE_END;
        pnlMain.add(lblPath, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 0;
        gridPane.gridwidth = 4;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        pnlMain.add(txtPath, gridPane);

        gridPane.gridx = 5;
        gridPane.gridy = 0;
        gridPane.gridwidth = 1;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        pnlMain.add(btnPath, gridPane);

        gridPane.gridx = 3;
        gridPane.gridy = 12;
        gridPane.gridwidth = 1;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        pnlMain.add(btnSearch, gridPane);

        gridPane.gridx = 4;
        gridPane.gridy = 12;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        pnlMain.add(btnClearList, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 2;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        gridPane.gridwidth = 1;
        pnlMain.add(lblName, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 2;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        gridPane.gridwidth = 1;
        pnlMain.add(txtName, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 3;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(lblFileType, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 3;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(cmbFileType, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 4;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(lblSize, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 4;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(cmbSize, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 5;
        gridPane.fill = GridBagConstraints.CENTER;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(lblVideo, gridPane);

        gridPane.gridx = 4;
        gridPane.gridy = 5;
        gridPane.fill = GridBagConstraints.CENTER;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(lblAudio, gridPane);

        gridPane.gridx = 3;
        gridPane.gridy = 6;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(lblTypeAudio, gridPane);

        gridPane.gridx = 4;
        gridPane.gridy = 6;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(cmbTypeAudio, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 6;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(lblType, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 6;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(cmbType, gridPane);

        gridPane.gridx = 3;
        gridPane.gridy = 8;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(lblAudioAC, gridPane);

        gridPane.gridx = 4;
        gridPane.gridy = 8;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(cmbAudioAC, gridPane);

        gridPane.gridx = 3;
        gridPane.gridy = 7;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(lblChannels, gridPane);

        gridPane.gridx = 4;
        gridPane.gridy = 7;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(cmbChannels, gridPane);


        gridPane.gridx = 0;
        gridPane.gridy = 7;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(lblFps, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 7;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(cmbFps, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 8;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(lblAspectRatio, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 8;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(cmbAspectRatio, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 9;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(lblResolution, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 9;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(cmbResolution, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 10;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(lblVideoVC, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 10;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(cmbVideoVC, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 11;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(lblVideoAC, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 11;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.2;
        gridPane.weighty = 0;
        pnlMain.add(cmbVideoAC, gridPane);


        this.add(pnlMain);
        this.setResizable(false);
    }

    /**
     * @param e choose a path.
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == btnPath) {

            JFileChooser f = new JFileChooser();
            f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            f.showSaveDialog(null);
            txtPath.setText(String.valueOf(f.getCurrentDirectory()));
        }
    }

    /**
     * @return txtpath path.
     */
    public JTextField getTxtPath() {
        return txtPath;
    }

    /**
     * @return txtname name of file.
     */
    public JTextField getTxtName() {
        return txtName;
    }

    /**
     * @return cmbSize size of file.
     */
    public JComboBox getCmbSize() {
        return cmbSize;
    }

    /**
     * @return cmdfiletype type of file.
     */
    public JComboBox getCmbFileType() {
        return cmbFileType;
    }

    /**
     *
     * @return button search.
     */
    public JButton getBtnSearch() {
        return btnSearch;
    }

    /**
     *
     * @return button clear List.
     */
    public JButton getBtnClearList() {
        return btnClearList;
    }

    public JComboBox getCmbType() {
        return cmbType;
    }

    public JComboBox getCmbFps() {
        return cmbFps;
    }

    public JComboBox getCmbAspectRatio() {
        return cmbAspectRatio;
    }

    public JComboBox getCmbResolution() {
        return cmbResolution;
    }

    public JComboBox getCmbTypeAudio() {
        return cmbTypeAudio;
    }

    public JComboBox getCmbChannels() {
        return cmbChannels;
    }

    public JComboBox getCmbVideoAC() {
        return cmbVideoAC;
    }

    public JComboBox getCmbVideoVC() {
        return cmbVideoVC;
    }

    public JComboBox getCmbAudioAC() {
        return cmbAudioAC;
    }
}
