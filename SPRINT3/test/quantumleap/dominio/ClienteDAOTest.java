package quantumleap.dominio;

import org.junit.jupiter.api.Test;
import quantumleap.banco.ClienteDAO;

public class ClienteDAOTest {

    @Test
    public void testAdicionaCliente() {
        Cliente cliente = new Cliente("ClienteTest1", "clientetest2@gmail.com", "11111111111", "senhaclientetest", true, "São Paulo");
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.adicionarCliente(cliente);


    }
}
