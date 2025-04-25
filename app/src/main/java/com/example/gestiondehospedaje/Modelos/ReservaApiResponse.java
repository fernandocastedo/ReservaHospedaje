package com.example.gestiondehospedaje.Modelos;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ReservaApiResponse {
    @SerializedName("page")
    private int page;

    @SerializedName("total")
    private int total;

    @SerializedName("status")
    private String status;

    @SerializedName("reservas")
    private List<ReservaApi> reservas;

    public int getPage() {
        return page;
    }

    public int getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public List<ReservaApi> getReservas() {
        return reservas;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReservas(List<ReservaApi> reservas) {
        this.reservas = reservas;
    }
} 