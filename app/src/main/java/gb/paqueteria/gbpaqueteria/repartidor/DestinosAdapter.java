package gb.paqueteria.gbpaqueteria.repartidor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gb.paqueteria.gbpaqueteria.R;
import gb.paqueteria.gbpaqueteria.cliente.Destinos;

public class DestinosAdapter extends RecyclerView.Adapter<DestinosAdapter.ViewHolder> {
    List<Destinos> destinos;
    Context context;

    public DestinosAdapter(Context context, List<Destinos> destinos )
    {
        this.context = context;
        this.destinos = destinos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destinos, parent, false);
        // pasar el view holder que emos creado nosotros
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.codigo.setText(destinos.get(position).codigo);
        holder.telefono.setText(destinos.get(position).telefono);
    }

    @Override
    public int getItemCount() {
        return destinos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView codigo, telefono;

        // esta clase es para pasar los datos a la vista
        public ViewHolder(View view)
        {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cardView);
            codigo = (TextView) view.findViewById(R.id.codigo);
            telefono = (TextView) view.findViewById(R.id.telefono);

        }

    }
}
