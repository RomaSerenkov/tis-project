package com.example.tis.entity;

import java.io.Serializable;

public class Model implements Serializable {

    private String id;
    private String series;
    private String image;

    public Model(String id, String series, String image) {
        this.id = id;
        this.series = series;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getSeries() {
        return series;
    }

    public String getImage() {
        return image;
    }
}
