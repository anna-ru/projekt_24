package com.example.projectbored;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.DynamicLayout;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Objects;

public class MainFragment extends Fragment {

    public static EventManager eventManager = null;

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

        //Ez a tag-ekhez lett készítve, hátha jól jön még később, esetleg listázáshoz akár
        //createLayoutDynamically(5,getActivity());
        //TODO: az optionsben meg kell majd jeleníteni az aktuális filtereket mert most visszaállnak kezdetleges értékre
        if(eventManager == null){
            eventManager = new EventManager();
            eventManager.fillEventsListWithSampleData();
        }

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

        Button options_button = view.findViewById(R.id.options_button);
        options_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new OptionsFragment(),null).addToBackStack(null).commit();
            }
        });

        Button settings_button = view.findViewById(R.id.settings_button);
        settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //((MainActivity)getActivity()).addSettingsFragment();
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new SettingsFragment(),null).addToBackStack(null).commit();
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

    private void createLayoutDynamically(int n,Context context){

        for (int i = 0; i < n; i++) {
            Button myButton = new Button(context);
            myButton.setText("Button :"+i);
            myButton.setId(i);
            final int id_ = myButton.getId();

            //LinearLayout layout = getActivity().findViewById(R.id.categories);
            //layout.addView(myButton);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(context,
                            "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }
    }
}
