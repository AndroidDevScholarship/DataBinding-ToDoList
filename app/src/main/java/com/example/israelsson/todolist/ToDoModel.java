package com.example.israelsson.todolist;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by israe on 2018-01-07.
 */

public class ToDoModel extends BaseObservable {
    @Bindable
    private String todoString;
    @Bindable
    private long itemId;

    public ToDoModel(String todoString, long itemId) {
        setTodoString(todoString);
        setItemId(itemId);
    }

    public String getTodoString(){
        return todoString;
    }

    public long itemId() {
        return itemId;
    }

    public void setTodoString(String todoString) {
        this.todoString = todoString;
        notifyPropertyChanged(com.example.israelsson.todolist.BR.todoString);
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
