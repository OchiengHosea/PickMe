package com.example.pickme.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.pickme.BR;
import com.example.pickme.objects.Vehicle;

public class VehicleViewModel extends BaseObservable {
    private Vehicle vehicle;

    @Bindable
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        notifyPropertyChanged(BR.vehicle);
    }

}
