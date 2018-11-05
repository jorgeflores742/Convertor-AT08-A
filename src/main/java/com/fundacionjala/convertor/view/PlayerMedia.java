package com.fundacionjala.convertor.view;

import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
        JButton btnPlay = new JButton("Play");
        JButton btnRewind = new JButton("Rewind");
        JButton btnSkip = new JButton("Skip");

        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                player.getMediaPlayer().playMedia(listFV.getUrl().getAbsolutePath());
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

        buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.LINE_AXIS));
        buttonsContainer.add(btnPlay);
        buttonsContainer.add(btnRewind);
        buttonsContainer.add(btnSkip);

        this.add(buttonsContainer, BorderLayout.CENTER);
    }
}
