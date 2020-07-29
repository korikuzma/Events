package com.example.events;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class EventAdapter extends FirestoreRecyclerAdapter<Event, EventAdapter.EventViewHolder> {

    private OnItemClickListener listener;

    EventAdapter(@NonNull FirestoreRecyclerOptions<Event> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull EventViewHolder holder, int position, @NonNull Event model){
        holder.eventName.setText(model.getName());
        holder.eventDescription.setText(model.getDescription());
        holder.eventDate.setText(model.getDate());
        holder.eventTime.setText(model.getTime());
        holder.eventResHall.setText(model.getReshall());
        holder.eventLocation.setText(model.getLocation());
        holder.eventCategory.setText(model.getCategory());
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new EventViewHolder(v);
    }

    class EventViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView eventName;
        TextView eventDescription;
        TextView eventDate;
        TextView eventTime;
        TextView eventResHall;
        TextView eventLocation;
        TextView eventCategory;
        ImageButton createCalendarEvent;

        public EventViewHolder(View itemView){
            super(itemView);

            cv = itemView.findViewById(R.id.card_view);
            eventName = itemView.findViewById(R.id.name);
            eventDescription = itemView.findViewById(R.id.description);
            eventDate = itemView.findViewById(R.id.date);
            eventTime = itemView.findViewById(R.id.time);
            eventResHall = itemView.findViewById(R.id.resHall);
            eventLocation = itemView.findViewById(R.id.location);
            eventCategory = itemView.findViewById(R.id.category);
            createCalendarEvent = itemView.findViewById(R.id.create_calendar_event);

            createCalendarEvent.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onCalendarClick(getSnapshots().getSnapshot(position), position);
                    }
                }

            });
        }
    }

    public interface OnItemClickListener{
        void onCalendarClick(DocumentSnapshot documentSnapshot, int position);
    }

    void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
