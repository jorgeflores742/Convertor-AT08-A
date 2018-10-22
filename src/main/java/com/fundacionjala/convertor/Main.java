package com.fundacionjala.convertor;

import com.fundacionjala.convertor.model.FileSearcher;
import com.fundacionjala.convertor.view.MainView;
import com.fundacionjala.convertor.view.SearchResult;
import com.fundacionjala.convertor.view.SearchView;

public class Main {
    public static void main(String[] args) {
        FileSearcher fs = new FileSearcher();
        System.out.println(fs.searchAll("C:\\Users\\wiltr\\Documents\\FIleTest"));

        MainView mv = new MainView();
        mv.setVisible(true);

        //SearchView sv = new SearchView();
        //System.out.println(sv.getTxtPath());


    }
}
