package com.anteriore.colab;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anteriore.colab.Model.Interest;
import com.anteriore.colab.Model.Like;

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
                + Interest.COLUMN_TYPE + " TEXT,"
                + Interest.COLUMN_IMAGE + " TEXT" + ")");
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
                localInterests.add(new Interest(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return localInterests;
    }

    public ArrayList<Like> getListLikes(){
        ArrayList<Like> localLikes = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Interest.TABLE_NAME + " WHERE " + Interest.COLUMN_TYPE + " = " + Interest.interestTypes.like.toString(), null);

        if (cursor.moveToFirst()) {
            do {
                localLikes.add(new Like(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return localLikes;
    }

    public void addInterest(Interest interest, String interestType)
    {

    }
}
