package quantumleap.dominio;

public class Diagnostico {
    private long idDiagnostico;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public ProblemasExistentes getProblemasExistentes() {
        return problemasExistentes;
    }

    public void setProblemasExistentes(ProblemasExistentes problemasExistentes) {
        this.problemasExistentes = problemasExistentes;
    }

    public Guincho getGuincho() {
        return guincho;
    }

    public void setGuincho(Guincho guincho) {
        this.guincho = guincho;
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

    public long getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(long idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }
}