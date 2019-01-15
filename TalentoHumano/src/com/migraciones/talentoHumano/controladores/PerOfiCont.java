package com.migraciones.talentoHumano.controladores;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.PerOfi;
import com.migraciones.talentoHumano.utilities.FechaUtil;

public class PerOfiCont extends AncestroCont {

	private PerOfi personalOficina = new PerOfi();

	public ArrayList<PerOfi> getHistorial(String cedula) throws ClassNotFoundException {
		ArrayList<PerOfi> oficinasHistorial = new ArrayList<PerOfi>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery(
					"SELECT * FROM ficha_personal.personales_oficinas WHERE perofi_estado='A' ORDER BY perofi_id");
			while (conn.resultado.next()) {
				PerOfi perofi = new PerOfi();
				perofi.setId(conn.resultado.getInt("perofi_id"));
				perofi.setCedula(conn.resultado.getString("personal_cedula"));
				perofi.setOficinaId(conn.resultado.getInt("oficina_id"));
				perofi.setEstado(conn.resultado.getString("perofi_estado"));
				perofi.setFechaInicio(conn.resultado.getDate("perofi_fecha_inicio"));
				perofi.setFechaFin(conn.resultado.getDate("perofi_fecha_fin"));
				perofi.setFechaAlta(conn.resultado.getTimestamp("perofi_fecha_alta"));
				perofi.setObservacion(conn.resultado.getString("perofi_observacion"));
				perofi.setAdministrador(conn.resultado.getString("admin_login"));
				oficinasHistorial.add(perofi);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return oficinasHistorial;
	}

	private int getIdOficina(String codigo) throws ClassNotFoundException {
		int id = 0;
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT ofi_id FROM ficha_personal.oficinas WHERE ofi_codigo='" + codigo + "'");
			while (conn.resultado.next()) {
				id = conn.resultado.getInt("ofi_id");
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public boolean actualizarOficinaTransaccion(PerOfi nuevaOficina) throws ClassNotFoundException, SQLException {
		ConexionPostgresql conn = new ConexionPostgresql();
		conn.conexion.setAutoCommit(false);

		PreparedStatement pstmUpdateTemporal = null, pstmtUpdate = null, pstmtInsert = null;
		String updateTempSQL = "UPDATE ficha_personal.personales SET oficina_id="
				+ this.getIdOficina(nuevaOficina.getOficinaCodigo()) + " WHERE pers_cedula_nro='"
				+ nuevaOficina.getCedula()+"'";
		String updateTableSQL = "UPDATE ficha_personal.personales_oficinas SET perofi_estado='H', perofi_fecha_fin=? WHERE personal_cedula='"
				+ nuevaOficina.getCedula() + "' AND perofi_estado='A'";
		String insertTableSQL = "INSERT INTO ficha_personal.personales_oficinas(personal_cedula,oficina_id,perofi_fecha_inicio,perofi_observacion,admin_login) VALUES(?,?,?,?,?)";

		try {
			// temporal hasta actualizacion de reportes
			pstmUpdateTemporal = conn.conexion.prepareStatement(updateTempSQL);
			pstmUpdateTemporal.executeUpdate();

			// actualizacion del ultima oficina del personal que pasa de activo
			// a historico o a baja
			pstmtUpdate = conn.conexion.prepareStatement(updateTableSQL);
			FechaUtil util = new FechaUtil();
			nuevaOficina.setFechaFin(util.sumarRestarDiasFecha(nuevaOficina.getFechaInicio(), -1));
			java.sql.Date sqlDate0 = new java.sql.Date(nuevaOficina.getFechaFin().getTime());
			pstmtUpdate.setDate(1, sqlDate0);
			pstmtUpdate.executeUpdate();

			// insertcion de la nueva oficina del personal
			pstmtInsert = conn.conexion.prepareStatement(insertTableSQL);
			pstmtInsert.setString(1, nuevaOficina.getCedula());
			java.sql.Date sqlDate1 = new java.sql.Date(nuevaOficina.getFechaInicio().getTime());
			pstmtInsert.setInt(2, this.getIdOficina(nuevaOficina.getOficinaCodigo()));
			pstmtInsert.setDate(3, sqlDate1);
			pstmtInsert.setString(4, nuevaOficina.getObservacion());
			pstmtInsert.setString(5, nuevaOficina.getAdministrador());

			pstmtInsert.executeUpdate();
			conn.conexion.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.conexion.rollback();
			return false;
		} finally {
			if (pstmUpdateTemporal != null) {
				pstmUpdateTemporal.close();
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
		this.personalOficina = (PerOfi) objeto;
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
		return "INSERT INTO ficha_personal.personales_oficinas(personal_cedula,oficina_id,perofi_fecha_inicio,perofi_observacion,admin_login) VALUES(?,?,?,?,?)";
	}

	@Override
	public void executeInsertSQL(PreparedStatement pstmt) {
		try {
			pstmt.setString(1, this.personalOficina.getCedula());
			pstmt.setInt(2, this.personalOficina.getOficinaId());
			pstmt.setDate(3, (Date) this.personalOficina.getFechaInicio());
			pstmt.setString(4, this.personalOficina.getObservacion());
			pstmt.setString(5, this.personalOficina.getAdministrador());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateSQL() {
		return "UPDATE ficha_personal.personales_oficinas SET perofi_observacion='"
				+ this.personalOficina.getObservacion() + "' WHERE perofi_estado='A' AND personal_cedula='"
				+ this.personalOficina.getCedula() + "'";
	}

	@Override
	public String deleteSQL() {
		return "DELETE FROM ficha_personal.personales_oficinas WHERE perofi_id=" + personalOficina.getId();
	}
}
