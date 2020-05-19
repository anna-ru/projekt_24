package com.example.projectbored;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectbored.database.Event;
import com.example.projectbored.viewmodel.EventViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

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

        lView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (lView.getLastVisiblePosition() - lView.getHeaderViewsCount() - lView.getFooterViewsCount() >= adapter.getCount() - 1) {
                    add_new_event_button.animate().alpha(0f).setDuration(300).withEndAction(() -> {
                        add_new_event_button.setVisibility(View.GONE);
                    }).start();
                }else{
                    add_new_event_button.setVisibility(View.VISIBLE);
                    add_new_event_button.animate().alpha(1f).setDuration(300).start();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }

}
