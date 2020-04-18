package com.example.projectbored.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * A wrapper for the SharedPreferences class
 *
 * @author  Szabó Bence
 * @version 1.0
 * @since   2020-04-15
 */
public class SharedPref {

    SharedPreferences sharedPrefs;

    /**
     * Gets a shared preferences file to work with
     *
     * @param context A context from which it will get a shared preferences file
     */
    public SharedPref(Context context) {
        sharedPrefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    /**
     * Sets the state of the "NightMode" boolean in the SharedPreferences to the state given
     *
     * @param state  "NightMode" will be set to this
     */
    public void setNightModeState(boolean state) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean("NightMode", state);
        editor.commit();
    }

    /**
     * Returns the state of the "NightMode" boolean in SharedPreferences
     *
     * @return boolean The state of "NightMode", or false if it doesn't exist yet
     */
    public boolean getNightModeState() {
        return sharedPrefs.getBoolean("NightMode", false);
    }

    /**
     * Sets the state of the "PowerUser" boolean in the SharedPreferences to the state given
     *
     * @param state  "PowerUser" will be set to this
     */
    public void setPowerUserState(boolean state) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean("PowerUser", state);
        editor.commit();
    }

    /**
     * Returns the state of the "PowerUser" boolean in SharedPreferences
     *
     * @return boolean The state of "PowerUser", or false if it doesn't exist yet
     */
    public boolean getPowerUserState() {
        return sharedPrefs.getBoolean("PowerUser", false);
    }

}
