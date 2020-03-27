package com.example.ddwiki.db;

import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;

public class Enterprises extends DataSupport {
    private String name;
    private int imageId;
    //private int id;


    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }

    /*public int getId() {
        return id;
    }*/

    public void setName(String name) {
        this.name = name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    /*public void setId(int id) {
        this.id = id;
    }*/
}
