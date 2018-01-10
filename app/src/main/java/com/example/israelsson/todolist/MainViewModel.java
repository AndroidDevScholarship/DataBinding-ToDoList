package com.example.israelsson.todolist;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.ObservableArrayList;
import android.util.Log;

import com.example.israelsson.todolist.data.ToDoContract;
import com.example.israelsson.todolist.data.ToDoDbHelper;

import java.util.ArrayList;

/**
 * Created by israe on 2018-01-07.
 */

public class MainViewModel extends ViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();
    private SQLiteDatabase mDb;
    private ToDoDbHelper dbHelper;


    MainViewModel(Application application) {
        //Create a ToDoDbHelper instance, pass the context from MainActivity
        dbHelper = new ToDoDbHelper(application.getBaseContext());
        //Get a writable database reference using getWritableDatabase and store it in mDb
        mDb = dbHelper.getWritableDatabase();
    }



    //Metod that returns an ArrayList of ToDoModel objects
    public ArrayList<ToDoModel> allNotes() {
        ArrayList<ToDoModel> todoList = new ObservableArrayList<>();

        Cursor allNotesCursor = mDb.query(ToDoContract.TodoEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        //Get all the notes in the database and store them each in a ToDoModel object

        if (allNotesCursor.moveToFirst()) {
            for (int i = 0; i < allNotesCursor.getCount(); i++) {
                allNotesCursor.moveToPosition(i);
                long id = allNotesCursor.getLong(allNotesCursor.getColumnIndex(ToDoContract.TodoEntry._ID));
                todoList.add(0, new ToDoModel(allNotesCursor.getString(1), id));
            }
        }

        allNotesCursor.close();
       // Log.d(TAG, "getAllGuests: " + todoList.get(0).getTodoString());

        return todoList;
    }


    //This function adds a new note into the database
    boolean addNote(String note) {
        Log.d(TAG, "setNote: " + note);

        // Create a ContentValues instance to pass the values onto the insert query
        ContentValues cv = new ContentValues();
        // Call put to insert the note value with the key COLUMN_NOTE
        cv.put(ToDoContract.TodoEntry.COLUMN_NOTE, note);
        // Create a new writable instance of the database
        SQLiteDatabase mDb = dbHelper.getWritableDatabase();
        // Call insert to run an insert query on TABLE_NAME with the ContentValues created
        mDb.insert(ToDoContract.TodoEntry.TABLE_NAME, null, cv);

        return true;
    }

    //This function removes a note from the database and sets a new Adapter with the updated list of notes.
    public boolean removeNote(long id) {
        //Call mDb.delete to pass in the TABLE_NAME and the condition that WaitlistEntry._ID equals id
        mDb.delete(ToDoContract.TodoEntry.TABLE_NAME, ToDoContract.TodoEntry._ID + "=" +
                String.valueOf(id), null);
        MainActivity.mTodoRecyclerView.setAdapter(new ToDoAdapter(allNotes(), this));
        Log.d(TAG, "removeNote: " + "clicked");

        return true;
    }
}
