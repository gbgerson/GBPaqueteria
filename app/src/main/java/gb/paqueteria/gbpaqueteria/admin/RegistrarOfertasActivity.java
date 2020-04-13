package gb.paqueteria.gbpaqueteria.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import gb.paqueteria.gbpaqueteria.R;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class RegistrarOfertasActivity extends AppCompatActivity {
    private static final int RECOGER_SOLICITUD_IMAGEN = 1;

    private Button btnEligeImagen;
    private Button btnCarga;
    private TextView txtVerSubidas;
    private EditText edtNombreDelArchivo;
    private EditText edtDescription;
    private ImageView imageView;
    private ProgressBar mprogressBar;

    private Uri mImagenRuta;
    private StorageTask mUploadTask;

    // Variables para storage
    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_ofertas);

        //Casteamos
        btnEligeImagen = findViewById(R.id.btnEligeImagen);
        btnCarga = findViewById(R.id.btnSubir);
        edtNombreDelArchivo = findViewById(R.id.edtFileName);
        edtDescription = findViewById(R.id.edtDescription);
        imageView = findViewById(R.id.image_view);
        mprogressBar = findViewById(R.id.progress_bar);

        //Storage
        storageReference = FirebaseStorage.getInstance().getReference("Ofertas");
        // Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("Ofertas");

        btnEligeImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abriArchivos();
            }
        });
        btnCarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUploadTask!= null && mUploadTask.isInProgress())
                {
                    Toast.makeText(RegistrarOfertasActivity.this, "Carga en Progreso", Toast.LENGTH_SHORT).show();
                }else
                {
                    subirArchivo();
                }
            }
        });


    }
    // MEtodo para anbrir el gestor de archivos de android
    private void abriArchivos() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, RECOGER_SOLICITUD_IMAGEN);
    }
    // Recuperar la url de la imagen y cargarla al imagenview en android
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RECOGER_SOLICITUD_IMAGEN && resultCode == RESULT_OK
                && data != null && data.getData() != null)
        {
            mImagenRuta = data.getData();
            Picasso.with(this).load(mImagenRuta).into(imageView);

        }
    }
    private String obtenerDireccionDelArchivo(Uri uri)
    {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    // Metodo para subir los datos a firebase
    private void subirArchivo()
    {
        if(mImagenRuta != null)
        {
            StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    + "." + obtenerDireccionDelArchivo(mImagenRuta));

            mUploadTask = fileReference.putFile(mImagenRuta)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mprogressBar.setProgress(0);
                                }
                            }, 500);
                            int Id = (int) new Date().getTime();
                            String subirId = Integer.toString(Id);
                            Toast.makeText(RegistrarOfertasActivity.this, "Imagen Cargada con Exito", Toast.LENGTH_LONG).show();
                            String nombre = edtNombreDelArchivo.getText().toString().trim();
                            String descripcion = edtDescription.getText().toString().trim();
                            if(TextUtils.isEmpty(nombre))
                            {
                                Toast.makeText(RegistrarOfertasActivity.this, "Escriba un Nombre de la oferta", Toast.LENGTH_LONG).show();
                            }else if(TextUtils.isEmpty(descripcion))
                            {
                                Toast.makeText(RegistrarOfertasActivity.this, "Escriba una descripcion de la oferta", Toast.LENGTH_LONG).show();
                            }else
                            {
                                Subir subir = new Subir(nombre, taskSnapshot.getUploadSessionUri().toString(),descripcion,Id
                                );

                                databaseReference.child(subirId).setValue(subir);
                                limpiarCampos();
                            }
                        }
                    })
                    //Si existirea un errror
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegistrarOfertasActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    //Progreso de la barra del progressbar
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mprogressBar.setProgress((int) progress);
                        }
                    });

        }else {
            Toast.makeText(this, "No ha seleccionado una imagen", Toast.LENGTH_SHORT).show();
        }

    }

    private void limpiarCampos() {
        edtNombreDelArchivo.setText("");
        imageView.invalidate();
        imageView.setImageBitmap(null);
        edtDescription.setText("");
    }

}
