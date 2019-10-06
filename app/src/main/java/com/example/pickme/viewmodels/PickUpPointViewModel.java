package com.example.pickme.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.pickme.objects.PickUpPoint;
import com.example.pickme.objects.Vehicle;

public class PickUpPointViewModel extends BaseObservable {
    private Vehicle vehicle;
    private PickUpPoint pickUpPoint;

    @Bindable
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Bindable
    public PickUpPoint getPickUpPoint() {
        return pickUpPoint;
    }

    public void setPickUpPoint(PickUpPoint pickUpPoint) {
        this.pickUpPoint = pickUpPoint;
    }

    public void calcDistFrom(){

    }
}
