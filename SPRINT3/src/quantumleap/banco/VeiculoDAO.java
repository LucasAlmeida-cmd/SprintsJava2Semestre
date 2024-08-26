package quantumleap.banco;

import quantumleap.dominio.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VeiculoDAO {

    private Connection conexao;

    public VeiculoDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    private Long obterProximoIdVeiculo() {
        Long id = null;
        try {
            String sql = "SELECT SEQ_VEICULO_ID.NEXTVAL FROM DUAL";
            PreparedStatement comandoDeGeracao = conexao.prepareStatement(sql);
            ResultSet rs = comandoDeGeracao.executeQuery();
            while (rs.next()) {
                id = rs.getLong(1);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public void adicionarVeiculo(int idCliente, ArrayList<Veiculo> veiculos) {
        try {
            String sql = "INSERT INTO tb_qfx_veiculo (id_veiculo, id_cliente, montadora_veiculo, modelo_veiculo, ano_veiculo, placa_veiculo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conexao.prepareStatement(sql);

            for (Veiculo veiculo : veiculos) {
                Long idVeiculo = obterProximoIdVeiculo();

                pstmt.setLong(1, idVeiculo);
                pstmt.setInt(2, idCliente);
                pstmt.setString(3, veiculo.getMontadoraVeiculo());
                pstmt.setString(4, veiculo.getModeloVeiculo());
                pstmt.setDate(5, new java.sql.Date(veiculo.getAnoVeiculo().getTime()));
                pstmt.setString(6, veiculo.getPlacaVeiculo());

                pstmt.addBatch();
            }

            pstmt.executeBatch();
            System.out.println("Veículos inseridos com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}