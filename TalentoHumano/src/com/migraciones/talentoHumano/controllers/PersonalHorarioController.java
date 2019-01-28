package com.migraciones.talentoHumano.controllers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.models.HorarioPersonal;
import com.migraciones.talentoHumano.utilities.FechaUtil;

public class PersonalHorarioController extends AncestroController {

	private HorarioPersonal horario = new HorarioPersonal();

	public ArrayList<HorarioPersonal> getHistorial(String cedula) throws ClassNotFoundException {
		ArrayList<HorarioPersonal> horarioHistorial = new ArrayList<HorarioPersonal>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM control_asistencia.personales_turnos WHERE personal_cedula='" + cedula
							+ "' AND horario_estado <> 'B' ORDER BY id");
			while (conn.resultado.next()) {
				HorarioPersonal horario = new HorarioPersonal();
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

	public HorarioPersonal getHorarioActivo(String cedula) throws ClassNotFoundException {
		HorarioPersonal horario = new HorarioPersonal();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM control_asistencia.personal_horarios_vista WHERE cedula='" + cedula
							+ "' AND horario_estado='A'");
			while (conn.resultado.next()) {

				horario.setId(conn.resultado.getInt("horario_id"));
				horario.setPersonalId(conn.resultado.getInt("personal_id"));
				horario.setHorarioObservacion(conn.resultado.getString("horario_observacion"));
				horario.setFechaInicio(conn.resultado.getDate("fecha_inicio"));
				horario.setFechaFin(conn.resultado.getDate("fecha_fin"));
				horario.setFechaAlta(conn.resultado.getTimestamp("fecha_alta"));
				horario.setAdministradorAlta(conn.resultado.getString("admin_horario_alta"));
				horario.setPersonalEstado(conn.resultado.getString("personal_estado"));

				horario.setCedula(conn.resultado.getString("cedula"));
				horario.setNombres(conn.resultado.getString("nombres"));
				horario.setApellidos(conn.resultado.getString("apellidos"));

				horario.setTurnoId(conn.resultado.getInt("turno_id"));
				horario.setTurnoEntrada(conn.resultado.getTime("turno_entrada"));
				horario.setTurnoSalida(conn.resultado.getTime("turno_salida"));

			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horario;
	}

	public boolean actualizarHorarioTransaccion(HorarioPersonal viejoHorario, HorarioPersonal nuevoHorario)
			throws ClassNotFoundException, SQLException {
		ConexionPostgresql conn = new ConexionPostgresql();
		conn.conexion.setAutoCommit(false);

		PreparedStatement pstmtUpdate = null, pstmtInsert = null;
		String updateTableSQL = "UPDATE control_asistencia.personales_turnos SET horario_estado=?, pers_fecha_fin=? WHERE id=?";
		String insertTableSQL = "INSERT INTO control_asistencia.personales_turnos(personal_id,personal_cedula,turno_id,pers_fecha_inicio,pers_observacion,admin_login) VALUES(?,?,?,?,?,?)";

		try {
			// actualizacion del ultimo horario del personal que pasa de activo
			// o a historico o a baja
			pstmtUpdate = conn.conexion.prepareStatement(updateTableSQL);
			pstmtUpdate.setString(1, "H");
			// falta restar un dia
			FechaUtil util = new FechaUtil();
			viejoHorario.setFechaFin(util.sumarRestarDiasFecha(nuevoHorario.getFechaInicio(), -1));
			java.sql.Date sqlDate0 = new java.sql.Date(viejoHorario.getFechaFin().getTime());
			pstmtUpdate.setDate(2, sqlDate0);
			pstmtUpdate.setInt(3, viejoHorario.getId());
			pstmtUpdate.executeUpdate();

			// insertcion del nuevo horario del personal
			pstmtInsert = conn.conexion.prepareStatement(insertTableSQL);
			pstmtInsert.setInt(1, nuevoHorario.getPersonalId());
			pstmtInsert.setString(2, nuevoHorario.getCedula());
			pstmtInsert.setInt(3, nuevoHorario.getTurnoId());
			java.sql.Date sqlDate1 = new java.sql.Date(nuevoHorario.getFechaInicio().getTime());
			pstmtInsert.setDate(4, sqlDate1);
			pstmtInsert.setString(5, nuevoHorario.getHorarioObservacion());
			pstmtInsert.setString(6, nuevoHorario.getAdministradorAlta());

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
		this.horario = (HorarioPersonal) objeto;
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
			pstmt.setString(5, this.horario.getHorarioObservacion());
			pstmt.setString(6, this.horario.getAdministradorAlta());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateSQL() {
		return "UPDATE control_asistencia.personales_turnos SET horario_estado='B' WHERE personal_id="
				+ horario.getPersonalId();
	}

	@Override
	public String deleteSQL() {
		return "DELETE FROM control_asistencia.personales_turnos WHERE personal_id=" + horario.getPersonalId();
	}
}
