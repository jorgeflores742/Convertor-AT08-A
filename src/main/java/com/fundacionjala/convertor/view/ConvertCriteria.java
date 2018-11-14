///*
// * @Controller.java Copyright (c) 2018 Fundacion Jala. All rights reserved.
// * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
// *
// * This code is distributed in the hope that it will be useful, but WITHOUT
// * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
// * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
// * version 2 for more details (a copy is included in the LICENSE file that
// * accompanied this code).
// *
// * Please contact Fundacion Jala, 2643 Av Melchor Perez de Olguin, Colquiri
// * Sud, Cochabamba, Bolivia. www.fundacion-jala.org if you need additional
// * information or have any questions.
// */
//package com.fundacionjala.convertor.view;
//
//import com.fundacionjala.convertor.model.AdvancedSearchAudio;
//import com.fundacionjala.convertor.model.AdvancedSearchVideo;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import java.util.ArrayList;
//
///**
// * Module View Searchview.
// *
// * @author Dennis Montaño.
// * @version 1.0
// */
//public class ConvertCriteria extends JDialog implements ActionListener  {
//
//    private JPanel pnlVideoMain;
//    private JPanel pnlCriteria;
//    private JPanel pnlCriteriaVideo;
//    private JPanel pnlCriteriaAudio;
//    JLabel lblTitle;
//    JLabel lblType;
//    JComboBox cmbType;
//    ArrayList<String> typeList = new ArrayList<>(1);
//
//    JLabel lblFps;
//    JComboBox cmbFps;
//    ArrayList<Float> fpsList = new ArrayList(1);
//
//
//    JLabel lblAspectRatio;
//    JComboBox cmbAspectRatio;
//    ArrayList<String> aspectRatioList = new ArrayList<>(1);
//
//    JLabel lblResolution;
//    JComboBox cmbResolution;
//    ArrayList<String> resolutionList = new ArrayList<>(1);
//
//    private JComboBox cmbFileType;
//
//    private ItemHandler h = new ItemHandler();
//
//    private JLabel lblTypeAudio;
//    private JComboBox cmbTypeAudio;
//    private ArrayList<String> typeListAudio = new ArrayList<>(1);
//
//    private JLabel lblChannels;
//    private JComboBox cmbChannels;
//    private ArrayList<String> channelsList = new ArrayList<>(1);
//    private JLabel lblPath;
//    private JTextField txtPath;
//    private JButton btnConvert;
//
//
//
//    /**
//     * Search for videos with specific data.
//     */
//    public ConvertCriteria() {
//        pnlVideoMain = new JPanel(new GridBagLayout());
//        pnlCriteria = new JPanel();
//        pnlCriteriaVideo = new JPanel(new GridBagLayout());
//        pnlCriteriaAudio = new JPanel(new GridBagLayout());
//
//        lblPath = new JLabel("Path");
//        txtPath = new JTextField();
//        txtPath.setColumns(20);
//
//        btnConvert = new JButton();
//        btnConvert.setText("Convert");
//        lblTitle = new JLabel();
//        lblTitle.setText("Convert video to");
//        cmbFileType = new JComboBox();
//        cmbFileType.addItem("Select");
//        cmbFileType.addItem("Audio");
//        cmbFileType.addItem("Video");
//        cmbFileType.addItemListener(h);
//
//        lblType = new JLabel();
//        cmbType = new JComboBox();
//        cmbType.addItem("All");
//        cmbType.addItem("avi");
//        cmbType.addItem("mpg");
//        cmbType.addItem("mp4");
//        cmbType.addItem("flv");
//        cmbType.addItem("wmv");
//        cmbType.addItem("mkv");
//        cmbType.addItem("mov");
//        cmbType.addItem("webm");
//
//        lblFps = new JLabel();
//        cmbFps = new JComboBox();
//        cmbFps.addItem("All");
//        cmbFps.addItem(24f);
//        cmbFps.addItem(25f);
//        cmbFps.addItem(27f);
//        cmbFps.addItem(29.9f);
//        cmbFps.addItem(30f);
//        cmbFps.addItem(60f);
//        cmbFps.addItem(120f);
//        cmbFps.addItem(144f);
//        cmbFps.addItem(240f);
//
//        lblAspectRatio = new JLabel();
//        cmbAspectRatio = new JComboBox();
//        cmbAspectRatio.addItem("All");
//        cmbAspectRatio.addItem("4:3");
//        cmbAspectRatio.addItem("12:5");
//        cmbAspectRatio.addItem("16:9");
//        cmbAspectRatio.addItem("17:9");
//
//        lblResolution = new JLabel();
//        cmbResolution = new JComboBox();
//        cmbResolution.addItem("All");
//        cmbResolution.addItem("640x360");
//        cmbResolution.addItem("740x480");
//        cmbResolution.addItem("768x576");
//        cmbResolution.addItem("1280x720");
//        cmbResolution.addItem("1920x1080");
//        cmbResolution.addItem("2048x1080");
//        cmbResolution.addItem("3840x2160");
//        cmbResolution.addItem("4096x2160");
//        cmbResolution.addItem("7680x4320");
//        cmbResolution.addItem("2560x1440");
//
//
//        lblTypeAudio = new JLabel();
//        cmbTypeAudio = new JComboBox();
//        typeListAudio.add("All");
//        typeListAudio.add("mp3");
//        typeListAudio.add("wav");
//        typeListAudio.add("ogg");
//        typeListAudio.add("m4a");
//        typeListAudio.add("wma");
//        typeListAudio.add("aac");
//        typeListAudio.add("flac");
//        cmbTypeAudio.setModel(new DefaultComboBoxModel(typeList.toArray()));
//
//        lblChannels = new JLabel();
//        cmbChannels = new JComboBox();
//        channelsList.add("All");
//        channelsList.add("mono");
//        channelsList.add("stereo");
//        channelsList.add("2.1");
//        channelsList.add("3.0");
//        channelsList.add("3.1");
//        channelsList.add("4.0");
//        channelsList.add("4.1");
//        channelsList.add("5.0");
//        channelsList.add("5.1");
//        channelsList.add("6.1");
//        channelsList.add("7.1");
//        channelsList.add("7.2");
//        channelsList.add("8.1");
//        channelsList.add("9.1");
//        cmbChannels.setModel(new DefaultComboBoxModel(channelsList.toArray()));
//
//        initializeControls();
//        initializeFrame();
//    }
//
//    /**
//     *
//     */
//    private void initializeControls() {
//        lblType.setText("Video type: ");
//        lblFps.setText("Frames Per Second:");
//        lblAspectRatio.setText("Aspect Ratio: ");
//        lblResolution.setText("Resolution: ");
//    }
//    private class ItemHandler implements ItemListener {
//        @Override
//        public void itemStateChanged(ItemEvent e) {
//            if(e.getSource() == cmbFileType) {
//                if(cmbFileType.getSelectedItem().equals("Video")) {
//                    pnlCriteriaAudio.setVisible(false);
//                    pnlCriteriaVideo.setVisible(true);
//                    pnlCriteria.add(pnlCriteriaVideo);
//                    pnlCriteria.updateUI();
//                }else if(cmbFileType.getSelectedItem().equals("Audio")){
//                    pnlCriteriaAudio.setVisible(true);
//                    pnlCriteriaVideo.setVisible(false);
//                    pnlCriteria.add(pnlCriteriaAudio);
//                    pnlCriteria.updateUI();
//                }else if(cmbFileType.getSelectedItem().equals("Select")){
//                    pnlCriteriaAudio.setVisible(false);
//                    pnlCriteriaVideo.setVisible(false);
//                }
//            }
//        }
//    }
//    /**
//     * Frames in position.
//     */
//    private void initializeFrame() {
//        GridBagConstraints gridPane = new GridBagConstraints();
//        gridPane.insets = new Insets(2, 2, 2, 2);
//
//        gridPane.gridx = 0;
//        gridPane.gridy = 0;
//        gridPane.fill = GridBagConstraints.NONE;
//        gridPane.anchor = GridBagConstraints.LINE_END;
//        gridPane.ipady = 0;
//        gridPane.weightx = 0.6;
//        gridPane.weighty = 0;
//        pnlVideoMain.add(lblTitle, gridPane);
//
//        gridPane.gridx = 1;
//        gridPane.gridy = 0;
//        gridPane.fill = GridBagConstraints.NONE;
//        gridPane.anchor = GridBagConstraints.LINE_END;
//        gridPane.ipady = 0;
//        gridPane.weightx = 0.6;
//        gridPane.weighty = 0;
//        pnlVideoMain.add(cmbFileType, gridPane);
//
//        gridPane.gridx = 0;
//        gridPane.gridy = 1;
//        gridPane.fill = GridBagConstraints.HORIZONTAL;
//        gridPane.anchor = GridBagConstraints.CENTER;
//        gridPane.ipady = 0;
//        gridPane.weightx = 0.6;
//        gridPane.weighty = 0;
//        pnlVideoMain.add(pnlCriteria, gridPane);
//
//        gridPane.gridx = 0;
//        gridPane.gridy = 2;
//        gridPane.fill = GridBagConstraints.HORIZONTAL;
//        gridPane.anchor = GridBagConstraints.CENTER;
//        gridPane.ipady = 0;
//        gridPane.weightx = 2;
//        gridPane.weighty = 0;
//        pnlVideoMain.add(lblPath, gridPane);
//
//        gridPane.gridx = 1;
//        gridPane.gridy = 2;
//        gridPane.fill = GridBagConstraints.HORIZONTAL;
//        gridPane.anchor = GridBagConstraints.CENTER;
//        gridPane.ipady = 0;
//        gridPane.weightx = 0.6;
//        gridPane.weighty = 0;
//        pnlVideoMain.add(txtPath, gridPane);
//
//        gridPane.gridx = 0;
//        gridPane.gridy = 3;
//        gridPane.fill = GridBagConstraints.HORIZONTAL;
//        gridPane.anchor = GridBagConstraints.CENTER;
//        gridPane.ipady = 0;
//        gridPane.weightx = 0.6;
//        gridPane.weighty = 0;
//        pnlVideoMain.add(btnConvert, gridPane);
//
//        gridPane.gridx = 0;
//        gridPane.gridy = 0;
//        gridPane.fill = GridBagConstraints.NONE;
//        gridPane.anchor = GridBagConstraints.LINE_END;
//        gridPane.ipady = 0;
//        gridPane.weightx = 0.6;
//        gridPane.weighty = 0;
//        pnlCriteriaVideo.add(lblType, gridPane);
//
//
//        gridPane.gridx = 1;
//        gridPane.gridy = 0;
//        gridPane.fill = GridBagConstraints.HORIZONTAL;
//        gridPane.anchor = GridBagConstraints.CENTER;
//        gridPane.ipady = 0;
//        gridPane.weightx = 0.6;
//        gridPane.weighty = 0;
//        pnlCriteriaVideo.add(cmbType, gridPane);
//
//        gridPane.gridx = 0;
//        gridPane.gridy = 1;
//        gridPane.fill = GridBagConstraints.NONE;
//        gridPane.anchor = GridBagConstraints.LINE_END;
//        pnlCriteriaVideo.add(lblFps, gridPane);
//
//        gridPane.gridx = 1;
//        gridPane.gridy = 1;
//        gridPane.fill = GridBagConstraints.HORIZONTAL;
//        gridPane.anchor = GridBagConstraints.CENTER;
//        gridPane.ipady = 0;
//        gridPane.weightx = 0.6;
//        gridPane.weighty = 0;
//        pnlCriteriaVideo.add(cmbFps, gridPane);
//
//        gridPane.gridx = 0;
//        gridPane.gridy = 2;
//        gridPane.fill = GridBagConstraints.NONE;
//        gridPane.anchor = GridBagConstraints.LINE_END;
//        gridPane.ipady = 0;
//        gridPane.weightx = 0.6;
//        gridPane.weighty = 0;
//        pnlCriteriaVideo.add(lblAspectRatio, gridPane);
//
//        gridPane.gridx = 1;
//        gridPane.gridy = 2;
//        gridPane.fill = GridBagConstraints.HORIZONTAL;
//        gridPane.anchor = GridBagConstraints.CENTER;
//        gridPane.ipady = 0;
//        gridPane.weightx = 0.6;
//        gridPane.weighty = 0;
//        pnlCriteriaVideo.add(cmbAspectRatio, gridPane);
//
//        gridPane.gridx = 0;
//        gridPane.gridy = 3;
//        gridPane.fill = GridBagConstraints.NONE;
//        gridPane.anchor = GridBagConstraints.LINE_END;
//        gridPane.ipady = 0;
//        gridPane.weightx = 0.6;
//        gridPane.weighty = 0;
//        pnlCriteriaVideo.add(lblResolution, gridPane);
//
//        gridPane.gridx = 1;
//        gridPane.gridy = 3;
//        gridPane.fill = GridBagConstraints.HORIZONTAL;
//        gridPane.anchor = GridBagConstraints.CENTER;
//        gridPane.ipady = 0;
//        gridPane.weightx = 0.6;
//        gridPane.weighty = 0;
//        pnlCriteriaVideo.add(cmbResolution, gridPane);
//
//        gridPane.gridx = 0;
//        gridPane.gridy = 0;
//        gridPane.fill = GridBagConstraints.NONE;
//        gridPane.anchor = GridBagConstraints.LINE_END;
//        pnlCriteriaAudio.add(lblType, gridPane);
//
//        gridPane.gridx = 1;
//        gridPane.gridy = 0;
//        gridPane.fill = GridBagConstraints.HORIZONTAL;
//        gridPane.anchor = GridBagConstraints.CENTER;
//        gridPane.ipady = 0;
//        gridPane.weightx = 0.6;
//        gridPane.weighty = 0;
//        pnlCriteriaAudio.add(cmbType, gridPane);
//
//        gridPane.gridx = 0;
//        gridPane.gridy = 1;
//        gridPane.fill = GridBagConstraints.NONE;
//        gridPane.anchor = GridBagConstraints.LINE_END;
//        pnlCriteriaAudio.add(lblChannels, gridPane);
//
//        gridPane.gridx = 1;
//        gridPane.gridy = 1;
//        gridPane.fill = GridBagConstraints.HORIZONTAL;
//        gridPane.anchor = GridBagConstraints.CENTER;
//        gridPane.ipady = 0;
//        gridPane.weightx = 0.6;
//        gridPane.weighty = 0;
//        pnlCriteriaAudio.add(cmbChannels, gridPane);
//
//        this.add(pnlVideoMain);
//
//        pnlCriteria.setSize(200,200);
//        pnlCriteria.setBackground(Color.orange);
//
//        pnlVideoMain.add(pnlCriteria);
//
//        this.setSize(350, 550);
//        this.setResizable(false);
//    }
//
//    /**
//     *
//     * @return ComboBox.
//     */
//    public JComboBox getCmbType() {
//        return cmbType;
//    }
//
//    /**
//     *
//     * @return ComboBox.
//     */
//    public JComboBox getCmbFps() {
//        return cmbFps;
//    }
//
//    /**
//     *
//     * @return ComboBox.
//     */
//    public JComboBox getCmbAspectRatio() {
//        return cmbAspectRatio;
//    }
//
//    /**
//     *
//     * @return ComboBox.
//     */
//    public JComboBox getCmbResolution() {
//        return cmbResolution;
//    }
//
//    public JComboBox getCmbFileType() {
//        return cmbFileType;
//    }
//
//    public JButton getBtnConvert() {
//        return btnConvert;
//    }
//
//    /**
//     * @param e event
//     */
//    @Override
//    public void actionPerformed(final ActionEvent e) {
//
//    }
//
//}
