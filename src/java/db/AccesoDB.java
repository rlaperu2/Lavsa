/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class AccesoDB {
	
	private AccesoDB() {
	    }

	    public static Connection getConnection() throws SQLException {
	        Connection cn = null;
	        try {
	            // Datos
	            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	            String url = "jdbc:sqlserver://192.168.1.50;databaseName=lavsa";
	            String user = "web";
	            String pass = "8t5lr2x";
                    
// 	            String driver = "com.mysql.jdbc.Driver";
//	            String url = "jdbc:mysql://localhost/sivecorp";
//	            String user = "root";
//	            String pass = "lucyla20";                   
                    
                    
	            // Cargar Driver
	            Class.forName(driver).newInstance();
	            // Obtener Conexión
	            cn = DriverManager.getConnection(url, user, pass);
	        } catch (ClassNotFoundException e) {
	            throw new SQLException("No se encontró el driver.");
	        } catch (SQLException e) {
	            throw e;
	        } catch (Exception e) {
	            throw new SQLException("Error en la conexión con la base de datos.");
	        }
	        return cn;
	    }
	}