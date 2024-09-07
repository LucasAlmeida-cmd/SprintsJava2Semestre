package quantumleap.banco;

import quantumleap.dominio.Agendamento;
import quantumleap.dominio.Diagnostico;
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
        try {
            String sqlInsert = "INSERT INTO tb_qfx_oficina (nome_oficina, localizacao_oficina, telefone_oficina, email_oficina) VALUES (?, ?, ?, ?)";
            PreparedStatement comandoInsercao = conexao.prepareStatement(sqlInsert, new String[] {"id_oficina"});

            comandoInsercao.setString(1, oficina.getNomeOficina());
            comandoInsercao.setString(2, oficina.getLocalizacaoOficina());
            comandoInsercao.setString(3, oficina.getTelefoneOficina());
            comandoInsercao.setString(4, oficina.getEmailOficina());

            int rowsAffected = comandoInsercao.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Falha ao inserir o cliente, nenhuma linha foi afetada.");
            }
            try (ResultSet generatedKeys = comandoInsercao.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    oficina.setIdOficina(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Falha ao obter o ID gerado para o cliente.");
                }
            }
            System.out.println("Oficina inserida com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Oficina buscarOficinaPorId(Long idOficina) {
        Oficina oficina = null;

        try {
            // Primeiro, busca os detalhes da oficina
            String sqlOficina = "SELECT nome_oficina, localizacao_oficina, telefone_oficina, email_oficina FROM tb_qfx_oficina WHERE id_oficina = ?";
            PreparedStatement pstmtOficina = conexao.prepareStatement(sqlOficina);
            pstmtOficina.setLong(1, idOficina);
            ResultSet rsOficina = pstmtOficina.executeQuery();

            if (rsOficina.next()) {
                oficina = new Oficina(
                        rsOficina.getString("nome_oficina"),
                        rsOficina.getString("localizacao_oficina"),
                        rsOficina.getString("telefone_oficina"),
                        rsOficina.getString("email_oficina"),
                        new ArrayList<>()
                );

                // Agora, busca os agendamentos relacionados a essa oficina
                String sqlAgendamentos = "SELECT data_agendamento, hora_agendamento, descricao_agendamento FROM tb_qfx_agendamento WHERE id_oficina = ?";
                PreparedStatement pstmtAgendamentos = conexao.prepareStatement(sqlAgendamentos);
                pstmtAgendamentos.setLong(1, idOficina);
                ResultSet rsAgendamentos = pstmtAgendamentos.executeQuery();

                while (rsAgendamentos.next()) {
                    Diagnostico diagnostico = new Diagnostico();
                    diagnostico.setDescricao(rsAgendamentos.getString("descricao_agendamento"));

                    Agendamento agendamento = new Agendamento(
                            diagnostico,
                            rsAgendamentos.getString("data_agendamento"),
                            rsAgendamentos.getString("hora_agendamento")
                    );

                    oficina.getAgendamentos().add(agendamento);
                }

                rsAgendamentos.close();
            }

            rsOficina.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return oficina;
    }



    public List<Oficina> listarOficinas() {
        List<Oficina> oficinas = new ArrayList<>();
        try {
            String sqlSelectAll = "SELECT nome_oficina, localizacao_oficina, telefone_oficina, email_oficina FROM tb_qfx_oficina";
            PreparedStatement comandoSelecao = conexao.prepareStatement(sqlSelectAll);

            ResultSet rs = comandoSelecao.executeQuery();
            while (rs.next()) {
                Oficina oficina = new Oficina(
                        rs.getString("nome_oficina"),
                        rs.getString("localizacao_oficina"),
                        rs.getString("telefone_oficina"),
                        rs.getString("email_oficina")
                );
                oficinas.add(oficina);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return oficinas;
    }


    public void atualizarOficina(Long idOficina, Oficina oficina) {
        try {
            String sqlUpdate = "UPDATE tb_qfx_oficina SET nome_oficina = ?, localizacao_oficina = ?, telefone_oficina = ?, email_oficina = ? WHERE id_oficina = ?";
            PreparedStatement comandoAtualizacao = conexao.prepareStatement(sqlUpdate);

            comandoAtualizacao.setString(1, oficina.getNomeOficina());
            comandoAtualizacao.setString(2, oficina.getLocalizacaoOficina());
            comandoAtualizacao.setString(3, oficina.getTelefoneOficina());
            comandoAtualizacao.setString(4, oficina.getEmailOficina());
            comandoAtualizacao.setLong(5, idOficina);

            comandoAtualizacao.executeUpdate();
            System.out.println("Oficina atualizada com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluirOficina(Long idOficina) {
        try {
            String sqlDelete = "DELETE FROM tb_qfx_oficina WHERE id_oficina = ?";
            PreparedStatement comandoExclusao = conexao.prepareStatement(sqlDelete);
            comandoExclusao.setLong(1, idOficina);

            comandoExclusao.execute();
            System.out.println("Oficina excluída com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarAgendamentos(Long idOficina, List<Agendamento> agendamentos) {
        String sql = "INSERT INTO tb_qfx_agendamento (id_agendamento, id_oficina, data_agendamento, hora_agendamento, descricao_agendamento) VALUES (SEQ_AGENDAMENTO_ID.NEXTVAL, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            for (Agendamento agendamento : agendamentos) {
                pstmt.setLong(1, idOficina);
                pstmt.setString(2, agendamento.getData());
                pstmt.setString(3, agendamento.getHora());
                pstmt.setString(4, agendamento.getDiagnostico().getDescricao());
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            System.out.println("Agendamentos inseridos com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
