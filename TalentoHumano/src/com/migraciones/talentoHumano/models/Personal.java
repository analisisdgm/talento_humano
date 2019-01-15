package com.migraciones.talentoHumano.models;

import com.migraciones.talentoHumano.utilities.GlobalUtil;

public class Personal {
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

	public String getNombresApellidos() {
		return nombres + " " + apellidos;
	}

	@Override
	public String toString() {
		return nombres + " " + apellidos;
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

	public String getRutaImagen() {
		String rutaImagen = GlobalUtil.PATH_CARNET + String.format("%010d", Integer.parseInt(this.cedula)) + ".jpg";
		return rutaImagen;
	}

}
