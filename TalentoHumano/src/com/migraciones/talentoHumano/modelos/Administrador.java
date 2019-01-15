package com.migraciones.talentoHumano.modelos;

import java.sql.Timestamp;

public class Administrador {
	private String login;
	private String password;
	private int tipoAdministrador;
	private String cedula;
	private Timestamp fechaCreacion;
	private String estado;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTipoAdministrador() {
		return tipoAdministrador;
	}

	public void setTipoAdministrador(int tipoAdministrador) {
		this.tipoAdministrador = tipoAdministrador;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
