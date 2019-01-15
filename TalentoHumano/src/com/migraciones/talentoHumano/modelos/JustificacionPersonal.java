package com.migraciones.talentoHumano.modelos;

import java.util.ArrayList;

public class JustificacionPersonal {
	private String periodo;
	private String cedula;
	private String nombres;
	private String apellidos;
	private String imagen;
	private String estado;
	private ArrayList<JPDetalle> detalles = new ArrayList<JPDetalle>();
	private boolean tieneRegistros = false;

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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getImagen() {
		this.imagen = String.format("%010d", Integer.parseInt(this.cedula)) + ".jpg";
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void addDetalle(JPDetalle detalle) {
		this.detalles.add(detalle);
	}

	public ArrayList<JPDetalle> getDetalles() {
		return this.detalles;
	}

	public void setDetalles(ArrayList<JPDetalle> detalles) {
		this.detalles = detalles;
	}

	public boolean isTieneRegistros() {
		return tieneRegistros;
	}

	public void setTieneRegistros(boolean tieneRegistros) {
		this.tieneRegistros = tieneRegistros;
	}

}
