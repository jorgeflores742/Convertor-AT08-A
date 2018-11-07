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
        searchResult.setBounds(0, 200, 300, 270);
        this.getContentPane().add(searchResult);
        ListFileView lfv = new ListFileView();
        searchResult.add(lfv.getContentPane());






        JPanel dataFiles = new JPanel();
        dataFiles.setBackground(Color.red);
        dataFiles.setBounds(0, 470, 300, 195);
        this.getContentPane().add(dataFiles);
        DataFiles df = new DataFiles();
        dataFiles.add(df.getContentPane());





        JPanel pReproductor = new JPanel();
        pReproductor.setBackground(Color.GREEN);
        pReproductor.setBounds(400,0,800,500);
        this.getContentPane().add(pReproductor);

        JPanel pConverted = new JPanel();
        pConverted.setBackground(Color.RED);
        pConverted.setBounds(400,500,800,200);
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


