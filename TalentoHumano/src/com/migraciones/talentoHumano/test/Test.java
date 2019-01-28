package com.migraciones.talentoHumano.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.migraciones.talentoHumano.app.TalentoHumano;
import com.migraciones.talentoHumano.controladores.CredencialCont;
import com.migraciones.talentoHumano.controladores.PersonalCont;
import com.migraciones.talentoHumano.controladores.Temporal;
import com.migraciones.talentoHumano.dataBases.ConexionMsAccess;
import com.migraciones.talentoHumano.dataBases.ConexionMssql;
import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.PerHistoDisip;
import com.migraciones.talentoHumano.modelos.Personal;
import com.migraciones.talentoHumano.utilities.FechaUtil;
import com.migraciones.talentoHumano.utilities.ImprimirReporte;

public class Test {
	static ArrayList<String> listaFuncionariosPg = new ArrayList<String>();

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, IOException {
	}

	public static void imprimirReporte() throws ClassNotFoundException {
		PersonalCont per = new PersonalCont();

		ImprimirReporte ir = new ImprimirReporte();
		// Imprimir personales por abcdario
		// ir.imprimirPersonalesABC(per.getABC());
		// Imprimir personales por categoria
		// ir.imprimirPersonalesCat(per.getPeronalCategoria(""));
		// Imprimir personales por condicion
		// ir.imprimirPersonalesCondicion(per.getPeronalCondicion("NOMBRADO"));
		// Imprimir personales por dependencia
		// ir.imprimirPersonalesDependencia(per.getPersonalDependencia("3"));
		// Imprimir personales por oficina
		// ir.imprimirPersonalesOficina(per.getPersonalOficina("PIA"));
		// Imprimir personales por sexo
		ir.imprimirPersonalesSexo(per.getPeronalporSexo("F"));

	}

	public static void llenarTablaCategorias() throws ClassNotFoundException, SQLException {
		PersonalCont personalCont = new PersonalCont();
		ArrayList<Personal> personales = personalCont.getAll();
		ConexionPostgresql connpg = new ConexionPostgresql();
		try {
			for (Personal p : personales) {
				String sql = "INSERT INTO ficha_personal.personales_categorias(personal_cedula,categoria_id,percat_fecha_inicio,percat_fecha_fin,admin_login) VALUES ('"
						+ p.getCedula() + "',999,'2016-01-01','2019-01-01','administrador')";
				// connpg.pstmt = connpg.conexion.prepareStatement(sql);
				// connpg.pstmt.executeUpdate();
			}
			System.out.println(personales.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static void llenarTablaCredenciales() throws ClassNotFoundException, SQLException {
		CredencialCont credCont = new CredencialCont();
		ArrayList<Temporal> credenciales = credCont.getAll();
		ConexionPostgresql connpg = new ConexionPostgresql();
		try {
			for (Temporal c : credenciales) {
				String sql = "INSERT INTO ficha_personal.personales_credenciales(percre_id, personal_cedula,credencial_id,"
						+ "percre_estado,percre_fecha_inicio,percre_fecha_fin,percre_observacion,admin_login) VALUES ("
						+ c.getId() + ",'" + c.getCedula() + "'," + c.getCargo_id() + ",'" + c.getEstado() + "','"
						+ c.getFecha_inicio() + "','" + c.getFecha_fin() + "','" + c.getObservacion() + "','"
						+ c.getAdmin() + "')";
				System.out.println(sql);
				connpg.pstmt = connpg.conexion.prepareStatement(sql);
				connpg.pstmt.executeUpdate();
			}
			System.out.println(credenciales.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connpg.conexion.close();
		}
	}

	public static void bertaPedidoFeriados() throws ParseException, ClassNotFoundException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String inicio = "31/12/2018";
		String fin = "31/12/2018";
		Date dateInicio = formatter.parse(inicio);
		Date dateFin = formatter.parse(fin);
		TalentoHumano sis = new TalentoHumano();
		for (Personal p : sis.obtenerFuncionarios()) {
			PerHistoDisip historia = new PerHistoDisip();
			historia.setCedula(p.getCedula());
			historia.setFechaDesde(dateInicio);
			historia.setFechaHasta(dateFin);
			historia.setTipo(3);
			historia.setObservacion("ASUETO NACIONAL - DECRETO N° 848/2018");
			historia.setAdministrador("administrador");
			sis.agregarHistoriaDisciplinaria(historia);
		}
	}

	public static void bertaPedidoSalidaAnt() throws ParseException, ClassNotFoundException {
		// pedido de berta fecha 06/04/2018 08:50
		// ############# 28/03/2018 ############
		@SuppressWarnings("unused")
		FechaUtil util = new FechaUtil();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String inicio = "30/04/2018";
		String fin = "30/04/2018";
		Date dateInicio = formatter.parse(inicio);
		Date dateFin = formatter.parse(fin);
		TalentoHumano sis = new TalentoHumano();
		for (Personal p : sis.obtenerFuncionariosByOficina("OCE")) {
			PerHistoDisip historia = new PerHistoDisip();
			historia.setCedula(p.getCedula());
			historia.setFechaDesde(dateInicio);
			historia.setFechaHasta(dateFin);
			historia.setTipo(2);
			historia.setObservacion("SALIDA ANTICIPADA S/ CIRCULAR DGTH N° 16/2018");
			historia.setAdministrador("administrador");
			sis.agregarHistoriaDisciplinaria(historia);
		}
	}

	public static String escaparCadena(String cadena) {
		String aux = "";
		if (cadena.contains("'")) {
			aux = cadena.replace("'", "&#39;");
		}
		return aux;
	}

	public static void pruebaFichaAsistencia() throws ParseException, ClassNotFoundException {
		// FechaUtil util = new FechaUtil();
		// ###################
		// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		// String inicio = "15/07/2017";
		// String fin = "15/07/2017";
		// Date dateInicio = formatter.parse(inicio);
		// Date dateFin = formatter.parse(fin);
		// System.out.println(dateFin.after(dateInicio));
		TalentoHumano sis = new TalentoHumano();
		// sis.imprimirFichaAsistencia("5099813", 8, 2017);
		// sis.imprimirFichaAsistencia("3827955", 8,2017);
		// sis.imprimirFichaAsistencia("426581", 2,2018);
		sis.imprimirFichaAsistencia("4347382", 2, 2018);
	}

	public static void consultaSqlServer() throws ClassNotFoundException, SQLException {
		// metodo creado en PersonalCont
		ConexionMssql conn = new ConexionMssql();
		try {
			conn.sentencia = conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM CrossChex.dbo.Checkinout WHERE Userid='3827955'");

			while (conn.resultado.next()) {
				System.out.println(conn.resultado.getString("CheckTime"));
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
			conn.conexion.close();
		}
	}

	public static void stringToOperation() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine interprete = manager.getEngineByName("js");
		try {
			String formula = "(SALARIO*X)/176";
			interprete.put("SALARIO", 2750000);
			interprete.put("X", 1.75);
			System.out.println("Resultado = " + interprete.eval(formula));
		} catch (ScriptException se) {
			se.printStackTrace();
		}
	}

	public static void imprimirDirferenciaPersonal() throws ClassNotFoundException, SQLException {
		ConexionMsAccess connms2 = new ConexionMsAccess();
		ConexionMsAccess connms = new ConexionMsAccess();
		try {
			connms.sentencia = connms.conexion.createStatement();
			String consulta = "SELECT Logid,Userid,CheckTime FROM Checkinout";
			connms.resultado = connms.sentencia.executeQuery(consulta);

			int cont = 0;
			int cont1 = 0;
			connms2.sentencia = connms2.conexion.createStatement();
			while (connms.resultado.next()) {
				String consulta2 = "SELECT Count(*) AS numero FROM Checkinout WHERE Userid='"
						+ connms.resultado.getString("Userid") + "' AND CheckTime='"
						+ connms.resultado.getString("CheckTime").substring(0, 19) + "'";
				connms2.resultado = connms2.sentencia.executeQuery(consulta2);
				while (connms2.resultado.next()) {
					if (connms2.resultado.getInt("numero") > 1) {
						cont1++;
						System.out.println(connms2.resultado.getInt("numero"));
						System.out.println(connms.resultado.getString("Userid") + " - "
								+ connms.resultado.getString("CheckTime").substring(0, 19));
						System.out.println(consulta2);
					} else {
					}
				}
				cont++;
			}
			System.out.println("DB ms: " + cont);
			System.out.println("DB duplicado: " + cont1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connms2.conexion.close();
			connms.conexion.close();
		}

	}

	public static void pruebaConexionRelojes() throws ClassNotFoundException, ParseException, IOException {
		InetAddress ping;
		String ip = "192.168.1.90";
		ping = InetAddress.getByName(ip);
		System.out.println();
		while (ping.isReachable(5000)) {
			System.out.println(ip + " - VERDE");
		}
	}

	public static void agregarEvaluaciones() throws ClassNotFoundException, SQLException {
		// lectura de archivo
		String csvFile = "C:\\Users\\yiyo\\Desktop\\Controlados_para imprimir\\comisionados.csv";
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
				System.out.println(reg[1] + " - " + reg[5] + " - " + reg[6] + " - " + reg[7]);
				cont++;
				// if (reg[1].toString().equals("3827955")) {
				// System.out.println("yiyo");
				// }

				String SQL = "INSERT INTO ficha_personal.personales_evaluaciones(personal_cedula,pereva_puntaje_total,pereva_nota,pereva_periodo)"
						+ " VALUES('" + reg[1].toString() + "'," + reg[5] + "," + reg[6] + ",'JUNIO/NOVIEMBRE 2016')";
				connpg.pstmt = connpg.conexion.prepareStatement(SQL);

				connpg.pstmt.executeUpdate();
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
