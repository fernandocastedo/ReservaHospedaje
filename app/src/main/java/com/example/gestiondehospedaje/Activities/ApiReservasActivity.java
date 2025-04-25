package com.example.gestiondehospedaje.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestiondehospedaje.Api.ApiService;
import com.example.gestiondehospedaje.Modelos.ReservaApi;
import com.example.gestiondehospedaje.Modelos.ReservaApiResponse;
import com.example.gestiondehospedaje.Adaptadores.ReservaApiAdapter;
import com.example.gestiondehospedaje.R;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

public class ApiReservasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnCargar;
    private static final String BASE_URL = "https://raw.githubusercontent.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_reservas);

        recyclerView = findViewById(R.id.recyclerReservasApi);
        btnCargar = findViewById(R.id.btnCargarReservas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnCargar.setOnClickListener(v -> cargarReservasDesdeApi());
    }

    private void cargarReservasDesdeApi() {
        btnCargar.setEnabled(false);
        Toast.makeText(this, "Cargando reservas...", Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<ReservaApiResponse> call = apiService.getReservas();
        call.enqueue(new Callback<ReservaApiResponse>() {
            @Override
            public void onResponse(Call<ReservaApiResponse> call, Response<ReservaApiResponse> response) {
                btnCargar.setEnabled(true);
                if (response.isSuccessful() && response.body() != null) {
                    List<ReservaApi> reservas = response.body().getReservas();
                    if (reservas != null && !reservas.isEmpty()) {
                        ReservaApiAdapter adapter = new ReservaApiAdapter(ApiReservasActivity.this, reservas);
                        recyclerView.setAdapter(adapter);
                        Toast.makeText(ApiReservasActivity.this, 
                            "Reservas cargadas: " + reservas.size(), 
                            Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ApiReservasActivity.this, 
                            "No hay reservas disponibles", 
                            Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String errorBody = "";
                    try {
                        if (response.errorBody() != null) {
                            errorBody = response.errorBody().string();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(ApiReservasActivity.this, 
                        "Error en la respuesta: " + response.code() + " - " + errorBody, 
                        Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ReservaApiResponse> call, Throwable t) {
                btnCargar.setEnabled(true);
                String errorMessage = "Error de conexión: ";
                if (t instanceof IOException) {
                    errorMessage += "Verifica tu conexión a internet";
                } else {
                    errorMessage += t.getMessage();
                }
                Toast.makeText(ApiReservasActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }
}