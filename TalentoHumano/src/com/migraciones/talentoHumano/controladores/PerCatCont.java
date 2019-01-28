package com.migraciones.talentoHumano.controladores;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.PerCat;
import com.migraciones.talentoHumano.utilities.FechaUtil;

public class PerCatCont extends AncestroCont {

	private PerCat personalCategoria = new PerCat();

	public ArrayList<PerCat> getHistorial(String cedula) throws ClassNotFoundException {
		ArrayList<PerCat> cargosHistorial = new ArrayList<PerCat>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.categorias WHERE percat_estado='A' ORDER BY percat_id");
			while (conn.resultado.next()) {
				PerCat percat = new PerCat();
				percat.setId(conn.resultado.getInt("percat_id"));
				percat.setCedula(conn.resultado.getString("personal_cedula"));
				percat.setId(conn.resultado.getInt("categoria_id"));
				percat.setEstado(conn.resultado.getString("percat_estado"));
				percat.setFechaInicio(conn.resultado.getDate("percat_fecha_inicio"));
				percat.setFechaFin(conn.resultado.getDate("percat_fecha_fin"));
				percat.setFechaAlta(conn.resultado.getTimestamp("percat_fecha_alta"));
				percat.setObservacion(conn.resultado.getString("percat_observacion"));
				percat.setAdministrador(conn.resultado.getString("admin_login"));
				cargosHistorial.add(percat);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cargosHistorial;
	}

	public boolean actualizarCategoriaTransaccion(PerCat nuevaCategoria) throws ClassNotFoundException, SQLException {
		ConexionPostgresql conn = new ConexionPostgresql();
		conn.conexion.setAutoCommit(false);

		PreparedStatement pstmtUpdate = null, pstmtInsert = null;
		String updateTableSQL = "UPDATE ficha_personal.personales_categorias SET percat_estado='H', percat_fecha_fin=? WHERE personal_cedula='"
				+ nuevaCategoria.getCedula() + "' AND percat_estado='A'";
		String insertTableSQL = "INSERT INTO ficha_personal.personales_categorias(personal_cedula,categoria_id,percat_fecha_inicio,percat_observacion,admin_login) VALUES(?,?,?,?,?)";

System.out.println(nuevaCategoria.getCodigo());
		try {
			// actualizacion del ultima categoria del personal que pasa de
			// activo
			// a historico o a baja
			pstmtUpdate = conn.conexion.prepareStatement(updateTableSQL);
			FechaUtil util = new FechaUtil();
			nuevaCategoria.setFechaFin(util.sumarRestarDiasFecha(nuevaCategoria.getFechaInicio(), -1));
			java.sql.Date sqlDate0 = new java.sql.Date(nuevaCategoria.getFechaFin().getTime());
			pstmtUpdate.setDate(1, sqlDate0);
			pstmtUpdate.executeUpdate();

			// insertcion de la nueva categoria del personal
			pstmtInsert = conn.conexion.prepareStatement(insertTableSQL);
			pstmtInsert.setString(1, nuevaCategoria.getCedula());
			java.sql.Date sqlDate1 = new java.sql.Date(nuevaCategoria.getFechaInicio().getTime());
			pstmtInsert.setInt(2, this.getIdCategoria(nuevaCategoria.getCodigo()));
			pstmtInsert.setDate(3, sqlDate1);
			pstmtInsert.setString(4, nuevaCategoria.getObservacion());
			pstmtInsert.setString(5, nuevaCategoria.getAdministrador());

			pstmtInsert.executeUpdate();
			conn.conexion.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.conexion.rollback();
			return false;
		} finally {

			if (pstmtUpdate != null) {
				pstmtUpdate.close();
			}

			if (pstmtInsert != null) {
				pstmtInsert.close();
			}
			if (conn.conexion != null) {
				conn.conexion.close();
			}
		}
	}
	private int getIdCategoria(String codigo) throws ClassNotFoundException {
		int id = 0;
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT cat_id FROM ficha_personal.categorias WHERE cat_codigo='" + codigo + "'");
			while (conn.resultado.next()) {
				id = conn.resultado.getInt("cat_id");
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public void validarObjeto(Object objeto) {
		this.personalCategoria = (PerCat) objeto;
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
		return "INSERT INTO ficha_personal.personales_categorias(personal_cedula,cartegoria_id,percat_fecha_inicio,percat_observacion,admin_login) VALUES(?,?,?,?,?)";
	}

	@Override
	public void executeInsertSQL(PreparedStatement pstmt) {
		try {
			pstmt.setString(1, this.personalCategoria.getCedula());
			pstmt.setInt(2, this.personalCategoria.getId());
			pstmt.setDate(3, (Date) this.personalCategoria.getFechaInicio());
			pstmt.setString(4, this.personalCategoria.getObservacion());
			pstmt.setString(5, this.personalCategoria.getAdministrador());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateSQL() {
		return "UPDATE ficha_personal.personales_categorias SET percat_observacion='"
				+ this.personalCategoria.getObservacion() + "' WHERE percat_estado='A' AND personal_cedula='"
				+ this.personalCategoria.getCedula() + "'";
	}

	@Override
	public String deleteSQL() {
		return "DELETE FROM ficha_personal.personales_categorias WHERE percar_id=" + personalCategoria.getId();
	}

	public ArrayList<PerCat> getHistorialCategoria(String cedula) throws ClassNotFoundException {
		ArrayList<PerCat> horarioHistorial = new ArrayList<PerCat>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery("SELECT * FROM ficha_personal.obtener_historial_categorias('"
					+ cedula + "') ORDER BY fecha_inicio");
			while (conn.resultado.next()) {
				PerCat categoria = new PerCat();
				categoria.setCodigo(conn.resultado.getString("categoria_codigo"));
				categoria.setDescripcion(conn.resultado.getString("categoria"));
				categoria.setObservacion(conn.resultado.getString("observaciones"));
				categoria.setFechaInicio(conn.resultado.getDate("fecha_inicio"));
				categoria.setFechaFin(conn.resultado.getDate("fecha_fin"));
				horarioHistorial.add(categoria);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horarioHistorial;
	}
}
