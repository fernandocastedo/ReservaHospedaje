package com.example.gestiondehospedaje.Activities;

public class ReservaCabana extends Reserva {
    private int metrosCuadrados;
    private boolean tieneChimenea;
    private int capacidadMaxima;

    public ReservaCabana(String codigo, String cliente, String fechaEntrada, String fechaSalida, double precioTotal,
                         int metrosCuadrados, boolean tieneChimenea, int capacidadMaxima) {
        super(codigo, cliente, fechaEntrada, fechaSalida, precioTotal);
        this.metrosCuadrados = metrosCuadrados;
        this.tieneChimenea = tieneChimenea;
        this.capacidadMaxima = capacidadMaxima;
    }

    public int getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public boolean isTieneChimenea() {
        return tieneChimenea;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    @Override
    public String getTipo() {
        return "cabana";
    }
}
