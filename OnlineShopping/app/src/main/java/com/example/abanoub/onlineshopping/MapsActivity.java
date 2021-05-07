package com.example.abanoub.onlineshopping;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locManager;
    myLocationListener locListener;
    ImageView imgGetLocation, imgSetLocation;
    EditText editTxt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locListener = new myLocationListener(getApplicationContext());
        imgGetLocation = (ImageView)findViewById(R.id.imgMyLocation1);
        imgSetLocation = (ImageView)findViewById(R.id.imgMyLocation);
        editTxt1 = (EditText)findViewById(R.id.txtMyLocation);
        try {
            //Request updates every 600000 ms and 0 meters via GPS provider
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 600000, 0, locListener);
        }
        catch (SecurityException ex)
        {
            Toast.makeText(getApplicationContext(), "You are not allowed to access the current location", Toast.LENGTH_LONG).show();
        }

        imgSetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cart = new Intent(MapsActivity.this, Activity_Cart.class);
                cart.putExtra("Address", editTxt1.getText().toString());
                cart.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(cart);
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Zoom Map to Cairo
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.04441960, 31.235711600), 8));
        imgGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Clear map from any markers
                mMap.clear();
                //Geocoder to convert Lng/Lat to address
                Geocoder coder = new Geocoder(getApplicationContext());
                //List of Possible addresses
                List<Address> addressList;
                Location loc = null;
                try {
                    //Get My Last known Location
                    loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }
                catch (SecurityException ex)
                {
                    Toast.makeText(getApplicationContext(), "You are not allowed to access the current location", Toast.LENGTH_LONG).show();
                }
                if (loc != null)
                {
                    //Get my current loc (lng and lat)into LatLng Object
                    LatLng myPosition = new LatLng(loc.getLatitude(), loc.getLongitude());
                    try {
                        //Get List of Addresses using geocoder
                        addressList = coder.getFromLocation(myPosition.latitude, myPosition.longitude, 1);
                        if (!addressList.isEmpty()) {
                            String address = "";
                            //Construct address into address String
                            for (int i = 0; i <= addressList.get(0).getMaxAddressLineIndex(); i++)
                                address += addressList.get(0).getAddressLine(i) + ", ";
                            //Add marker referring to my position on map
                            mMap.addMarker(new MarkerOptions().position(myPosition).title("My location").snippet(address)).setDraggable(true);
                            //Put address into address edittext
                            editTxt1.setText(address);
                        }
                    }
                    catch (IOException e)
                    {// if network not available
                        mMap.addMarker(new MarkerOptions().position(myPosition).title("My location"));
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 15));
                }
                else
                {
                    //If No Location is determined
                    Toast.makeText(getApplicationContext(), "Please wait until your position is determined", Toast.LENGTH_LONG).show();
                }

            }
        });

        //Add on marker drag Listener To map object
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Geocoder coder = new Geocoder(getApplicationContext());
                List<Address> addressList;
                try {
                    addressList = coder.getFromLocation(marker.getPosition().latitude, marker.getPosition().longitude, 1);
                    if (!addressList.isEmpty()) {
                        String address = "";
                        for (int i = 0; i <= addressList.get(0).getMaxAddressLineIndex(); i++)
                            address += addressList.get(0).getAddressLine(i) + ", ";
                        editTxt1.setText(address);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "No address for this Location", Toast.LENGTH_LONG).show();
                    }
                }
                catch (IOException e)
                {
                    Toast.makeText(getApplicationContext(), "Can't get the address, Check your network", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }
}
