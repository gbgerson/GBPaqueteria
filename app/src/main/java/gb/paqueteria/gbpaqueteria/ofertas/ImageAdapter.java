package gb.paqueteria.gbpaqueteria.ofertas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import gb.paqueteria.gbpaqueteria.R;
import gb.paqueteria.gbpaqueteria.admin.Subir;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Subir> mSubir;

    public ImageAdapter(Context context, List<Subir> subidas)
    {
        mContext = context;
        mSubir = subidas;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Subir subirCurrent = mSubir.get(position);
        holder.textViewName.setText(subirCurrent.getName());
        Picasso.with(mContext)
                .load(subirCurrent.getImagenUri())
                .placeholder(R.drawable.loading)
                .fit()
                .centerCrop()
                .into(holder.imageView);
        holder.txtDescription.setText(subirCurrent.getDescription());
    }

    @Override
    public int getItemCount() {
        return mSubir.size();
    }

    public class ImageViewHolder extends  RecyclerView.ViewHolder
    {
        public TextView textViewName, txtDescription;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.nombre_oferta);
            imageView = itemView.findViewById(R.id.image_view_subida);
            txtDescription =itemView.findViewById(R.id.txtDescription);

        }
    }
}
