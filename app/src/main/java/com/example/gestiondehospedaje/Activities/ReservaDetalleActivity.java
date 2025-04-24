package com.example.gestiondehospedaje.Activities;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gestiondehospedaje.R;

public class ReservaDetalleActivity extends AppCompatActivity {

    private TextView tvDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_detalle);

        tvDetalle = findViewById(R.id.tvDetalle);

        Reserva reserva = (Reserva) getIntent().getSerializableExtra("reserva");

        if (reserva != null) {
            String texto = "Código: " + reserva.getCodigo() + "\n"
                    + "Cliente: " + reserva.getCliente() + "\n"
                    + "Entrada: " + reserva.getFechaEntrada() + "\n"
                    + "Salida: " + reserva.getFechaSalida() + "\n"
                    + "Precio: $" + reserva.getPrecioTotal() + "\n"
                    + "Tipo: " + reserva.getTipo();
            tvDetalle.setText(texto);
        }
    }
}
