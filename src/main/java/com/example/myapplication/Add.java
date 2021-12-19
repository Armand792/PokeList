package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {
    TasksDatabaseManager db;
    private EditText editName, editType, editLVL;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editName = (EditText) findViewById(R.id.editName);
        editType = (EditText) findViewById(R.id.editType);
        editLVL = (EditText) findViewById(R.id.editLvl);

        Intent intent = getIntent();
        String tablename = intent.getStringExtra("table");

        btnAdd = (Button) findViewById(R.id.btnAdd);
        db = new TasksDatabaseManager(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pokemon newEntry = new Pokemon(editName.getText().toString(), editType.getText().toString(), editLVL.getText().toString());

                if (editName.length() != 0 && editType.length() != 0 && editLVL.length() != 0 ) {
                    AddData(newEntry, tablename);
                    toastMessage("Pokemon succesfully inserted");

                    Intent refresh = new Intent(Add.this, allPokemon.class);
                    startActivity(refresh);//Start the same Activity
                    finish(); //finish Activity.

                } else {
                    toastMessage("You must put something in the all text field!");
                }

            }
        });
    }



    public void AddData(Pokemon pokemon, String tablename) {

        db.open();
        boolean insertData = db.addPokemon(pokemon, tablename);
        db.close();

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }
}

