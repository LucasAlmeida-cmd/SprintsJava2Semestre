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
        this.emailCliente = emailCliente;
        this.telefoneCliente = telefoneCliente;
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

    public boolean isClientePorto() {
        return clientePorto;
    }

    public String getLocalizacaoCliente(){
        return localizacaoCliente;
    }
}
