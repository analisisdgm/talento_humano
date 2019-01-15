package com.migraciones.talentoHumano.modelos;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.migraciones.talentoHumano.controladores.PerHistoDisipCont;
import com.migraciones.talentoHumano.utilities.FechaUtil;

public class FichaAsistencia {
	private String periodo;
	private String cedula;
	private String nombres;
	private String apellidos;
	private String fechaNacimiento;
	private String estado;
	private String condicion;
	private String dependencia;
	private String oficina;
	private String codigoOficina;
	private String cargo;
	private String codigoCargo;
	private String horario;
	private Time turnoEntrada;
	private Time turnoSalida;
	@SuppressWarnings("unused")
	private String horasFijasDia, horaLimiteHE;
	private FechaUtil utilFecha = new FechaUtil();
	private ArrayList<String> calendario;
	private ArrayList<FADetalle> detalles = new ArrayList<FADetalle>();
	private String imagen;
	private String administrador;
	private boolean tieneRegistros = false;
	// TOTALES
	private String totalHorasTrabajadas = "0";
	private String totalHorasAcumuladas = "0";
	private float totalHorasExtras;
	private String totalHADiurno;
	private String totalHAExtra;
	// DATOS PARA REALIZAR CALCULOS
	private FechaUtil util = new FechaUtil();
	private final float MINUTOS = 60, SEGUNDOS = 3600;
	private float turEntrada, turSalida, turPonderado;

	public FichaAsistencia(String cedula, int mes, int anho) throws ClassNotFoundException {
		this.calendario = utilFecha.getCalendarioMes(mes, anho);
		this.periodo = this.util.getMes(mes) + " / " + Integer.toString(anho);
		// cargar en detalles el calendario
		for (String s : this.calendario) {
			FADetalle detalle = new FADetalle();
			detalle.setFecha(s.substring(0, 10));
			detalle.setDiaSemana(s.substring(13));
			// agregar en el campo observaciones las justificaciones
			// correspondientes
			PerHistoDisipCont perCont = new PerHistoDisipCont();
			String dd = detalle.getFecha().substring(0, 2);
			String mm = detalle.getFecha().substring(3, 5);
			String yyyy = detalle.getFecha().substring(6, 10);
			String fechaSQL = yyyy + "-" + mm + "-" + dd;
			for (PerHistoDisip historia : perCont.getAllByMes(cedula, fechaSQL)) {
				if (detalle.getObservaciones() == null) {
					detalle.setObservaciones(historia.getObservacion());
				} else {
					detalle.setObservaciones(detalle.getObservaciones() + ", " + historia.getObservacion());
				}
			}
			this.detalles.add(detalle);
		}

	}

	public FichaAsistencia(String cedula, Date fechaDesde, Date fechaHasta) throws ClassNotFoundException {
		this.calendario = utilFecha.getCalendarioPeriodo(fechaDesde, fechaHasta);
		this.periodo = this.util.getDateToString(fechaDesde) + " - " + this.util.getDateToString(fechaHasta);
		// cargar en detalles el calendario
		for (String s : this.calendario) {
			FADetalle detalle = new FADetalle();
			detalle.setFecha(s.substring(0, 10));
			detalle.setDiaSemana(s.substring(13));
			// agregar en el campo observaciones las justificaciones
			// correspondientes
			PerHistoDisipCont perCont = new PerHistoDisipCont();
			String dd = detalle.getFecha().substring(0, 2);
			String mm = detalle.getFecha().substring(3, 5);
			String yyyy = detalle.getFecha().substring(6, 10);
			String fechaSQL = yyyy + "-" + mm + "-" + dd;
			for (PerHistoDisip historia : perCont.getAllByMes(cedula, fechaSQL)) {
				if (detalle.getObservaciones() == null) {
					detalle.setObservaciones(historia.getObservacion());
				} else {
					detalle.setObservaciones(detalle.getObservaciones() + ", " + historia.getObservacion());
				}
			}
			this.detalles.add(detalle);
		}
	}

	public String getAdministrador() {
		return administrador;
	}

	public void setAdministrador(String administrador) {
		this.administrador = administrador;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int mes, int anho) {
		this.periodo = this.util.getMes(mes) + " / " + Integer.toString(anho);
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = util.getEdad(fechaNacimiento);
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCodigoCargo() {
		return codigoCargo;
	}

	public void setCodigoCargo(String codigoCargo) {
		this.codigoCargo = codigoCargo;
	}

	public String getCodigoOficina() {
		return codigoOficina;
	}

	public void setCodigoOficina(String codOficina) {
		this.codigoOficina = codOficina;
	}

	public String getHorario() {
		return this.horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Time getTurnoEntrada() {
		return turnoEntrada;
	}

	public void setTurnoEntrada(Time turnoEntrada) {
		this.turnoEntrada = turnoEntrada;
	}

	public Time getTurnoSalida() {
		return turnoSalida;
	}

	public void setTurnoSalida(Time turnoSalida) {
		this.turnoSalida = turnoSalida;
	}

	public ArrayList<FADetalle> getDetalle() {
		return this.detalles;
	}

	public void addRegistro(Date fecha, String horaReloj, String tipoRegistro) throws ClassNotFoundException {
		FADetalle detalle = new FADetalle();
		String fechaString = util.getDateToString(fecha);
		detalle.setFecha(fechaString);
		if (this.detalles.contains(detalle)) {
			// si ya existe el registro poner en el lugar que corresponde
			int posicion = 0;
			posicion = this.detalles.indexOf(detalle);
			if (tipoRegistro.equals("I")) {
				this.detalles.get(posicion).addEntradas(horaReloj);
			} else if (tipoRegistro.equals("O")) {
				this.detalles.get(posicion).addSalidas(horaReloj);
			} else {
				if (this.detalles.get(posicion).getObservaciones() == null) {
					this.detalles.get(posicion).setObservaciones(horaReloj);
				} else {
					this.detalles.get(posicion).setObservaciones(
							", " + this.detalles.get(posicion).getObservaciones() + " - " + horaReloj);
				}
			}
		}
	}

	public String getImagen() {
		this.imagen = String.format("%010d", Integer.parseInt(this.cedula)) + ".jpg";
		return this.imagen;
	}

	public String getTotalHorasTrabajadas() {
		return totalHorasTrabajadas;
	}

	public String getTotalHorasTrabajadasFormato() {
		float horas = Float.parseFloat(this.getTotalHorasTrabajadas());
		int HH = (int) Math.abs(horas);
		int MM = (int) (horas * 60) % 60;
		int SS = (int) (horas * (60 * 60)) % 60;
		String convertido = String.format("%02d", HH) + ":" + String.format("%02d", MM) + ":"
				+ String.format("%02d", SS);
		return convertido;
	}

	public void setTotalHorasTrabajadas(String totalHorasTrabajadas) {
		this.totalHorasTrabajadas = totalHorasTrabajadas;
	}

	public String getTotalHorasAcumuladas() {
		return totalHorasAcumuladas;
	}

	public void setTotalHorasAcumuladas(String totalHorasAcumuladas) {
		this.totalHorasAcumuladas = totalHorasAcumuladas;
	}

	public String getTotalHorasAcumuladasFormato() {
		float horas = Float.parseFloat(this.getTotalHorasAcumuladas());
		int HH = (int) Math.abs(horas);
		int MM = (int) (horas * 60) % 60;
		int SS = (int) (horas * (60 * 60)) % 60;
		String convertido = String.format("%02d", HH) + ":" + String.format("%02d", MM) + ":"
				+ String.format("%02d", SS);
		return convertido;
	}

	public float getTotalHorasExtras() {
		return totalHorasExtras;
	}

	public void setTotalHorasExtras(float totalHorasExtras) {
		this.totalHorasExtras = totalHorasExtras;
	}

	public String getTotalHorasExtrasFormato() {
		float horas = totalHorasExtras;
		int HH = (int) Math.abs(horas);
		int MM = (int) (horas * 60) % 60;
		int SS = (int) (horas * (60 * 60)) % 60;
		String convertido = String.format("%02d", HH) + ":" + String.format("%02d", MM) + ":"
				+ String.format("%02d", SS);
		return convertido;
	}

	public String getTotalHADiurno() {
		return totalHADiurno;
	}

	public void setTotalHADiurno(String totalHADiurno) {
		this.totalHADiurno = totalHADiurno;
	}

	public String getTotalHAExtra() {
		return totalHAExtra;
	}

	public void setTotalHAExtra(String totalHAExtra) {
		this.totalHAExtra = totalHAExtra;
	}

	public boolean isTieneRegistros() {
		return tieneRegistros;
	}

	public void setTieneRegistros(boolean tieneRegistros) {
		this.tieneRegistros = tieneRegistros;
	}

	// ###############################
	// ##### CALCULO DE DATOS ########
	// ###############################

	public void calcularDatos() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Float sumaMensual = (float) 0, horasFijasMinima = (float) 0, horasFijas = (float) 0,
				sumaHorasAcumuladas = (float) 0, limiteHEMensual = (float) 32, sumaHAExtra = (float) 0,
				limiteHEDiario = (float) 2, limiteHESemanal = (float) 8, heDiario = (float) 0, heSemanal = (float) 0,
				heMensual = (float) 0, sumaHAMensual = (float) 0;

		this.turEntrada = convertirHoraFloat(this.turnoEntrada.toString());
		this.turSalida = convertirHoraFloat(this.turnoSalida.toString());
		this.turPonderado = (turEntrada + ((float) 15.983333 / (float) 60));
		String fechaInicio = "15/02/2018";
		Date dateInicio = formatter.parse(fechaInicio);
		// hora limite para realizar horas extras
		this.horaLimiteHE = "20:00:00";
		// horas fijas minimas diarias establecidas por ley
		horasFijasMinima = convertirHoraFloat("08:00:00");
		// horas de trabajos que debe cumplir el funcionario segun su horario
		horasFijas = this.turSalida - turEntrada;
		this.horasFijasDia = convertirFloatHora(horasFijas);

		if (this.getCodigoCargo().equals("6") || this.getCodigoCargo().equals("7")) {
			// cumple la condicion si es encargado o jefe de puesto de control
			// entonces tiene 2 horas de tolerancia
			this.turPonderado = (turEntrada + ((float) 120.983333 / (float) 60));
		}
		if (this.detalles.size() != 0) {
			for (FADetalle det : this.detalles) {
				// ##### HORAS TRABAJADAS Y ACUMULADAS #####
				// se utiliza la primera entrada y la ultima salida para el
				// calculo

				Date fecha = formatter.parse(det.getFecha());
				if (fecha.after(dateInicio)) {
					if (det.getEntradas().size() != 0 && det.getSalidas().size() != 0) {
						// total de horas diarias trabajadas
						float primeraEntrada = convertirHoraFloat(det.getEntradas().get(0));
						det.setHorasTrabajadas(calcularDiferenciaEntradaSalida(det.getEntradas().get(0).substring(0, 8),
								det.getSalidas().get(det.getSalidas().size() - 1).substring(0, 8)));
						sumaMensual += convertirHoraFloat(det.getHorasTrabajadas());
						if (this.getCodigoOficina().equals("OCE") || this.getCodigoOficina().equals("ODS")) {
							// ##### OFICINA CENTRAL #####
							if (det.getDiaSemana().equals("Sabado") || det.getDiaSemana().equals("Domingo")) {
								det.setHorasDiariasAcumuladas(det.getHorasTrabajadas());
								sumaHorasAcumuladas += convertirHoraFloat(det.getHorasTrabajadas());
								sumaHAExtra += convertirHoraFloat(det.getHorasTrabajadas());
								det.setHorasAdicionales(det.getHorasDiariasAcumuladas());
							} else {
								if (this.turPonderado >= primeraEntrada
										&& horasFijas <= convertirHoraFloat(det.getHorasTrabajadas())
										&& horasFijasMinima <= convertirHoraFloat(det.getHorasTrabajadas())) {

									String hac = calcularDiferenciaEntradaSalida(this.horasFijasDia,
											det.getHorasTrabajadas());
									det.setHorasDiariasAcumuladas(hac);
									sumaHorasAcumuladas += convertirHoraFloat(hac);
								}
							}
						}
						this.setTotalHAExtra(convertirFloatHora(sumaHAExtra));
						this.setTotalHorasTrabajadas(Float.toString(sumaMensual));
						this.setTotalHorasAcumuladas(Float.toString(sumaHorasAcumuladas));
					}
				}
			}

			// ##### HORAS EXTRAS Y ADICIONALES DIARIAS TRABAJADAS ####
			// si llega tarde o no pasa de las 8 horas laborales
			// ordinarias no posee horas acumuladas

			for (FADetalle det : this.detalles) {
				Date fecha = formatter.parse(det.getFecha());
				if (fecha.after(dateInicio)) {
					// se inicializa el contador semanal de horas extras
					if (det.getDiaSemana().equals("Sabado")) {
						heSemanal = (float) 0;
					}
					if (det.getEntradas().size() != 0 && det.getSalidas().size() != 0) {
						if (this.getCodigoOficina().equals("OCE") || this.getCodigoOficina().equals("ODS")) {
							if (!det.getDiaSemana().equals("Sabado") && !det.getDiaSemana().equals("Domingo")) {
								heDiario = (float) 0;
								float hac = convertirHoraFloat(det.getHorasDiariasAcumuladas());
								if (hac > 0) {
									if (heMensual < limiteHEMensual) {
										if (heSemanal < limiteHESemanal) {
											if (hac < limiteHEDiario) {

												float auxSemanal = heSemanal + hac;
												if (auxSemanal < limiteHESemanal) {
													heDiario = hac;
													heSemanal += hac;
													heMensual += hac;
												} else {
													heDiario = limiteHESemanal - heSemanal;
													heSemanal += heDiario;
													heMensual += heDiario;
												}
											} else {
												heDiario = limiteHEDiario;
												heSemanal += limiteHEDiario;
												heMensual += limiteHEDiario;
											}

										}
									}
									if (heMensual > limiteHEMensual) {
										float aux = limiteHEMensual - this.getTotalHorasExtras();
										det.setHorasExtras(convertirFloatHora(aux));
										this.setTotalHorasExtras(limiteHEMensual);
										float ha = convertirHoraFloat(det.getHorasDiariasAcumuladas())
												- convertirHoraFloat(det.getHorasExtras());
										sumaHAMensual += ha;
										det.setHorasAdicionales(calcularDiferenciaHoras(det.getHorasExtras(),
												det.getHorasDiariasAcumuladas()));
										this.setTotalHADiurno(convertirFloatHora(sumaHAMensual));
									} else {
										this.setTotalHorasExtras(this.getTotalHorasExtras() + heDiario);
										det.setHorasExtras(convertirFloatHora(heDiario));
										float ha = convertirHoraFloat(det.getHorasDiariasAcumuladas())
												- convertirHoraFloat(det.getHorasExtras());
										sumaHAMensual += ha;
										det.setHorasAdicionales(calcularDiferenciaHoras(det.getHorasExtras(),
												det.getHorasDiariasAcumuladas()));
										this.setTotalHADiurno(convertirFloatHora(sumaHAMensual));
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private String calcularDiferenciaEntradaSalida(String primeraHora, String segundaHora) {
		String[] h1 = primeraHora.split(":");
		// Version 2 del calculo de horas trabajadas modificada a pedido
		if (this.turPonderado > convertirHoraFloat(primeraHora)) {
			h1 = this.turnoEntrada.toString().split(":");
		}

		String[] h2 = segundaHora.split(":");
		int resto = 0;
		int segundo = Integer.parseInt(h2[2]) - Integer.parseInt(h1[2]);
		if (segundo < 0) {
			resto++;
			segundo = 60 + segundo;
		}
		int minuto = (Integer.parseInt(h2[1]) - Integer.parseInt(h1[1])) - resto;
		resto = 0;
		if (minuto < 0) {
			resto++;
			minuto = 60 + minuto;
		}
		int hora = (Integer.parseInt(h2[0]) - Integer.parseInt(h1[0])) - resto;

		String horasTrabajadas = String.format("%02d", hora) + ":" + String.format("%02d", minuto) + ":"
				+ String.format("%02d", segundo);
		return horasTrabajadas;
	}

	private String calcularDiferenciaHoras(String primeraHora, String segundaHora) {
		String[] h1 = primeraHora.split(":");
		String[] h2 = segundaHora.split(":");
		int resto = 0;
		int segundo = Integer.parseInt(h2[2]) - Integer.parseInt(h1[2]);
		if (segundo < 0) {
			resto++;
			segundo = 60 + segundo;
		}
		int minuto = (Integer.parseInt(h2[1]) - Integer.parseInt(h1[1])) - resto;
		resto = 0;
		if (minuto < 0) {
			resto++;
			minuto = 60 + minuto;
		}
		int hora = (Integer.parseInt(h2[0]) - Integer.parseInt(h1[0])) - resto;

		String horasTrabajadas = String.format("%02d", hora) + ":" + String.format("%02d", minuto) + ":"
				+ String.format("%02d", segundo);
		return horasTrabajadas;
	}

	private float convertirHoraFloat(String hora) {
		// float con parte entera de 2 digitos
		float hh = 0, mm = 0, ss = 0;
		if (hora != null) {
			if (hora.substring(2, 3).equals(":")) {
				hh = Float.parseFloat(hora.substring(0, 2));
				mm = Float.parseFloat(hora.substring(3, 5)) / this.MINUTOS;
				ss = Float.parseFloat(hora.substring(6, 8)) / SEGUNDOS;

			} else {

				hh = Float.parseFloat(hora.substring(0, 3));
				mm = Float.parseFloat(hora.substring(4, 6)) / this.MINUTOS;
				ss = Float.parseFloat(hora.substring(7, 9)) / SEGUNDOS;
			}
		}

		return hh + mm + ss;
	}

	public String convertirFloatHora(Float hora) {
		int HH = (int) Math.abs(hora);
		int MM = (int) (hora * 60) % 60;
		int SS = (int) (hora * (60 * 60)) % 60;
		String convertido = String.format("%02d", HH) + ":" + String.format("%02d", MM) + ":"
				+ String.format("%02d", SS);
		return convertido;
	}
}
