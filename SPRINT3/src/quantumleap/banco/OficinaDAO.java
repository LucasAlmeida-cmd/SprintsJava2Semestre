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
            PreparedStatement comandoInsercao = conexao.prepareStatement(sqlInsert);

            comandoInsercao.setString(1, oficina.getNomeOficina());
            comandoInsercao.setString(2, oficina.getLocalizacaoOficina());
            comandoInsercao.setString(3, oficina.getTelefoneOficina());
            comandoInsercao.setString(4, oficina.getEmailOficina());

            comandoInsercao.execute();
            System.out.println("Oficina inserida com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Oficina buscarOficinaPorId(Long idOficina) {
        Oficina oficina = null;
        try {
            String sqlSelect = "SELECT nome_oficina, localizacao_oficina, telefone_oficina, email_oficina FROM tb_qfx_oficina WHERE id_oficina = ?";
            PreparedStatement comandoSelecao = conexao.prepareStatement(sqlSelect);
            comandoSelecao.setLong(1, idOficina);

            ResultSet rs = comandoSelecao.executeQuery();
            if (rs.next()) {
                ArrayList<Agendamento> agendamentos = (ArrayList<Agendamento>) buscarAgendamentosPorOficina(idOficina);

                oficina = new Oficina(
                        rs.getString("nome_oficina"),
                        rs.getString("localizacao_oficina"),
                        rs.getString("telefone_oficina"),
                        rs.getString("email_oficina"),
                        agendamentos
                );
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return oficina;
    }

    public List<Agendamento> buscarAgendamentosPorOficina(Long idOficina) {
        List<Agendamento> agendamentos = new ArrayList<>();
        try {
            String sqlSelect = "SELECT id_agendamento, id_diagnostico, data_agendamento, hora_agendamento FROM tb_qfx_agendamento WHERE id_oficina = ?";
            PreparedStatement comandoSelecao = conexao.prepareStatement(sqlSelect);
            comandoSelecao.setLong(1, idOficina);

            ResultSet rs = comandoSelecao.executeQuery();
            while (rs.next()) {
                Long idDiagnostico = rs.getLong("id_diagnostico");
                Diagnostico diagnostico = new DiagnosticoDAO().buscarDiagnosticoPorId(idDiagnostico);

                Agendamento agendamento = new Agendamento(
                        diagnostico,
                        rs.getString("data_agendamento"),
                        rs.getString("hora_agendamento")
                );
                agendamentos.add(agendamento);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return agendamentos;
    }



//    public List<Oficina> listarOficinas() {
//        List<Oficina> oficinas = new ArrayList<>();
//        try {
//            String sqlSelectAll = "SELECT id_oficina, nome_oficina, localizacao_oficina, telefone_oficina, email_oficina FROM tb_qfx_oficina";
//            PreparedStatement comandoSelecao = conexao.prepareStatement(sqlSelectAll);
//
//            ResultSet rs = comandoSelecao.executeQuery();
//            while (rs.next()) {
//                Oficina oficina = new Oficina(
//                        rs.getString("nome_oficina"),
//                        rs.getString("localizacao_oficina"),
//                        rs.getString("telefone_oficina"),
//                        rs.getString("email_oficina")
//                );
//                oficinas.add(oficina);
//            }
//            rs.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return oficinas;
//    }

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
                pstmt.setString(4, agendamento.getDescricao());
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            System.out.println("Agendamentos inseridos com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
