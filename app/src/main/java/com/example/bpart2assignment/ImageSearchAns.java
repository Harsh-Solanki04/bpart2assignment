package com.example.bpart2assignment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageSearchAns {
    @SerializedName("totalHits")
    private int totalHits;

    @SerializedName("hits")
    private List<PixabayImage> hits;
    public void setImageToHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public List<PixabayImage> getImageHits() {
        return hits;
    }

    public void setImageHits(List<PixabayImage> hits) {
        this.hits = hits;
    }

    public int getImageToHits() {
        return totalHits;
    }

}