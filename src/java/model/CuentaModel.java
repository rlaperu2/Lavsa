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

/**
 *
 * @author salegria
 */
public class CuentaModel {public void registrarDeposito(String cuenta, Double importe, String empleado) {
		Connection cn = null;
		try {
			// Inicio de la Tx
			cn =AccesoDB.getConnection();
			cn.setAutoCommit(false);
			// Leer contador
			String sql = "select dec_cuensaldo, int_cuencontmov "
					+ "from cuenta "
					+ "where chr_cuencodigo = ? for update";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, cuenta);
			ResultSet rs = pstm.executeQuery();
			if(!rs.next()){
				throw new SQLException("Cuenta no existe.");
			}
			Double saldo = rs.getDouble("dec_cuensaldo") + importe;
			int cont = rs.getInt("int_cuencontmov") + 1;
			rs.close();
			pstm.close();
			// Actualizar cuenta
			sql = "update cuenta "
					+ "set dec_cuensaldo = ?, int_cuencontmov = ? "
					+ "where chr_cuencodigo = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setDouble(1, saldo);
			pstm.setInt(2, cont);
			pstm.setString(3, cuenta);
			pstm.executeUpdate();
			pstm.close();
			// Registrar movimiento
			sql = "insert into movimiento(chr_cuencodigo,int_movinumero,"
					+ "dtt_movifecha,chr_emplcodigo,chr_tipocodigo,"
					+ "dec_moviimporte) values(?,?,sysdate,?,'003',?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, cuenta);
			pstm.setInt(2, cont);
			pstm.setString(3, empleado);
			pstm.setDouble(4, importe);
			pstm.executeUpdate();
			pstm.close();
			// Fin de la Tx
			cn.commit();
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
