 
package model;

import db.AccesoDB;
import domain.Cliente;
import domain.Comisiones;
import domain.CtaxCobrar;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
 

 
public class ComisionesModel {
    
       public List<Comisiones> traerComisiones(String as_usuario , String as_moneda , String as_anio , String as_mes) {
           
       
           
        List<Comisiones> lista = new ArrayList<>();
        Connection cn = null;
        Comisiones bean = null;
        try {
            cn = AccesoDB.getConnection();
 
            CallableStatement storeProc = cn.prepareCall("{call sp_comisiones_inicial(?,?,?,?)}");
            storeProc.setString(1,  as_usuario);
            storeProc.setString(2, as_moneda);
            storeProc.setInt(3, Integer.valueOf(as_anio) );
            storeProc.setInt(4, Integer.valueOf(as_mes)  );
    
            ResultSet rs = storeProc.executeQuery();
            Float Total_comisiones  ;
            Total_comisiones = Float.valueOf(0);
            while (rs.next()) {
        
                bean = new Comisiones();
 
                bean.setDocumento(new CtaxCobrar());
                bean.getDocumento().setC_t_tipo_doc_abrev(rs.getString("c_t_tipo_doc_abre"));
                bean.getDocumento().setC_t_documento(rs.getString("c_n_salida"));
                bean.getDocumento().setC_t_moneda_abre(rs.getString("c_t_moneda_abre"));
                bean.getDocumento().setN_i_total(rs.getFloat("subtotal") );
                bean.setComision(rs.getFloat("tot"));
                bean.getDocumento().setCliente(new Cliente());
                bean.getDocumento().getCliente().setC_t_cliente(rs.getString("c_t_cliente"));
//                bean.getDocumento().setN_i_saldo(rs.getFloat("t_saldo"));
                Total_comisiones=Total_comisiones + rs.getFloat("tot");
                lista.add(bean);
               
            }
             
            
            bean = new Comisiones();
            bean.setDocumento(new CtaxCobrar());
            bean.getDocumento().setCliente(new Cliente());
            bean.getDocumento().getCliente().setC_t_cliente("Total: " +  String.valueOf(Total_comisiones ));
          
             
           // bean.setComision(    Float.valueOf(df.format(Total_comisiones))           );
            lista.add(bean);
            
            rs.close();
            storeProc.close();
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
}
