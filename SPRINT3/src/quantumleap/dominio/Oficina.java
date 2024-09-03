package quantumleap.dominio;

import java.util.ArrayList;

public class Oficina {
    private String nomeOficina;
    private String localizacaoOficina;
    private String telefoneOficina;
    private String emailOficina;
    private ArrayList<Agendamento> agendamentos;


    public Oficina(String nomeOficina, String localizacaoOficina, String telefoneOficina, String emailOficina, ArrayList<Agendamento> agendamentos) {
        this.nomeOficina = nomeOficina;
        this.localizacaoOficina = localizacaoOficina;
        this.telefoneOficina = telefoneOficina;
        this.emailOficina = emailOficina;
        this.agendamentos = agendamentos;
    }




    public boolean verificaAgendamento(Agendamento novoAgendamento) {
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getData().equals(novoAgendamento.getData()) && agendamento.getHora().equals(novoAgendamento.getHora()))
                throw new IllegalArgumentException("Data e Hora já existentes");

        }
        return true;
    }

    public String getNomeOficina() {
        return nomeOficina;
    }

    public void setNomeOficina(String nomeOficina) {
        this.nomeOficina = nomeOficina;
    }

    public void setLocalizacaoOficina(String localizacaoOficina) {
        this.localizacaoOficina = localizacaoOficina;
    }

    public String getTelefoneOficina() {
        return telefoneOficina;
    }

    public void setTelefoneOficina(String telefoneOficina) {
        this.telefoneOficina = telefoneOficina;
    }

    public String getEmailOficina() {
        return emailOficina;
    }

    public void setEmailOficina(String emailOficina) {
        this.emailOficina = emailOficina;
    }

    public ArrayList<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(ArrayList<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public String getLocalizacaoOficina() {
        return localizacaoOficina;
    }
}


