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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder> {
    private static final String TAG = "ReservaAdapter";
    private Context context;
    private List<Reserva> listaReservas;

    public ReservaAdapter(Context context, List<Reserva> listaReservas) {
        this.context = context;
        this.listaReservas = listaReservas;
    }

    @NonNull
    @Override
    public ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            View vista = LayoutInflater.from(context).inflate(R.layout.item_reserva, parent, false);
            return new ReservaViewHolder(vista);
        } catch (Exception e) {
            Log.e(TAG, "Error en onCreateViewHolder: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaViewHolder holder, int position) {
        try {
            Reserva reserva = listaReservas.get(position);

            if (reserva == null) {
                Log.e(TAG, "Reserva null en posición " + position);
                return;
            }

            holder.tvTipo.setText("Tipo: " + reserva.getTipo());
            holder.tvCliente.setText("Cliente: " + reserva.getCliente());
            holder.tvFechas.setText("Del " + reserva.getFechaEntrada() + " al " + reserva.getFechaSalida());

            // Colores según tipo
            switch (reserva.getTipo().toLowerCase()) {
                case "hotel":
                    holder.itemView.setBackgroundColor(Color.parseColor("#B3E5FC")); // Azul claro
                    break;
                case "cabana":
                    holder.itemView.setBackgroundColor(Color.parseColor("#C8E6C9")); // Verde claro
                    break;
                case "glamping":
                    holder.itemView.setBackgroundColor(Color.parseColor("#E1BEE7")); // Morado claro
                    break;
                default:
                    holder.itemView.setBackgroundColor(Color.WHITE);
            }

            // Click para detalle
            holder.itemView.setOnClickListener(v -> {
                try {
                    Intent intent = new Intent(context, ReservaDetalleActivity.class);
                    intent.putExtra("reserva", reserva);
                    context.startActivity(intent);
                } catch (Exception e) {
                    Log.e(TAG, "Error al iniciar ReservaDetalleActivity: " + e.getMessage(), e);
                    Toast.makeText(context, "Error al abrir el detalle", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error en onBindViewHolder: " + e.getMessage(), e);
        }
    }

    @Override
    public int getItemCount() {
        return listaReservas != null ? listaReservas.size() : 0;
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
