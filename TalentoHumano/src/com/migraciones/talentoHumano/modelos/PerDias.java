package com.migraciones.talentoHumano.modelos;

import java.util.Date;

public class PerDias {
	private int id;
	private int diaId;
	private String descripciondia;
	private String cedula;
	private String estado;
	private Date fechaAlta;
	private Date fechaInicio;
	private Date fechaFin;
	private String observacion;
	private String administrador;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
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

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getAdministrador() {
		return administrador;
	}

	public void setAdministrador(String administrador) {
		this.administrador = administrador;
	}

	public int getDiaId() {
		return diaId;
	}

	public void setDiaId(int diaId) {
		this.diaId = diaId;
	}

	public String getDescripciondia() {
		return descripciondia;
	}

	public void setDescripciondia(String descripciondia) {
		this.descripciondia = descripciondia;
	}

}
