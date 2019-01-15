package com.migraciones.talentoHumano.vistas.formularios;

import java.awt.Dimension;

import javax.swing.JFileChooser;

import java.awt.SystemColor;

public class CargaImagenModal extends AncestroModal {

	private static final long serialVersionUID = 1L;
	public static JFileChooser jfchImagen;

	public CargaImagenModal() throws ClassNotFoundException {
		getContentPane().setBackground(SystemColor.control);
		getContentPane().setLayout(null);

		jfchImagen = new JFileChooser();
		jfchImagen.setBounds(0, 0, 623, 397);
		getContentPane().add(jfchImagen);
		setSize(new Dimension(638, 427));
		setTitle("CARGAR FOTO");
	}

	// /////////////////// METODOS DEL SISTEMA ///////////////////////////

	public JFileChooser getFileChooser() {
		return jfchImagen;
	}
}
