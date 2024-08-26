package quantumleap.dominio;

public class Diagnostico {
    private Cliente cliente;
    private Veiculo veiculo;
    private ProblemasExistentes problemasExistentes;
    private double orcamento;
    private Guincho guincho;

    public Diagnostico(Cliente cliente, Veiculo veiculo, ProblemasExistentes problemasExistentes, Guincho guincho) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.problemasExistentes = problemasExistentes;
        this.guincho = guincho;

        if(cliente.isClientePorto()){
            orcamento = 0.00;
        }

    }

    public double getOrcamento() {
        return orcamento;
    }

    public String getDescricao(){ return problemasExistentes.getDescricaoProblema();}

    public double adicionandoGuincho(){
        return orcamento + guincho.getPreco() + problemasExistentes.getCustoMaoDeObraProblema() + problemasExistentes.getQtdPeca()  * 500;
    }

    public double orcamentoPadrao(){
        return orcamento + problemasExistentes.getCustoMaoDeObraProblema() + problemasExistentes.getQtdPeca()  * 500;
    }

    public void setOrcamento(double orcamento){
        this.orcamento = orcamento;
    }

}