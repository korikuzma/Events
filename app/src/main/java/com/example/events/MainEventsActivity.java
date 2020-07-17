package com.example.events;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainEventsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_events);
    }

    @Override
    public void onResume(){
        super.onResume();

        Button settingsBtn = findViewById(R.id.settingsBtn);
        Button viewEventsBtn = findViewById(R.id.viewEventsBtn);
        Button addEventsBtn = findViewById(R.id.addEventsBtn);

        // When settings button clicked, opens SettingsActivity
        settingsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainEventsActivity.this, SettingsActivity.class);
                MainEventsActivity.this.startActivity(i);
            }
        });

        // When viewEvents button clicked, opens ViewEventsActivity
        viewEventsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainEventsActivity.this, ViewEventsActivity.class);
                MainEventsActivity.this.startActivity(i);
            }
        });

        // When addEvents button clicked, opens addEventsActivity
        addEventsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainEventsActivity.this, AddEventsActivity.class);
                MainEventsActivity.this.startActivity(i);
            }
        });
    }

}
