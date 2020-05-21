package com.example.tis.list;

import com.example.tis.breadcrumb.Breadcrumb;

import java.util.ArrayList;

public class BreadcrumbList {
    private static ArrayList<Breadcrumb> breadcrumbs = new ArrayList<>();

    public static ArrayList<Breadcrumb> getAll() {
        return breadcrumbs;
    }

    public static void addBreadcrumb(Breadcrumb breadcrumb) {
        breadcrumbs.add(breadcrumb);
    }

    public static void deleteBreadcrumb(int id) {
        for (int i = breadcrumbs.size() - 1; i >= id + 1; i--) {
            breadcrumbs.remove(i);
        }
    }

    public static void updateBreadcrumbModel(int id, String model){
        breadcrumbs.get(id).setModel(model);
    }

    public static void updateBreadcrumbCategory(int id, String category){
        breadcrumbs.get(id).setCategory(category);
    }

    public static void updateBreadcrumbSubCategory(int id, String subCategory){
        breadcrumbs.get(id).setSubCategory(subCategory);
    }

    public static Breadcrumb getBreadcrumbById(int id){
        return breadcrumbs.get(id);
    }
}
