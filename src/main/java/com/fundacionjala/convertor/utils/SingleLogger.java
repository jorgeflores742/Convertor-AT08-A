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
import java.util.logging.*;

/**
 * Class SingleLogger.
 *
 * @author Jorge Flores
 * @version 1.0
 */
public class SingleLogger {

    private static SingleLogger ourInstance = null;
    private static Logger logRaiz = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Method to get an Instance.
     *
     * @return an instance of Abstract Logger class.
     */
    public static SingleLogger getInstance() {
        createInstance();
        return ourInstance;
    }

    /**
     * Set logger to trace a specific class.
     *
     * @param className type String.
     */
    public void setLogger(final String className) {
        logger = Logger.getLogger(className);
    }

    private synchronized static void createInstance() {
        if (ourInstance == null) {
            ourInstance = new SingleLogger();
        }
    }

    /**
     * Private constructor in order to implement Singleton Pattern.
     */
    protected SingleLogger() {
    }

    /**
     *Method register in file SingleLogger.log .
     * @param logLevels is type string.
     * @param ex container.
     * @param msg is type String.
     */
    public static void register(Exception ex, String logLevels, String msg) {
        try {
            Handler consoleHandler = new ConsoleHandler();
            Handler fileHandler = new FileHandler("./SingleLogger.log", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logRaiz.addHandler(consoleHandler);
            logRaiz.addHandler(fileHandler);
            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);

            switch (logLevels) {
                case "INFO":
                    logger.info(msg);
                    break;
                case "SEVERE":
                    logger.log(Level.SEVERE, msg, ex);
                    break;
                case "WARNING":
                    logger.log(Level.WARNING, msg, ex);
                    break;
                case "FINE":
                    logger.fine(msg);
                    break;
            }
        } catch (IOException | SecurityException ex1) {
            logger.log(Level.SEVERE, null, ex1);
        }
    }
}

