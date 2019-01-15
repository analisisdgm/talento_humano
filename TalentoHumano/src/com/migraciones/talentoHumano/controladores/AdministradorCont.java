package com.migraciones.talentoHumano.controladores;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.Administrador;

public class AdministradorCont extends AncestroCont {

	private Administrador administrador = new Administrador();

	public ArrayList<Administrador> getAll() {
		ArrayList<Administrador> listaAdministradores = new ArrayList<Administrador>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery("SELECT * FROM dgth.administradores");
			while (conn.resultado.next()) {
				Administrador administrador = new Administrador();
				administrador.setLogin(conn.resultado.getString("admin_login"));
				administrador.setPassword(conn.resultado.getString("admin_password"));
				administrador.setTipoAdministrador(conn.resultado.getInt("tipoadmin_id"));
				administrador.setEstado(conn.resultado.getString("admin_estado"));
				administrador.setCedula(conn.resultado.getString("personal_cedula"));
				administrador.setFechaCreacion(conn.resultado.getTimestamp("admin_fecha_creacion"));
				listaAdministradores.add(administrador);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listaAdministradores;
	}

	public Administrador getById(String login, String password) throws ClassNotFoundException {
		Administrador administrador = new Administrador();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery("SELECT * FROM dgth.administradores WHERE admin_login='"
					+ login + "' AND admin_password='" + password + "'");
			while (conn.resultado.next()) {
				administrador.setLogin(conn.resultado.getString("admin_login"));
				administrador.setPassword(conn.resultado.getString("admin_password"));
				administrador.setTipoAdministrador(conn.resultado.getInt("tipoadmin_id"));
				administrador.setEstado(conn.resultado.getString("admin_estado"));
				administrador.setCedula(conn.resultado.getString("personal_cedula"));
				administrador.setFechaCreacion(conn.resultado.getTimestamp("admin_fecha_creacion"));
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return administrador;
	}

	public Administrador getById(String login) throws ClassNotFoundException {
		Administrador administrador = new Administrador();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM dgth.administradores WHERE admin_login='" + login + "'");
			while (conn.resultado.next()) {
				administrador.setLogin(conn.resultado.getString("admin_login"));
				administrador.setPassword(conn.resultado.getString("admin_password"));
				administrador.setTipoAdministrador(conn.resultado.getInt("tipoadmin_id"));
				administrador.setEstado(conn.resultado.getString("admin_estado"));
				administrador.setCedula(conn.resultado.getString("personal_cedula"));
				administrador.setFechaCreacion(conn.resultado.getTimestamp("admin_fecha_creacion"));
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return administrador;
	}

	@Override
	public void validarObjeto(Object objeto) {
		this.administrador = (Administrador) objeto;
	}

	@Override
	public void validarInsercionRegistro(Object objeto) {
		// TODO Auto-generated method stub
	}

	@Override
	public void validarActualizacionRegistro(Object objeto) {
		// TODO Auto-generated method stub
	}

	@Override
	public void validarEliminacionRegistro(Object objeto) {
		// TODO Auto-generated method stub
	}

	@Override
	public String insertSQL() {
		return "INSERT INTO dgth.administradores(admin_login,admin_password,tipoadmin_id,personal_cedula) "
				+ "VALUES(?,?,?,?)";
	}

	@Override
	public void executeInsertSQL(PreparedStatement pstmt) {
		try {
			pstmt.setString(1, this.administrador.getLogin());
			pstmt.setString(2, this.administrador.getPassword());
			pstmt.setInt(3, this.administrador.getTipoAdministrador());
			pstmt.setString(4, this.administrador.getCedula());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateSQL() {
		return "UPDATE dgth.administradores SET admin_password='" + administrador.getPassword()
				+ "' WHERE admin_login='" + administrador.getLogin() + "'";
	}

	@Override
	public String deleteSQL() {
		return "DELETE FROM dgth.administradores WHERE admin_login='" + administrador.getLogin() + "'";
	}

}