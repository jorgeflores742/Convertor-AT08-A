package com.fundacionjala.convertor.view;

import javax.swing.*;
import java.awt.*;

public class Converter extends JDialog{
    private JPanel pnlConverter;
    private JLabel lblName;
    private JTextField txtName;

    private JLabel lblSelectConvertTo;
    private JComboBox cmbConvertTo;

    private JLabel lblType;
    private JComboBox cmbType;

    private JLabel lblFps;
    private JComboBox cmbFps;

    private JLabel lblAspectRatio;
    private JComboBox cmbAspectRatio;

    private JLabel lblResolution;
    private JComboBox cmbResolution;

    private JLabel lblVideo;
    private JLabel lblAudio;

    private JLabel lblTypeAudio;
    private JComboBox cmbTypeAudio;

    private JLabel lblChannels;
    private JComboBox cmbChannels;

    private JTextField txtPathSave;
    private JButton btnPathSave;

    private JButton btnConvert;

    public Converter() {
        pnlConverter = new JPanel(new GridBagLayout());

        lblName = new JLabel();
        txtName = new JTextField();

        lblSelectConvertTo = new JLabel();
        cmbConvertTo = new JComboBox();

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

        txtPathSave = new JTextField();
        btnPathSave = new JButton();

        btnConvert = new JButton();

        initializeControls();
        initializeFrame();

    }

    private void initializeControls() {
        lblName.setText("Name:");
        txtName.setColumns(12);

        lblSelectConvertTo.setText("Convert to:");
        cmbConvertTo.addItem("Select");
        cmbConvertTo.addItem("Audio");
        cmbConvertTo.addItem("Video");

        lblType.setText("Type:");
        cmbType.addItem("Select");
        cmbType.addItem("avi");
        cmbType.addItem("mpg");
        cmbType.addItem("mp4");
        cmbType.addItem("flv");
        cmbType.addItem("wmv");
        cmbType.addItem("mkv");

        lblFps.setText("Frames:");
        cmbFps.addItem("Select");
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
        cmbAspectRatio.addItem("Select");
        cmbAspectRatio.addItem("4:3");
        cmbAspectRatio.addItem("12:5");
        cmbAspectRatio.addItem("16:9");
        cmbAspectRatio.addItem("17:9");

        lblResolution.setText("Resolution:");
        cmbResolution.addItem("Select");
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
        cmbTypeAudio.addItem("Select");
        cmbTypeAudio.addItem("mp3");
        cmbTypeAudio.addItem("wav");
        cmbTypeAudio.addItem("ogg");
        cmbTypeAudio.addItem("m4a");
        cmbTypeAudio.addItem("wma");
        cmbTypeAudio.addItem("aac");
        cmbTypeAudio.addItem("flac");

        lblChannels.setText("Channels");
        cmbChannels.addItem("Select");
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

        txtPathSave.setColumns(12);
        btnPathSave.setIcon(new ImageIcon("img\\save.png"));

        lblAudio.setIcon(new ImageIcon("img\\music.png"));
        lblVideo.setIcon(new ImageIcon("img\\video.png"));

        btnConvert.setText("Convert");
    }

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
        pnlConverter.add(lblName, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 0;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(txtName, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 1;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(lblSelectConvertTo, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 1;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(cmbConvertTo, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 2;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(lblVideo, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 3;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(lblType, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 3;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(cmbType, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 4;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(lblFps, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 4;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(cmbFps, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 5;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(lblAspectRatio, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 5;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(cmbAspectRatio, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 6;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(lblResolution, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 6;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(cmbResolution, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 7;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(lblAudio, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 8;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(lblTypeAudio, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 8;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(cmbTypeAudio, gridPane);


        gridPane.gridx = 0;
        gridPane.gridy = 9;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(lblChannels, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 9;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(cmbChannels, gridPane);


        gridPane.gridx = 0;
        gridPane.gridy = 10;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(btnPathSave, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 10;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(txtPathSave, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 11;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlConverter.add(btnConvert, gridPane);

        this.add(pnlConverter);

        this.setSize(350, 550);
        this.setResizable(false);
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public JComboBox getCmbConvertTo() {
        return cmbConvertTo;
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

    public JTextField getTxtPathSave() {
        return txtPathSave;
    }

    public JButton getBtnPathSave() {
        return btnPathSave;
    }

    public JButton getBtnConvert() {
        return btnConvert;
    }
}