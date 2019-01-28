package com.migraciones.talentoHumano.controladores;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.PerTipos;


public class PerTipoCont extends AncestroCont {

	private PerTipos personalTipo = new PerTipos();

	public ArrayList<PerTipos> getHistorial(String cedula) throws ClassNotFoundException {
		ArrayList<PerTipos> dependenciaHistorial = new ArrayList<PerTipos>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.personales_tipos WHERE personal_cedula='"
							+ cedula + "' AND pertip_estado='A' ORDER BY pertip_id");
			while (conn.resultado.next()) {
				PerTipos pertip = new PerTipos();
				pertip.setId(conn.resultado.getInt("perdep_id"));
				pertip.setCedula(conn.resultado.getString("personal_cedula"));
				pertip.setEstado(conn.resultado.getString("perdep_estado"));
				pertip.setFechaInicio(conn.resultado.getDate("perdep_fecha_inicio"));
				pertip.setFechaFin(conn.resultado.getDate("perdep_fecha_fin"));
				pertip.setFechaAlta(conn.resultado.getTimestamp("perdep_fecha_alta"));
				pertip.setObservacion(conn.resultado.getString("perdep_observacion"));
				pertip.setAdministrador(conn.resultado.getString("admin_login"));
				dependenciaHistorial.add(pertip);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dependenciaHistorial;
	}

//	private int getIdTipoPersonal(String codigo) throws ClassNotFoundException {
//		int id = 0;
//		try {
//			ConexionPostgresql conn = new ConexionPostgresql();
//			conn.sentencia = (Statement) conn.conexion.createStatement();
//			conn.resultado = conn.sentencia
//					.executeQuery("SELECT pertip_id FROM ficha_personal.tipos_personal WHERE tipoper_id='" + codigo + "'");
//			while (conn.resultado.next()) {
//				id = conn.resultado.getInt("tipoper_id");
//			}
//			conn.conexion.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return id;
//	}

//	public boolean actualizarDependenciaTransaccion(PerTipos nuevaDependencia)
//			throws ClassNotFoundException, SQLException {
//		ConexionPostgresql conn = new ConexionPostgresql();
//		conn.conexion.setAutoCommit(false);
//
//		PreparedStatement pstmtUpdateTemporal = null, pstmtUpdate = null, pstmtInsert = null;
//		String updateTempSQL = "UPDATE ficha_personal.personales SET dependencia_id="
//				+ this.getIdTipoPersonal(nuevaDependencia.getTipoCodigo()) + " WHERE pers_cedula_nro='"
//				+ nuevaDependencia.getCedula() + "'";
//		String updateTableSQL = "UPDATE ficha_personal.personales_dependencias SET perdep_estado='H', perdep_fecha_fin=? WHERE personal_cedula='"
//				+ nuevaDependencia.getCedula() + "' AND perdep_estado='A'";
//		String insertTableSQL = "INSERT INTO ficha_personal.personales_dependencias(personal_cedula,dependencia_id,perdep_fecha_inicio,perdep_observacion,admin_login) VALUES(?,?,?,?,?)";
//
//		try {
//			// actualizacion temporal de la tabla personales hasta actualizar
//			// reportes
//			pstmtUpdateTemporal = conn.conexion.prepareStatement(updateTempSQL);
//			pstmtUpdateTemporal.executeUpdate();
//
//			// actualizacion del ultima oficina del personal que pasa de activo
//			// a historico o a baja
//			pstmtUpdate = conn.conexion.prepareStatement(updateTableSQL);
//			FechaUtil util = new FechaUtil();
//			nuevaDependencia.setFechaFin(util.sumarRestarDiasFecha(nuevaDependencia.getFechaInicio(), -1));
//			java.sql.Date sqlDate0 = new java.sql.Date(nuevaDependencia.getFechaFin().getTime());
//			pstmtUpdate.setDate(1, sqlDate0);
//			pstmtUpdate.executeUpdate();
//
//			// insertcion de la nueva oficina del personal
//			pstmtInsert = conn.conexion.prepareStatement(insertTableSQL);
//			pstmtInsert.setString(1, nuevaDependencia.getCedula());
//			pstmtInsert.setInt(2, this.getIdDependencia(nuevaDependencia.getDependenciaCodigo()));
//			java.sql.Date sqlDate1 = new java.sql.Date(nuevaDependencia.getFechaInicio().getTime());
//			pstmtInsert.setDate(3, sqlDate1);
//			pstmtInsert.setString(4, nuevaDependencia.getObservacion());
//			pstmtInsert.setString(5, nuevaDependencia.getAdministrador());
//
//			pstmtInsert.executeUpdate();
//			conn.conexion.commit();
//			return true;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			conn.conexion.rollback();
//			return false;
//		} finally {
//			if (pstmtUpdateTemporal != null) {
//				pstmtUpdateTemporal.close();
//			}
//			if (pstmtUpdate != null) {
//				pstmtUpdate.close();
//			}
//			if (pstmtInsert != null) {
//				pstmtInsert.close();
//			}
//			if (conn.conexion != null) {
//				conn.conexion.close();
//			}
//		}
//	}

	@Override
	public void validarObjeto(Object objeto) {
		this.personalTipo = (PerTipos) objeto;
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
			pstmt.setString(1, this.personalTipo.getCedula());
//			pstmt.setInt(2, this.personalTipo.getDependenciaId());
			pstmt.setDate(3, (Date) this.personalTipo.getFechaInicio());
			pstmt.setString(4, this.personalTipo.getObservacion());
			pstmt.setString(5, this.personalTipo.getAdministrador());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateSQL() {
		return "UPDATE ficha_personal.personales_dependencias SET perdep_observacion='"
				+ this.personalTipo.getObservacion() + "' WHERE perdep_estado='A' AND personal_cedula='"
				+ this.personalTipo.getCedula() + "'";
	}

	@Override
	public String deleteSQL() {
		return "DELETE FROM ficha_personal.personales_oficinas WHERE perdep_id=" + personalTipo.getId();
	}
	
	public ArrayList<PerTipos> getHistorialTipo(String cedula) throws ClassNotFoundException {
		ArrayList<PerTipos> dependenciaHistorial = new ArrayList<PerTipos>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.obtener_historial_tipos('"+cedula+"') ORDER BY fecha_inicio");
			while (conn.resultado.next()) {
				PerTipos perTip = new PerTipos();
				perTip.setTipoCodigo(conn.resultado.getInt("tipo_codigo"));
				perTip.setTipoDescripcion(conn.resultado.getString("tipo"));
				perTip.setFechaInicio(conn.resultado.getDate("fecha_inicio"));
				perTip.setFechaFin(conn.resultado.getDate("fecha_fin"));
//				perdep.setFechaAlta(conn.resultado.getTimestamp("perdep_fecha_alta"));
				perTip.setObservacion(conn.resultado.getString("observaciones"));
//				perdep.setAdministrador(conn.resultado.getString("admin_login"));
				dependenciaHistorial.add(perTip);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dependenciaHistorial;
	}
}
