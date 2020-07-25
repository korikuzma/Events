package com.example.events;
import java.util.ArrayList;

public class Event {
    private String name;
    private String description;
    private String id;
    private String date;
    private String time;
    private String location;
    private String category;
    private String reshall;

    private Event() {};

    Event(String name, String description, String date, String time, String resHall, String location, String category){
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.reshall = resHall;
        this.location = location;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReshall() {
        return reshall;
    }

    public void setReshall(String reshall) {
        this.reshall = reshall;
    }

    // Returns whether date is in the form of mm/dd/yyyy
    // Date must occur between 2000-2099
    boolean isValidDate(String strDate){
        String pattern= "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/20[2-9][0-9]$";
        return strDate.matches(pattern);
    }

    // Returns whether time is in the form of hh:mm a
    boolean isValidTime(String strTime){
        String pattern = "^(0[1-9]|1[0-2]):([0-5][0-9])\\s(A|P)$";
        return (strTime.toUpperCase()).matches(pattern);
    }

    // Returns whether category is in the form of DI, C, W, or L
    boolean isValidCategory(String strCategory){
        String pattern = "^DI|C|W|L$";
        return (strCategory.toUpperCase()).matches(pattern);
    }

    // Returns whether reshall entered is a valid res hall at OSU
    boolean isValidResHall(String strResHall, ArrayList resHallsList){
        return resHallsList.contains(strResHall);
    }

    // Returns whether all variables are valid and not null
    boolean allValid(boolean boolDate, boolean boolTime, boolean boolCategory, boolean boolResHall){
        return boolDate && boolTime && boolCategory && boolResHall && (name != null && !name.isEmpty()) &&
                (description != null && !description.isEmpty()) && (location != null && !location.isEmpty());
    }
}


