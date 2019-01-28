package com.migraciones.talentoHumano.controladores;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.PerTur;
import com.migraciones.talentoHumano.utilities.FechaUtil;

public class PerTurCont extends AncestroCont {

	private PerTur horario = new PerTur();

	public ArrayList<PerTur> getHistorial(String cedula) throws ClassNotFoundException {
		ArrayList<PerTur> horarioHistorial = new ArrayList<PerTur>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM control_asistencia.personales_turnos WHERE personal_cedula='" + cedula
							+ "' AND horario_estado <> 'B' ORDER BY id");
			while (conn.resultado.next()) {
				PerTur horario = new PerTur();
				horario.setId(conn.resultado.getInt("id"));
				horario.setPersonalId(conn.resultado.getInt("personal_id"));
				horario.setCedula(conn.resultado.getString("personal_cedula"));
				horario.setTurnoId(conn.resultado.getInt("turno_id"));
				horario.setFechaAlta(conn.resultado.getTimestamp("pers_fecha_alta"));
				horario.setFechaInicio(conn.resultado.getDate("pers_fecha_inicio"));
				horario.setFechaFin(conn.resultado.getDate("pers_fecha_fin"));
				horarioHistorial.add(horario);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horarioHistorial;
	}

	private int getIdPersonal(String cedula) throws ClassNotFoundException {
		int id = 0;
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT personal_id FROM control_asistencia.personales_turnos WHERE personal_cedula='"
							+ cedula + "'");
			while (conn.resultado.next()) {
				id = conn.resultado.getInt("personal_id");
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public boolean actualizarTurnoTransaccion(PerTur nuevoTurno) throws ClassNotFoundException, SQLException {
		ConexionPostgresql conn = new ConexionPostgresql();
		conn.conexion.setAutoCommit(false);

		PreparedStatement pstmtUpdate = null, pstmtInsert = null;
		String updateTableSQL = "UPDATE control_asistencia.personales_turnos SET horario_estado='H', pers_fecha_fin=? WHERE personal_cedula='"
				+ nuevoTurno.getCedula() + "' AND horario_estado='A'";
		String insertTableSQL = "INSERT INTO control_asistencia.personales_turnos(personal_id,personal_cedula,turno_id,pers_fecha_inicio,pers_observacion,admin_login) VALUES(?,?,?,?,?,?)";

		try {
			// actualizacion del ultimo horario del personal que pasa de activo
			// o a historico o a baja
			pstmtUpdate = conn.conexion.prepareStatement(updateTableSQL);
			FechaUtil util = new FechaUtil();
			nuevoTurno.setFechaFin(util.sumarRestarDiasFecha(nuevoTurno.getFechaInicio(), -1));
			java.sql.Date sqlDate0 = new java.sql.Date(nuevoTurno.getFechaFin().getTime());
			pstmtUpdate.setDate(1, sqlDate0);
			pstmtUpdate.executeUpdate();

			// insertcion del nuevo horario del personal
			pstmtInsert = conn.conexion.prepareStatement(insertTableSQL);
			pstmtInsert.setInt(1, this.getIdPersonal(nuevoTurno.getCedula()));
			pstmtInsert.setString(2, nuevoTurno.getCedula());
			pstmtInsert.setInt(3, nuevoTurno.getTurnoId());
			java.sql.Date sqlDate1 = new java.sql.Date(nuevoTurno.getFechaInicio().getTime());
			pstmtInsert.setDate(4, sqlDate1);
			pstmtInsert.setString(5, nuevoTurno.getObservacion());
			pstmtInsert.setString(6, nuevoTurno.getAdministrador());

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
		this.horario = (PerTur) objeto;
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
		return "INSERT INTO control_asistencia.personales_turnos(personal_cedula,turno_id,pers_fecha_inicio,pers_fecha_fin,pers_observacion,admin_login) VALUES(?,?,?,?,?,?)";
	}

	@Override
	public void executeInsertSQL(PreparedStatement pstmt) {
		try {
			pstmt.setString(1, this.horario.getCedula());
			pstmt.setInt(2, this.horario.getTurnoId());
			pstmt.setDate(3, (Date) this.horario.getFechaInicio());
			pstmt.setDate(4, (Date) this.horario.getFechaFin());
			pstmt.setString(5, this.horario.getObservacion());
			pstmt.setString(6, this.horario.getAdministrador());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateSQL() {
		return "UPDATE control_asistencia.personales_turnos SET pers_observacion='" + this.horario.getObservacion()
				+ "' WHERE personal_cedula='" + this.horario.getCedula() + "' AND horario_estado='A'";
	}

	@Override
	public String deleteSQL() {
		return "DELETE FROM control_asistencia.personales_turnos WHERE personal_id=" + horario.getPersonalId();
	}

	public ArrayList<PerTur> getHistorialTurno(String cedula) throws ClassNotFoundException {
		ArrayList<PerTur> horarioHistorial = new ArrayList<PerTur>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM control_asistencia.obtener_historial_turnos('"+cedula+"') ORDER BY fecha_inicio");
			while (conn.resultado.next()) {
				PerTur turno = new PerTur();
				turno.setTurnoId(conn.resultado.getInt("turno_id"));
				turno.setDescripcionTurno(conn.resultado.getString("turno"));
				turno.setObservacion(conn.resultado.getString("observaciones"));
				turno.setFechaInicio(conn.resultado.getDate("fecha_inicio"));
				turno.setFechaFin(conn.resultado.getDate("fecha_fin"));
				horarioHistorial.add(turno);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horarioHistorial;
	}
}
