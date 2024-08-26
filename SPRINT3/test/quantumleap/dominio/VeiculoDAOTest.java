package quantumleap.dominio;

import org.junit.jupiter.api.Test;
import quantumleap.banco.VeiculoDAO;

import java.text.ParseException;
import java.util.ArrayList;

public class VeiculoDAOTest{

    @Test
    public void testAdicionarVeiculo() throws ParseException {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        VeiculoDAO veiculoDAO = new VeiculoDAO();

        Veiculo ve1 = new Veiculo("vw", "123", DateUtil.parseYear("2002"), 123, "1233");
        Veiculo ve2 = new Veiculo("vw", "123", DateUtil.parseYear("2003"), 123, "1233");
        veiculos.add(ve1);
        veiculos.add(ve2);

        int idCliente = 1;
        veiculoDAO.adicionarVeiculo(idCliente, veiculos);

    }
}
