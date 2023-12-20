package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private ArrayList<Task> tasks = new ArrayList<>();
    private EditText edtTaskText;
    private Button btnAddTask;
    private DatabaseHelper databaseHelper;
    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onItemClick(int position) {
            Toast.makeText(MainActivity.this, "position : "+position,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onChecked(int position, boolean checked) {
            //Toast.makeText(MainActivity.this, "position : " +position+" checked: "+checked,Toast.LENGTH_SHORT).show();
            tasks.get(position).setCompleted(checked);
            databaseHelper.saveTasks(tasks);

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        tasks = databaseHelper.getTasks();

        edtTaskText = findViewById(R.id.edtTaskText);
        btnAddTask  = findViewById(R.id.btnAddTask);

        btnAddTask.setOnClickListener(v->addTask());

        /*
        tasks.add(new Task("this is my first task"));
        tasks.add(new Task("this is my new task"));
        tasks.add(new Task("this is my another task"));
         */
        recyclerView = findViewById(R.id.taskRecyclerView);
        adapter = new TaskAdapter(tasks,onClickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_delete){
            Iterator<Task> iterator = tasks.iterator();

            while (iterator.hasNext()){
                if (iterator.next().isCompleted())
                    iterator.remove();
            }
            adapter.notifyDataSetChanged();
            databaseHelper.saveTasks(tasks);
        }
        return super.onOptionsItemSelected(item);
    }

    private void addTask() {
        tasks.add(0,new Task(edtTaskText.getText().toString()));
        edtTaskText.setText(null);
        adapter.notifyItemInserted(0);
        databaseHelper.saveTasks(tasks);

    }
}