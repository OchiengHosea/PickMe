package com.example.pickme.objects;

import android.content.Context;
import android.location.Location;

import com.example.pickme.R;

public class LocationSetting {
    private Location location;
    private String status;
    private boolean locationSet;

    public LocationSetting() {
    }

    public LocationSetting(Location location, String status, boolean locationSet) {
        this.location = location;
        this.status = status;
        this.locationSet = locationSet;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isLocationSet() {
        return locationSet;
    }

    public void setLocationSet(boolean locationSet) {
        this.locationSet = locationSet;
    }

    public static LocationSetting initLocationSetting(Context context){
        return new LocationSetting(null, context.getString(R.string.loc_setting_init_message), false);
    }
}
