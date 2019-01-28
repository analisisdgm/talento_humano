package com.migraciones.talentoHumano.controladores;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import com.migraciones.talentoHumano.dataBases.ConexionMsAccess;
import com.migraciones.talentoHumano.dataBases.ConexionPostgresql;
import com.migraciones.talentoHumano.modelos.FaltaDisciplinaria;
import com.migraciones.talentoHumano.modelos.FichaAsistencia;
import com.migraciones.talentoHumano.modelos.JLDetalle;
import com.migraciones.talentoHumano.modelos.JPDetalle;
import com.migraciones.talentoHumano.modelos.JustificacionLista;
import com.migraciones.talentoHumano.modelos.JustificacionPersonal;
import com.migraciones.talentoHumano.modelos.Personal;
import com.migraciones.talentoHumano.modelos.Registro;
import com.migraciones.talentoHumano.utilities.Encrypt;
import com.migraciones.talentoHumano.utilities.FechaUtil;

public class PersonalCont extends AncestroCont {

	private Personal personal = new Personal();

	public ArrayList<Personal> getAll() throws ClassNotFoundException {
		ArrayList<Personal> listaPersonales = new ArrayList<Personal>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.personales_vista ORDER BY cedula");
			while (conn.resultado.next()) {
				Personal personal = new Personal();
				personal.setCedula(conn.resultado.getString("cedula"));
				personal.setNombres(conn.resultado.getString("nombres"));
				personal.setApellidos(conn.resultado.getString("apellidos"));
				personal.setFechaNacimiento(conn.resultado.getDate("fecha_nacimiento"));
				personal.setSexo(conn.resultado.getString("sexo"));
				personal.setEstadoCivil(conn.resultado.getString("estado_civil"));
				personal.setTelefonos(conn.resultado.getString("telefonos"));
				personal.setCorreo(conn.resultado.getString("correo"));
				personal.setDomicilio(conn.resultado.getString("domicilio"));
				personal.setObservacion(conn.resultado.getString("observacion"));
				personal.setEstado(conn.resultado.getString("estado"));

				personal.setCodCondicion(conn.resultado.getInt("cond_codigo"));
				personal.setCondicion(conn.resultado.getString("condicion"));
				personal.setCondObservacion(conn.resultado.getString("cond_observacion"));
				personal.setCondFechaIni(conn.resultado.getDate("cond_fecha_inicio"));

				personal.setCodDependencia(conn.resultado.getString("dep_codigo"));
				personal.setDependencia(conn.resultado.getString("dependencia"));
				personal.setDepObservacion(conn.resultado.getString("dep_observacion"));
				personal.setDepFechaIni(conn.resultado.getDate("dep_fecha_inicio"));

				personal.setCodOficina(conn.resultado.getString("ofi_codigo"));
				personal.setOficina(conn.resultado.getString("oficina"));
				personal.setOfiObservacion(conn.resultado.getString("ofi_observacion"));
				personal.setOfiFechaIni(conn.resultado.getDate("ofi_fecha_inicio"));

				personal.setCodHorario(conn.resultado.getInt("hor_codigo"));
				personal.setHorEntrada(conn.resultado.getTime("hor_entrada"));
				personal.setHorSalida(conn.resultado.getTime("hor_salida"));
				personal.setHorObservacion(conn.resultado.getString("hor_observacion"));
				personal.setHorFechaIni(conn.resultado.getDate("hor_fecha_inicio"));
				
				personal.setCategoriaCodigo(conn.resultado.getString("cat_codigo"));
				personal.setCategoria(conn.resultado.getString("categoria"));
				personal.setCatObservacion(conn.resultado.getString("categoria_observacion"));
				personal.setCatFechaInicio(conn.resultado.getDate("categoria_fecha_inicio"));				
				
				listaPersonales.add(personal);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPersonales;
	}

	// ##############################################
	// ##### PARA OBTENER PERSONAL POR ABC ########
	// ##############################################
	public ArrayList<Personal> getABC() throws ClassNotFoundException {
		ArrayList<Personal> listaPersonales = new ArrayList<Personal>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.personales_vista WHERE estado = 'A' ORDER BY nombres");
			while (conn.resultado.next()) {
				Personal personal = new Personal();
				personal.setCedula(conn.resultado.getString("cedula"));
				personal.setNombres(conn.resultado.getString("nombres"));
				personal.setApellidos(conn.resultado.getString("apellidos"));
				personal.setCondicion(conn.resultado.getString("condicion"));
				personal.setDependencia(conn.resultado.getString("dependencia"));
				personal.setHorEntrada(conn.resultado.getTime("hor_entrada"));
				personal.setHorSalida(conn.resultado.getTime("hor_salida"));
				listaPersonales.add(personal);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPersonales;
	}

	// ##### PARA OBTENER PERSONAL POR CONDICION ####
	public ArrayList<Personal> getPersonalCondicion(String codigo) throws ClassNotFoundException {
		ArrayList<Personal> listaPersonales = new ArrayList<Personal>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.personales_vista WHERE cond_codigo = '" + codigo
							+ "' AND estado = 'A' ORDER BY nombres, condicion");
			while (conn.resultado.next()) {
				Personal personal = new Personal();
				personal.setCedula(conn.resultado.getString("cedula"));
				personal.setNombres(conn.resultado.getString("nombres"));
				personal.setApellidos(conn.resultado.getString("apellidos"));
				personal.setCondicion(conn.resultado.getString("condicion"));
				personal.setDependencia(conn.resultado.getString("dependencia"));
				personal.setHorEntrada(conn.resultado.getTime("hor_entrada"));
				personal.setHorSalida(conn.resultado.getTime("hor_salida"));
				listaPersonales.add(personal);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPersonales;
	}

	// ##### PARA OBTENER TODOS LOS PERSONALES POR CONDICION ####
	public ArrayList<Personal> getAllPeronalCondicion(String codigo) throws ClassNotFoundException {
		ArrayList<Personal> listaPersonales = new ArrayList<Personal>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery(
					"SELECT * FROM ficha_personal.personales_vista WHERE estado = 'A' ORDER BY nombres,condicion");
			while (conn.resultado.next()) {
				Personal personal = new Personal();
				personal.setCedula(conn.resultado.getString("cedula"));
				personal.setNombres(conn.resultado.getString("nombres"));
				personal.setApellidos(conn.resultado.getString("apellidos"));
				personal.setCondicion(conn.resultado.getString("condicion"));
				personal.setDependencia(conn.resultado.getString("dependencia"));
				personal.setHorEntrada(conn.resultado.getTime("hor_entrada"));
				personal.setHorSalida(conn.resultado.getTime("hor_salida"));
				listaPersonales.add(personal);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPersonales;
	}

	// ##### PARA OBTENER PERSONAL POR DEPENDENCIA ####
	// Fala agregar las opciones
	public ArrayList<Personal> getPersonalDependencia(String codigo) throws ClassNotFoundException {
		ArrayList<Personal> listaPersonales = new ArrayList<Personal>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					// filtrar por campo dependencia
					.executeQuery("SELECT * FROM ficha_personal.personales_vista WHERE dep_codigo = '" + codigo
							+ "' AND estado = 'A' ORDER BY dependencia");
			while (conn.resultado.next()) {
				Personal personal = new Personal();
				personal.setCedula(conn.resultado.getString("cedula"));
				personal.setNombres(conn.resultado.getString("nombres"));
				personal.setApellidos(conn.resultado.getString("apellidos"));
				personal.setCondicion(conn.resultado.getString("condicion"));
				personal.setDependencia(conn.resultado.getString("dependencia"));
				personal.setHorEntrada(conn.resultado.getTime("hor_entrada"));
				personal.setHorSalida(conn.resultado.getTime("hor_salida"));
				listaPersonales.add(personal);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPersonales;
	}

	// ##### PARA OBTENER TODOS LOS PERSONALES POR DEPENDENCIA ####
	public ArrayList<Personal> getAllPeronalDependencia(String codigo) throws ClassNotFoundException {
		ArrayList<Personal> listaPersonales = new ArrayList<Personal>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.personales_vista WHERE estado = 'A' ORDER BY dependencia,nombres");
			while (conn.resultado.next()) {
				Personal personal = new Personal();
				personal.setCedula(conn.resultado.getString("cedula"));
				personal.setNombres(conn.resultado.getString("nombres"));
				personal.setApellidos(conn.resultado.getString("apellidos"));
				personal.setCondicion(conn.resultado.getString("condicion"));
				personal.setDependencia(conn.resultado.getString("dependencia"));
				personal.setHorEntrada(conn.resultado.getTime("hor_entrada"));
				personal.setHorSalida(conn.resultado.getTime("hor_salida"));
				listaPersonales.add(personal);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPersonales;
	}

	// ##### PARA OBTENER PERSONAL POR oficina ####
	// PASANDO PARAMETROS
	public ArrayList<Personal> getPersonalOficina(String codigo) throws ClassNotFoundException {
		ArrayList<Personal> listaPersonales = new ArrayList<Personal>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.personales_vista WHERE ofi_codigo = '" + codigo
							+ "' AND estado = 'A' ORDER BY nombres");
			while (conn.resultado.next()) {
				Personal personal = new Personal();
				personal.setCedula(conn.resultado.getString("cedula"));
				personal.setNombres(conn.resultado.getString("nombres"));
				personal.setApellidos(conn.resultado.getString("apellidos"));
				personal.setCondicion(conn.resultado.getString("condicion"));
				personal.setDependencia(conn.resultado.getString("dependencia"));
				personal.setHorEntrada(conn.resultado.getTime("hor_entrada"));
				personal.setHorSalida(conn.resultado.getTime("hor_salida"));
				personal.setOficina(conn.resultado.getString("oficina"));
				listaPersonales.add(personal);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPersonales;
	}

	// ##### PARA OBTENER PERSONAL POR oficina ####
	public ArrayList<Personal> getAllPersonalOficina(String codigo) throws ClassNotFoundException {
		ArrayList<Personal> listaPersonales = new ArrayList<Personal>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery(
					"SELECT * FROM ficha_personal.personales_vista WHERE estado = 'A' ORDER BY oficina,nombres");
			while (conn.resultado.next()) {
				Personal personal = new Personal();
				personal.setCedula(conn.resultado.getString("cedula"));
				personal.setNombres(conn.resultado.getString("nombres"));
				personal.setApellidos(conn.resultado.getString("apellidos"));
				personal.setCondicion(conn.resultado.getString("condicion"));
				personal.setDependencia(conn.resultado.getString("dependencia"));
				personal.setHorEntrada(conn.resultado.getTime("hor_entrada"));
				personal.setHorSalida(conn.resultado.getTime("hor_salida"));
				personal.setOficina(conn.resultado.getString("oficina"));
				listaPersonales.add(personal);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPersonales;
	}

	// ##### PARA OBTENER PERSONAL POR sexo ####
	public ArrayList<Personal> getPeronalporSexo(String codigo) throws ClassNotFoundException {
		ArrayList<Personal> listaPersonales = new ArrayList<Personal>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery("SELECT * FROM ficha_personal.personales_vista WHERE sexo = '"
					+ codigo + "' AND estado = 'A' ORDER BY nombres");
			while (conn.resultado.next()) {
				Personal personal = new Personal();
				personal.setCedula(conn.resultado.getString("cedula"));
				personal.setNombres(conn.resultado.getString("nombres"));
				personal.setApellidos(conn.resultado.getString("apellidos"));
				personal.setCondicion(conn.resultado.getString("condicion"));
				personal.setDependencia(conn.resultado.getString("dependencia"));
				personal.setHorEntrada(conn.resultado.getTime("hor_entrada"));
				personal.setHorSalida(conn.resultado.getTime("hor_salida"));
				personal.setSexo(conn.resultado.getString("sexo"));
				listaPersonales.add(personal);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPersonales;
	}

	// ##### PARA OBTENER PERSONAL POR sexo ####
	public ArrayList<Personal> getAllPeronalporSexo(String codigo) throws ClassNotFoundException {
		ArrayList<Personal> listaPersonales = new ArrayList<Personal>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.personales_vista WHERE estado = 'A' ORDER BY sexo,nombres");
			while (conn.resultado.next()) {
				Personal personal = new Personal();
				personal.setCedula(conn.resultado.getString("cedula"));
				personal.setNombres(conn.resultado.getString("nombres"));
				personal.setApellidos(conn.resultado.getString("apellidos"));
				personal.setCondicion(conn.resultado.getString("condicion"));
				personal.setDependencia(conn.resultado.getString("dependencia"));
				personal.setHorEntrada(conn.resultado.getTime("hor_entrada"));
				personal.setHorSalida(conn.resultado.getTime("hor_salida"));
				personal.setSexo(conn.resultado.getString("sexo"));
				listaPersonales.add(personal);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPersonales;
	}

	// ##### PARA OBTENER PERSONAL POR CATEGORIA PANSANDOLE UN PARAMETRO DE
	// BUSQUEDA ####
	public ArrayList<Personal> getPeronalCategoria(String codigo) throws ClassNotFoundException {
		ArrayList<Personal> listaPersonales = new ArrayList<Personal>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.personales_vista WHERE cat_codigo = '" + codigo
							+ "' AND estado = 'A' ORDER BY nombres");
			while (conn.resultado.next()) {
				Personal personal = new Personal();
				personal.setCedula(conn.resultado.getString("cedula"));
				personal.setNombres(conn.resultado.getString("nombres"));
				personal.setApellidos(conn.resultado.getString("apellidos"));
				personal.setCondicion(conn.resultado.getString("condicion"));
				personal.setDependencia(conn.resultado.getString("dependencia"));
				personal.setHorEntrada(conn.resultado.getTime("hor_entrada"));
				personal.setHorSalida(conn.resultado.getTime("hor_salida"));
				personal.setCategoria(conn.resultado.getString("categoria"));
				personal.setCategoriaCodigo(conn.resultado.getString("cat_codigo"));
				listaPersonales.add(personal);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPersonales;
	}

	// ##### PARA OBTENER PERSONAL POR TODAS LAS CATEGORIAS ####
	public ArrayList<Personal> getAllPeronalCategoria() throws ClassNotFoundException {
		ArrayList<Personal> listaPersonales = new ArrayList<Personal>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.personales_vista WHERE estado = 'A' ORDER BY nombres");
			while (conn.resultado.next()) {
				Personal personal = new Personal();
				personal.setCedula(conn.resultado.getString("cedula"));
				personal.setNombres(conn.resultado.getString("nombres"));
				personal.setApellidos(conn.resultado.getString("apellidos"));
				personal.setCondicion(conn.resultado.getString("condicion"));
				personal.setDependencia(conn.resultado.getString("dependencia"));
				personal.setHorEntrada(conn.resultado.getTime("hor_entrada"));
				personal.setHorSalida(conn.resultado.getTime("hor_salida"));
				personal.setCategoria(conn.resultado.getString("categoria"));
				personal.setCategoriaCodigo(conn.resultado.getString("cat_codigo"));
				listaPersonales.add(personal);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPersonales;
	}

	public Personal getByCedula(String cedula) throws ClassNotFoundException {
		Personal personal = new Personal();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.personales_vista WHERE cedula='" + cedula + "'");
			while (conn.resultado.next()) {
				personal.setCedula(conn.resultado.getString("cedula"));
				personal.setNombres(conn.resultado.getString("nombres"));
				personal.setApellidos(conn.resultado.getString("apellidos"));
				personal.setFechaNacimiento(conn.resultado.getDate("fecha_nacimiento"));
				personal.setSexo(conn.resultado.getString("sexo"));
				personal.setEstadoCivil(conn.resultado.getString("estado_civil"));
				personal.setTelefonos(conn.resultado.getString("telefonos"));
				personal.setCorreo(conn.resultado.getString("correo"));
				personal.setDomicilio(conn.resultado.getString("domicilio"));
				personal.setObservacion(conn.resultado.getString("observacion"));
				personal.setEstado(conn.resultado.getString("estado"));

				personal.setCodCondicion(conn.resultado.getInt("cond_codigo"));
				personal.setCondicion(conn.resultado.getString("condicion"));
				personal.setCondObservacion(conn.resultado.getString("cond_observacion"));
				personal.setCondFechaIni(conn.resultado.getDate("cond_fecha_inicio"));

				personal.setCodDependencia(conn.resultado.getString("dep_codigo"));
				personal.setDependencia(conn.resultado.getString("dependencia"));
				personal.setDepObservacion(conn.resultado.getString("dep_observacion"));
				personal.setDepFechaIni(conn.resultado.getDate("dep_fecha_inicio"));

				personal.setCodOficina(conn.resultado.getString("ofi_codigo"));
				personal.setOficina(conn.resultado.getString("oficina"));
				personal.setOfiObservacion(conn.resultado.getString("ofi_observacion"));
				personal.setOfiFechaIni(conn.resultado.getDate("ofi_fecha_inicio"));

				personal.setCodHorario(conn.resultado.getInt("hor_codigo"));
				personal.setHorEntrada(conn.resultado.getTime("hor_entrada"));
				personal.setHorSalida(conn.resultado.getTime("hor_salida"));
				personal.setHorObservacion(conn.resultado.getString("hor_observacion"));
				personal.setHorFechaIni(conn.resultado.getDate("hor_fecha_inicio"));

				personal.setCodCargo(conn.resultado.getInt("cargo_id"));
				personal.setCargo(conn.resultado.getString("cargo"));
				personal.setCarObservacion(conn.resultado.getString("cargo_observacion"));
				personal.setCarFechaIni(conn.resultado.getDate("cargo_fecha_inicio"));
				
				personal.setCategoriaCodigo(conn.resultado.getString("cat_codigo"));
				personal.setCategoria(conn.resultado.getString("categoria"));
				personal.setCatObservacion(conn.resultado.getString("categoria_observacion"));
				personal.setCatFechaInicio(conn.resultado.getDate("categoria_fecha_inicio"));
				
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personal;
	}

	public ArrayList<Personal> getByOficina(String codigo) throws ClassNotFoundException {
		ArrayList<Personal> listaPersonales = new ArrayList<Personal>();
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM ficha_personal.personales_vista WHERE ofi_codigo='" + codigo
							+ "' ORDER BY apellidos");
			while (conn.resultado.next()) {
				Personal personal = new Personal();
				personal.setCedula(conn.resultado.getString("cedula"));
				personal.setNombres(conn.resultado.getString("nombres"));
				personal.setApellidos(conn.resultado.getString("apellidos"));
				personal.setFechaNacimiento(conn.resultado.getDate("fecha_nacimiento"));
				personal.setSexo(conn.resultado.getString("sexo"));
				personal.setEstadoCivil(conn.resultado.getString("estado_civil"));
				personal.setTelefonos(conn.resultado.getString("telefonos"));
				personal.setCorreo(conn.resultado.getString("correo"));
				personal.setDomicilio(conn.resultado.getString("domicilio"));
				personal.setObservacion(conn.resultado.getString("observacion"));
				personal.setEstado(conn.resultado.getString("estado"));

				personal.setCodCondicion(conn.resultado.getInt("cond_codigo"));
				personal.setCondicion(conn.resultado.getString("condicion"));
				personal.setCondObservacion(conn.resultado.getString("cond_observacion"));
				personal.setCondFechaIni(conn.resultado.getDate("cond_fecha_inicio"));

				personal.setCodDependencia(conn.resultado.getString("dep_codigo"));
				personal.setDependencia(conn.resultado.getString("dependencia"));
				personal.setDepObservacion(conn.resultado.getString("dep_observacion"));
				personal.setDepFechaIni(conn.resultado.getDate("dep_fecha_inicio"));

				personal.setCodOficina(conn.resultado.getString("ofi_codigo"));
				personal.setOficina(conn.resultado.getString("oficina"));
				personal.setOfiObservacion(conn.resultado.getString("ofi_observacion"));
				personal.setOfiFechaIni(conn.resultado.getDate("ofi_fecha_inicio"));

				personal.setCodHorario(conn.resultado.getInt("hor_codigo"));
				personal.setHorEntrada(conn.resultado.getTime("hor_entrada"));
				personal.setHorSalida(conn.resultado.getTime("hor_salida"));
				personal.setHorObservacion(conn.resultado.getString("hor_observacion"));
				personal.setHorFechaIni(conn.resultado.getDate("hor_fecha_inicio"));
				listaPersonales.add(personal);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPersonales;

	}

	public ArrayList<FaltaDisciplinaria> getInasistenciaSinJustificar(Date fechaInicio, Date fechaFin)
			throws ClassNotFoundException {
		ArrayList<FaltaDisciplinaria> listaFaltas = new ArrayList<FaltaDisciplinaria>();
		try {
			FechaUtil utilFecha = new FechaUtil();
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery("SELECT " + "pv.nombres ||' '|| pv.apellidos AS funcionario,"
					+ "ina.p_cedula AS cedula," + "ina.r_fecha AS fecha,"
					+ "'INASISTENCIA SIN JUSTIFICAR'::varchar AS falta"
					+ " FROM control_asistencia.obtener_inasistencias_sin_justificar('','HAB','" + fechaInicio + "','"
					+ fechaFin + "') ina"
					+ "  LEFT JOIN ficha_personal.personales_vista pv ON ina.p_cedula=pv.cedula ORDER BY p_cedula,r_fecha");
			while (conn.resultado.next()) {
				FaltaDisciplinaria falta = new FaltaDisciplinaria();
				falta.setPeriodo(utilFecha.getDateToString(fechaInicio) + " - " + utilFecha.getDateToString(fechaFin));
				falta.setCedula(conn.resultado.getString("cedula"));
				falta.setNombres(conn.resultado.getString("funcionario"));
				falta.setFecha(conn.resultado.getDate("fecha"));
				falta.setDescripcion(conn.resultado.getString("falta"));
				listaFaltas.add(falta);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaFaltas;
	}

	public ArrayList<FaltaDisciplinaria> getInasistenciaSinJustificar(int mes, int anho) throws ClassNotFoundException {
		ArrayList<FaltaDisciplinaria> listaFaltas = new ArrayList<FaltaDisciplinaria>();
		try {
			FechaUtil utilFecha = new FechaUtil();
			ArrayList<String> calendario = utilFecha.getCalendarioMes(mes, anho);

			Date fechaInicio = utilFecha.getStringToDate(calendario.get(0)),
					fechaFin = utilFecha.getStringToDate(calendario.get(calendario.size() - 1));
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery("SELECT " + "pv.nombres ||' '|| pv.apellidos AS funcionario,"
					+ "ina.p_cedula AS cedula," + "ina.r_fecha AS fecha,"
					+ "'INASISTENCIA SIN JUSTIFICAR'::varchar AS falta"
					+ " FROM control_asistencia.obtener_inasistencias_sin_justificar('','HAB','" + fechaInicio + "','"
					+ fechaFin + "') ina"
					+ "  LEFT JOIN ficha_personal.personales_vista pv ON ina.p_cedula=pv.cedula ORDER BY p_cedula,r_fecha");
			while (conn.resultado.next()) {
				FaltaDisciplinaria falta = new FaltaDisciplinaria();
				falta.setPeriodo(utilFecha.getMes(mes) + " / " + Integer.toString(anho));
				falta.setCedula(conn.resultado.getString("cedula"));
				falta.setNombres(conn.resultado.getString("funcionario"));
				falta.setFecha(conn.resultado.getDate("fecha"));
				falta.setDescripcion(conn.resultado.getString("falta"));
				listaFaltas.add(falta);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaFaltas;
	}

	public ArrayList<FaltaDisciplinaria> getInasistenciaSinJustificarByCedula(String cedula, int mes, int anho)
			throws ClassNotFoundException {
		ArrayList<FaltaDisciplinaria> listaFaltas = new ArrayList<FaltaDisciplinaria>();
		try {
			FechaUtil utilFecha = new FechaUtil();
			ArrayList<String> calendario = utilFecha.getCalendarioMes(mes, anho);

			Date fechaInicio = utilFecha.getStringToDate(calendario.get(0)),
					fechaFin = utilFecha.getStringToDate(calendario.get(calendario.size() - 1));
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			String sql = "SELECT " + "pv.nombres ||' '|| pv.apellidos AS funcionario," + "ina.p_cedula AS cedula,"
					+ "ina.r_fecha AS fecha," + "'INASISTENCIA SIN JUSTIFICAR'::varchar AS falta"
					+ " FROM control_asistencia.obtener_inasistencias_sin_justificar('" + cedula + "','HAB','"
					+ fechaInicio + "','" + fechaFin + "') ina"
					+ "  LEFT JOIN ficha_personal.personales_vista pv ON ina.p_cedula=pv.cedula ORDER BY p_cedula,r_fecha";
			conn.resultado = conn.sentencia.executeQuery(sql);
			while (conn.resultado.next()) {
				FaltaDisciplinaria falta = new FaltaDisciplinaria();
				falta.setPeriodo(utilFecha.getMes(mes) + " / " + Integer.toString(anho));
				falta.setCedula(conn.resultado.getString("cedula"));
				falta.setNombres(conn.resultado.getString("funcionario"));
				falta.setFecha(conn.resultado.getDate("fecha"));
				falta.setDescripcion(conn.resultado.getString("falta"));
				listaFaltas.add(falta);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaFaltas;
	}

	public ArrayList<FaltaDisciplinaria> getInasistenciaSinJustificarByCedula(String cedula, Date fechaInicio,
			Date fechaFin) throws ClassNotFoundException {
		ArrayList<FaltaDisciplinaria> listaFaltas = new ArrayList<FaltaDisciplinaria>();
		try {
			FechaUtil utilFecha = new FechaUtil();
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			String sql = "SELECT " + "pv.nombres ||' '|| pv.apellidos AS funcionario," + "ina.p_cedula AS cedula,"
					+ "ina.r_fecha AS fecha," + "'INASISTENCIA SIN JUSTIFICAR'::varchar AS falta"
					+ " FROM control_asistencia.obtener_inasistencias_sin_justificar('" + cedula + "','HAB','"
					+ fechaInicio + "','" + fechaFin + "') ina"
					+ "  LEFT JOIN ficha_personal.personales_vista pv ON ina.p_cedula=pv.cedula ORDER BY p_cedula,r_fecha";
			conn.resultado = conn.sentencia.executeQuery(sql);
			while (conn.resultado.next()) {
				FaltaDisciplinaria falta = new FaltaDisciplinaria();
				falta.setPeriodo(utilFecha.getDateToString(fechaInicio) + " - " + utilFecha.getDateToString(fechaFin));
				falta.setCedula(conn.resultado.getString("cedula"));
				falta.setNombres(conn.resultado.getString("funcionario"));
				falta.setFecha(conn.resultado.getDate("fecha"));
				falta.setDescripcion(conn.resultado.getString("falta"));
				listaFaltas.add(falta);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaFaltas;
	}

	public JustificacionPersonal getHistorial(String cedula, int mes, int anho) throws ClassNotFoundException {
		JustificacionPersonal justificacion = new JustificacionPersonal();
		FechaUtil util = new FechaUtil();
		ArrayList<String> calendario = util.getCalendarioMes(mes, anho);
		Date inicio = util.getStringToDate(calendario.get(0)),
				fin = util.getStringToDate(calendario.get(calendario.size() - 1));
		Personal per = new Personal();
		per = getByCedula(cedula);
		// cargar cabecera de la ficha de justificaciones
		justificacion.setPeriodo(util.getMes(mes) + " / " + Integer.toString(anho));
		justificacion.setCedula(per.getCedula());
		justificacion.setNombres(per.getNombres());
		justificacion.setApellidos(per.getApellidos());
		justificacion.setEstado(per.getEstado());

		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			String sql = "SELECT * FROM ficha_personal.historial_disciplinario WHERE personal_cedula='" + cedula
					+ "' AND histodisp_fecha_desde BETWEEN '" + inicio + "' AND '" + fin
					+ "' AND histodisp_estado='A' ORDER BY histodisp_fecha_desde ASC";
			conn.resultado = conn.sentencia.executeQuery(sql);
			while (conn.resultado.next()) {
				if (!justificacion.isTieneRegistros()) {
					justificacion.setTieneRegistros(true);
				}
				JPDetalle detalle = new JPDetalle();
				detalle.setFechaDesde(util.getDateToString(conn.resultado.getDate("histodisp_fecha_desde")));
				detalle.setFechaHasta(util.getDateToString(conn.resultado.getDate("histodisp_fecha_hasta")));
				detalle.setDescripcion(conn.resultado.getString("histodisp_motivo"));
				justificacion.addDetalle(detalle);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return justificacion;
	}

	public JustificacionPersonal getHistorial(String cedula, Date inicio, Date fin) throws ClassNotFoundException {
		JustificacionPersonal justificacion = new JustificacionPersonal();
		FechaUtil util = new FechaUtil();
		Personal per = new Personal();
		per = getByCedula(cedula);
		// cargar cabecera de la ficha de justificaciones
		justificacion.setPeriodo(util.getDateToString(inicio) + " - " + util.getDateToString(fin));
		justificacion.setCedula(per.getCedula());
		justificacion.setNombres(per.getNombres());
		justificacion.setApellidos(per.getApellidos());
		justificacion.setEstado(per.getEstado());

		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			String sql = "SELECT * FROM ficha_personal.historial_disciplinario WHERE personal_cedula='" + cedula
					+ "' AND histodisp_fecha_desde BETWEEN '" + inicio + "' AND '" + fin
					+ "' AND histodisp_estado='A' ORDER BY histodisp_fecha_desde ASC";
			conn.resultado = conn.sentencia.executeQuery(sql);
			while (conn.resultado.next()) {
				if (!justificacion.isTieneRegistros()) {
					justificacion.setTieneRegistros(true);
				}
				JPDetalle detalle = new JPDetalle();
				detalle.setFechaDesde(util.getDateToString(conn.resultado.getDate("histodisp_fecha_desde")));
				detalle.setFechaHasta(util.getDateToString(conn.resultado.getDate("histodisp_fecha_hasta")));
				detalle.setDescripcion(conn.resultado.getString("histodisp_motivo"));
				justificacion.addDetalle(detalle);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return justificacion;
	}

	public JustificacionLista getHistorialByOficina(String oficina, Date inicio, Date fin)
			throws ClassNotFoundException {
		JustificacionLista justificacion = new JustificacionLista();
		FechaUtil util = new FechaUtil();

		// cargar cabecera de la ficha de justificaciones
		justificacion.setPeriodo(util.getDateToString(inicio) + " - " + util.getDateToString(fin));

		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			String sql = "SELECT personal_cedula, nombres ||' '|| apellidos AS funcionario, "
					+ "histodisp_fecha_desde, histodisp_fecha_hasta, histodisp_motivo, ofi_codigo, oficina "
					+ "FROM ficha_personal.historial_disciplinario hd "
					+ "LEFT JOIN ficha_personal.personales_vista pv ON hd.personal_cedula=pv.cedula "
					+ "WHERE ofi_codigo='" + oficina + "' AND histodisp_fecha_desde BETWEEN '" + inicio + "' AND '"
					+ fin + "' AND histodisp_estado='A' ORDER BY funcionario,histodisp_fecha_desde ASC";
			conn.resultado = conn.sentencia.executeQuery(sql);
			while (conn.resultado.next()) {
				if (!justificacion.isTieneRegistros()) {
					justificacion.setTieneRegistros(true);
					justificacion.setOficina(conn.resultado.getString("oficina"));
				}
				JLDetalle detalle = new JLDetalle();
				detalle.setCedula(conn.resultado.getString("personal_cedula"));
				detalle.setFuncionario(conn.resultado.getString("funcionario"));
				detalle.setFechaDesde(util.getDateToString(conn.resultado.getDate("histodisp_fecha_desde")));
				detalle.setFechaHasta(util.getDateToString(conn.resultado.getDate("histodisp_fecha_hasta")));
				detalle.setDescripcion(conn.resultado.getString("histodisp_motivo"));
				justificacion.addDetalle(detalle);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return justificacion;
	}

	public JustificacionLista getHistorialByOficina(String oficina, int mes, int anho) throws ClassNotFoundException {
		JustificacionLista justificacion = new JustificacionLista();
		FechaUtil util = new FechaUtil();
		ArrayList<String> calendario = util.getCalendarioMes(mes, anho);
		Date inicio = util.getStringToDate(calendario.get(0)),
				fin = util.getStringToDate(calendario.get(calendario.size() - 1));
		// cargar cabecera de la ficha de justificaciones
		justificacion.setPeriodo(util.getMes(mes) + " / " + Integer.toString(anho));

		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			String sql = "SELECT personal_cedula, nombres ||' '|| apellidos AS funcionario, "
					+ "histodisp_fecha_desde, histodisp_fecha_hasta, histodisp_motivo, ofi_codigo, oficina "
					+ "FROM ficha_personal.historial_disciplinario hd "
					+ "LEFT JOIN ficha_personal.personales_vista pv ON hd.personal_cedula=pv.cedula "
					+ "WHERE ofi_codigo='" + oficina + "' AND histodisp_fecha_desde BETWEEN '" + inicio + "' AND '"
					+ fin + "' AND histodisp_estado='A' ORDER BY funcionario,histodisp_fecha_desde ASC";
			conn.resultado = conn.sentencia.executeQuery(sql);
			while (conn.resultado.next()) {
				if (!justificacion.isTieneRegistros()) {
					justificacion.setTieneRegistros(true);
					justificacion.setOficina(conn.resultado.getString("oficina"));
				}
				JLDetalle detalle = new JLDetalle();
				detalle.setCedula(conn.resultado.getString("personal_cedula"));
				detalle.setFuncionario(conn.resultado.getString("funcionario"));
				detalle.setFechaDesde(util.getDateToString(conn.resultado.getDate("histodisp_fecha_desde")));
				detalle.setFechaHasta(util.getDateToString(conn.resultado.getDate("histodisp_fecha_hasta")));
				detalle.setDescripcion(conn.resultado.getString("histodisp_motivo"));
				justificacion.addDetalle(detalle);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return justificacion;
	}

	public FichaAsistencia getFichaMensual(String cedula, int mes, int anho)
			throws ClassNotFoundException, ParseException {
		FichaAsistencia ficha = new FichaAsistencia(cedula, mes, anho);
		Personal per = new Personal();
		per = getByCedula(cedula);
		// cargar cabecera de la ficha de asistencia
		ficha.setCedula(per.getCedula());
		ficha.setNombres(per.getNombres());
		ficha.setApellidos(per.getApellidos());
		ficha.setFechaNacimiento(per.getFechaNacimiento());
		ficha.setEstado(per.getEstado());
		ficha.setCondicion(per.getCondicion());
		ficha.setDependencia(per.getDependencia());
		ficha.setOficina(per.getOficina());
		ficha.setCodigoOficina(per.getCodOficina());
		ficha.setCargo(per.getCargo());
		ficha.setCodigoCargo(Integer.toString(per.getCodCargo()));
		ficha.setTurnoEntrada(per.getHorEntrada());
		ficha.setTurnoSalida(per.getHorSalida());
		String horario = per.getHorEntrada().toString().substring(0, 5) + " - "
				+ per.getHorSalida().toString().substring(0, 5);
		ficha.setHorario(horario);
		// cargar detalle de la ficha de asistencia
		try {
			try {
				ConexionPostgresql conn = new ConexionPostgresql();
				conn.sentencia = (Statement) conn.conexion.createStatement();
				conn.resultado = conn.sentencia
						.executeQuery("SELECT * FROM control_asistencia.registros_vista WHERE cedula='" + cedula
								+ "' AND extract(month from fecha)=" + mes + " AND extract(year from fecha)=" + anho);
				while (conn.resultado.next()) {
					if (!ficha.isTieneRegistros()) {
						ficha.setTieneRegistros(true);
					}
					Date fecha = conn.resultado.getDate("fecha");
					String registro = conn.resultado.getString("registro");
					String tipo = conn.resultado.getString("tipo");
					ficha.addRegistro(fecha, registro, tipo);
				}
				conn.conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ficha.calcularDatos();
		return ficha;
	}

	public FichaAsistencia getFichaRango(String cedula, Date fechaInicio, Date fechaFin)
			throws ClassNotFoundException, ParseException {
		FichaAsistencia ficha = new FichaAsistencia(cedula, fechaInicio, fechaFin);
		Personal per = new Personal();
		per = getByCedula(cedula);
		// cargar cabecera de la ficha de asistencia
		ficha.setCedula(per.getCedula());
		ficha.setNombres(per.getNombres());
		ficha.setApellidos(per.getApellidos());
		ficha.setFechaNacimiento(per.getFechaNacimiento());
		ficha.setEstado(per.getEstado());
		ficha.setCondicion(per.getCondicion());
		ficha.setDependencia(per.getDependencia());
		ficha.setOficina(per.getOficina());
		ficha.setCodigoOficina(per.getCodOficina());
		ficha.setCargo(per.getCargo());
		ficha.setCodigoCargo(Integer.toString(per.getCodCargo()));
		ficha.setTurnoEntrada(per.getHorEntrada());
		ficha.setTurnoSalida(per.getHorSalida());
		String horario = per.getHorEntrada().toString().substring(0, 5) + " - "
				+ per.getHorSalida().toString().substring(0, 5);
		ficha.setHorario(horario);
		// cargar detalle de la ficha de asistencia
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT * FROM control_asistencia.registros_vista WHERE cedula='" + cedula
							+ "' AND fecha>='" + fechaInicio + "' AND fecha<='" + fechaFin + "'");
			while (conn.resultado.next()) {
				if (!ficha.isTieneRegistros()) {
					ficha.setTieneRegistros(true);
				}
				Date fecha = conn.resultado.getDate("fecha");
				String registro = conn.resultado.getString("registro");
				String tipo = conn.resultado.getString("tipo");
				ficha.addRegistro(fecha, registro, tipo);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ficha.calcularDatos();
		return ficha;
	}

	public ArrayList<Registro> getRegistroFecha(String cedula, Date fecha) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		String consulta = "SELECT * FROM control_asistencia.registros_entradas_salidas" + " WHERE personal_cedula='"
				+ cedula + "' AND DATE(reg_registro)='" + fecha + "' ORDER BY reg_registro";
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia.executeQuery(consulta);
			while (conn.resultado.next()) {
				Registro reg = new Registro();
				reg.setId(conn.resultado.getInt("reg_id"));
				reg.setRegistro(conn.resultado.getTimestamp("reg_registro"));
				reg.setRelojCodigo(Integer.toString(conn.resultado.getInt("reloj_id")));
				reg.setTipoRegistro(conn.resultado.getString("tipo_registro"));
				registros.add(reg);
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return registros;
	}

	public boolean agregarNuevoPersonal(Personal nuevoPersonal) throws ClassNotFoundException, SQLException {
		ConexionPostgresql conn = new ConexionPostgresql();
		conn.conexion.setAutoCommit(false);
		PreparedStatement pstmtPersonal = null, pstmtCondicion = null, pstmtDependencia = null, pstmtOficina = null,
				pstmtInsHorario = null, pstmtUpdHorario = null, pstmtCargo = null, pstmtCargoCredencial = null,
				pstmtAdmin = null, pstmtCategoria = null;

		try {
			// insertar en tabla ficha_personal.personales
			String iPersonalSQL = "INSERT INTO ficha_personal.personales(pers_nombre,pers_apellido,pers_cedula_nro,pers_fecha_nacimiento,pers_sexo,pers_estado_civil,pers_telefonos,pers_observacion,pers_correo,pers_domicilio) VALUES(?,?,?,?,?,?,?,?,?,?)";
			pstmtPersonal = conn.conexion.prepareStatement(iPersonalSQL);
			pstmtPersonal.setString(1, nuevoPersonal.getNombres());
			pstmtPersonal.setString(2, nuevoPersonal.getApellidos());
			pstmtPersonal.setString(3, nuevoPersonal.getCedula());
			java.sql.Date sqlDate = new java.sql.Date(nuevoPersonal.getFechaNacimiento().getTime());
			pstmtPersonal.setDate(4, sqlDate);
			pstmtPersonal.setString(5, nuevoPersonal.getSexo());
			pstmtPersonal.setString(6, nuevoPersonal.getEstadoCivil());
			pstmtPersonal.setString(7, nuevoPersonal.getTelefonos());
			pstmtPersonal.setString(8, nuevoPersonal.getObservacion());
			pstmtPersonal.setString(9, nuevoPersonal.getCorreo());
			pstmtPersonal.setString(10, nuevoPersonal.getDomicilio());
			// a eliminar una vez implementada la nueva consulta y reporte
//			pstmtPersonal.setInt(11, nuevoPersonal.getCodCondicion());
//			pstmtPersonal.setInt(12, getIdDependencia(nuevoPersonal.getCodDependencia()));
//			pstmtPersonal.setInt(13, getIdOficina(nuevoPersonal.getCodOficina()));
			pstmtPersonal.executeUpdate();

			// insertar en tabla ficha_personal.personales_tipos
			String iCondicionSQL = "INSERT INTO ficha_personal.personales_tipos(personal_cedula,tipo_personal_id,pertip_fecha_inicio,admin_login,pertip_observacion) VALUES(?,?,?,?,?)";
			pstmtCondicion = conn.conexion.prepareStatement(iCondicionSQL);
			pstmtCondicion.setString(1, nuevoPersonal.getCedula());
			pstmtCondicion.setInt(2, nuevoPersonal.getCodCondicion());
			sqlDate = new java.sql.Date(nuevoPersonal.getCondFechaIni().getTime());
			pstmtCondicion.setDate(3, sqlDate);
			pstmtCondicion.setString(4, nuevoPersonal.getAdministradorAlta());
			pstmtCondicion.setString(5, nuevoPersonal.getCondObservacion());
			pstmtCondicion.executeUpdate();

			// insertar en tabla ficha_personal.personales_dependencias
			String iDependenciaSQL = "INSERT INTO ficha_personal.personales_dependencias(personal_cedula,dependencia_id,perdep_fecha_inicio,admin_login,perdep_observacion) VALUES(?,?,?,?,?)";
			pstmtDependencia = conn.conexion.prepareStatement(iDependenciaSQL);
			pstmtDependencia.setString(1, nuevoPersonal.getCedula());
			pstmtDependencia.setInt(2, getIdDependencia(nuevoPersonal.getCodDependencia()));
			sqlDate = new java.sql.Date(nuevoPersonal.getDepFechaIni().getTime());
			pstmtDependencia.setDate(3, sqlDate);
			pstmtDependencia.setString(4, nuevoPersonal.getAdministradorAlta());
			pstmtDependencia.setString(5, nuevoPersonal.getDepObservacion());
			pstmtDependencia.executeUpdate();

			// insertar en tabla ficha_personal.personales_oficinas
			String iOficinaSQL = "INSERT INTO ficha_personal.personales_oficinas(personal_cedula,oficina_id,perofi_fecha_inicio,admin_login,perofi_observacion) VALUES(?,?,?,?,?)";
			pstmtOficina = conn.conexion.prepareStatement(iOficinaSQL);
			pstmtOficina.setString(1, nuevoPersonal.getCedula());
			pstmtOficina.setInt(2, getIdOficina(nuevoPersonal.getCodOficina()));
			sqlDate = new java.sql.Date(nuevoPersonal.getOfiFechaIni().getTime());
			pstmtOficina.setDate(3, sqlDate);
			pstmtOficina.setString(4, nuevoPersonal.getAdministradorAlta());
			pstmtOficina.setString(5, nuevoPersonal.getOfiObservacion());
			pstmtOficina.executeUpdate();

			// insertar en tabla ficha_personal.personales_categorias
			String iCategoriaSQL = "INSERT INTO ficha_personal.personales_categorias(personal_cedula,categoria_id,percat_fecha_inicio,admin_login,percat_observacion) VALUES(?,?,?,?,?)";
			pstmtCategoria = conn.conexion.prepareStatement(iCategoriaSQL);
			pstmtCategoria.setString(1, nuevoPersonal.getCedula());
			pstmtCategoria.setInt(2, getIdCategoria(nuevoPersonal.getCategoriaCodigo()));
			sqlDate = new java.sql.Date(nuevoPersonal.getCatFechaInicio().getTime());
			pstmtCategoria.setDate(3, sqlDate);
			pstmtCategoria.setString(4, nuevoPersonal.getAdministradorAlta());
			pstmtCategoria.setString(5, nuevoPersonal.getCatObservacion());
			pstmtCategoria.executeUpdate();

			// insertar en tabla control_asistencia.personales_turnos
			String iHorarioSQL = "INSERT INTO control_asistencia.personales_turnos(personal_cedula,turno_id,pers_fecha_inicio,admin_login,pers_observacion) VALUES(?,?,?,?,?)";
			pstmtInsHorario = conn.conexion.prepareStatement(iHorarioSQL);
			pstmtInsHorario.setString(1, nuevoPersonal.getCedula());
			pstmtInsHorario.setInt(2, nuevoPersonal.getCodHorario());
			sqlDate = new java.sql.Date(nuevoPersonal.getHorFechaIni().getTime());
			pstmtInsHorario.setDate(3, sqlDate);
			pstmtInsHorario.setString(4, nuevoPersonal.getAdministradorAlta());
			pstmtInsHorario.setString(5, nuevoPersonal.getHorObservacion());
			pstmtInsHorario.executeUpdate();
			String uHorarioSQL = "UPDATE control_asistencia.personales_turnos SET personal_id=(SELECT id FROM control_asistencia.personales_turnos WHERE personal_cedula='"
					+ nuevoPersonal.getCedula() + "' AND horario_estado='A')WHERE personal_cedula='"
					+ nuevoPersonal.getCedula() + "'";
			pstmtUpdHorario = conn.conexion.prepareStatement(uHorarioSQL);
			pstmtUpdHorario.executeUpdate();

			// insertar en tabla ficha_personal.personales_cargos
			String iCargoSQL = "INSERT INTO ficha_personal.personales_cargos(personal_cedula,cargo_id,percar_fecha_inicio,admin_login,percar_observacion) VALUES(?,?,?,?,?)";
			pstmtCargo = conn.conexion.prepareStatement(iCargoSQL);
			pstmtCargo.setString(1, nuevoPersonal.getCedula());
			// modificacion pendiente a la carga de cargos
			pstmtCargo.setInt(2, nuevoPersonal.getCodCargo());
			sqlDate = new java.sql.Date(nuevoPersonal.getCarFechaIni().getTime());
			pstmtCargo.setDate(3, sqlDate);
			pstmtCargo.setString(4, nuevoPersonal.getAdministradorAlta());
			pstmtCargo.setString(5, nuevoPersonal.getCarObservacion());
			pstmtCargo.executeUpdate();

			// insertar en tabla ficha_personal.personales_credenciales
			String iCargoCredencialSQL = "INSERT INTO ficha_personal.personales_credenciales(personal_cedula,credencial_id,percre_fecha_inicio,admin_login) VALUES(?,?,?,?)";
			pstmtCargoCredencial = conn.conexion.prepareStatement(iCargoCredencialSQL);
			pstmtCargoCredencial.setString(1, nuevoPersonal.getCedula());
			// modificacion pendiente a la carga de credenciales
			pstmtCargoCredencial.setInt(2, 3);
			sqlDate = new java.sql.Date(nuevoPersonal.getHorFechaIni().getTime());
			pstmtCargoCredencial.setDate(3, sqlDate);
			pstmtCargoCredencial.setString(4, nuevoPersonal.getAdministradorAlta());
			pstmtCargoCredencial.executeUpdate();

			// insertar en tabla dgth.administradores
			String iAdminSQL = "INSERT INTO dgth.administradores(admin_login,admin_password,tipoadmin_id,personal_cedula) VALUES(?,?,?,?)";
			pstmtAdmin = conn.conexion.prepareStatement(iAdminSQL);
			pstmtAdmin.setString(1, nuevoPersonal.getCedula());
			// modificacion pendiente a la carga de administradores
			String passwd = nuevoPersonal.getCedula().substring(nuevoPersonal.getCedula().length() - 3,
					nuevoPersonal.getCedula().length());
			passwd = Encrypt.getStringMessageDigest(passwd, Encrypt.MD5);
			pstmtAdmin.setString(2, passwd);
			pstmtAdmin.setInt(3, 4);
			pstmtAdmin.setString(4, nuevoPersonal.getCedula());
			pstmtAdmin.executeUpdate();

			conn.conexion.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.conexion.rollback();
			return false;
		} finally {
			if (pstmtPersonal != null) {
				pstmtPersonal.close();
			}
			if (pstmtCondicion != null) {
				pstmtCondicion.close();
			}
			if (pstmtDependencia != null) {
				pstmtDependencia.close();
			}
			if (pstmtOficina != null) {
				pstmtOficina.close();
			}
			if (pstmtInsHorario != null) {
				pstmtInsHorario.close();
			}
			if (pstmtUpdHorario != null) {
				pstmtUpdHorario.close();
			}
			if (pstmtCargo != null) {
				pstmtCargo.close();
			}
			if (pstmtAdmin != null) {
				pstmtAdmin.close();
			}
			if (conn.conexion != null) {
				conn.conexion.close();
			}
		}

	}

	public void cargarRegistrosCompleto() throws ClassNotFoundException, SQLException {
		ConexionMsAccess connms = new ConexionMsAccess();
		ConexionPostgresql connpg = new ConexionPostgresql();
		try {

			connms.sentencia = connms.conexion.createStatement();
			connms.resultado = connms.sentencia
					.executeQuery("SELECT Userid,CheckTime,Sensorid,CheckType FROM Checkinout");

			connpg.sentencia = connpg.conexion.createStatement();

			while (connms.resultado.next()) {
				// consultar si registro ya existe
				String consulta = "SELECT count(*) FROM control_asistencia.registros_entradas_salidas WHERE personal_cedula='"
						+ connms.resultado.getString("Userid") + "' AND reg_registro='"
						+ connms.resultado.getString("CheckTime").substring(0, 19) + "'";
				connpg.resultado = connpg.sentencia.executeQuery(consulta);

				while (connpg.resultado.next()) {
					if (connpg.resultado.getInt("count") == 0) {
						try {
							// insertar en la tabla los registros diferentes
							consulta = "INSERT INTO control_asistencia.registros_entradas_salidas(reg_registro,tipo_registro,reloj_id,personal_cedula)"
									+ " VALUES('" + connms.resultado.getString("CheckTime").substring(0, 19) + "','"
									+ connms.resultado.getString("CheckType") + "',"
									+ connms.resultado.getString("Sensorid") + ",'"
									+ connms.resultado.getString("Userid") + "')";
							connpg.pstmt = connpg.conexion.prepareStatement(consulta);
							connpg.pstmt.executeUpdate();
						} catch (SQLException e) {
							// System.err.println(connms.resultado.getString("CheckTime").substring(0,
							// 19));
							// System.err.println(connms.resultado.getString("Userid"));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connms.conexion.close();
			connpg.conexion.close();
		}

	}

	public void cargarRegistrosMensual() throws ClassNotFoundException, SQLException {

		ConexionPostgresql connpg = new ConexionPostgresql();
		ConexionMsAccess connms = new ConexionMsAccess();
		try {
			connms.sentencia = connms.conexion.createStatement();
			String consulta = "SELECT Logid,Userid,CheckTime,Sensorid,CheckType FROM Checkinout WHERE extract(month from CheckTime)="
					+ FechaUtil.getFechaActualString().substring(3, 5) + " AND extract(year from CheckTime)="
					+ FechaUtil.getFechaActualString().substring(6);
			connms.resultado = connms.sentencia.executeQuery(consulta);

			connpg.sentencia = connpg.conexion.createStatement();
			while (connms.resultado.next()) {
				// consultar si registro ya existe
				consulta = "SELECT count(*) FROM control_asistencia.registros_entradas_salidas WHERE personal_cedula='"
						+ connms.resultado.getString("Userid") + "' AND reg_registro='"
						+ connms.resultado.getString("CheckTime").substring(0, 19) + "'";
				connpg.resultado = connpg.sentencia.executeQuery(consulta);

				while (connpg.resultado.next()) {
					if (connpg.resultado.getInt("count") == 0) {
						try {
							// insertar en la tabla los registros diferentes
							consulta = "INSERT INTO control_asistencia.registros_entradas_salidas(reg_registro,tipo_registro,reloj_id,personal_cedula)"
									+ " VALUES('" + connms.resultado.getString("CheckTime").substring(0, 19) + "','"
									+ connms.resultado.getString("CheckType") + "',"
									+ connms.resultado.getString("Sensorid") + ",'"
									+ connms.resultado.getString("Userid") + "')";
							connpg.pstmt = connpg.conexion.prepareStatement(consulta);
							connpg.pstmt.executeUpdate();
						} catch (SQLException e) {
							System.err.println(connms.resultado.getString("CheckTime").substring(0, 19));
							System.err.println(connms.resultado.getString("Userid"));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connpg.conexion.close();
			connms.conexion.close();
		}

	}

	public boolean actualizarTipoRegistro(int id, String tipo) throws ClassNotFoundException {
		boolean control = false;
		ConexionPostgresql connpg = new ConexionPostgresql();
		String SQL = "UPDATE control_asistencia.registros_entradas_salidas SET tipo_registro='" + tipo
				+ "' WHERE reg_id=" + id;
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

	private int getIdDependencia(String codigo) throws ClassNotFoundException {
		int id = 0;
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT dep_id FROM ficha_personal.dependencias WHERE dep_codigo='" + codigo + "'");
			while (conn.resultado.next()) {
				id = conn.resultado.getInt("dep_id");
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	private int getIdOficina(String codigo) throws ClassNotFoundException {
		int id = 0;
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT ofi_id FROM ficha_personal.oficinas WHERE ofi_codigo='" + codigo + "'");
			while (conn.resultado.next()) {
				id = conn.resultado.getInt("ofi_id");
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	private int getIdCategoria(String codigo) throws ClassNotFoundException {
		int id = 0;
		try {
			ConexionPostgresql conn = new ConexionPostgresql();
			conn.sentencia = (Statement) conn.conexion.createStatement();
			conn.resultado = conn.sentencia
					.executeQuery("SELECT cat_id FROM ficha_personal.categorias WHERE cat_codigo='" + codigo + "'");
			while (conn.resultado.next()) {
				id = conn.resultado.getInt("cat_id");
			}
			conn.conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public void validarObjeto(Object objeto) {
		this.personal = (Personal) objeto;
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
		return "";
	}

	@Override
	public void executeInsertSQL(PreparedStatement pstmt) {
	}

	@Override
	public String updateSQL() {
		java.sql.Date sqlDate = new java.sql.Date(this.personal.getFechaNacimiento().getTime());
		String SQL = "UPDATE ficha_personal.personales SET pers_estado='" + this.personal.getEstado()
				+ "', pers_fecha_nacimiento='" + sqlDate + "', pers_sexo='" + this.personal.getSexo()
				+ "', pers_estado_civil='" + this.personal.getEstadoCivil() + "', pers_telefonos='"
				+ this.personal.getTelefonos() + "', pers_observacion='" + this.personal.getObservacion()
				+ "', pers_correo='" + this.personal.getCorreo() + "', pers_domicilio='" + this.personal.getDomicilio()
				+ "' WHERE pers_cedula_nro='" + this.personal.getCedula() + "';";
		return SQL;
	}

	@Override
	public String deleteSQL() {
		return "";
	}
}
