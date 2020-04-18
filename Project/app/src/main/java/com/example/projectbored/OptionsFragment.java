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

/**
 * This is the options page of the app, it sets listeners to spinners and a checkbox
 *
 * @author  Osbáth Gergely
 * @version 1.0
 * @since   2020-04-11
 */
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

    /**
     * This is the method that gets called after this fragment has been created. It handles setting
     * listeners for the spinners and the checkbox on this page
     *
     * @param view The current view that is in part used to find the spinners and checkbox on the page
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //MainFragment.eventManager.fillEventsListWithSampleData(); //ez addig kell csak amíg nincs adatbázis

        final CheckBox isGroupCheckBox = view.findViewById(R.id.checkbox_isGroup);
        final Spinner locationSpinner = view.findViewById(R.id.spinner_location);

        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.locationArray, R.layout.color_spinner_layout);
        locationAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        locationSpinner.setAdapter(locationAdapter);

        final Spinner priceSpinner = view.findViewById(R.id.spinner_price);

        ArrayAdapter<CharSequence> priceAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.price, R.layout.color_spinner_layout);
        priceAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        priceSpinner.setAdapter(priceAdapter);

        //set the default value of spinners and checkbox
        isGroupCheckBox.setChecked(MainFragment.eventManager.getSearchForGroup());

        if(MainFragment.eventManager.getSelectedLocation().equals(Location.Any)){
            locationSpinner.setSelection(0);
        }
        if(MainFragment.eventManager.getSelectedLocation().equals(Location.Indoors)){
            locationSpinner.setSelection(1);
        }
        if(MainFragment.eventManager.getSelectedLocation().equals(Location.Outdoors)){
            locationSpinner.setSelection(2);
        }

        if(MainFragment.eventManager.getSelectedPrice().equals(Price.Any)){
            priceSpinner.setSelection(0);
        }
        if(MainFragment.eventManager.getSelectedPrice().equals(Price.Free)){
            priceSpinner.setSelection(1);
        }
        if(MainFragment.eventManager.getSelectedPrice().equals(Price.Paid)){
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
