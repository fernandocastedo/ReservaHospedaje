package com.example.gestiondehospedaje.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestiondehospedaje.Modelos.ReservaApi;
import com.example.gestiondehospedaje.R;

public class FormularioReservaActivity extends AppCompatActivity {

    private EditText etCodigo, etCliente, etEntrada, etSalida, etPrecio;
    private Button   btnGuardar;

    private boolean esEdicion   = false;
    private int     indiceEdicion = -1;
    private ReservaApi reservaActual;
    private Spinner spTipo, spHabitacion;
    private Switch swDesayuno;
    private EditText etHuespedes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fomulario_reserva);

        /* Referencias a la UI */
        etCodigo = findViewById(R.id.etCodigo);
        etCliente = findViewById(R.id.etCliente);
        etEntrada = findViewById(R.id.etFechaEntrada);
        etSalida = findViewById(R.id.etFechaSalida);
        etPrecio = findViewById(R.id.etPrecio);
        btnGuardar = findViewById(R.id.btnGuardar);
        spTipo = findViewById(R.id.spTipo);
        spHabitacion = findViewById(R.id.spHabitacion);
        swDesayuno = findViewById(R.id.swDesayuno);
        etHuespedes = findViewById(R.id.etHuespedes);

        /* Inicializar adaptadores */
        ArrayAdapter<CharSequence> adTipo = ArrayAdapter.createFromResource(
                this, R.array.tipos_array, android.R.layout.simple_spinner_item);
        adTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipo.setAdapter(adTipo);

        ArrayAdapter<CharSequence> adHab = ArrayAdapter.createFromResource(
                this, R.array.habitaciones_array, android.R.layout.simple_spinner_item);
        adHab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spHabitacion.setAdapter(adHab);

        /* Obtener datos de la reserva si estamos en edición */
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("reserva")) {
            reservaActual = intent.getParcelableExtra("reserva");
            indiceEdicion = intent.getIntExtra("indiceEdicion", -1);
            esEdicion = true;
            setTitle("Editar reserva");

            /* Llenar campos con los datos de la reserva */
            etCodigo.setText(reservaActual.getCodigo());
            etCliente.setText(reservaActual.getCliente());
            etEntrada.setText(reservaActual.getFechaEntrada());
            etSalida.setText(reservaActual.getFechaSalida());
            etPrecio.setText(String.valueOf(reservaActual.getPrecioTotal()));
            spTipo.setSelection(adTipo.getPosition(reservaActual.getTipo()));
            spHabitacion.setSelection(adHab.getPosition(reservaActual.getTipoHabitacion()));
            swDesayuno.setChecked(reservaActual.isIncluyeDesayuno());
            etHuespedes.setText(String.valueOf(reservaActual.getNumeroHuespedes()));
        } else {
            setTitle("Nueva reserva");
            reservaActual = new ReservaApi(); // en modo creación
        }

        /* Guardar datos */
        btnGuardar.setOnClickListener(v -> guardar());
    }
    private void llenarCampos(ReservaApi r) {
        etCodigo .setText(r.getCodigo());
        etCliente.setText(r.getCliente());
        etEntrada.setText(r.getFechaEntrada());
        etSalida .setText(r.getFechaSalida());
        etPrecio .setText(String.valueOf(r.getPrecioTotal()));
    }

    private void guardar() {
        reservaActual.setCodigo(etCodigo.getText().toString().trim());
        reservaActual.setCliente(etCliente.getText().toString().trim());
        reservaActual.setFechaEntrada(etEntrada.getText().toString().trim());
        reservaActual.setFechaSalida(etSalida.getText().toString().trim());
        reservaActual.setTipo(spTipo.getSelectedItem().toString());
        reservaActual.setTipoHabitacion(spHabitacion.getSelectedItem().toString());
        reservaActual.setIncluyeDesayuno(swDesayuno.isChecked());

        // Convertir el precio
        try {
            reservaActual.setPrecioTotal(Double.parseDouble(etPrecio.getText().toString()));
        } catch (NumberFormatException e) {
            reservaActual.setPrecioTotal(0); // Si no se puede convertir, asignar 0
        }

        // Convertir el número de huéspedes
        int numHuespedes = 0;
        try {
            numHuespedes = Integer.parseInt(etHuespedes.getText().toString());
        } catch (NumberFormatException e) {
            numHuespedes = 0; // Valor por defecto en caso de error
        }
        reservaActual.setNumeroHuespedes(numHuespedes);

        // Preparar la respuesta y devolverla
        Intent data = new Intent();
        data.putExtra("reserva", reservaActual);
        data.putExtra("esEdicion", esEdicion);
        data.putExtra("indiceEdicion", indiceEdicion);

        setResult(RESULT_OK, data);
        finish(); // Cerrar el formulario
    }

}
