package diegocompany.granacontrol.models;


public class DadosAlertas {

    private String alertaGastoDiario;
    private String alertaGastoMensal;

    public DadosAlertas() {
    }

    public DadosAlertas(String alertaGastoDiario, String alertaGastoMensal) {
        this.alertaGastoDiario = alertaGastoDiario;
        this.alertaGastoMensal = alertaGastoMensal;
    }

    public String getAlertaGastoDiario() {
        return alertaGastoDiario;
    }

    public void setAlertaGastoDiario(String alertaGastoDiario) {
        this.alertaGastoDiario = alertaGastoDiario;
    }

    public String getAlertaGastoMensal() {
        return alertaGastoMensal;
    }

    public void setAlertaGastoMensal(String alertaGastoMensal) {
        this.alertaGastoMensal = alertaGastoMensal;
    }

    @Override
    public String toString() {
        return "DadosAlertas{" +
                "alertaGastoDiario='" + alertaGastoDiario + '\'' +
                ", alertaGastoMensal='" + alertaGastoMensal + '\'' +
                '}';
    }
}
