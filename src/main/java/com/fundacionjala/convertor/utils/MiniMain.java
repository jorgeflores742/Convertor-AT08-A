package com.fundacionjala.convertor.utils;

import java.io.IOException;
import java.util.logging.Logger;
/**
 *The MiniMain class
 **/
public class MiniMain {

    static SingleLogger sL = SingleLogger.getInstanceLogger();

    /**
     *Class Main.
     * @param args is of type String.
     **/
    public static void main(String[] args) {
        sL.setLogger(MiniMain.class.getName());
        try {
            int a=4,b=0;
            int c=a/b;
            sL.register(null,"FINE","Successful multiplication");
        }catch (Exception ex){
            sL.register(ex,"SEVERE","Multiplication failed");
        }
    }
}
