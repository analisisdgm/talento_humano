package com.migraciones.talentoHumano.datasources;

import java.util.ArrayList;

import com.migraciones.talentoHumano.modelos.Personal;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ListadoPersonal implements JRDataSource {
	private ArrayList<Personal> personales;
	private int indiceRegistroActual = -1;
	private String titulo;
	//
	private int numeroDiasMes;
	
	@Override
	public boolean next() throws JRException {
		return ++indiceRegistroActual < numeroDiasMes;
	}

	@Override
	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
		if ("txtCI".equals(jrf.getName())) {
			valor = this.personales.get(indiceRegistroActual).getCedula();
		} else if ("txtNombres".equals(jrf.getName())) {
			valor = this.personales.get(indiceRegistroActual).getNombres()+" "+this.personales.get(indiceRegistroActual).getApellidos();
		}else if ("txtCondicion".equals(jrf.getName())) {
			valor = this.personales.get(indiceRegistroActual).getCondicion();
		}else if ("txtHorario".equals(jrf.getName())) {
			valor = this.personales.get(indiceRegistroActual).getHorEntrada()			
					+ " - " + this.personales.get(indiceRegistroActual).getHorSalida();
		}else if ("txtDependencia".equals(jrf.getName())) {
			valor = this.personales.get(indiceRegistroActual).getDependencia();
		}else if ("txtTitulo".equals(jrf.getName())) {
			valor = titulo;
		}

		return valor;
	}

	public void addFicha(ArrayList<Personal> personales, String titulo) {
		this.personales = personales;
		this.numeroDiasMes = personales.size();
		this.titulo = titulo;
	}
}
