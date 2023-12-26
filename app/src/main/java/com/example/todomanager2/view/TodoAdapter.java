package com.example.todomanager2.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.todomanager2.R;
import com.example.todomanager2.model.TodoItem;

import java.util.List;

//ToDo Adapter class for list view
public class TodoAdapter extends ArrayAdapter<TodoItem> {

    public TodoAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.todo_item, null);
        }

        TodoItem todoItem = getItem(position);

        if (todoItem != null) {
            TextView titleTextView = view.findViewById(R.id.titleTextView);
            TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);

            titleTextView.setText(todoItem.getTitle());
            descriptionTextView.setText(todoItem.getDescription());
        }

        return view;
    }
}

