package gb.paqueteria.gbpaqueteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

public class MainActivity extends AppCompatActivity {
    CircleMenu circleMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#FA8258"), R.drawable.menu, R.drawable.cancelar)
                .addSubMenu(Color.parseColor("#258CFF"), R.drawable.repartidor)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.pedidos)
                .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.informacion)
                .addSubMenu(Color.parseColor("#8A39FF"), R.drawable.ofertas)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {
                        if (index==0){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                                    startActivity(intent);
                                }
                            },600);

                        }

                        if (index==1){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MainActivity.this,RegistroActivity.class);
                                    startActivity(intent);
                                }
                            },600);

                        }
                        if (index==2){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MainActivity.this,InformacionActivity.class);
                                    startActivity(intent);
                                }
                            },600);

                        }
                        if (index==3){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MainActivity.this,OfertasActivity.class);
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
