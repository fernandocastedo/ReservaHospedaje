package com.example.gestiondehospedaje.Activities;

public class ReservaHotel extends Reserva {
    private String tipoHabitacion;
    private boolean incluyeDesayuno;
    private int numeroHuespedes;

    public ReservaHotel(String codigo, String cliente, String fechaEntrada, String fechaSalida, double precioTotal,
                        String tipoHabitacion, boolean incluyeDesayuno, int numeroHuespedes) {
        super(codigo, cliente, fechaEntrada, fechaSalida, precioTotal);
        this.tipoHabitacion = tipoHabitacion;
        this.incluyeDesayuno = incluyeDesayuno;
        this.numeroHuespedes = numeroHuespedes;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public boolean isIncluyeDesayuno() {
        return incluyeDesayuno;
    }

    public int getNumeroHuespedes() {
        return numeroHuespedes;
    }

    @Override
    public String getTipo() {
        return "hotel";
    }
}