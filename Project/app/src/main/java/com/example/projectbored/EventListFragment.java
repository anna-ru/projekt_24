package com.example.projectbored;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectbored.adapters.EventAdapter;
import com.example.projectbored.database.Event;
import com.example.projectbored.viewmodel.EventViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class EventListFragment extends Fragment {

    private EventViewModel mEventViewModel;
    public static final int NEW_EVENT_ACTIVITY_REQUEST_CODE = 1;
    private ArrayList<Event> eventsList = (ArrayList<Event>) MainActivity.appDatabase.eventDao().getEvents();

    public EventListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for(Event event : eventsList){
            Log.d("id","" + event.getId());
            Log.d("name","" + event.getName());
            Log.d("group","" + event.isIs_group());
            Log.d("location","" + event.getIs_indoor());
            Log.d("price","" + event.getIs_free());
            Log.d("mapsdata","" + event.getSearch_map());
            Log.d("showmap","" + event.isShow_map());
            Log.d("separator","----------------------------------------------------------------------------");
        }
        //AllIdeasListAdapter adapter = new AllIdeasListAdapter((LinkedList<EventClass>) MainFragment.eventManager.getEventsList(), this.getActivity());
        AllIdeasListAdapter adapter = new AllIdeasListAdapter(MainActivity.appDatabase.eventDao().getEvents(),this.getActivity());

        ListView lView = (ListView)getActivity().findViewById(R.id.all_ideas_list);
        lView.setAdapter(adapter);

        FloatingActionButton add_new_event_button = view.findViewById(R.id.add_new_idea_fabutton);
        add_new_event_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new AddNewEventFragment(),null).addToBackStack(null).commit();
            }
        });
        /*
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final EventAdapter adapter = new EventAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mEventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        mEventViewModel.getAllEvents().observe(getViewLifecycleOwner(), new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable final List<Event> events) {
                // Update the cached copy of the words in the adapter.
                adapter.addEventList(events);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_EVENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Toast.makeText(
                    getContext(),
                    "OK",
                    Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(
                    getContext(),
                    "Can't save data",
                    Toast.LENGTH_LONG).show();
        }
     */
    }

}
