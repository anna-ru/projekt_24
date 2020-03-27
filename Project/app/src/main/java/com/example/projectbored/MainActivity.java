package com.example.projectbored;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.projectbored.database.AppDatabase;
import com.example.projectbored.database.Event;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "event").createFromAsset("database/events.csv").build();

        //find idea button and set onclick event
        Button button = findViewById(R.id.ideaButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Event randomEvent = new Event(); //TODO: switch out for database query
                randomEvent.name = "testing 123";
                onButtonShowPopupWindowClick(v, randomEvent.name);
            }
        });
    }

    //https://stackoverflow.com/questions/5944987/how-to-create-a-popup-window-popupwindow-in-android
    public void onButtonShowPopupWindowClick(View view, String event) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

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
        if(chosenIdea != null) {
            chosenIdea.setText(event);
        } else {
            Log.d("debug","chosenIdea was null");
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
        showInMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988"); //TODO: ezt kell átírni

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");

                // Attempt to start an activity that can handle the Intent
                startActivity(mapIntent);
            }
        });
    }
}
