package com.example.events;

public class Event {
    private String mName;
    private String mDescription;
    private String mDate;
    private String mTime;
    private String mLocation;
    private String mCategory;

    public Event(String name, String description, String date, String time, String location, String category){
        mName = name;
        mDescription = description;
        mDate = date;
        mTime = time;
        mLocation = location;
        mCategory = category;
    }

    // Returns whether date is in the form of mm/dd/yyyy
    // Date must occur after 2020
    public boolean isValidDate(String strDate){
        String pattern= "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/20[2-9][0-9]$";
        return strDate.matches(pattern);
    }

    // Returns whether time is in the form of hh:mm a
    public boolean isValidTime(String strTime){
        String pattern = "^(0[1-9]|1[0-2]):([0-5][0-9])\\s(AM|A|PM|P)$";
        return (strTime.toUpperCase()).matches(pattern);
    }

    // Returns whether category is in the form of DI, C, W, or L
    public boolean isValidCategory(String strCategory){
        String pattern = "^DI|C|W|L$";
        return (strCategory.toUpperCase()).matches(pattern);
    }

    // Returns whether all variables are valid and not null
    public boolean allValid(boolean boolDate, boolean boolTime, boolean boolCategory){
        return boolDate && boolTime && boolCategory && (mName != null && !mName.isEmpty()) &&
                (mDescription != null && !mDescription.isEmpty()) && (mLocation != null && !mLocation.isEmpty());
    }
}


