package com.migraciones.talentoHumano.views.componentesGUI;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaUtil extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean isCellEditable(int row, int column) {
		// all cells false
		return false;
	}
	

}
