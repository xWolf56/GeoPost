package com.example.loginregister;

public class User {
    private String first_name, last_name, email, birthday;

    public User(String first_name, String last_name, String email, String birthday) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String gender) {
        this.last_name = gender;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFIrstName(String name) {
        this.first_name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) { this.birthday = birthday; }
}
