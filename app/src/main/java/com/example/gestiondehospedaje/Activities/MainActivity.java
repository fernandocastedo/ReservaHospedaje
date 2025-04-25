package com.example.gestiondehospedaje.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestiondehospedaje.Adaptadores.ReservaAdapter;
import com.example.gestiondehospedaje.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private ReservaAdapter adapter;
    private List<Reserva> listaReservas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            inicializarViews();
            cargarDatos();
        } catch (Exception e) {
            Log.e(TAG, "Error en onCreate: " + e.getMessage(), e);
            Toast.makeText(this, "Error al inicializar la aplicación", Toast.LENGTH_LONG).show();
        }
    }

    private void inicializarViews() {
        recyclerView = findViewById(R.id.recyclerReservas);
        if (recyclerView == null) {
            throw new IllegalStateException("No se pudo encontrar el RecyclerView");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void cargarDatos() {
        listaReservas = new ArrayList<>();
        try {

            listaReservas.add(new ReservaHotel("R001", "Carlos Pérez", "2025-05-01", "2025-05-05", 500.0, "Suite", true, 2));
            listaReservas.add(new ReservaHotel("R002", "Ana Torres", "2025-08-01", "2025-08-03", 420.0, "Doble estándar", false, 2));
            listaReservas.add(new ReservaHotel("R003", "Mario López", "2025-06-12", "2025-06-14", 390.0, "King Size", true, 3));
            listaReservas.add(new ReservaHotel("R004", "Lucía Flores", "2025-07-18", "2025-07-20", 450.0, "Familiar", true, 4));
            listaReservas.add(new ReservaHotel("R005", "Camila Romero", "2025-09-25", "2025-09-28", 530.0, "Suite Junior", true, 2));


            listaReservas.add(new ReservaCabana("R006", "Laura Gómez", "2025-06-10", "2025-06-15", 300.0, 60, true, 4));
            listaReservas.add(new ReservaCabana("R007", "David López", "2025-09-15", "2025-09-18", 280.0, 50, false, 3));
            listaReservas.add(new ReservaCabana("R008", "Pedro Castillo", "2025-07-01", "2025-07-05", 350.0, 65, true, 5));
            listaReservas.add(new ReservaCabana("R009", "Marcela Díaz", "2025-08-10", "2025-08-13", 270.0, 55, false, 2));
            listaReservas.add(new ReservaCabana("R010", "Esteban Ruiz", "2025-10-01", "2025-10-04", 320.0, 70, true, 6));


            listaReservas.add(new ReservaGlamping("R011", "Fernando Rojas", "2025-07-20", "2025-07-22", 700.0,
                    40, false, 2, "Romántica", Arrays.asList("Yoga", "Cabalgata", "Fogata nocturna"), 850, R.drawable.glamping2));
            listaReservas.add(new ReservaGlamping("R012", "Fernando Castedo", "2025-10-10", "2025-10-13", 1000.0,
                    70, true, 2, "Aventura", Arrays.asList("Senderismo", "Rappel"), 950, R.drawable.glamping1));
            listaReservas.add(new ReservaGlamping("R013", "Paola Méndez", "2025-11-01", "2025-11-03", 780.0,
                    45, true, 3, "Wellness", Arrays.asList("Meditación", "Baños termales"), 890, R.drawable.glamping3));
            listaReservas.add(new ReservaGlamping("R014", "Javier Aguilar", "2025-08-22", "2025-08-24", 850.0,
                    50, false, 2, "Exploración", Arrays.asList("Escalada", "Tirolesa"), 930, R.drawable.glamping4));
            listaReservas.add(new ReservaGlamping("R015", "Andrea Suárez", "2025-09-10", "2025-09-12", 920.0,
                    60, true, 2, "Luna de miel", Arrays.asList("Cena gourmet", "Spa", "Caminata al atardecer"), 1000, R.drawable.glamping5));

            adapter = new ReservaAdapter(this, listaReservas);
            recyclerView.setAdapter(adapter);
            
            if (adapter == null) {
                throw new IllegalStateException("No se pudo crear el adaptador");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error al cargar los datos: " + e.getMessage(), e);
            Toast.makeText(this, "Error al cargar los datos", Toast.LENGTH_LONG).show();
        }
    }
}