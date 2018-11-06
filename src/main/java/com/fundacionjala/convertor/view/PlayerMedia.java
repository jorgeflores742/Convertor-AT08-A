package com.fundacionjala.convertor.view;

import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Class PlayerMedia extends JPanel.
 */
public class PlayerMedia extends JPanel {
    private static EmbeddedMediaPlayerComponent player;
    private static File file;
    private static final int MAX_VALUE = 400;
    private static int width;
    private static int height;
    ListFileView listFV;
    boolean continuePlay = false;

    /**
     * Method constructor, initialize file, player and methods for.
     */

    public PlayerMedia(ListFileView url, int playerWidth, int playerHeight) {
        height = playerHeight;
        width = playerWidth;
        NativeLibrary.addSearchPath(
                RuntimeUtil.getLibVlcLibraryName(), "lib/pluginVlcj");
        listFV =url;
        player = new EmbeddedMediaPlayerComponent();
        iniMediaPlayer();
        createButtons();
    }

    /**
     * Setting player component.
     */
    private void iniMediaPlayer() {
        this.setMinimumSize(new Dimension(MAX_VALUE, MAX_VALUE));
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        player.setSize(this.getSize());
        player.setVisible(true);
        this.add(player);
    }

    /**
     * Create player control buttons.
     */
    private void createButtons() {
        JPanel buttonsContainer = new JPanel();
        JButton btnPlay = new JButton();
        btnPlay.setIcon(new ImageIcon("img\\play.png"));
        btnPlay.setPreferredSize(new Dimension(32, 32));


        JButton btnRewind = new JButton();
        btnRewind.setIcon(new ImageIcon("img\\rewind.png"));
        btnRewind.setPreferredSize(new Dimension(32, 32));

        JButton btnSkip = new JButton();
        btnSkip.setIcon(new ImageIcon("img\\fast_forward.png"));
        btnSkip.setPreferredSize(new Dimension(32, 32));

        JButton btnStop = new JButton();
        btnStop.setIcon(new ImageIcon("img\\Stop.png"));
        btnStop.setPreferredSize(new Dimension(32, 32));

        JButton btnPause = new JButton();
        btnPause.setIcon(new ImageIcon("img\\Pause.png"));
        btnPause.setPreferredSize(new Dimension(32, 32));

        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (continuePlay) {
                    player.getMediaPlayer().start();
                    continuePlay = false;
                } else {
                    player.getMediaPlayer().playMedia(listFV.getUrl().getAbsolutePath());
                }
            }
        });
        btnSkip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.getMediaPlayer().skip(10000);
            }
        });
        btnRewind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.getMediaPlayer().skip(-10000);
            }
        });
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.getMediaPlayer().stop();
            }
        });
        btnPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.getMediaPlayer().pause();
                continuePlay = true;
            }
        });

        buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.LINE_AXIS));
        buttonsContainer.add(btnPlay);
        buttonsContainer.add(btnPause);
        buttonsContainer.add(btnStop);
        buttonsContainer.add(btnRewind);
        buttonsContainer.add(btnSkip);

        this.add(buttonsContainer, BorderLayout.CENTER);
    }

    public void setFile(File file) {
        this.file=file;
    }
    public EmbeddedMediaPlayer getPlayer() {
        return player.getMediaPlayer();
    }
}
