package model;

import db.AccesoDB;
import domain.CtaxCobrar;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CtaxCobrarModel {

    public List<CtaxCobrar> traerCtaxCobrar(String codigo) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        
        List<CtaxCobrar> lista = new ArrayList<>();
        Connection cn = null;
        CtaxCobrar bean = null;
        Float totalSoles ;
        Float totalDolares;
        totalSoles = Float.valueOf(0);
        totalDolares = Float.valueOf(0);
        try {
            cn = AccesoDB.getConnection();

            CallableStatement storeProc = cn.prepareCall("{call sp_ctaxcobrar(?)}");
            storeProc.setString(1, codigo);
            ResultSet rs = storeProc.executeQuery();
            String ls_fvenc="";  
            while (rs.next()) {
                bean = new CtaxCobrar();
          
                bean.setC_t_documento(rs.getString("n_documento"));
             
                bean.setC_t_tipo_doc_abrev(rs.getString("c_t_tipo_doc_abre"));
                bean.setN_i_saldo(rs.getFloat("t_saldo"));
              
                bean.setN_i_total(rs.getFloat("n_i_total"));
                bean.setD_dt_documento(rs.getString("f_documento"));
           
                  
                //rs.getString("f_vencimiento")
             if  (!rs.getString("f_vencimientox").isEmpty()){
                 bean.setD_dt_vencimiento(  rs.getString("f_vencimientox").substring(0,6) + rs.getString("f_vencimientox").substring(8)  );
             }
                
       
                bean.setC_t_moneda_abre(rs.getString("c_t_moneda_abre"));
               
                bean.setN_intbanco(rs.getString("n_intbanco"));
                
 
                
//                if (rs.getString("c_c_moneda").equals("02")){
//                    totalDolares = totalDolares + rs.getFloat("t_saldo");
//                }
//                else
//                {
//                     totalSoles = totalSoles + rs.getFloat("t_saldo");
//                }    

                
                lista.add(bean);
            }
            
             
//              bean = new CtaxCobrar();
//              bean.setN_intbanco("Total");
//              bean.setC_t_moneda_abre("S/.");
//              bean.setN_i_saldo(totalSoles*100/100);
//              lista.add(bean);
//               
//              bean = new CtaxCobrar();
//              bean.setN_intbanco("Total");
//              bean.setC_t_moneda_abre("US$");
//               
//              bean.setN_i_saldo(totalDolares*100/100);
//              lista.add(bean); 
               
            
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
