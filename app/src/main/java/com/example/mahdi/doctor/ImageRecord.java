package com.example.mahdi.doctor;

/**
 * Created by mahdi on 1/31/2015.
 */
public class ImageRecord {
    private String url;
    private String title;

    public ImageRecord(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
