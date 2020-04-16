package gb.paqueteria.gbpaqueteria.informacion;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import gb.paqueteria.gbpaqueteria.R;

public class InformacionActivity extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_layout);


        // creamos una instancia de la base de datos
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // aqui le pasasmos los nodos a los cuales queremos acceder  en este caso tendremos  como referencia  informacion
        // y un hijo que se llama numero
        DatabaseReference myRef0 = database.getReference("Foto").child("url");
        DatabaseReference myRef = database.getReference("Informacion").child("Titulo");
        DatabaseReference myRef1 = database.getReference("Informacion").child("parrafoUno");
        DatabaseReference myRef2 = database.getReference("Informacion").child("TituloDos");
        DatabaseReference myRef3 = database.getReference("Informacion").child("parrafoDos");
        DatabaseReference myRef4 = database.getReference("Informacion").child("TituloTres");
        DatabaseReference myRef5 = database.getReference("Informacion").child("parrafoTres");
        DatabaseReference myRef6 = database.getReference("Informacion").child("TituloCuatro");
        DatabaseReference myRef7 = database.getReference("Informacion").child("parrafoCuatro");

        // myRef.setValue("Jalapa Guatemala");
        //castemos un texview al cual le cargaremosm la  informacion
        final ImageView imageView = findViewById(R.id.foto);
        final TextView textView = (TextView) findViewById(R.id.titulo);
        final TextView textView1 = (TextView) findViewById(R.id.parrafoUno);
        final TextView textView2 = (TextView) findViewById(R.id.tituloDos);
        final TextView textView3 = (TextView) findViewById(R.id.parrafoDos);
        final TextView textView4 = (TextView) findViewById(R.id.tituloTres);
        final TextView textView5 = (TextView) findViewById(R.id.parrafoTres);
        final TextView textView6 = (TextView) findViewById(R.id.tituloCuatro);
        final TextView textView7 = (TextView) findViewById(R.id.parrafoCuatro);

        myRef0.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // aqui hacemos uso del texviw
                Picasso.with(InformacionActivity.this).load(dataSnapshot.getValue(String.class)).into(imageView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // en este addValueLista nos sirve para cargarle los datos del snaptchot al textview
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // aqui hacemos uso del texviw
                textView.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // aqui hacemos uso del texviw
                textView1.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // aqui hacemos uso del texviw
                textView2.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // aqui hacemos uso del texviw
                textView3.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // aqui hacemos uso del texviw
                textView4.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // aqui hacemos uso del texviw
                textView5.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // aqui hacemos uso del texviw
                textView6.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // aqui hacemos uso del texviw
                textView7.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
