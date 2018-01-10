package com.example.israelsson.todolist;

import android.support.v7.widget.RecyclerView;

import com.example.israelsson.todolist.databinding.TodoItemBinding;

/**
 * Created by israe on 2018-01-07.
 */

public class CustomViewHolder extends RecyclerView.ViewHolder{
    private TodoItemBinding mViewDataBinding;

    public CustomViewHolder(TodoItemBinding viewDataBinding) {
        super(viewDataBinding.getRoot());

        mViewDataBinding = viewDataBinding;
        mViewDataBinding.executePendingBindings();
    }

    public TodoItemBinding getmViewDataBinding() {
        return mViewDataBinding;
    }
}
