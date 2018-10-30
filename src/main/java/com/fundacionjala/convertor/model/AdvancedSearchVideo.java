package com.fundacionjala.convertor.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.HashMap;
import java.util.Map;


public class AdvancedSearchVideo {
    public String searchByLastModifiedTime(String pathFile) throws IOException {
        Path path = Paths.get(pathFile);
        String attribList = "basic:size,lastModifiedTime";
        Map<String, Object> attribs = Files.readAttributes(path, attribList);
        return (String) attribs.get("lastModifiedTime");
    }

    public String creationFileTime(String creationTimeFile) throws IOException {
        Path  path   = Paths.get(creationTimeFile);
        BasicFileAttributeView bfv = Files.getFileAttributeView(path,  BasicFileAttributeView.class);
        BasicFileAttributes bfa  = bfv.readAttributes();

        String creationT = String.valueOf(bfa.creationTime());
        return creationT;
    }

}
