package quantumleap.dominio;

import org.junit.jupiter.api.Test;
import quantumleap.banco.*;

import java.text.ParseException;
import java.util.ArrayList;

public class AgendamentoDAOTest {

    @Test
    public void adicionandoAgendamentoDAO() throws ParseException {

        Cliente cliente = new Cliente("ClienteTest1", "a2aa@gmail.com", "11111111111", "senhaclientetest", true, "São Paulo");
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.adicionarCliente(cliente);

        ArrayList<Veiculo> veiculos = new ArrayList<>();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        Veiculo ve1 = new Veiculo("vw", "123", DateUtil.parseYear("2002"), 123, "1233");
        veiculos.add(ve1);

        veiculoDAO.adicionarVeiculo(cliente, ve1);

        Peca peca = new Peca("Peça", 100, "teste", "teste");

        PecaDAO pecaDAO = new PecaDAO();
        pecaDAO.adicionaPeca(peca);
        Long idPecaExistente = 1L;
        Peca pecaExistente = pecaDAO.buscarPecaPorId(idPecaExistente);

        Guincho guincho = new Guincho("Guinc3", 1000, 100000);
        GuinchoDAO guinchoDAO = new GuinchoDAO();
        guinchoDAO.adicionaGuincho(guincho);


        ProblemasExistentesDAO problemasExistentesDAO = new ProblemasExistentesDAO();
        ProblemasExistentes problema = new ProblemasExistentes("Teste2", "azul", 100, 2, pecaExistente);
        problemasExistentesDAO.adicionarProblemaExistente(problema);

        Diagnostico diagnostico = new Diagnostico(cliente, ve1, problema, guincho );

        Agendamento agendamento = new Agendamento(diagnostico, "DIACERTO", "HORACERTA");
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();

        agendamentoDAO.adicionarAgendamento(1L, agendamento);
    }

    @Test
    public void buscandoAgendamentosPorId() {
        AgendamentoDAO dao = new AgendamentoDAO();
        Agendamento agendamento = dao.buscarAgendamentoPorId(1L);

        if (agendamento != null) {
            System.out.println("Data: " + agendamento.getData());
            System.out.println("Hora: " + agendamento.getHora());
            System.out.println("Localização Oficina: " + agendamento.getLocalizacao());
            System.out.println("Descrição: " + agendamento.getDiagnostico().getDescricao());
        } else {
            System.out.println("Agendamento não encontrado.");
        }
    }

    @Test
    public void atualizarAgendamento() throws ParseException {
        Cliente cliente = new Cliente("ClienteTest1", "2235@gmail.com", "11111111111", "senhaclientetest", true, "São Paulo");
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.adicionarCliente(cliente);

        VeiculoDAO veiculoDAO = new VeiculoDAO();
        Veiculo ve1 = new Veiculo("vw", "123", DateUtil.parseYear("2002"), 123, "1233");
        veiculoDAO.adicionarVeiculo(cliente, ve1);

        Peca peca = new Peca("Peça", 100, "teste", "teste");

        PecaDAO pecaDAO = new PecaDAO();
        pecaDAO.adicionaPeca(peca);
        Long idPecaExistente = 1L;
        Peca pecaExistente = pecaDAO.buscarPecaPorId(idPecaExistente);

        Guincho guincho = new Guincho("Guinc3", 1000, 100000);
        GuinchoDAO guinchoDAO = new GuinchoDAO();
        guinchoDAO.adicionaGuincho(guincho);


        ProblemasExistentesDAO problemasExistentesDAO = new ProblemasExistentesDAO();
        ProblemasExistentes problema = new ProblemasExistentes("Teste2", "agendamenttoatualizado", 100, 2, pecaExistente);
        problemasExistentesDAO.adicionarProblemaExistente(problema);

        Diagnostico diagnostico = new Diagnostico(cliente, ve1, problema, guincho );


        Agendamento agendamento = new Agendamento(diagnostico, "agendamenttoatualizado", "agendamenttoatualizado");
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();

        agendamentoDAO.atualizarAgendamento(1L, agendamento);


    }

    @Test
    public void deletarAgendamento(){
        AgendamentoDAO dao = new AgendamentoDAO();
        dao.deletarAgendamento(2L);
    }

    @Test
    public void listarAgendamentos() {
        AgendamentoDAO dao = new AgendamentoDAO();

        ArrayList<Agendamento> agendamentos = dao.listarAgendamentos();

        for (Agendamento agendamento : agendamentos) {
            System.out.println("ID Agendamento: " + agendamento.getIdAgendamento());
            System.out.println("Data: " + agendamento.getData());
            System.out.println("Hora: " + agendamento.getHora());
            System.out.println("------------");
        }
    }



}
