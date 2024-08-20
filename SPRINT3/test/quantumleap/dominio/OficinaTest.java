package quantumleap.dominio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class OficinaTest {

    @Test
    void verificaAgendamento_comValoresValidos_deveRetornarTrue(){
        Peca peca = new Peca("vira", 500.00, "Cotinental", "modelo1");
        ProblemasExistentes problema = new ProblemasExistentes("nome1", "descrição1", 500.00, 3, peca);
        Guincho guincho = new Guincho("123", 250, 100000);

        Veiculo veiculo = new Veiculo("Fiat", "Palio", "2005", 100000.00, "ABC1D23");
        Cliente cliente = new Cliente("Arthur", "Arthur@gmail.com", "111111111", "senha", false, "São Paulo");
        Diagnostico diag = new Diagnostico(cliente, veiculo, problema, guincho);
        Agendamento ag = new Agendamento(diag, "13/12/2024", "12:30");

        Veiculo veiculo1 = new Veiculo("Fiat", "Palio", "2005", 100000.00, "EFG4H56");
        Cliente cliente1 = new Cliente("Arthur", "Arthur@gmail.com", "111111111", "senha", true, "Rio Paulo");
        Diagnostico diag1 = new Diagnostico(cliente, veiculo, problema,guincho);
        Agendamento ag1 = new Agendamento(diag1, "12/12/2024", "12:00");

        ArrayList<Agendamento> agendamentos = new ArrayList<Agendamento>();
        agendamentos.add(ag);
        agendamentos.add(ag1);

        Oficina oficina = new Oficina("oficina", "São Paulo", "11111111111", "oficina@gmail.com", agendamentos);
        boolean verifica = oficina.verificaAgendamento(new Agendamento(diag1, "13/12/2024", "12:00"));

        Assertions.assertTrue(verifica);
    }

    @Test
    void verificaAgendamento_comValoresValidos_deveRetornarFalse(){
        Peca peca = new Peca("vira", 500.00, "Cotinental", "modelo1");
        ProblemasExistentes problema = new ProblemasExistentes("nome1", "descrição1", 500.00, 3, peca);
        Guincho guincho = new Guincho("123", 250, 100000);

        Veiculo veiculo = new Veiculo("Fiat", "Palio", "2005", 100000.00, "ABC1D23");
        Cliente cliente = new Cliente("Arthur", "Arthur@gmail.com", "111111111", "senha", false, "São Paulo");
        Diagnostico diag = new Diagnostico(cliente, veiculo, problema, guincho);
        Agendamento ag = new Agendamento(diag, "13/12/2024", "12:30");

        Veiculo veiculo1 = new Veiculo("Fiat", "Palio", "2005", 100000.00, "EFG4H56");
        Cliente cliente1 = new Cliente("Arthur", "Arthur@gmail.com", "111111111", "senha", true, "Rio Paulo");
        Diagnostico diag1 = new Diagnostico(cliente, veiculo, problema,guincho);
        Agendamento ag1 = new Agendamento(diag1, "12/12/2024", "12:00");

        ArrayList<Agendamento> agendamentos = new ArrayList<Agendamento>();
        agendamentos.add(ag);
        agendamentos.add(ag1);

        Oficina oficina = new Oficina("oficina", "São Paulo", "11111111111", "oficina@gmail.com", agendamentos);


        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            oficina.verificaAgendamento(new Agendamento(diag1, "13/12/2024", "12:30"));
        });
    }
}
