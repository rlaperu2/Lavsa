package model;

import db.AccesoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.AccesoDB;
import domain.LugarEntrega;

public class LugarEntregaModel {

    public List<LugarEntrega> traerporCliente(String as_codigoclientex) {

        List<LugarEntrega> lista = new ArrayList<LugarEntrega>();
        Connection cn = null;

        try {
            cn = AccesoDB.getConnection();
            String sql = "select c_c_lugar,c_t_lugar from tb_lugar where c_c_cliente = ? ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, as_codigoclientex);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                LugarEntrega bean = new LugarEntrega();
                bean.setC_c_lugar(rs.getString("c_c_lugar"));
                bean.setC_t_lugar(rs.getString("c_t_lugar"));
                lista.add(bean);
            }
            rs.close();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("ERROR, no se tiene acceso al servidor.");
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
        return lista;
    }
    
   
    
    public String[][] traerporCliente_arrary(String as_codigoclientex) {

        //List<LugarEntrega> lista = new ArrayList<LugarEntrega>();
        String names[][] = new String[10][2];
        Connection cn = null;

        try {
            cn = AccesoDB.getConnection();
            String sql = "select c_c_lugar,c_t_lugar from tb_lugar where c_c_cliente = ? ";
            PreparedStatement pstm = cn.prepareStatement(sql);

            pstm.setString(1, as_codigoclientex);
            ResultSet rs = pstm.executeQuery();
   
            Integer fila=0;

            while (rs.next()) {
                   names[fila][0] = rs.getString("c_t_lugar");
                names[fila][1] = rs.getString("c_c_lugar");
                fila = fila + 1;
            }
       
            rs.close();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("ERROR, no se tiene acceso al servidor.");
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
        return names;
    }
    
    

}
