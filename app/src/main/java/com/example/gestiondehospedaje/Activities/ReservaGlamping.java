package com.example.gestiondehospedaje.Activities;
import java.util.List;
public class ReservaGlamping extends ReservaCabana {
    private String tipoExperiencia;
    private List<String> actividadesIncluidas;

    public ReservaGlamping(String codigo, String cliente, String fechaEntrada, String fechaSalida, double precioTotal,
                           int metrosCuadrados, boolean tieneChimenea, int capacidadMaxima,
                           String tipoExperiencia, List<String> actividadesIncluidas) {
        super(codigo, cliente, fechaEntrada, fechaSalida, precioTotal, metrosCuadrados, tieneChimenea, capacidadMaxima);
        this.tipoExperiencia = tipoExperiencia;
        this.actividadesIncluidas = actividadesIncluidas;
    }

    public String getTipoExperiencia() {
        return tipoExperiencia;
    }

    public List<String> getActividadesIncluidas() {
        return actividadesIncluidas;
    }

    @Override
    public String getTipo() {
        return "glamping";
    }
}