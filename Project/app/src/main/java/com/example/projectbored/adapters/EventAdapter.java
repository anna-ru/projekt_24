package com.example.projectbored.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectbored.R;
import com.example.projectbored.database.Event;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private final LayoutInflater mInflater;
    private List<Event> eventList; // Cached copy of events

    public EventAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        if(event != null){
            holder.event_name.setText(event.getName());
        } else {
            holder.event_name.setText("Null");
        }
    }

    @Override
    public int getItemCount() {
        if(eventList != null) return eventList.size();
        else return 0;
    }

    public void addEventList(List<Event> events) {
        eventList = events;
        notifyDataSetChanged();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder{
        private final TextView event_name;
        private final Button delete_button;

        private EventViewHolder(@NonNull View itemView){
            super(itemView);
            event_name = itemView.findViewById(R.id.event_name);
            delete_button = itemView.findViewById(R.id.delete_button);
        }
    }
}
