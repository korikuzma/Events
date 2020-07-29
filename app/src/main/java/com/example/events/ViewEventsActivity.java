package com.example.events;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ViewEventsActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference eventsRef = db.collection("events");
    private EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        getSupportActionBar().setElevation(0);

        // query to keep showing events that haven't happened or events that haven't ended (assumes one hour event)
        Query query = eventsRef.whereGreaterThan("timestamp", getEndTimestamp());

        //recycler options
        FirestoreRecyclerOptions<Event> options = new FirestoreRecyclerOptions.Builder<Event>()
                .setQuery(query, Event.class)
                .build();

        adapter = new EventAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // When calendar is clicked, create a google calendar event
        adapter.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
            @Override
            public void onCalendarClick(DocumentSnapshot documentSnapshot, int position) {
                Event event = documentSnapshot.toObject(Event.class);
                String name = event.getName();
                String location = event.getReshall() + " " + event.getLocation();
                String description = event.getDescription();
                String date = event.getDate();
                String time = event.getTime();

                createCalendarEvent(name, location, description, date, time);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void createCalendarEvent(String name, String location, String description, String date, String time){
        // Create new event in Google Calendar
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.Events.TITLE, name);
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, location);
        intent.putExtra(CalendarContract.Events.DESCRIPTION, description);


        // Setting dates
        long beginTime = convertStrToTime(date,time);

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, beginTime + 3600 * 1000);

        intent.putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
        startActivity(intent);
    }

    // Converts string to time in milliseconds
    public long convertStrToTime(String str_date, String str_time){
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
        Date date = null;

        try {
            date = (Date) formatter.parse(str_date + " " + str_time + "M");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    // Get one hour before timestamp
    public Timestamp getEndTimestamp(){
        String str_date = new SimpleDateFormat("MM/dd/yyyy hh:mm aa").format(new Date());
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
        Date date = null;

        Calendar c = Calendar.getInstance();
        try{
            //Setting the date to the current date
            c.setTime(formatter.parse(str_date));
        }catch(ParseException e){
            e.printStackTrace();
        }

        // Add one hour to timestamp
        c.add(Calendar.HOUR_OF_DAY, -1);

        //Date after adding the days to the given date
        String newDate = formatter.format(c.getTime());

        try {
            date = (Date) formatter.parse(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Timestamp(date.getTime());
    }
}
