package com.fundacionjala.convertor.view;

/**
 *
 */
public class SearchResult {
    private String fullPath;
    private String fileName;

    /**
     * @param fullPath fullPath
     * @param fileName fileName
     */
    public SearchResult(final String fullPath, final String fileName) {
        this.fullPath = fullPath;
        this.fileName = fileName;
    }

    /**
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName fileName
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return fullPath
     */
    public String getFullPath() {
        return fullPath;
    }

    /**
     * @param fullPath fullPath
     */
    public void setFullPath(final String fullPath) {
        this.fullPath = fullPath;
    }
}


