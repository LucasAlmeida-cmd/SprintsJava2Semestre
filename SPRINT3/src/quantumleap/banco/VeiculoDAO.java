package quantumleap.banco;

import quantumleap.dominio.Cliente;
import quantumleap.dominio.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    private Connection conexao;

    public VeiculoDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }


    public void adicionarVeiculo(Cliente cliente, ArrayList<Veiculo> veiculos) {
        String sql = "INSERT INTO tb_qfx_veiculo (id_cliente, montadora_veiculo, modelo_veiculo, ano_veiculo, quantidade_quilometros, placa_veiculo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql, new String[] {"id_veiculo"})) {

            // Adicionar veículos ao lote
            for (Veiculo veiculo : veiculos) {
                pstmt.setLong(1, cliente.getIdCliente());
                pstmt.setString(2, veiculo.getMontadoraVeiculo());
                pstmt.setString(3, veiculo.getModeloVeiculo());
                pstmt.setDate(4, new java.sql.Date(veiculo.getAnoVeiculo().getTime()));
                pstmt.setDouble(5, veiculo.getQuantidadeQuilometros());
                pstmt.setString(6, veiculo.getPlacaVeiculo());
                pstmt.addBatch();
            }

            // Executar o lote e obter IDs gerados
            int[] affectedRows = pstmt.executeBatch();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                int i = 0;
                while (generatedKeys.next()) {
                    if (i < veiculos.size()) {
                        veiculos.get(i).setIdVeiculo(generatedKeys.getLong(1));
                        i++;
                    }
                }
            }

            // Verificar se todas as inserções foram bem-sucedidas
            for (int affectedRow : affectedRows) {
                if (affectedRow == Statement.EXECUTE_FAILED) {
                    throw new SQLException("Falha ao inserir um ou mais veículos.");
                }
            }

            System.out.println("Veículos inseridos com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public Veiculo buscarVeiculoPorId(long veiculoId) {
        Veiculo veiculo = null;
        try {
            String sql = "SELECT * FROM tb_qfx_veiculo WHERE id_veiculo = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setLong(1, veiculoId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                veiculo = new Veiculo(
                        rs.getString("montadora_veiculo"),
                        rs.getString("modelo_veiculo"),
                        rs.getDate("ano_veiculo"),
                        rs.getDouble("quantidade_quilometros"),
                        rs.getString("placa_veiculo")
                );
                veiculo.setIdVeiculo(rs.getLong("id_veiculo"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculo;
    }

    public void atualizarVeiculo(long idVeiculo, Veiculo veiculo) {
        try {
            String sql = "UPDATE tb_qfx_veiculo SET montadora_veiculo = ?, modelo_veiculo = ?, ano_veiculo = ?, quantidade_quilometros = ?, placa_veiculo = ? WHERE id_veiculo = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, veiculo.getMontadoraVeiculo());
            pstmt.setString(2, veiculo.getModeloVeiculo());
            pstmt.setDate(3, new java.sql.Date(veiculo.getAnoVeiculo().getTime()));
            pstmt.setDouble(4, veiculo.getQuantidadeQuilometros());
            pstmt.setString(5, veiculo.getPlacaVeiculo());
            pstmt.setLong(6, idVeiculo); // Use o ID fornecido para a cláusula WHERE

            pstmt.executeUpdate();
            System.out.println("Veículo atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarVeiculo(long veiculoId) {
        try {
            String sql = "DELETE FROM tb_qfx_veiculo WHERE id_veiculo = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setLong(1, veiculoId);

            pstmt.executeUpdate();
            System.out.println("Veículo deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Veiculo> listarVeiculos() {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        try {
            String sql = "SELECT v.id_veiculo, v.montadora_veiculo, v.modelo_veiculo, v.ano_veiculo, v.quantidade_quilometros, v.placa_veiculo, c.nome_cliente " +
                    "FROM tb_qfx_veiculo v " +
                    "JOIN tb_qfx_cliente c ON v.id_cliente = c.id_cliente";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Veiculo veiculo = new Veiculo(
                        rs.getString("montadora_veiculo"),
                        rs.getString("modelo_veiculo"),
                        rs.getDate("ano_veiculo"),
                        rs.getDouble("quantidade_quilometros"),
                        rs.getString("placa_veiculo")
                );
                veiculo.setIdVeiculo(rs.getLong("id_veiculo"));

                // Adiciona o nome do cliente ao veículo
                veiculo.setNomeCliente(rs.getString("nome_cliente"));

                veiculos.add(veiculo);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculos;
    }

}
