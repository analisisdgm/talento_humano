package com.migraciones.talentoHumano.controladores;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.Evaluacion;

public class PerEvaCont extends AncestroCont {

	private Evaluacion evaluacion = new Evaluacion();

	public ArrayList<Evaluacion> getAll() throws ClassNotFoundException {
		ArrayList<Evaluacion> listaEvaluacion = new ArrayList<Evaluacion>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.personales_evaluaciones ORDER BY pereva_id");
			while (conn.resultado.next()) {
				Evaluacion eva = new Evaluacion();
				eva.setId(conn.resultado.getInt("pereva_id"));
				eva.setCedula(conn.resultado.getString("personal_cedula"));
				eva.setPeriodo(conn.resultado.getString("pereva_periodo"));
				eva.setPuntajeDesempeno(conn.resultado.getInt("pereva_puntaje_desempenho"));
				eva.setPuntajeExamen(conn.resultado.getInt("pereva_puntaje_examen"));
				eva.setPuntajeTotal(conn.resultado.getDouble("pereva_puntaje_total"));
				eva.setNota(conn.resultado.getInt("pereva_nota"));
				listaEvaluacion.add(eva);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEvaluacion;
	}

	public ArrayList<Evaluacion> getByCedula(String cedula) throws ClassNotFoundException {
		ArrayList<Evaluacion> listaEvaluacion = new ArrayList<Evaluacion>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.personales_evaluaciones WHERE personal_cedula='"
							+ cedula + "' ORDER BY pereva_id");
			while (conn.resultado.next()) {
				Evaluacion eva = new Evaluacion();
				eva.setId(conn.resultado.getInt("pereva_id"));
				eva.setCedula(conn.resultado.getString("personal_cedula"));
				eva.setPeriodo(conn.resultado.getString("pereva_periodo"));
				eva.setPuntajeDesempeno(conn.resultado.getInt("pereva_puntaje_desempenho"));
				eva.setPuntajeExamen(conn.resultado.getInt("pereva_puntaje_examen"));
				eva.setPuntajeTotal(conn.resultado.getDouble("pereva_puntaje_total"));
				eva.setNota(conn.resultado.getInt("pereva_nota"));
				listaEvaluacion.add(eva);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEvaluacion;
	}

	@Override
	public void validarObjeto(Object objeto) {
		this.evaluacion = (Evaluacion) objeto;
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
		return "INSERT INTO ficha_personal.personales_oficinas(personal_cedula,pereva_puntaje_total,per_eva_nota,pereva_periodo,pereva_puntaje_desempenho,pereva_puntaje_examen) VALUES(?,?,?,?,?,?)";
	}

	@Override
	public void executeInsertSQL(PreparedStatement pstmt) {
		try {
			pstmt.setString(1, this.evaluacion.getCedula());
			pstmt.setDouble(2, this.evaluacion.getPuntajeTotal());
			pstmt.setInt(3, this.evaluacion.getNota());
			pstmt.setString(3, this.evaluacion.getPeriodo());
			pstmt.setInt(3, this.evaluacion.getPuntajeDesempeno());
			pstmt.setInt(3, this.evaluacion.getPuntajeExamen());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateSQL() {
		return "";
	}

	@Override
	public String deleteSQL() {
		return "DELETE FROM ficha_personal.personales_evaluaciones WHERE pereva_id=" + evaluacion.getId();
	}
}
