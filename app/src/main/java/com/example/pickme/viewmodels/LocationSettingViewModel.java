package com.example.pickme.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.pickme.objects.LocationSetting;

public class LocationSettingViewModel extends BaseObservable {
    private LocationSetting locationSetting;

    @Bindable
    public LocationSetting getLocationSetting() {
        return locationSetting;
    }


    public void setLocationSetting(LocationSetting locationSetting) {
        this.locationSetting = locationSetting;
        notifyPropertyChanged(BR.locationSetting);
    }
}
