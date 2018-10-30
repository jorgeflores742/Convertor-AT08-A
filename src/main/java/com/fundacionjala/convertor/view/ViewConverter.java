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
    AdvancedSearchAudio advanceAudio;
    ListFileView listFile;

    /**
     * Constructor.
     */
    public ViewConverter(SearchViewer sView,AdvancedSearchAudio advanceAudio,AdvancedSearchVideo advanceVideo,ListFileView listFile) {
        this.sView = sView;
        this.advanceAudio = advanceAudio;
        this.advanceVideo = advanceVideo;
        this.listFile = listFile;

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
        // sView = new SearchViewer();
        defaultSearchPanel.add(sView.getContentPane());
        System.out.println(sView.getSize());

        JLabel textAll = new JLabel();
        textAll.setText("Busqueda por defecto");
        advanceSearchPane.add(textAll);
        advanceSearchPane.setBorder(BorderFactory.createEmptyBorder(70,0,70,0));

        JPanel listLayout = new JPanel();
        //ListFileView listFile = new ListFileView();
        listLayout.setLayout(new GridLayout());
        listLayout.add(listFile.getContentPane());

//        JPanel dataFilePane = new JPanel();
//        DataFiles data = new DataFiles();
//        dataFilePane.add(data.getContentPane());
//        dataFilePane.setBorder(BorderFactory.createEmptyBorder(50,0,50,0));

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

        JPanel playerPanel = new JPanel();
        PlayerMedia playerM = new PlayerMedia();
        playerPanel.add(playerM.getContentPane());
        playerPanel.setBorder(BorderFactory.createEmptyBorder(150,335,150,335));
        playerPanel.setBackground(Color.BLACK);

        JPanel ConverterOptions = new JPanel();
        Converter converting = new Converter();
        ConverterOptions.add(converting.getContentPane());
        ConverterOptions.setBorder(BorderFactory.createEmptyBorder(150,205,150,205));
        ConverterOptions.setBackground(Color.darkGray);

        JPanel listConvertorPanel = new JPanel();
        ListConverting listConv = new ListConverting();
        listConvertorPanel.setLayout(new GridLayout());
        listConvertorPanel.add(listConv.getContentPane());

        converterPanel.add(playerPanel, BorderLayout.NORTH);
        converterPanel.add(ConverterOptions, BorderLayout.CENTER);
        converterPanel.add(listConvertorPanel, BorderLayout.SOUTH);

        mainPanel.add(converterPanel);

        ActionListener advanced = e -> {
            String type = sView.getCmbFileType().getSelectedItem().toString();
            if (type.equals("Video")) {
                loadAdSearchVideo();
            } else if (type.equals("All")) {
                loadAllVideo();
            } else if (type.equals("Audio")) {
                loadAdSearchAudio();
            }
        };

        sView.getCmbFileType().addActionListener(advanced);

    }

    /**
     * Change of pane to video search.
     */
    public  void loadAdSearchVideo() {
        advanceSearchPane.removeAll();
        // advanceVideo = new AdvancedSearchVideo();
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
        advanceSearchPane.setBorder(BorderFactory.createEmptyBorder(70,0,70,0));
        mainPanel.updateUI();
    }

    public void loadAdSearchAudio() {
        advanceSearchPane.removeAll();
        //advanceAudio = new AdvancedSearchAudio();
        advanceSearchPane.add(advanceAudio.getContentPane());
        advanceSearchPane.setBorder(BorderFactory.createEmptyBorder(49,0,49,0));
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
     * @return item selected.
     */
    public String getVideoType() {
        return advanceVideo.getCmbType().getSelectedItem().toString();
    }

    /**
     *
     * @return item Selected.
     */
    public int getFPS() {
        return Integer.parseInt(advanceVideo.getCmbFps().getSelectedItem().toString());
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

    /**
     *
     * @return item selected.
     */
    public String getAudioType() {
        return advanceAudio.getCmbType().getSelectedItem().toString();
    }

    /**
     *
     * @return item selected.
     */
    public String getChannels() {
        return advanceAudio.getCmbChannels().getSelectedItem().toString();
    }

    /*public static void main(String[] args) {
        ViewConverter newView = new ViewConverter();
        newView.setVisible(true);
    }*/
}


