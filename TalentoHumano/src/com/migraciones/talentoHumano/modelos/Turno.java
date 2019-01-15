package com.migraciones.talentoHumano.modelos;

import java.sql.Time;

public class Turno {
	private int id;
	private String descripcion;
	private Time entrada;
	private Time salida;
	private double tiempoTrabajo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Time getEntrada() {
		return entrada;
	}

	public void setEntrada(Time entrada) {
		this.entrada = entrada;
	}

	public Time getSalida() {
		return salida;
	}

	public void setSalida(Time salida) {
		this.salida = salida;
	}

	public double getTiempoTrabajo() {
		return tiempoTrabajo;
	}

	public void setTiempoTrabajo(double tiempoTrabajo) {
		this.tiempoTrabajo = tiempoTrabajo;
	}

}
