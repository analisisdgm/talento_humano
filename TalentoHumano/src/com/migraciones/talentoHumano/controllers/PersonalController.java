package com.migraciones.talentoHumano.controllers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.models.Personal;

public class PersonalController extends AncestroController {

	private Personal personal = new Personal();

	public ArrayList<Personal> getAll() throws ClassNotFoundException {
		ArrayList<Personal> listaPersonales = new ArrayList<Personal>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery(
					"SELECT cedula,nombres,apellidos FROM ficha_personal.personales_view WHERE personal_estado='A' ORDER BY cedula");
			while (conn.resultado.next()) {
				Personal personal = new Personal();
				personal.setCedula(conn.resultado.getString("cedula"));
				personal.setNombres(conn.resultado.getString("nombres"));
				personal.setApellidos(conn.resultado.getString("apellidos"));
				listaPersonales.add(personal);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPersonales;
	}

	public ArrayList<Personal> getByOficina(int oficina_id) throws ClassNotFoundException {
		ArrayList<Personal> listaPersonales = new ArrayList<Personal>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery(
					"SELECT pers_cedula_nro,pers_nombre,pers_apellido FROM ficha_personal.personales WHERE pers_estado='A' AND oficina_id="
							+ oficina_id + " ORDER BY pers_apellido");
			while (conn.resultado.next()) {
				Personal personal = new Personal();
				personal.setCedula(conn.resultado.getString("pers_cedula_nro"));
				personal.setNombres(conn.resultado.getString("pers_nombre"));
				personal.setApellidos(conn.resultado.getString("pers_apellido"));
				listaPersonales.add(personal);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPersonales;
	}

	/*
	 * retorna los personales activos
	 **/
	public Personal getById(String cedula) throws ClassNotFoundException {
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT cedula,nombres,apellidos FROM ficha_personal.personales_view WHERE cedula='"
							+ cedula + "' AND personal_estado='A' ");
			while (conn.resultado.next()) {
				personal.setCedula(conn.resultado.getString("cedula"));
				personal.setNombres(conn.resultado.getString("nombres"));
				personal.setApellidos(conn.resultado.getString("apellidos"));
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personal;
	}

	/*
	 * retorna los personales con horarios activos e inactivos
	 **/
	public Personal getByIdCompleto(String cedula) throws ClassNotFoundException {
		Personal personal = new Personal();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery(
					"SELECT * FROM ficha_personal.personales_completo_view WHERE cedula='" + cedula + "'");
			while (conn.resultado.next()) {
				personal.setCedula(conn.resultado.getString("cedula"));
				personal.setNombres(conn.resultado.getString("nombres"));
				personal.setApellidos(conn.resultado.getString("apellidos"));
				personal.setPersonalEstado(conn.resultado.getString("estado"));
				personal.setTipoPersonalId(conn.resultado.getInt("tipo_personal_id"));
				personal.setTipoPersonal(conn.resultado.getString("tipoper_descripcion"));
				personal.setCodigoDependencia(conn.resultado.getString("codigo_dependencia"));
				personal.setDependencia(conn.resultado.getString("dependencia"));
				personal.setCodigoOficina(conn.resultado.getString("codigo_oficina"));
				personal.setOficina(conn.resultado.getString("oficina"));
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personal;
	}

	@Override
	public void validarObjeto(Object objeto) {
		this.personal = (Personal) objeto;
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
