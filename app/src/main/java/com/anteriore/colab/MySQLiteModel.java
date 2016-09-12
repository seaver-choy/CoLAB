package com.anteriore.colab;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MySQLiteModel extends SQLiteOpenHelper{

    public MySQLiteModel(Context context) {
        super(context, Interest.TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Interest.TABLE_NAME + "("
                + Interest.COLUMN_ID + " TEXT PRIMARY KEY,"
                + Interest.COLUMN_NAME + " TEXT,"
                + Interest.COLUMN_IMAGE + " INTEGER" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Interest> getListInterests() {
        ArrayList<Interest> localInterests = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Interest.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                localInterests.add(new Interest(cursor.getString(0), cursor.getString(1), cursor.getInt(2)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return localInterests;
    }
}
