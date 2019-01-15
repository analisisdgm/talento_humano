package com.migraciones.talentoHumano.modelos;

import java.util.Date;

import com.migraciones.talentoHumano.utilities.FechaUtil;

public class FaltaDisciplinaria {

	private String periodo;
	private String cedula;
	private String nombres;
	private Date fecha;
	private String descripcion;

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDiaSemana() {
		FechaUtil cal = new FechaUtil();
		return cal.getDiaSemana(this.fecha);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
