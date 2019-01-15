package com.migraciones.talentoHumano.modelos;

import java.util.ArrayList;

public class JustificacionLista {
	private String oficina;
	private String periodo;
	private ArrayList<JLDetalle> detalles = new ArrayList<JLDetalle>();
	private boolean tieneRegistros = false;

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public void addDetalle(JLDetalle detalle) {
		this.detalles.add(detalle);
	}

	public ArrayList<JLDetalle> getDetalles() {
		return this.detalles;
	}

	public void setDetalles(ArrayList<JLDetalle> detalles) {
		this.detalles = detalles;
	}

	public void addDetalles(JLDetalle detalle) {
		this.detalles.add(detalle);
	}

	public boolean isTieneRegistros() {
		return tieneRegistros;
	}

	public void setTieneRegistros(boolean tieneRegistros) {
		this.tieneRegistros = tieneRegistros;
	}

}
