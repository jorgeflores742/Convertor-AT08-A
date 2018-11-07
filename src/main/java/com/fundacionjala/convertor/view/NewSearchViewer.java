package com.fundacionjala.convertor.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NewSearchViewer extends JDialog {
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

    public NewSearchViewer() {
        pnlMain = new JPanel();
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

    private void initializeFrame() {
        lblPath.setText("Path :");
        txtPath.setColumns(20);
        btnPath.setText("...");


    }

    private void initializeControls() {
        pnlMain.add(lblPath);
        pnlMain.add(txtPath);
        pnlMain.add(btnPath);
        this.add(pnlMain);



    }
}
