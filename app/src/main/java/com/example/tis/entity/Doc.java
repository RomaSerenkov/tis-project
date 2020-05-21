package com.example.tis.entity;

public class Doc {
    private String image;
    private String html;
    private String text;
    private int position;
    private String doc;
    private String title;

    public Doc(String image, String html, String text, int position, String doc, String title) {
        this.image = image;
        this.html = html;
        this.text = text;
        this.position = position;
        this.doc = doc;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
