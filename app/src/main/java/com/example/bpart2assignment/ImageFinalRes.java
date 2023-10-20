package com.example.bpart2assignment;

public class ImageFinalRes {
    private int id;
    private String previewURL;
    private String largeImageURL;

    public ImageFinalRes(int id, String previewURL) {
        this.id = id;
        this.previewURL = previewURL;
    }

    public int getId() {
        return id;
    }

    public String getPreviewURL() {
        return previewURL;
    }
    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }
}