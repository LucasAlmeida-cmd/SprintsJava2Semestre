package quantumleap.dominio;

import org.junit.jupiter.api.Test;
import quantumleap.banco.ClienteDAO;

import java.util.ArrayList;

public class ClienteDAOTest {

    @Test
    public void testAdicionaCliente() {
        Cliente cliente = new Cliente("ClienteTest1", "c111@gmail.com", "11111111111", "senhaclientetest", true, "São Paulo");
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.adicionarCliente(cliente);
    }

    @Test
    public void buscandoClientePorId() {
        ClienteDAO clienteDAO = new ClienteDAO();
        Long idCliente = 1L;
        Cliente cliente = clienteDAO.buscarClientePorId(idCliente);

        if (cliente == null) {
            System.out.println(".");
        } else {
            System.out.println("ID:" + cliente.getIdCliente());
            System.out.println("Nome:" + cliente.getNomeCliente());
            System.out.println("Email:" + cliente.getEmailCliente());
            System.out.println("Telefone" + cliente.getTelefoneCliente());
            System.out.println("Senha:" + cliente.getSenhaCliente());
            System.out.println("Cliente Porto:" + cliente.getClientePorto());
            System.out.println("Localização:" + cliente.getLocalizacaoCliente());
        }
    }

    @Test
    public void testListarClientes() {
        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<Cliente> clientes = clienteDAO.listarClientes();

        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getIdCliente());
            System.out.println("Nome: " + cliente.getNomeCliente());
            System.out.println("Email: " + cliente.getEmailCliente());
            System.out.println("Telefone: " + cliente.getTelefoneCliente());
            System.out.println("Senha: " + cliente.getSenhaCliente());
            System.out.println("Cliente Porto: " + (cliente.isClientePorto() ? "Sim" : "Não"));
            System.out.println("Localização: " + cliente.getLocalizacaoCliente());
            System.out.println("---------------------------");
        }


    }

    @Test
    public void atualizarCliente() {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente2 = new Cliente("Lucas", "Lucas@gmail.com", "123", "senha", false, "rio");

        long idCliente = 1L;

        if(clienteDAO.buscarClientePorId(idCliente) == null){
            System.out.println("ID Cliente não encontrado.");
        }else {
            clienteDAO.atualizarCliente(idCliente, cliente2);
        }

    }

    @Test
    public void deletarCliente() {
        ClienteDAO clienteDAO = new ClienteDAO();
        Long idCliente = 1L;
        clienteDAO.removerCliente(idCliente);
    }
}
