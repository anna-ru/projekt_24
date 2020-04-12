package com.example.projectbored;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class EventListFragment extends Fragment {

    private EventViewModel mEventViewModel;
    public static final int NEW_EVENT_ACTIVITY_REQUEST_CODE = 1;

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

        createLayoutDynamically(MainFragment.eventManager.getEventsList().size(),getActivity());

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

    private void createLayoutDynamically(int n, Context context){

        for (int i = 0; i < n; i++) {
            Button myButton = new Button(context);
            myButton.setText(i+1 + ". " + MainFragment.eventManager.getEventsList().get(i).getName());
            myButton.setId(i);
            final int id_ = myButton.getId();

            LinearLayout layout = getActivity().findViewById(R.id.all_ideas_list);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(20, 0, 20, 20); // (left, top, right, bottom)
            myButton.setLayoutParams(params);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(context,
                            "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                            .show();
                }
            });

            layout.addView(myButton);
        }
    }

}
