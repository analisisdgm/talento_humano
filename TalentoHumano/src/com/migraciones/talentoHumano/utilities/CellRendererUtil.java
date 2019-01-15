package com.migraciones.talentoHumano.utilities;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class CellRendererUtil extends DefaultTableCellRenderer implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// establecemos el fondo blanco o vacío
		setBackground(null);
		// COnstructor de la clase DefaultTableCellRenderer
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		// Establecemos las filas que queremos cambiar el color. == 0 para pares
		// y != 0 para impares
		// boolean oddRow = (row % 2 == 0);

		// Creamos un color para las filas. El 200, 200, 200 en RGB es un color
		// gris
		Color c = new Color(200, 200, 200);

		// Si las filas son pares, se cambia el color a gris
		// if (oddRow) {
		// setBackground(c);
		// }
		if (String.valueOf(table.getValueAt(row, 2)).equals("SALIDA")) {
			setBackground(c);
		}
		return this;
	}

}