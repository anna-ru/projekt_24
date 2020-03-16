package com.example.projectbored;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectbored.database.AppDatabase;

public class MainActivity extends AppCompatActivity {

    public int c = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "event").createFromAsset("database/events.csv").build();
=======
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = findViewById(R.id.button);
                button.setText("Hello: " + ++c);
            }
        });
>>>>>>> e754fb169aba2192db8a03852851c007bf27920b
    }
}
