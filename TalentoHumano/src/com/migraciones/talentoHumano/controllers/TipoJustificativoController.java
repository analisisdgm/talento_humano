package com.migraciones.talentoHumano.controllers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.models.TipoJustificativo;

public class TipoJustificativoController extends AncestroController {

	@SuppressWarnings("unused")
	private TipoJustificativo tipo = new TipoJustificativo();

	public ArrayList<TipoJustificativo> getAll() throws ClassNotFoundException {
		ArrayList<TipoJustificativo> listaTipos = new ArrayList<TipoJustificativo>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.tipos_justificativo ORDER BY justificativo_id");
			while (conn.resultado.next()) {
				TipoJustificativo tipo = new TipoJustificativo();
				tipo.setId(conn.resultado.getInt("justificativo_id"));
				tipo.setDescripcion(conn.resultado.getString("just_descripcion"));
				listaTipos.add(tipo);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaTipos;
	}

	public TipoJustificativo getById(int id) throws ClassNotFoundException {
		TipoJustificativo tipo = new TipoJustificativo();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.tipos_justificativo WHERE justificativo_id=" + id);
			while (conn.resultado.next()) {
				tipo.setId(conn.resultado.getInt("justificativo_id"));
				tipo.setDescripcion(conn.resultado.getString("just_descripcion"));
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipo;
	}

	@Override
	public void validarObjeto(Object objeto) {
		this.tipo = (TipoJustificativo) objeto;
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
