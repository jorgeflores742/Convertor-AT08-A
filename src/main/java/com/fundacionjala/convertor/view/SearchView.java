package com.fundacionjala.convertor.view;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;
import java.awt.Frame;
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
public class SearchView extends JDialog implements ActionListener {

    /**
     * @param aFrame frame
     */
    public SearchView(final Frame aFrame) {
        super(aFrame, true);
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
        lstSearchResult = new JList(listModel);
        scrlSearchResult = new JScrollPane(lstSearchResult);
        btnOk = new JButton();
        btnCancel = new JButton();
        initializeControls();
        initializeFrame();
    }


    /**
     *
     */
    private void initializeControls() {
        lblPath.setText("Path :");
        txtPath.setColumns(20);
        btnPath.setText("Choose Path");
        btnPath.addActionListener(this);
        lblName.setText("File Name :");
        txtName.setColumns(20);
        lblFileFormat.setText("FileFormat :");
        lblFileType.setText("File Type :");
        lblSize.setText("Size :");
        btnSearch.setText("Search");
        lstSearchResult.setPreferredSize(new Dimension(350, 250));
        scrlSearchResult.setPreferredSize(new Dimension(350, 250));
        btnOk.setText("Ok");
        btnOk.setMinimumSize(new Dimension(100, 25));
        btnOk.addActionListener(this);
        btnCancel.setText("Cancel");
        btnCancel.setMinimumSize(new Dimension(100, 25));
        btnCancel.addActionListener(this);
    }

    /**
     *
     */
    private void initializeFrame() {
        //populate panel
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2, 2, 2, 2);

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        pnlMain.add(lblPath, c);

        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnlMain.add(txtPath, c);

        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnlMain.add(btnPath, c);

        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_END;
        c.ipady = 0;
        c.weightx = 0.4;
        c.weighty = 0;
        pnlMain.add(lblName, c);

        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.ipady = 0;
        c.weightx = 0.6;
        c.weighty = 0;
        pnlMain.add(txtName, c);

        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_END;
        c.ipady = 0;
        c.weightx = 0.4;
        c.weighty = 0;
        pnlMain.add(lblFileType, c);

        c.gridx = 1;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.ipady = 0;
        c.weightx = 0.6;
        c.weighty = 0;
        pnlMain.add(cmbFileType, c);

        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_END;
        c.ipady = 0;
        c.weightx = 0.4;
        c.weighty = 0;
        pnlMain.add(lblFileFormat, c);

        c.gridx = 1;
        c.gridy = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.ipady = 0;
        c.weightx = 0.6;
        c.weighty = 0;
        pnlMain.add(cmbFileFormat, c);


        c.gridx = 0;
        c.gridy = 5;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_END;
        c.ipady = 0;
        c.weightx = 0.4;
        c.weighty = 0;
        pnlMain.add(lblSize, c);

        c.gridx = 1;
        c.gridy = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.ipady = 0;
        c.weightx = 0.6;
        c.weighty = 0;
        pnlMain.add(cmbSize, c);

        c.gridx = 1;
        c.gridy = 6;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.ipady = 0;
        c.weightx = 0.6;
        c.weighty = 0;
        pnlMain.add(btnSearch, c);

        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.ipady = 80;
        c.weightx = 1;
        c.weighty = 1.0;
        pnlMain.add(scrlSearchResult, c);

        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.ipady = 0;
        c.weightx = 0.5;
        c.weighty = 0;
        pnlMain.add(btnOk, c);

        c.gridx = 1;
        c.gridy = 10;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.ipady = 0;
        c.weightx = 0.5;
        c.weighty = 0;
        pnlMain.add(btnCancel, c);

        this.add(pnlMain);

        this.setTitle("Search Files");
        this.setSize(350, 550);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        lstSearchResult.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

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

    /**
     * @return txtpath
     */
    public JTextField getTxtPath() {
        return txtPath;
    }

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
    private JScrollPane scrlSearchResult;
    private DefaultListModel listModel = new DefaultListModel();
    private JList lstSearchResult;
    private JButton btnOk;
    private JButton btnCancel;
    private boolean isOk = false;

    /**
     * @return is Ok
     */
    public boolean getIsOk() {
        return isOk;
    }







    /**
     * @param e event
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
       /* if (e.getSource() == btnSearch) {
            //hardcoded result from controller
            searchResultList.add(new SearchResult("c:\\test\\test.txt", "test.txt"));
            searchResultList.add(new SearchResult("c:\\test2\\test2.txt", "test2.txt"));

            for (SearchResult resu : searchResultList) {
                listModel.addElement(resu.getFullPath());
            }

        } else*/
        if (e.getSource() == btnCancel) {
            this.setVisible(false);
        } else if (e.getSource() == btnOk) {
           /* if (selectedPath ==""){
                JOptionPane.showMessageDialog(this,
                        "Please select a file.",
                        "No File Selected",
                        JOptionPane.WARNING_MESSAGE);
            }else{*/
            Object sel = null;

            int[] selectedIx = this.lstSearchResult.getSelectedIndices();

            for (int i = 0; i < selectedIx.length; i++) {
                sel = listModel.get(selectedIx[i]);
                selectedResult.add(sel.toString());
            }

            isOk = true;
            this.setVisible(false);
            /* }*/

        } else if (e.getSource() == btnPath) {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                txtPath.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        }
    }
}


