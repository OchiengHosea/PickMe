package com.example.pickme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pickme.databinding.ActivityLocationSettingsBinding;
import com.example.pickme.objects.LocationSetting;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;

import java.util.List;

public class LocationSettingsActivity extends AppCompatActivity implements PermissionsListener {
    private static final int REQUEST_CHECK_SETTING = 100;
    ActivityLocationSettingsBinding binding;
    LocationSetting locationSetting;
    LocationRequest locationRequest;

    // Permissions management via mapbox
    PermissionsManager permissionsManager = new PermissionsManager(this);
    PermissionsListener permissionsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationSetting = LocationSetting.initLocationSetting(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_location_settings);
        binding.setLocationSetting(locationSetting);

        permissionsListener = new PermissionsListener() {
            @Override
            public void onExplanationNeeded(List<String> permissionsToExplain) {
                Toast.makeText(LocationSettingsActivity.this, "Location is needed to show you nearby bus stops", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPermissionResult(boolean granted) {
                if (granted) goToMain();

                remainInLaubcer();
            }
        };

        createLocationRequest();
        checkLocationSettings();
    }

    private void createLocationRequest(){
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(2000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void remainInLaubcer() {
        Toast.makeText(LocationSettingsActivity.this, "Permissions denied", Toast.LENGTH_LONG).show();
    }

    private void checkLocationSettings() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                Toast.makeText(LocationSettingsActivity.this, "df", Toast.LENGTH_LONG).show();
                locationSetting.setStatus("Settings are Ok, trying to get device location");
                checkPermissions();

            }
        });


        task.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException){
                    try {
                        ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                        resolvableApiException.startResolutionForResult(LocationSettingsActivity.this, REQUEST_CHECK_SETTING);

                    } catch (IntentSender.SendIntentException sendEx){
                        //
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, requestCode+" "+resultCode, Toast.LENGTH_LONG).show();
        if(requestCode == REQUEST_CHECK_SETTING){
            if (resultCode == RESULT_OK){
                checkPermissions();
            }else{
                Toast.makeText(LocationSettingsActivity.this, "Enjoy The Homescreen!, No location provided", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {

    }

    @Override
    public void onPermissionResult(boolean granted) {

    }

    public void goToMain() {
        Toast.makeText(LocationSettingsActivity.this, "Welcome", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LocationSettingsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void checkPermissions() {
        if (PermissionsManager.areLocationPermissionsGranted(this)){
            goToMain();
        }else{
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

}
