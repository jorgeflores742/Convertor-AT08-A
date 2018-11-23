package com.fundacionjala.convertor.view;

import com.fundacionjala.convertor.utils.SingleLogger;

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
    private static SingleLogger sL = SingleLogger.getInstanceLogger();

    public NewWindows() {
        sL.register(null, "INFO", "Successful - NewWindows - start");

        setWindow();
        starComponents();
        try{
            Image img = ImageIO.read(new File("img\\camaleon32.png"));
            this.setIconImage(img);
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            sL.register(e, "SEVERE", "Successful - NewWindows - start");
        }
        sL.register(null, "INFO", "Successful - NewWindows - finished");
    }

    private void makePanels() {
        sL.register(null, "INFO", "Successful - makePanels - start");

        sL.register(null, "INFO", "Successful - makePanels - panelSearch");
        JPanel pSearch = new JPanel();
        pSearch.setBackground(new Colors().backgroundColor);
        pSearch.setBounds(0,0,430,350);
        this.getContentPane().add(pSearch);
        pSearch.add(sView.getContentPane());
        sL.register(null, "INFO", "Successful - makePanels - panelSearchResult");
        JPanel searchResult = new JPanel();
        searchResult.setBackground(new Colors().backgroundColor);
        searchResult.setBounds(0, 350, 430, 320);
        this.getContentPane().add(searchResult);
        searchResult.add(listFile.getContentPane());

        sL.register(null, "INFO", "Successful - makePanels - panelDataInfo");
        JPanel dataFiles = new JPanel();
        dataFiles.setBackground(new Colors().backgroundColor);
        dataFiles.setBounds(430, 350, 492, 120);
        this.getContentPane().add(dataFiles);
        dataFiles.add(data.getContentPane());

        sL.register(null, "INFO", "Successful - makePanels - panelReproductor");
        JPanel pReproductor = new JPanel();
        pReproductor.setBackground(new Colors().backgroundColor);
        pReproductor.setBounds(430,0,492,350);
        this.getContentPane().add(pReproductor);
        playerM = new PlayerMedia( 470, 340);
        pReproductor.add(playerM);

        sL.register(null, "INFO", "Successful - makePanels - panelConverter");
        JPanel pConverter = new JPanel();
        pConverter.setBackground(new Colors().backgroundColor);
        pConverter.setBounds(922, 0, 355, 470);
        converting = new Converter();
        this.getContentPane().add(pConverter);
        pConverter.add(converting.getContentPane());

        sL.register(null, "INFO", "Successful - makePanels - panelConverted");
        JPanel pConverted = new JPanel();
        pConverted.setBackground(new Colors().backgroundColor);
        pConverted.setBounds(430,470,850,200);
        this.getContentPane().add(pConverted);
        pConverted.add(listConv.getContentPane());
        sL.register(null, "INFO", "Successful - makePanels - finished");

    }

    private void starComponents(){
        sL.register(null, "INFO", "Successful - startComponents - start");
        makePanels();
        sL.register(null, "INFO", "Successful - startComponents - finished");
    }

    private void setWindow() {
        sL.register(null, "INFO", "Successful - setWindow - start");
        setSize(1280, 700);
        setTitle("Chameleon Converter");
        this.setBackground(new Colors().cmbColor);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        setResizable(false);
        this.setBackground(new Colors().backgroundColor);
        sL.register(null, "INFO", "Successful - setWindow - finished");

    }

    public NewSearchViewer getsView() {
        sL.register(null, "INFO", "Successful - NewSearchViewer - start");
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


