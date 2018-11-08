package com.fundacionjala.convertor.view;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.io.Serializable;

public class NewWindows extends JFrame {
    public NewWindows () {
        setWindow();
        starComponents();
    }

    private void makePanels() {
        JPanel pSearch = new JPanel();
        pSearch.setBackground(Color.ORANGE);
        pSearch.setBounds(0,0,350,200);
        this.getContentPane().add(pSearch);
        NewSearchViewer sv = new NewSearchViewer();
        pSearch.add(sv.getContentPane());


        JPanel searchResult = new JPanel();
        searchResult.setBackground(Color.BLUE);
        searchResult.setBounds(0, 200, 350, 270);
        this.getContentPane().add(searchResult);
        ListFileView lfv = new ListFileView();
        searchResult.add(lfv.getContentPane());






        JPanel dataFiles = new JPanel();
        dataFiles.setBackground(Color.CYAN);
        dataFiles.setBounds(0, 470, 350, 205);
        this.getContentPane().add(dataFiles);
        DataFiles df = new DataFiles();
        dataFiles.add(df.getContentPane());





        JPanel pReproductor = new JPanel();
        pReproductor.setBackground(Color.GREEN);
        pReproductor.setBounds(350,0,550,450);
        this.getContentPane().add(pReproductor);

        JPanel pConverter = new JPanel();
        pConverter.setBackground(Color.MAGENTA);
        pConverter.setBounds(00, 0, 150, 450);
        this.getContentPane().add(pConverter);


        JPanel pConverted = new JPanel();
        pConverted.setBackground(Color.RED);
        pConverted.setBounds(350,470,850,200);
        this.getContentPane().add(pConverted);
    }

    private void starComponents(){
        makePanels();
    }

    private void setWindow() {
        setSize(1200, 700);
        setTitle("Convertes Team A");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        setResizable(false);
    }

}


