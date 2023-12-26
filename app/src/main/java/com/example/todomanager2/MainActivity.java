package com.example.todomanager2;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todomanager2.DB.TodoDbHelper;
import com.example.todomanager2.model.TodoItem;
import com.example.todomanager2.model.TodoModel;
import com.example.todomanager2.presenter.TodoPresenter;
import com.example.todomanager2.view.TodoAdapter;
import com.example.todomanager2.view.TodoView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TodoView {

    private TodoPresenter presenter;
    private TodoAdapter todoAdapter;

    private EditText titleEditText;
    private EditText descriptionEditText;
    private Button addButton;
    private ListView todoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        addButton = findViewById(R.id.addButton);
        todoListView = findViewById(R.id.todoListView);

        TodoDbHelper dbHelper = new TodoDbHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        TodoModel model = new TodoModel(database);
        presenter = new TodoPresenter(this, model);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                presenter.addTodoItem(title, description);
                presenter.loadTodoItems(); // Refresh the list
            }
        });

        todoAdapter = new TodoAdapter(this, R.layout.todo_item);
        todoListView.setAdapter(todoAdapter);

        presenter.loadTodoItems();
    }

    @Override
    public void displayTodoItems(List<TodoItem> todoItemList) {
        todoAdapter.clear();
        todoAdapter.addAll(todoItemList);
    }

    @Override
    public void showAddSuccess() {
        Toast.makeText(this, "Todo added successfully", Toast.LENGTH_SHORT).show();
        clearInputFields();
    }

    @Override
    public void showAddError() {
        Toast.makeText(this, "Error adding todo", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUpdateSuccess() {
        Toast.makeText(this, "Todo updated successfully", Toast.LENGTH_SHORT).show();
        clearInputFields();
        presenter.loadTodoItems(); // Refresh the list
    }

    @Override
    public void showDeleteSuccess() {
        Toast.makeText(this, "Todo deleted successfully", Toast.LENGTH_SHORT).show();
        clearInputFields();
        presenter.loadTodoItems(); // Refresh the list
    }

    private void clearInputFields() {
        titleEditText.setText("");
        descriptionEditText.setText("");
    }
}

