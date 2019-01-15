package com.migraciones.talentoHumano.vistas.formularios;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import com.migraciones.talentoHumano.utilities.GlobalUtil;
import com.migraciones.talentoHumano.views.componentesGUI.PanelBotonesUtil;
import com.migraciones.talentoHumano.views.componentesGUI.PanelCamposUtil;
import com.migraciones.talentoHumano.views.componentesGUI.PanelDatosUtil;

public abstract class AncestroVista extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	public PanelDatosUtil panelDatos = new PanelDatosUtil();
	public PanelBotonesUtil panelBotones;
	public PanelCamposUtil panelCampos;

	/**
	 * Create the frame.
	 */
	public AncestroVista() {
		setFrameIcon(new ImageIcon(AncestroVista.class.getResource(GlobalUtil.RUTA_FAVICON_MIGRACIONES)));
		setBounds(100, 100, 780, 504);
		getContentPane().add(panelDatos, BorderLayout.NORTH);
		
		panelBotones = new PanelBotonesUtil();
		panelBotones.btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSalirActionPerformed(e);
			}
		});
		panelBotones.btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnVerActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		panelBotones.btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnImprimirActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		panelBotones.btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelarActionPerformed(e);
			}
		});
		panelBotones.btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnGuardarActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panelBotones.btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnBorrarActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panelBotones.btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnEditarActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panelBotones.btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAgregarActionPerformed(e);
			}
		});
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		panelCampos = new PanelCamposUtil();
		getContentPane().add(panelCampos, BorderLayout.CENTER);
	}

	// /////////////////// MANEJO DE EVENTOS ///////////////////////////

	protected void btnAgregarActionPerformed(ActionEvent e) {
		this.agregarObjeto();
	}

	protected void btnEditarActionPerformed(ActionEvent e) throws ClassNotFoundException {
		this.editarObjeto();
	}

	protected void btnBorrarActionPerformed(ActionEvent e) throws ClassNotFoundException {
		this.borrarObjeto();
	}

	protected void btnGuardarActionPerformed(ActionEvent e)
			throws ClassNotFoundException, ParseException, SQLException {
		this.guardarObjeto();
	}

	protected void btnCancelarActionPerformed(ActionEvent e) {
		this.cancelarOperacion();
	}

	protected void btnVerActionPerformed(ActionEvent e) throws ClassNotFoundException {
		this.verObjeto();
	}

	protected void btnImprimirActionPerformed(ActionEvent e) throws ClassNotFoundException {
		this.imprimirObjeto();
	}

	protected void btnSalirActionPerformed(ActionEvent e) {
		this.salirFormulario();
	}

	// //////// METODOS DEL SISTEMA ABSTRACTOS ///////////////////

	protected abstract void agregarObjeto();

	protected abstract void editarObjeto() throws ClassNotFoundException;

	protected abstract void borrarObjeto() throws ClassNotFoundException;

	protected abstract void guardarObjeto() throws ClassNotFoundException, ParseException, SQLException;

	protected abstract void cancelarOperacion();

	protected abstract void verObjeto() throws ClassNotFoundException;

	protected abstract void imprimirObjeto() throws ClassNotFoundException;

	protected void salirFormulario() {
		this.dispose();
	}

}
