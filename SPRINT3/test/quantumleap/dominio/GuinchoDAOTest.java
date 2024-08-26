package quantumleap.dominio;

import org.junit.jupiter.api.Test;
import quantumleap.banco.GuinchoDAO;

public class GuinchoDAOTest {

    @Test
    public void adicionandoGuincho(){
        Guincho guincho = new Guincho("teste", 1000, 100000);
        GuinchoDAO guinchoDAO = new GuinchoDAO();
        guinchoDAO.adicionaGuincho(guincho);
    }
}
