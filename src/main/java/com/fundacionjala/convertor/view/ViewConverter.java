
/*
 * @Controller.java Copyright (c) 2018 Fundacion Jala. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * Please contact Fundacion Jala, 2643 Av Melchor Perez de Olguin, Colquiri
 * Sud, Cochabamba, Bolivia. www.fundacion-jala.org if you need additional
 * information or have any questions.
 */

package com.fundacionjala.convertor.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Dennis Montaño and Melvi Caballero.
 */
public class ViewConverter extends JFrame {

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel advanceSearchPane = new JPanel();
    SearchViewer sView;
    AdvancedSearchVideo advanceVideo;

    /**
     * Constructor.
     */
    public ViewConverter() {
        iniView();
        settingsView();
    }

    /**
     * initializing main panel.
     */
    public void iniView() {
        setBounds(0, 0, (int) dim.getWidth(), (int)dim.getHeight());
        setTitle("Convertor");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.blue);
    }

    /**
     * Initializiing panes.
     */
    public void settingsView() {
        //panel principal

        mainPanel.setBackground(Color.red);
        add(mainPanel);

        /**
         * Busqueda
         */

        //panel de busqueda
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.blue);
        searchPanel.setLayout(new BorderLayout());

        JPanel defaultSearchPanel = new JPanel();
        sView = new SearchViewer();
        defaultSearchPanel.add(sView.getContentPane());
        System.out.println(sView.getSize());

        JLabel textAll = new JLabel();
        textAll.setText("Busqueda por defecto");
        advanceSearchPane.add(textAll);
        advanceSearchPane.setBorder(BorderFactory.createEmptyBorder(55,0,55,0));

        JPanel listLayout = new JPanel();
        ListFileView listFile = new ListFileView();
        listLayout.setLayout(new GridLayout());
        listLayout.add(listFile.getContentPane());

        searchPanel.add(defaultSearchPanel, BorderLayout.NORTH);
        searchPanel.add(listLayout, BorderLayout.SOUTH);
        searchPanel.add(advanceSearchPane, BorderLayout.CENTER);

        mainPanel.add(searchPanel);

        /**
         * Conversor
         */
        //panel conversor
        JPanel converterPanel = new JPanel();
        converterPanel.setBackground(Color.green);
        converterPanel.setLayout(new BorderLayout());
//        converterPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        JPanel listConvertorPanel = new JPanel();
        listConvertorPanel.setLayout(new BoxLayout(listConvertorPanel, BoxLayout.Y_AXIS));

        converterPanel.add(playerPanel);
        converterPanel.add(listConvertorPanel);

        mainPanel.add(converterPanel, BorderLayout.CENTER);

        ActionListener advanced = e -> {
            String type = sView.getCmbFileType().getSelectedItem().toString();
            if (type.equals("Video")) {
                loadAdSearchVideo();
            } else if (type.equals("All")) {
                loadAllVideo();
            }
        };

        sView.getCmbFileType().addActionListener(advanced);

    }

    /**
     * Change of pane to video search.
     */
    public  void loadAdSearchVideo() {
        advanceSearchPane.removeAll();
        advanceVideo = new AdvancedSearchVideo();
        advanceSearchPane.add(advanceVideo.getContentPane());
        advanceSearchPane.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
        mainPanel.updateUI();
    }

    /**
     * Change of pane to default search.
     */
    public void loadAllVideo() {
        advanceSearchPane.removeAll();
        JLabel textAll = new JLabel();
        textAll.setText("Busqueda por defecto");
        advanceSearchPane.add(textAll);
        advanceSearchPane.setBorder(BorderFactory.createEmptyBorder(55,0,55,0));
        mainPanel.updateUI();
    }

    /**
     *
     * @return item Selected.
     */
    public String getTxtPath() {
        return sView.getTxtPath().toString();
    }

    /**
     *
     * @return item Selected.
     */
    public String getFileName() {
        return sView.getTxtName().toString();
    }

    /**
     *
     * @return item Selected.
     */
    public String getFileSize() {
        return sView.getCmbSize().getSelectedItem().toString();
    }

    /**
     *
     * @return item Selected.
     */
    public String getFileType() {
        return sView.getCmbFileType().getSelectedItem().toString();
    }

    /**
     *
     * @return item Selected.
     */
    public String getFPS() {
        return advanceVideo.getCmbFps().getSelectedItem().toString();
    }

    /**
     *
     * @return item Selected.
     */
    public String getAspectRatio() {
        return advanceVideo.getCmbAspectRatio().getSelectedItem().toString();
    }

    /**
     *
     * @return item Selected.
     */
    public String getResolution() {
        return advanceVideo.getCmbResolution().getSelectedItem().toString();
    }

    public static void main(String[] args) {
        ViewConverter newView = new ViewConverter();
        newView.setVisible(true);
    }
}
