package com.example.foodsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FridgeActivity extends AppCompatActivity {

    DBHelper db;
    Button add_data;
    EditText add_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        db = new DBHelper(this);

        add_data = findViewById(R.id.add_data);
        add_name = findViewById(R.id.add_name);

        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = add_name.getText().toString();
                if(!name.equals("") && db.insertFood(name)){
                    Toast.makeText(FridgeActivity.this, "Data added", Toast.LENGTH_SHORT).show();
                    add_name.setText("");
                } else{
                    Toast.makeText(FridgeActivity.this, "Data not added", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}