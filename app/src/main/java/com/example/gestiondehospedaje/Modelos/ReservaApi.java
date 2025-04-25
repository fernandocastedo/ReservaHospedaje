package com.example.gestiondehospedaje.Modelos;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ReservaApi {
    @SerializedName("codigo")
    private String codigo;
    
    @SerializedName("cliente")
    private String cliente;
    
    @SerializedName("fechaEntrada")
    private String fechaEntrada;
    
    @SerializedName("fechaSalida")
    private String fechaSalida;
    
    @SerializedName("precioTotal")
    private double precioTotal;

    @SerializedName("tipo")
    private String tipo;

    @SerializedName("tipoHabitacion")
    private String tipoHabitacion;

    @SerializedName("incluyeDesayuno")
    private boolean incluyeDesayuno;

    @SerializedName("numeroHuespedes")
    private int numeroHuespedes;

    @SerializedName("informacionAdicional")
    private InformacionAdicional informacionAdicional;

    // Constructor vacío necesario para Gson
    public ReservaApi() {}

    // Getters
    public String getCodigo() { return codigo; }
    public String getCliente() { return cliente; }
    public String getFechaEntrada() { return fechaEntrada; }
    public String getFechaSalida() { return fechaSalida; }
    public double getPrecio() { return precioTotal; }
    public double getPrecioTotal() { return precioTotal; }
    public String getTipo() { return tipo; }
    public String getTipoHabitacion() { return tipoHabitacion; }
    public boolean isIncluyeDesayuno() { return incluyeDesayuno; }
    public int getNumeroHuespedes() { return numeroHuespedes; }
    public InformacionAdicional getInformacionAdicional() { return informacionAdicional; }

    // Setters
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public void setFechaEntrada(String fechaEntrada) { this.fechaEntrada = fechaEntrada; }
    public void setFechaSalida(String fechaSalida) { this.fechaSalida = fechaSalida; }
    public void setPrecioTotal(double precioTotal) { this.precioTotal = precioTotal; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setTipoHabitacion(String tipoHabitacion) { this.tipoHabitacion = tipoHabitacion; }
    public void setIncluyeDesayuno(boolean incluyeDesayuno) { this.incluyeDesayuno = incluyeDesayuno; }
    public void setNumeroHuespedes(int numeroHuespedes) { this.numeroHuespedes = numeroHuespedes; }
    public void setInformacionAdicional(InformacionAdicional informacionAdicional) { this.informacionAdicional = informacionAdicional; }

    public static class InformacionAdicional {
        @SerializedName("esperanzaVida")
        private int esperanzaVida;

        @SerializedName("datos")
        private List<DatoAdicional> datos;

        public int getEsperanzaVida() { return esperanzaVida; }
        public List<DatoAdicional> getDatos() { return datos; }

        public void setEsperanzaVida(int esperanzaVida) { this.esperanzaVida = esperanzaVida; }
        public void setDatos(List<DatoAdicional> datos) { this.datos = datos; }
    }
}
