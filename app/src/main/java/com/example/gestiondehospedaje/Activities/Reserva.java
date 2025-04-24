package com.example.gestiondehospedaje.Activities;
import java.io.Serializable; //se implementa esto para pasar un objeto entre actividades se implementa la interfaz Serializable
public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;
    public String codigo;
    public String cliente;
    public String fechaEntrada;
    public String fechaSalida;
    public double precioTotal;

    public Reserva(String codigo, String cliente, String fechaEntrada, String fechaSalida, double precioTotal) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.precioTotal = precioTotal;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
    public String getTipo() {
        return "reserva"; // valor genérico, las subclases lo sobrescriben
    }
}
