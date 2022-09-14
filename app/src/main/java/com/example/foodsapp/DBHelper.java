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

    public static final String DB_NAME = "Login.db";
    public static final String DB_TABLE_FOOD = "Foods"; //LV

    Context context;

    //columns
    private static final String ID = "ID";
    private static final String NAME = "NAME";

    private static final String CREATE_TABLE_FOOD = "CREATE TABLE " +DB_TABLE_FOOD +" (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NAME + " TEXT "+ ")";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
        MyDB.execSQL(CREATE_TABLE_FOOD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS users");
        MyDB.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_FOOD);


        onCreate(MyDB); // LV
    }



    //**************************** OTHERS FUNCTIONS *************************************************

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

    public Boolean insertFood(String name){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(NAME,name);

        long result = MyDB.insert(DB_TABLE_FOOD, null, contentValue);

        return result != -1; // if result = -1 data dosent insert

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
