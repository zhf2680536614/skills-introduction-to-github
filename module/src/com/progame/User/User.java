package com.progame.User;

public class User {
    private String user_Name;
    private String user_Password;


    public User() {
    }

    public User(String user_Name, String user_Password) {
        this.user_Name = user_Name;
        this.user_Password = user_Password;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_Password() {
        return user_Password;
    }

    public void setUser_Password(String user_Password) {
        this.user_Password = user_Password;
    }

    public String toString() {
        return "User{user_Name = " + user_Name + ", user_Password = " + user_Password + "}";
    }
}
