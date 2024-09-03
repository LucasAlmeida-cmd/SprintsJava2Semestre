package quantumleap.dominio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import quantumleap.banco.OficinaDAO;

import java.text.ParseException;
import java.util.ArrayList;

public class OficinaDAOTest {

    @Test
    public void adicionarOficina(){
        ArrayList<Agendamento> lista2 = new ArrayList<>();


        Oficina oficina1 = new Oficina("Oficina111", "Rua Teste","123", "email@teste.com", lista2);
        Oficina oficina2 = new Oficina("Oficina222", "Rua Teste","123", "email@teste.com", lista2);

        OficinaDAO oficinaDAO = new OficinaDAO();
        oficinaDAO.adicionarOficina(oficina1);
    }





    @Test
    public void adicionandoAgendamentoNaOficina() throws ParseException {

    OficinaDAO oficinaDAO = new OficinaDAO();
        Guincho guincho1 = new Guincho("123", 250, 100000);
        Peca peca = new Peca("vira", 500.00, "Cotinental", "modelo1");
        Peca peca2 = new Peca("Vela", 250.00, "bosch", "modelo2");
        ProblemasExistentes problema1 = new ProblemasExistentes("nome1", "descrição1", 500.00, 3, peca);
        ProblemasExistentes problema2 = new ProblemasExistentes("nome2", "descrição2", 1000.00, 1, peca2);
        ArrayList<ProblemasExistentes> problemas = new ArrayList<ProblemasExistentes>();
        problemas.add(problema1);
        problemas.add(problema2);

        Veiculo veiculo = new Veiculo("Fiat", "Palio", DateUtil.parseYear("2005"), 100000.00, "EFG4H56");
        Cliente cliente = new Cliente("Arthur", "Arthur@gmail.com", "111111111", "senha", true, "Rio Paulo");
        Diagnostico diag1 = new Diagnostico(cliente, veiculo, problema1,guincho1);
        Agendamento ag1 = new Agendamento(diag1, "12/12/2024", "12:30");

        Veiculo veiculo2 = new Veiculo("Fiat", "Palio", DateUtil.parseYear("2005"), 100000.00, "ABC1D23");
        Cliente cliente2 = new Cliente("Arthur", "Arthur@gmail.com", "111111111", "senha", false, "São Paulo");
        Diagnostico diag2 = new Diagnostico(cliente2, veiculo, problema2, guincho1);
        Agendamento ag2 = new Agendamento(diag2, "13/12/2024", "12:30");

        ArrayList<Agendamento> agendamentos = new ArrayList<Agendamento>();
        agendamentos.add(ag1);
        agendamentos.add(ag2);

        OficinaDAO oficinaDAO1 = new OficinaDAO();

        oficinaDAO1.adicionarAgendamentos(1L, agendamentos);

    }

    @Test
    void buscandoOficinasPorID(){
        OficinaDAO oficinaDAO = new OficinaDAO();
        oficinaDAO.buscarOficinaPorId(1L);
    }





    @Test
    void verificaAgendamento_comValoresValidos_deveRetornarTrue() throws ParseException {
        Peca peca = new Peca("vira", 500.00, "Cotinental", "modelo1");
        ProblemasExistentes problema = new ProblemasExistentes("nome1", "descrição1", 500.00, 3, peca);
        Guincho guincho = new Guincho("123", 250, 100000);

        Veiculo veiculo = new Veiculo("Fiat", "Palio", DateUtil.parseYear("2005"), 100000.00, "ABC1D23");
        Cliente cliente = new Cliente("Arthur", "Arthur@gmail.com", "111111111", "senha", false, "São Paulo");
        Diagnostico diag = new Diagnostico(cliente, veiculo, problema, guincho);
        Agendamento ag = new Agendamento(diag, "13/12/2024", "12:30");

        Veiculo veiculo1 = new Veiculo("Fiat", "Palio", DateUtil.parseYear("2005"), 100000.00, "EFG4H56");
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
    void verificaAgendamento_comValoresValidos_deveRetornarFalse() throws ParseException {
        Peca peca = new Peca("vira", 500.00, "Cotinental", "modelo1");
        ProblemasExistentes problema = new ProblemasExistentes("nome1", "descrição1", 500.00, 3, peca);
        Guincho guincho = new Guincho("123", 250, 100000);

        Veiculo veiculo = new Veiculo("Fiat", "Palio", DateUtil.parseYear("2005"), 100000.00, "ABC1D23");
        Cliente cliente = new Cliente("Arthur", "Arthur@gmail.com", "111111111", "senha", false, "São Paulo");
        Diagnostico diag = new Diagnostico(cliente, veiculo, problema, guincho);
        Agendamento ag = new Agendamento(diag, "13/12/2024", "12:30");

        Veiculo veiculo1 = new Veiculo("Fiat", "Palio", DateUtil.parseYear("2005"), 100000.00, "EFG4H56");
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
