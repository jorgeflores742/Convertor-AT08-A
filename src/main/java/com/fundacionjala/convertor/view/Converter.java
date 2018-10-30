package com.fundacionjala.convertor.view;

import javax.swing.*;

public class Converter extends JDialog{
    private JPanel player;
    private JLabel playerText;

    public Converter() {
        player = new JPanel();
        playerText = new JLabel();
        playerText.setText("CONVERTER OPTIONS COMING SOON");
        player.add(playerText);

        this.add(player);
        this.setResizable(false);
    }
}
