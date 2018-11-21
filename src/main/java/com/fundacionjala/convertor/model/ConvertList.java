/**
 * @(#)ConvertList.java Copyright (c) 2018 Fundacion Jala. All rights reserved.
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

package com.fundacionjala.convertor.model;

import com.fundacionjala.convertor.utils.SingleLogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class ConvertList.
 *
 * @author Jorge Flores
 * @version 1.0
 */

public class ConvertList {
    private static SingleLogger sL = SingleLogger.getInstanceLogger();
    private List<String> listTxt;

    public String[] convertLis() {
        try {
            sL.register(null, "INFO", "Successful - convertLis - start");
            File file = new File("records.txt");
            // Si el archivo no existe, se crea!
            if (!file.exists()) {
                file.createNewFile();
            }
            listTxt =  Files.readAllLines(Paths.get("records.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            sL.register(e, "INFO", "Successful - convertLis - finished");
        }
        return listTxt.toArray(new String[listTxt.size()]);
    }

    public void writeConvert(String route) {
        sL.register(null, "INFO", "Successful - writeConvert - start");
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            sL.register(null, "INFO", "Successful - FileWriter/BufferedWriter - start");
            String data = route;
            File file = new File("records.txt");
            // Si el archivo no existe, se crea!
            if (!file.exists()) {
                file.createNewFile();
            }
            // flag true, indica adjuntar información al archivo.
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(data+"\n");
        } catch (IOException e) {
            sL.register(e, "SEVERE", "Successful - FileWriter/BufferedWriter - failed");
        } finally {
            try {
                //Cierra instancias de FileWriter y BufferedWriter
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
