package com.fundacionjala.convertor.model;

import java.io.File;

public class Asset {
    private String nameFile;
    private String sizeFile;
    private String creationFile;
    private String typeFile;
    private File file;
    private String path;

    public Asset () {

    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getSizeFile() {
        return sizeFile;
    }

    public void setSizeFile(String sizeFile) {
        this.sizeFile = sizeFile;
    }

    public String getCreationFile() {
        return creationFile;
    }

    public void setCreationFile(String creationFile) {
        this.creationFile = creationFile;
    }

    public String getTypeFile() {
        return typeFile;
    }

    public void setTypeFile(String typeFile) {
        this.typeFile = typeFile;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
