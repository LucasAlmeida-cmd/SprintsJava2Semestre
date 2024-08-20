package quantumleap.dominio;

public class Veiculo {
    private String montadoraVeiculo;
    private String modeloVeiculo;
    private String anoVeiculo;
    private double quantidadeQuilometros;
    private String placaVeiculo;

    public Veiculo(String montadoraVeiculo, String modeloVeiculo, String anoVeiculo, double quantidadeQuilometros, String placaVeiculo){
        this.montadoraVeiculo = montadoraVeiculo;
        this.modeloVeiculo = modeloVeiculo;
        this.anoVeiculo = anoVeiculo;
        this.quantidadeQuilometros = quantidadeQuilometros;
        this.placaVeiculo = placaVeiculo;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }
}
