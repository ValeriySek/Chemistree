package com.selflearning.chemistree.data;

import com.google.firebase.auth.FirebaseAuth;

public class User {

    private FirebaseAuth mAuth;

    private String firstName;
    private String uid;
    private String secondName;
    private String email;
    private int profileImg;

    private static User user = null;

    public static User getInstance(){
        if(user == null){
            user = new User();
        }
        return user;
    }

    public void deleteUser(){
        user = null;
    }

    private User(){
        mAuth = FirebaseAuth.getInstance();
    }

    public String getUid() {
        return mAuth.getCurrentUser().getUid();
    }

    public String getFirstName() {
        return mAuth.getCurrentUser().getDisplayName();
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return mAuth.getCurrentUser().getEmail();
    }

    public int getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(int profileImg) {
        this.profileImg = profileImg;
    }
}
