package com.example.android.bactrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
//import android.widget.PopupWindow;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the phrases category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link PhrasesActivity}
                Intent intent = new Intent(StartActivity.this, Settings.class);

                // Start the new activity
                startActivity(intent);
            }
        });

        Button about = (Button) findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the phrases category is clicked on.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, AboutActivity.class);
                // Start the new activity
                startActivity(intent);
            }
        });

//        Button about = (Button) findViewById(R.id.about);
//        about.setOnClickListener(new View.OnClickListener() {
//            // The code in this method will be executed when the phrases category is clicked on.
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(StartActivity.this, AboutActivity.class);
//                // Start the new activity
//                startActivity(intent);
//            }
//        });
    }
}
