package quantumleap.dominio;

import org.junit.jupiter.api.Test;
import quantumleap.banco.ClienteDAO;
import quantumleap.banco.VeiculoDAO;

import java.text.ParseException;
import java.util.ArrayList;

public class VeiculoDAOTest{

    @Test
    public void testAdicionarVeiculo() throws ParseException {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        VeiculoDAO veiculoDAO = new VeiculoDAO();

        Veiculo ve1 = new Veiculo("vw", "123", DateUtil.parseYear("2002"), 123, "1233");
        Veiculo ve2 = new Veiculo("teste", "123", DateUtil.parseYear("2003"), 123, "1233");
        veiculos.add(ve1);
        veiculos.add(ve2);


        long idCliente = 2;

        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.buscarClientePorId(idCliente);

        if (cliente == null){
            System.out.println("Usuario nao encontrado");
        } else {
            veiculoDAO.adicionarVeiculo(cliente.getIdCliente(), veiculos);
        }
    }

    @Test
    public void buscarVeiculoPorId(){
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        Veiculo veiculo = veiculoDAO.buscarVeiculoPorId(1);

        if (veiculo == null) {
            System.out.println("Nenhum veículo encontrado para o ID.");
        } else {
            System.out.println("Veículo encontrado:");
            System.out.println("ID: " + veiculo.getIdVeiculo());
            System.out.println("Montadora: " + veiculo.getMontadoraVeiculo());
            System.out.println("Modelo: " + veiculo.getModeloVeiculo());
            System.out.println("Ano: " + veiculo.getAnoVeiculo());
            System.out.println("Quantidade de Quilômetros: " + veiculo.getQuantidadeQuilometros());
            System.out.println("Placa: " + veiculo.getPlacaVeiculo());
        }
    }

    @Test
    public void atualizarVeiculo() throws ParseException {
        VeiculoDAO veiculoDAO = new VeiculoDAO();

        Veiculo veiculo = new Veiculo("Fiat", "Palio"
                , DateUtil.parseYear("2005"), 100000.00, "EFG4H56");

        long idVeiculo = 1L;
        if(veiculoDAO.buscarVeiculoPorId(idVeiculo) == null){
            System.out.println("Id não encontrado.");
        } else {
            veiculoDAO.atualizarVeiculo(idVeiculo, veiculo);
        }

    }



    @Test
    public void listarVeiculos() {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        ArrayList<Veiculo> veiculos = veiculoDAO.listarVeiculos();

        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo encontrado.");
        } else {
            System.out.println("Lista de veículos:");
            for (Veiculo veiculo : veiculos) {
                System.out.println("ID: " + veiculo.getIdVeiculo());
                System.out.println("Proprietário: " + veiculo.getNomeCliente());
                System.out.println("Montadora: " + veiculo.getMontadoraVeiculo());
                System.out.println("Modelo: " + veiculo.getModeloVeiculo());
                System.out.println("Ano: " + veiculo.getAnoVeiculo());
                System.out.println("Quantidade de Quilômetros: " + veiculo.getQuantidadeQuilometros());
                System.out.println("Placa: " + veiculo.getPlacaVeiculo());

                System.out.println("-----------------------------------------------------------");
            }
        }
    }

    @Test
    public void deletarVeiculo() throws ParseException {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.deletarVeiculo(1L);
    }


}
