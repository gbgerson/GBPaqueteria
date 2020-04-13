package gb.paqueteria.gbpaqueteria.ofertas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import gb.paqueteria.gbpaqueteria.R;
import gb.paqueteria.gbpaqueteria.admin.Subir;

public class OfertasActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private ProgressBar progressBarCircle;
    private DatabaseReference databaseReference;
    private List<Subir> mSubir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertas);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_layout);
        progressBarCircle = findViewById(R.id.progress_circle);
        mRecyclerView = findViewById(R.id.reciclerViewImg);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSubir = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Ofertas");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Subir upload = postSnapshot.getValue(Subir.class);
                    mSubir.add(upload);
                }
                mAdapter = new ImageAdapter(OfertasActivity.this, mSubir);
                mRecyclerView.setAdapter(mAdapter);
                progressBarCircle.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(OfertasActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progressBarCircle.setVisibility(View.INVISIBLE);
            }
        });
    }
}
