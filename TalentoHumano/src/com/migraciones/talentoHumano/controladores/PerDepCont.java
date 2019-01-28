package com.migraciones.talentoHumano.controladores;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.PerDep;
import com.migraciones.talentoHumano.utilities.FechaUtil;

public class PerDepCont extends AncestroCont {

	private PerDep personalDependencia = new PerDep();

	public ArrayList<PerDep> getHistorial(String cedula) throws ClassNotFoundException {
		ArrayList<PerDep> dependenciaHistorial = new ArrayList<PerDep>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.personales_dependencias WHERE personal_cedula='"
							+ cedula + "' AND perdep_estado='A' ORDER BY perdep_id");
			while (conn.resultado.next()) {
				PerDep perdep = new PerDep();
				perdep.setId(conn.resultado.getInt("perdep_id"));
				perdep.setCedula(conn.resultado.getString("personal_cedula"));
				perdep.setDependenciaId(conn.resultado.getInt("dependencia_id"));
				perdep.setEstado(conn.resultado.getString("perdep_estado"));
				perdep.setFechaInicio(conn.resultado.getDate("perdep_fecha_inicio"));
				perdep.setFechaFin(conn.resultado.getDate("perdep_fecha_fin"));
				perdep.setFechaAlta(conn.resultado.getTimestamp("perdep_fecha_alta"));
				perdep.setObservacion(conn.resultado.getString("perdep_observacion"));
				perdep.setAdministrador(conn.resultado.getString("admin_login"));
				dependenciaHistorial.add(perdep);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dependenciaHistorial;
	}

	private int getIdDependencia(String codigo) throws ClassNotFoundException {
		int id = 0;
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT dep_id FROM ficha_personal.dependencias WHERE dep_codigo='" + codigo + "'");
			while (conn.resultado.next()) {
				id = conn.resultado.getInt("dep_id");
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public boolean actualizarDependenciaTransaccion(PerDep nuevaDependencia)
			throws ClassNotFoundException, SQLException {
		ConexionPostgresql conn = new ConexionPostgresql();
		conn.conexion.setAutoCommit(false);

		PreparedStatement pstmtUpdateTemporal = null, pstmtUpdate = null, pstmtInsert = null;
//		String updateTempSQL = "UPDATE ficha_personal.personales SET dependencia_id="
//				+ this.getIdDependencia(nuevaDependencia.getDependenciaCodigo()) + " WHERE pers_cedula_nro='"
//				+ nuevaDependencia.getCedula() + "'";
		String updateTableSQL = "UPDATE ficha_personal.personales_dependencias SET perdep_estado='H', perdep_fecha_fin=? WHERE personal_cedula='"
				+ nuevaDependencia.getCedula() + "' AND perdep_estado='A'";
		String insertTableSQL = "INSERT INTO ficha_personal.personales_dependencias(personal_cedula,dependencia_id,perdep_fecha_inicio,perdep_observacion,admin_login) VALUES(?,?,?,?,?)";

		try {
			// actualizacion temporal de la tabla personales hasta actualizar
			// reportes
//			pstmtUpdateTemporal = conn.conexion.prepareStatement(updateTempSQL);
//			pstmtUpdateTemporal.executeUpdate();

			// actualizacion del ultima oficina del personal que pasa de activo
			// a historico o a baja
			pstmtUpdate = conn.conexion.prepareStatement(updateTableSQL);
			FechaUtil util = new FechaUtil();
			nuevaDependencia.setFechaFin(util.sumarRestarDiasFecha(nuevaDependencia.getFechaInicio(), -1));
			java.sql.Date sqlDate0 = new java.sql.Date(nuevaDependencia.getFechaFin().getTime());
			pstmtUpdate.setDate(1, sqlDate0);
			pstmtUpdate.executeUpdate();

			// insertcion de la nueva oficina del personal
			pstmtInsert = conn.conexion.prepareStatement(insertTableSQL);
			pstmtInsert.setString(1, nuevaDependencia.getCedula());
			pstmtInsert.setInt(2, this.getIdDependencia(nuevaDependencia.getDependenciaCodigo()));
			java.sql.Date sqlDate1 = new java.sql.Date(nuevaDependencia.getFechaInicio().getTime());
			pstmtInsert.setDate(3, sqlDate1);
			pstmtInsert.setString(4, nuevaDependencia.getObservacion());
			pstmtInsert.setString(5, nuevaDependencia.getAdministrador());

			pstmtInsert.executeUpdate();
			conn.conexion.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.conexion.rollback();
			return false;
		} finally {
			if (pstmtUpdateTemporal != null) {
				pstmtUpdateTemporal.close();
			}
			if (pstmtUpdate != null) {
				pstmtUpdate.close();
			}
			if (pstmtInsert != null) {
				pstmtInsert.close();
			}
			if (conn.conexion != null) {
				conn.conexion.close();
			}
		}
	}

	@Override
	public void validarObjeto(Object objeto) {
		this.personalDependencia = (PerDep) objeto;
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
		return "INSERT INTO ficha_personal.personales_oficinas(personal_cedula,oficina_id,perdep_fecha_inicio,perdep_observacion,admin_login) VALUES(?,?,?,?,?)";
	}

	@Override
	public void executeInsertSQL(PreparedStatement pstmt) {
		try {
			pstmt.setString(1, this.personalDependencia.getCedula());
			pstmt.setInt(2, this.personalDependencia.getDependenciaId());
			pstmt.setDate(3, (Date) this.personalDependencia.getFechaInicio());
			pstmt.setString(4, this.personalDependencia.getObservacion());
			pstmt.setString(5, this.personalDependencia.getAdministrador());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateSQL() {
		return "UPDATE ficha_personal.personales_dependencias SET perdep_observacion='"
				+ this.personalDependencia.getObservacion() + "' WHERE perdep_estado='A' AND personal_cedula='"
				+ this.personalDependencia.getCedula() + "'";
	}

	@Override
	public String deleteSQL() {
		return "DELETE FROM ficha_personal.personales_oficinas WHERE perdep_id=" + personalDependencia.getId();
	}
	
	public ArrayList<PerDep> getHistorialDep(String cedula) throws ClassNotFoundException {
		ArrayList<PerDep> dependenciaHistorial = new ArrayList<PerDep>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.obtener_historial_dependencias('"+cedula+"') ORDER BY fecha_inicio");
			while (conn.resultado.next()) {
				PerDep perdep = new PerDep();
				//perdep.setCedula(conn.resultado.getString("personal_cedula"));
				perdep.setDependenciaCodigo(conn.resultado.getString("dependencia_codigo"));
				perdep.setDependenciaDescripcion(conn.resultado.getString("dependencia"));
				perdep.setFechaInicio(conn.resultado.getDate("fecha_inicio"));
				perdep.setFechaFin(conn.resultado.getDate("fecha_fin"));
//				perdep.setFechaAlta(conn.resultado.getTimestamp("perdep_fecha_alta"));
				perdep.setObservacion(conn.resultado.getString("observaciones"));
//				perdep.setAdministrador(conn.resultado.getString("admin_login"));
				dependenciaHistorial.add(perdep);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dependenciaHistorial;
	}
}
