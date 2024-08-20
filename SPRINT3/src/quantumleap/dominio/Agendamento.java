package quantumleap.dominio;

public class Agendamento {
    private Diagnostico diagnostico;
    private String data;
    private String hora;
    private String localizacao;

    public Agendamento(Diagnostico diagnostico, String data, String hora) {
        this.diagnostico = diagnostico;
        this.data = data;
        this.hora = hora;
    }

    public String getData() {
        return data;
    }

    public String getHora(){
        return hora;
    }

    public void setLocalizacao(String localizacao){
        this.localizacao = localizacao;
    }

    public String getLocalizacao(){
        return localizacao;
    }
}
