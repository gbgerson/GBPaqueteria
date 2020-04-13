package gb.paqueteria.gbpaqueteria.menu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

import gb.paqueteria.gbpaqueteria.R;
import gb.paqueteria.gbpaqueteria.admin.LoginAdminActivity;
import gb.paqueteria.gbpaqueteria.cliente.RegistroActivity;
import gb.paqueteria.gbpaqueteria.informacion.InformacionActivity;
import gb.paqueteria.gbpaqueteria.ofertas.OfertasActivity;
import gb.paqueteria.gbpaqueteria.repartidor.LoginActivity;

public class MainActivity extends AppCompatActivity {
    CircleMenu circleMenu;
    Location location;
    LocationManager locationManager;
    LocationListener locationListener;
    AlertDialog alert = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_layout);
        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
        //pre
        getConexionIntenet();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            AlertNoGps();
        }

        circleMenu.setMainMenu(Color.parseColor("#FA8258"), R.drawable.menu, R.drawable.cancelar)
                .addSubMenu(Color.parseColor("#258CFF"), R.drawable.repartir)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.pedidos)
                .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.informacion)
                .addSubMenu(Color.parseColor("#8A39FF"), R.drawable.ofertas)
                .addSubMenu(Color.parseColor("#56F9AF"), R.drawable.admin)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {
                        if (index==0){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            },600);

                        }

                        if (index==1){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                                    startActivity(intent);
                                }
                            },600);

                        }
                        if (index==2){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MainActivity.this, InformacionActivity.class);
                                    startActivity(intent);
                                }
                            },600);

                        }
                        if (index==3){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MainActivity.this, OfertasActivity.class);
                                    startActivity(intent);
                                }
                            },600);

                        }
                        if (index==4){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MainActivity.this, LoginAdminActivity.class);
                                    startActivity(intent);
                                }
                            },600);

                        }


                    }

                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            @Override
            public void onMenuOpened() {}

            @Override
            public void onMenuClosed() {}

        });

    }
    private void AlertNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Por favor Encienda la Ubicación del Telefono. ¿Desea activarlo?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        alert = builder.create();
        alert.show();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(alert != null)
        {
            alert.dismiss ();
        }
    }

    //metodo para preguntar por la conexiona de internet
    private void getConexionIntenet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        //sino existiera la conexion a internet cerramos la aplicacion
        if (networkInfo == null ) {
            //Toast.makeText(MainActivity.this, "El Dispositivo no tiene Conexion a Internet", Toast.LENGTH_SHORT).show();
            Toast mensaje = Toast.makeText(getApplicationContext(), "El Dispositivo no tiene Conexion a Internet", Toast.LENGTH_SHORT);
            mensaje.setGravity(Gravity.CENTER|Gravity.LEFT,0,0);
            mensaje.show();
            //finalizar la actividad
            finish();
        };

    }
}
