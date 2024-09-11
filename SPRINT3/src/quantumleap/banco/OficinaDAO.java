package quantumleap.banco;

import quantumleap.dominio.Oficina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OficinaDAO {

    private Connection conexao;

    public OficinaDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void adicionarOficina(Oficina oficina) {
        String sqlInsertOficina = "INSERT INTO tb_qfx_oficina (nome_oficina, localizacao_oficina, telefone_oficina, email_oficina) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexao.prepareStatement(sqlInsertOficina, new String[] {"id_oficina"})) {
            pstmt.setString(1, oficina.getNomeOficina());
            pstmt.setString(2, oficina.getLocalizacaoOficina());
            pstmt.setString(3, oficina.getTelefoneOficina());
            pstmt.setString(4, oficina.getEmailOficina());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long idOficina = generatedKeys.getLong(1);
                    oficina.setIdOficina(idOficina);
                } else {
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Oficina buscarOficinaPorId(long idOficina) {
        String sqlBuscarOficina = "SELECT * FROM tb_qfx_oficina WHERE id_oficina = ?";
        Oficina oficina = null;

        try (PreparedStatement pstmt = conexao.prepareStatement(sqlBuscarOficina)) {
            pstmt.setLong(1, idOficina);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String nomeOficina = rs.getString("nome_oficina");
                    String localizacaoOficina = rs.getString("localizacao_oficina");
                    String telefoneOficina = rs.getString("telefone_oficina");
                    String emailOficina = rs.getString("email_oficina");

                    oficina = new Oficina(nomeOficina, localizacaoOficina, telefoneOficina, emailOficina);
                    oficina.setIdOficina(idOficina);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return oficina;
    }

    public List<Oficina> listarOficinas() {
        List<Oficina> oficinas = new ArrayList<>();
        String sqlSelectAll = "SELECT nome_oficina, localizacao_oficina, telefone_oficina, email_oficina FROM tb_qfx_oficina";

        try (PreparedStatement pstmt = conexao.prepareStatement(sqlSelectAll);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Oficina oficina = new Oficina(
                        rs.getString("nome_oficina"),
                        rs.getString("localizacao_oficina"),
                        rs.getString("telefone_oficina"),
                        rs.getString("email_oficina")
                );
                oficinas.add(oficina);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return oficinas;
    }

    public void atualizarOficina(Long idOficina, Oficina oficina) {
        String sqlUpdate = "UPDATE tb_qfx_oficina SET nome_oficina = ?, localizacao_oficina = ?, telefone_oficina = ?, email_oficina = ? WHERE id_oficina = ?";

        try (PreparedStatement pstmt = conexao.prepareStatement(sqlUpdate)) {
            pstmt.setString(1, oficina.getNomeOficina());
            pstmt.setString(2, oficina.getLocalizacaoOficina());
            pstmt.setString(3, oficina.getTelefoneOficina());
            pstmt.setString(4, oficina.getEmailOficina());
            pstmt.setLong(5, idOficina);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluirOficina(Long idOficina) {
        String sqlDelete = "DELETE FROM tb_qfx_oficina WHERE id_oficina = ?";

        try (PreparedStatement pstmt = conexao.prepareStatement(sqlDelete)) {
            pstmt.setLong(1, idOficina);

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fecharConexao(){
        try{
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
