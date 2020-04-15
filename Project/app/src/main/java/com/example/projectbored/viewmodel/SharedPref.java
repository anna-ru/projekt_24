package com.example.projectbored.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    SharedPreferences sharedPrefs;

    public SharedPref(Context context) {
        sharedPrefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    public void setNightModeState(boolean state) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean("NightMode", state);
        editor.commit();
    }

    public boolean getNightModeState() {
        return sharedPrefs.getBoolean("NightMode", false);
    }

    public void setPowerUserState(boolean state) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean("PowerUser", state);
        editor.commit();
    }

    public boolean getPowerUserState() {
        return sharedPrefs.getBoolean("PowerUser", false);
    }

}
