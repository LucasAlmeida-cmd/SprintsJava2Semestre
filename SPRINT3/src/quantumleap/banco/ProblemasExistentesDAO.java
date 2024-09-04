package quantumleap.banco;

import quantumleap.dominio.ProblemasExistentes;
import quantumleap.dominio.Peca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProblemasExistentesDAO {

    private Connection conexao;

    public ProblemasExistentesDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void adicionarProblemaExistente(ProblemasExistentes problema) {
        String sqlInsert = "INSERT INTO tb_qfx_problemas_existentes (nome_problema, descricao_problema, custo_mao_de_obra_problema, qtd_peca, id_peca) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement comandoInsercao = conexao.prepareStatement(sqlInsert, new String[] {"id_problema"});


            comandoInsercao.setString(1, problema.getNomeProblema());
            comandoInsercao.setString(2, problema.getDescricaoProblema());
            comandoInsercao.setDouble(3, problema.getCustoMaoDeObraProblema());
            comandoInsercao.setInt(4, problema.getQtdPeca());
            comandoInsercao.setLong(5, problema.getPeca().getIdPeca());

            int rowsAffected = comandoInsercao.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Falha ao inserir o cliente, nenhuma linha foi afetada.");
            }
            try (ResultSet generatedKeys = comandoInsercao.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    problema.setIdProblemas(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Falha ao obter o ID gerado para o cliente.");
                }
            }

            System.out.println("Problema ok");

            System.out.println("Problema existente inserido com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProblemasExistentes buscarProblemaPorId(long idProblema) {
        ProblemasExistentes problema = null;
        try {
            String sql = "SELECT * FROM tb_qfx_problemas_existentes WHERE id_problema = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setLong(1, idProblema);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                problema = new ProblemasExistentes(
                        rs.getString("nome_problema"),
                        rs.getString("descricao_problema"),
                        rs.getDouble("custo_mao_de_obra_problema"),
                        rs.getInt("qtd_peca"),
                        new Peca() // Deve ser substituído pela lógica real para buscar a peça associada
                );
                problema.setIdProblemas(rs.getLong("id_problema"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return problema;
    }

    public void atualizarProblemaExistente(long idProblema, ProblemasExistentes problema) {
        try {
            String sql = "UPDATE tb_qfx_problemas_existentes SET nome_problema = ?, descricao_problema = ?, custo_mao_de_obra_problema = ?, qtd_peca = ?, id_peca = ? WHERE id_problema = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);

            pstmt.setString(1, problema.getNomeProblema());
            pstmt.setString(2, problema.getDescricaoProblema());
            pstmt.setDouble(3, problema.getCustoMaoDeObraProblema());
            pstmt.setInt(4, problema.getQtdPeca());
            pstmt.setLong(5, problema.getPeca().getIdPeca());
            pstmt.setLong(6, idProblema);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ProblemasExistentes> listarProblemasExistentes() {
        ArrayList<ProblemasExistentes> problemas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tb_qfx_problemas_existentes";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ProblemasExistentes problema = new ProblemasExistentes(
                        rs.getString("nome_problema"),
                        rs.getString("descricao_problema"),
                        rs.getDouble("custo_mao_de_obra_problema"),
                        rs.getInt("qtd_peca"),
                        new Peca()
                );
                problema.setIdProblemas(rs.getLong("id_problema"));
                problemas.add(problema);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return problemas;
    }

    public void deletarProblemaExistente(long idProblema) {
        try {
            String sql = "DELETE FROM tb_qfx_problemas_existentes WHERE id_problema = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setLong(1, idProblema);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public ProblemasExistentes buscarProblemasExistentesPorId(long problemasExistentesId) {
        ProblemasExistentes problema = null;
        String sql = "SELECT * FROM tb_qfx_problemas_existentes WHERE id_problema = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, problemasExistentesId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String nomeProblema = rs.getString("nome_problema");
                    String descricaoProblema = rs.getString("descricao_problema");
                    double custoMaoDeObraProblema = rs.getDouble("custo_mao_de_obra_problema");
                    int qtdPeca = rs.getInt("qtd_peca");
                    long idPeca = rs.getLong("id_peca");


                    Peca peca = new PecaDAO().buscarPecaPorId(idPeca);
                    problema = new ProblemasExistentes(nomeProblema, descricaoProblema, custoMaoDeObraProblema, qtdPeca, peca);
                    problema.setIdProblemas(problemasExistentesId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return problema;
    }
}
