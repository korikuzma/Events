package com.example.events;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddEventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events);
    }

    @Override
    public void onResume(){
        super.onResume();

        final Button submitEventBtn = (Button)findViewById(R.id.submitEventBtn);
        final EditText eventName = (EditText)findViewById(R.id.addName);
        final EditText eventDescription = (EditText)findViewById(R.id.addDescription);
        final EditText eventDate = (EditText)findViewById(R.id.addDate);
        final EditText eventTime = (EditText)findViewById(R.id.addTime);
        final EditText eventLocation = (EditText)findViewById(R.id.addLocation);
        final EditText eventCategory = (EditText)findViewById(R.id.addCategory);

        // When submit event button is pressed, save string values and store in firebase
        submitEventBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                String  eventNameStr = eventName.getText().toString().trim();
                String  eventDescriptionStr = eventDescription.getText().toString().trim();
                String  eventDateStr = eventDate.getText().toString().trim();
                String  eventTimeStr = eventTime.getText().toString().trim();
                String  eventLocationStr = eventLocation.getText().toString().trim();
                String  eventCategoryStr = eventCategory.getText().toString().trim();

                Event event = new Event(eventNameStr, eventDescriptionStr, eventDateStr,
                        eventTimeStr, eventLocationStr, eventCategoryStr);

                // If form is filled out correctly, go back to MainEventsActivity
                if(event.allValid(event.isValidDate(eventDateStr), event.isValidTime(eventTimeStr),
                        event.isValidCategory(eventCategoryStr))){
                    Intent i = new Intent(AddEventsActivity.this, MainEventsActivity.class);
                    AddEventsActivity.this.startActivity(i);
                }
            }
        });
    }
}
