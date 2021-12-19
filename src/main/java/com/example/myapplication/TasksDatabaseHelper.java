package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

    public class TasksDatabaseHelper extends SQLiteOpenHelper
    {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "PokemonDb2";
        public static final String TABLE_POKEMON = "Pokemon";
        public static final String TABLE_POKEMON2 = "Pokemon2";
        public static final String TABLE_POKEMON3 = "Pokemon3";
        public static final String TABLE_POKELIST2 = "PokeList2";
        public static final String KEY_ID = "_id";
        public static final String KEY_IDList = "_id";
        public static final String KEY_NAME = "name";
        public static final String KEY_NAMElIST = "nameList";
        public static final String KEY_TYPE = "type";
        public static final String KEY_LEVEL = "level";

        public TasksDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            //3rd argument to be passed is CursorFactory instance
        }

        // Creating Tables
        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_POKEMON_TABLE = "CREATE TABLE " + TABLE_POKEMON + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                    + KEY_TYPE + " TEXT,"
                    + KEY_LEVEL + " TEXT"
                    + ")";
            db.execSQL(CREATE_POKEMON_TABLE);

            String CREATE_POKELIST2_TABLE = "CREATE TABLE " + TABLE_POKELIST2 + "("
                    + KEY_IDList + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAMElIST + " TEXT"
                    + ")";
            db.execSQL(CREATE_POKELIST2_TABLE);

            String CREATE_POKEMON_TABLE2 = "CREATE TABLE " + TABLE_POKEMON2 + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                    + KEY_TYPE + " TEXT,"
                    + KEY_LEVEL + " TEXT"
                    + ")";
            db.execSQL(CREATE_POKEMON_TABLE2);

            String CREATE_POKEMON_TABLE3 = "CREATE TABLE " + TABLE_POKEMON3 + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                    + KEY_TYPE + " TEXT,"
                    + KEY_LEVEL + " TEXT"
                    + ")";
            db.execSQL(CREATE_POKEMON_TABLE3);
        }

        // update database structure
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_POKEMON);

            // Create tables again
            onCreate(db);
        }
    }
