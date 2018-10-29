package com.fundacionjala.convertor.model;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class FileSearcherTest {

    FileSearcher search = new FileSearcher();
    public static final String PATH = "E:\\UnitTest";
    public static final String PATH2 = "E:\\UnitTest2";

    /**
     *
     */
    @Test
    public void testSearchByNamePass() {
        String nameFile = "Charada";
        String files = "[E:\\UnitTest\\Charada.avi]";
        assertEquals(files, search.searchByName(nameFile, PATH).toString());
    }

    /**
     *
     */
    @Test
    public void testSearchByExt() {
        String ext = "mkv";
        String files = "[E:\\UnitTest\\Clair de lune Debussy (guitare).mkv]";
        assertEquals(files, search.searchByExt(ext, PATH).toString());
    }

    /**
     *
     */
    @Test
    public void testHasExtention() {
        File file = new File("E:\\UnitTest\\ch1.mp4");
        String ext = "mp4";
        assertTrue("Pass", search.hasExtention(file, ext));
    }

    /**
     *
     */
    @Test
    public void testSearchBySize() {
        String size = "very big";
        String files = "[E:\\UnitTest\\Charada.avi, E:\\UnitTest\\OpenShot-v2.4.1-x86_64.exe, E:\\UnitTest\\TD1a.mp4]";
        assertEquals(files, search.searchBySize(size, PATH).toString());
    }

    /**
     *
     */
    @Test
    public void testIsSize() {
        File file = new File("E:\\UnitTest\\fourier.pdf");
        String size = "medium";
        assertTrue("Passed", search.isSize(file, size));
    }

    /**
     *
     */
    @Test
    public void testSearchFiles() {
        String files = "[E:\\UnitTest2\\Charada.avi, E:\\UnitTest2\\TD1a.mp4]";
        assertEquals(files, search.searchFiles(PATH2, null, null, "very big").toString());
    }

    /**
     *
     */
    @Test
    public void testDfinitiveList() {
        String files = "[E:\\UnitTest2\\Charada.avi]";
        assertEquals(files, search.searchFiles(PATH2, "Charada", "avi", null).toString());
    }

    /**
     *
     */
    @Test
    public void testSearchAll() {
        String files = "[E:\\UnitTest2\\Charada.avi, E:\\UnitTest2\\fourier.pdf, E:\\UnitTest2\\TD1a.mp4]";
        assertEquals(files, search.searchAll(PATH2).toString());
    }
}
