package com.example.events;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class MainEventsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_events);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
    }

    @Override
    public void onResume(){
        super.onResume();

        ImageButton settingsBtn = findViewById(R.id.settingsBtn);
        Button viewEventsBtn = findViewById(R.id.viewEventsBtn);
        Button addEventsBtn = findViewById(R.id.addEventsBtn);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(MainEventsActivity.this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        showAddEventsButton(addEventsBtn, account.getEmail());

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

    // show button if admin email
    public void showAddEventsButton(Button addEventsBtn, String email){
        if (email.equals("korikuzma@gmail.com"))
        {
            addEventsBtn.setVisibility(View.VISIBLE);
        } else {
            addEventsBtn.setVisibility(View.INVISIBLE);
        }
    }

}
