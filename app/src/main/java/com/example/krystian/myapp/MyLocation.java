package com.example.krystian.myapp;

/**
 * Created by Krystian on 06.07.2017.
 */

public class MyLocation {
    private double longitude;
    private double latitude;
    private String city;

    MyLocation(double longitude, double latitude, String city) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
