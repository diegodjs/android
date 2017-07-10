package diegocompany.granacontrol.models;

/**
 * Created by Dih on 30/05/2017.
 */

public class DadosUsuario {

    //private String usuario;
    //private String senha;
    private String granaConta;
    private String alertaGasto;

    public DadosUsuario() {
    }

    public DadosUsuario(String granaConta, String alertaGasto) {
        //this.usuario = usuario;
        //this.senha = senha;
        this.granaConta = granaConta;
        this.alertaGasto = alertaGasto;
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
                ", granaConta=" + granaConta +
                ", alertaGasto=" + alertaGasto +
                '}';
    }
}
