package quantumleap.banco;

import quantumleap.dominio.ProblemasExistentes;

import java.sql.*;


public class ProblemasExistentesDAO {

    private Connection conexao;

    public ProblemasExistentesDAO(Connection conexao) {
        this.conexao = conexao;
    }

    private Long obterProximoIdProblema() {
        Long id = null;
        try {
            String sql = "SELECT SEQ_PROBLEMA_ID.NEXTVAL FROM DUAL";
            PreparedStatement comandoDeGeracao = conexao.prepareStatement(sql);
            ResultSet rs = comandoDeGeracao.executeQuery();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public void adicionarProblemaExistente(ProblemasExistentes problema) {
        try {
            String sqlInsert = "INSERT INTO tb_qfx_problemas_existentes (id_problema, nome_problema, descricao_problema, custo_mao_de_obra_problema, qtd_peca, id_peca) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement comandoInsercao = conexao.prepareStatement(sqlInsert);

            Long idProblema = obterProximoIdProblema();
            comandoInsercao.setLong(1, idProblema);
            comandoInsercao.setString(2, problema.getNomeProblema());
            comandoInsercao.setString(3, problema.getDescricaoProblema());
            comandoInsercao.setDouble(4, problema.getCustoMaoDeObraProblema());
            comandoInsercao.setInt(5, problema.getQtdPeca());
            comandoInsercao.setLong(6, problema.getPeca().getIdPeca());

            comandoInsercao.executeUpdate();

            System.out.println("Problema existente inserido com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
