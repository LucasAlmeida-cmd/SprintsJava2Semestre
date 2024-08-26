package quantumleap.banco;

import quantumleap.dominio.Agendamento;
import quantumleap.dominio.Oficina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OficinaDAO {

    private Connection conexao;
    public OficinaDAO(){
        this.conexao = new ConnectionFactory().getConnection();
    }

    private Long obterProximoIdOficina() {
        Long id = null;
        try {
            String sql = "SELECT SEQ_OFICINA_ID.NEXTVAL FROM DUAL";
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


    public void adicinaOficina(Oficina oficina){
        try {
            String sqlInsert = "INSERT INTO tb_qfx_oficina (id_oficina, nome_oficina, localizacao_oficina, telefone_oficina, email_oficina) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement comandoInsercao = conexao.prepareStatement(sqlInsert);

            Long idOficina = obterProximoIdOficina();
            comandoInsercao.setLong(1, idOficina);
            comandoInsercao.setString(2, oficina.getNomeOficina());
            comandoInsercao.setString(3, oficina.getLocalizacaoOficina());
            comandoInsercao.setString(4, oficina.getTelefoneOficina());
            comandoInsercao.setString(5, oficina.getEmailOficina());


            comandoInsercao.execute();

            System.out.println("Oficina inserida com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarAgendamentos(Long idOficina, ArrayList<Agendamento> agendamentos) {
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
