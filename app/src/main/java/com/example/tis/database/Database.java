package com.example.tis.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.tis.helper.DatabaseHelper;

import java.io.IOException;

public class Database {

    private static DatabaseHelper mDBHelper;
    private static SQLiteDatabase mDb;

    public static SQLiteDatabase getInstance(Context context){
        mDBHelper = new DatabaseHelper(context);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        return mDb;
    }

}
