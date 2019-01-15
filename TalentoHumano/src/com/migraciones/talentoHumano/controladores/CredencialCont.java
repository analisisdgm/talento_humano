package com.migraciones.talentoHumano.controladores;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.Credencial;
import com.migraciones.talentoHumano.test.Temporal;

public class CredencialCont extends AncestroCont {

	private Credencial credencial = new Credencial();

	public ArrayList<Temporal> getAll() throws ClassNotFoundException {
		ArrayList<Temporal> listaCredenciales = new ArrayList<Temporal>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery("SELECT * FROM ficha_personal.personales_cargos");
			while (conn.resultado.next()) {
				Temporal cred = new Temporal();
				cred.setId(conn.resultado.getInt("percar_id"));
				cred.setCedula(conn.resultado.getString("personal_cedula"));
				cred.setCargo_id(conn.resultado.getInt("cargo_id"));
				cred.setEstado(conn.resultado.getString("percar_estado"));
				cred.setFecha_inicio(conn.resultado.getDate("percar_fecha_inicio"));
				cred.setFecha_fin(conn.resultado.getDate("percar_fecha_fin"));
				cred.setObservacion(conn.resultado.getString("percar_observacion"));
				if (cred.getObservacion() == null) {
					cred.setObservacion("");
				}
				cred.setAdmin(conn.resultado.getString("admin_login"));

				listaCredenciales.add(cred);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCredenciales;
	}

	public Credencial getById(String cedula) throws ClassNotFoundException {
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery(
					"SELECT cedula,nombres,apellidos,cargo,condicion FROM ficha_personal.credenciales_vista WHERE cedula='"
							+ cedula + "'");
			while (conn.resultado.next()) {
				credencial.setCedula(conn.resultado.getString("cedula"));
				credencial.setNombres(conn.resultado.getString("nombres"));
				credencial.setApellidos(conn.resultado.getString("apellidos"));
				credencial.setCargo(conn.resultado.getString("cargo"));
				credencial.setCondicion(conn.resultado.getInt("condicion"));
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return credencial;
	}

	@Override
	public void validarObjeto(Object objeto) {
		this.credencial = (Credencial) objeto;
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
