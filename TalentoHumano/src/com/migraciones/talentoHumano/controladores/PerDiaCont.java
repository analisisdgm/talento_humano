package com.migraciones.talentoHumano.controladores;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.PerCat;
import com.migraciones.talentoHumano.modelos.PerDias;
import com.migraciones.talentoHumano.utilities.FechaUtil;

public class PerDiaCont extends AncestroCont {

	private PerDias personalDias = new PerDias();

	public ArrayList<PerCat> getHistorial(String cedula) throws ClassNotFoundException {
		ArrayList<PerCat> cargosHistorial = new ArrayList<PerCat>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.categorias WHERE percat_estado='A' ORDER BY percat_id");
			while (conn.resultado.next()) {
				PerCat percat = new PerCat();
				percat.setId(conn.resultado.getInt("percat_id"));
				percat.setCedula(conn.resultado.getString("personal_cedula"));
				percat.setId(conn.resultado.getInt("categoria_id"));
				percat.setEstado(conn.resultado.getString("percat_estado"));
				percat.setFechaInicio(conn.resultado.getDate("percat_fecha_inicio"));
				percat.setFechaFin(conn.resultado.getDate("percat_fecha_fin"));
				percat.setFechaAlta(conn.resultado.getTimestamp("percat_fecha_alta"));
				percat.setObservacion(conn.resultado.getString("percat_observacion"));
				percat.setAdministrador(conn.resultado.getString("admin_login"));
				cargosHistorial.add(percat);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cargosHistorial;
	}

	public boolean actualizarPersonalDiaTransaccion(PerDias nuevoDia) throws ClassNotFoundException, SQLException {
		ConexionPostgresql conn = new ConexionPostgresql();
		conn.conexion.setAutoCommit(false);

		PreparedStatement pstmtUpdate = null, pstmtInsert = null;
		String updateTableSQL = "UPDATE control_asistencia.personales_dias_trabajo SET perdi_estado='H', perdi_fecha_fin=? WHERE personal_cedula='"
				+ nuevoDia.getCedula() + "' AND perdi_estado='A'";
		String insertTableSQL = "INSERT INTO control_asistencia.personales_dias_trabajo(personal_cedula,perdi_fecha_ini,perdi_observacion,perdi_dias,admin_login) VALUES(?,?,?,?,?)";

		try {
			// actualizacion del ultima categoria del personal que pasa de
			// activo
			// a historico o a baja
			pstmtUpdate = conn.conexion.prepareStatement(updateTableSQL);
			FechaUtil util = new FechaUtil();
			nuevoDia.setFechaFin(util.sumarRestarDiasFecha(nuevoDia.getFechaInicio(), -1));
			java.sql.Date sqlDate0 = new java.sql.Date(nuevoDia.getFechaFin().getTime());
			pstmtUpdate.setDate(1, sqlDate0);
			pstmtUpdate.executeUpdate();

			// insertcion de la nueva categoria del personal
			pstmtInsert = conn.conexion.prepareStatement(insertTableSQL);
			pstmtInsert.setString(1, nuevoDia.getCedula());
			java.sql.Date sqlDate1 = new java.sql.Date(nuevoDia.getFechaInicio().getTime());
			pstmtInsert.setDate(2, sqlDate1);
			pstmtInsert.setString(3, nuevoDia.getObservacion());
			pstmtInsert.setString(4, nuevoDia.getDescripciondia());
			pstmtInsert.setString(5, nuevoDia.getAdministrador());

			pstmtInsert.executeUpdate();
			conn.conexion.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.conexion.rollback();
			return false;
		} finally {

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
		this.personalDias = (PerDias) objeto;
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
		return "INSERT INTO ficha_personal.personales_categorias(personal_cedula,cartegoria_id,percat_fecha_inicio,percat_observacion,admin_login) VALUES(?,?,?,?,?)";
	}

	@Override
	public void executeInsertSQL(PreparedStatement pstmt) {
		try {
			pstmt.setString(1, this.personalDias.getCedula());
			pstmt.setInt(2, this.personalDias.getId());
			pstmt.setDate(3, (Date) this.personalDias.getFechaInicio());
			pstmt.setString(4, this.personalDias.getObservacion());
			pstmt.setString(5, this.personalDias.getAdministrador());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateSQL() {
		return "UPDATE control_asistencia.personales_dias_trabajo SET perdi_observacion='"
				+ this.personalDias.getObservacion() + "' WHERE perdi_estado='A' AND personal_cedula='"
				+ this.personalDias.getCedula() + "'";
	}

	@Override
	public String deleteSQL() {
		return "DELETE FROM control_aistencia.personales_dias_trabajo WHERE perdi_id=" + personalDias.getId();
	}

	public ArrayList<PerDias> getHistorialDias(String cedula) throws ClassNotFoundException {
		ArrayList<PerDias> horarioHistorial = new ArrayList<PerDias>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM control_asistencia.obtener_historial_dias_trabajo('" + cedula
							+ "') ORDER BY fecha_inicio");
			while (conn.resultado.next()) {
				PerDias dias = new PerDias();
				dias.setDiaId(conn.resultado.getInt("dia_id"));
				dias.setDescripciondia(conn.resultado.getString("dia_descripcion"));
				dias.setObservacion(conn.resultado.getString("observaciones"));
				dias.setFechaInicio(conn.resultado.getDate("fecha_inicio"));
				dias.setFechaFin(conn.resultado.getDate("fecha_fin"));
				horarioHistorial.add(dias);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horarioHistorial;
	}
}
