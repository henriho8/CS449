package com.example.cs449project;


public class Earthquake {

    // Magnitude of earthquake
    private double mMagnitude;

    // Location of earthquake
    private String mLocation;

    // Time of earthquake
    private long mTimeInMilliseconds;

    // URL of earthquake
    private String mUrl;

    // Constructs new earthquake objects
    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }

    // Returns magnitude
    public double getMagnitude() {
        return mMagnitude;
    }

    // Returns location
    public String getLocation() {
        return mLocation;
    }

    // Returns time
    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    // Returns URL
    public String getUrl() {
        return mUrl;
    }
}
