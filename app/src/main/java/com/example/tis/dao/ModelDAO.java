package com.example.tis.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tis.database.Database;
import com.example.tis.entity.Model;

import java.util.ArrayList;

public class ModelDAO extends DAO <Model> {

    private SQLiteDatabase mDb;

    public ModelDAO(Context context) {
        mDb = Database.getInstance(context);
    }

    @Override
    public ArrayList<Model> getAll() {
        ArrayList<Model> models = new ArrayList<>();

        Cursor cursor = mDb.rawQuery("SELECT * FROM models  WHERE id != 'E0'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            models.add(new Model(cursor.getString(0),
                                 cursor.getString(1),
                                 cursor.getString(2)));
            cursor.moveToNext();
        }
        cursor.close();

        return models;
    }

    /*
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    SQLiteStatement stmt = db.compileStatement("SELECT * FROM Country WHERE code = ?");
    stmt.bindString(1, "US");
    stmt.execute();
     */
}
