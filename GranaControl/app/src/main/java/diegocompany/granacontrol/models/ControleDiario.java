package diegocompany.granacontrol.models;


import java.util.ArrayList;
import java.util.List;

public class ControleDiario {

    private List<Registro> registros = new ArrayList<Registro>();

    public ControleDiario() {
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    @Override
    public String toString() {
        return "ControleDiario{" +
                "registros=" + registros +
                '}';
    }
}
