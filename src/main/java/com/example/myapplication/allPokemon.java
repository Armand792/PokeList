package com.example.myapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;



public class allPokemon extends ListActivity {

    private Button create;
    SimpleCursorAdapter taskAdapter;
    String names []     = new String[] {"name", "type","level"};
    int[] rowColumns    = new int [] {R.id.name, R.id.type, R.id.lvl};
    String tablename = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_pokemon);

        Intent intent = getIntent();
        long itemid = intent.getLongExtra("id", -1);

        initViews();

        TasksDatabaseManager db = new TasksDatabaseManager(this);

        switch ((int) itemid){
            case 1:
                tablename = "Pokemon";
                break;
            case 2:
                tablename = "Pokemon2";
                break;
            case 3:
                tablename = "Pokemon3";
        }

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(allPokemon.this, Add.class);
                intent.putExtra("table", tablename);
                startActivity(intent);
            }
        });

        db.open();

        // Add some sample Pokemon
        Log.d("Insert: ", "Inserting ..");
        if(db.getPokemonCount() == 0) {
            db.addPokemon(new Pokemon("Bulbasaur", "Grass","12"), "Pokemon");
            db.addPokemon(new Pokemon("Charmander", "Fire","15"), "Pokemon");
            db.addPokemon(new Pokemon("Squirtle", "Water","8"), "Pokemon");
        }
        else
            Log.i("Message", "There are " + db.getPokemonCount()+ " on the database");

        //Display table based on Item ID

        Cursor results = db.getAllPokemon(tablename);
        taskAdapter = new SimpleCursorAdapter(this, R.layout.row, results, names, rowColumns, 0);
        Log.i("test", "Number of rows returned are " + results.getCount());
        setListAdapter(taskAdapter);

        db.close();

    }


    private void initViews() {
        create = findViewById(R.id.create);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        String name = l.getItemAtPosition(position).toString();

        Cursor data = (Cursor)l.getItemAtPosition(position);

            Intent intent = new Intent(allPokemon.this, edit_delete.class);

            intent.putExtra("id",id);
            intent.putExtra("table", tablename);

            startActivity(intent);

    }
}


