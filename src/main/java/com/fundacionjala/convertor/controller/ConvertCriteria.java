package com.fundacionjala.convertor.controller;

/**
 * Module View MainView.
 *
 * @author Melvi Caballero.
 * @version 1.0.
 */
public class ConvertCriteria {
    private String fileName;
    private String formatFrom;
    private String formatTo;

    /**
     * @return file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return format from convert
     */
    public String getFormatFrom() {
        return formatFrom;
    }

    /**
     * @param formatFrom format from convert
     */
    public void setFormatFrom(String formatFrom) {
        this.formatFrom = formatFrom;
    }

    /**
     * @return format to convert
     */
    public String getFormatTo() {
        return formatTo;
    }

    /**
     * @param formatTo format to convert
     */
    public void setFormatTo(String formatTo) {
        this.formatTo = formatTo;
    }
}
