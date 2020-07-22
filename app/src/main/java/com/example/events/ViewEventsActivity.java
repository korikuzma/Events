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
                        TextView tvResHall = findViewById(R.id.resHall);
                        TextView tvLocation = findViewById(R.id.location);
                        TextView tvCategory = findViewById(R.id.category);

                        tvName.setText((document.get("name")).toString());
                        tvDescription.setText((document.get("description")).toString());
                        tvDate.setText((document.get("date")).toString());
                        tvTime.setText((document.get("time")).toString() + "M");
                        tvResHall.setText((document.get("reshall")).toString());
                        tvLocation.setText((document.get("location")).toString());
                        tvCategory.setText(categoryConversion((document.get("category")).toString()));
                    }
                }
            }
        });
    }

    public String categoryConversion(String category){
        switch(category){
            case "DI":
                return "(DIVERSITY & INCLUSION)";
            case "L":
                return "(LEARNING)";
            case "C":
                return "(COMMUNITY)";
            case "W":
                return "(WELLNES)";
        }
        return null;
    }
}
