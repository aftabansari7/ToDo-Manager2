package com.example.todomanager2.view;

import com.example.todomanager2.model.TodoItem;

import java.util.List;

public interface TodoView {

    void displayTodoItems(List<TodoItem> todoItemList);

    void showAddSuccess();

    void showAddError();

    void showUpdateSuccess();

    void showDeleteSuccess();
}
