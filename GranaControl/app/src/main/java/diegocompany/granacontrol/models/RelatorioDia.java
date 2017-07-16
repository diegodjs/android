package diegocompany.granacontrol.models;

/**
 * Created by Dih on 30/05/2017.
 */

public class RelatorioDia {

    private String dia;
    private String totalEntradaDia;
    private String totalSaidaDia;

    public RelatorioDia() {
    }

    public RelatorioDia(String dia, String totalEntradaDia, String totalSaidaDia) {
        this.dia = dia;
        this.totalEntradaDia = totalEntradaDia;
        this.totalSaidaDia = totalSaidaDia;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getTotalEntradaDia() {
        return totalEntradaDia;
    }

    public void setTotalEntradaDia(String totalEntradaDia) {
        this.totalEntradaDia = totalEntradaDia;
    }

    public String getTotalSaidaDia() {
        return totalSaidaDia;
    }

    public void setTotalSaidaDia(String totalSaidaDia) {
        this.totalSaidaDia = totalSaidaDia;
    }

    @Override
    public String toString() {
        return "RelatorioDia{" +
                "dia='" + dia + '\'' +
                ", totalEntradaDia='" + totalEntradaDia + '\'' +
                ", totalSaidaDia='" + totalSaidaDia + '\'' +
                '}';
    }
}
