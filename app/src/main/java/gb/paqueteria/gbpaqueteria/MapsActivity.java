package gb.paqueteria.gbpaqueteria;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    private ArrayList<Marker> tmpRealTimeMarkers = new ArrayList<>();
    private ArrayList<Marker> realTimeMarkers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //creasmo una instancia   con el nombre mDatabase
        mDatabase = FirebaseDatabase.getInstance().getReference();
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
        // punto donde esta ubicadad la clinica
        LatLng jalapa = new LatLng(14.4971784, -90.0672485);
        // pasamos la posicion  que este caso la tenemos guardad en el objeto jalapa y le damos un titulo
        mMap.addMarker(new MarkerOptions().position(jalapa).title("Tienda KEDATEENCASA"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(jalapa));
        // le decimo que la posicion de la camara se mueva al objeto jalapa
        CameraPosition camera =  new CameraPosition.Builder()
                .target(jalapa)
                .zoom(18)
                .bearing(90)
                .tilt(45)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
        // usamo la referencia mDatabase y le decimos de que hijo queremos traer informacion
        // para este caso serra destinos
        mDatabase.child("destinos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (Marker marker:realTimeMarkers)
                {
                    // este for nos servira para remover los marcadores que se allan borrado en tiempo real
                    marker.remove();

                }

                // este for  rellena el mapa pero antes usa la clase donde tenemos lo setter y getter
                // de esta manera llnamos el mapa
                for (DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    MapsPojo mp = snapshot.getValue(MapsPojo.class);
                    Double latitud  = mp.getLatitud();
                    Double longitud = mp.getLongitud();
                    String titulo = mp.getCodigo();
                    String telefono = mp.getTelefono();
                    // es esta parte le ponemos los datos que queremos que se  muestren
                    // an tocar el marker lurho lod llamaremos en el markerOptions
                    String paquete = "No.Paquete "+ titulo;
                    String telefono1 = "No. Tel: " + telefono;




                    // creamos una instancia de markeroptions
                    MarkerOptions markerOptions = new MarkerOptions();
                    /// aqui es donde cargamos los datos que queremos pasarle en este caso el titutlo y el telefono
                    markerOptions.position(new LatLng(latitud,longitud)).title(paquete).snippet(telefono1);


                    tmpRealTimeMarkers.add(mMap.addMarker(markerOptions));

                }

                realTimeMarkers.clear();
                realTimeMarkers.addAll(tmpRealTimeMarkers);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
