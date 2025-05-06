package com.example.gestiondehospedaje.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestiondehospedaje.Adaptadores.ReservaApiAdapter;
import com.example.gestiondehospedaje.Api.ApiService;
import com.example.gestiondehospedaje.Modelos.ReservaApi;
import com.example.gestiondehospedaje.Modelos.ReservaApiResponse;
import com.example.gestiondehospedaje.R;
import com.example.gestiondehospedaje.Repository.ReservaRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiReservasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReservaApiAdapter adapter;
    private Button btnCargar;
    private List<ReservaApi> reservas;
    private FloatingActionButton fabAdd;
    private ActivityResultLauncher<Intent> formLauncher;

    private static final String BASE_URL = "https://raw.githubusercontent.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_reservas);

        recyclerView = findViewById(R.id.recyclerReservasApi);
        btnCargar    = findViewById(R.id.btnCargarReservas);
        fabAdd       = findViewById(R.id.fabAdd);
        reservas = new ArrayList<>();

        // LayoutManager (faltaba)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Adapter inicial (lista viva del repositorio)
        adapter = new ReservaApiAdapter(
                this,
                reservas,
                (index, reserva) -> {              // callback long‑press
                    Intent i = new Intent(this, FormularioReservaActivity.class);
                    i.putExtra("reserva", reserva);
                    i.putExtra("indiceEdicion", index);
                    formLauncher.launch(i);
                });
        recyclerView.setAdapter(adapter);

        attachSwipeToDelete();     // swipe‑to‑delete
        registerFormLauncher();    // activity result

        btnCargar.setOnClickListener(v -> cargarReservasDesdeApi());
        fabAdd.setOnClickListener(v -> formLauncher.launch(
                new Intent(this, FormularioReservaActivity.class))); // crear nueva
    }

    /* ---------------- Cargar datos desde la API ---------------- */
    private void cargarReservasDesdeApi() {
        btnCargar.setEnabled(false);
        Toast.makeText(this, "Cargando reservas...", Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getReservas().enqueue(new Callback<ReservaApiResponse>() {
            @Override
            public void onResponse(Call<ReservaApiResponse> call,
                                   Response<ReservaApiResponse> response) {
                btnCargar.setEnabled(true);
                if (response.isSuccessful() && response.body() != null) {

                    List<ReservaApi> reservas = response.body().getReservas();
                    if (reservas == null) reservas = new ArrayList<>();

                    // Actualizar repositorio y notificar
                    ReservaRepository.getInstance().setFromApi(reservas);
                    adapter.notifyDataSetChanged();

                    Toast.makeText(ApiReservasActivity.this,
                            "Reservas cargadas: " + reservas.size(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    mostrarErrorRespuesta(response);
                }
            }

            @Override
            public void onFailure(Call<ReservaApiResponse> call, Throwable t) {
                btnCargar.setEnabled(true);
                String msg = (t instanceof IOException)
                        ? "Verifica tu conexión a internet"
                        : t.getMessage();
                Toast.makeText(ApiReservasActivity.this,
                        "Error de conexión: " + msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    /* ------------------------------------------------------------- */
    /* Helpers                                                       */
    /* ------------------------------------------------------------- */
    private void mostrarErrorRespuesta(Response<?> response) {
        String errorBody = "";
        try {
            if (response.errorBody() != null) errorBody = response.errorBody().string();
        } catch (IOException e) { e.printStackTrace(); }

        Toast.makeText(this,
                "Error en la respuesta: " + response.code() + " - " + errorBody,
                Toast.LENGTH_LONG).show();
    }

    private void attachSwipeToDelete() {
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    @Override public boolean onMove(@NonNull RecyclerView rv,
                                                    @NonNull RecyclerView.ViewHolder v,
                                                    @NonNull RecyclerView.ViewHolder t) {
                        return false;
                    }

                    @Override public void onSwiped(@NonNull RecyclerView.ViewHolder vh, int dir) {
                        int pos = vh.getAdapterPosition();
                        new AlertDialog.Builder(ApiReservasActivity.this)
                                .setTitle("Eliminar reserva")
                                .setMessage("¿Seguro que deseas eliminarla?")
                                .setPositiveButton("Sí", (d,i) -> {
                                    ReservaRepository.getInstance().remove(pos);
                                    adapter.notifyItemRemoved(pos);
                                })
                                .setNegativeButton("Cancelar", (d,i) -> adapter.notifyItemChanged(pos))
                                .show();
                    }
                });
        helper.attachToRecyclerView(recyclerView);
    }

    private void registerFormLauncher() {
        formLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::handleFormResult
        );
    }

    private void handleFormResult(ActivityResult result) {
        if (result.getResultCode() != RESULT_OK || result.getData() == null) return;

        Intent data = result.getData();
        boolean isEdit = data.getBooleanExtra("esEdicion", false);
        int index      = data.getIntExtra("indiceEdicion", -1);
        ReservaApi reserva = data.getParcelableExtra("reserva");

        if (reserva == null) return;

        if (isEdit && index >= 0) {
            ReservaRepository.getInstance().update(index, reserva);
        } else {
            ReservaRepository.getInstance().add(reserva);
        }
        adapter.notifyDataSetChanged();
    }
}
