package com.example.tis.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tis.database.Database;
import com.example.tis.entity.SubCategory;

import java.util.ArrayList;

public class SubCategoryDAO extends DAO <SubCategory> {

    private SQLiteDatabase mDb;

    public SubCategoryDAO(Context context) {
        mDb = Database.getInstance(context);
    }
    @Override
    public ArrayList<SubCategory> getAll() {
        ArrayList<SubCategory> categories = new ArrayList<>();

        String sql = "SELECT * FROM subcat";

        Cursor cursor = mDb.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            categories.add(new SubCategory(cursor.getString(0), cursor.getString(1)));
            cursor.moveToNext();
        }
        cursor.close();

        return categories;
    }


    public ArrayList<SubCategory> findByCategory(String category) {
        ArrayList<SubCategory> categories = new ArrayList<>();

        String sql = "SELECT id, title\n" +
                "FROM subcat\n" +
                "WHERE subcat.id_cat = ?";

        Cursor cursor = mDb.rawQuery(sql, new String[]{ category });
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            categories.add(new SubCategory(cursor.getString(0), cursor.getString(1)));
            cursor.moveToNext();
        }
        cursor.close();

        return categories;
    }
}
