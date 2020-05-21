package com.example.tis.file;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HtmlFile {

    public static String read(String filepath, Context context) throws IOException {
        File file = new File(filepath);

        if(!file.exists()){
            return "";
        }

        InputStream is = new FileInputStream(file);
        //InputStream is = context.getAssets().open(filepath);
        int size = 0;
        size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer);
    }

}
