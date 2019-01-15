package com.migraciones.talentoHumano.controladores;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.controllers.AncestroController;
import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.Oficina;
import com.migraciones.talentoHumano.modelos.PerHistoDisip;
import com.migraciones.talentoHumano.utilities.FechaUtil;

public class PerHistoDisipCont extends AncestroController {
	private FechaUtil util = new FechaUtil();
	private PerHistoDisip historial = new PerHistoDisip();

	public ArrayList<PerHistoDisip> getAllByMes(String cedula, String fecha) throws ClassNotFoundException {
		ArrayList<PerHistoDisip> histarialListado = new ArrayList<PerHistoDisip>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery(
					"SELECT * FROM ficha_personal.historial_disciplinario WHERE personal_cedula='" + cedula + "' "
							+ "AND '" + fecha + "' BETWEEN histodisp_fecha_desde AND histodisp_fecha_hasta"
							+ " AND histodisp_estado='A' ORDER BY histodisip_id");
			while (conn.resultado.next()) {
				PerHistoDisip historial = new PerHistoDisip();
				historial.setId(conn.resultado.getInt("histodisip_id"));
				historial.setCedula(conn.resultado.getString("personal_cedula"));
				historial.setTipo(conn.resultado.getInt("tipo_justificativo_id"));
				historial.setFechaDesde(conn.resultado.getDate("histodisp_fecha_desde"));
				historial.setFechaHasta(conn.resultado.getDate("histodisp_fecha_hasta"));
				historial.setObservacion(conn.resultado.getString("histodisp_motivo"));
				historial.setEstado(conn.resultado.getString("histodisp_estado"));
				historial.setAdministrador(conn.resultado.getString("admin_login"));
				histarialListado.add(historial);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return histarialListado;
	}

	public ArrayList<PerHistoDisip> getHistorialRango(String inicio, String fin) throws ClassNotFoundException {
		ArrayList<PerHistoDisip> histarialListado = new ArrayList<PerHistoDisip>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery(
					"SELECT * FROM ficha_personal.historial_disciplinario WHERE histodisp_fecha_desde BETWEEN '"
							+ inicio + "' AND '" + fin + "' AND histodisp_estado='A' ORDER BY histodisip_id");
			while (conn.resultado.next()) {
				PerHistoDisip historial = new PerHistoDisip();
				historial.setId(conn.resultado.getInt("histodisip_id"));
				historial.setCedula(conn.resultado.getString("personal_cedula"));
				historial.setTipo(conn.resultado.getInt("tipo_justificativo_id"));
				historial.setFechaDesde(conn.resultado.getDate("histodisp_fecha_desde"));
				historial.setFechaHasta(conn.resultado.getDate("histodisp_fecha_hasta"));
				historial.setObservacion(conn.resultado.getString("histodisp_motivo"));
				historial.setEstado(conn.resultado.getString("histodisp_estado"));
				historial.setAdministrador(conn.resultado.getString("admin_login"));
				histarialListado.add(historial);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return histarialListado;
	}

	public Oficina getById(int id) throws ClassNotFoundException {
		Oficina oficina = new Oficina();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery("SELECT * FROM ficha_personal.oficinas WHERE ofi_id=" + id);
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

	public boolean actualizarHistorial(PerHistoDisip hist) throws ClassNotFoundException {
		boolean control = false;
		ConexionPostgresql connpg = new ConexionPostgresql();
		String SQL = "UPDATE ficha_personal.historial_disciplinario SET personal_cedula='" + hist.getCedula()
				+ "', tipo_justificativo_id=" + hist.getTipo() + ", histodisp_fecha_desde='"
				+ util.getDateToString(hist.getFechaDesde()) + "', histodisp_fecha_hasta='"
				+ util.getDateToString(hist.getFechaHasta()) + "', histodisp_motivo='" + hist.getObservacion()
				+ "', admin_login='" + hist.getAdministrador() + "' WHERE histodisip_id=" + hist.getId();
		try {
			connpg.sentencia = (Statement) connpg.conexion.createStatement();
			connpg.sentencia.executeUpdate(SQL);
			connpg.conexion.close();
			control = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return control;
	}

	@Override
	public void validarObjeto(Object objeto) {
		this.historial = (PerHistoDisip) objeto;
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
		return "INSERT INTO ficha_personal.historial_disciplinario(personal_cedula,tipo_justificativo_id,histodisp_fecha_desde,histodisp_fecha_hasta,histodisp_motivo,admin_login) VALUES(?,?,?,?,?,?)";
	}

	@Override
	public void executeInsertSQL(PreparedStatement pstmt) {
		try {
			pstmt.setString(1, this.historial.getCedula());
			pstmt.setInt(2, this.historial.getTipo());
			java.sql.Date sqlDate0 = new java.sql.Date(this.historial.getFechaDesde().getTime());
			java.sql.Date sqlDate1 = new java.sql.Date(this.historial.getFechaHasta().getTime());
			pstmt.setDate(3, sqlDate0);
			pstmt.setDate(4, sqlDate1);
			pstmt.setString(5, this.historial.getObservacion());
			pstmt.setString(6, this.historial.getAdministrador());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateSQL() {
		return "UPDATE ficha_personal.historial_disciplinario SET histodisp_estado='H', admin_login='"
				+ historial.getAdministrador() + "' WHERE histodisip_id=" + historial.getId();
	}

	@Override
	public String deleteSQL() {
		return "DELETE FROM ficha_personal.historial_disciplinario WHERE histodiscip_id=" + historial.getId();
	}
}
