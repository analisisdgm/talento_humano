package com.migraciones.talentoHumano.datasources;

import com.migraciones.talentoHumano.modelos.FichaAsistencia;
import com.migraciones.talentoHumano.utilities.GlobalUtil;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class FichaAsistenciaDS implements JRDataSource {
	private FichaAsistencia ficha;
	private int indiceRegistroActual = -1;
	private int numeroDiasMes;

	@Override
	public boolean next() throws JRException {
		return ++indiceRegistroActual < numeroDiasMes;
	}

	@Override
	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
		if ("txtCI".equals(jrf.getName())) {
			valor = this.ficha.getCedula();
		} else if ("txtNombres".equals(jrf.getName())) {
			valor = this.ficha.getNombres();
		} else if ("txtApellidos".equals(jrf.getName())) {
			valor = this.ficha.getApellidos();
		} else if ("txtFechaNac".equals(jrf.getName())) {
			valor = this.ficha.getFechaNacimiento();
		} else if ("txtDependencia".equals(jrf.getName())) {
			valor = this.ficha.getDependencia();
		} else if ("txtOficina".equals(jrf.getName())) {
			valor = this.ficha.getOficina();
		} else if ("txtCondicion".equals(jrf.getName())) {
			valor = this.ficha.getCondicion();
		} else if ("txtHorario".equals(jrf.getName())) {
			valor = this.ficha.getHorario();
		} else if ("txtPeriodo".equals(jrf.getName())) {
			valor = this.ficha.getPeriodo();
		} else if ("txtEstado".equals(jrf.getName())) {
			if (this.ficha.getEstado().equals("A")) {
				valor = "ACTIVO";
			} else {
				valor = "INACTIVO";
			}
		} else if ("txtDireccionImagen".equals(jrf.getName())) {
			valor = GlobalUtil.PUBLIC_REPORTE_CARNET;
		} else if ("txtPersonalImagen".equals(jrf.getName())) {
			valor = this.ficha.getImagen();
		} else if ("txtDiaSemana".equals(jrf.getName())) {
			valor = this.ficha.getDetalle().get(indiceRegistroActual).getDiaSemana();
		} else if ("txtFecha".equals(jrf.getName())) {
			valor = this.ficha.getDetalle().get(indiceRegistroActual).getFecha();
		} else if ("txtEntradas".equals(jrf.getName())) {
			valor = this.ficha.getDetalle().get(indiceRegistroActual).getEntradasString();
		} else if ("txtSalidas".equals(jrf.getName())) {
			valor = this.ficha.getDetalle().get(indiceRegistroActual).getSalidasString();
		} else if ("txtHorasTrabajadas".equals(jrf.getName())) {
			valor = this.ficha.getDetalle().get(indiceRegistroActual).getHorasTrabajadas();
		} else if ("txtHorasAcumuladas".equals(jrf.getName())) {
			valor = this.ficha.getTotalHorasAcumuladasFormato();
		} else if ("txtHE".equals(jrf.getName())) {
			valor = this.ficha.getDetalle().get(indiceRegistroActual).getHorasExtras();
		} else if ("txtHA".equals(jrf.getName())) {
			valor = this.ficha.getDetalle().get(indiceRegistroActual).getHorasAdicionales();
		} else if ("txtObservacion".equals(jrf.getName())) {
			valor = this.ficha.getDetalle().get(indiceRegistroActual).getObservaciones();
		} else if ("txtTotalHoras".equals(jrf.getName())) {
			valor = this.ficha.getTotalHorasTrabajadasFormato();
		} else if ("txtHAC".equals(jrf.getName())) {
			valor = this.ficha.getDetalle().get(indiceRegistroActual).getHorasDiariasAcumuladas();
		} else if ("txtHorasAdicionales".equals(jrf.getName())) {
			valor = this.ficha.getTotalHADiurno();
		} else if ("txtHorasExtras".equals(jrf.getName())) {
			valor = this.ficha.getTotalHorasExtrasFormato();
		} else if ("txtHAExtra".equals(jrf.getName())) {
			valor = this.ficha.getTotalHAExtra();
		}
		return valor;
	}

	public void addFicha(FichaAsistencia ficha) {
		this.ficha = ficha;
		this.numeroDiasMes = this.ficha.getDetalle().size();
	}
}
