package com.migraciones.talentoHumano.modelos;

import java.util.ArrayList;

public class FADetalle {
	private String fecha;
	private String diaSemana;
	private ArrayList<String> entradas = new ArrayList<String>();
	private ArrayList<String> salidas = new ArrayList<String>();
	private String horasDiariasAcumuladas;
	private String horasTrabajadas;
	private String horasExtras;
	private String horasAdicionales;
	private String horasAuxiliar;
	private String observaciones;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public ArrayList<String> getEntradas() {
		return entradas;
	}

	public String getEntradasString() {
		String valor = "";
		for (String entrada : this.entradas) {
			if (valor.equals("")) {
				valor = entrada;
			} else {
				valor += "\n" + entrada;
			}
		}
		return valor;
	}

	public void setEntradas(ArrayList<String> entradas) {
		this.entradas = entradas;
	}

	public void addEntradas(String entrada) {
		this.entradas.add(entrada);
	}

	public ArrayList<String> getSalidas() {
		return salidas;
	}

	public String getSalidasString() {
		String valor = "";
		for (String salida : this.salidas) {
			if (valor.equals("")) {
				valor = salida;
			} else {
				valor += "\n" + salida;
			}
		}
		return valor;
	}

	public void setSalidas(ArrayList<String> salidas) {
		this.salidas = salidas;
	}

	public void addSalidas(String salida) {
		this.salidas.add(salida);
	}

	public String getHorasDiariasAcumuladas() {
		return horasDiariasAcumuladas;
	}

	public void setHorasDiariasAcumuladas(String horasDiariasAcumuladas) {
		this.horasDiariasAcumuladas = horasDiariasAcumuladas;
	}

	public String getHorasTrabajadas() {
		return horasTrabajadas;
	}

	public void setHorasTrabajadas(String horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}

	public String getHorasExtras() {
		return horasExtras;
	}

	public void setHorasExtras(String horasExtras) {
		this.horasExtras = horasExtras;
	}

	public String getHorasAdicionales() {
		return horasAdicionales;
	}

	public void setHorasAdicionales(String horasAdicionales) {
		this.horasAdicionales = horasAdicionales;
	}

	public String getHorasAuxiliar() {
		return horasAuxiliar;
	}

	public void setHorasAuxiliar(String horasAuxiliar) {
		this.horasAuxiliar = horasAuxiliar;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FADetalle) {
			if (((FADetalle) obj).getFecha().equals(this.fecha)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return 1;
	}

}
