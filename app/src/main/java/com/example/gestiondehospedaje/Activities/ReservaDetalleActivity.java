package com.example.gestiondehospedaje.Activities;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gestiondehospedaje.R;

public class ReservaDetalleActivity extends AppCompatActivity {

    private TextView tvDetalle, tvPrecio;
    private ImageView imgDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_detalle);

        tvDetalle = findViewById(R.id.tvDetalle);
        tvPrecio = findViewById(R.id.tvPrecio);
        imgDetalle = findViewById(R.id.imgDetalle);

        Reserva reserva = (Reserva) getIntent().getSerializableExtra("reserva");

        if (reserva != null) {
            String texto = "Código: " + reserva.getCodigo() + "\n"
                    + "Cliente: " + reserva.getCliente() + "\n"
                    + "Entrada: " + reserva.getFechaEntrada() + "\n"
                    + "Salida: " + reserva.getFechaSalida() + "\n"
                    + "Precio Total: $" + reserva.getPrecioTotal() + "\n"
                    + "Tipo: " + reserva.getTipo();

            tvDetalle.setText(texto); // ✅ se muestra para cualquier tipo

            if (reserva instanceof ReservaGlamping) {
                ReservaGlamping glamping = (ReservaGlamping) reserva;

                tvPrecio.setText("Precio adicional: $" + glamping.getPrecio());
                tvPrecio.setVisibility(TextView.VISIBLE);

                imgDetalle.setImageResource(glamping.getImagenResourceId());
                imgDetalle.setVisibility(ImageView.VISIBLE);
            }
        }
    }
}
