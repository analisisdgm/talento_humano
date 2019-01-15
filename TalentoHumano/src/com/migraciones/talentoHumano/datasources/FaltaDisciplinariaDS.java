package com.migraciones.talentoHumano.datasources;

import java.util.ArrayList;

import com.migraciones.talentoHumano.modelos.FaltaDisciplinaria;
import com.migraciones.talentoHumano.utilities.FechaUtil;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class FaltaDisciplinariaDS implements JRDataSource {
	private ArrayList<FaltaDisciplinaria> faltas;
	private int indiceRegistroActual = -1;
	private int numeroDiasMes;
	private FechaUtil utilFecha = new FechaUtil();

	@Override
	public boolean next() throws JRException {
		return ++indiceRegistroActual < numeroDiasMes;
	}

	@Override
	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
		if ("txtFecha".equals(jrf.getName())) {
			valor = utilFecha.getDateToString(this.faltas.get(indiceRegistroActual).getFecha());
		} else if ("txtDiaSemana".equals(jrf.getName())) {
			valor = this.faltas.get(indiceRegistroActual).getDiaSemana();
		} else if ("txtCedula".equals(jrf.getName())) {
			valor = this.faltas.get(indiceRegistroActual).getCedula();
		} else if ("txtFuncionario".equals(jrf.getName())) {
			valor = this.faltas.get(indiceRegistroActual).getNombres();
		} else if ("txtPeriodo".equals(jrf.getName())) {
			valor = this.faltas.get(indiceRegistroActual).getPeriodo();
		}
		return valor;
	}

	public void addFaltas(ArrayList<FaltaDisciplinaria> faltas) {
		this.faltas = faltas;
		this.numeroDiasMes = this.faltas.size();
	}
}
