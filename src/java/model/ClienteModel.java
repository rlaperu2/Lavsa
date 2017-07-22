package model;

//import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import db.AccesoDB;
import domain.Cliente;
import util.Eureka;

public class ClienteModel {

    public String[][] buscarPorNombreNew(String nombre , String codusuario) {

        String names[][] = new String[10][2];

        Connection cn = null;
        Cliente bean = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select top 10 tb_cliente.c_c_cliente, C_T_CLIENTE = RTRIM(c_t_cliente),tb_cliente.c_c_ruc from TB_CLIENTE,tb_usuario "
                    + " where tb_cliente.c_t_cliente like ? and tb_cliente.c_c_vendedor = tb_usuario.c_c_vendedor_asignado and tb_usuario.c_c_usuario=? "
                    + "   order by tb_cliente.c_t_cliente ";

            PreparedStatement pstm = cn.prepareStatement(sql);
            //nombre ="%" + nombre + "%";
//                    System.out.println("estoy buscando");
//                    System.out.println( nombre );
            int fila = 0;
            pstm.setString(1, '%' + nombre + '%');
            pstm.setString(2, codusuario );
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                names[fila][0] = rs.getString("c_t_cliente");
                names[fila][1] = rs.getString("c_c_cliente");
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

    public List<Cliente> traerPorNombre(String nombre) {
        List<Cliente> lista = new ArrayList<>();
        Connection cn = null;
        Cliente bean = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select top 100 c_c_cliente, c_t_cliente,c_c_ruc from TB_CLIENTE where c_t_cliente like ? ";

            PreparedStatement pstm = cn.prepareStatement(sql);
	    //nombre ="%" + nombre + "%";

            pstm.setString(1, '%' + nombre + '%');
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                bean = new Cliente();
                bean.setC_c_cliente(rs.getString("c_c_cliente"));
                bean.setC_t_cliente(rs.getString("c_t_cliente"));
                bean.setC_c_ruc(rs.getString("c_c_ruc"));
                 
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

    public static List<Cliente> listarcliente(String nombre,String codusuario) {

        List<Cliente> listar = new ArrayList<Cliente>();
        // String sql="select top 100 c_c_cliente, c_t_cliente,c_c_ruc from TB_CLIENTE where c_t_cliente like ?";
        String sql = "select top 30 tb_cliente.c_c_cliente, tb_cliente.c_t_cliente, tb_cliente.c_c_ruc from TB_CLIENTE , tb_usuario "
                + " where tb_cliente.c_t_cliente like ? and tb_cliente.c_c_vendedor = tb_usuario.c_c_vendedor_asignado and tb_usuario.c_c_usuario=? ";
        Cliente cli = null;

        Connection cn;
        try {

            cn = AccesoDB.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, '%' + nombre + '%');
            pr.setString(2, codusuario);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                cli = new Cliente();
                cli.setC_c_cliente(rs.getString("c_c_cliente"));
                cli.setC_t_cliente(rs.getString("c_t_cliente"));
                cli.setC_c_ruc(rs.getString("c_c_ruc"));
                listar.add(cli);
            }
        } catch (Exception e) {
        }
        return listar;
    }

    public static Cliente seleccioncliente(String code) {

        Cliente clii = new Cliente();
        String sql = "select c_c_cliente,c_t_cliente,c_c_ruc from TB_CLIENTE where c_c_cliente=?";
        try {
            Connection cn = AccesoDB.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, code);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                clii.setC_c_cliente(rs.getString("c_c_cliente"));
                clii.setC_t_cliente(rs.getString("c_t_cliente"));
                clii.setC_c_ruc(rs.getString("c_c_ruc"));
            }

        } catch (SQLException ex) {

        }

        return clii;
    }
    
    
    
        public  Cliente seleccionclientePorRUC(String code) {

        Cliente clii = new Cliente();
        String sql = "exec sp_w2_cliente_por_ruc ?";
        try {
            Connection cn = AccesoDB.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, code);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                clii.setC_c_cliente(rs.getString("c_c_cliente"));
               
               
            }

        } catch (SQLException ex) {

        }

        return clii;
    }
    
    
      public String insertar(Cliente bean,String codusuario) {

        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
 
             // Insertar
            String  sql = "exec sp_web_crear_cliente ?,?,?";
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, bean.getC_c_ruc());
            pr.setString(2, bean.getC_t_cliente());
            pr.setString(3, codusuario);

            
            
            pr.executeUpdate();
            pr.close();
            cn.commit();
            return "";
        } catch (SQLException e) {
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            throw new RuntimeException("ERROR, no se tiene acceso al servidor.");
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }

    }

    
    

}
