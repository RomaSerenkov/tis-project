package com.example.tis.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tis.database.Database;
import com.example.tis.entity.Doc;

import java.util.ArrayList;

public class DocDao extends DAO<Doc> {
    private SQLiteDatabase mDb;

    public DocDao(Context context){
        mDb = Database.getInstance(context);
    }

    @Override
    public ArrayList<Doc> getAll() {
        ArrayList<Doc> docs = new ArrayList<>();

        Cursor cursor = mDb.rawQuery("SELECT * FROM doc", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            docs.add(new Doc(   cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getInt(4),
                                cursor.getString(5),
                                cursor.getString(6)));
            cursor.moveToNext();
        }
        cursor.close();

        return docs;
    }

    public ArrayList<Doc> findByDoc(String doc){
        ArrayList<Doc> docs = new ArrayList<>();

        Cursor cursor = mDb.rawQuery("SELECT * FROM doc WHERE doc.doc = ?", new String[]{doc});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            docs.add(new Doc(   cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getString(6)));
            cursor.moveToNext();
        }
        cursor.close();

        return docs;
    }
}
