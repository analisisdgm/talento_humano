package com.migraciones.talentoHumano.dataBases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConexionPostgresql {
	public PreparedStatement pstmt = null;
	public Statement sentencia = null;
	public ResultSet resultado = null;
	public Connection conexion = null;
	private final String USER = "dpane";
	private final String HOST = "192.168.1.238";
	private final String PASSWD = "230489";
	// private final String USER = "postgres";
	// private final String HOST = "192.168.1.245";
	// private final String PASSWD = "*sis2004";
	private final String URLCONECT = "jdbc:postgresql://" + HOST + ":5432/talento_humano";
	private final String DRIVER = "org.postgresql.Driver";

	public ConexionPostgresql() throws ClassNotFoundException {
		try {
			Class.forName(DRIVER);
			conexion = DriverManager.getConnection(URLCONECT, USER, PASSWD);
		} catch (SQLException e) {
			System.out.println(e.toString());
			JOptionPane.showMessageDialog(null, "Error en la conexion", "Atencion", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
}
