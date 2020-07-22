package com.example.events;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Event {
    private String mName;
    private String mDescription;
    private String mId;
    private String mDate;
    private String mTime;
    private String mLocation;
    private String mCategory;
    private String mResHall;

    public Event(String name, String description, String date, String time, String resHall, String location, String category){
        mName = name;
        mDescription = description;
        mDate = date;
        mTime = time;
        mResHall = resHall;
        mLocation = location;
        mCategory = category;
    }

    public void setmId(String Id){
        mId = Id;
    }

    public String getmId(){
        return mId;
    }

    // Returns whether date is in the form of mm/dd/yyyy
    // Date must occur between 2000-2099
    public boolean isValidDate(String strDate){
        String pattern= "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/20[2-9][0-9]$";
        return strDate.matches(pattern);
    }

    // Returns whether time is in the form of hh:mm a
    public boolean isValidTime(String strTime){
        String pattern = "^(0[1-9]|1[0-2]):([0-5][0-9])\\s(A|P)$";
        return (strTime.toUpperCase()).matches(pattern);
    }

    // Returns whether category is in the form of DI, C, W, or L
    public boolean isValidCategory(String strCategory){
        String pattern = "^DI|C|W|L$";
        return (strCategory.toUpperCase()).matches(pattern);
    }

    // Returns whether reshall entered is a valid res hall at OSU
    public boolean isValidResHall(String strResHall, ArrayList resHallsList){
        return resHallsList.contains(strResHall);
    }

    // Returns whether all variables are valid and not null
    public boolean allValid(boolean boolDate, boolean boolTime, boolean boolCategory, boolean boolResHall){
        return boolDate && boolTime && boolCategory && boolResHall && (mName != null && !mName.isEmpty()) &&
                (mDescription != null && !mDescription.isEmpty()) && (mLocation != null && !mLocation.isEmpty());
    }
}


