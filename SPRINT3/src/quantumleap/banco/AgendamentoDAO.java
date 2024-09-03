package quantumleap.banco;

import quantumleap.dominio.Agendamento;
import quantumleap.dominio.Oficina;
import quantumleap.dominio.Diagnostico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AgendamentoDAO {

    private Connection conexao;

    public AgendamentoDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    private Long obterProximoIdAgendamento() {
        Long id = null;
        try {
            String sql = "SELECT SEQ_AGENDAMENTO_ID.NEXTVAL FROM DUAL";
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

    public void adicionarAgendamento(Long idOficina, Agendamento agendamento) {
        String sql = "INSERT INTO tb_qfx_agendamento (id_agendamento, id_oficina, id_diagnostico, data_agendamento, hora_agendamento, localizacao_agendamento) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            Long idAgendamento = obterProximoIdAgendamento();
            pstmt.setLong(1, idAgendamento);
            pstmt.setLong(2, idOficina);
            pstmt.setLong(3, agendamento.getDiagnostico().getIdDiagnostico());
            pstmt.setString(4, agendamento.getData());
            pstmt.setString(5, agendamento.getHora());
            pstmt.setString(6, agendamento.getLocalizacao());

            pstmt.executeUpdate();
            System.out.println("Agendamento inserido com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public Agendamento buscarAgendamentoPorId(Long idAgendamento) {
//        Agendamento agendamento = null;
//        String sql = "SELECT * FROM tb_qfx_agendamento WHERE id_agendamento = ?";
//
//        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
//            pstmt.setLong(1, idAgendamento);
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                Oficina oficina = new OficinaDAO().buscarOficinaPorId(rs.getLong("id_oficina"));
//                Diagnostico diagnostico = new DiagnosticoDAO().buscarDiagnosticoPorId(rs.getLong("id_diagnostico"));
//                agendamento = new Agendamento(oficina, diagnostico, rs.getString("data_agendamento"), rs.getString("hora_agendamento"));
//                agendamento.setLocalizacao(rs.getString("localizacao_agendamento"));
//            }
//            rs.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return agendamento;
//    }
//
//    public void atualizarAgendamento(Agendamento agendamento) {
//        String sql = "UPDATE tb_qfx_agendamento SET id_oficina = ?, id_diagnostico = ?, data_agendamento = ?, hora_agendamento = ?, localizacao_agendamento = ? WHERE id_agendamento = ?";
//        OficinaDAO oficinaDAO = new OficinaDAO();
//
//        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
//            pstmt.setLong(1, agendamento.getOficina().getIdOficina());
//            pstmt.setLong(2, agendamento.getDiagnostico().getIdDiagnostico());
//            pstmt.setString(3, agendamento.getData());
//            pstmt.setString(4, agendamento.getHora());
//            pstmt.setString(5, agendamento.getLocalizacao());
//            pstmt.setLong(6, agendamento.getIdAgendamento());
//
//            pstmt.executeUpdate();
//            System.out.println("Agendamento atualizado com sucesso!");
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void deletarAgendamento(Long idAgendamento) {
        String sql = "DELETE FROM tb_qfx_agendamento WHERE id_agendamento = ?";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, idAgendamento);
            pstmt.executeUpdate();
            System.out.println("Agendamento deletado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public ArrayList<Agendamento> listarAgendamentos() {
//        ArrayList<Agendamento> agendamentos = new ArrayList<>();
//        String sql = "SELECT * FROM tb_qfx_agendamento";
//
//        try (PreparedStatement pstmt = conexao.prepareStatement(sql);
//             ResultSet rs = pstmt.executeQuery()) {
//            while (rs.next()) {
//                Oficina oficina = new OficinaDAO().buscarOficinaPorId(rs.getLong("id_oficina"));
//                Diagnostico diagnostico = new DiagnosticoDAO().buscarDiagnosticoPorId(rs.getLong("id_diagnostico"));
//
//                Agendamento agendamento = new Agendamento(oficina, diagnostico, rs.getString("data_agendamento"), rs.getString("hora_agendamento"));
//                agendamento.setLocalizacao(rs.getString("localizacao_agendamento"));
//
//                agendamentos.add(agendamento);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return agendamentos;
//    }
}
