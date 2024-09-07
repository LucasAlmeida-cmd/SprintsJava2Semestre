package quantumleap.dominio;

import org.junit.jupiter.api.Test;
import quantumleap.banco.*;

import java.text.ParseException;
import java.util.ArrayList;

public class DiagnosticoDAOTest {

    @Test
    public void adicionandoDiagnostico() throws ParseException {
        DiagnosticoDAO dao = new DiagnosticoDAO();


        Cliente cliente = new Cliente("ClienteTest1", "3@gmail.com", "11111111111", "senhaclientetest", true, "São Paulo");
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
        ProblemasExistentes problema = new ProblemasExistentes("Teste2", "Teste", 100, 2, pecaExistente);
        problemasExistentesDAO.adicionarProblemaExistente(problema);

        Diagnostico diagnostico = new Diagnostico(cliente, ve1, problema, guincho );
        dao.adicionarDiagnostico(diagnostico);
    }

    @Test
    public void buscarDiagnostico() {
        DiagnosticoDAO dao = new DiagnosticoDAO();
        dao.buscarDiagnosticoPorId(1L);
    }

    @Test
    public void atualizarDiagnostico() throws ParseException {
        DiagnosticoDAO dao = new DiagnosticoDAO();

        Cliente cliente = new Cliente("NOVODIAG", "NOVODIAG", "11111111111", "senhaclientetest", true, "São Paulo");
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.adicionarCliente(cliente);


        VeiculoDAO veiculoDAO = new VeiculoDAO();
        Veiculo ve1 = new Veiculo("NOVODIAG", "123", DateUtil.parseYear("2002"), 123, "1233");
        veiculoDAO.adicionarVeiculo(cliente, ve1);

        Peca peca = new Peca("NOVODIAG", 100, "teste", "teste");

        PecaDAO pecaDAO = new PecaDAO();
        pecaDAO.adicionaPeca(peca);
        Long idPecaExistente = 1L;
        Peca pecaExistente = pecaDAO.buscarPecaPorId(idPecaExistente);

        Guincho guincho = new Guincho("NOVODI", 1000, 100000);
        GuinchoDAO guinchoDAO = new GuinchoDAO();
        guinchoDAO.adicionaGuincho(guincho);


        ProblemasExistentesDAO problemasExistentesDAO = new ProblemasExistentesDAO();
        ProblemasExistentes problema = new ProblemasExistentes("NOVODIAG", "Teste", 100, 2, pecaExistente);
        problemasExistentesDAO.adicionarProblemaExistente(problema);

        Diagnostico diagnostico = new Diagnostico(cliente, ve1, problema, guincho );

        dao.atualizarDiagnostico(1L,diagnostico);


    }

    @Test
    public void deletarDiagnostico() throws ParseException {
        DiagnosticoDAO dao = new DiagnosticoDAO();
        dao.deletarDiagnostico(2L);
    }

    @Test
    public void listarDiagnosticos() {
        DiagnosticoDAO dao = new DiagnosticoDAO();
        ArrayList<Diagnostico> diagnosticos = dao.listarDiagnosticos();

        for (Diagnostico diagnostico : diagnosticos) {
            System.out.println("Diagnóstico ID: " + diagnostico.getIdDiagnostico());
            System.out.println("Cliente ID: " + diagnostico.getCliente().getIdCliente());
            System.out.println("Veículo ID: " + diagnostico.getVeiculo().getIdVeiculo());
            System.out.println("Problemas Existentes ID: " + diagnostico.getProblemasExistentes().getIdProblemas());
            System.out.println("Guincho ID: " + diagnostico.getGuincho().getIdGuincho());
            System.out.println("-----------------------------");
        }
    }

}
