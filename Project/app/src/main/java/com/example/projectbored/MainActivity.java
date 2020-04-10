package com.example.projectbored;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            addMainFragment();
        }
    }

    private void addMainFragment() {
        getSupportFragmentManager().popBackStackImmediate(null,POP_BACK_STACK_INCLUSIVE );
        MainFragment loginFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, loginFragment)
                .addToBackStack(null).commit();
    }

    public void addEventListFragment() {
        getSupportFragmentManager().popBackStackImmediate(null,POP_BACK_STACK_INCLUSIVE );
        EventListFragment eventListFragment = new EventListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, eventListFragment)
                .addToBackStack(null).commit();
    }


    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() <= 1){
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
