package com.fundacionjala.convertor.view;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Module View MainView.
 *
 * @author Melvi Caballero.
 * @version 1.0.
 */
public class MainView extends JFrame implements ActionListener {

    private JPanel pnlMain;

    //Search section
    private JButton btnSearch;
    private JScrollPane scrlSearchResult;
    private DefaultListModel listModel = new DefaultListModel();
    private JList lstSearchResult;
    private JPanel pnlSearch;

    private JPanel pnlPlayer;
    private JPanel pnlConvert;

    /**
     * contructor MainView.
     */
    public MainView() {
        super();

        pnlMain = new JPanel();

        //Search section
        btnSearch = new JButton();
        lstSearchResult = new JList(listModel);
        scrlSearchResult = new JScrollPane(lstSearchResult);
        pnlSearch = new JPanel(new GridLayout(2, 1));

        pnlPlayer = new JPanel();
        pnlConvert = new JPanel();
        initializeFrame();
    }

    /**
     * Initialize the frame main.
     */
    private void initializeFrame() {
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.X_AXIS));

        //Search section
        btnSearch.setText("Search");
        btnSearch.setBounds(85, 115, 200, 30);
        btnSearch.addActionListener(this);

        pnlSearch.add(btnSearch);
        pnlSearch.add(scrlSearchResult);
        pnlSearch.setBorder(BorderFactory.createLineBorder(Color.black));


        //Player section
        pnlPlayer.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        //ConverterSection
        pnlConvert.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));


        pnlMain.add(pnlSearch);
        pnlMain.add(pnlPlayer);
        //this.add(pnlConvert);

        this.add(pnlMain);
        this.setTitle("MainView");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        //  this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * @param args arguments
     */
    public static void main(final String[] args) {
        MainView v = new MainView();
        v.setVisible(true);
    }

    /**
     * @param e event buttoms.
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == btnSearch) {

            SearchView searchView = new SearchView(this);

            // searchView.pack();
            searchView.setVisible(true);
            if (searchView.getIsOk()) {
                ArrayList<String> test = searchView.getSelectedResult();
                for (String resu : test) {
                    listModel.addElement(resu);
                }

                System.out.println("Selected Ok");
            }
            searchView.dispose();

        }
    }
}
