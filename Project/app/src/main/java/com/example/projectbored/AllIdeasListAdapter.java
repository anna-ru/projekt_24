package com.example.projectbored;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.projectbored.database.Event;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AllIdeasListAdapter extends BaseAdapter implements ListAdapter {
    //private LinkedList<EventClass> list;
    private ArrayList<Event> list;

    private Context context;
/*
    public AllIdeasListAdapter(LinkedList<EventClass> list, Context context) {
        this.list = list;
        this.context = context;
    }
*/
    public AllIdeasListAdapter(List<Event> list, Context context) {
        this.list = new ArrayList<>();
        this.list.addAll(list);
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.all_ideas_list_layout, null);
        }

        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position).getName());

        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                notifyDataSetChanged();
                MainActivity.appDatabase.eventDao().deleteEvent(list.get(position));
                list.remove(position);

            }
        });

        return view;
    }
}
