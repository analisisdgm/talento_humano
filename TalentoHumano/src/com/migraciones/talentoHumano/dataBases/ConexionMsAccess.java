package com.migraciones.talentoHumano.dataBases;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionMsAccess {
	public PreparedStatement pstmt = null;
	public Statement sentencia = null;
	public ResultSet resultado = null;
	public Connection conexion = null;

	private final String USER = " ";
	private final String PASSWD = " ";
	private final String URLCONECT = "jdbc:ucanaccess://";

	private final String BANCO = "\\\\192.168.1.245\\stanbm$\\Att2003.mdb";
	// linea para actualizacion automatica de la db
	// private final String BANCO = "\\\\C:\\standard\\Att2003.mdb";
	private final String DRIVER = "net.ucanaccess.jdbc.UcanaccessDriver";

	public ConexionMsAccess() throws ClassNotFoundException {
		try {
			Class.forName(DRIVER);
			conexion = DriverManager.getConnection(URLCONECT + BANCO, USER, PASSWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
