package com.migraciones.talentoHumano.controllers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.TipoPersonal;

public class TipoPersonalController extends AncestroController {

	@SuppressWarnings("unused")
	private TipoPersonal tipo = new TipoPersonal();

	public ArrayList<TipoPersonal> getAll() throws ClassNotFoundException {
		ArrayList<TipoPersonal> listaTipos = new ArrayList<TipoPersonal>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.tipos_personal ORDER BY tipoper_id");
			while (conn.resultado.next()) {
				TipoPersonal tipo = new TipoPersonal();
				tipo.setId(conn.resultado.getInt("tipoper_id"));
				tipo.setDescripcion(conn.resultado.getString("tipoper_descripcion"));

				listaTipos.add(tipo);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaTipos;
	}

	public TipoPersonal getById(int id) throws ClassNotFoundException {
		TipoPersonal tipo = new TipoPersonal();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.tipos_personal WHERE tipoper_id=" + id);
			while (conn.resultado.next()) {
				tipo.setId(conn.resultado.getInt("tipoper_id"));
				tipo.setDescripcion(conn.resultado.getString("tipoper_descripcion"));
				tipo.setCondicion(conn.resultado.getString("tipoper_condicion"));
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipo;
	}

	public TipoPersonal getByCedulaPersonal(String cedula) throws ClassNotFoundException {
		TipoPersonal tipo = new TipoPersonal();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.personales_vista WHERE cedula = '" + cedula	+ "'");
			while (conn.resultado.next()) {
				tipo.setId(conn.resultado.getInt("cond_id"));
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipo;
	}
	
	@Override
	public void validarObjeto(Object objeto) {
		this.tipo = (TipoPersonal) objeto;
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
