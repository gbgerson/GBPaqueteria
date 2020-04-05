package gb.paqueteria.gbpaqueteria.repartidor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import gb.paqueteria.gbpaqueteria.R;
import gb.paqueteria.gbpaqueteria.cliente.Destinos;


public class EntregadasActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Destinos> destinosList;
    //DestinosAdapter adapter;
    FirebaseRecyclerAdapter<Destinos, DestinosAdapter.ViewHolder> adapter;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregadas);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_titulo);
        //llamar datos
        //createDatos();
        //creamos la referencia de firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();
        // casteamos la variable
        recyclerView = (RecyclerView) findViewById(R.id.reciclerView);
        // siempre se usa un linear layout manager donde pasamos el contexto
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // Definimos la orientacion del linearlayout
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        // enviar datos a la vista usando el adapatador
        //adapter = new DestinosAdapter(this, destinosList);
        adapter = new FirebaseRecyclerAdapter<Destinos, DestinosAdapter.ViewHolder>(
                Destinos.class,
                R.layout.destinos,
                DestinosAdapter.ViewHolder.class,
                databaseReference.child("destinos")
        ) {
            @Override
            protected void populateViewHolder(DestinosAdapter.ViewHolder viewHolder, Destinos destinos, final int i) {
                viewHolder.codigo.setText(String.valueOf(destinos.getCodigo()));
                viewHolder.telefono.setText(destinos.getTelefono());
                //linea para borrar destinos
                viewHolder.trash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.getRef(i).removeValue();
                    }
                });
            }
        };


        recyclerView.setAdapter(adapter);
    }
    //ejemplificando
    /*public void createDatos() {
        destinosList = new ArrayList<>();
        destinosList.add(new Destinos("500","33590323"));
        destinosList.add(new Destinos("501","33590324"));
        destinosList.add(new Destinos("502","33590325"));
    }
    */

}
