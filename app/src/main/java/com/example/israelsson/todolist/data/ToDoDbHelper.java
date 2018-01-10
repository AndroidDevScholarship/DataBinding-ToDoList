package com.example.israelsson.todolist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by israe on 2018-01-05.
 */

public class ToDoDbHelper extends SQLiteOpenHelper {


    static final String DATABASE_NAME = "todo.db";
    static final int DATABASE_VERSION = 1;


    public ToDoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //String query called SQL_CREATE_TODO_TABLE that will create the table
        final String SQL_CREATE_TODO_TABLE = "CREATE TABLE " +
                ToDoContract.TodoEntry.TABLE_NAME + " (" +
                ToDoContract.TodoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ToDoContract.TodoEntry.COLUMN_NOTE + " TEXT NOT NULL, " +
                ToDoContract.TodoEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        //Execute the query by calling execSQL on sqLiteDatabase and pass the string query SQL_CREATE_TODO_TABLE
        db.execSQL(SQL_CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        //Execute a drop table query, and then call onCreate to re-create it
        db.execSQL("DROP TABLE IF EXISTS " + ToDoContract.TodoEntry.TABLE_NAME);
        onCreate(db);
    }
}
