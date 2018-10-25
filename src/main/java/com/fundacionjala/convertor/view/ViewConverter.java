package com.fundacionjala.convertor.view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.*;

public class ViewConverter extends JFrame {

    public ViewConverter() {
        iniView();
        settingsView();
    }

    public void iniView() {
        setSize(1280, 1024);
        setTitle("Convertor");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.blue);
    }

    public void settingsView() {

        //Panel base
//        JPanel basePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        add(basePanel);

        //panel principal
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        //mainPanel.setSize(1280, 1024);
        mainPanel.setBackground(Color.red);
        add(mainPanel);

        //panel de busqueda
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.blue);
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));

        //panel conversor
        JPanel converterPanel = new JPanel();
        converterPanel.setBackground(Color.green);
        converterPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        JPanel listConvertorPanel = new JPanel();
        listConvertorPanel.setLayout(new BoxLayout(listConvertorPanel, BoxLayout.Y_AXIS));

        converterPanel.add(playerPanel);
        converterPanel.add(listConvertorPanel);

        //add ambos paneles
        mainPanel.add(searchPanel);
        mainPanel.add(converterPanel);

        JPanel defaultSearchPanel = new JPanel();
        SearchViewer sView = new SearchViewer();
        defaultSearchPanel.add(sView.getContentPane());

        JPanel listLayout = new JPanel(new BorderLayout());
        ListFileView lfv = new ListFileView();
        listLayout.setLayout(new GridLayout());
        listLayout.add(lfv.getContentPane(),BorderLayout.SOUTH);

        searchPanel.add(defaultSearchPanel);
        searchPanel.add(listLayout);
    }

    public static void main(String[] args) {
        ViewConverter newView = new ViewConverter();
        newView.setVisible(true);
    }
}
