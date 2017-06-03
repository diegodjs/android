package diegocompany.granacontrol.models;

/**
 * Created by Dih on 30/05/2017.
 */

public class Relatorio {

    private String ano;
    private String mes;
    private String totalEntrada;
    private String totalSaida;
    private String totalGeral;

    public Relatorio() {
    }

    public Relatorio(String ano, String mes, String totalEntrada, String totalSaida, String totalGeral) {
        this.ano = ano;
        this.mes = mes;
        this.totalEntrada = totalEntrada;
        this.totalSaida = totalSaida;
        this.totalGeral = totalGeral;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
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

    public String getTotalGeral() {
        return totalGeral;
    }

    public void setTotalGeral(String totalGeral) {
        this.totalGeral = totalGeral;
    }

    @Override
    public String toString() {
        return "Relatorio{" +
                "ano='" + ano + '\'' +
                ", mes='" + mes + '\'' +
                ", totalEntrada='" + totalEntrada + '\'' +
                ", totalSaida='" + totalSaida + '\'' +
                ", totalGeral='" + totalGeral + '\'' +
                '}';
    }
}
