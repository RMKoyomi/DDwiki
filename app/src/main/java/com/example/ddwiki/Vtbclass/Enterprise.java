package com.example.ddwiki.Vtbclass;

import org.litepal.crud.DataSupport;

public class Enterprise extends DataSupport {
    private String name;
    private int imageId;


    public Enterprise(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
