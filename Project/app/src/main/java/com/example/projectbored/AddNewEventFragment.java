package com.example.projectbored;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projectbored.database.Event;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


//TODO: A google maps dolgot meg kell még oldani itt, ha újat akarunk addolni a user hogyan tudja megadni

public class AddNewEventFragment extends Fragment {

    private EventClass newEvent = new EventClass("","",false,false,null,null);

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

        final CheckBox isGroupCheckBox = view.findViewById(R.id.checkbox_isGroup);
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

        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = locationSpinner.getSelectedItem().toString();
                newEvent.setLocation(MainFragment.eventManager.StringToLocation(selected));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        priceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = priceSpinner.getSelectedItem().toString();
                newEvent.setPrice(MainFragment.eventManager.StringToPrice(selected));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        isGroupCheckBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                newEvent.setGroup(isGroupCheckBox.isChecked());
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newEvent.setName(titleInputField.getText().toString());
                Log.d("debug","new event: " + newEvent.getName() + ", " + newEvent.getGroup() + ", " + newEvent.getLocation() + ", " + newEvent.getPrice());
                MainFragment.eventManager.getEventsList().add(newEvent);


                Event dbEvent = new Event(newEvent.getName(),newEvent.getGroup(),newEvent.getLocation().ordinal(),newEvent.getPrice().ordinal(),newEvent.getMapsData(),newEvent.getShowMap());
                Toast.makeText(getActivity(),"Event added successfully" + dbEvent.getName() + ", " + dbEvent.isIs_group() + ", " + dbEvent.getIs_indoor() + ", " + dbEvent.getIs_free() + ", " + dbEvent.getSearch_map(),Toast.LENGTH_SHORT).show();

                MainActivity.appDatabase.eventDao().addEvent(dbEvent);
                titleInputField.setText("");
                isGroupCheckBox.setChecked(false);
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
