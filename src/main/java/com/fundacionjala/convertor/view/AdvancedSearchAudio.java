package com.fundacionjala.convertor.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdvancedSearchAudio extends JDialog{
    private JPanel pnlAudioMain;

    JLabel lblChannels;
    JComboBox cmbChannels;
    ArrayList<String> channelsList = new ArrayList<>(1);

    public AdvancedSearchAudio() {
        pnlAudioMain = new JPanel(new GridBagLayout());
        lblChannels = new JLabel();
        cmbChannels = new JComboBox();
        channelsList.add("Mono");
        channelsList.add("Stereo");
        channelsList.add("2.1");
        channelsList.add("3.0");
        channelsList.add("3.1");
        channelsList.add("4.0");
        channelsList.add("4.1");
        channelsList.add("5.0");
        channelsList.add("5.1");
        channelsList.add("6.1");
        channelsList.add("7.1");
        channelsList.add("7.2");
        channelsList.add("8.1");
        channelsList.add("9.1");
        cmbChannels.setModel(new DefaultComboBoxModel(channelsList.toArray()));

        initializeControls();
        initializeFrame();
    }

    private void initializeFrame() {
        GridBagConstraints gridPane = new GridBagConstraints();
        gridPane.insets = new Insets(2, 2, 2, 2);
        gridPane.gridx = 0;
        gridPane.gridy = 0;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        pnlAudioMain.add(lblChannels, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 0;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlAudioMain.add(cmbChannels, gridPane);

        this.add(pnlAudioMain);
        this.setResizable(false);
    }

    private void initializeControls() {
        lblChannels.setText("Channels: ");
    }
}
