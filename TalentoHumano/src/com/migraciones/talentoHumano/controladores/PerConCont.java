package com.migraciones.talentoHumano.controladores;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.PerCon;
import com.migraciones.talentoHumano.utilities.FechaUtil;

public class PerConCont extends AncestroCont {

	private PerCon personalCondicion = new PerCon();

	public ArrayList<PerCon> getHistorial(String cedula) throws ClassNotFoundException {
		ArrayList<PerCon> condicionHistorial = new ArrayList<PerCon>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.personales_tipos WHERE personal_cedula='" + cedula
							+ "' AND pertip_estado='A' ORDER BY pertip_id");
			while (conn.resultado.next()) {
				PerCon perCon = new PerCon();
				perCon.setId(conn.resultado.getInt("pertip_id"));
				perCon.setCedula(conn.resultado.getString("personal_cedula"));
				perCon.setCondicionId(conn.resultado.getInt("tipo_personal_id"));
				perCon.setEstado(conn.resultado.getString("pertip_estado"));
				perCon.setFechaInicio(conn.resultado.getDate("pertip_fecha_inicio"));
				perCon.setFechaFin(conn.resultado.getDate("pertip_fecha_fin"));
				perCon.setFechaAlta(conn.resultado.getTimestamp("pertip_fecha_alta"));
				perCon.setObservacion(conn.resultado.getString("pertip_observacion"));
				perCon.setAdministrador(conn.resultado.getString("admin_login"));
				condicionHistorial.add(perCon);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return condicionHistorial;
	}

	public boolean actualizarCondicionTransaccion(PerCon nuevaCondicion) throws ClassNotFoundException, SQLException {
		ConexionPostgresql conn = new ConexionPostgresql();
		conn.conexion.setAutoCommit(false);

		PreparedStatement pstmtUpdateTemporal = null, pstmtUpdate = null, pstmtInsert = null;
		String updateTempSQL = "UPDATE ficha_personal.personales SET tipo_personal_id="
				+ nuevaCondicion.getCondicionId() + " WHERE pers_cedula_nro='" + nuevaCondicion.getCedula()+"'";
		
		String updateTableSQL = "UPDATE ficha_personal.personales_tipos SET pertip_estado='H', pertip_fecha_fin=? WHERE personal_cedula='"
				+ nuevaCondicion.getCedula() + "' AND pertip_estado='A'";
		String insertTableSQL = "INSERT INTO ficha_personal.personales_tipos(personal_cedula,tipo_personal_id,pertip_fecha_inicio,pertip_observacion,admin_login) VALUES(?,?,?,?,?)";

		try {
			// actualizacion temporal de la tabla personales hasta actualizar
			// reportes
			pstmtUpdateTemporal = conn.conexion.prepareStatement(updateTempSQL);
			pstmtUpdateTemporal.executeUpdate();

			// actualizacion del ultima oficina del personal que pasa de activo
			// a historico o a baja
			pstmtUpdate = conn.conexion.prepareStatement(updateTableSQL);
			FechaUtil util = new FechaUtil();
			nuevaCondicion.setFechaFin(util.sumarRestarDiasFecha(nuevaCondicion.getFechaInicio(), -1));
			java.sql.Date sqlDate0 = new java.sql.Date(nuevaCondicion.getFechaFin().getTime());
			pstmtUpdate.setDate(1, sqlDate0);
			pstmtUpdate.executeUpdate();

			// insertcion de la nueva oficina del personal
			pstmtInsert = conn.conexion.prepareStatement(insertTableSQL);
			pstmtInsert.setString(1, nuevaCondicion.getCedula());
			pstmtInsert.setInt(2, nuevaCondicion.getCondicionId());
			java.sql.Date sqlDate1 = new java.sql.Date(nuevaCondicion.getFechaInicio().getTime());
			pstmtInsert.setDate(3, sqlDate1);
			pstmtInsert.setString(4, nuevaCondicion.getObservacion());
			pstmtInsert.setString(5, nuevaCondicion.getAdministrador());

			pstmtInsert.executeUpdate();
			conn.conexion.commit();
			return true;
		} catch (SQLException e) {
			// e.printStackTrace();
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
		this.personalCondicion = (PerCon) objeto;
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
			pstmt.setString(1, this.personalCondicion.getCedula());
			pstmt.setInt(2, this.personalCondicion.getCondicionId());
			pstmt.setDate(3, (Date) this.personalCondicion.getFechaInicio());
			pstmt.setString(4, this.personalCondicion.getObservacion());
			pstmt.setString(5, this.personalCondicion.getAdministrador());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateSQL() {
		return "UPDATE ficha_personal.personales_tipos SET pertip_observacion='"
				+ this.personalCondicion.getObservacion() + "' WHERE personal_cedula='"
				+ this.personalCondicion.getCedula() + "' AND pertip_estado='A'";
	}

	@Override
	public String deleteSQL() {
		return "DELETE FROM ficha_personal.personales_tipos WHERE pertip_id=" + personalCondicion.getId();
	}
}
