package quantumleap.dominio;

public class ProblemasExistentes {
    private String nomeProblema;
    private String descricaoProblema;
    private double custoMaoDeObraProblema;
    private int qtdPeca;
    private Peca peca;

    public ProblemasExistentes() {

    }

    public ProblemasExistentes(String nomeProblema, String descricaoProblema, double custoMaoDeObraProblema, int qtdPeca, Peca peca) {
        this.nomeProblema = nomeProblema;
        this.descricaoProblema = descricaoProblema;
        this.custoMaoDeObraProblema = custoMaoDeObraProblema;
        this.qtdPeca = qtdPeca;
        this.peca = peca;
    }

    public double precoPeca(){
        return peca.getPrecoPeca();
    }

    public int getQtdPeca(){
        return qtdPeca;
    }

    public double getCustoMaoDeObraProblema(){
        return custoMaoDeObraProblema;
    }

    public String getNomeProblema() {
        return nomeProblema;
    }
}
