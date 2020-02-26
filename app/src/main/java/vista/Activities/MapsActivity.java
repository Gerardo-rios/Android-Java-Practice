package vista.Activities;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lostzone.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Controller.LeerArchivosParaMaps;
import modelo.Ruta;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    Button satelite, terreno, hibrido;
    LeerArchivosParaMaps lapm = new LeerArchivosParaMaps(this);
    List<Ruta> ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        componentes();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ls = lapm.Obtener_rutas();
    }

    private void componentes(){
        satelite = findViewById(R.id.btn_satelite);
        terreno = findViewById(R.id.btn_terreno);
        hibrido = findViewById(R.id.btn_hibrido);
        satelite.setOnClickListener(this);
        terreno.setOnClickListener(this);
        hibrido.setOnClickListener(this);
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
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.setMyLocationEnabled(true);

        LatLng sydney = new LatLng(-4.013377, -79.201160);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Mi JATO").snippet("Aqui vivo e inicia la ruta"));
        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.inicio)) para poner icono
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        float zoomLevel = 10.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));
        Polyline polyline1 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(R.color.colorAccent));
        List<LatLng> lg = new ArrayList<>();
        for (int i = 0; i< ls.size(); i++){
            LatLng lgp = new LatLng(ls.get(i).getLati(), ls.get(i).getLongi());
            mMap.addMarker(new MarkerOptions().position(lgp).title(ls.get(i).getTitle()) );
            lg.add(lgp);
        }
        polyline1.setPoints(lg);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_satelite:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;

            case R.id.btn_terreno:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;

            case R.id.btn_hibrido:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
        }

    }
}
