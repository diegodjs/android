package diegocompany.granacontrol.models;

/**
 * Created by Dih on 30/05/2017.
 */

public class Relatorio {

    private int ano;
    private int mes;
    private int dia;
    private String totalEntrada;
    private String totalSaida;

    public Relatorio() {
    }

    public Relatorio(int ano, int mes, int dia, String totalEntrada, String totalSaida) {
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
        this.totalEntrada = totalEntrada;
        this.totalSaida = totalSaida;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getTotalEntrada() {
        return totalEntrada;
    }

    public void setTotalEntrada(String totalEntrada) {
        this.totalEntrada = totalEntrada;
    }

    public String getTotalSaida() {
        return totalSaida;
    }

    public void setTotalSaida(String totalSaida) {
        this.totalSaida = totalSaida;
    }

    @Override
    public String toString() {
        return "Relatorio{" +
                "ano=" + ano +
                ", mes=" + mes +
                ", dia=" + dia +
                ", totalEntrada=" + totalEntrada +
                ", totalSaida=" + totalSaida +
                '}';
    }
}
