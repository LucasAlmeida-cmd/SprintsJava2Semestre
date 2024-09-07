package quantumleap.banco;

import quantumleap.dominio.Agendamento;
import quantumleap.dominio.Oficina;
import quantumleap.dominio.Diagnostico;

import java.sql.*;
import java.util.ArrayList;

public class AgendamentoDAO {

    private Connection conexao;

    public AgendamentoDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void adicionarAgendamento(Long idOficina, Agendamento agendamento) {
        String sql = "INSERT INTO tb_qfx_agendamento (id_oficina, data_agendamento, hora_agendamento, descricao_agendamento) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, idOficina);
            pstmt.setString(2, agendamento.getData());
            pstmt.setString(3, agendamento.getHora());
            pstmt.setString(4, agendamento.getDiagnostico().getDescricao());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Agendamento buscarAgendamentoPorId(Long idAgendamento) {
        Agendamento agendamento = null;
        try {

            String sql = "SELECT a.*, o.localizacao_oficina " +
                    "FROM tb_qfx_agendamento a " +
                    "JOIN tb_qfx_oficina o ON a.id_oficina = o.id_oficina " +
                    "WHERE a.id_agendamento = ?";

            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setLong(1, idAgendamento);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Diagnostico diagnostico = new Diagnostico();
                diagnostico.setDescricao(rs.getString("descricao_agendamento"));
                agendamento = new Agendamento(diagnostico, rs.getString("data_agendamento"), rs.getString("hora_agendamento"));
                agendamento.setLocalizacao(rs.getString("localizacao_oficina"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return agendamento;
    }

    public void atualizarAgendamento(long idAgendamento, Agendamento agendamento) {
        String sql = "UPDATE tb_qfx_agendamento SET data_agendamento = ?, hora_agendamento = ?, descricao_agendamento = ? WHERE id_agendamento = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, agendamento.getData());
            pstmt.setString(2, agendamento.getHora());
            pstmt.setString(3, agendamento.getDiagnostico().getDescricao());
            pstmt.setLong(4, idAgendamento);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar o agendamento: " + e.getMessage(), e);
        }
    }

    public void deletarAgendamento(Long idAgendamento) {
        String sql = "DELETE FROM tb_qfx_agendamento WHERE id_agendamento = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, idAgendamento);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Agendamento> listarAgendamentos() {
        ArrayList<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT id_agendamento, data_agendamento, hora_agendamento FROM tb_qfx_agendamento";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Agendamento agendamento = new Agendamento();
                agendamento.setIdAgendamento(rs.getLong("id_agendamento"));
                agendamento.setData(rs.getString("data_agendamento"));
                agendamento.setHora(rs.getString("hora_agendamento"));

                agendamentos.add(agendamento);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return agendamentos;
    }
}
