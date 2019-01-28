package com.migraciones.talentoHumano.controladores;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.Feriado;
import com.migraciones.talentoHumano.utilities.FechaUtil;

public class FeriadoCont extends AncestroCont {
	private FechaUtil util = new FechaUtil();
	private Feriado feriado = new Feriado();

	public ArrayList<Feriado> getAll() throws ClassNotFoundException {
		ArrayList<Feriado> listaFeriados = new ArrayList<Feriado>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM dgth.feriados ORDER BY f_id");
			while (conn.resultado.next()) {
				Feriado feriado = new Feriado();
				feriado.setId(conn.resultado.getInt("f_id"));
				feriado.setDescripcion(conn.resultado.getString("f_descripcion"));
				feriado.setFechaDesde(conn.resultado.getDate("f_desde"));
				feriado.setFechaHasta(conn.resultado.getDate("f_hasta"));
								
				listaFeriados.add(feriado);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaFeriados;
	}

	public ArrayList<Feriado> getHistorialRango(String inicio, String fin) throws ClassNotFoundException {
		ArrayList<Feriado> histarialListado = new ArrayList<Feriado>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery(
					"SELECT * FROM dgth.feriados WHERE f_desde BETWEEN '"
							+ inicio + "' AND '" + fin + "'AND f_estado = 'A' ORDER BY f_id");
			while (conn.resultado.next()) {
				Feriado historial = new Feriado();
				historial.setId(conn.resultado.getInt("f_id"));
				historial.setFechaDesde(conn.resultado.getDate("f_desde"));
				historial.setFechaHasta(conn.resultado.getDate("f_hasta"));
				historial.setDescripcion(conn.resultado.getString("f_descripcion"));
				
				histarialListado.add(historial);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return histarialListado;
	}
	
	public boolean agregarNuevoFeriado(Feriado nuevoFeriado) throws ClassNotFoundException, SQLException {
		ConexionPostgresql conn = new ConexionPostgresql();
		conn.conexion.setAutoCommit(false);
		PreparedStatement pstmtFeriado = null;
		try {
			// insertar en tabla ficha_personal.personales
			String iFeriadoSQL = "INSERT INTO dgth.feriados(f_desde,f_hasta,f_descripcion,usuario_alta) VALUES(?,?,?,?)";
			pstmtFeriado = conn.conexion.prepareStatement(iFeriadoSQL);
			java.sql.Date sqlDateDesde = new java.sql.Date(nuevoFeriado.getFechaDesde().getTime());
			pstmtFeriado.setDate(1,sqlDateDesde);
			java.sql.Date sqlDateHasta = new java.sql.Date(nuevoFeriado.getFechaHasta().getTime());
			pstmtFeriado.setDate(2,sqlDateHasta);
			pstmtFeriado.setString(3, nuevoFeriado.getDescripcion());
			pstmtFeriado.setString(4, nuevoFeriado.getUsuarioAlta());
			pstmtFeriado.executeUpdate();
			conn.conexion.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.conexion.rollback();
			return false;
		} finally {
			if (pstmtFeriado != null) {
				pstmtFeriado.close();
			}
		}

	}

public boolean actualizarFeriado(Feriado feriado) throws ClassNotFoundException {
		boolean control = false;
		ConexionPostgresql connpg = new ConexionPostgresql();
		String SQL = "UPDATE dgth.feriados SET f_desde='"
				+ util.getDateToString(feriado.getFechaDesde()) + "', f_hasta='"
				+ util.getDateToString(feriado.getFechaHasta()) + "', f_descripcion='" + feriado.getDescripcion()
				+ "', usuario_modificacion='" + feriado.getUsuarioModificacion() + "' WHERE f_id=" + feriado.getId();
		try {
			connpg.sentencia = (Statement) connpg.conexion.createStatement();
			connpg.sentencia.executeUpdate(SQL);
			connpg.conexion.close();
			control = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return control;
	}
	
	public boolean eliminarRegistro(Feriado feriado) throws ClassNotFoundException {
		boolean control = false;
		ConexionPostgresql connpg = new ConexionPostgresql();
		String SQL = "UPDATE dgth.feriados SET f_estado='H' WHERE f_id=" + feriado.getId();
		try {
			connpg.sentencia = (Statement) connpg.conexion.createStatement();
			connpg.sentencia.executeUpdate(SQL);
			connpg.conexion.close();
			control = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return control;
	}

	
	@Override
	public void validarObjeto(Object objeto) {
		this.feriado = (Feriado) objeto;
	}

	@Override
	public void validarInsercionRegistro(Object objeto) {
	}

	@Override
	public void validarActualizacionRegistro(Object objeto) {
	}

	@Override
	public void validarEliminacionRegistro(Object objeto) {
	}

	@Override
	public String insertSQL() {
		return "";
	}

	@Override
	public void executeInsertSQL(PreparedStatement pstmt) {
	}

	@Override
	public String updateSQL() {
		java.sql.Date sqlDateDesde = new java.sql.Date(this.feriado.getFechaDesde().getTime());
		java.sql.Date sqlDateHasta = new java.sql.Date(this.feriado.getFechaHasta().getTime());
		String SQL = "UPDATE dgth.feriados SET f_desde='" + sqlDateDesde
				+ "', f_hasta='" + sqlDateHasta + "', f_descripcion='" + this.feriado.getDescripcion()
				+ "' WHERE f_id='" + this.feriado.getId() + "';";
		return SQL;
	}

	@Override
	public String deleteSQL() {
		return "";
	}
}
