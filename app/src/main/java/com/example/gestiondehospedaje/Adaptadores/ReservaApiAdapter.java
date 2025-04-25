package com.example.gestiondehospedaje.Adaptadores;

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

import com.example.gestiondehospedaje.Activities.DetalleReservaApiActivity;
import com.example.gestiondehospedaje.Modelos.ReservaApi;
import com.example.gestiondehospedaje.R;

import java.util.List;
import java.util.stream.Collectors;

public class ReservaApiAdapter extends RecyclerView.Adapter<ReservaApiAdapter.ReservaViewHolder> {
    private static final String TAG = "ReservaApiAdapter";
    private final Context context;
    private final List<ReservaApi> lista;

    public ReservaApiAdapter(Context context, List<ReservaApi> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reserva_api, parent, false);
            return new ReservaViewHolder(vista);
        } catch (Exception e) {
            Log.e(TAG, "Error en onCreateViewHolder: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaViewHolder holder, int position) {
        try {
            ReservaApi reserva = lista.get(position);

            if (reserva == null) {
                Log.e(TAG, "Reserva null en posición " + position);
                return;
            }

            holder.tvCodigo.setText("Código: " + reserva.getCodigo());
            holder.tvCliente.setText("Cliente: " + reserva.getCliente());
            holder.tvFechas.setText("Del " + reserva.getFechaEntrada() + " al " + reserva.getFechaSalida());
            holder.tvPrecio.setText(String.format("Precio Total: $%.2f", reserva.getPrecioTotal()));
            holder.tvTipo.setText("Tipo: " + reserva.getTipo());
            holder.tvHabitacion.setText("Habitación: " + reserva.getTipoHabitacion());
            holder.tvDesayuno.setText("Incluye Desayuno: " + (reserva.isIncluyeDesayuno() ? "Sí" : "No"));
            holder.tvHuespedes.setText("Huéspedes: " + reserva.getNumeroHuespedes());

            // Información adicional
            if (reserva.getInformacionAdicional() != null) {
                holder.tvEsperanzaVida.setText("Esperanza de Vida: " + reserva.getInformacionAdicional().getEsperanzaVida());
                
                if (reserva.getInformacionAdicional().getDatos() != null) {
                    String datosAdicionales = reserva.getInformacionAdicional().getDatos().stream()
                        .map(dato -> dato.getNombreDato() + ": " + dato.getValor())
                        .collect(Collectors.joining("\n"));
                    holder.tvDatosAdicionales.setText(datosAdicionales);
                }
            }

            // Establecer color de fondo según el tipo
            try {
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
                        holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF")); // Blanco
                }
            } catch (Exception e) {
                Log.e(TAG, "Error al establecer color de fondo: " + e.getMessage());
                holder.itemView.setBackgroundColor(Color.WHITE); // Color por defecto si hay error
            }

            // Click para detalle
            holder.itemView.setOnClickListener(v -> {
                try {
                    Intent intent = new Intent(v.getContext(), DetalleReservaApiActivity.class);
                    intent.putExtra("codigo", reserva.getCodigo());
                    intent.putExtra("cliente", reserva.getCliente());
                    intent.putExtra("entrada", reserva.getFechaEntrada());
                    intent.putExtra("salida", reserva.getFechaSalida());
                    intent.putExtra("precio", reserva.getPrecioTotal());
                    intent.putExtra("tipo", reserva.getTipo());
                    intent.putExtra("habitacion", reserva.getTipoHabitacion());
                    intent.putExtra("desayuno", reserva.isIncluyeDesayuno());
                    intent.putExtra("huespedes", reserva.getNumeroHuespedes());
                    v.getContext().startActivity(intent);
                } catch (Exception e) {
                    Log.e(TAG, "Error al iniciar DetalleReservaApiActivity: " + e.getMessage(), e);
                    Toast.makeText(context, "Error al abrir el detalle", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error en onBindViewHolder: " + e.getMessage(), e);
        }
    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0;
    }

    public static class ReservaViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodigo, tvCliente, tvFechas, tvPrecio, tvTipo, tvHabitacion, tvDesayuno, 
                tvHuespedes, tvEsperanzaVida, tvDatosAdicionales;

        public ReservaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigoApi);
            tvCliente = itemView.findViewById(R.id.tvClienteApi);
            tvFechas = itemView.findViewById(R.id.tvFechasApi);
            tvPrecio = itemView.findViewById(R.id.tvPrecioApi);
            tvTipo = itemView.findViewById(R.id.tvTipoApi);
            tvHabitacion = itemView.findViewById(R.id.tvHabitacionApi);
            tvDesayuno = itemView.findViewById(R.id.tvDesayunoApi);
            tvHuespedes = itemView.findViewById(R.id.tvHuespedesApi);
            tvEsperanzaVida = itemView.findViewById(R.id.tvEsperanzaVidaApi);
            tvDatosAdicionales = itemView.findViewById(R.id.tvDatosAdicionalesApi);
        }
    }
}
