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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Module View Searchview.
 *
 * @author Melvi Caballero
 * @version 1.0
 */
public class SearchViewer extends JDialog implements ActionListener {

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
    public SearchViewer() {
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
        lblPath.setText("Path :");
        txtPath.setColumns(20);
        txtPath.setEditable(false);
        btnPath.setText("Choose Path");
        btnPath.addActionListener(this);
        lblName.setText("File Name :");
        txtName.setColumns(20);
        lblSize.setText("Size :");
        lblFileType.setText("File Type :");
        btnSearch.setText("Search");
        btnClearList.setText("Clear List");
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

        gridPane.gridx = 1;
        gridPane.gridy = 1;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        pnlMain.add(btnPath, gridPane);

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

        gridPane.gridx = 1;
        gridPane.gridy = 6;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlMain.add(btnSearch, gridPane);

        this.add(pnlMain);

        gridPane.gridx = 0;
        gridPane.gridy = 6;
        gridPane.fill = GridBagConstraints.VERTICAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlMain.add(btnClearList, gridPane);
        this.setResizable(false);
    }

    /**
     * @param e choose a path.
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == btnPath) {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                txtPath.setText(chooser.getSelectedFile().getAbsolutePath());
            }
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
