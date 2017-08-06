package com.example.ridowanahmed.childlocator.HelperClass;

import android.location.Location;

/**
 * Created by Ridowan Ahmed on 0029, July, 29, 2017.
 */

public class MyLocation {

    double latitude;
    double longitude;
    long time;

    public MyLocation() {
    }

    public MyLocation(double latitude, double longitude, long time) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public long getTime() {
        return time;
    }
}
