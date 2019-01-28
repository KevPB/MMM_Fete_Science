package com.example.kev.ftedelascience;

public class Events {

    public int id;
    public String description;
    public String longDescription;
    public String url;
    public String img;

    public Events(int id, String description, String longDescription, String url, String img) {
        this.id = id;
        this.description = description;
        this.longDescription = longDescription;
        this.url = url;
        this.img = img;
    }
}
