package com.example.tis.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tis.database.Database;
import com.example.tis.entity.Category;

import java.util.ArrayList;

public class CategoryDAO extends DAO <Category> {

    private SQLiteDatabase mDb;

    public CategoryDAO(Context context) {
        mDb = Database.getInstance(context);
    }
    @Override
    public ArrayList<Category> getAll() {
        ArrayList<Category> categories = new ArrayList<>();

        String sql = "SELECT * FROM categories";

        Cursor cursor = mDb.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            categories.add(new Category(cursor.getString(0), cursor.getString(1)));
            cursor.moveToNext();
        }
        cursor.close();

        return categories;
    }


    public ArrayList<Category> findByModelAndDocument(String model, String document) {
        ArrayList<Category> categories = new ArrayList<>();

        String sql = "SELECT categories.id, categories.title\n" +
                "FROM info\n" +
                "INNER JOIN categories ON info.id_cat = categories.id\n" +
                "INNER JOIN documents ON info.id_document = documents.id\n" +
                "INNER JOIN models ON info.id_model = models.id\n" +
                "WHERE info.id_model = ? AND info.id_document = ?";

        Cursor cursor = mDb.rawQuery(sql, new String[]{model, document});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            categories.add(new Category(cursor.getString(0), cursor.getString(1)));
            cursor.moveToNext();
        }
        cursor.close();

        return categories;
    }
}
