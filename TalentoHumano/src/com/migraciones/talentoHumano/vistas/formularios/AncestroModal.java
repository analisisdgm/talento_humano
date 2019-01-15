package com.migraciones.talentoHumano.vistas.formularios;

import javax.swing.JDialog;

public class AncestroModal extends JDialog {

	private static final long serialVersionUID = 1L;

	public AncestroModal() {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 660, 415);
		setLocationRelativeTo(null);

	}

}
