package com.fundacionjala.convertor.view;

import javax.swing.*;

public class Converter extends JDialog{
    private JPanel panelConverter;
    private JLabel playerText;
    private JButton btnConvert;
    private JPanel converterBox1;
    private JTextField pathFile;

    public JButton getBtnConvert() {
        return btnConvert;
    }

    public Converter() {
        panelConverter = new JPanel();
        playerText = new JLabel();
        panelConverter.add(playerText);

        converterBox1 = new JPanel();
        btnConvert = new JButton();
        pathFile = new JTextField();


        //btnConvert.setText("CONVERT");
        btnConvert.setIcon(new ImageIcon("img\\camera.png"));
        converterBox1.add(btnConvert);
        panelConverter.add(converterBox1);


        this.add(panelConverter);
        this.setResizable(false);


    }
}