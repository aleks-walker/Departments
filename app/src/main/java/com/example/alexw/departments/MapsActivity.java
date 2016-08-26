package com.example.alexw.departments;


import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button hybridViewBtn;
    private Button normalViewBtn;
    private Button infoButton;
    private AutoCompleteTextView autoCompleteTextView;
    private Switch mapTypeSwitch;

    Marker army;
    Marker obsGyn1;
    Marker obsGyn2;
    Marker anest;
    Marker otorhin;
    Marker ophtalm;
    Marker roentg;
    Marker neurol;
    Marker edocr;
    Marker propPed;
    Marker psych;
    Marker nurse;
    Marker family;
    Marker trauma;
    Marker urol;
    Marker hospThera;
    Marker haema;
    Marker neonat;
    Marker facThera;
    Marker facTheraCard;

    Marker propSurg;
    Marker facPed;
    Marker surgComb;
    Marker tb;
    Marker mamakeev;
    Marker infect;
    Marker facSurg;

    Marker fourthCorp;
    Marker mainCorp;
    Marker mortuaryMarker;


    View.OnClickListener btnTestListener;
    GoogleMap.OnMapClickListener onMapClickListener;
    GoogleMap.OnMarkerClickListener onMarkerClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        infoButton = (Button) findViewById(R.id.infoButton);
        mapTypeSwitch = (Switch)findViewById(R.id.maptypeswitch);

        mapTypeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked() ){
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });


        btnTestListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {

                    case R.id.infoButton:
                        autoCompleteTextView.setText("");
                        break;
                }

            }
        };

        infoButton.setOnClickListener(btnTestListener);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.departmentsArray, android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);
        //registerForContextMenu(autoCompleteTextView);


    }


    /**
     * I will turn this feature on after I figure out how to make it work without having to repeat
     * code 50 times
     */
   /* final int MENU_DEPARTMENT_PHYSIOLOGY = 1;
    final int MENU_DEPARTMENT_PATHOPHYSIOLOGY = 2;


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, MENU_DEPARTMENT_PHYSIOLOGY, 0, R.string.physiology);
        menu.add(0, MENU_DEPARTMENT_PATHOPHYSIOLOGY, 0, R.string.pathological_physiology);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_DEPARTMENT_PHYSIOLOGY:
                autoCompleteTextView.setText(R.string.physiology);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                break;
            case MENU_DEPARTMENT_PATHOPHYSIOLOGY:
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));
                break;

        }
        return super.onContextItemSelected(item);
    }*/

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
        /*mMap.setMyLocationEnabled(true);*/
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);


        onMarkerClickListener = new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        };


        mMap.setOnMarkerClickListener(onMarkerClickListener);


        LatLng mortuary = Constants.PATHOPHYSILOGY_COORDINATES;
        mortuaryMarker = mMap.addMarker(new MarkerOptions().position(mortuary).title(getString(R.string.mortuary_title)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mortuary, 17));

        LatLng physiology = Constants.PHYSIOLOGY_COORDINATES;
        mainCorp = mMap.addMarker(new MarkerOptions().position(physiology).title(getString(R.string.main_corp)));

        final LatLng fourthCorpus = Constants.FOURTH_CORPUS_COORDINATES;
        fourthCorp = mMap.addMarker(new MarkerOptions().position(fourthCorpus).title(getString(R.string.fourth_corpus)));















        /**
         * ATTENTION! Your eyes may start bleeding after you see code below. Do it on your own risk.
         */



















        army = mMap.addMarker(new MarkerOptions().position(Constants.ARMY).title("Кафедра военно - медицинской подготовки и экстремальной медицины"));
        obsGyn1 = mMap.addMarker(new MarkerOptions().position(Constants.OBSTETRICS_AND_GYNECOLOGY_1_COORDINATES).title(getString(R.string.obstetrics_and_gynecology1_title)));
        obsGyn2 = mMap.addMarker(new MarkerOptions().position(Constants.OBSTETRICS_AND_GYNECOLOGY_2_COORDINATES).title(getString(R.string.obstetrics_and_gynecology2_title)));
        anest = mMap.addMarker(new MarkerOptions().position(Constants.ANESTHESIOLOGY_COORDINATES).title("Кафедра анестезиологии, реанимации и интенсивной терапии"));
        otorhin = mMap.addMarker(new MarkerOptions().position(Constants.OTORHINOLARYNGOLOGY_COORDINATES).title("Кафедра оториноларингологии"));
        ophtalm = mMap.addMarker(new MarkerOptions().position(Constants.OPHTHALMOLOGY_COORDINATES).title("Кафедра офтальмологии"));
        roentg = mMap.addMarker(new MarkerOptions().position(Constants.ROENTGENOLOGY).title("Кафедра лучевой диагностики и терапии"));
        neurol = mMap.addMarker(new MarkerOptions().position(Constants.NEUROLOGY).title("Кафедра неврологии с курсом медицинской генетики"));
        edocr = mMap.addMarker(new MarkerOptions().position(Constants.ENDOCRINOLOGY).title("Кафедра пропедевтики внутренних болезней с курсом эндокринологии"));
        propPed = mMap.addMarker(new MarkerOptions().position(Constants.PROPED_PEDIATRICS).title("Кафедра пропедевтики детских болезней"));
        psych = mMap.addMarker(new MarkerOptions().position(Constants.PSYCHIATRY).title("Кафедра психиатрии, психотерапии и наркологии"));
        nurse = mMap.addMarker(new MarkerOptions().position(Constants.NURSE).title("Стоматологический центр"));
        family = mMap.addMarker(new MarkerOptions().position(Constants.FAMILY_MEDICINE).title("Кафедра терапии общей практики с курсом семейной медицины"));
        trauma = mMap.addMarker(new MarkerOptions().position(Constants.TRAUMATOLOGY).title("Кафедра травматологии, ортопедии и экстремальной хирургии"));
        urol = mMap.addMarker(new MarkerOptions().position(Constants.UROLOGY).title("Кафедра урологии и андрологии до- и последипломного обучения"));
        hospThera = mMap.addMarker(new MarkerOptions().position(Constants.HOSPITAL_THERAPY).title("Кафедра госпитальной терапии, профпатологии с курсом гематологии"));
        haema = mMap.addMarker(new MarkerOptions().position(Constants.HAEMATOLOGY).title("Центр гематологии"));
        neonat = mMap.addMarker(new MarkerOptions().position(Constants.HOSPITAL_PEDIATRICS_AND_NEONATOLOGY).title("Кафедра госпитальной педиатрии с курсом неонатологии"));
        facThera = mMap.addMarker(new MarkerOptions().position(Constants.FACULTY_THERAPY).title("Кафедра факультетской терапии"));
        facTheraCard = mMap.addMarker(new MarkerOptions().position(Constants.FACULTY_THERAPY_CARDIOLOGY).title("Кафедра факультетской терапии (кардиология)"));


        propSurg = mMap.addMarker(new MarkerOptions().position(Constants.PROPED_SURGERY).title("Кафедра пропедхирургии"));
        facPed = mMap.addMarker(new MarkerOptions().position(Constants.FACULTY_PEDIATRICS).title("Кафедра факультетской педиатрии"));
        surgComb = mMap.addMarker(new MarkerOptions().position(Constants.SURGERY_COMBUST).title("Кафедра хирургии общей практики с курсом комбустиологии"));
        tb = mMap.addMarker(new MarkerOptions().position(Constants.TB).title("Кафедра фтизиатрии"));
        mamakeev = mMap.addMarker(new MarkerOptions().position(Constants.MAMAKEEV).title("Кафедра госпитальной хирургии с курсом оперативной хирургии им. академика М.М. Мамакеева"));
        infect = mMap.addMarker(new MarkerOptions().position(Constants.INFECTION).title("Кафедра инфекционных болезней"));
        facSurg = mMap.addMarker(new MarkerOptions().position(Constants.FAC_SURGERY).title("Кафедра факультетской хирургии"));


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                switch (charSequence.toString()) {
                    case "Кафедра патологической физиологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));
                        mortuaryMarker.showInfoWindow();
                        break;
                    case "Кафедра патологической анатомии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));
                        mortuaryMarker.showInfoWindow();
                        break;
                    case "Кафедра гистологии, цитологии, эмбриологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));
                        mortuaryMarker.showInfoWindow();
                        break;
                    case "Кафедра нормальной и топографической анатомии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));
                        mortuaryMarker.showInfoWindow();
                        break;
                    case "Кафедра судебной медицины и правоведения":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PATHOPHYSILOGY_COORDINATES, 17));
                        mortuaryMarker.showInfoWindow();
                        break;
                    case "Кафедра базисной и клинической фармакологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        mortuaryMarker.showInfoWindow();
                        break;
                    case "Кафедра военно - медицинской подготовки и экстремальной медицины":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.ARMY, 17));
                        army.showInfoWindow();
                        break;


                    case "Кафедра физики, математики, информатики и компьютерных технологий":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        mainCorp.showInfoWindow();
                        break;
                    case "Кафедра микробиологии, вирусологии и иммунологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        mainCorp.showInfoWindow();
                        break;
                    case "Кафедра фундаментальной и клинической физиологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        mainCorp.showInfoWindow();
                        break;
                    case "Кафедра фундаментальных дисциплин":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        mainCorp.showInfoWindow();
                        break;
                    case "Кафедра общественного здоровья и здравоохранения":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        mainCorp.showInfoWindow();
                        break;
                    case "Кафедра общей и клинической эпидемиологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        mainCorp.showInfoWindow();
                        break;
                    case "Кафедра физического воспитания":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PHYSIOLOGY_COORDINATES, 17));
                        mainCorp.showInfoWindow();
                        break;


                    case "Кафедра гигиенических дисциплин":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        fourthCorp.showInfoWindow();
                        break;
                    case "Кафедра общей гигиены":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        fourthCorp.showInfoWindow();
                        break;
                    case "Кафедра иностранных и латинского языка":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        fourthCorp.showInfoWindow();
                        break;
                    case "Кафедра кыргызского и русского языков":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        fourthCorp.showInfoWindow();
                        break;
                    case "Кафедра клинической реабилитологии и физиотерапии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        fourthCorp.showInfoWindow();
                        break;
                    case "Кафедра философии и общественных наук":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        fourthCorp.showInfoWindow();
                        break;
                    case "Кафедра фармакогнозии и химии лекарственных средств":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        fourthCorp.showInfoWindow();
                        break;
                    case "Кафедра управления и экономики фармации, технологии лекарственных средств":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FOURTH_CORPUS_COORDINATES, 19));
                        fourthCorp.showInfoWindow();
                        break;


                    case "Кафедра акушерства и гинекологии №1":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.OBSTETRICS_AND_GYNECOLOGY_1_COORDINATES, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.OBSTETRICS_AND_GYNECOLOGY_1_COORDINATES).title(getString(R.string.obstetrics_and_gynecology1_title)));*/
                        obsGyn1.showInfoWindow();
                        break;
                    case "Кафедра акушерства и гинекологии №2":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.OBSTETRICS_AND_GYNECOLOGY_2_COORDINATES, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.OBSTETRICS_AND_GYNECOLOGY_2_COORDINATES).title(getString(R.string.obstetrics_and_gynecology2_title)));*/
                        obsGyn2.showInfoWindow();
                        break;
                    case "Кафедра анестезиологии, реанимации и интенсивной терапии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.ANESTHESIOLOGY_COORDINATES, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.ANESTHESIOLOGY_COORDINATES).title("Кафедра анестезиологии, реанимации и интенсивной терапии"));*/
                        anest.showInfoWindow();
                        break;
                    case "Кафедра оториноларингологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.OTORHINOLARYNGOLOGY_COORDINATES, 18));
                       /* mMap.addMarker(new MarkerOptions().position(Constants.OTORHINOLARYNGOLOGY_COORDINATES).title("Кафедра оториноларингологии"));*/
                        otorhin.showInfoWindow();
                        break;
                    case "Кафедра офтальмологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.OPHTHALMOLOGY_COORDINATES, 18));
                       /* mMap.addMarker(new MarkerOptions().position(Constants.OPHTHALMOLOGY_COORDINATES).title("Кафедра офтальмологии"));*/
                        ophtalm.showInfoWindow();
                        break;
                    case "Кафедра лучевой диагностики и терапии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.ROENTGENOLOGY, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.ROENTGENOLOGY).title("Кафедра лучевой диагностики и терапии"));*/
                        roentg.showInfoWindow();
                        break;
                    case "Кафедра неврологии с курсом медицинской генетики":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.NEUROLOGY, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.NEUROLOGY).title("Кафедра неврологии с курсом медицинской генетики"));*/
                        neurol.showInfoWindow();
                        break;
                    case "Кафедра пропедевтики внутренних болезней с курсом эндокринологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.ENDOCRINOLOGY, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.ENDOCRINOLOGY).title("Кафедра пропедевтики внутренних болезней с курсом эндокринологии"));*/
                        edocr.showInfoWindow();
                        break;
                    case "Кафедра пропедевтики детских болезней":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PROPED_PEDIATRICS, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.PROPED_PEDIATRICS).title("Кафедра пропедевтики детских болезней"));*/
                        propPed.showInfoWindow();
                        break;
                    case "Кафедра психиатрии, психотерапии и наркологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PSYCHIATRY, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.PSYCHIATRY).title("Кафедра психиатрии, психотерапии и наркологии"));*/
                        psych.showInfoWindow();
                        break;
                    case "Кафедра сестринского дела":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.NURSE, 18));
                        Marker marker = mMap.addMarker(new MarkerOptions().position(Constants.NURSE).title("Кафедра сестринского дела"));
                        marker.showInfoWindow();
                        break;
                    case "Кафедра ортопедической стоматологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.NURSE, 18));
                        Marker marker1 = mMap.addMarker(new MarkerOptions().position(Constants.NURSE).title("Кафедра ортопедической стоматологии"));
                        marker1.showInfoWindow();
                        break;
                    case "Кафедра детской стоматологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.NURSE, 18));
                        Marker marker2 = mMap.addMarker(new MarkerOptions().position(Constants.NURSE).title("Кафедра детской стоматологии"));
                        marker2.showInfoWindow();
                        break;
                    case "Кафедра терапевтической стоматологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.NURSE, 18));
                        Marker marker3 = mMap.addMarker(new MarkerOptions().position(Constants.NURSE).title("Кафедра терапевтической стоматологии"));
                        marker3.showInfoWindow();
                        break;
                    case "Кафедра хирургической стоматологии и челюстно - лицевой хирургии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.NURSE, 18));
                        Marker marker4 = mMap.addMarker(new MarkerOptions().position(Constants.NURSE).title("Кафедра хирургической стоматологии и челюстно - лицевой хирургии"));
                        marker4.showInfoWindow();
                        break;
                    case "Кафедра терапии общей практики с курсом семейной медицины":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FAMILY_MEDICINE, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.FAMILY_MEDICINE).title("Кафедра терапии общей практики с курсом семейной медицины"));*/
                        family.showInfoWindow();
                        break;
                    case "Кафедра травматологии, ортопедии и экстремальной хирургии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.TRAUMATOLOGY, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.TRAUMATOLOGY).title("Кафедра травматологии, ортопедии и экстремальной хирургии"));*/
                        trauma.showInfoWindow();
                        break;
                    case "Кафедра урологии и андрологии до- и последипломного обучения":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.UROLOGY, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.UROLOGY).title("Кафедра урологии и андрологии до- и последипломного обучения"));*/
                        urol.showInfoWindow();
                        break;
                    case "Кафедра госпитальной терапии, профпатологии с курсом гематологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.HOSPITAL_THERAPY, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.HOSPITAL_THERAPY).title("Кафедра госпитальной терапии, профпатологии с курсом гематологии"));*/
                        hospThera.showInfoWindow();
                        break;
                    case "Центр гематологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.HAEMATOLOGY, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.HAEMATOLOGY).title("Центр гематологии"));*/
                        haema.showInfoWindow();
                        break;
                    case "Кафедра госпитальной педиатрии с курсом неонатологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.HOSPITAL_PEDIATRICS_AND_NEONATOLOGY, 18));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.HOSPITAL_PEDIATRICS_AND_NEONATOLOGY).title("Кафедра госпитальной педиатрии с курсом неонатологии"));*/
                        neonat.showInfoWindow();
                        break;
                    case "Кафедра факультетской терапии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FACULTY_THERAPY, 17));
                        /*mMap.addMarker(new MarkerOptions().position(Constants.FACULTY_THERAPY).title("Кафедра факультетской терапии"));
                        mMap.addMarker(new MarkerOptions().position(Constants.FACULTY_THERAPY_CARDIOLOGY).title("Кафедра факультетской терапии (кардиология)"));*/
                        facThera.showInfoWindow();
                        break;
                    case "Кафедра пропедхирургии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.PROPED_SURGERY, 18));
                        propSurg.showInfoWindow();
                        break;
                    case "Кафедра факультетской педиатрии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FACULTY_PEDIATRICS, 18));
                        facPed.showInfoWindow();
                        break;
                    case "Кафедра хирургии общей практики с курсом комбустиологии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.SURGERY_COMBUST, 18));
                        surgComb.showInfoWindow();
                        break;
                    case "Кафедра фтизиатрии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.TB, 18));
                        tb.showInfoWindow();
                        break;
                    case "Кафедра госпитальной хирургии с курсом оперативной хирургии им. академика М.М. Мамакеева":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.MAMAKEEV, 18));
                        mamakeev.showInfoWindow();
                        break;
                    case "Кафедра инфекционных болезней":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.INFECTION, 18));
                        infect.showInfoWindow();
                        break;
                    case "Кафедра факультетской хирургии":
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.FAC_SURGERY, 18));
                        facSurg.showInfoWindow();
                        break;
                }
            }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        autoCompleteTextView.addTextChangedListener(textWatcher);


    }


}
