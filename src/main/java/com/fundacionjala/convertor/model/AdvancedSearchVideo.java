package com.fundacionjala.convertor.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class AdvancedSearchVideo {
    public String searchByLastModifiedTime(String pathFile) throws IOException {
        Path path = Paths.get(pathFile);
        String attribList = "basic:size,lastModifiedTime";
        Map<String, Object> attribs = Files.readAttributes(path, attribList);
        return (String) attribs.get("lastModifiedTime");
    }
}
