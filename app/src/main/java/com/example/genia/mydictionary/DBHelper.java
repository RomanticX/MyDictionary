package com.example.genia.mydictionary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by YAUHENI on 29.07.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DICTIONARY";
    public static final String TABLE_NAME = "WORDS";

    public static final String KEY_ID = "id";
    public static final String LANGUAGE = "lang";
    public static final String WORD = "word";
    public static final String TRANSLATE = "translate";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DBHelper", "---Create a new database---");
        //create new db
        db.execSQL("create table " + TABLE_NAME + "(" + KEY_ID +" integer primary key autoincrement," + LANGUAGE + " text,"
                + WORD +" text," + TRANSLATE +" text" + ")");

        Log.i("DBHelper", "create table " + TABLE_NAME + "(" + KEY_ID +" integer primary key autoincrement," + LANGUAGE + " text,"
                + WORD +" text," + TRANSLATE +" text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

}
