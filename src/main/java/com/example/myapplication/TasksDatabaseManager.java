package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.myapplication.TasksDatabaseHelper.KEY_ID;
import static com.example.myapplication.TasksDatabaseHelper.KEY_LEVEL;
import static com.example.myapplication.TasksDatabaseHelper.KEY_NAME;
import static com.example.myapplication.TasksDatabaseHelper.KEY_NAMElIST;
import static com.example.myapplication.TasksDatabaseHelper.KEY_TYPE;
import static com.example.myapplication.TasksDatabaseHelper.TABLE_POKELIST2;
import static com.example.myapplication.TasksDatabaseHelper.TABLE_POKEMON;
import static com.example.myapplication.TasksDatabaseHelper.TABLE_POKEMON2;

public class TasksDatabaseManager
{
    Context context;
    private TasksDatabaseHelper TaskdbHelper;
    private SQLiteDatabase database;


    public TasksDatabaseManager(Context context)
    {
        this.context = context;
    }

    public TasksDatabaseManager open() throws SQLException {
        TaskdbHelper = new TasksDatabaseHelper(context);
        database = TaskdbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        TaskdbHelper.close();
    }

    public void Remove() {
        TaskdbHelper.onUpgrade(database,1,1);
    }

    // add the new pokemon
    boolean addPokemon(Pokemon pokemon, String tablename) {

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, pokemon.get_name()); //
        values.put(KEY_TYPE, pokemon.get_type()); //
        values.put(KEY_LEVEL, pokemon.get_level()); //

        // Inserting Row
        long result = database.insert(tablename, null, values);
        //2nd argument is String containing nullColumnHack

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }


    boolean addPokeStop(PokeStop pokeStop) {

        ContentValues values = new ContentValues();
        values.put(KEY_NAMElIST, pokeStop.getNameList());
        long result = database.insert(TABLE_POKELIST2, null, values);


        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    Pokemon getPokemon(long id, String tablename) {

        Cursor cursor = database.query(tablename, new String[] { KEY_ID,
                        KEY_NAME, KEY_TYPE, KEY_LEVEL }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Pokemon pokemon = new Pokemon(cursor.getString(1), cursor.getString(2),cursor.getString(3));
        // return
        return pokemon;
    }

    // code to get all tasks in a list view
    public Cursor getAllPokemon(String tablename) {
        Cursor List = database.rawQuery("SELECT * FROM " + tablename, null);
        return List;
    }

    public Cursor getAllPokeStop() {
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_POKELIST2;

        Cursor List1 = database.rawQuery(selectQuery, null);

        return List1;
    }

    // code to update a pokemon
    public boolean updatePokemon(long id, String name, String type, String lvl, String tablename) {

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, name);
        values.put(KEY_TYPE, type);
        values.put(KEY_LEVEL, lvl);

        // updating row
        return database.update(tablename, values, KEY_ID + "="+ id, null)>0;


    }

    // Deleting a pokemon
    public void deletePokemon(long id, String tablename) {
        database.delete(tablename, KEY_ID + " =?",
                new String[] { String.valueOf(id) });
        database.close();
    }

    // Getting  Count
    public int getPokemonCount() {
        String countQuery = "SELECT * FROM " + TABLE_POKEMON;
        Cursor cursor = database.rawQuery(countQuery, null);
        int total_rows = cursor.getCount();
        cursor.close();

        // return count
        return total_rows;
    }

    public int getPokeStopCount(){
        String countQuery = "SELECT * FROM " + TABLE_POKELIST2;
        Cursor cursor = database.rawQuery(countQuery, null);
        int total_rows = cursor.getCount();
        cursor.close();

        // return count
        return total_rows;
    }


}
