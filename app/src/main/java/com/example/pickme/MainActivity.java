package com.example.pickme;

import android.content.SharedPreferences;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pickme.data.DataUtil;
import com.example.pickme.databinding.ActivityMainBinding;
import com.example.pickme.objects.PickUpPoint;
import com.example.pickme.objects.PreferenceKeys;
import com.example.pickme.objects.Reservation;
import com.example.pickme.objects.Vehicle;
import com.example.pickme.ui.ViewVehicleFragment;
import com.example.pickme.ui.home.HomeFragment;
import com.example.pickme.viewmodels.VehicleViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineCallback;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.location.LocationEngineRequest;
import com.mapbox.android.core.location.LocationEngineResult;
import com.mapbox.geojson.Point;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        IMainActivity, ViewVehicleFragment.OnFragmentInteractionListener {

    ActivityMainBinding mainBinding;
    LocationEngine locationEngine;
    ViewVehicleFragment viewVehicleFragment;
    private LocationListeningCallback callback;
    long DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L;
    long DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataUtil.initVehicles();
        DataUtil.reservations = new ArrayList<>();
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        LocationEngineRequest request = new LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
                .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
                .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME)
                .build();
        locationEngine = LocationEngineProvider.getBestLocationEngine(this);
        callback = new LocationListeningCallback(this);

        locationEngine.requestLocationUpdates(request, callback, getMainLooper());
        locationEngine.getLastLocation(callback);
        init();
    }

    @Override
    public void inflateViewVehicleProductFragment(Vehicle vehicle) {
        viewVehicleFragment = ViewVehicleFragment.newInstance("par", "par");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.intent_vehicle), vehicle);
        viewVehicleFragment.setArguments(bundle);
        transaction.replace(R.id.fragment_container, viewVehicleFragment, getString(R.string.fragment_view_vehicle));
        transaction.addToBackStack(getString(R.string.fragment_view_vehicle));
        transaction.commit();
    }

    @Override
    public void showVehicleSitsDialog(Vehicle vehicle) {

    }

    @Override
    public void reserveSpace(VehicleViewModel vehicleViewModel) {
        SharedPreferences sharedPreferences = this.getSharedPreferences(PreferenceKeys.reservations_key, 0);
        if (vehicleViewModel.getVehicle().getNoOfPassOnBoard() >= vehicleViewModel.getVehicle().getTotalCapacity()){
            shoToast("Vehicle is Already Full");
            return;
        }

        if (!sharedPreferences.contains(PreferenceKeys.active_pickup_point)){
            shoToast("Kindly Select a a Pick Up Point from the list of Vehicle Stops");
            return;
        }
        Gson savedPickupPtGsn = new Gson();
        String savedPickUpPtGsnStr = sharedPreferences.getString(PreferenceKeys.active_pickup_point, "");
        PickUpPoint pickUpPoint = savedPickupPtGsn.fromJson(savedPickUpPtGsnStr, PickUpPoint.class);
        vehicleViewModel.getVehicle().setNoOfBookings(vehicleViewModel.getVehicle().getNoOfBookings()+1);
        viewVehicleFragment.updateVehicle(vehicleViewModel);
        DataUtil.updateVehicle(vehicleViewModel.getVehicle());
        Vehicle vehicle = vehicleViewModel.getVehicle();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Reservation reservation = new Reservation(vehicle, pickUpPoint, 1);
        Gson savedJson = new Gson();
        String savedRsvtnStr = sharedPreferences.getString(vehicle.getRegNumber(), "");
        shoToast(savedRsvtnStr);
        if (!savedRsvtnStr.equals("")){
            reservation = savedJson.fromJson(savedRsvtnStr, Reservation.class);
            reservation.setNumberOfSits(reservation.getNumberOfSits()+1);
        }
        Gson gson = new Gson();
        String reservationJsonString = gson.toJson(reservation);
        editor.putString(vehicle.getRegNumber(), reservationJsonString);

        editor.apply();
        shoToast("Reserved" + reservation);

    }

    @Override
    public void setPickUpPoint(PickUpPoint pickUpPoint) {
        SharedPreferences sharedPreferences = this.getSharedPreferences(PreferenceKeys.reservations_key, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String pickUpPointString = gson.toJson(pickUpPoint);
        editor.putString(PreferenceKeys.active_pickup_point, pickUpPointString);
        editor.apply();
        shoToast("Pick Up Point Set!");
    }

    @Override
    public String calcDist(Point A, Point B) {
        //Double dist = TurfMeasurement.distance(A, B, TurfConstants.UNIT_METERS);
        // return dist.toString();
        shoToast(A.toString().concat(B.toString()));
        return "6 Km from CBD";
    }

    public void init(){
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, homeFragment, getString(R.string.title_home));
        transaction.commit();
    }

    public void initLocationTracking() {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void showFragment(View view){
        Toast.makeText(this, "Am clicked", Toast.LENGTH_SHORT).show();
    }

    private class LocationListeningCallback  implements LocationEngineCallback<LocationEngineResult> {

        private final WeakReference<MainActivity> activityWeakReference;

        LocationListeningCallback(MainActivity activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void onSuccess(LocationEngineResult result) {
            Location lastLocation = result.getLastLocation();
            //Toast.makeText(activityWeakReference.get(),lastLocation.toString(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailure(@NonNull Exception exception) {
            Toast.makeText(activityWeakReference.get(),"Failure getting device location", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(locationEngine != null){
            locationEngine.removeLocationUpdates(callback);
        }
    }

    private void shoToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
