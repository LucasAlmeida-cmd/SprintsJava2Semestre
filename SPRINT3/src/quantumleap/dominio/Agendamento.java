package quantumleap.dominio;

import quantumleap.banco.AgendamentoDAO;

import java.util.ArrayList;

public class Agendamento {
    private long idAgendamento;
    private Diagnostico diagnostico;
    private String data;
    private String hora;
    private String localizacao;
    private Oficina oficina;


    public Agendamento(Diagnostico diagnostico, String data, String hora, Oficina oficina) {
        this.diagnostico = diagnostico;
        this.data = data;
        this.hora = hora;
        this.oficina = oficina;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

//
//    private static final AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
//
//    public boolean verificaAgendamento() {
//        ArrayList<Agendamento> agenda = agendamentoDAO.listarAgendamentos();
//        for (Agendamento agendamento : agenda) {
//            if (agendamento.getData().equals(this.getData()) && agendamento.getHora().equals(this.getHora())) {
//                throw new IllegalArgumentException("Data e Hora já existentes");
//            }
//        }
//        return true;
//    }



    public boolean verificaAgendamento(ArrayList<Agendamento> agendaParaVerificacao) {
        for (Agendamento agendamento : agendaParaVerificacao) {
            if (agendamento.getData().equals(this.getData()) && agendamento.getHora().equals(this.getHora())) {
                throw new IllegalArgumentException("Data e Hora já existentes");
            }
        }
        return true;
    }



    public long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(long idAgendamento) {
        this.idAgendamento = idAgendamento;
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

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


    public void setIdCliente(long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }
}
