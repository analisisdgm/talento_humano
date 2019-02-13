package com.migraciones.talentoHumano.datasources;

import com.migraciones.talentoHumano.modelos.Credencial;
import com.migraciones.talentoHumano.utilities.GlobalUtil;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class CredencialDS implements JRDataSource {
	private Credencial credencial = new Credencial();
	private int indiceRegistroActual = -1;
	private int numero;

	@Override
	public boolean next() throws JRException {
		return ++indiceRegistroActual < numero;
	}

	@Override
	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
		if ("txtCI".equals(jrf.getName())) {
			valor = this.credencial.getCedula();
		} else if ("txtNombreApe".equals(jrf.getName())) {
			String delimitadores = "[ ]";
			@SuppressWarnings("unused")
			String[] nomApeSeparado = null, nomSeparado = null, apeSeparado = null;
			String nombres = this.credencial.getNombres(), apellidos = this.credencial.getApellidos();
			String nomApe = nombres + " " + apellidos;
			nomSeparado = nombres.split(delimitadores);
			apeSeparado = apellidos.split(delimitadores);
			if (nomApe.length() > 29) {
				nomApe = nombres + " " + apeSeparado[0];
			}
			valor = nomApe;
		} else if ("txtNombres".equals(jrf.getName())) {
			valor = this.credencial.getNombres();
		} else if ("txtApellidos".equals(jrf.getName())) {
			valor = this.credencial.getApellidos();
		} else if ("txtDireccionImagen".equals(jrf.getName())) {
			valor = GlobalUtil.PUBLIC_REPORTE_CARNET;
		} else if ("txtPersonalImagen".equals(jrf.getName())) {
			valor = this.credencial.getImagen();
		} else if ("txtCargo".equals(jrf.getName())) {
			valor = this.credencial.getCargo();
		} else if ("txtVigencia".equals(jrf.getName())) {
			if (this.credencial.getCondicion() == 1) {
				valor = "";
			} else {
				valor = "Vencimiento: Diciembre 2019";
			}
		}

		return valor;
	}

	public void addCredencial(Credencial credencial) {
		this.credencial = credencial;
		this.numero = 1;
	}
}
