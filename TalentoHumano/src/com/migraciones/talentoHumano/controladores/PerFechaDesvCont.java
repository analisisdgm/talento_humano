package com.migraciones.talentoHumano.controladores;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.PerFechaDesvinculacion;

public class PerFechaDesvCont extends AncestroCont {

	private PerFechaDesvinculacion personalDesvinculacion = new PerFechaDesvinculacion();

	public ArrayList<PerFechaDesvinculacion> getHistorial(String cedula) throws ClassNotFoundException {
		ArrayList<PerFechaDesvinculacion> ingresoHistorial = new ArrayList<PerFechaDesvinculacion>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.personales_fecha_ingreso WHERE personal_cedula='"
							+ cedula + "' AND pering_estado='A' ORDER BY pering_id");
			while (conn.resultado.next()) {
				PerFechaDesvinculacion perdesv = new PerFechaDesvinculacion();
				perdesv.setId(conn.resultado.getInt("perdesv_id"));
				perdesv.setCedula(conn.resultado.getString("personal_cedula"));
				perdesv.setFecha(conn.resultado.getDate("perdesv_fecha"));
				perdesv.setObservacion(conn.resultado.getString("perdesv_observacion"));
				perdesv.setFechaAlta(conn.resultado.getTimestamp("perdesv_fecha_alta"));
				perdesv.setAdministrador(conn.resultado.getString("admin_login"));
				perdesv.setIdPersonalesTipos(conn.resultado.getString("pertip_id"));
				ingresoHistorial.add(perdesv);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingresoHistorial;
	}

	public boolean darBajaTransaccion(PerFechaDesvinculacion nuevaFechaDesvinculacion)
			throws ClassNotFoundException, SQLException {
		ConexionPostgresql conn = new ConexionPostgresql();
		conn.conexion.setAutoCommit(false);

		PreparedStatement pstmtUpdateTemporal = null, pstmtUpdate = null, pstmtInsert = null;
		String updateTableSQL = "UPDATE ficha_personal.personales SET pers_estado='I' WHERE pers_cedula_nro='"
				+ nuevaFechaDesvinculacion.getCedula() + "' AND pers_estado='A'";
		String insertTableSQL = "INSERT INTO ficha_personal.personales_fecha_desvinculacion(personal_cedula,perdesv_fecha,perdesv_observacion,admin_login,pertip_id) VALUES(?,?,?,?,?)";

		try {
		// actualizacion del ultima oficina del personal que pasa de activo
			// a historico o a baja
			pstmtUpdate = conn.conexion.prepareStatement(updateTableSQL);
			pstmtUpdate.executeUpdate();

			// insercion de la nueva oficina del personal
			pstmtInsert = conn.conexion.prepareStatement(insertTableSQL);
			pstmtInsert.setString(1, nuevaFechaDesvinculacion.getCedula());
			java.sql.Date sqlDate1 = new java.sql.Date(nuevaFechaDesvinculacion.getFecha().getTime());
			pstmtInsert.setDate(2, sqlDate1);
			pstmtInsert.setString(3, nuevaFechaDesvinculacion.getObservacion());
			pstmtInsert.setString(4, nuevaFechaDesvinculacion.getAdministrador());
			pstmtInsert.setInt(5, Integer.parseInt(nuevaFechaDesvinculacion.getIdPersonalesTipos()));

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
		this.personalDesvinculacion = (PerFechaDesvinculacion) objeto;
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
			pstmt.setString(1, this.personalDesvinculacion.getCedula());
			pstmt.setDate(2, (Date) this.personalDesvinculacion.getFecha());
			pstmt.setString(3, this.personalDesvinculacion.getAdministrador());
			pstmt.setString(4, this.personalDesvinculacion.getObservacion());
			pstmt.setString(5, this.personalDesvinculacion.getIdPersonalesTipos());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateSQL() {
		return "UPDATE ficha_personal.personales_dependencias SET perdep_observacion='"
				+ this.personalDesvinculacion.getObservacion() + "' WHERE perdep_estado='A' AND personal_cedula='"
				+ this.personalDesvinculacion.getCedula() + "'";
	}

	@Override
	public String deleteSQL() {
		return "DELETE FROM ficha_personal.personales_oficinas WHERE perdep_id=" + personalDesvinculacion.getId();
	}
}
