package com.example.israelsson.todolist;

import android.app.Application;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.israelsson.todolist.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static RecyclerView mTodoRecyclerView;
    private ArrayList<ToDoModel> toDoModels = new ArrayList<>();
    ActivityMainBinding binding;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Application application = this.getApplication();
        viewModel = new MainViewModel(application);

        //Set up the ActivityMainBinding between this activity and the layout.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        //get an instance of the RecyclerView from the binding, set LayoutManager and a new ToDoAdapter
        mTodoRecyclerView = binding.recyclerView;
        mTodoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ToDoAdapter mToDoAdapter = new ToDoAdapter(viewModel.allNotes(), viewModel);
        mTodoRecyclerView.setAdapter(mToDoAdapter);

        //Set this view into the mainView variable inside the <data> tag in activity_main.xml to be able to
        //use it for the onClick attribute.
        binding.setMainView(this);

    }

        /* This function stores the entered text inside the noteEndered String and calls the addNote function in
           MainViewModel to get the new list of notes from the allNotesCursor and sets a new Adapter to the
           RecyclerView
         */
        public void addNoteFromEditText(View view) {
            String noteEntered = binding.enterNoteEditText.getText().toString();
            viewModel.addNote(noteEntered);
            binding.enterNoteEditText.setText("");
            mTodoRecyclerView.setAdapter(new ToDoAdapter(viewModel.allNotes(), viewModel));
        }

    }

