package quantumleap.banco;

import quantumleap.dominio.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DiagnosticoDAO {

    private Connection conexao;
    public DiagnosticoDAO(){
        this.conexao = new ConnectionFactory().getConnection();
    }

    private Long obterProximoIdDiagnostico() {
        Long id = null;
        try {
            String sql = "SELECT SEQ_DIAGNOSTICO_ID.NEXTVAL FROM DUAL";
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

    public void adicionarDiagnostico(Diagnostico diagnostico) {
        try {
            String sql = "INSERT INTO tb_diagnostico (cliente_id, veiculo_id, problemas_existentes_id, guincho_id, orcamento) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setLong(1, diagnostico.getCliente().getIdCliente());
            pstmt.setLong(2, diagnostico.getVeiculo().getIdVeiculo());
            pstmt.setLong(3, diagnostico.getProblemasExistentes().getIdProblemas());
            pstmt.setLong(4, diagnostico.getGuincho().getIdGuincho());
            pstmt.setDouble(5, diagnostico.getOrcamento());

            pstmt.executeUpdate();
            System.out.println("Diagnóstico inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Diagnostico buscarDiagnosticoPorId(Long idDiagnostico) {
        Diagnostico diagnostico = null;
        try {
            String sql = "SELECT * FROM tb_diagnostico WHERE id_diagnostico = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setLong(1, idDiagnostico);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Cliente cliente = new ClienteDAO().buscarClientePorId(rs.getLong("cliente_id"));
                Veiculo veiculo = new VeiculoDAO().buscarVeiculoPorId(rs.getLong("veiculo_id"));
                ProblemasExistentes problemasExistentes = new ProblemasExistentesDAO().buscarProblemasExistentesPorId(rs.getLong("problemas_existentes_id"));
                Guincho guincho = new GuinchoDAO().buscarGuinchoPorId(rs.getLong("guincho_id"));

                diagnostico = new Diagnostico(cliente, veiculo, problemasExistentes, guincho);
                diagnostico.setOrcamento(rs.getDouble("orcamento"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnostico;
    }

    public void atualizarDiagnostico(Diagnostico diagnostico) {
        try {
            String sql = "UPDATE tb_diagnostico SET cliente_id = ?, veiculo_id = ?, problemas_existentes_id = ?, guincho_id = ?, orcamento = ? WHERE id_diagnostico = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setLong(1, diagnostico.getCliente().getIdCliente());
            pstmt.setLong(2, diagnostico.getVeiculo().getIdVeiculo());
            pstmt.setLong(3, diagnostico.getProblemasExistentes().getIdProblemas());
            pstmt.setLong(4, diagnostico.getGuincho().getIdGuincho());
            pstmt.setDouble(5, diagnostico.getOrcamento());
            pstmt.setLong(6, diagnostico.getIdDiagnostico()); // Assumindo que Diagnostico tem um método getIdDiagnostico

            pstmt.executeUpdate();
            System.out.println("Diagnóstico atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarDiagnostico(Long idDiagnostico) {
        try {
            String sql = "DELETE FROM tb_diagnostico WHERE id_diagnostico = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setLong(1, idDiagnostico);

            pstmt.executeUpdate();
            System.out.println("Diagnóstico deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Diagnostico> listarDiagnosticos() {
        ArrayList<Diagnostico> diagnosticos = new ArrayList<>();
        String sql = "SELECT * FROM tb_diagnostico";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                long clienteId = rs.getLong("cliente_id");
                long veiculoId = rs.getLong("veiculo_id");
                long problemasExistentesId = rs.getLong("problemas_existentes_id");
                long guinchoId = rs.getLong("guincho_id");

                // Recupera os objetos relacionados
                Cliente cliente = new ClienteDAO().buscarClientePorId(clienteId);
                Veiculo veiculo = new VeiculoDAO().buscarVeiculoPorId(veiculoId);
                ProblemasExistentes problemasExistentes = new ProblemasExistentesDAO().buscarProblemaPorId(problemasExistentesId);
                Guincho guincho = new GuinchoDAO().buscarGuinchoPorId(guinchoId);

                // Cria o Diagnostico e define o orçamento
                Diagnostico diagnostico = new Diagnostico(cliente, veiculo, problemasExistentes, guincho);
                diagnostico.setOrcamento(rs.getDouble("orcamento"));

                // Adiciona o Diagnostico à lista
                diagnosticos.add(diagnostico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnosticos;
    }
}
