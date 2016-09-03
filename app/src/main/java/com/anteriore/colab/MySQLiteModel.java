package com.anteriore.colab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Seaver on 9/3/2016.
 */
public class MySQLiteModel extends SQLiteOpenHelper {

    public MySQLiteModel(Context context) {
        super(context, Interest.TABLE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
