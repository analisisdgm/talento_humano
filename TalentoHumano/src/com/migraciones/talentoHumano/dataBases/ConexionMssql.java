package com.migraciones.talentoHumano.dataBases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConexionMssql {
	public PreparedStatement pstmt = null;
	public Statement sentencia = null;
	public ResultSet resultado = null;
	public Connection conexion = null;
	private final String PASSWD = "x139208na.*";
	private final String HOST = "192.168.1.105";
	private final String USER = "sa";
	private final String URLCONECT = "jdbc:sqlserver://" + HOST + ":1433;databaseName=CrossChex";
	private final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public ConexionMssql() throws ClassNotFoundException {
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
