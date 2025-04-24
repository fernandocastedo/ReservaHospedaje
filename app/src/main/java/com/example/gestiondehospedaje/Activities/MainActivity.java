package com.example.gestiondehospedaje.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestiondehospedaje.Adaptadores.ReservaAdapter;
import com.example.gestiondehospedaje.R;
import com.example.gestiondehospedaje.Activities.Reserva;
import com.example.gestiondehospedaje.Activities.ReservaCabana;
import com.example.gestiondehospedaje.Activities.ReservaGlamping;
import com.example.gestiondehospedaje.Activities.ReservaHotel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReservaAdapter adapter;
    private List<Reserva> listaReservas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerReservas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaReservas = obtenerReservasEjemplo();

        adapter = new ReservaAdapter(this, listaReservas);
        recyclerView.setAdapter(adapter);
    }

    private List<Reserva> obtenerReservasEjemplo() {
        List<Reserva> lista = new ArrayList<>();

        lista.add(new ReservaHotel(
                "R001", "Carlos Pérez", "2025-05-01", "2025-05-05", 500.0,
                "Suite", true, 2));

        lista.add(new ReservaCabana(
                "R002", "Laura Gómez", "2025-06-10", "2025-06-15", 300.0,
                60, true, 4));

        lista.add(new ReservaGlamping(
                "R003", "Fernando Rojas", "2025-07-20", "2025-07-22", 700.0,
                40, false, 2,
                "Romántica", Arrays.asList("Yoga", "Cabalgata", "Fogata nocturna")));

        lista.add(new ReservaHotel(
                "R004", "Ana Torres", "2025-08-01", "2025-08-03", 420.0,
                "Doble estándar", false, 2));

        lista.add(new ReservaCabana(
                "R005", "David López", "2025-09-15", "2025-09-18", 280.0,
                50, false, 3));

        return lista;
    }
}