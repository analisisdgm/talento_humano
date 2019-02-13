package com.migraciones.talentoHumano.controladores;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.PerFechaIngreso;

public class PerFechaIngresoCont extends AncestroCont {

	private PerFechaIngreso personalIngreso = new PerFechaIngreso();

	public ArrayList<PerFechaIngreso> getHistorial(String cedula) throws ClassNotFoundException {
		ArrayList<PerFechaIngreso> ingresoHistorial = new ArrayList<PerFechaIngreso>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.personales_fecha_ingreso WHERE personal_cedula='"
							+ cedula + "' AND pering_estado='A' ORDER BY pering_id");
			while (conn.resultado.next()) {
				PerFechaIngreso pering = new PerFechaIngreso();
				pering.setId(conn.resultado.getInt("pering_id"));
				pering.setCedula(conn.resultado.getString("personal_cedula"));
				pering.setFecha(conn.resultado.getDate("pering_fecha"));
				pering.setObservacion(conn.resultado.getString("pering_observacion"));
				pering.setFechaAlta(conn.resultado.getTimestamp("pering_fecha_alta"));
				pering.setEstado(conn.resultado.getString("pering_estado"));
				pering.setAdministrador(conn.resultado.getString("admin_login"));
				pering.setCondicion(conn.resultado.getString("pering_condicion"));
				ingresoHistorial.add(pering);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingresoHistorial;
	}

	public boolean actualizarIngresoTransaccion(PerFechaIngreso nuevaFechaIngreso)
			throws ClassNotFoundException, SQLException {
		ConexionPostgresql conn = new ConexionPostgresql();
		conn.conexion.setAutoCommit(false);

		PreparedStatement pstmtUpdateTemporal = null, pstmtUpdate = null, pstmtInsert = null;
		String updateTableSQL = "UPDATE ficha_personal.personales_fecha_ingreso SET pering_estado='H', pering_fecha=? WHERE personal_cedula='"
				+ nuevaFechaIngreso.getCedula() + "' AND pering_estado='A'";
		String insertTableSQL = "INSERT INTO ficha_personal.personales_fecha_ingreso(personal_cedula,pering_fecha,pering_observacion,admin_login,pering_condicion) VALUES(?,?,?,?,?)";

		try {
		// actualizacion del ultima oficina del personal que pasa de activo
			// a historico o a baja
			pstmtUpdate = conn.conexion.prepareStatement(updateTableSQL);
		
			java.sql.Date sqlDate0 = new java.sql.Date(nuevaFechaIngreso.getFecha().getTime());
			pstmtUpdate.setDate(1, sqlDate0);
			pstmtUpdate.executeUpdate();

			// insercion de la nueva oficina del personal
			pstmtInsert = conn.conexion.prepareStatement(insertTableSQL);
			pstmtInsert.setString(1, nuevaFechaIngreso.getCedula());
			java.sql.Date sqlDate1 = new java.sql.Date(nuevaFechaIngreso.getFecha().getTime());
			pstmtInsert.setDate(2, sqlDate1);
			pstmtInsert.setString(3, nuevaFechaIngreso.getObservacion());
			pstmtInsert.setString(4, nuevaFechaIngreso.getAdministrador());
			pstmtInsert.setString(5, nuevaFechaIngreso.getCondicion());

			pstmtInsert.executeUpdate();
			conn.conexion.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.conexion.rollback();
			return false;
		} finally {
			if (pstmtUpdateTemporal != null) {
				pstmtUpdateTemporal.close();
			}
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
		this.personalIngreso = (PerFechaIngreso) objeto;
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
		return "INSERT INTO ficha_personal.personales_oficinas(personal_cedula,oficina_id,perdep_fecha_inicio,perdep_observacion,admin_login) VALUES(?,?,?,?,?)";
	}

	@Override
	public void executeInsertSQL(PreparedStatement pstmt) {
		try {
			pstmt.setString(1, this.personalIngreso.getCedula());
			pstmt.setDate(2, (Date) this.personalIngreso.getFecha());
			pstmt.setString(3, this.personalIngreso.getCondicion());
			pstmt.setString(4, this.personalIngreso.getAdministrador());
			pstmt.setString(5, this.personalIngreso.getObservacion());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateSQL() {
		return "UPDATE ficha_personal.personales_dependencias SET perdep_observacion='"
				+ this.personalIngreso.getObservacion() + "' WHERE perdep_estado='A' AND personal_cedula='"
				+ this.personalIngreso.getCedula() + "'";
	}

	@Override
	public String deleteSQL() {
		return "DELETE FROM ficha_personal.personales_oficinas WHERE perdep_id=" + personalIngreso.getId();
	}
}
