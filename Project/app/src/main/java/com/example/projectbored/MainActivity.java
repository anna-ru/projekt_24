package com.example.projectbored;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

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


    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
