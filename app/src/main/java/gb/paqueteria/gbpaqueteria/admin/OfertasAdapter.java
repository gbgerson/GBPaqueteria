package gb.paqueteria.gbpaqueteria.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gb.paqueteria.gbpaqueteria.R;
import gb.paqueteria.gbpaqueteria.cliente.Destinos;

public class OfertasAdapter extends RecyclerView.Adapter<OfertasAdapter.ViewHolder> {
    List<Ofertas> ofertas;
    Context context;

    public OfertasAdapter(Context context, List<Ofertas> ofertas )
    {
        this.context = context;
        this.ofertas = ofertas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ofertas, parent, false);
        // pasar el view holder que emos creado nosotros
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(ofertas.get(position).name);

    }

    @Override
    public int getItemCount() {
        return ofertas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView name;
        ImageView trash;

        // esta clase es para pasar los datos a la vista
        public ViewHolder(View view)
        {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cardViewUno);
            name = (TextView) view.findViewById(R.id.name);
            trash =(ImageView)view.findViewById(R.id.borrar);

        }

    }
}
