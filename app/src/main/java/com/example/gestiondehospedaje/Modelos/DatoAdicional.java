package com.example.gestiondehospedaje.Modelos;

import com.google.gson.annotations.SerializedName;

public class DatoAdicional {
    @SerializedName("nombreDato")
    private String nombreDato;

    @SerializedName("valor")
    private String valor;

    public String getNombreDato() {
        return nombreDato;
    }

    public String getValor() {
        return valor;
    }

    public void setNombreDato(String nombreDato) {
        this.nombreDato = nombreDato;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
} 