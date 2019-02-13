package com.migraciones.talentoHumano.controladores;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.Dependencia;

public class DependenciaCont extends AncestroCont {

	private Dependencia dependencia = new Dependencia();

	public ArrayList<Dependencia> getAll() throws ClassNotFoundException {
		ArrayList<Dependencia> listaDependencias = new ArrayList<Dependencia>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery("SELECT * FROM ficha_personal.dependencias_vista ORDER BY id");
			while (conn.resultado.next()) {
				Dependencia dep = new Dependencia();
				dep.setId(conn.resultado.getInt("id"));
				dep.setDescripcion(conn.resultado.getString("descripcion"));
				dep.setCodigo(conn.resultado.getString("codigo"));
				dep.setIdPadre(conn.resultado.getInt("id_padre"));
				dep.setOficina(conn.resultado.getString("oficina_codigo"));
				listaDependencias.add(dep);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDependencias;
	}

	public Dependencia getById(int id) throws ClassNotFoundException {
		Dependencia dep = new Dependencia();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.dependencias_vista WHERE id=" + id);
			while (conn.resultado.next()) {
				dep.setId(conn.resultado.getInt("id"));
				dep.setDescripcion(conn.resultado.getString("descripcion"));
				dep.setCodigo(conn.resultado.getString("codigo"));
				dep.setIdPadre(conn.resultado.getInt("id_padre"));
				dep.setIdOficina(conn.resultado.getInt("oficina_id"));
				dep.setCodigoOficina(conn.resultado.getString("oficina_codigo"));
				dep.setOficina(conn.resultado.getString("oficina"));
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dep;
	}

	public Dependencia getByCodigo(String codigo) throws ClassNotFoundException {
		Dependencia dep = new Dependencia();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.dependencias_vista WHERE codigo='" + codigo + "'");
			while (conn.resultado.next()) {
				dep.setId(conn.resultado.getInt("id"));
				dep.setDescripcion(conn.resultado.getString("descripcion"));
				dep.setCodigo(conn.resultado.getString("codigo"));
				dep.setIdPadre(conn.resultado.getInt("id_padre"));
				dep.setIdOficina(conn.resultado.getInt("oficina_id"));
				dep.setCodigoOficina(conn.resultado.getString("oficina_codigo"));
				dep.setOficina(conn.resultado.getString("oficina"));
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dep;
	}

	@Override
	public void validarObjeto(Object objeto) {
		this.dependencia = (Dependencia) objeto;
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
		return "INSERT INTO ficha_personal.dependencias(dep_descripcion, dependencia_id_padre,dep_codigo) VALUES(?,?,?)";
	}

	@Override
	public void executeInsertSQL(PreparedStatement pstmt) {
		try {
			pstmt.setString(1, this.dependencia.getDescripcion());
			pstmt.setInt(2, this.dependencia.getIdPadre());
			pstmt.setString(3, this.dependencia.getCodigo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateSQL() {
		return "UPDATE controles SET control_descripcion='" + dependencia.getDescripcion().toUpperCase() + "' WHERE id="
				+ dependencia.getId();
	}

	@Override
	public String deleteSQL() {
		return "DELETE FROM ficha_personal.dependencia WHERE dep_id=" + dependencia.getId();
	}
}
