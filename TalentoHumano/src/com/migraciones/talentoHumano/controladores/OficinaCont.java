package com.migraciones.talentoHumano.controladores;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.Oficina;

public class OficinaCont extends AncestroCont {

	private Oficina oficina = new Oficina();

	public ArrayList<Oficina> getAll() throws ClassNotFoundException {
		ArrayList<Oficina> listaOficinas = new ArrayList<Oficina>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery("SELECT * FROM ficha_personal.oficinas ORDER BY ofi_id");
			while (conn.resultado.next()) {
				Oficina oficina = new Oficina();
				oficina.setId(conn.resultado.getInt("ofi_id"));
				oficina.setDescripcion(conn.resultado.getString("ofi_descripcion"));
				oficina.setDireccion(conn.resultado.getString("ofi_direccion"));
				oficina.setCodigo(conn.resultado.getString("ofi_codigo"));
				listaOficinas.add(oficina);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaOficinas;
	}

	public Oficina getByCodigo(String codigo) throws ClassNotFoundException {
		Oficina oficina = new Oficina();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.oficinas WHERE ofi_codigo='" + codigo + "'");
			while (conn.resultado.next()) {
				oficina.setId(conn.resultado.getInt("ofi_id"));
				oficina.setDescripcion(conn.resultado.getString("ofi_descripcion"));
				oficina.setDireccion(conn.resultado.getString("ofi_direccion"));
				oficina.setCodigo(conn.resultado.getString("ofi_codigo"));
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return oficina;
	}

	@Override
	public void validarObjeto(Object objeto) {
		this.oficina = (Oficina) objeto;
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
		return "INSERT INTO ficha_personal.oficinas(ofi_descripcion, ofi_direccion,ofi_codigo) VALUES(?,?,?)";
	}

	@Override
	public void executeInsertSQL(PreparedStatement pstmt) {
		try {
			pstmt.setString(1, this.oficina.getDescripcion());
			pstmt.setString(2, this.oficina.getDireccion());
			pstmt.setString(3, this.oficina.getCodigo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateSQL() {
		return "UPDATE controles SET control_descripcion='" + oficina.getDescripcion().toUpperCase() + "' WHERE id="
				+ oficina.getId();
	}

	@Override
	public String deleteSQL() {
		return "DELETE FROM ficha_personal.oficina WHERE ofi_id=" + oficina.getId();
	}
}
