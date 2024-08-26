package quantumleap.dominio;

import org.junit.jupiter.api.Test;
import quantumleap.banco.PecaDAO;

public class PecaDAOTest {

    @Test
    void adicionaPeca(){
        Peca peca = new Peca("teste5", 100, "teste", "teste");
        PecaDAO pecaDAO = new PecaDAO();
        pecaDAO.adicionaPeca(peca);
    }

}
