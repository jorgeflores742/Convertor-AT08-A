package com.fundacionjala.convertor.view;

import javax.swing.*;
import java.awt.*;

public class Converter extends JDialog{
    private JPanel player;
    private JLabel playerText;
    private JButton btnConvert;
    private JPanel converterBox1;
    private JPanel converterBox2;
    private JTextField pathFile;

    public Converter() {
        player = new JPanel();
        playerText = new JLabel();
        player.add(playerText);

        converterBox1 = new JPanel();
        btnConvert = new JButton();
        pathFile = new JTextField();


        btnConvert.setText("CONVERT");
        converterBox1.add(btnConvert);
        player.add(converterBox1);

        converterBox2 = new JPanel();
        AdvancedSearchVideo asv = new AdvancedSearchVideo();
        converterBox2.add(asv.getContentPane());
        player.add(converterBox2);
        this.add(player);
        this.setResizable(false);




    }
}