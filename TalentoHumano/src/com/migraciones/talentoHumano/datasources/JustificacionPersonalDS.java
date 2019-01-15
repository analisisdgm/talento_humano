package com.migraciones.talentoHumano.datasources;

import com.migraciones.talentoHumano.modelos.JustificacionPersonal;
import com.migraciones.talentoHumano.utilities.GlobalUtil;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class JustificacionPersonalDS implements JRDataSource {
	private JustificacionPersonal justificacion;
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
			valor = this.justificacion.getCedula();
		} else if ("txtNombres".equals(jrf.getName())) {
			valor = this.justificacion.getNombres();
		} else if ("txtApellidos".equals(jrf.getName())) {
			valor = this.justificacion.getApellidos();
		} else if ("txtPeriodo".equals(jrf.getName())) {
			valor = this.justificacion.getPeriodo();
		} else if ("txtEstado".equals(jrf.getName())) {
			if (this.justificacion.getEstado().equals("A")) {
				valor = "ACTIVO";
			} else {
				valor = "INACTIVO";
			}
		} else if ("txtDireccionImagen".equals(jrf.getName())) {
			valor = GlobalUtil.RUTA_REPORTE_IMG_CARNET;
		} else if ("txtPersonalImagen".equals(jrf.getName())) {
			valor = this.justificacion.getImagen();
		} else if ("txtItem".equals(jrf.getName())) {
			valor = Integer.toString(indiceRegistroActual + 1);
		} else if ("txtFechaDesde".equals(jrf.getName())) {
			valor = this.justificacion.getDetalles().get(indiceRegistroActual).getFechaDesde();
		} else if ("txtFechaHasta".equals(jrf.getName())) {
			valor = this.justificacion.getDetalles().get(indiceRegistroActual).getFechaHasta();
		} else if ("txtDescripcion".equals(jrf.getName())) {
			valor = this.justificacion.getDetalles().get(indiceRegistroActual).getDescripcion();
		}
		return valor;
	}

	public void addJustificacion(JustificacionPersonal justificaciones) {
		this.justificacion = justificaciones;
		this.numeroDiasMes = this.justificacion.getDetalles().size();
	}
}
