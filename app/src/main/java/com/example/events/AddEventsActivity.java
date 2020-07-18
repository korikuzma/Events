package com.example.events;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddEventsActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events);

        // Access a Cloud Firestore instance from current Activity
        db = FirebaseFirestore.getInstance();
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

                String  eventNameStr = eventName.getText().toString().toUpperCase().trim();
                String  eventDescriptionStr = eventDescription.getText().toString().toUpperCase().trim();
                String  eventDateStr = eventDate.getText().toString().trim();
                String  eventTimeStr = eventTime.getText().toString().toUpperCase().trim();
                String  eventLocationStr = eventLocation.getText().toString().toUpperCase().trim();
                String  eventCategoryStr = eventCategory.getText().toString().toUpperCase().trim();

                Event event = new Event(eventNameStr, eventDescriptionStr, eventDateStr,
                        eventTimeStr, eventLocationStr, eventCategoryStr);

                // If form is filled out correctly, go back to MainEventsActivity
                if(event.allValid(event.isValidDate(eventDateStr), event.isValidTime(eventTimeStr),
                        event.isValidCategory(eventCategoryStr))){

                    // Adds event to firebase
                    DocumentReference docRef = db.collection("events").document();
                    Map<String, String> docEventData = new HashMap<>();
                    docEventData.put("name", eventNameStr);
                    docEventData.put("description", eventDescriptionStr);
                    docEventData.put("date", eventDateStr);
                    docEventData.put("time", eventTimeStr);
                    docEventData.put("location", eventLocationStr);
                    docEventData.put("category", eventCategoryStr);

                    event.setmId(docRef.getId());
                    Log.d("EventID", "Event ID: " + event.getmId());
                    db.collection("events").document(event.getmId()).set(docEventData);

                    // Start MainEventsActivity
                    Intent i = new Intent(AddEventsActivity.this, MainEventsActivity.class);
                    AddEventsActivity.this.startActivity(i);
                }
            }
        });
    }
}
