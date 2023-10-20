package com.example.bpart2assignment;

public class PixabayImage {
    private int id;
    private String previewURL;
    private String largeImageURL;

    public int getId() {
        return id;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }
}