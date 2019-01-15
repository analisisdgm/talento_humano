package com.migraciones.talentoHumano.dataBases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConexionMysql {
	public PreparedStatement pstmt = null;
	public Statement sentencia = null;
	public ResultSet resultado = null;
	public Connection conexion = null;
	private final String PASSWD = "230489";
	private final String HOST = "localhost";
	private final String USER = "root";
	private final String URLCONECT = "jdbc:mysql://" + HOST + ":3306/marker";
	private final String DRIVER = "com.mysql.jdbc.Driver";

	public ConexionMysql() throws ClassNotFoundException {
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
