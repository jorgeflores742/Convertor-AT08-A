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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Module View Searchview.
 *
 * @author Melvi Caballero
 * @version 1.0
 */
public class NewSearchViewer extends JDialog implements ActionListener {

    private JPanel pnlMain;

    private JLabel lblPath;
    private JTextField txtPath;
    private JButton btnPath;

    private JLabel lblName;
    private JTextField txtName;

    private JLabel lblFileType;
    private JComboBox cmbFileType;

    private JLabel lblSize;
    private JComboBox cmbSize;

    private JButton btnSearch;

    private JButton btnClearList;
    /**
     * Pane for default search.
     */
    public NewSearchViewer() {
        pnlMain = new JPanel(new GridBagLayout());
        lblPath = new JLabel();
        txtPath = new JTextField();
        btnPath = new JButton();
        lblName = new JLabel();
        txtName = new JTextField();
        lblSize = new JLabel();
        cmbSize = new JComboBox(FileSizeEnum.values());
        lblFileType = new JLabel();
        cmbFileType = new JComboBox(FileTypeEnum.values());
        btnSearch = new JButton();
        btnClearList = new JButton();
        initializeControls();
        initializeFrame();


    }

    /**
     * Initialize elements.
     */
    private void initializeControls() {
        pnlMain.setBackground(Color.orange);
        lblPath.setText("Path :");
        txtPath.setColumns(20);
        txtPath.setEditable(false);

        btnPath.setIcon(new ImageIcon("img\\folder.png"));
        btnPath.setPreferredSize(new Dimension(25, 25));
        btnPath.setBackground(Color.orange);
        btnPath.setBorder(null);


        btnPath.addActionListener(this);
        lblName.setText("File Name :");
        txtName.setColumns(20);
        lblSize.setText("Size :");
        lblFileType.setText("File Type :");

        btnSearch.setIcon(new ImageIcon("img\\searchBlack.png"));
        btnSearch.setBackground(Color.orange);
        btnSearch.setBorder(null);

        btnClearList.setIcon(new ImageIcon("img\\deleteBlack.png"));
        btnClearList.setBackground(Color.orange);
        btnClearList.setBorder(null);

    }

    /**
     * initialize panes.
     */
    private void initializeFrame() {
        GridBagConstraints gridPane = new GridBagConstraints();
        gridPane.insets = new Insets(2, 2, 2, 2);

        gridPane.gridx = 0;
        gridPane.gridy = 0;
        gridPane.anchor = GridBagConstraints.LINE_END;
        pnlMain.add(lblPath, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 0;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        pnlMain.add(txtPath, gridPane);

        gridPane.gridx = 2;
        gridPane.gridy = 0;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        pnlMain.add(btnPath, gridPane);

        gridPane.gridx = 3;
        gridPane.gridy = 0;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        pnlMain.add(btnSearch, gridPane);

        gridPane.gridx = 4;
        gridPane.gridy = 0;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        pnlMain.add(btnClearList, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 2;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.4;
        gridPane.weighty = 0;
        pnlMain.add(lblName, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 2;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlMain.add(txtName, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 5;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.4;
        gridPane.weighty = 0;
        pnlMain.add(lblFileType, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 5;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlMain.add(cmbFileType, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 3;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.4;
        gridPane.weighty = 0;
        pnlMain.add(lblSize, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 3;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlMain.add(cmbSize, gridPane);

        


/*        gridPane.gridx = 0;
        gridPane.gridy = 7;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlMain.add(btnSearch, gridPane);*/

        this.add(pnlMain);

/*        gridPane.gridx = 0;
        gridPane.gridy = 6;
        gridPane.fill = GridBagConstraints.VERTICAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlMain.add(btnClearList, gridPane);*/
        this.setResizable(false);
    }

    /**
     * @param e choose a path.
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == btnPath) {

            JFileChooser f = new JFileChooser();
            f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            f.showSaveDialog(null);
            txtPath.setText(String.valueOf(f.getCurrentDirectory()));
        }
    }

    /**
     * @return txtpath path.
     */
    public JTextField getTxtPath() {
        return txtPath;
    }

    /**
     * @return txtname name of file.
     */
    public JTextField getTxtName() {
        return txtName;
    }

    /**
     * @return cmbSize size of file.
     */
    public JComboBox getCmbSize() {
        return cmbSize;
    }

    /**
     * @return cmdfiletype type of file.
     */
    public JComboBox getCmbFileType() {
        return cmbFileType;
    }

    /**
     *
     * @return button search.
     */
    public JButton getBtnSearch() {
        return btnSearch;
    }

    /**
     *
     * @return button clear List.
     */
    public JButton getBtnClearList() {
        return btnClearList;
    }
}
