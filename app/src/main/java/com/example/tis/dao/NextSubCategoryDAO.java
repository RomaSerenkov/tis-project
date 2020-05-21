package com.example.tis.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tis.database.Database;
import com.example.tis.entity.NextSubCategory;

import java.util.ArrayList;

public class NextSubCategoryDAO extends DAO <NextSubCategory> {

    private SQLiteDatabase mDb;

    public NextSubCategoryDAO(Context context) {
        mDb = Database.getInstance(context);
    }
    @Override
    public ArrayList<NextSubCategory> getAll() {
        ArrayList<NextSubCategory> categories = new ArrayList<>();

        String sql = "SELECT * FROM lastsubcat";

        Cursor cursor = mDb.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            categories.add(new NextSubCategory(cursor.getString(0), cursor.getString(1)));
            cursor.moveToNext();
        }
        cursor.close();

        return categories;
    }


    public ArrayList<NextSubCategory> findBySubCategory(String subCategory) {
        ArrayList<NextSubCategory> categories = new ArrayList<>();

        String sql = "SELECT id, title\n" +
                "FROM lastsubcat\n" +
                "WHERE lastsubcat.id_subcat = ?";

        Cursor cursor = mDb.rawQuery(sql, new String[]{ subCategory });
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            categories.add(new NextSubCategory(cursor.getString(0), cursor.getString(1)));
            cursor.moveToNext();
        }
        cursor.close();

        return categories;
    }
}