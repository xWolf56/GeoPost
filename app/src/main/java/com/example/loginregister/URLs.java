package com.example.loginregister;

public class URLs {
    private static final String ROOT_URL = "http://geopost.college-dev.com/RestService/Users";
    public static final String URL_REGISTER = ROOT_URL + "/New?lastName=%s&firstName=%s&email=%s&password=%s&birthDate=%s";
    public static final String URL_LOGIN = ROOT_URL + "/Login?email=%s&password=%s";
}

