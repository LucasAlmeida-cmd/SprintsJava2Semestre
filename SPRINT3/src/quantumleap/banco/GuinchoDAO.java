package quantumleap.banco;

import quantumleap.dominio.Guincho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuinchoDAO {

    private Connection conexao;

    public GuinchoDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }


    public void adicionaGuincho(Guincho guincho) {
        String sqlInsert = "INSERT INTO tb_qfx_guincho (placa_guincho, preco_guincho, carga_maxima) VALUES (?, ?, ?)";
        try {
            PreparedStatement comandoDeInsercao = conexao.prepareStatement(sqlInsert, new String[] {"id_guincho"});
            comandoDeInsercao.setString(1, guincho.getPlaca());
            comandoDeInsercao.setDouble(2, guincho.getPreco());
            comandoDeInsercao.setDouble(3, guincho.getCargaMaxima());

            int rowsAffected = comandoDeInsercao.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Falha ao inserir o cliente, nenhuma linha foi afetada.");
            }
            try (ResultSet generatedKeys = comandoDeInsercao.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    guincho.setIdGuincho(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Falha ao obter o ID gerado para o cliente.");
                }
            }
            comandoDeInsercao.close();
            System.out.println("Guincho ok ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Guincho buscarGuinchoPorId(long guinchoId) {
        Guincho guincho = null;
        try {
            String sql = "SELECT placa_guincho, preco_guincho, carga_maxima FROM tb_qfx_guincho WHERE id_guincho = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setLong(1, guinchoId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String placa = rs.getString("placa_guincho");
                double preco = rs.getDouble("preco_guincho");
                double cargaMaxima = rs.getDouble("carga_maxima");

                guincho = new Guincho(placa, preco, cargaMaxima);
                guincho.setIdGuincho(guinchoId);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return guincho;
    }

    public void atualizarGuincho(long idGuincho, Guincho guincho) {
        try {
            String sqlUpdate = "UPDATE tb_qfx_guincho SET placa_guincho = ?, preco_guincho = ?, carga_maxima = ? WHERE id_guincho = ?";
            PreparedStatement comandoDeAtualizacao = conexao.prepareStatement(sqlUpdate);
            comandoDeAtualizacao.setString(1, guincho.getPlaca());
            comandoDeAtualizacao.setDouble(2, guincho.getPreco());
            comandoDeAtualizacao.setDouble(3, guincho.getCargaMaxima());
            comandoDeAtualizacao.setLong(4, idGuincho);

            comandoDeAtualizacao.executeUpdate();
            comandoDeAtualizacao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Guincho> listarGuincho(){
        ArrayList<Guincho> guinchos = new ArrayList<>();
        try{
            String sql = "SELECT * FROM tb_qfx_guincho";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                Guincho guincho = new Guincho();
                guincho.setIdGuincho(rs.getLong("id_guincho"));
                guincho.setPlaca(rs.getString("placa_guincho"));
                guincho.setPreco(rs.getDouble("preco_guincho"));
                guincho.setCargaMaxima(rs.getDouble("carga_maxima"));
                guinchos.add(guincho);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return guinchos;
    }

    public void deletarGuincho(long guinchoId) {
        try {
            String sqlDelete = "DELETE FROM tb_qfx_guincho WHERE id_guincho = ?";
            PreparedStatement comandoDeDelecao = conexao.prepareStatement(sqlDelete);
            comandoDeDelecao.setLong(1, guinchoId);

            comandoDeDelecao.executeUpdate();
            comandoDeDelecao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
