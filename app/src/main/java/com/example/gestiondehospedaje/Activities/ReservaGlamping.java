package com.example.gestiondehospedaje.Activities;
import java.util.List;
public class ReservaGlamping extends ReservaCabana {
    private String tipoExperiencia;
    private List<String> actividadesIncluidas;
    private int precio;
    private int imagenResourceId; // Referencia a imagen en drawable

    public ReservaGlamping(String codigo, String cliente, String fechaEntrada, String fechaSalida, double precioTotal,
                           int metrosCuadrados, boolean tieneChimenea, int capacidadMaxima,
                           String tipoExperiencia, List<String> actividadesIncluidas,
                           int precio, int imagenResourceId) {

        super(codigo, cliente, fechaEntrada, fechaSalida, precioTotal, metrosCuadrados, tieneChimenea, capacidadMaxima);

        this.tipoExperiencia = tipoExperiencia;
        this.actividadesIncluidas = actividadesIncluidas;
        this.precio = precio;
        this.imagenResourceId = imagenResourceId;
    }

    public String getTipoExperiencia() {
        return tipoExperiencia;
    }

    public List<String> getActividadesIncluidas() {
        return actividadesIncluidas;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getImagenResourceId() {
        return imagenResourceId;
    }

    public void setImagenResourceId(int imagenResourceId) {
        this.imagenResourceId = imagenResourceId;
    }

    @Override
    public String getTipo() {
        return "glamping";
    }
}