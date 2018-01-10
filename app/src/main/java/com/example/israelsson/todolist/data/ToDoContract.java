package com.example.israelsson.todolist.data;

import android.provider.BaseColumns;

/**
 * Created by israe on 2018-01-05.
 */

public class ToDoContract {

    public static final class TodoEntry implements BaseColumns {

        // Static final members for the table name and each of the database columns
        public static final String TABLE_NAME = "todo";
        public static final String COLUMN_NOTE = "note";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
