/**
 * @(#)SingleLogger.java Copyright (c) 2018 Fundacion Jala. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * <p>
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 * <p>
 * Please contact Fundacion Jala, 2643 Av Melchor Perez de Olguin, Colquiri
 * Sud, Cochabamba, Bolivia. www.fundacion-jala.org if you need additional
 * information or have any questions.
 */


package com.fundacionjala.convertor.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

/**
 * Class SingleLogger.
 *
 * @author Jorge Flores
 * @version 1.0
 */
public class SingleLogger {

    private static SingleLogger ourInstance = null;


    private static Logger LOGGER = null;

    static {
        InputStream stream = SingleLogger.class.getClassLoader().
                getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method to get an Instance.
     *
     * @return an instance of Abstract Logger class.
     */
    public static SingleLogger getInstanceLogger() {
        createInstance();
        return ourInstance;
    }

    /**
     * Set logger to trace a specific class.
     *
     * @param className type String.
     */
    public void setLogger(final String className) {
        LOGGER = Logger.getLogger(className);
    }

    private synchronized static void createInstance() {
        if (ourInstance == null) {
            ourInstance = new SingleLogger();
        }
    }

    /**
     * Private constructor in order to implement Singleton Pattern.
     */
    public SingleLogger() {
        String path = SingleLogger.class.getClassLoader()
                .getResource("logging.properties")
                .getFile();
        System.setProperty("java.util.logging.config.file", path);
    }

    /**
     *Method register in file SingleLogger.log .
     * @param logLevels is type string.
     * @param ex container.
     * @param msg is type String.
     */
    public static void register(Exception ex, String logLevels, String msg){
        try {
            switch (logLevels) {
                case "INFO":
                    LOGGER.info(msg);
                    break;
                case "SEVERE":
                    LOGGER.log(Level.SEVERE, msg, ex);
                    break;
                case "WARNING":
                    LOGGER.log(Level.WARNING, msg, ex);
                    break;
                case "FINE":
                    LOGGER.fine(msg);
                    break;
            }
        } catch (SecurityException ex1) {
            LOGGER.log(Level.SEVERE, null, ex1);
        }
    }
}
