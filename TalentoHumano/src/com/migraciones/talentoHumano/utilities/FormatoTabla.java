package com.migraciones.talentoHumano.utilities;

/*
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatoTabla extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -3546547482961811368L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
			int row, int column) {
		// SI EN CADA FILA DE LA TABLA LA CELDA 5 ES IGUAL A ACTIVO COLOR negro
		if (String.valueOf(table.getValueAt(row, 5)).equals("ENTRADA")) {
			// setBackground(Color.RED);
			setForeground(Color.GREEN);
		} // SI NO ES ACTIVO ENTONCES COLOR ROJO
		else {

			// setBackground(Color.red);
			setForeground(Color.RED);
		}

		super.getTableCellRendererComponent(table, value, selected, focused, row, column);
		return this;
	}
}
