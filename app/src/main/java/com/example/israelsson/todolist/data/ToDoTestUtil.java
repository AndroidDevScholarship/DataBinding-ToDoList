package com.example.israelsson.todolist.data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by israe on 2018-01-05.
 */

    public class ToDoTestUtil {

        public static void insertFakeData(SQLiteDatabase db){
            if(db == null){
                return;
            }
            //create a list of fake guests
            List<ContentValues> list = new ArrayList<ContentValues>();

            ContentValues cv = new ContentValues();
            cv.put(ToDoContract.TodoEntry.COLUMN_NOTE, "John");
            list.add(cv);

            cv = new ContentValues();
            cv.put(ToDoContract.TodoEntry.COLUMN_NOTE, "Robert");
            list.add(cv);

            cv = new ContentValues();
            cv.put(ToDoContract.TodoEntry.COLUMN_NOTE, "Henrik");
            list.add(cv);

            cv = new ContentValues();
            cv.put(ToDoContract.TodoEntry.COLUMN_NOTE, "Susanne");
            list.add(cv);


            //insert all users in one transaction
            try
            {
                db.beginTransaction();
                //clear the table first
                db.delete (ToDoContract.TodoEntry.TABLE_NAME,null,null);
                //go through the list and add one by one
                for(ContentValues c:list){
                    db.insert(ToDoContract.TodoEntry.TABLE_NAME, null, c);
                }
                db.setTransactionSuccessful();
            }
            catch (SQLException e) {
                //too bad :(
            }
            finally
            {
                db.endTransaction();
            }

        }
    }
