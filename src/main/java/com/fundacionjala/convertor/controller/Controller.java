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
package com.fundacionjala.convertor.controller;

import com.fundacionjala.convertor.model.FileSearcher;
import com.fundacionjala.convertor.view.MainView;

/**
 * Main controller
 * @author wilson lopez
 */
public class Controller {
    /**
     * The model of this controller
     */
    private FileSearcher fSearcher;
    /**
     * the view of this controller
     */
    private MainView mView;

    /**
     * Default Constructor
     */
    public Controller() {
        this.fSearcher = new FileSearcher();
        this.mView = new MainView();
    }

    /**
     * Run main controller
     */
    public void runController() {
        mView.setVisible(true);

    }
}
