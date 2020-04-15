package com.example.projectbored;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.projectbored.viewmodel.SharedPref;

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

    }
}
