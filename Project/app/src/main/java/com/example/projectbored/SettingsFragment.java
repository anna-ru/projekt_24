package com.example.projectbored;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectbored.database.EventsData;

/**
 * This is the settings page of the app, it sets listeners to two switches
 *
 * @author  Osbáth Gergely
 * @version 1.1
 * @since   2020-04-12
 */
public class SettingsFragment extends Fragment {

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    /**
     * This is the method that gets called after this fragment has been created. It handles setting
     * listeners for the two switches on this page
     *
     * @param view The current view that is in part used to find the switches on the page
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Switch darkThemeSwitch = view.findViewById(R.id.dark_theme_switch);
        darkThemeSwitch.setChecked(((MainActivity)getActivity()).sharedPrefs.getNightModeState());
        darkThemeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    ((MainActivity)getActivity()).sharedPrefs.setNightModeState(true);
                    ((MainActivity)getActivity()).restartApp();
                }
                else {
                    ((MainActivity)getActivity()).sharedPrefs.setNightModeState(false);
                    ((MainActivity)getActivity()).restartApp();
                }
            }
        });

        Switch powerUserSwitch = view.findViewById(R.id.power_user_switch);
        powerUserSwitch.setChecked(((MainActivity)getActivity()).sharedPrefs.getPowerUserState());
        powerUserSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    ((MainActivity)getActivity()).sharedPrefs.setPowerUserState(true);
                }
                else {
                    ((MainActivity)getActivity()).sharedPrefs.setPowerUserState(false);
                }
            }
        });

        Button resetButton = view.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonShowPopupWindowClick(view);

            }
        });
    }

    @SuppressLint("InflateParams")
    public void onButtonShowPopupWindowClick(View view){
        final View popupView = LayoutInflater.from(this.getActivity()).inflate(R.layout.popup_dialog_reset, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        Button noButton = popupView.findViewById(R.id.reset_no_button);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        Button yesButton = popupView.findViewById((R.id.reset_yes_button));
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.appDatabase.eventDao().clearEvents();
                MainActivity.appDatabase.eventDao().populateDatabase(EventsData.populateEventsData());
                Toast.makeText(getActivity(),"Database reset was successful.",Toast.LENGTH_LONG).show();
                popupWindow.dismiss();
            }
        });

    }
}
