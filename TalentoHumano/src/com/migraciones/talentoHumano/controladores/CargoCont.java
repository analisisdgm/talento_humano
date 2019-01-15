package com.migraciones.talentoHumano.controladores;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.Cargo;

public class CargoCont extends AncestroCont {

	private Cargo cargo = new Cargo();

	public ArrayList<Cargo> getAll() throws ClassNotFoundException {
		ArrayList<Cargo> listaCargos = new ArrayList<Cargo>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery("SELECT * FROM ficha_personal.cargos ORDER BY cargo_id");
			while (conn.resultado.next()) {
				Cargo cargo = new Cargo();
				cargo.setCodigo(conn.resultado.getInt("cargo_id"));
				cargo.setDescripcion(conn.resultado.getString("cargo_descripcion"));
				listaCargos.add(cargo);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCargos;
	}

	public Cargo getByCodigo(String codigo) throws ClassNotFoundException {
		Cargo cargo = new Cargo();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.cargos WHERE cargo_id='" + codigo + "'");
			while (conn.resultado.next()) {
				cargo.setCodigo(conn.resultado.getInt("cargo_id"));
				cargo.setDescripcion(conn.resultado.getString("cargo_descripcion"));
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cargo;
	}

	@Override
	public void validarObjeto(Object objeto) {
		this.cargo = (Cargo) objeto;
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
		return "INSERT INTO ficha_personal.cargos(cargo_descripcion) VALUES(?)";
	}

	@Override
	public void executeInsertSQL(PreparedStatement pstmt) {
		try {
			pstmt.setString(1, this.cargo.getDescripcion());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateSQL() {
		return "UPDATE cargos SET cargo_descripcion='" + cargo.getDescripcion().toUpperCase() + "' WHERE id="
				+ cargo.getCodigo();
	}

	@Override
	public String deleteSQL() {
		return "DELETE FROM ficha_personal.cargos WHERE cargo_id=" + cargo.getCodigo();
	}
}
