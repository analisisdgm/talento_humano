package com.migraciones.talentoHumano.models;

import java.sql.Time;
import java.util.Date;

import com.migraciones.talentoHumano.utilities.GlobalUtil;

public class HorarioPersonal {
	// datos del horario
	private int id;
	private int personalId;
	private String horarioObservacion;
	private Date fechaInicio;
	private Date fechaFin;
	private String horarioEstado;
	private Date fechaAlta;
	private String administradorAlta;
	// datos del personal
	private String cedula;
	private String nombres;
	private String apellidos;
	private int tipoPersonalId;
	private String tipoPersonal;
	private int dependenciaId;
	private String codigoDependencia;
	private String dependencia;
	private int oficinaId;
	private String codigoOficina;
	private String oficina;
	private String personalEstado;
	// datos del turno
	private int turnoId;
	private String turno;
	private Time turnoEntrada;
	private Time turnoSalida;
	private Double turnoTiempoTrabajo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPersonalId() {
		return personalId;
	}

	public void setPersonalId(int personalId) {
		this.personalId = personalId;
	}

	public String getHorarioObservacion() {
		return horarioObservacion;
	}

	public void setHorarioObservacion(String horarioObservacion) {
		this.horarioObservacion = horarioObservacion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getHorarioEstado() {
		return horarioEstado;
	}

	public void setHorarioEstado(String horarioEstado) {
		this.horarioEstado = horarioEstado;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getAdministradorAlta() {
		return administradorAlta;
	}

	public void setAdministradorAlta(String administradorAlta) {
		this.administradorAlta = administradorAlta;
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

	public String getNombresApellidos() {
		return nombres + " " + apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getTipoPersonalId() {
		return tipoPersonalId;
	}

	public void setTipoPersonalId(int tipoPersonalId) {
		this.tipoPersonalId = tipoPersonalId;
	}

	public String getTipoPersonal() {
		return tipoPersonal;
	}

	public void setTipoPersonal(String tipoPersonal) {
		this.tipoPersonal = tipoPersonal;
	}

	public int getDependenciaId() {
		return dependenciaId;
	}

	public void setDependenciaId(int dependenciaId) {
		this.dependenciaId = dependenciaId;
	}

	public String getCodigoDependencia() {
		return codigoDependencia;
	}

	public void setCodigoDependencia(String codigoDependencia) {
		this.codigoDependencia = codigoDependencia;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public int getOficinaId() {
		return oficinaId;
	}

	public void setOficinaId(int oficinaId) {
		this.oficinaId = oficinaId;
	}

	public String getCodigoOficina() {
		return codigoOficina;
	}

	public void setCodigoOficina(String codigoOficina) {
		this.codigoOficina = codigoOficina;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public String getPersonalEstado() {
		return personalEstado;
	}

	public void setPersonalEstado(String personalEstado) {
		this.personalEstado = personalEstado;
	}

	public int getTurnoId() {
		return turnoId;
	}

	public void setTurnoId(int turnoId) {
		this.turnoId = turnoId;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
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

	public Double getTurnoTiempoTrabajo() {
		return turnoTiempoTrabajo;
	}

	public void setTurnoTiempoTrabajo(Double turnoTiempoTrabajo) {
		this.turnoTiempoTrabajo = turnoTiempoTrabajo;
	}

	public String getRutaImagen() {
		String rutaImagen = GlobalUtil.PATH_CARNET + String.format("%010d", Integer.parseInt(this.cedula)) + ".jpg";
		return rutaImagen;
	}

}
