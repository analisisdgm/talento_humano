package com.migraciones.talentoHumano.modelos;

import java.sql.Timestamp;

public class Registro {
	private int id;
	private Timestamp registro;
	private String relojCodigo;
	private String tipoRegistro;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getRegistro() {
		return registro;
	}

	public void setRegistro(Timestamp registro) {
		this.registro = registro;
	}

	public String getRelojCodigo() {
		return relojCodigo;
	}

	public void setRelojCodigo(String relojCodigo) {
		this.relojCodigo = relojCodigo;
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

}
