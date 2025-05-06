package com.example.gestiondehospedaje.Modelos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class ReservaApi implements Parcelable {

    /* ------------------------------- Atributos ------------------------------- */
    @SerializedName("codigo")            private String codigo;
    @SerializedName("cliente")           private String cliente;
    @SerializedName("fechaEntrada")      private String fechaEntrada;
    @SerializedName("fechaSalida")       private String fechaSalida;
    @SerializedName("precioTotal")       private double precioTotal;
    @SerializedName("tipo")              private String tipo;
    @SerializedName("tipoHabitacion")    private String tipoHabitacion;
    @SerializedName("incluyeDesayuno")   private boolean incluyeDesayuno;
    @SerializedName("numeroHuespedes")   private int numeroHuespedes;
    @SerializedName("informacionAdicional")
    private InformacionAdicional informacionAdicional;

    /* ----------------------------- Constructores ---------------------------- */
    public ReservaApi() {}                               // vacío para Gson

    protected ReservaApi(Parcel in) {
        codigo           = in.readString();
        cliente          = in.readString();
        fechaEntrada     = in.readString();
        fechaSalida      = in.readString();
        precioTotal      = in.readDouble();
        tipo             = in.readString();
        tipoHabitacion   = in.readString();
        incluyeDesayuno  = in.readByte() != 0;
        numeroHuespedes  = in.readInt();
        informacionAdicional = in.readParcelable(InformacionAdicional.class.getClassLoader());
    }

    /* ------------------------------- Getters -------------------------------- */
    public String  getCodigo()          { return codigo; }
    public String  getCliente()         { return cliente; }
    public String  getFechaEntrada()    { return fechaEntrada; }
    public String  getFechaSalida()     { return fechaSalida; }
    public double  getPrecioTotal()     { return precioTotal; }
    public String  getTipo()            { return tipo; }
    public String  getTipoHabitacion()  { return tipoHabitacion; }
    public boolean isIncluyeDesayuno()  { return incluyeDesayuno; }
    public int     getNumeroHuespedes() { return numeroHuespedes; }
    public InformacionAdicional getInformacionAdicional() { return informacionAdicional; }

    /* ------------------------------- Setters -------------------------------- */
    public void setCodigo(String codigo)                     { this.codigo = codigo; }
    public void setCliente(String cliente)                   { this.cliente = cliente; }
    public void setFechaEntrada(String fechaEntrada)         { this.fechaEntrada = fechaEntrada; }
    public void setFechaSalida(String fechaSalida)           { this.fechaSalida = fechaSalida; }
    public void setPrecioTotal(double precioTotal)           { this.precioTotal = precioTotal; }
    public void setTipo(String tipo)                         { this.tipo = tipo; }
    public void setTipoHabitacion(String tipoHabitacion)     { this.tipoHabitacion = tipoHabitacion; }
    public void setIncluyeDesayuno(boolean incluyeDesayuno)  { this.incluyeDesayuno = incluyeDesayuno; }
    public void setNumeroHuespedes(int numeroHuespedes)      { this.numeroHuespedes = numeroHuespedes; }
    public void setInformacionAdicional(InformacionAdicional info) { this.informacionAdicional = info; }

    /* --------------------------- Parcelable stuff --------------------------- */
    @Override public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codigo);
        dest.writeString(cliente);
        dest.writeString(fechaEntrada);
        dest.writeString(fechaSalida);
        dest.writeDouble(precioTotal);
        dest.writeString(tipo);
        dest.writeString(tipoHabitacion);
        dest.writeByte((byte) (incluyeDesayuno ? 1 : 0));
        dest.writeInt(numeroHuespedes);
        dest.writeParcelable(informacionAdicional, flags);
    }

    public static final Creator<ReservaApi> CREATOR = new Creator<ReservaApi>() {
        @Override public ReservaApi createFromParcel(Parcel in) { return new ReservaApi(in); }
        @Override public ReservaApi[] newArray(int size) { return new ReservaApi[size]; }
    };

    /* =====================  Clases internas parcelables  ==================== */
    public static class InformacionAdicional implements Parcelable {
        @SerializedName("esperanzaVida") private int esperanzaVida;
        @SerializedName("datos")         private List<DatoAdicional> datos;

        public InformacionAdicional() {}

        protected InformacionAdicional(Parcel in) {
            esperanzaVida = in.readInt();
            datos = new ArrayList<>();
            in.readTypedList(datos, DatoAdicional.CREATOR);
        }

        public int getEsperanzaVida()               { return esperanzaVida; }
        public List<DatoAdicional> getDatos()       { return datos; }
        public void setEsperanzaVida(int v)         { this.esperanzaVida = v; }
        public void setDatos(List<DatoAdicional> d) { this.datos = d; }

        /* Parcelable */
        @Override public int describeContents() { return 0; }
        @Override public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(esperanzaVida);
            dest.writeTypedList(datos);
        }
        public static final Creator<InformacionAdicional> CREATOR =
                new Creator<InformacionAdicional>() {
                    @Override public InformacionAdicional createFromParcel(Parcel in) {
                        return new InformacionAdicional(in);
                    }
                    @Override public InformacionAdicional[] newArray(int size) {
                        return new InformacionAdicional[size];
                    }
                };
    }

    public static class DatoAdicional implements Parcelable {
        @SerializedName("nombreDato") private String nombreDato;
        @SerializedName("valor")      private String valor;

        public DatoAdicional() {}

        protected DatoAdicional(Parcel in) {
            nombreDato = in.readString();
            valor      = in.readString();
        }

        public String getNombreDato() { return nombreDato; }
        public String getValor()      { return valor; }
        public void setNombreDato(String n) { this.nombreDato = n; }
        public void setValor(String v)      { this.valor = v; }

        /* Parcelable */
        @Override public int describeContents() { return 0; }
        @Override public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(nombreDato);
            dest.writeString(valor);
        }
        public static final Creator<DatoAdicional> CREATOR =
                new Creator<DatoAdicional>() {
                    @Override public DatoAdicional createFromParcel(Parcel in) {
                        return new DatoAdicional(in);
                    }
                    @Override public DatoAdicional[] newArray(int size) {
                        return new DatoAdicional[size];
                    }
                };
    }
}
