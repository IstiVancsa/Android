package com.example.lab1.ui.Location;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Nav_Location extends Fragment {

    private NavLocationViewModel mViewModel;
    EditText locationEditText;
    Button getCoordinatesButton;
    TextView addressTextView;
    private static final int PERMISSION_REQUEST_CODE_LOCATION = 4000;
    FusedLocationProviderClient locationProviderClient;

    String currentLocation = null;

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            currentLocation =  mLastLocation.getLatitude() + "   " + mLastLocation.getLongitude();
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.nav__location_fragment, container, false);
        locationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mViewModel = ViewModelProviders.of(this, new NavLoactionViewModelFactory(getContext())).get(NavLocationViewModel.class);

        locationEditText = root.findViewById(R.id.locationEditText);
        getCoordinatesButton = root.findViewById(R.id.submitLocationButton);
        addressTextView = root.findViewById(R.id.addressTextView);
        addressTextView.setMovementMethod(new ScrollingMovementMethod());

        getCoordinatesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = locationEditText.getText().toString();
                GeoLocation geoLocation = new GeoLocation();

                geoLocation.GetAddress(address, getContext(), new GeoMessageHandler());

                getLastLocation();
                ShowCurrentLocation();
                mViewModel.hideKeyboard(getActivity());
            }
        });

        return root;
    }

    private void ShowCurrentLocation() {

        addressTextView.setText(addressTextView.getText() + "\n\n\nCurrent Address\nLatitude And Longitude\n\n" + currentLocation);
    }

    private class GeoMessageHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            String address;
            Bundle bundle = msg.getData();
            switch (msg.what){
                case 1:
                    address = bundle.getString("address");
                    break;
                case 2:
                    String location = bundle.getString("location");
                    address = "Address   :   "  + location + "\n\n\n No Such Place On This Planet\n";
                    break;
                default:
                    address = null;
            }

            addressTextView.setText(address);
        }
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(getContext(), "Please allow us accessing your location. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE_LOCATION);
        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Toast.makeText(getContext(), "Please allow us accessing your location. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE_LOCATION);
        }
    }
    private void requestPermissions(){
        ActivityCompat.requestPermissions(
                getActivity(),
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_REQUEST_CODE_LOCATION
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
                ShowCurrentLocation();
            }
        }
    }

    private void getLastLocation(){
        if (mViewModel.checkPermissions()) {
            if (mViewModel.isLocationEnabled()) {
                locationProviderClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    currentLocation =  location.getLatitude() + "\n" + location.getLongitude();
                                    ShowCurrentLocation();
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(getActivity(), "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        locationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        locationProviderClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }
}
