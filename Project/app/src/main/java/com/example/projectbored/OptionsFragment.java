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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

public class OptionsFragment extends Fragment {

    public OptionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_options, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainFragment.eventManager.fillEventsListWithSampleData(); //ez addig kell csak amíg nincs adatbázis

        final CheckBox isGroupCheckBox = view.findViewById(R.id.checkbox_isGroup);
        final Spinner locationSpinner = view.findViewById(R.id.spinner_location);

        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.locationArray,android.R.layout.simple_spinner_item);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);

        final Spinner priceSpinner = view.findViewById(R.id.spinner_price);

        ArrayAdapter<CharSequence> priceAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.price,android.R.layout.simple_spinner_item);
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priceSpinner.setAdapter(priceAdapter);
//set the default value of spinners and checkbox

        if(MainFragment.eventManager.getSearchForGroup()){
            isGroupCheckBox.setChecked(true);
        }

        if(MainFragment.eventManager.getSelectedLocation().toString().equals(Location.Any.toString())){
            locationSpinner.setSelection(0);
        }
        if(MainFragment.eventManager.getSelectedLocation().toString().equals(Location.Indoors.toString())){
            locationSpinner.setSelection(1);
        }
        if(MainFragment.eventManager.getSelectedLocation().toString().equals(Location.Outdoors.toString())){
            locationSpinner.setSelection(2);
        }

        if(MainFragment.eventManager.getSelectedPrice().toString().equals(Price.Any.toString())){
            priceSpinner.setSelection(0);
        }
        if(MainFragment.eventManager.getSelectedPrice().toString().equals(Price.Free.toString())){
            priceSpinner.setSelection(1);
        }
        if(MainFragment.eventManager.getSelectedPrice().toString().equals(Price.Paid.toString())){
            priceSpinner.setSelection(2);
        }

        //TODO: ez így nem biztos, hogy a legoptimálisabb
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = locationSpinner.getSelectedItem().toString();
                MainFragment.eventManager.setSelectedLocation(MainFragment.eventManager.StringToLocation(selected));
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
                MainFragment.eventManager.setSelectedPrice(MainFragment.eventManager.StringToPrice(selected));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        isGroupCheckBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainFragment.eventManager.setSearchForGroup(isGroupCheckBox.isChecked());
            }
        });

    }

}
