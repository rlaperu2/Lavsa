package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.AccesoDB;
import domain.Articulo;
import domain.DetPedido;

public class ArticuloModel {
    
      public DetPedido traer_nombre_y_precios(String codigox , String pedido){
            Connection cn = null;   
             
            DetPedido detp = new DetPedido();
            try {
 
                cn = AccesoDB.getConnection();
                String sql = "select xarticulo = tb_articulo.c_t_articulo , precio=case when tb_pedido.c_c_moneda='01' then n_i_pventa_nac_sigv else n_i_pventa_ext_sigv  end  "
                                + " from tb_articulo,tb_pedido where tb_articulo.c_c_articulo= ?  and tb_pedido.c_c_pedido=? "   ;
                      
                PreparedStatement pstm = cn.prepareStatement(sql);
                pstm.setString(1, codigox);
                pstm.setString(2, pedido);
                ResultSet rs = pstm.executeQuery();
                if(!rs.next()){
                        throw new SQLException("Al obtener el articulo.");
                }
                
                
                detp.setC_t_articulo(rs.getString("xarticulo"));
                detp.setN_i_precio(rs.getFloat("precio"));
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
         return detp;  
        }
    
        public String traer_nombre(String codigox){
            Connection cn = null;   
            String xarticulo;
            try {
 
                cn = AccesoDB.getConnection();
                String sql = "select xarticulo = c_t_articulo "
                                + " from tb_articulo where c_c_articulo= ? "   ;
                      
                PreparedStatement pstm = cn.prepareStatement(sql);
                pstm.setString(1, codigox);
                ResultSet rs = pstm.executeQuery();
                if(!rs.next()){
                        throw new SQLException("Al obtener el articulo.");
                }
                xarticulo = rs.getString("xarticulo") ;
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
         return xarticulo;  
        }
          
        public List<Articulo>  traer_articulo_para_pedido(String codigox,String monedax){
            List<Articulo> lista = new ArrayList<Articulo>();
            Connection cn = null;   
            String xarticulo;
            try {
 
                cn = AccesoDB.getConnection();
                String sql = "select xarticulo = c_t_articulo ,   tb_articulo.n_i_pventa_nac_sigv , tb_articulo.n_i_pventa_ext_sigv  "
                                + " from tb_articulo where c_c_articulo= ? "   ;
                Articulo bean = new Articulo();

                PreparedStatement pstm = cn.prepareStatement(sql);
                pstm.setString(1, codigox);
                ResultSet rs = pstm.executeQuery();
                if(!rs.next()){
                        throw new SQLException("Al obtener el articulo.");
                }
                bean.setC_t_articulo(rs.getString("xarticulo"))  ;
                if (monedax=="01")
                {
                    bean.setPrecio( rs.getFloat("n_i_pventa_nac_sigv") );
                }
                else
                {
                    bean.setPrecio( rs.getFloat("n_i_pventa_ext_sigv") );
                }    
                        
                lista.add(bean);        
                
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
        
	public List<Articulo> traerPornombre(String nombre , String nombre2){
		
		List<Articulo> lista = new ArrayList<Articulo>();
		Connection cn = null;
	 
		try {
	    cn = AccesoDB.getConnection();
            
            // revisar si lo que digito es el codigo de una familia
            // si es una familia el criterio de busqueda sera solo por 
            String sql2="select encx=count(*) from tb_familia where c_c_linea='01' and  upper(c_c_familia)=? ";
            PreparedStatement pstm2=cn.prepareStatement(sql2);
            pstm2.setString(1, nombre.toUpperCase() );
            ResultSet rs2=pstm2.executeQuery();
 	    while(rs2.next()){
                if (rs2.getFloat("encx") >0 ){
                    nombre2="XXWWW==####EE";
                }
	    }
	    rs2.close();
	    pstm2.close();            
            
	    String sql= "select  rtrim(c_c_articulo) as c_c_articulo,c_t_articulo, TB_UNIDAD.c_t_uni_abre "+
                        " ,  case when tb_articulo.c_c_moneda ='01' then tb_articulo.n_i_pventa_nac_sigv else  " + 
                        " tb_articulo.n_i_pventa_ext_sigv  end as precio   , "+
                        "    moneda_venta.c_t_moneda_abre as moneda ,   "+ 
                        " c_t_linea,c_t_familia , tb_articulo.n_i_stock , tb_articulo.n_fl_archivo_grafico "+
                        "	  from TB_ARTICULO  "+
                        "	       inner join TB_UNIDAD "+
                        "				on tb_articulo.c_c_unidad = TB_UNIDAD.c_c_unidad  "+
                        "		   left join tb_moneda as moneda_venta  "+
                        "				on TB_ARTICULO.c_c_moneda  = moneda_venta.c_c_moneda "+ 	    					
                        "		   inner join tb_linea  "+
                        "		        on tb_articulo.c_c_linea = TB_LINEA.c_c_linea "+		
                        "		   inner join tb_familia  "+ 
                        "		        on tb_articulo.c_c_linea = tb_familia.c_c_linea and "+
                        "		           TB_ARTICULO.c_c_familia = TB_FAMILIA.c_c_familia  "+      
                        "  where tb_familia.n_fl_familia_activo=1 and  "+
                        "    tb_articulo.N_FL_ACTIVO = 1 and  ( tb_articulo.c_c_articulo  like  ? or tb_articulo.c_t_articulo like ?  )"+ 
                        " order by c_t_linea,c_t_familia,n_n_orden     ";
	    PreparedStatement pstm = cn.prepareStatement(sql);
	    nombre = nombre + "%";
            pstm.setString(1, nombre);
            nombre2 = "%" + nombre2 +"%";
            pstm.setString(2, nombre2);
            
	    ResultSet rs= pstm.executeQuery();
	    //System.out.println(sql);
	    while(rs.next()){
	    	Articulo bean = new Articulo();
                
//                System.out.println("paremtro entro " );
//                System.out.println(rs.getString("c_c_articulo") );
                
	    	bean.setC_c_articulo( rs.getString("c_c_articulo"));
	    	bean.setC_t_articulo( rs.getString("c_t_articulo"));
	    	bean.setC_t_uni_abre( rs.getString("c_t_uni_abre"));
	    	bean.setPrecio( rs.getFloat("precio"));
	    	bean.setMoneda( rs.getString("moneda"));
	    	bean.setC_t_linea( rs.getString("c_t_linea"));
	    	bean.setC_t_familia( rs.getString("c_t_familia"));
	    	bean.setN_i_stock( rs.getString("n_i_stock")); 	 
                bean.setN_fl_archivo_grafico(rs.getFloat("n_fl_archivo_grafico")); 	
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
