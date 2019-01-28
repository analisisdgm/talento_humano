package com.migraciones.talentoHumano.controladores;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.Categoria;

public class CategoriaCont extends AncestroCont {

	private Categoria categoria = new Categoria();

	public ArrayList<Categoria> getAll() throws ClassNotFoundException {
		ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery("SELECT * FROM ficha_personal.categorias ORDER BY cat_id");
			while (conn.resultado.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(conn.resultado.getInt("cat_id"));
				categoria.setDescripcion(conn.resultado.getString("cat_descripcion"));
				categoria.setCodigo(conn.resultado.getString("cat_codigo"));
				listaCategorias.add(categoria);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCategorias;
	}

	public Categoria getByCodigo(String codigo) throws ClassNotFoundException {
		Categoria categoria = new Categoria();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.categorias WHERE cat_codigo='" + codigo + "'");
			while (conn.resultado.next()) {
				categoria.setId(conn.resultado.getInt("cat_id"));
				categoria.setDescripcion(conn.resultado.getString("cat_descripcion"));
				categoria.setCodigo(conn.resultado.getString("cat_codigo"));
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoria;
	}

	@Override
	public void validarObjeto(Object objeto) {
		this.categoria = (Categoria) objeto;
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
		return "INSERT INTO ficha_personal.oficinas(cat_descripcion, cat_codigo) VALUES(?,?)";
	}

	@Override
	public void executeInsertSQL(PreparedStatement pstmt) {
		try {
			pstmt.setString(1, this.categoria.getDescripcion());
			pstmt.setString(2, this.categoria.getCodigo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateSQL() {
		return "UPDATE ficha_personal.categorias SET control_descripcion='" + categoria.getDescripcion().toUpperCase() + "' WHERE id="
				+ categoria.getId();
	}

	@Override
	public String deleteSQL() {
		return "DELETE FROM ficha_personal.categorias WHERE cat_id=" + categoria.getId();
	}
}
