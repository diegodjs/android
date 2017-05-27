package diegocompany.granacontrol.models;

import java.io.Serializable;

public class Registro implements Serializable{

    private boolean checked;
    private String entrada;
    private String saida;
    private String descricao;

    public Registro() {
    }

    public Registro(boolean checked, String entrada, String saida, String descricao) {
        this.checked = checked;
        this.entrada = entrada;
        this.saida = saida;
        this.descricao = descricao;
    }


    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSaida() {
        return saida;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEntrada() {
        return entrada;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "checked='" + checked + '\'' +
                "entrada='" + entrada + '\'' +
                ", saida='" + saida + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
