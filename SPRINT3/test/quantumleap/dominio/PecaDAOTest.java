package quantumleap.dominio;

import org.junit.jupiter.api.Test;
import quantumleap.banco.PecaDAO;

import java.util.ArrayList;

public class PecaDAOTest {

    @Test
    void adicionaPeca(){
        Peca peca = new Peca("Peça", 100, "teste", "teste");
        PecaDAO pecaDAO = new PecaDAO();
        pecaDAO.adicionaPeca(peca);
    }

    @Test
    void buscandoPecaPeloId(){
        PecaDAO pecaDAO = new PecaDAO();
        Peca peca = pecaDAO.buscarPecaPorId(1L);

        if(peca == null){
            System.out.println("Peça não encontrada");
        }else{
            System.out.println("Peca encontrada");
            System.out.println("Nome: " + peca.getNomePeca());
            System.out.println("Preço: " + peca.getPrecoPeca());
            System.out.println("Marca: " + peca.getMarcaPeca());
            System.out.println("Modelo: " + peca.getModeloPeca());
        }

    }

    @Test
    void atualizaPeca(){
        PecaDAO pecaDAO = new PecaDAO();
        Peca peca = new Peca("PecaAtualizada", 150.00, "Nova Marca", "Novo Modelo");
        pecaDAO.atualizarPeca(peca);

    }


    @Test
    void listandoPecas(){
        PecaDAO pecaDAO = new PecaDAO();
        ArrayList<Peca> pecas = pecaDAO.listarPeca();

        for (Peca peca: pecas){
            System.out.println("ID: " + peca.getIdPeca());
            System.out.println("Nome Peça: " + peca.getNomePeca());
            System.out.println("Preço: " + peca.getPrecoPeca());
            System.out.println("Modelo: "+ peca.getModeloPeca());
            System.out.println("Marca: "+ peca.getMarcaPeca());
            System.out.println("------------------------------");
        }
    }

    @Test
    void removendoPecaPeloId(){
        PecaDAO pecaDAO = new PecaDAO();
        pecaDAO.removerPeca(1L);
    }



}
