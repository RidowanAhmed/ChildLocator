package com.example.ridowanahmed.childlocator;


import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.example.ridowanahmed.childlocator.HelperClass.ChildInformation;
import com.example.ridowanahmed.childlocator.Registration.ParentLoginActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ParentActivity extends FragmentActivity implements OnMapReadyCallback {
    private String mobileNumber;
    private GoogleMap mMap;
    DatabaseReference childData;
    ChildInformation mChildInformation;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        sharedPreferences = ParentActivity.this.getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);

        mobileNumber = sharedPreferences.getString(getString(R.string.PARENT_MOBILE), "");
        childData = FirebaseDatabase.getInstance().getReference("Children").child(mobileNumber);

        childData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    mChildInformation = dataSnapshot.getValue(ChildInformation.class);
                    LatLng latLng = new LatLng(mChildInformation.getLatitude(), mChildInformation.getLongitude());

                    Log.e("Lati " + mChildInformation.getLatitude() , "Longi " + mChildInformation.getLongitude());

                    String title = mChildInformation.getChildName();
                    MarkerOptions locationMarker = new MarkerOptions().position(latLng).title(title);
                    locationMarker.snippet(calculateTime(mChildInformation.getTime()));

                    mMap.addMarker(locationMarker).showInfoWindow();
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.animateCamera(CameraUpdateFactory.zoomBy(14));

                    Toast toast = Toast.makeText(getApplicationContext(), "Locating " + title, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 30);
                    toast.show();
                } else {
                    Toast.makeText(getApplicationContext(), "Can't find your children. Try again later", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    private String calculateTime(long timeInMillis){
        timeInMillis -= System.currentTimeMillis();
        int hours = (int) ((timeInMillis / (1000 * 60 * 60)));
        int minutes = (int) ((timeInMillis / (1000 * 60)) % 60);
        int seconds = (int) ((timeInMillis / 1000) % 60);
        return hours+":"+minutes+":"+seconds;
    }
}
