package com.migraciones.talentoHumano.controladores;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.PerCar;
import com.migraciones.talentoHumano.utilities.FechaUtil;

public class PerCarCont extends AncestroCont {

	private PerCar personalCargo = new PerCar();

	public ArrayList<PerCar> getHistorial(String cedula) throws ClassNotFoundException {
		ArrayList<PerCar> cargosHistorial = new ArrayList<PerCar>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery(
					"SELECT * FROM ficha_personal.personales_cargos WHERE percar_estado='A' ORDER BY percar_id");
			while (conn.resultado.next()) {
				PerCar percar = new PerCar();
				percar.setId(conn.resultado.getInt("percar_id"));
				percar.setCedula(conn.resultado.getString("personal_cedula"));
				percar.setCargoId(conn.resultado.getInt("cargo_id"));
				percar.setEstado(conn.resultado.getString("percar_estado"));
				percar.setFechaInicio(conn.resultado.getDate("percar_fecha_inicio"));
				percar.setFechaFin(conn.resultado.getDate("percar_fecha_fin"));
				percar.setFechaAlta(conn.resultado.getTimestamp("percar_fecha_alta"));
				percar.setObservacion(conn.resultado.getString("percar_observacion"));
				percar.setAdministrador(conn.resultado.getString("admin_login"));
				cargosHistorial.add(percar);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cargosHistorial;
	}

	public boolean actualizarCargoTransaccion(PerCar nuevoCargo) throws ClassNotFoundException, SQLException {
		ConexionPostgresql conn = new ConexionPostgresql();
		conn.conexion.setAutoCommit(false);

		PreparedStatement pstmtUpdate = null, pstmtInsert = null;
		String updateTableSQL = "UPDATE ficha_personal.personales_cargos SET percar_estado='H', percar_fecha_fin=? WHERE personal_cedula='"
				+ nuevoCargo.getCedula() + "' AND percar_estado='A'";
		String insertTableSQL = "INSERT INTO ficha_personal.personales_cargos(personal_cedula,cargo_id,percar_fecha_inicio,percar_observacion,admin_login) VALUES(?,?,?,?,?)";

		try {
			// actualizacion del ultima oficina del personal que pasa de activo
			// a historico o a baja
			pstmtUpdate = conn.conexion.prepareStatement(updateTableSQL);
			FechaUtil util = new FechaUtil();
			nuevoCargo.setFechaFin(util.sumarRestarDiasFecha(nuevoCargo.getFechaInicio(), -1));
			java.sql.Date sqlDate0 = new java.sql.Date(nuevoCargo.getFechaFin().getTime());
			pstmtUpdate.setDate(1, sqlDate0);
			pstmtUpdate.executeUpdate();

			// insertcion de la nueva oficina del personal
			pstmtInsert = conn.conexion.prepareStatement(insertTableSQL);
			pstmtInsert.setString(1, nuevoCargo.getCedula());
			java.sql.Date sqlDate1 = new java.sql.Date(nuevoCargo.getFechaInicio().getTime());
			pstmtInsert.setInt(2, nuevoCargo.getCargoId());
			pstmtInsert.setDate(3, sqlDate1);
			pstmtInsert.setString(4, nuevoCargo.getObservacion());
			pstmtInsert.setString(5, nuevoCargo.getAdministrador());

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

	@Override
	public void validarObjeto(Object objeto) {
		this.personalCargo = (PerCar) objeto;
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
		return "INSERT INTO ficha_personal.personales_cargos(personal_cedula,cargo_id,percar_fecha_inicio,percar_observacion,admin_login) VALUES(?,?,?,?,?)";
	}

	@Override
	public void executeInsertSQL(PreparedStatement pstmt) {
		try {
			pstmt.setString(1, this.personalCargo.getCedula());
			pstmt.setInt(2, this.personalCargo.getId());
			pstmt.setDate(3, (Date) this.personalCargo.getFechaInicio());
			pstmt.setString(4, this.personalCargo.getObservacion());
			pstmt.setString(5, this.personalCargo.getAdministrador());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateSQL() {
		return "UPDATE ficha_personal.personales_cargos SET percar_observacion='" + this.personalCargo.getObservacion()
				+ "' WHERE percar_estado='A' AND personal_cedula='" + this.personalCargo.getCedula() + "'";
	}

	@Override
	public String deleteSQL() {
		return "DELETE FROM ficha_personal.personales_cargos WHERE percar_id=" + personalCargo.getId();
	}
}
