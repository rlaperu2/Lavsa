/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.AccesoDB;
import domain.Empleado;
import domain.Usuario;
import util.Eureka;

/**
 *
 * @author salegria
 */
public class LogonModel {
     public Usuario validar(String usuario, String clave){
	    Usuario bean = null;
	    Connection cn = null;
	    try {
	      cn = AccesoDB.getConnection();
	      String sql = "select c_c_usuario , "
	          + " c_t_usuario , c_t_correo "
	          + "   "
	          + "from tb_usuario "
	          + "where c_t_usuario = ? "
	          + "and c_t_password = ?";
	      PreparedStatement pstm = cn.prepareStatement(sql);
	      pstm.setString(1, usuario);
	      pstm.setString(2, clave);
	      ResultSet rs = pstm.executeQuery();
	      if(rs.next()){
	        bean = new Usuario();
	        bean.setC_c_usuario(rs.getString("c_c_usuario"));
	        bean.setC_t_usuario(rs.getString("c_t_usuario"));
	        bean.setC_t_correo(rs.getString("c_t_correo"));
                //Eureka.CODIGO_USUARIO = rs.getString("c_c_usuario");
	      }
	      rs.close();
	      pstm.close();
	      if(bean == null){
	        throw new SQLException("ERROR, datos incorrectos.");
	      }
	    } catch (SQLException e) {
	      throw new RuntimeException(e.getMessage());
	    } catch(Exception e){
	      throw new RuntimeException("ERROR, no se tiene acceso a la BD.");
	    } finally{
	      try {
	        cn.close();
	      } catch (Exception e2) {
	      }
	    }
	    return bean;
	  }
	


	}
