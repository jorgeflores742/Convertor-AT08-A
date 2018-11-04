package com.fundacionjala.convertor.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.Map;

/**
 * Module Controller SearchController.
 *
 * @author Wilson Lopez
 * @version 1.0.
 */

public class AdvancedSearchVideo {

    /**
     *
     * @param pathFile
     * @return
     * @throws IOException
     */
    public String searchByLastModifiedTime(String pathFile) throws IOException {
        Path path = Paths.get(pathFile);
        String attribList = "basic:size,lastModifiedTime";
        Map<String, Object> attribs = Files.readAttributes(path, attribList);
        return (String) attribs.get("lastModifiedTime");
    }

    /**
     *
     * @param creationTimeFile
     * @return
     * @throws IOException
     */
    public String creationFileTime(String creationTimeFile) throws IOException {
        Path  path = Paths.get(creationTimeFile);
        BasicFileAttributeView bfv = Files.getFileAttributeView(path,  BasicFileAttributeView.class);
        BasicFileAttributes bfa  = bfv.readAttributes();

        String creationT = String.valueOf(bfa.creationTime());
        return creationT;
    }

    /**
     *
     * @param pathFile
     * @return
     * @throws IOException
     */
    public String fileOwnerAttributeView(String pathFile) throws IOException {
        Path path = Paths.get(pathFile);
        FileOwnerAttributeView foav = Files.getFileAttributeView(path, FileOwnerAttributeView.class);

        UserPrincipal owner = foav.getOwner();
        String ownerName = String.valueOf(owner.getName());
        return ownerName;
    }

}
