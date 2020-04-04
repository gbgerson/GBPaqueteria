package gb.paqueteria.gbpaqueteria;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity {
    Button btnGPS, btnGuardar;
    EditText edtLat, edtLong,edtcodigo, edtbillete, edttelefono;
    DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_layout);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        //Botones
        btnGPS      = (Button) findViewById(R.id.button);
        btnGuardar  = (Button) findViewById(R.id.btnEnviar);
        //Editex
        edtLat      = (EditText) findViewById(R.id.txvLat);
        edtLong     = (EditText) findViewById(R.id.txvLog);
        edtcodigo   = (EditText) findViewById(R.id.txvNo);
        edtbillete  = (EditText) findViewById(R.id.txvBillete);
        edttelefono = (EditText) findViewById(R.id.txvTelefono);

        //Boton para enviar los datos a Firebase
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latitud  = edtLat.getText().toString();
                String longitud = edtLong.getText().toString();
                String codigo   = edtcodigo.getText().toString();
                String billete  = edtbillete.getText().toString();
                String telefono = edttelefono.getText().toString();

                if (TextUtils.isEmpty(latitud)){
                    Toast.makeText(getApplicationContext(),"Genere de nuevo su ubicación", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(codigo))
                {
                    Toast.makeText(getApplicationContext(),"Ingrese un Codigo", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(billete))
                {
                    Toast.makeText(getApplicationContext(),"Ingrese el valor del billete", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(telefono))
                {
                    Toast.makeText(getApplicationContext(),"Ingrese su numero de Telefono", Toast.LENGTH_SHORT).show();
                }else
                {
                    Destinos destinos1 = new Destinos(Double.valueOf(latitud),Double.valueOf(longitud),codigo,
                            billete,telefono);

                    databaseReference.child("destinos").child(codigo).setValue(destinos1);
                    Toast.makeText(getApplicationContext(),"Eviado Correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


        //Boton para consultar las coordenadas
        btnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acquire a reference to the system Location Manager
                LocationManager locationManager = (LocationManager) RegistroActivity.this.getSystemService(Context.LOCATION_SERVICE);

                // Define a listener that responds to location updates
                LocationListener locationListener = new LocationListener() {
                    public void onLocationChanged(Location location) {
                        // Called when a new location is found by the network location provider.
                        //makeUseOfNewLocation(location);
                        //tvUbicacion.setText(" " + location.getLatitude() + " " + location.getLongitude());
                        edtLat.setText(""+location.getLatitude());
                        edtLong.setText(""+location.getLongitude());

                    }

                    public void onStatusChanged(String provider, int status, Bundle extras) {}

                    public void onProviderEnabled(String provider) {}

                    public void onProviderDisabled(String provider) {}
                };
                int permissionCheck = ContextCompat.checkSelfPermission(RegistroActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION);
                // Register the listener with the Location Manager to receive location updates
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            }
        });





        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        if(permissionCheck == PackageManager.PERMISSION_DENIED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

        }

    }
}
