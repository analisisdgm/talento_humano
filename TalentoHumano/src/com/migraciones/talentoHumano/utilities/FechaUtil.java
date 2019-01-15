package com.migraciones.talentoHumano.utilities;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FechaUtil {

	private static SimpleDateFormat formateador0 = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat formateador1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm:ss");
	public static SimpleDateFormat formateador3 = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat formateador4 = new SimpleDateFormat("yyyy");
	public static SimpleDateFormat formateador5 = new SimpleDateFormat("MM");
	private static Date fechaActual = new Date();
	final long MILSEG_POR_DIA = (24 * 60 * 60 * 1000);

	public String getEdad(Date fechaNacimiento) {
		if (fechaNacimiento != null) {
			StringBuilder result = new StringBuilder();
			if (fechaNacimiento != null) {
				result.append(formateador0.format(fechaNacimiento));
				result.append(" (");
				Calendar c = new GregorianCalendar();
				c.setTime(fechaNacimiento);
				result.append(calcularEdad(c));
				result.append(" años)");
			}
			return result.toString();
		}
		return "";
	}

	private static int calcularEdad(Calendar fechaNac) {
		Calendar today = Calendar.getInstance();
		int diffYear = today.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
		int diffMonth = today.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
		int diffDay = today.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);
		// Si está en ese año pero todavía no los ha cumplido
		if (diffMonth < 0 || (diffMonth == 0 && diffDay < 0)) {
			diffYear = diffYear - 1;
		}
		return diffYear;
	}

	public static String getFechaActualString() {
		return formateador0.format(fechaActual);
	}

	public String getTimestampToHours(Date date) {
		return formateador2.format(date);
	}

	public static Date getFechaActualDate() {
		return fechaActual;
	}

	public String getDiaSemana(Date date) {
		String[] dias = { "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" };
		int numeroDia = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		numeroDia = cal.get(Calendar.DAY_OF_WEEK);
		return dias[numeroDia - 1];
	}

	public String getTimestampToString(Timestamp date) {
		return formateador1.format(date);
	}

	public String getDateToString(Date date) {
		return formateador0.format(date);
	}

	public int getDateToAnho(Date date) {
		return Integer.parseInt(formateador4.format(date));
	}

	public int getDateToMes(Date date) {
		return Integer.parseInt(formateador5.format(date));
	}

	public Date getStringToDate(String date) {
		Date fecha = null;
		try {
			fecha = formateador0.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fecha;
	}

	public String getDateToStringSQL(Date date) {
		return formateador3.format(date);
	}

	public String getMes(int mes) {
		String[] dias = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
				"Octubre", "Noviembre", "Diciembre" };
		return dias[mes - 1];
	}

	public int getDiasMes(int mes, int anho) {
		switch (mes) {
		case 1: // enero
		case 3: // marzo
		case 5: // mayo
		case 7: // julio
		case 8: // agosto
		case 10: // octubre
		case 12: // diciembre
			return 31;
		case 4: // abril
		case 6: // junio
		case 9: // septiembre
		case 11: // noviembre
			return 30;
		case 2: // febrero
			if (((anho % 100 == 0) && (anho % 400 == 0)) || ((anho % 100 != 0) && (anho % 4 == 0)))
				return 29; // Año Bisiesto
			else
				return 28;
		default:
			return 0;
		}
	}

	public int getDiasPeriodo(Date inicio, Date fin) {
		return (int) ((fin.getTime() - inicio.getTime()) / MILSEG_POR_DIA) + 1;
	}

	public ArrayList<String> getCalendarioMes(int mes, int anho) {
		--mes;
		String fecha = null;
		ArrayList<String> diasLista = new ArrayList<String>();
		Calendar cal = new GregorianCalendar(anho, mes, 1);
		int diasMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int cont = 0;
		while (++cont <= diasMes) {
			cal.set(anho, mes, cont);
			fecha = formateador0.format(cal.getTime());
			diasLista.add(fecha + " - " + getDiaSemana(cal.getTime()));
		}
		return diasLista;
	}

	public ArrayList<String> getCalendarioPeriodo(Date fechaDesde, Date fechaHasta) {
		String fecha = null;
		ArrayList<String> diasLista = new ArrayList<String>();
		Date inicio = new Date();
		inicio.setTime(fechaDesde.getTime());
		Date aux = null;
		while (inicio.getTime() < fechaHasta.getTime()) {
			// incrementar un dia
			if (aux == null) {
				aux = inicio;
			} else {
				aux.setTime((long) inicio.getTime() + MILSEG_POR_DIA);
			}
			fecha = formateador0.format(aux.getTime());
			diasLista.add(fecha + " - " + getDiaSemana(aux));
		}
		return diasLista;
	}

	// suma dias a la fecha
	public Date sumarRestarDiasFecha(Date fecha, int dias) {
		Calendar calendario = Calendar.getInstance();
		// configuramos la fecha que recibe
		calendario.setTime(fecha);
		// numero de dias a sumar o restar en caso que dias sea<0
		calendario.add(Calendar.DAY_OF_YEAR, dias);
		return calendario.getTime();

	}

}