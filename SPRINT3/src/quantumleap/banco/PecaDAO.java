package quantumleap.banco;

import quantumleap.dominio.Peca;

import java.sql.*;
import java.util.ArrayList;

public class PecaDAO {

    private Connection conexao;
    public PecaDAO(){
        this.conexao = new ConnectionFactory().getConnection();
    }


    public void adicionaPeca(Peca peca) {
        try {
            String sqlInsert = "INSERT INTO tb_qfx_peca (nome_peca, preco_peca, marca_peca, modelo_peca) VALUES (?, ?, ?, ?)";
            PreparedStatement comandoDeInsercao = conexao.prepareStatement(sqlInsert);
            comandoDeInsercao.setString(1, peca.getNomePeca());
            comandoDeInsercao.setDouble(2, peca.getPrecoPeca());
            comandoDeInsercao.setString(3, peca.getMarcaPeca());
            comandoDeInsercao.setString(4, peca.getModeloPeca());

            comandoDeInsercao.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Peca buscarPecaPorId(Long idPeca) {
        Peca peca = null;
        try {
            String sql = "SELECT nome_peca, preco_peca, marca_peca, modelo_peca FROM tb_qfx_peca WHERE id_peca = ?";
            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                pstmt.setLong(1, idPeca);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        peca = new Peca();
                        peca.setIdPeca(idPeca);
                        peca.setNomePeca(rs.getString("nome_peca"));
                        peca.setPrecoPeca(rs.getDouble("preco_peca"));
                        peca.setMarcaPeca(rs.getString("marca_peca"));
                        peca.setModeloPeca(rs.getString("modelo_peca"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return peca;
    }



    public void atualizarPeca(Peca peca) {
        String sqlUpdate = "UPDATE tb_qfx_peca SET nome_peca = ?, preco_peca = ?, marca_peca = ?, modelo_peca = ? WHERE id_peca = ?";

        try (PreparedStatement comandoDeAtualizacao = conexao.prepareStatement(sqlUpdate)) {
            comandoDeAtualizacao.setString(1, peca.getNomePeca());
            comandoDeAtualizacao.setDouble(2, peca.getPrecoPeca());
            comandoDeAtualizacao.setString(3, peca.getMarcaPeca());
            comandoDeAtualizacao.setString(4, peca.getModeloPeca());
            comandoDeAtualizacao.setLong(5, peca.getIdPeca());

            comandoDeAtualizacao.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerPeca(Long idPeca) {
        String sqlDelete = "DELETE FROM tb_qfx_peca WHERE id_peca = ?";

        try (PreparedStatement comandoDeRemocao = conexao.prepareStatement(sqlDelete)) {
            comandoDeRemocao.setLong(1, idPeca);

            comandoDeRemocao.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Peca> listarPeca(){
        ArrayList<Peca> pecas = new ArrayList<>();
        try{
            String sql = "SELECT * FROM tb_qfx_peca";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Peca peca = new Peca();
                peca.setIdPeca(rs.getLong("id_peca"));
                peca.setNomePeca(rs.getString(("nome_peca")));
                peca.setPrecoPeca(rs.getDouble("preco_peca"));
                peca.setModeloPeca(rs.getString("modelo_peca"));
                peca.setMarcaPeca(rs.getString("marca_peca"));
                pecas.add(peca);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pecas;

    }


}
