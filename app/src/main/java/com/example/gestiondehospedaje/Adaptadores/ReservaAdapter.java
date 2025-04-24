package com.example.gestiondehospedaje.Adaptadores;
import com.example.gestiondehospedaje.Activities.Reserva;
import com.example.gestiondehospedaje.Activities.ReservaDetalleActivity;
import com.example.gestiondehospedaje.Activities.ReservaHotel;
import com.example.gestiondehospedaje.Activities.ReservaCabana;
import com.example.gestiondehospedaje.Activities.ReservaGlamping;
import com.example.gestiondehospedaje.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder> {
    private Context context;
    private List<Reserva> listaReservas;

    public ReservaAdapter(Context context, List<Reserva> listaReservas) {
        this.context = context;
        this.listaReservas = listaReservas;
    }

    @NonNull
    @Override
    public ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_reserva, parent, false);
        return new ReservaViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaViewHolder holder, int position) {
        Reserva reserva = listaReservas.get(position);

        holder.tvTipo.setText("Tipo: " + reserva.getTipo());
        holder.tvCliente.setText("Cliente: " + reserva.getCliente());
        holder.tvFechas.setText("Del " + reserva.getFechaEntrada() + " al " + reserva.getFechaSalida());

        // Colores según tipo
        switch (reserva.getTipo()) {
            case "hotel":
                holder.itemView.setBackgroundColor(Color.parseColor("#E3F2FD")); // Azul claro
                break;
            case "cabana":
                holder.itemView.setBackgroundColor(Color.parseColor("#E8F5E9")); // Verde claro
                break;
            case "glamping":
                holder.itemView.setBackgroundColor(Color.parseColor("#F3E5F5")); // Morado claro
                break;
            default:
                holder.itemView.setBackgroundColor(Color.WHITE);
        }

        // Click para detalle (por ahora solo muestra un intent)
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ReservaDetalleActivity.class);
            intent.putExtra("reserva", reserva); // Enviar la reserva como Serializable
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaReservas.size();
    }

    public static class ReservaViewHolder extends RecyclerView.ViewHolder {
        TextView tvTipo, tvCliente, tvFechas;

        public ReservaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvCliente = itemView.findViewById(R.id.tvCliente);
            tvFechas = itemView.findViewById(R.id.tvFechas);
        }
    }
}
