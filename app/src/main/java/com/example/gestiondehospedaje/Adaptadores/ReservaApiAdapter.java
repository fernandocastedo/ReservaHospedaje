package com.example.gestiondehospedaje.Adaptadores;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestiondehospedaje.Activities.DetalleReservaApiActivity;
import com.example.gestiondehospedaje.Activities.FormularioReservaActivity;
import com.example.gestiondehospedaje.Modelos.ReservaApi;
import com.example.gestiondehospedaje.R;
import com.example.gestiondehospedaje.Repository.ReservaRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ReservaApiAdapter
        extends RecyclerView.Adapter<ReservaApiAdapter.ReservaViewHolder> {

    /* ---------- callback para edición ---------- */
    public interface OnReservaLongClickListener {
        void onReservaLongClick(int index, ReservaApi reserva);
    }

    /* ---------- fields ---------- */
    private static final String TAG = "ReservaApiAdapter";
    private static final String BASE_URL = "https://raw.githubusercontent.com/";
    private final Context context;
    private final List<ReservaApi> lista;
    private final OnReservaLongClickListener longClickListener;

    public ReservaApiAdapter(Context ctx,
                             List<ReservaApi> data,
                             OnReservaLongClickListener listener) {
        this.context           = ctx;
        this.lista             = data;
        this.longClickListener = listener;
    }

    /* ---------- ViewHolder ---------- */
    static class ReservaViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodigo, tvCliente;
        ImageView ivEditar, ivEliminar;

        ReservaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigo   = itemView.findViewById(R.id.tvCodigoApi);
            tvCliente  = itemView.findViewById(R.id.tvClienteApi);
            ivEditar   = itemView.findViewById(R.id.ivEditar);
            ivEliminar = itemView.findViewById(R.id.ivEliminar);
        }
    }

    /* ---------- Adapter overrides ---------- */
    @NonNull @Override
    public ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reserva_api, parent, false);
        return new ReservaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaViewHolder h, int pos) {
        ReservaApi r = lista.get(pos);
        if (r == null) return;

        h.tvCodigo.setText("Código: " + r.getCodigo());
        h.tvCliente.setText("Cliente: " + r.getCliente());

        String tipo = r.getTipo();
        if (tipo != null) {
            switch (tipo.toLowerCase()) {
                case "hotel":
                    h.itemView.setBackgroundColor(Color.parseColor("#B3E5FC"));
                    break;
                case "cabana":
                    h.itemView.setBackgroundColor(Color.parseColor("#C8E6C9"));
                    break;
                case "glamping":
                    h.itemView.setBackgroundColor(Color.parseColor("#E1BEE7"));
                    break;
                default:
                    h.itemView.setBackgroundColor(Color.WHITE);
            }
        } else {
            h.itemView.setBackgroundColor(Color.WHITE);
        }

        h.itemView.setOnClickListener(v -> {
            try {
                Intent i = new Intent(context, DetalleReservaApiActivity.class);
                i.putExtra("reserva", r);
                context.startActivity(i);
            } catch (Exception e) {
                Log.e(TAG, "detalle", e);
                Toast.makeText(context, "Error al abrir detalle", Toast.LENGTH_SHORT).show();
            }
        });
        h.ivEditar.setOnClickListener(v -> {
            Intent i = new Intent(context, FormularioReservaActivity.class);
            i.putExtra("reserva", r);
            i.putExtra("indiceEdicion", pos);
            context.startActivity(i);
        });
        h.ivEliminar.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Eliminar reserva")
                    .setMessage("¿Seguro que deseas eliminarla?")
                    .setPositiveButton("Sí", (dialog, which) -> {
                        ReservaRepository.getInstance().remove(pos);
                        notifyItemRemoved(pos);
                    })
                    .setNegativeButton("Cancelar", (dialog, which) -> notifyItemChanged(pos))
                    .show();
        });
        h.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onReservaLongClick(pos, r);
            }
            return true;
        });
    }

    @Override public int getItemCount() { return lista != null ? lista.size() : 0; }
}
