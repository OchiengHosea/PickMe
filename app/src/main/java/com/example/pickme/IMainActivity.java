package com.example.pickme;

import com.example.pickme.objects.PickUpPoint;
import com.example.pickme.objects.Vehicle;
import com.example.pickme.viewmodels.VehicleViewModel;
import com.mapbox.geojson.Point;

public interface IMainActivity {
    void inflateViewVehicleProductFragment(Vehicle vehicle);
    void showVehicleSitsDialog(Vehicle vehicle);
    void reserveSpace(VehicleViewModel vehicleViewModel);
    void setPickUpPoint(PickUpPoint pickUpPoint);
    String calcDist(Point A, Point B);

}
