package com.example.kevinli.mycontactapp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.net.IDN;

import static android.os.Build.ID;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_Name = "Contact2-2-18.db";
    public static final String TABLE_NAME = "Contact2-2018_table";
    public static final String COLUMN_NAME_CONTACT = "contact";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    COLUMN_NAME_CONTACT + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " +TABLE_NAME;
    public static final String COLUMN_PHONE_CONTACT = "Device";
    public static final String COLUMN_ADDRESS_CONTACT = "Location";
    public DatabaseHelper(Context context){

        super(context, DATABASE_Name, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("MyContactApp", "DatabaseHelper: constructed DatabaseHelper");


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.d("MyContactApp", "DatabaseHelper: creating Database");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        Log.d( "MyContactApp", "DatabaseHelper: upgraded database");
        onCreate(db);
    }
    public boolean insertData(String toString, String s, String name){
        Log.d("MyContactApp", "DatabaseHelper: inserting data");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_CONTACT, name);
        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1){
            Log.d("MyContactApp", "DatabaseHelper: Contact insert - FAILED");
            return false;
        }
        else{
            Log.d("MyContactApp", "DatabaseHelper: Contact insert - PASSED");
            return true;
        }



    }
    public Cursor getAllData(){
        Log.d("MyContactApp", "Databasehelper: calling getAllDataMethod");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME, null);
        return res;
    }


}