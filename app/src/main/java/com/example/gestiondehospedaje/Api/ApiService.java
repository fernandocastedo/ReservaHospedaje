package com.example.gestiondehospedaje.Api;
import com.example.gestiondehospedaje.Modelos.ReservaApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("adancondori/TareaAPI/main/api/reservas.json")
    Call<ReservaApiResponse> getReservas();
}