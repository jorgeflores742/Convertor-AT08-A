package com.fundacionjala.convertor.view;

import javax.swing.*;

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
    }

    private void makePanels() {
        JPanel pSearch = new JPanel();
        //pSearch.setBackground(Color.ORANGE);
        pSearch.setBounds(0,0,430,350);
        this.getContentPane().add(pSearch);
        pSearch.add(sView.getContentPane());


        JPanel searchResult = new JPanel();
        //searchResult.setBackground(Color.ORANGE);
        searchResult.setBounds(0, 351, 430, 318);
        this.getContentPane().add(searchResult);
        searchResult.add(listFile.getContentPane());


        JPanel dataFiles = new JPanel();
        //dataFiles.setBackground(Color.blue);
        dataFiles.setBounds(431, 351, 492, 118);
        this.getContentPane().add(dataFiles);
        dataFiles.add(data.getContentPane());


        JPanel pReproductor = new JPanel();
        //pReproductor.setBackground(Color.GREEN);
        pReproductor.setBounds(431,0,492,350);
        this.getContentPane().add(pReproductor);
        playerM = new PlayerMedia(listFile, 470, 340);
        pReproductor.add(playerM);

        JPanel pConverter = new JPanel();
        //pConverter.setBackground(Color.BLACK);
        pConverter.setBounds(923, 0, 350, 470);
        converting = new Converter();
        this.getContentPane().add(pConverter);
        pConverter.add(converting.getContentPane());




        JPanel pConverted = new JPanel();
        ///pConverted.setBackground(Color.RED);
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
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        setResizable(false);


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


