package gb.paqueteria.gbpaqueteria.admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import gb.paqueteria.gbpaqueteria.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;



public class MenuAdminActivity extends AppCompatActivity {
    CircleMenu circleMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menurepartidorctivity);
        // para traer nuestro titulo de la app centrado
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_loginadmin);
        // casteando nuestro circle menu
        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
        //
        circleMenu.setMainMenu(Color.parseColor("#FA8258"), R.drawable.menutres, R.drawable.cancelar)
                .addSubMenu(Color.parseColor("#0FF45C"), R.drawable.subir)
                .addSubMenu(Color.parseColor("#80F816"), R.drawable.remover)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {
                        if (index==0){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MenuAdminActivity.this, RegistrarOfertasActivity.class);
                                     startActivity(intent);
                                }
                            },600);

                        }

                        if (index==1){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MenuAdminActivity.this, RemoverOfertasActivity.class);
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
