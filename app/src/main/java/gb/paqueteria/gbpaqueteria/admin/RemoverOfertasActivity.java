package gb.paqueteria.gbpaqueteria.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import gb.paqueteria.gbpaqueteria.R;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;

public class RemoverOfertasActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Ofertas> destinosList;
    //DestinosAdapter adapter;
    FirebaseRecyclerAdapter<Ofertas, OfertasAdapter.ViewHolder> adapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remover_ofertas);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_loginadmin);
        //llamar datos
        //createDatos();
        //creamos la referencia de firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();
        // casteamos la variable
        recyclerView = (RecyclerView) findViewById(R.id.reciclerViewUno);
        // siempre se usa un linear layout manager donde pasamos el contexto
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // Definimos la orientacion del linearlayout
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        // enviar datos a la vista usando el adapatador
        //adapter = new DestinosAdapter(this, destinosList);
        adapter = new FirebaseRecyclerAdapter<Ofertas, OfertasAdapter.ViewHolder>(
                Ofertas.class,
                R.layout.ofertas,
                OfertasAdapter.ViewHolder.class,
                databaseReference.child("Ofertas")
        ) {
            @Override
            protected void populateViewHolder(OfertasAdapter.ViewHolder viewHolder, Ofertas ofertas, final int i) {
                viewHolder.name.setText(String.valueOf(ofertas.getName()));
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

