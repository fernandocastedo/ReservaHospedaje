package com.example.gestiondehospedaje.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gestiondehospedaje.R;

import android.widget.TextView;
import android.widget.Toast;


public class DetalleReservaApiActivity extends AppCompatActivity {

    private TextView tvDetalleApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reserva_api);

        tvDetalleApi = findViewById(R.id.tvDetalleApi);

        // Obtener datos del intent
        String codigo = getIntent().getStringExtra("codigo");
        String cliente = getIntent().getStringExtra("cliente");
        String fechaEntrada = getIntent().getStringExtra("entrada");
        String fechaSalida = getIntent().getStringExtra("salida");
        double precio = getIntent().getDoubleExtra("precio", 0.0);
        String tipo = getIntent().getStringExtra("tipo");
        String habitacion = getIntent().getStringExtra("habitacion");
        boolean desayuno = getIntent().getBooleanExtra("desayuno", false);
        int huespedes = getIntent().getIntExtra("huespedes", 0);

        if (codigo != null && cliente != null) {
            StringBuilder detalle = new StringBuilder();
            detalle.append(String.format("Código: %s\n", codigo));
            detalle.append(String.format("Cliente: %s\n", cliente));
            detalle.append(String.format("Fecha Entrada: %s\n", fechaEntrada));
            detalle.append(String.format("Fecha Salida: %s\n", fechaSalida));
            detalle.append(String.format("Precio Total: $%.2f\n", precio));
            detalle.append(String.format("Tipo: %s\n", tipo));
            detalle.append(String.format("Tipo de Habitación: %s\n", habitacion));
            detalle.append(String.format("Incluye Desayuno: %s\n", desayuno ? "Sí" : "No"));
            detalle.append(String.format("Número de Huéspedes: %d\n", huespedes));

            // Aplicar estilo al texto
            tvDetalleApi.setTextSize(16);
            tvDetalleApi.setPadding(32, 32, 32, 32);
            tvDetalleApi.setText(detalle.toString());
        } else {
            Toast.makeText(this, "Error: Datos incompletos", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}