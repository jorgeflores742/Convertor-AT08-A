package com.fundacionjala.convertor.view;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Module View Searchview.
 *
 * @author Melvi Caballero
 * @version 1.0
 */
public class SearchViewer extends JDialog implements ActionListener {

    private ArrayList<String> selectedResult = new ArrayList<String>();

    /**
     * @return selected result
     */
    public ArrayList getSelectedResult() {
        return selectedResult;
    }

    /**
     * @param selectedResult selected result
     */
    public void setSelectedResult(final ArrayList selectedResult) {
        this.selectedResult = selectedResult;
    }

    private ArrayList<SearchResult> searchResultList = new ArrayList<SearchResult>();

    /**
     * @return searchresultlist
     */
    public ArrayList getSearchResultList() {
        return searchResultList;
    }

    /**
     * @param searchResultList search result
     */
    public void setSearchResultList(final ArrayList searchResultList) {
        this.searchResultList = searchResultList;
    }

    private JPanel pnlMain;
    private JLabel lblPath;
    private JTextField txtPath;

    private JButton btnPath;
    private JLabel lblName;
    private JTextField txtName;

    /**
     * @return txtname
     */
    public JTextField getTxtName() {
        return txtName;
    }

    private JLabel lblFileFormat;
    private JComboBox cmbFileFormat;

    /**
     * @return cmbfileformat
     */
    public JComboBox getCmbFileFormat() {
        return cmbFileFormat;
    }

    private JLabel lblFileType;
    private JComboBox cmbFileType;

    /**
     * @return cmdfiletype
     */
    public JComboBox getCmbFileType() {
        return cmbFileType;
    }

    private JLabel lblSize;
    private JComboBox cmbSize;

    /**
     * @return cmbSize
     */
    public JComboBox getCmbSize() {
        return cmbSize;
    }

    private JButton btnSearch;
    private boolean isOk = false;

    /**
     * @return is Ok
     */
    public boolean getIsOk() {
        return isOk;
    }

    public SearchViewer() {
        pnlMain = new JPanel(new GridBagLayout());
        lblPath = new JLabel();
        txtPath = new JTextField();
        btnPath = new JButton();
        lblName = new JLabel();
        txtName = new JTextField();
        lblFileType = new JLabel();
        cmbFileType = new JComboBox(FileTypeEnum.values());
        lblFileFormat = new JLabel();
        cmbFileFormat = new JComboBox();
        lblSize = new JLabel();
        cmbSize = new JComboBox(FileSizeEnum.values());
        btnSearch = new JButton();
        initializeControls();
        initializeFrame();
    }

    /**
     *
     */
    private void initializeControls() {
        lblPath.setText("Path :");
        txtPath.setColumns(20);
        txtPath.setEditable(false);
        btnPath.setText("Choose Path");
        btnPath.addActionListener(this);
        lblName.setText("File Name :");
        txtName.setColumns(20);
        lblFileFormat.setText("FileFormat :");
        lblFileType.setText("File Type :");
        lblSize.setText("Size :");
        btnSearch.setText("Search");
    }

    /**
     *
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
        gridPane.gridy = 3;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.4;
        gridPane.weighty = 0;
        pnlMain.add(lblFileType, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 3;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlMain.add(cmbFileType, gridPane);

        gridPane.gridx = 0;
        gridPane.gridy = 4;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.4;
        gridPane.weighty = 0;
        pnlMain.add(lblFileFormat, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 4;
        gridPane.fill = GridBagConstraints.HORIZONTAL;
        gridPane.anchor = GridBagConstraints.CENTER;
        gridPane.ipady = 0;
        gridPane.weightx = 0.6;
        gridPane.weighty = 0;
        pnlMain.add(cmbFileFormat, gridPane);


        gridPane.gridx = 0;
        gridPane.gridy = 5;
        gridPane.fill = GridBagConstraints.NONE;
        gridPane.anchor = GridBagConstraints.LINE_END;
        gridPane.ipady = 0;
        gridPane.weightx = 0.4;
        gridPane.weighty = 0;
        pnlMain.add(lblSize, gridPane);

        gridPane.gridx = 1;
        gridPane.gridy = 5;
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

        this.setSize(350, 550);
        this.setResizable(false);
    }

    /**
     * @param e event
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
     * @return txtpath
     */
    public JTextField getTxtPath() {
        return txtPath;
    }
}
