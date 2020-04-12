package gb.paqueteria.gbpaqueteria.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import gb.paqueteria.gbpaqueteria.R;

public class LoginAdminActivity extends AppCompatActivity {

    // cresmo dos variables para las entradas del correo y contrase;a
    EditText email, password;
    // TextView email1, password1;

    // creamos una variable tipo boton que se llama login
    Button login, register;
    // creamos una variable de tipo autentificacion que se llama auth
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        auth = FirebaseAuth.getInstance();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_loginadmin);
        //casteamos los editex como el boton
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login= findViewById(R.id.login);
        //email1 =(TextView) findViewById(R.id.email1);
        //password1 =(TextView) findViewById(R.id.password1);


        // ya teniendo la variable que hace referencia al boton le creamos un set
        //onclicklistener

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // creamos dos variables que conviertan las entradas a un tostring
                //trim(() para eliminar espacios al inicio y al final
                String userE = email.getText().toString().trim();
                String passE = password.getText().toString().trim();
                // luego creamos una condicion diciendo si viene en blanco manda un mensaje que diga coloca algo
                if (TextUtils.isEmpty(userE)){
                    Toast.makeText(getApplicationContext(),"Coloca un usuario", Toast.LENGTH_SHORT).show();
                }
                // las misma condicional para el campo passE
                else if (TextUtils.isEmpty(passE)){
                    Toast.makeText(getApplicationContext(),"Coloca una contraseña", Toast.LENGTH_SHORT).show();
                }else
                {
                    // utilizamos la variable que declaramos al principio
                    // le decimos que queremos loguearnos por correo y contrase;a
                    auth.signInWithEmailAndPassword(userE,passE)
                            .addOnCompleteListener(LoginAdminActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // si la tarea es diferente de exito pues mandar un mensaje que algo esta
                                    // incorrecto
                                    if (!task.isSuccessful()){

                                        Toast.makeText(getApplicationContext(),"Correo o Contraseña incorrecta",Toast.LENGTH_SHORT).show();

                                    }else {
                                        // si sale bien lanzarse de  una actividad hacia otra
                                        Intent intent = new Intent(LoginAdminActivity.this, MenuAdminActivity.class);
                                        startActivity(intent);
                                        finish();


                                    }
                                }
                            });
                }


            }
        });




    }

}
