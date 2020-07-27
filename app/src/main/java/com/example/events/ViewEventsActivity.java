package com.example.events;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ViewEventsActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirestoreRecyclerAdapter adapter;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);

        db = FirebaseFirestore.getInstance();
        rv = (RecyclerView)findViewById(R.id.recycler_view);

        // query to keep showing events that haven't happened or events that haven't ended (assumes one hour event)
        Query query = db.collection("events").whereGreaterThan("timestamp", getEndTimestamp());

        Log.d("KUZMA", ""+ getEndTimestamp());

        //recycler options
        FirestoreRecyclerOptions<Event> options = new FirestoreRecyclerOptions.Builder<Event>()
                .setQuery(query, Event.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Event, EventViewHolder>(options) {
            @NonNull
            @Override
            public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
                return new EventViewHolder(v);
            }

            @Override
            protected void onBindViewHolder(@NonNull EventViewHolder holder, int position, @NonNull Event model) {
                holder.eventName.setText(model.getName());
                holder.eventDescription.setText(model.getDescription());
                holder.eventDate.setText(model.getDate());
                holder.eventTime.setText(model.getTime());
                holder.eventResHall.setText(model.getReshall());
                holder.eventLocation.setText(model.getLocation());
                holder.eventCategory.setText(model.getCategory());
            }
        };

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
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

    private class EventViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView eventName;
        TextView eventDescription;
        TextView eventDate;
        TextView eventTime;
        TextView eventResHall;
        TextView eventLocation;
        TextView eventCategory;

        EventViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            eventName = (TextView)itemView.findViewById(R.id.name);
            eventDescription = (TextView)itemView.findViewById(R.id.description);
            eventDate = (TextView)itemView.findViewById(R.id.date);
            eventTime = (TextView)itemView.findViewById(R.id.time);
            eventResHall = (TextView)itemView.findViewById(R.id.resHall);
            eventLocation = (TextView)itemView.findViewById(R.id.location);
            eventCategory = (TextView)itemView.findViewById(R.id.category);
        }


    }
}
