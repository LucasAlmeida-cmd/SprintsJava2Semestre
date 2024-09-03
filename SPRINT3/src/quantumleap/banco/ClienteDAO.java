package quantumleap.banco;

import quantumleap.dominio.Cliente;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO {

    private Connection conexao;

    public ClienteDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }



    public void adicionarCliente(Cliente cliente) {
        try {
            String sql = "INSERT INTO tb_qfx_cliente (nome_cliente, email_cliente, telefone_cliente, senha_cliente, cliente_porto, localizacao_cliente) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conexao.prepareStatement(sql);

            pstmt.setString(1, cliente.getNomeCliente());
            pstmt.setString(2, cliente.getEmailCliente());
            pstmt.setString(3, cliente.getTelefoneCliente());
            pstmt.setString(4, cliente.getSenhaCliente());
            pstmt.setInt(5, cliente.isClientePorto() ? 1 : 0);
            pstmt.setString(6, cliente.getLocalizacaoCliente());

            pstmt.executeUpdate();
            System.out.println("Cliente inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public Cliente buscarClientePorId(Long id) {
        Cliente cliente = null;
        try {
            String sql = "SELECT * FROM tb_qfx_cliente WHERE id_cliente = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getLong("id_cliente"));
                cliente.setNomeCliente(rs.getString("nome_cliente"));
                cliente.setEmailCliente(rs.getString("email_cliente"));
                cliente.setTelefoneCliente(rs.getString("telefone_cliente"));
                cliente.setSenhaCliente(rs.getString("senha_cliente"));
                cliente.setClientePorto(rs.getInt("cliente_porto") == 1);
                cliente.setLocalizacaoCliente(rs.getString("localizacao_cliente"));
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tb_qfx_cliente";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getLong("id_cliente"));
                cliente.setNomeCliente(rs.getString("nome_cliente"));
                cliente.setEmailCliente(rs.getString("email_cliente"));
                cliente.setTelefoneCliente(rs.getString("telefone_cliente"));
                cliente.setSenhaCliente(rs.getString("senha_cliente"));
                cliente.setClientePorto(rs.getInt("cliente_porto") == 1);
                cliente.setLocalizacaoCliente(rs.getString("localizacao_cliente"));

                clientes.add(cliente);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public void atualizarCliente(long idCliente, Cliente cliente) {
        try {
            String sql = "UPDATE tb_qfx_cliente SET nome_cliente = ?, email_cliente = ?, telefone_cliente = ?, senha_cliente = ?, cliente_porto = ?, localizacao_cliente = ? WHERE id_cliente = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, cliente.getNomeCliente());
            pstmt.setString(2, cliente.getEmailCliente());
            pstmt.setString(3, cliente.getTelefoneCliente());
            pstmt.setString(4, cliente.getSenhaCliente());
            pstmt.setInt(5, cliente.isClientePorto() ? 1 : 0);
            pstmt.setString(6, cliente.getLocalizacaoCliente());
            pstmt.setLong(7, idCliente);

            pstmt.executeUpdate();
            System.out.println("Cliente atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerCliente(Long id) {
        try {
            String sql = "DELETE FROM tb_qfx_cliente WHERE id_cliente = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setLong(1, id);

            pstmt.executeUpdate();
            System.out.println("Cliente removido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
