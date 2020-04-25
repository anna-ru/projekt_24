package com.example.projectbored;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.projectbored.database.Event;

import java.util.ArrayList;
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

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.all_ideas_list_layout, null);
        }

        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position).getName());

        listItemText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                notifyDataSetChanged();
                MainFragment.currentId = list.get(position).getId();
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new UpdateEventFragment(),null).addToBackStack(null).commit();

            }
        });

        Button deleteBtn = view.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v, list.get(position), position);
            }
        });

        return view;
    }

    @SuppressLint("InflateParams")
    public void onButtonShowPopupWindowClick(View view, Event ev, int position){
        final View popupView = LayoutInflater.from(context).inflate(R.layout.popup_dialog, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        Button noButton = popupView.findViewById(R.id.no_button);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        Button yesButton = popupView.findViewById((R.id.yes_button));
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyDataSetChanged();
                MainActivity.appDatabase.eventDao().deleteEvent(ev);
                list.remove(position);
                popupWindow.dismiss();
            }
        });
    }
}
