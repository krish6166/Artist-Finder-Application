package com.examples.application.artistfinder;

public class ArtistData {

    private String artistName;
    private Integer artImage;


    public ArtistData(String artistName, Integer artImage) {
        this.artistName = artistName;
        this.artImage = artImage;
    }

    public String getArtistName() {
        return artistName;
    }

    public Integer getArtImage() {
        return artImage;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setArtImage(Integer artImage) {
        this.artImage = artImage;
    }
}
