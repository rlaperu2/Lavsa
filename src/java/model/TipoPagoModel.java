package model;

import db.AccesoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.AccesoDB;
import domain.TipoPago;


public class TipoPagoModel {
 
	public List<TipoPago> traertodo(){
		
		List<TipoPago> lista = new ArrayList<TipoPago>();
		Connection cn = null;
	 
		try {
	    cn = AccesoDB.getConnection();
	    String sql= "select c_c_tipo_pago,c_t_tipo_pago from tb_tipo_pago ";
	    PreparedStatement pstm = cn.prepareStatement(sql);
	 
	    ResultSet rs= pstm.executeQuery();
	  // System.out.println("richard0");
	    while(rs.next()){
	    	TipoPago bean = new TipoPago();
	    	bean.setC_c_tipo_pago(rs.getString("c_c_tipo_pago"));
	    	bean.setC_t_tipo_pago(rs.getString("c_t_tipo_pago"));
	    	lista.add(bean);
	    }
	    rs.close();
	    pstm.close();
    } catch (SQLException e) {
    	throw new RuntimeException(e.getMessage());
    } catch (Exception e) {
    	throw new RuntimeException("ERROR, no se tiene acceso al servidor.");
    } finally{
    	try {
	      cn.close();
      } catch (Exception e2) {
      }
    }
		return lista;
	}
	
}
