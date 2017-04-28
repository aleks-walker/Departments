package com.example.alexw.departments;


import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button cleanButton;
    private ImageButton actionbar;
    private PopupMenu popup;
    private MenuItem mapTypeHybridMenuItem;
    private AutoCompleteTextView autoCompleteTextView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<Departments> departmentsData;
    private ArrayList<String> autoCompleteTextViewData;
    private View.OnClickListener onClickListener;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference().child("Departments");

        cleanButton = (Button) findViewById(R.id.cleanButton);
        actionbar = (ImageButton)findViewById(R.id.actionbarImageButton);

        popup = new PopupMenu(MapsActivity.this, actionbar);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu, popup.getMenu());
        mapTypeHybridMenuItem = popup.getMenu().findItem(R.id.map_type_hybrid_menu_item);

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.cleanButton:
                        autoCompleteTextView.setText("");
                        break;
                    case R.id.actionbarImageButton:
                        popup.show();
                        break;
                }
            }
        };
        cleanButton.setOnClickListener(onClickListener);
        actionbar.setOnClickListener(onClickListener);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mistake_report_menu_item:
                        sendMessage();
                        break;
                    case R.id.map_type_hybrid_menu_item:
                        if(!item.isChecked() ){
                            item.setChecked(true);
                            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        } else {
                            item.setChecked(false);
                            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        }
                        break;
                    case R.id.kgma_item:
                        goKGMA();
                }
                return true;
            }
        });

        /*OrientationEventListener orientationEventListener = new OrientationEventListener(getApplication()) {
            @Override
            public void onOrientationChanged(int i) {
                Configuration newConfig = new Configuration();
                if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE
                        && mMap.getMapType() == GoogleMap.MAP_TYPE_HYBRID) {
                    mapTypeHybridMenuItem.setChecked(true);
                } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT
                        && mMap.getMapType() == GoogleMap.MAP_TYPE_HYBRID) {
                    mapTypeHybridMenuItem.setChecked(true);
                } else {
                    mapTypeHybridMenuItem.setChecked(false);
                }
            }
        };
        orientationEventListener.canDetectOrientation();
        orientationEventListener.enable();*/
    }

    private void sendMessage() {
        Intent sentMessage = new Intent(Intent.ACTION_SENDTO);
        sentMessage.setData(Uri.parse("mailto:feedbackmessagetodeveloper@gmail.com"));
        sentMessage.putExtra(Intent.EXTRA_SUBJECT, "Сообщение об ошибке");
        if (sentMessage.resolveActivity(getPackageManager()) != null) {
            startActivity(sentMessage);
        }
    }

    private void goKGMA() {
        Intent goKGMA = new Intent(Intent.ACTION_VIEW);
        goKGMA.setData(Uri.parse("https://vk.com/lovekgma"));
        startActivity(goKGMA);
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
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));

        departmentsData = new ArrayList<>();
        autoCompleteTextViewData = new ArrayList<>();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Departments departments = dataSnapshot.getValue(Departments.class);
                departmentsData.add(departments);
                autoCompleteTextViewData.add(departments.getName());

                for (Departments department : departmentsData){
                    LatLng latLng = new LatLng(department.getLat(), department.getLng());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(department.getName()));
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        arrayAdapter = new ArrayAdapter<>(MapsActivity.this, R.layout.support_simple_spinner_dropdown_item
                , autoCompleteTextViewData);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setThreshold(1);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                for (Departments department : departmentsData) {
                    if (department.getName().equals(charSequence.toString())) {
                        LatLng coordinates = new LatLng(department.getLat(), department.getLng());
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 17));
                        //костыль, чтобы отображать название кафедры над маркером
                        Marker marker = mMap.addMarker(new MarkerOptions().position(coordinates).title(department.getName()));
                        marker.showInfoWindow();
                        break;
                    }
                }

            }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        autoCompleteTextView.addTextChangedListener(textWatcher);


    }


}
