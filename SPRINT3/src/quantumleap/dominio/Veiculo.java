package quantumleap.dominio;

import java.util.Date;

public class Veiculo {
    private String montadoraVeiculo;
    private String modeloVeiculo;
    private Date anoVeiculo;
    private double quantidadeQuilometros;
    private String placaVeiculo;

    public Veiculo(String montadoraVeiculo, String modeloVeiculo, Date anoVeiculo, double quantidadeQuilometros, String placaVeiculo){
        this.montadoraVeiculo = montadoraVeiculo;
        this.modeloVeiculo = modeloVeiculo;
        this.anoVeiculo = anoVeiculo;
        this.quantidadeQuilometros = quantidadeQuilometros;
        this.placaVeiculo = placaVeiculo;
    }

    public String getMontadoraVeiculo() {
        return montadoraVeiculo;
    }

    public void setMontadoraVeiculo(String montadoraVeiculo) {
        this.montadoraVeiculo = montadoraVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public Date getAnoVeiculo() {
        return anoVeiculo;
    }

    public void setAnoVeiculo(Date anoVeiculo) {
        this.anoVeiculo = anoVeiculo;
    }

    public double getQuantidadeQuilometros() {
        return quantidadeQuilometros;
    }

    public void setQuantidadeQuilometros(double quantidadeQuilometros) {
        this.quantidadeQuilometros = quantidadeQuilometros;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }
}
