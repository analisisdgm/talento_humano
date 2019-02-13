package com.migraciones.talentoHumano.modelos;

import java.sql.Timestamp;
import java.util.Date;

public class PerFechaDesvinculacion {
	private int id;
	private Date fecha;
	private String observacion;
	private Timestamp fechaAlta;
	private String cedula;
	private String administrador;
	private String idPersonalesTipos;
	

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
	
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getIdPersonalesTipos() {
		return idPersonalesTipos;
	}

	public void setIdPersonalesTipos(String idPersonalesTipos) {
		this.idPersonalesTipos = idPersonalesTipos;
	}



	

}
