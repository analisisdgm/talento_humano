package com.migraciones.talentoHumano.datasources;

import com.migraciones.talentoHumano.modelos.JustificacionLista;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class JustificacionListaDS implements JRDataSource {
	private JustificacionLista justificacion;
	private int indiceRegistroActual = -1;
	private int numeroDiasMes;

	@Override
	public boolean next() throws JRException {
		return ++indiceRegistroActual < numeroDiasMes;
	}

	@Override
	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
		if ("txtOficina".equals(jrf.getName())) {
			valor = this.justificacion.getOficina();
		} else if ("txtPeriodo".equals(jrf.getName())) {
			valor = this.justificacion.getPeriodo();
		} else if ("txtItem".equals(jrf.getName())) {
			valor = Integer.toString(indiceRegistroActual + 1);
		} else if ("txtFechaDesde".equals(jrf.getName())) {
			valor = this.justificacion.getDetalles().get(indiceRegistroActual).getFechaDesde();
		} else if ("txtFechaHasta".equals(jrf.getName())) {
			valor = this.justificacion.getDetalles().get(indiceRegistroActual).getFechaHasta();
		} else if ("txtCedula".equals(jrf.getName())) {
			valor = this.justificacion.getDetalles().get(indiceRegistroActual).getCedula();
		} else if ("txtFuncionario".equals(jrf.getName())) {
			valor = this.justificacion.getDetalles().get(indiceRegistroActual).getFuncionario();
		} else if ("txtDescripcion".equals(jrf.getName())) {
			valor = this.justificacion.getDetalles().get(indiceRegistroActual).getDescripcion();
		}

		return valor;
	}

	public void addJustificacion(JustificacionLista justificacion) {
		this.justificacion = justificacion;
		this.numeroDiasMes = this.justificacion.getDetalles().size();
	}
}
