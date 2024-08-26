package quantumleap.banco;

import quantumleap.dominio.Guincho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuinchoDAO {

    private Connection conexao;
    public GuinchoDAO(){
        this.conexao = new ConnectionFactory().getConnection();
    }

    private Long obterProximoIdGuincho() {
        Long id = null;
        try {
            String sql = "SELECT SEQ_GUINCHO_ID.NEXTVAL FROM DUAL";
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

    public void adicionaGuincho(Guincho guincho){
        try{
            String sqlInsert = "INSERT INTO tb_qfx_guincho (id_guincho, placa_guincho, preco_guincho, carga_maxima) VALUES (?, ?, ?, ?)";
            PreparedStatement comandoDeInsercao = conexao.prepareStatement(sqlInsert);
            comandoDeInsercao.setLong(1,obterProximoIdGuincho());
            comandoDeInsercao.setString(2, guincho.getPlaca());
            comandoDeInsercao.setDouble(3, guincho.getPreco());
            comandoDeInsercao.setDouble(4, guincho.getCargaMaxima());

            comandoDeInsercao.execute();
            comandoDeInsercao.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }



}
