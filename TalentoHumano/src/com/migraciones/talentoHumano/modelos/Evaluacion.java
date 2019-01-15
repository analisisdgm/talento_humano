package com.migraciones.talentoHumano.modelos;

public class Evaluacion {
	private int id;
	private String cedula;
	private String periodo;
	private int puntajeDesempeno;
	private int puntajeExamen;
	private Double puntajeTotal;
	private int nota;

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

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public int getPuntajeDesempeno() {
		return puntajeDesempeno;
	}

	public void setPuntajeDesempeno(int puntajeDesempeno) {
		this.puntajeDesempeno = puntajeDesempeno;
	}

	public int getPuntajeExamen() {
		return puntajeExamen;
	}

	public void setPuntajeExamen(int puntajeExamen) {
		this.puntajeExamen = puntajeExamen;
	}

	public Double getPuntajeTotal() {
		return puntajeTotal;
	}

	public void setPuntajeTotal(Double puntajeTotal) {
		this.puntajeTotal = puntajeTotal;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

}
