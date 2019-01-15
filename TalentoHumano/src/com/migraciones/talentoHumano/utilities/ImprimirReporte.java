package com.migraciones.talentoHumano.utilities;

import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.migraciones.talentoHumano.datasources.CredencialDS;
import com.migraciones.talentoHumano.datasources.FaltaDisciplinariaDS;
import com.migraciones.talentoHumano.datasources.FichaAsistenciaDS;
import com.migraciones.talentoHumano.datasources.JustificacionListaDS;
import com.migraciones.talentoHumano.datasources.JustificacionPersonalDS;
import com.migraciones.talentoHumano.modelos.Credencial;
import com.migraciones.talentoHumano.modelos.FaltaDisciplinaria;
import com.migraciones.talentoHumano.modelos.FichaAsistencia;
import com.migraciones.talentoHumano.modelos.JustificacionLista;
import com.migraciones.talentoHumano.modelos.JustificacionPersonal;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ImprimirReporte {

//	public boolean imprimirPersonales(ArrayList<Personal> personales) {
//		// Formulario Ficha de Asistencia
//		FaltaDisciplinariaDS datasource = new FaltaDisciplinariaDS();
//		datasource.addFaltas(faltas);
//		try {
//			JasperReport masterReport = (JasperReport) JRLoader.loadObjectFromFile(GlobalUtil.RUTA_REPORTE_FD);
//			JasperPrint masterPrint = JasperFillManager.fillReport(masterReport, null, datasource);
//			JasperViewer ventana = new JasperViewer(masterPrint, false);
//			ventana.setTitle("Faltas disciplinarias");
//			ventana.setVisible(true);
//		} catch (JRException e) {
//			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_GLOBAL_ERROR + e.toString(), "ATENCION ",
//					JOptionPane.INFORMATION_MESSAGE);
//		}
//		return true;
//	}

	public boolean imprimirCredencial(Credencial datos) throws ParseException {
		boolean control = true;
		try {
			ArrayList<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
			JasperReport masterReportFront = (JasperReport) JRLoader
					.loadObjectFromFile(GlobalUtil.RUTA_CREDENCIAL_FRONT);
			JasperReport masterReportBack = (JasperReport) JRLoader.loadObjectFromFile(GlobalUtil.RUTA_CREDENCIAL_BACK);

			CredencialDS datasourceFront = new CredencialDS();
			datasourceFront.addCredencial(datos);
			JasperPrint masterPrintFront = JasperFillManager.fillReport(masterReportFront, null, datasourceFront);
			jasperPrintList.add(masterPrintFront);

			CredencialDS datasourceBack = new CredencialDS();
			datasourceBack.addCredencial(datos);
			JasperPrint masterPrintBack = JasperFillManager.fillReport(masterReportBack, null, datasourceBack);
			jasperPrintList.add(masterPrintBack);

			JasperPrint jasperPrint = jasperPrintList.get(0);

			for (int i = 1; i < jasperPrintList.size(); i++) {
				java.util.List<JRPrintPage> paginas = jasperPrintList.get(i).getPages();
				for (JRPrintPage pagina : paginas) {
					jasperPrint.addPage(pagina);
				}
			}

			JasperViewer ventana = new JasperViewer(jasperPrintList.get(0), false);
			ventana.setTitle("CREDENCIAL");
			ventana.setVisible(true);
		} catch (JRException e) {
			control = false;
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_GLOBAL_ERROR + e.toString(), "ATENCION ",
					JOptionPane.INFORMATION_MESSAGE);
		}

		return control;
	}

	public boolean imprimirFichaAsistencia(FichaAsistencia ficha) {
		// Formulario Ficha de Asistencia
		FichaAsistenciaDS datasource = new FichaAsistenciaDS();
		datasource.addFicha(ficha);
		try {
			JasperReport masterReport = (JasperReport) JRLoader.loadObjectFromFile(GlobalUtil.RUTA_REPORTE_FA);
			JasperPrint masterPrint = JasperFillManager.fillReport(masterReport, null, datasource);
			JasperViewer ventana = new JasperViewer(masterPrint, false);
			ventana.setTitle("Ficha de Asistencia");
			ventana.setVisible(true);
		} catch (JRException e) {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_GLOBAL_ERROR + e.toString(), "ATENCION ",
					JOptionPane.INFORMATION_MESSAGE);
		}
		return true;
	}

	public boolean imprimirFichaAsistenciaLista(ArrayList<FichaAsistencia> fichas) {
		boolean control = false;
		if (fichas.size() != 0) {
			control = true;
			try {
				ArrayList<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
				JasperReport masterReport = (JasperReport) JRLoader.loadObjectFromFile(GlobalUtil.RUTA_REPORTE_FA);
				boolean tieneRegistro = false;
				for (FichaAsistencia ficha : fichas) {
					if (ficha.isTieneRegistros()) {
						tieneRegistro = true;
						FichaAsistenciaDS datasource = new FichaAsistenciaDS();
						datasource.addFicha(ficha);
						JasperPrint masterPrint = JasperFillManager.fillReport(masterReport, null, datasource);
						jasperPrintList.add(masterPrint);
					}
				}
				if (tieneRegistro) {
					JasperPrint jasperPrint = jasperPrintList.get(0);
					for (int i = 1; i < jasperPrintList.size(); i++) {
						java.util.List<JRPrintPage> paginas = jasperPrintList.get(i).getPages();

						for (JRPrintPage pagina : paginas) {
							jasperPrint.addPage(pagina);
						}
					}

					JasperViewer ventana = new JasperViewer(jasperPrintList.get(0), false);
					ventana.setTitle("Ficha de Asistencia");
					ventana.setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "No hay registros", "ATENCION ",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (JRException e) {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_GLOBAL_ERROR + e.toString(), "ATENCION ",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		return control;
	}

	public boolean imprimirFaltasDisciplinarias(ArrayList<FaltaDisciplinaria> faltas) {
		// Formulario Ficha de Asistencia
		FaltaDisciplinariaDS datasource = new FaltaDisciplinariaDS();
		datasource.addFaltas(faltas);
		try {
			JasperReport masterReport = (JasperReport) JRLoader.loadObjectFromFile(GlobalUtil.RUTA_REPORTE_FD);
			JasperPrint masterPrint = JasperFillManager.fillReport(masterReport, null, datasource);
			JasperViewer ventana = new JasperViewer(masterPrint, false);
			ventana.setTitle("Faltas disciplinarias");
			ventana.setVisible(true);
		} catch (JRException e) {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_GLOBAL_ERROR + e.toString(), "ATENCION ",
					JOptionPane.INFORMATION_MESSAGE);
		}
		return true;
	}

	public boolean imprimirJustificaciones(JustificacionPersonal justificacion) {
		// Formulario Ficha de Asistencia
		JustificacionPersonalDS datasource = new JustificacionPersonalDS();
		datasource.addJustificacion(justificacion);
		try {
			JasperReport masterReport = (JasperReport) JRLoader.loadObjectFromFile(GlobalUtil.RUTA_REPORTE_JP);
			JasperPrint masterPrint = JasperFillManager.fillReport(masterReport, null, datasource);
			JasperViewer ventana = new JasperViewer(masterPrint, false);
			ventana.setTitle("Ficha de Justificaciones");
			ventana.setVisible(true);
		} catch (JRException e) {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_GLOBAL_ERROR + e.toString(), "ATENCION ",
					JOptionPane.INFORMATION_MESSAGE);
		}
		return true;
	}

	public boolean imprimirJustificaciones(JustificacionLista justificacion) {
		// Formulario Ficha de Asistencia
		JustificacionListaDS datasource = new JustificacionListaDS();
		datasource.addJustificacion(justificacion);
		try {
			JasperReport masterReport = (JasperReport) JRLoader.loadObjectFromFile(GlobalUtil.RUTA_REPORTE_JL);
			JasperPrint masterPrint = JasperFillManager.fillReport(masterReport, null, datasource);
			JasperViewer ventana = new JasperViewer(masterPrint, false);
			ventana.setTitle("Ficha de Justificaciones");
			ventana.setVisible(true);
		} catch (JRException e) {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_GLOBAL_ERROR + e.toString(), "ATENCION ",
					JOptionPane.INFORMATION_MESSAGE);
		}
		return true;
	}
}
