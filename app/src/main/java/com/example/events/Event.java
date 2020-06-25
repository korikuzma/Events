package com.example.events;

public class Event {
    private int mId;
    private String mName;
    private String mDescription;
    private String mDate;
    private String mTime;
    private String mLocation;

    public Event(int id, String name, String description, String date, String time, String location){
        mId = id;
        mName = name;
        mDescription = description;
        mDate = date;
        mTime = time;
        mLocation = location;
    }

}
