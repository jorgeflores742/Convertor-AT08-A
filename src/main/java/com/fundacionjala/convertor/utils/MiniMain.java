package com.fundacionjala.convertor.utils;

import java.util.logging.Logger;
/**
 *It's just a test
 **/
public class MiniMain {

    static SingleLogger sL = new SingleLogger();

    /**
     *Class Main.
     **/
    public static void main(String[] args) {
        sL.setLogger(Logger.GLOBAL_LOGGER_NAME);
        try {
            int a=4,b=0;
            int c=a/b;
            sL.register(null,"FINE","Successful multiplication");//
        }catch (Exception ex){
            System.out.println("the log file is in the src folder with the name of SingleLogger.log");
            sL.register(ex,"SEVERE","Multiplication failed");
        }
    }
}
