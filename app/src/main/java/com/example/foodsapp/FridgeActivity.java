package com.example.foodsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class FridgeActivity extends AppCompatActivity {

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);
        DB = new DBHelper(this);


        ListView LVFridge = findViewById(R.id.LVFridge);
        SimpleCursorAdapter simpleCursorAdapter = DB.populateListViewFromDB();
        LVFridge.setAdapter(simpleCursorAdapter);
        LVFridge.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                String name = cursor.getString(1);// 1= column index
                Toast.makeText(FridgeActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });
    }}
