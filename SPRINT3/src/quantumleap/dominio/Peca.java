package quantumleap.dominio;

public class Peca {
    private String nomePeca;
    private double precoPeca;
    private String marcaPeca;
    private String modeloPeca;

    public Peca(String nomePeca, double precoPeca, String marcaPeca, String modeloPeca){
        this.nomePeca = nomePeca;
        this.precoPeca = precoPeca;
        this.marcaPeca = marcaPeca;
        this.modeloPeca = modeloPeca;
    }

    protected double getPrecoPeca(){
        return precoPeca;
    }
}
