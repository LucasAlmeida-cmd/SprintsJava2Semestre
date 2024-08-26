package quantumleap.banco;

import quantumleap.dominio.Cliente;

import java.sql.*;

public class ClienteDAO {

    private Connection conexao;

    public ClienteDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    private Long obterProximoIdFuncionarios() {
        Long id = null;
        try {
            String sql = "SELECT SEQ_CLIENTE_ID.NEXTVAL FROM DUAL";
            PreparedStatement comandoDeGeracao = conexao.prepareStatement(sql);
            ResultSet rs = comandoDeGeracao.executeQuery();
            while (rs.next()) {
                id = rs.getLong(1);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public void adicionarCliente(Cliente cliente) {
        try {
            String sql = "INSERT INTO tb_qfx_cliente (id_cliente, nome_cliente, email_cliente, telefone_cliente, senha_cliente, cliente_porto, localizacao_cliente) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setLong(1, obterProximoIdFuncionarios());
            pstmt.setString(2, cliente.getNomeCliente());
            pstmt.setString(3, cliente.getEmailCliente());
            pstmt.setString(4, cliente.getTelefoneCliente());
            pstmt.setString(5, cliente.getSenhaCliente());

            // Converte o boolean para 0 ou 1
            pstmt.setInt(6, cliente.isClientePorto() ? 1 : 0);

            pstmt.setString(7, cliente.getLocalizacaoCliente());

            pstmt.executeUpdate();
            System.out.println("Cliente inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
