package com.example.projectbored;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import com.example.projectbored.database.AppDatabase;

import com.example.projectbored.database.EventsData;
import com.example.projectbored.viewmodel.SharedPref;

import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    public static AppDatabase appDatabase;
    SharedPref sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPrefs = new SharedPref(this);
        if(sharedPrefs.getNightModeState()) {
            setTheme(R.style.DarkTheme); //setTheme needs to be called before setContentView
        } else setTheme(R.style.AppTheme);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar

        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        appDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"eventdb").allowMainThreadQueries().build();
        if(appDatabase.eventDao().getEvents().size() == 0){
            appDatabase.eventDao().populateDatabase(EventsData.populateEventsData());
        }

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            addMainFragment();
        }
    }

    private void addMainFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment loginFragment = new MainFragment();
        fragmentTransaction.add(R.id.fragment_container, loginFragment,"main");
        fragmentTransaction.commit();
    }

    public void addOptionsFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        OptionsFragment optionsFragment = new OptionsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, optionsFragment,"options")
                .addToBackStack("options").commit();
    }

    public void addSettingsFragment() {
        getSupportFragmentManager().popBackStackImmediate(null,POP_BACK_STACK_INCLUSIVE );
        SettingsFragment settingsFragment = new SettingsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, settingsFragment,null)
                .addToBackStack(null).commit();
    }

    public void addEventListFragment() {
        getSupportFragmentManager().popBackStackImmediate(null,POP_BACK_STACK_INCLUSIVE );
        EventListFragment eventListFragment = new EventListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, eventListFragment,null)
                .addToBackStack(null).commit();
    }

    public void restartApp() {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }


    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
