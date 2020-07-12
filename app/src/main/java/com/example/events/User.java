package com.example.events;

public class User {
    private String mEmail;
    private String mId;

    public User(String email, String id) {
        mEmail = email;
        mId = id;
    }

    public String getUserEmail(){
        return mEmail;
    }

    public String getUserID(){
        return mId;
    }

}
