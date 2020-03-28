package com.example.ddwiki.Vtbclass;

public class History {
    private String name;
    private int imageId;
    private String datetime;

    /*
    public History(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }*/

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
