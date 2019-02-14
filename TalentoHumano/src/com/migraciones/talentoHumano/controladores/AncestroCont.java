package com.migraciones.talentoHumano.controladores;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;

public abstract class AncestroCont {

	// metodos principales
	public boolean create(Object objeto) throws ClassNotFoundException {
		try {
			validarObjeto(objeto);
			validarInsercionRegistro(objeto);
			String SQL = insertSQL();
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.pstmt = conn.conexion.prepareStatement(SQL);
			executeInsertSQL(conn.pstmt);
			conn.pstmt.executeUpdate();
			conn.pstmt.close();
			conn.conexion.close();
			return true;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	public boolean update(Object objeto) throws ClassNotFoundException {
		try {
			validarObjeto(objeto);
			validarActualizacionRegistro(objeto);
			String SQL = updateSQL();
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.sentencia.executeUpdate(SQL);
			conn.sentencia.close();
			conn.conexion.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean delete(Object objeto) throws ClassNotFoundException {
		try {
			validarObjeto(objeto);
			validarEliminacionRegistro(objeto);
			String SQL = deleteSQL();
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.sentencia.executeUpdate(SQL);
	
			conn.sentencia.close();
			conn.conexion.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	// metodos de validaciones
	public abstract void validarObjeto(Object objeto);

	public abstract void validarInsercionRegistro(Object objeto);

	public abstract void validarActualizacionRegistro(Object objeto);

	public abstract void validarEliminacionRegistro(Object objeto);

	// metodos de consulta SQL
	public abstract String insertSQL();

	public abstract void executeInsertSQL(PreparedStatement pstmt);

	public abstract String updateSQL();

	public abstract String deleteSQL();
}