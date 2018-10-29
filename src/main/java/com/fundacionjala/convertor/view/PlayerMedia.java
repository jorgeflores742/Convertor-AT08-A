package com.fundacionjala.convertor.view;

import javax.swing.*;

public class PlayerMedia extends JDialog{
    private JPanel player;
    private JLabel playerText;

    public PlayerMedia() {
        player = new JPanel();
        playerText = new JLabel();
        playerText.setText("Inserte Reproductor aqui");
        player.add(playerText);

        this.add(player);
        this.setResizable(false);
    }
}
