package com.example.gestiondehospedaje.Repository;
import java.util.ArrayList;
import java.util.List;
import com.example.gestiondehospedaje.Modelos.ReservaApi;
public class ReservaRepository {
    private static ReservaRepository instance;
    private final List<ReservaApi> reservas = new ArrayList<>();

    private ReservaRepository() {}                       // ctor privado

    public static synchronized ReservaRepository getInstance() {
        if (instance == null) instance = new ReservaRepository();
        return instance;
    }

    /* GET & SET  ------------------------------------------------------- */
    public List<ReservaApi> getReservas() { return reservas; }
    public void setFromApi(List<ReservaApi> data) {
        reservas.clear(); reservas.addAll(data);
    }

    /* CRUD local -------------------------------------------------------- */
    public void add(ReservaApi r)            { reservas.add(r); }
    public void update(int idx, ReservaApi r){ reservas.set(idx, r); }
    public void remove(int idx)              { reservas.remove(idx); }
}
