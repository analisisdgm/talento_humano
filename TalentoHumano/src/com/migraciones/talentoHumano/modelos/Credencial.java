package com.migraciones.talentoHumano.modelos;

public class Credencial {
	private String cedula;
	private String nombres;
	private String apellidos;
	private String cargo;
	private int condicion;
	private String imagen;

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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getCondicion() {
		return condicion;
	}

	public void setCondicion(int condicion) {
		this.condicion = condicion;
	}

	public String getImagen() {
		this.imagen = String.format("%010d", Integer.parseInt(this.cedula)) + ".jpg";
		return this.imagen;
	}

}
