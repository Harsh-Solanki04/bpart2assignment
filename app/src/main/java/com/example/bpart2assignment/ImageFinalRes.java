package com.example.bpart2assignment;

public class ImageFinalRes {
    private int id;
    private String previewURL;
    private String largeImageURL;
    private String downloadURL;

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

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }
}