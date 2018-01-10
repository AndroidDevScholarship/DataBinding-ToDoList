package com.example.israelsson.todolist;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.israelsson.todolist.databinding.TodoItemBinding;

import java.util.ArrayList;

/**
 * Created by israe on 2018-01-07.
 */

public class ToDoAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private ArrayList<ToDoModel> todoModels = new ObservableArrayList<>();
    MainViewModel viewModel;
    public TodoItemBinding binding;

    /*Constructor that gets the ArrayList of ToDoModel object to be used for display in the recyclerview.
      It alse gets an instance of the viewModel from MainActivity. This has to be set into the ToDoItemBinding
      in order to be able to use the functions inside MainViewModel for the onClick attribute in the xml.
     */

    public ToDoAdapter(ArrayList<ToDoModel> todoModels, MainViewModel viewModel) {
        this.todoModels = todoModels;
        this.viewModel = viewModel;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.todo_item, parent, false);
        return new CustomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {

        //Set all variables in the binding for this particular object
        TodoItemBinding viewDataBinding = holder.getmViewDataBinding();
        viewDataBinding.setViewModel(viewModel);
        viewDataBinding.setTodomodel(todoModels.get(position));
        viewDataBinding.setView(holder.itemView);
        viewDataBinding.setPosition(todoModels.get(position).itemId());
        Log.d("adapter", "onBindViewHolder: " + binding.getPosition());
    }

    @Override
    public int getItemCount() {
        return null != todoModels ? todoModels.size() : 0;
    }

}

