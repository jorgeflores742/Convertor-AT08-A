package com.fundacionjala.convertor.view;

import javax.swing.*;

public class Converter extends JDialog{
    private JPanel player;
    private JLabel playerText;

    private JPanel converterBox2;



    public Converter() {
        player = new JPanel();
        playerText = new JLabel();
        player.add(playerText);


        converterBox2 = new JPanel();
        ConvertCriteria asv = new ConvertCriteria();
        converterBox2.add(asv.getContentPane());
        player.add(converterBox2);
        this.add(player);
        this.setResizable(false);


    }
}