package com.example.foodsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleExpandableListAdapter;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DBNAME = "Login.db";
    Context context;

    public DBHelper(@Nullable Context context) {
        super(context, "Login.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData (String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if(result == -1) return true;
        else
            return false;
    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?",new String [] {username});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String [] {username, password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    /*public SimpleCursorAdapter populateListViewFromDB(){
        String columns[]={DBHelper.KEY_ROWID,DBHelper.KEY_NAME, DBHelper.KEY_EMAIL};
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.query(DBHelper.TABLE_NAME,columns, null,null,null,null,null);
        String[] fromFieldNames = new String[]{
                DBHelper.KEY_ROWID,DBHelper.KEY_NAME, DBHelper.KEY_EMAIL
        };

        int [] toViewIDs = new int [] {R.id.item_id, R.id.item_name, R.id.item_email};
        SimpleCursorAdapter fridgeAdapter = new SimpleCursorAdapter(
            context, R.layout.single_item, cursor, fromFieldNames, toViewIDs
        );
        return fridgeAdapter;
    };*/
}
