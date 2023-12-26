package com.example.todomanager2.presenter;

import com.example.todomanager2.model.TodoItem;
import com.example.todomanager2.model.TodoModel;
import com.example.todomanager2.view.TodoView;

import java.util.List;

public class TodoPresenter {

    private TodoView view;
    private TodoModel model;

    public TodoPresenter(TodoView view, TodoModel model) {
        this.view = view;
        this.model = model;
    }

    public void addTodoItem(String title, String description) {
        long result = model.addTodoItem(title, description);
        if (result != -1) {
            view.showAddSuccess();
        } else {
            view.showAddError();
        }
    }

    public void loadTodoItems() {
        List<TodoItem> todoItemList = model.getTodoItems();
        view.displayTodoItems(todoItemList);
    }

    public void updateTodoItem(String oldTitle, String newTitle, String newDescription) {
        model.updateTodoItem(oldTitle, newTitle, newDescription);
        view.showUpdateSuccess();
    }

    public void deleteTodoItem(String title) {
        model.deleteTodoItem(title);
        view.showDeleteSuccess();
    }
}

