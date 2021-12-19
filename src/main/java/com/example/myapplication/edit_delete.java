package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class edit_delete extends AppCompatActivity {

    private long itemid;
    private String tablename;


    private Button btnEdit,btnDelete;
    private EditText editName, editType, editLvl;


    TasksDatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete);

        editName = findViewById(R.id.editName1);
        editType = findViewById(R.id.editType1);
        editLvl = findViewById(R.id.editLvl1);

        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        db = new TasksDatabaseManager(this);

        Intent intent = getIntent();

        itemid = intent.getLongExtra("id", -1);
        tablename = intent.getStringExtra("table");

        db.open();

        Pokemon pokemon = db.getPokemon(itemid, tablename);

        editName.setText(pokemon.get_name());
        editType.setText(pokemon.get_type());
        editLvl.setText(pokemon.get_level());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.open();

                Pokemon pokemon = db.getPokemon(itemid, tablename);

                pokemon.set_name(editName.getText().toString());
                pokemon.set_type(editType.getText().toString());
                pokemon.set_level(editLvl.getText().toString());

                if (editName.length() != 0 && editType.length() != 0 && editLvl.length() != 0 ) {
                    db.updatePokemon(itemid, pokemon.get_name(), pokemon.get_type(), pokemon.get_level(), tablename);
                    toastMessage("Pokemon succesfully updated");

                    Intent refresh = new Intent(edit_delete.this, allPokemon.class);
                    startActivity(refresh);//Start the same Activity
                    finish(); //finish Activity.

                } else {
                    toastMessage("You must put something in the all text field!");
                }

                db.close();

            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                db.open();
                Pokemon pokemon = db.getPokemon(itemid, tablename);

                pokemon.set_name(editName.getText().toString());
                pokemon.set_type(editType.getText().toString());
                pokemon.set_level(editLvl.getText().toString());

                db.deletePokemon(itemid, tablename);
                toastMessage("Pokemon Successfully Deleted!");
                db.close();

                Intent refresh = new Intent(edit_delete.this, allPokemon.class);
                startActivity(refresh);//Start the same Activity
                finish(); //finish Activity.

            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}