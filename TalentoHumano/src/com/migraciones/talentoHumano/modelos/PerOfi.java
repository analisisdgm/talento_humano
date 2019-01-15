package com.migraciones.talentoHumano.modelos;

import java.sql.Timestamp;
import java.util.Date;

public class PerOfi {
	private int id;
	private String cedula;
	private int oficinaId;
	private String oficinaCodigo;
	private String observacion;
	private String estado;
	private Date fechaInicio;
	private Date fechaFin;
	private Timestamp fechaAlta;
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

	public int getOficinaId() {
		return oficinaId;
	}

	public void setOficinaId(int oficinaId) {
		this.oficinaId = oficinaId;
	}

	public String getOficinaCodigo() {
		return oficinaCodigo;
	}

	public void setOficinaCodigo(String oficinaCodigo) {
		this.oficinaCodigo = oficinaCodigo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public Timestamp getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getAdministrador() {
		return administrador;
	}

	public void setAdministrador(String administrador) {
		this.administrador = administrador;
	}

}
