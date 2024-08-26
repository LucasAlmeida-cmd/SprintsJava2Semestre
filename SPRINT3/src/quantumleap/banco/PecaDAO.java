package quantumleap.banco;

import quantumleap.dominio.Peca;

import java.sql.*;

public class PecaDAO {

    private Connection conexao;
    public PecaDAO(){
        this.conexao = new ConnectionFactory().getConnection();
    }


    public void adicionaPeca(Peca peca) {
        Long idPeca = obterProximoIdPeca();

        String sqlInsert = "INSERT INTO tb_qfx_peca (id_peca, nome_peca, preco_peca, marca_peca, modelo_peca) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement comandoDeInsercao = conexao.prepareStatement(sqlInsert)) {
            comandoDeInsercao.setLong(1, idPeca);
            comandoDeInsercao.setString(2, peca.getNomePeca());
            comandoDeInsercao.setDouble(3, peca.getPrecoPeca());
            comandoDeInsercao.setString(4, peca.getMarcaPeca());
            comandoDeInsercao.setString(5, peca.getModeloPeca());

            comandoDeInsercao.executeUpdate();
            peca.setIdPeca(idPeca); // Atualiza o ID da peça no objeto

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Peca obterPecaPorId(Long idPeca) {
        Peca peca = null;
        try {
            String sql = "SELECT nome_peca, preco_peca, marca_peca, modelo_peca FROM tb_qfx_peca WHERE id_peca = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setLong(1, idPeca);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String nomePeca = rs.getString("nome_peca");
                double precoPeca = rs.getDouble("preco_peca");
                String marcaPeca = rs.getString("marca_peca");
                String modeloPeca = rs.getString("modelo_peca");
                peca = new Peca(nomePeca, precoPeca, marcaPeca, modeloPeca);
                peca.setIdPeca(idPeca); // Defina o ID da peça
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return peca;
    }






    private Long obterProximoIdPeca() {
        Long id = null;
        try {
            String sql = "SELECT SEQ_PECA_ID.NEXTVAL FROM DUAL";
            PreparedStatement comandoDeGeracao = conexao.prepareStatement(sql);
            ResultSet rs = comandoDeGeracao.executeQuery();
            while(rs.next()) {
                id = rs.getLong(1);
            }
            rs.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}
