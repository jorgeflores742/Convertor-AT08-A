package com.fundacionjala.convertor.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Dennis Montaño and Melvi Caballero.
 */
public class ViewConverter extends JFrame {

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private int ADVANCED_SEARCH7 = (int) (dim.getHeight()*7)/100;
    private int ADV_VIDEO2 = (int) (dim.getHeight()*2)/100;
    private int ADV_AUDIO5 = (int) (dim.getHeight()*5)/100;
    private int playerWidth = (int) (dim.getWidth()*64)/100;
    private int playerHeight = (int) (dim.getHeight()*40)/100;
    private int converterHeight = (int) (dim.getHeight()*28)/100;

    JPanel mainPanel = new JPanel();
    JPanel advanceSearchPane = new JPanel();
    private SearchViewer sView = new SearchViewer();
    private AdvancedSearchVideoView advanceVideo = new AdvancedSearchVideoView();
    private AdvancedSearchAudioView advanceAudio = new AdvancedSearchAudioView();
    private ListFileView listFile = new ListFileView();
    private DataFiles data = new DataFiles();
    private Converter converting;

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
        setBounds(0, 0, (int) dim.getWidth(), (int)dim.getHeight()-50);
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

        //mainPanel.setBackground(Color.white);
        add(mainPanel);

        /**
         * Busqueda
         */

        //panel de busqueda
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.blue);
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));

        JPanel defaultSearchPanel = new JPanel();
        // sView = new SearchViewer();
        defaultSearchPanel.add(sView.getContentPane());
        System.out.println(dim);

        JLabel textAll = new JLabel();
        textAll.setText("Busqueda por defecto");
        advanceSearchPane.add(textAll);
        advanceSearchPane.setBorder(BorderFactory.createEmptyBorder(ADVANCED_SEARCH7,0, ADVANCED_SEARCH7,0));

        JPanel listLayout = new JPanel();
        listLayout.setLayout(new GridLayout());
        listLayout.add(listFile.getContentPane());

        JPanel dataFilePane = new JPanel();
        dataFilePane.setLayout(new GridLayout());
        dataFilePane.add(data.getContentPane());

        searchPanel.add(defaultSearchPanel);
        searchPanel.add(advanceSearchPane);
        searchPanel.add(listLayout);
        searchPanel.add(dataFilePane);


        mainPanel.add(searchPanel);

        /**
         * Conversor
         */
        //panel conversor
        JPanel converterPanel = new JPanel();
        converterPanel.setBackground(Color.green);
        converterPanel.setLayout(new BorderLayout());

        JPanel playerPanel = new JPanel();
        PlayerMedia playerM = new PlayerMedia(listFile, playerWidth, playerHeight);
        playerPanel.add(playerM);
        playerPanel.setPreferredSize(new Dimension(playerWidth, playerHeight));
        //playerPanel.setBackground(Color.YELLOW);

        JPanel ConverterOptions = new JPanel();
        converting = new Converter();
        ConverterOptions.add(converting.getContentPane());
        ConverterOptions.setPreferredSize(new Dimension(playerWidth, converterHeight));
        ConverterOptions.setBackground(Color.darkGray);

        JPanel listConvertorPanel = new JPanel();
        ListConverting listConv = new ListConverting();
        listConvertorPanel.setLayout(new GridLayout());
        listConvertorPanel.setPreferredSize(new Dimension(playerWidth, converterHeight));
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
        advanceSearchPane.setBorder(BorderFactory.createEmptyBorder(ADV_VIDEO2,0,ADV_VIDEO2,0));
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
        advanceSearchPane.setBorder(BorderFactory.createEmptyBorder(ADVANCED_SEARCH7,0, ADVANCED_SEARCH7,0));
        mainPanel.updateUI();
    }

    public void loadAdSearchAudio() {
        advanceSearchPane.removeAll();
        //advanceAudio = new AdvancedSearchAudio();
        advanceSearchPane.add(advanceAudio.getContentPane());
        advanceSearchPane.setBorder(BorderFactory.createEmptyBorder(ADV_AUDIO5,0,ADV_AUDIO5,0));
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

    public SearchViewer getSearchviewer() {
        return sView;
    }
    public AdvancedSearchVideoView getAdvSearchVideoView() {
        return advanceVideo;
    }

    public AdvancedSearchAudioView getAdvSearchAudioView() {
        return advanceAudio;
    }

    public ListFileView getListFile() {
        return listFile;
    }

    public DataFiles getData() {
        return data;
    }

    public Converter getConverting() {
        return converting;
    }


    /*public static void main(String[] args) {
        ViewConverter newView = new ViewConverter();
        newView.setVisible(true);
    }*/
}


    