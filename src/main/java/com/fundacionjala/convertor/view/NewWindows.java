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
        //pSearch.setBackground(Color.ORANGE);
        pSearch.setBounds(0,0,430,350);
        this.getContentPane().add(pSearch);
        NewSearchViewer sv = new NewSearchViewer();
        pSearch.add(sv.getContentPane());


        JPanel searchResult = new JPanel();
        //searchResult.setBackground(Color.ORANGE);
        searchResult.setBounds(0, 351, 430, 318);
        this.getContentPane().add(searchResult);
        ListFileView lfv = new ListFileView();
        searchResult.add(lfv.getContentPane());


        JPanel dataFiles = new JPanel();
        //dataFiles.setBackground(Color.blue);
        dataFiles.setBounds(431, 351, 492, 118);
        this.getContentPane().add(dataFiles);
        DataFiles df = new DataFiles();
        dataFiles.add(df.getContentPane());


        JPanel pReproductor = new JPanel();
        //pReproductor.setBackground(Color.GREEN);
        pReproductor.setBounds(431,0,492,350);
        this.getContentPane().add(pReproductor);
        ListFileView listFile = new ListFileView();
        PlayerMedia playerM = new PlayerMedia(listFile, 470, 340);
        pReproductor.add(playerM);

        JPanel pConverter = new JPanel();
        //pConverter.setBackground(Color.BLACK);
        pConverter.setBounds(923, 0, 350, 470);
        Converter c = new Converter();
        this.getContentPane().add(pConverter);
        pConverter.add(c.getContentPane());




        JPanel pConverted = new JPanel();
        ///pConverted.setBackground(Color.RED);
        pConverted.setBounds(430,470,850,200);
        this.getContentPane().add(pConverted);
        ListConverting lc = new ListConverting();
        pConverted.add(lc.getContentPane());
    }

    private void starComponents(){
        makePanels();
    }

    private void setWindow() {
        setSize(1280, 700);
        setTitle("Converter Team A");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        setResizable(false);


    }

}


