package com.migraciones.talentoHumano.views.componentesGUI;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.awt.Dimension;

public class PanelBotonesUtil extends JPanel {

	private static final long serialVersionUID = -7943305183131165023L;

	public JButton btnAgregar = new JButton("Agregar");
	public JButton btnModificar = new JButton("Modificar");
	public JButton btnEliminar = new JButton("Eliminar");
	public JButton btnGuardar = new JButton("Guardar");
	public JButton btnCancelar = new JButton("Cancelar");
	public JButton btnVer = new JButton("Ver");
	public JButton btnImprimir = new JButton("Imprimir");
	public JButton btnSalir = new JButton("Salir");

	public PanelBotonesUtil() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setBackground(new Color(43, 70, 97));

		btnAgregar.setPreferredSize(new Dimension(85, 27));
		add(btnAgregar);
		btnModificar.setPreferredSize(new Dimension(85, 27));
		add(btnModificar);
		btnEliminar.setPreferredSize(new Dimension(85, 27));
		add(btnEliminar);
		btnGuardar.setEnabled(false);
		btnGuardar.setPreferredSize(new Dimension(85, 27));
		add(btnGuardar);
		btnCancelar.setEnabled(false);
		btnCancelar.setPreferredSize(new Dimension(85, 27));
		add(btnCancelar);
		btnVer.setPreferredSize(new Dimension(85, 27));
		add(btnVer);
		btnImprimir.setPreferredSize(new Dimension(85, 27));
		add(btnImprimir);
		btnSalir.setPreferredSize(new Dimension(85, 27));
		add(btnSalir);

	}

	public void habilitar() {
		btnAgregar.setEnabled(false);
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnVer.setEnabled(false);
		btnSalir.setEnabled(false);
		
		btnGuardar.setEnabled(true);
		btnCancelar.setEnabled(true);
	}

	public void deshabilitar() {
		btnAgregar.setEnabled(true);
		btnModificar.setEnabled(true);
		btnEliminar.setEnabled(true);
		btnVer.setEnabled(true);
		
		btnSalir.setEnabled(true);
		btnGuardar.setEnabled(false);
		btnCancelar.setEnabled(false);
	}

}
