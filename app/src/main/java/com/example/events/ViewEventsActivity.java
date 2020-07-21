package com.example.events;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ViewEventsActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);
    }

    @Override
    public void onResume(){
        super.onResume();

        db = FirebaseFirestore.getInstance();

        db.collection("events").document("9aM0l3P8nwMm02tKmvwa").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        TextView tvName = findViewById(R.id.name);
                        TextView tvDescription = findViewById(R.id.description);
                        TextView tvDate = findViewById(R.id.date);
                        TextView tvTime = findViewById(R.id.time);
                        TextView tvLocation = findViewById(R.id.location);
                        TextView tvCategory = findViewById(R.id.category);

                        tvName.setText("Name: " + document.get("name"));
                        tvDescription.setText("Description: " + document.get("description"));
                        tvDate.setText("Date: " + document.get("date"));
                        tvTime.setText("Time: " + document.get("time"));
                        tvLocation.setText("Location: " + document.get("location"));
                        tvCategory.setText("Category: " + document.get("category"));
                    }
                }
            }
        });
    }
}
