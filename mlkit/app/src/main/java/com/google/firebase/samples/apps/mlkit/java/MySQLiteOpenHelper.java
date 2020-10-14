package com.google.firebase.samples.apps.mlkit.java;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class MySQLiteOpenHelper extends SQLiteOpenHelper{
    //Version of the database
    private static Integer Version = 1;

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                              int version) {

        super(context, name, factory, version);
    }

    //context:object
    //name:name of database
    //param:factory
    //version:current version of database, must be increasing integer

    public MySQLiteOpenHelper(Context context,String name,int version)
    {
        this(context,name,null,version);
    }


    public MySQLiteOpenHelper(Context context,String name)
    {
        this(context, name, Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("Create database and table");

        String sql = "create table user(id int primary key,name varchar(200))";

        db.execSQL(sql);



    }

    //use if database is upgraded

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("New Database version:"+newVersion);
    }
}
