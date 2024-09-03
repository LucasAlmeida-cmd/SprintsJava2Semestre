package quantumleap.dominio;

import org.junit.jupiter.api.Test;
import quantumleap.banco.ClienteDAO;
import quantumleap.banco.GuinchoDAO;

import java.util.ArrayList;

public class GuinchoDAOTest {

    @Test
    public void adicionandoGuincho(){
        Guincho guincho = new Guincho("Guinc3", 1000, 100000);
        GuinchoDAO guinchoDAO = new GuinchoDAO();
        guinchoDAO.adicionaGuincho(guincho);
    }

    @Test
    public void buscarGuinchoPorId(){
        GuinchoDAO guinchoDAO = new GuinchoDAO();
        Long idGuincho = 1L;
        Guincho guincho = guinchoDAO.buscarGuinchoPorId(idGuincho);

        if(guincho == null){
            System.out.println("ID Guincho não existe.");
        } else {
            System.out.println("ID: " + guincho.getIdGuincho());
            System.out.println("Placa: " + guincho.getPlaca());
            System.out.println("Preço: " + guincho.getPreco());
            System.out.println("Carga Máxima: " + guincho.getCargaMaxima());
            System.out.println("-------------------");
        }
    }

    @Test
    public void atualizaGuincho(){
        GuinchoDAO guinchoDAO = new GuinchoDAO();
        Guincho guincho = new Guincho("6", 10, 10);
        long idGuincho = 4L;

        if(guinchoDAO.buscarGuinchoPorId(idGuincho) == null){
            System.out.println("ID Guincho não encontrado.");
        } else {
            guinchoDAO.atualizarGuincho(idGuincho, guincho);
        }

    }

    @Test
    public void listaGuinchos(){
        GuinchoDAO guinchoDAO = new GuinchoDAO();
        ArrayList<Guincho> guincho = guinchoDAO.listarGuincho();

        for (Guincho g : guincho) {
            System.out.println("ID: " + g.getIdGuincho());
            System.out.println("Placa: " + g.getPlaca());
            System.out.println("Preco: " + g.getPreco());
            System.out.println("Carga Maxima: " + g.getCargaMaxima());
            System.out.println("--------------------");
        }
    }


    @Test
    public void deletarGuincho(){
        GuinchoDAO guinchoDAO = new GuinchoDAO();
        Long idGuincho = 1L;
        guinchoDAO.deletarGuincho(idGuincho);
    }



}
