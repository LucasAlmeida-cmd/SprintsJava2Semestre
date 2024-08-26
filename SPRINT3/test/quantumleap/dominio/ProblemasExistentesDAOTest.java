package quantumleap.dominio;

import org.junit.jupiter.api.Test;
import quantumleap.banco.ConnectionFactory;
import quantumleap.banco.PecaDAO;
import quantumleap.banco.ProblemasExistentesDAO;

public class ProblemasExistentesDAOTest {


    @Test
    void adicionandoProblemasComPecas() {
        PecaDAO pecaDAO = new PecaDAO();
        ProblemasExistentesDAO problemasDAO = new ProblemasExistentesDAO(new ConnectionFactory().getConnection());

        Long idPecaExistente = 1L;
        Peca pecaExistente = pecaDAO.obterPecaPorId(idPecaExistente);

        ProblemasExistentes problema = new ProblemasExistentes("Teste", "Teste", 100, 2, pecaExistente);

        problemasDAO.adicionarProblemaExistente(problema);
    }

}
