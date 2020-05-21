package com.example.tis.entity;

import java.io.Serializable;

public class Category implements Serializable {
    private String id;
    private String title;

    public Category(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
