package com.migraciones.talentoHumano.controllers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.Turno;

public class TurnoController extends AncestroController {

	@SuppressWarnings("unused")
	private Turno turno = new Turno();

	public ArrayList<Turno> getAll() throws ClassNotFoundException {
		ArrayList<Turno> listaTurnos = new ArrayList<Turno>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM control_asistencia.turnos ORDER BY turno_entrada,turno_salida");
			while (conn.resultado.next()) {
				Turno turno = new Turno();
				turno.setId(conn.resultado.getInt("turno_id"));
				turno.setDescripcion(conn.resultado.getString("turno_descripcion"));
				turno.setEntrada(conn.resultado.getTime("turno_entrada"));
				turno.setSalida(conn.resultado.getTime("turno_salida"));
				turno.setTiempoTrabajo(conn.resultado.getDouble("turno_tiempo_trabajo"));
				listaTurnos.add(turno);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaTurnos;
	}

	public Turno getById(int id) throws ClassNotFoundException {
		Turno turno = new Turno();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM control_asistencia.turnos WHERE turno_id=" + id);
			while (conn.resultado.next()) {
				turno.setId(conn.resultado.getInt("turno_id"));
				turno.setDescripcion(conn.resultado.getString("turno_descripcion"));
				turno.setEntrada(conn.resultado.getTime("turno_entrada"));
				turno.setSalida(conn.resultado.getTime("turno_salida"));
				turno.setTiempoTrabajo(conn.resultado.getDouble("turno_tiempo_trabajo"));
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return turno;
	}

	public int getMayorId() throws ClassNotFoundException {
		int mayor = 0;
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery("SELECT MAX(turno_id) FROM control_asistencia.turnos");
			while (conn.resultado.next()) {
				mayor = conn.resultado.getInt("max");
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mayor;
	}

	@Override
	public void validarObjeto(Object objeto) {
		this.turno = (Turno) objeto;
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
		return "";
	}

	@Override
	public String deleteSQL() {
		return "";
	}
}
