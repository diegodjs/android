package diegocompany.granacontrol.models;

/**
 * Created by Dih on 30/05/2017.
 */

public class DadosUsuario {

    private String usuario;
    private String senha;
    private int anoInicial;
    private int mesInicial;
    private String granaConta;
    private String alertaGasto;

    public DadosUsuario() {
    }

    public DadosUsuario(String usuario, String senha, int anoInicial, int mesInicial, String granaConta, String alertaGasto) {
        this.usuario = usuario;
        this.senha = senha;
        this.anoInicial = anoInicial;
        this.mesInicial = mesInicial;
        this.granaConta = granaConta;
        this.alertaGasto = alertaGasto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getAnoInicial() {
        return anoInicial;
    }

    public void setAnoInicial(int anoInicial) {
        this.anoInicial = anoInicial;
    }

    public int getMesInicial() {
        return mesInicial;
    }

    public void setMesInicial(int mesInicial) {
        this.mesInicial = mesInicial;
    }

    public String getGranaConta() {
        return granaConta;
    }

    public void setGranaConta(String granaConta) {
        this.granaConta = granaConta;
    }

    public String getAlertaGasto() {
        return alertaGasto;
    }

    public void setAlertaGasto(String alertaGasto) {
        this.alertaGasto = alertaGasto;
    }

    @Override
    public String toString() {
        return "DadosUsuario{" +
                "usuario='" + usuario + '\'' +
                ", senha='" + senha + '\'' +
                ", anoInicial=" + anoInicial +
                ", mesInicial=" + mesInicial +
                ", granaConta=" + granaConta +
                ", alertaGasto=" + alertaGasto +
                '}';
    }
}