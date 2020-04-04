package gb.paqueteria.gbpaqueteria.repartidor;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

import gb.paqueteria.gbpaqueteria.R;
import gb.paqueteria.gbpaqueteria.cliente.RegistroActivity;
import gb.paqueteria.gbpaqueteria.informacion.InformacionActivity;
import gb.paqueteria.gbpaqueteria.menu.MainActivity;
import gb.paqueteria.gbpaqueteria.ofertas.OfertasActivity;

public class Menurepartidorctivity extends AppCompatActivity {
    CircleMenu circleMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menurepartidorctivity);
        // para traer nuestro titulo de la app centrado
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo);
        // casteando nuestro circle menu
        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
        //
        circleMenu.setMainMenu(Color.parseColor("#FA8258"), R.drawable.menudos, R.drawable.cancelar)
                .addSubMenu(Color.parseColor("#258CFF"), R.drawable.entregadas)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.mapa)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {
                        if (index==0){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(Menurepartidorctivity.this, EntregadasActivity.class);
                                    startActivity(intent);
                                }
                            },600);

                        }

                        if (index==1){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(Menurepartidorctivity.this, MapsActivity.class);
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
}
