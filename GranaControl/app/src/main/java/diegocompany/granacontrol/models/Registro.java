package diegocompany.granacontrol.models;

import java.io.Serializable;


public class Registro implements Serializable{

    private String id;
    private String entrada;
    private String saida;
    private String descricao;

    public Registro() {
    }

    public Registro(String id, String entrada, String saida, String descricao) {
        this.id = id;
        this.entrada = entrada;
        this.saida = saida;
        this.descricao = descricao;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "id=" + id +
                ", entrada='" + entrada + '\'' +
                ", saida='" + saida + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
