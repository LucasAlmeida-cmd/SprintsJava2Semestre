package quantumleap.dominio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiagnosticoTest {

    @Test
    void adicionandoGuincho_comValoresValidos_deveRetornarSomaCorreta(){
        double orcamento = 0;
        Guincho guincho = new Guincho("123", 250, 100000);
        Peca peca = new Peca("vira", 500.00, "Cotinental", "modelo1");
        ProblemasExistentes problemasExistentes = new ProblemasExistentes("nome1", "descrição1", 500.00, 3, peca);
        Cliente cliente = new Cliente("Lucas", "lucas@gmail", "123", "123", false, "São Paulo");
        Veiculo veiculo = new Veiculo("VW", "jetta", "2001", 1200, "123dd");

        Diagnostico diagnostico = new Diagnostico(cliente, veiculo, problemasExistentes, guincho);

        double resultado = diagnostico.adicionandoGuincho();
        Assertions.assertEquals(2250, resultado);
    }

    @Test
    void orcamentoPadrao_comValoresValidos_deveRetornarSomaCorreta(){
        double orcamento = 0;
        Guincho guincho = new Guincho("123", 250, 100000);
        Peca peca = new Peca("vira", 500.00, "Cotinental", "modelo1");
        ProblemasExistentes problemasExistentes = new ProblemasExistentes("nome1", "descrição1", 500.00, 3, peca);
        Cliente cliente = new Cliente("Lucas", "lucas@gmail", "123", "123", false, "São Paulo");
        Veiculo veiculo = new Veiculo("VW", "jetta", "2001", 1200, "123dd");

        Diagnostico diagnostico = new Diagnostico(cliente, veiculo, problemasExistentes, guincho);
        double resultado = diagnostico.orcamentoPadrao();
        Assertions.assertEquals(2000, resultado);
    }

}
