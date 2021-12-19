package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class PokeList extends ListActivity {

    SimpleCursorAdapter taskAdapter;
    String names []     = new String[] {"nameList"};
    int[] rowColumns    = new int [] {R.id.name1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_list);

        TasksDatabaseManager db = new TasksDatabaseManager(this);

        db.open();

        Log.d("Insert: ", "Inserting ..");

        if(db.getPokeStopCount() == 0) {
            db.addPokeStop(new PokeStop("Oconell St"));
            db.addPokeStop(new PokeStop("Happeny Bridge"));
            db.addPokeStop(new PokeStop("Croke Park"));
        }
        else{
            Log.i("Message", "There are " + db.getPokeStopCount() + " on the database");
        }

        Cursor results = db.getAllPokeStop();
        taskAdapter = new SimpleCursorAdapter(this, R.layout.row1, results, names, rowColumns, 0);
        setListAdapter(taskAdapter);
        db.close();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(PokeList.this, allPokemon.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

}