package com.example.projectbored;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

    private EventManager eventManager;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eventManager = new EventManager();
        eventManager.fillEventsListWithSampleData(); //ez addig kell csak amíg nincs adatbázis

        final CheckBox isGroupCheckBox = view.findViewById(R.id.checkbox_isGroup);
        final Spinner timeOfDaySpinner = view.findViewById(R.id.spinner_timeOfDay);

        ArrayAdapter<CharSequence> timeOfDayAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.timeOfDayArray,android.R.layout.simple_spinner_item);
        timeOfDayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeOfDaySpinner.setAdapter(timeOfDayAdapter);

        final Spinner priceSpinner = view.findViewById(R.id.spinner_price);

        ArrayAdapter<CharSequence> priceAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.price,android.R.layout.simple_spinner_item);
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priceSpinner.setAdapter(priceAdapter);

        //TODO: ez így nem biztos, hogy a legoptimálisabb
        timeOfDaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = timeOfDaySpinner.getSelectedItem().toString();
                Log.d("debug","selected TimeOfDay: " + selected);
                eventManager.setSelectedLocation(eventManager.StringToTimeOfDay(selected));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        priceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = priceSpinner.getSelectedItem().toString();
                Log.d("debug","selected Price: " + selected);
                eventManager.setSelectedPrice(eventManager.StringToPrice(selected));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        isGroupCheckBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                eventManager.setSearchForGroup(isGroupCheckBox.isChecked());
            }
        });

        //find idea button and set onclick event
        Button button = view.findViewById(R.id.ideaButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                EventClass randomEventClass = eventManager.getRandomElementOfEventsListByParameters();
                //TODO: switch out for database query
                onButtonShowPopupWindowClick(v, randomEventClass.getName(), randomEventClass.getMapsData(),
                        randomEventClass.getShowMap());
            }
        });

        Button all_ideas_button = view.findViewById(R.id.all_ideas_button);
        all_ideas_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).addEventListFragment();
            }
        });
    }

    //https://stackoverflow.com/questions/5944987/how-to-create-a-popup-window-popupwindow-in-android
    public void onButtonShowPopupWindowClick(View view, String event, final String mapsData, final Boolean showMap) {

        // inflate the layout of the popup window
        final View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_window, null);
        //final AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(popupView).create();
        //LayoutInflater inflater = LayoutInflater.from(getContext())(LAYOUT_INFLATER_SERVICE);
        //View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        //setting the text of the chosen idea
        TextView chosenIdea = popupView.findViewById(R.id.chosenIdea);
        if (chosenIdea != null) {
            chosenIdea.setText(event);
        } else {
            Log.d("debug", "chosenIdea was null");
        }

        // dismiss the popup window when back button is clicked
        Button popupBackButton = popupView.findViewById(R.id.popupBackButton);
        popupBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        Button showInMapButton = popupView.findViewById(R.id.showInMapButton);
        if (showMap) {
            showInMapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create a Uri from an intent string. Use the result to create an Intent.
                    // Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988"); //TODO: ezt kell átírni
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + mapsData);

                    // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    // Make the Intent explicit by setting the Google Maps package
                    mapIntent.setPackage("com.google.android.apps.maps");

                    // Attempt to start an activity that can handle the Intent
                    startActivity(mapIntent);
                }
            });
        }else{
            showInMapButton.setVisibility(View.GONE);
        }
    }
}
