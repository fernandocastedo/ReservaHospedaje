package com.example.gestiondehospedaje.Activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestiondehospedaje.Modelos.ReservaApi;
import com.example.gestiondehospedaje.R;

public class DetalleReservaApiActivity extends AppCompatActivity {

    /* Referencias UI */
    private TextView tvCodigo, tvCliente, tvFechas, tvPrecio,
            tvTipo, tvHabitacion, tvDesayuno,
            tvHuespedes, tvEsperanzaVida, tvDatosAdicionales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reserva_api);

        /* Enlazar vistas */
        tvCodigo          = findViewById(R.id.tvCodigoApi);
        tvCliente         = findViewById(R.id.tvClienteApi);
        tvFechas          = findViewById(R.id.tvFechasApi);
        tvPrecio          = findViewById(R.id.tvPrecioApi);
        tvTipo            = findViewById(R.id.tvTipoApi);
        tvHabitacion      = findViewById(R.id.tvHabitacionApi);
        tvDesayuno        = findViewById(R.id.tvDesayunoApi);
        tvHuespedes       = findViewById(R.id.tvHuespedesApi);
        tvEsperanzaVida   = findViewById(R.id.tvEsperanzaVidaApi);
        tvDatosAdicionales= findViewById(R.id.tvDatosAdicionalesApi);

        /* Obtener la reserva parcelable */
        ReservaApi reserva = getIntent().getParcelableExtra("reserva");

        if (reserva == null) {
            Toast.makeText(this, "Error: reserva no encontrada", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        /* Mostrar datos básicos */
        tvCodigo.setText("Código: " + reserva.getCodigo());
        tvCliente.setText("Cliente: " + reserva.getCliente());
        tvFechas.setText(reserva.getFechaEntrada() + " → " + reserva.getFechaSalida());
        tvPrecio.setText(String.format("Precio Total: $%.2f", reserva.getPrecioTotal()));
        tvTipo.setText("Tipo: " + reserva.getTipo());
        tvHabitacion.setText("Habitación: " + reserva.getTipoHabitacion());
        tvDesayuno.setText("Incluye Desayuno: " + (reserva.isIncluyeDesayuno() ? "Sí" : "No"));
        tvHuespedes.setText("Huéspedes: " + reserva.getNumeroHuespedes());

        /* Información adicional */
        if (reserva.getInformacionAdicional() != null) {
            tvEsperanzaVida.setText("Esperanza de Vida: "
                    + reserva.getInformacionAdicional().getEsperanzaVida());

            // Construir texto con el array de datos
            StringBuilder sb = new StringBuilder();
            if (reserva.getInformacionAdicional().getDatos() != null) {
                for (ReservaApi.DatoAdicional d : reserva.getInformacionAdicional().getDatos()) {
                    sb.append("• ").append(d.getNombreDato())
                            .append(": ").append(d.getValor()).append('\n');
                }
            }
            tvDatosAdicionales.setText(sb.toString().trim());
        }
    }
}
