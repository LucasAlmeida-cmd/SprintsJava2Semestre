package quantumleap.dominio;

import java.util.ArrayList;

public class Cliente {
    private String nomeCliente;
    private String emailCliente;
    private String telefoneCliente;
    private String senhaCliente;
    private boolean clientePorto;
    private ArrayList<Veiculo> veiculosDoCliente;
    private String localizacaoCliente;

    public Cliente(String nomeCliente, String emailCliente, String telefoneCliente, String senhaCliente, boolean clientePorto, String localizacaoCliente){
        this.nomeCliente = nomeCliente;
        this.telefoneCliente = telefoneCliente;
        this.emailCliente = emailCliente;
        this.senhaCliente = senhaCliente;
        this.clientePorto = clientePorto;
        this.veiculosDoCliente = new ArrayList<Veiculo>();
        this.localizacaoCliente = localizacaoCliente;
    }

    public void atualizaCadastro(String nomeCliente, String emailCliente, String telefoneCliente, String senhaCliente, boolean clientePorto){
        if(nomeCliente != null){
            this.nomeCliente = nomeCliente;
        }

        if(emailCliente != null){
            this.emailCliente = emailCliente;
        }

        if(telefoneCliente != null){
            this.telefoneCliente = telefoneCliente;
        }

        if(senhaCliente != null){
            this.senhaCliente = senhaCliente;
        }

        this.clientePorto = clientePorto;
    }

    public void associaVeiculo(Veiculo veiculo){
        veiculosDoCliente.add(veiculo);
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public void setClientePorto(boolean clientePorto) {
        this.clientePorto = clientePorto;
    }

    public String getSenhaCliente() {
        return senhaCliente;
    }

    public void setSenhaCliente(String senhaCliente) {
        this.senhaCliente = senhaCliente;
    }

    public ArrayList<Veiculo> getVeiculosDoCliente() {
        return veiculosDoCliente;
    }

    public void setVeiculosDoCliente(ArrayList<Veiculo> veiculosDoCliente) {
        this.veiculosDoCliente = veiculosDoCliente;
    }

    public void setLocalizacaoCliente(String localizacaoCliente) {
        this.localizacaoCliente = localizacaoCliente;
    }

    public boolean isClientePorto() {
        return clientePorto;
    }

    public String getLocalizacaoCliente(){
        return localizacaoCliente;
    }
}
