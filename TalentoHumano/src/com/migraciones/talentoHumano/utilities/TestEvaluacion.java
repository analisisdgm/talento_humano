package com.migraciones.talentoHumano.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;

public class TestEvaluacion {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// lectura de archivo
		String csvFile = "C:\\Users\\yiyo\\Desktop\\comisionados.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";
		// conexion a DB
		ConexionPostgresql connpg = new ConexionPostgresql();

		try {
			int cont = 0;
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] reg = line.split(cvsSplitBy);
				//System.out.println(cont +" ----"+reg[0] +" - " + reg[1] + " - " + reg[2] +" - " + reg[3] + " - " + reg[4].substring(1, 2));
				cont++;
				// if (reg[1].toString().equals("3827955")) {
				// System.out.println("yiyo");
				// }
				String SQL = "INSERT INTO ficha_personal.personales_evaluaciones(personal_cedula,pereva_puntaje_desempenho,pereva_puntaje_examen,pereva_puntaje_total,pereva_nota,pereva_periodo)"
						+ " VALUES('" + reg[0].toString() + "'," + reg[1] + "," + reg[2] + "," + reg[3]+ "," + reg[4].substring(1, 2)+ ",'ENERO 2015/OCTUBRE 2015')";
				//connpg.pstmt = connpg.conexion.prepareStatement(SQL);
				//connpg.pstmt.executeUpdate();
			}
			System.out.println(cont);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					connpg.conexion.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
