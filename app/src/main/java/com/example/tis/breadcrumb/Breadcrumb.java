package com.example.tis.breadcrumb;

import java.io.Serializable;

public class Breadcrumb implements Serializable {
    private String title;
    private String className;
    private String document;
    private String model;
    private String category;
    private String subCategory;
    private String nextSubCategory;

    public Breadcrumb(){}

    public Breadcrumb(String title, String className, String document, String model) {
        this.title = title;
        this.className = className;
        this.document = document;
        this.model = model;
    }

    public Breadcrumb(String title, String className, String document, String model, String category, String subCategory, String nextSubCategory) {
        this.title = title;
        this.className = className;
        this.document = document;
        this.model = model;
        this.category = category;
        this.subCategory = subCategory;
        this.nextSubCategory = nextSubCategory;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public void setNextSubCategory(String nextSubCategory) {
        this.nextSubCategory = nextSubCategory;
    }

    public Breadcrumb(String title, String className, String document, String model, String category, String subCategory) {
        this.title = title;
        this.className = className;
        this.document = document;
        this.model = model;
        this.category = category;
        this.subCategory = subCategory;
    }

    public Breadcrumb(String title, String className, String document, String model, String category) {
        this.title = title;
        this.className = className;
        this.document = document;
        this.model = model;
        this.category = category;
    }

    public Breadcrumb(String title, String className, String document) {
        this.title = title;
        this.className = className;
        this.document = document;
    }

    public Breadcrumb(String title, String className) {
        this.title = title;
        this.className = className;
    }

    public String getTitle() {
        return title;
    }

    public String getClassName() {
        return className;
    }

    public String getDocument() {
        return document;
    }

    public String getModel() {
        return model;
    }

    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public String getNextSubCategory() {
        return nextSubCategory;
    }
}
