package com.example.projectbored;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectbored.database.Event;
import com.google.android.material.textfield.TextInputEditText;


//TODO: A google maps dolgot meg kell még oldani itt, ha újat akarunk addolni a user hogyan tudja megadni

public class AddNewEventFragment extends Fragment {

    private Event newEvent = new Event("",0,0,0,null,false);

    public AddNewEventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_event, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputEditText titleInputField = getActivity().findViewById(R.id.event_name_input_field);

        Button backButton = getActivity().findViewById(R.id.back_button);
        Button saveButton = getActivity().findViewById(R.id.save_button);
        saveButton.setEnabled(false);
        saveButton.setAlpha(0.5f);

        final Spinner isGroupSpinner = view.findViewById(R.id.spinner_isGroup);

        ArrayAdapter<CharSequence> isGroupAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.isGroupArray,R.layout.color_spinner_layout);
        isGroupAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        isGroupSpinner.setAdapter(isGroupAdapter);

        final Spinner locationSpinner = view.findViewById(R.id.spinner_location);

        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.locationArray,R.layout.color_spinner_layout);
        locationAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        locationSpinner.setAdapter(locationAdapter);

        final Spinner priceSpinner = view.findViewById(R.id.spinner_price);

        ArrayAdapter<CharSequence> priceAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.price,R.layout.color_spinner_layout);
        priceAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        priceSpinner.setAdapter(priceAdapter);

        TextInputEditText mapsDataInputField = getActivity().findViewById(R.id.google_maps_search_input_field);

        if(((MainActivity) getActivity()).sharedPrefs.getPowerUserState()){
            mapsDataInputField.setVisibility(View.VISIBLE);
        }else{
            mapsDataInputField.setVisibility(View.GONE);
        }


        titleInputField.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() == 0) {
                    saveButton.setEnabled(false);
                    saveButton.setAlpha(0.5f);
                }
                else {
                    saveButton.setEnabled(true);
                    saveButton.setAlpha(1.0f);
                }
            }
        });

        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = locationSpinner.getSelectedItem().toString();
                newEvent.setIs_indoor(MainFragment.eventManager.StringToLocation(selected).ordinal());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        priceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = priceSpinner.getSelectedItem().toString();
                newEvent.setIs_free(MainFragment.eventManager.StringToPrice(selected).ordinal());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        isGroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = isGroupSpinner.getSelectedItem().toString();
                newEvent.setIs_group(MainFragment.eventManager.StringToPrice(selected).ordinal());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(titleInputField.getText().toString().equals("")){

                }
                newEvent.setName(titleInputField.getText().toString());
                newEvent.setSearch_map(mapsDataInputField.getText().toString());

                if(!newEvent.getSearch_map().equals("")){
                    newEvent.setShow_map(true);
                }

                MainFragment.eventManager.getEventsList().add(newEvent);
                Toast.makeText(getActivity(),"Event added successfully" + newEvent.getName() + ", " + newEvent.isIs_group() + ", " + newEvent.getIs_indoor() + ", " + newEvent.getIs_free() + ", " + newEvent.getSearch_map(),Toast.LENGTH_SHORT).show();

                MainActivity.appDatabase.eventDao().addEvent(newEvent);
                titleInputField.setText("");
                isGroupSpinner.setSelection(0);
                locationSpinner.setSelection(0);
                priceSpinner.setSelection(0);
                ((MainActivity)getActivity()).onBackPressed();
            }
        });

       backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).onBackPressed();
            }
        });
    }

}
