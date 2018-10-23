/**
 *
 * @(#)Main.java Copyright (c) 2018 Fundacion Jala. All rights reserved.
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
 *
 */
package com.fundacionjala.convertor;

import com.fundacionjala.convertor.controller.Controller;

/**
 * @author  Wilson Lopez [wilson.lopez@fundacion-jala.org]
 * @version 1.0
 */

public final class Main {

    /**
     * Main method
     * @param args type array String
     */
    public static void main(String[] args) {
        Controller c = new Controller();
        c.runController();
    }
}
