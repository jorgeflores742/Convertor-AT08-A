package com.fundacionjala.convertor.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class NewWindows extends JFrame {
    private NewSearchViewer sView = new NewSearchViewer();
    private ListFileView listFile = new ListFileView();
    private DataFiles data = new DataFiles();;
    private PlayerMedia playerM;
    private Converter converting;
    private ListConverting listConv = new ListConverting();



    public NewWindows() {
        setWindow();
        starComponents();
        try{
            Image img = ImageIO.read(new File("img\\camaleon32.png"));
            this.setIconImage(img);
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void makePanels() {
        JPanel pSearch = new JPanel();
        pSearch.setBackground(new Colors().backgroundColor);
        pSearch.setBounds(0,0,430,350);
        this.getContentPane().add(pSearch);
        pSearch.add(sView.getContentPane());


        JPanel searchResult = new JPanel();
        searchResult.setBackground(new Colors().backgroundColor);
        searchResult.setBounds(0, 350, 430, 320);
        this.getContentPane().add(searchResult);
        searchResult.add(listFile.getContentPane());


        JPanel dataFiles = new JPanel();
        dataFiles.setBackground(new Colors().backgroundColor);
        dataFiles.setBounds(430, 350, 492, 120);
        this.getContentPane().add(dataFiles);
        dataFiles.add(data.getContentPane());


        JPanel pReproductor = new JPanel();
        pReproductor.setBackground(new Colors().backgroundColor);
        pReproductor.setBounds(430,0,492,350);
        this.getContentPane().add(pReproductor);
        playerM = new PlayerMedia( 470, 340);
        pReproductor.add(playerM);

        JPanel pConverter = new JPanel();
        pConverter.setBackground(new Colors().backgroundColor);
        pConverter.setBounds(922, 0, 355, 470);
        converting = new Converter();
        this.getContentPane().add(pConverter);
        pConverter.add(converting.getContentPane());




        JPanel pConverted = new JPanel();
        pConverted.setBackground(new Colors().backgroundColor);
        pConverted.setBounds(430,470,850,200);
        this.getContentPane().add(pConverted);
        pConverted.add(listConv.getContentPane());
    }

    private void starComponents(){
        makePanels();
    }

    private void setWindow() {
        setSize(1280, 700);
        setTitle("Converter Team A");
        this.setBackground(new Colors().cmbColor);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        setResizable(false);
        this.setBackground(new Colors().backgroundColor);


    }

    public NewSearchViewer getsView() {
        return sView;
    }

    public ListFileView getListFile() {
        return listFile;
    }

    public DataFiles getData() {
        return data;
    }

    public PlayerMedia getPlayerM() {
        return playerM;
    }

    public Converter getConverting() {
        return converting;
    }

    public ListConverting getListConv() {
        return listConv;
    }
}


