package com.example.gestiondehospedaje.Activities;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gestiondehospedaje.R;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_principal);

        // Aplica padding según las barras del sistema (opcional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //  Lógica del botón de navegación (fuera del listener de padding)
        Button btnVerReservas = findViewById(R.id.btnVerReservas);
        btnVerReservas.setOnClickListener(v -> {
            Intent intent = new Intent(MenuPrincipalActivity.this, MainActivity.class);
            startActivity(intent);
        });
        Button btnHospedajesApi = findViewById(R.id.btnHospedajesApi);
        btnHospedajesApi.setOnClickListener(v -> {
            Intent intent = new Intent(MenuPrincipalActivity.this, ApiReservasActivity.class);
            startActivity(intent);
        });
    }
}