package com.migraciones.talentoHumano.views.componentesGUI;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;

import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;

public class PanelBuscadorUtil extends JPanel {

	private static final long serialVersionUID = -2066064428794198373L;
	public JTextField txtDescripcion;
	@SuppressWarnings("rawtypes")
	public JComboBox cbOpciones = new JComboBox();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PanelBuscadorUtil() {
		setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 1,
				true), "BUSCADOR", TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(255, 255, 255)));
		setBackground(new Color(64, 64, 66));

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		cbOpciones.setModel(new DefaultComboBoxModel(
				new String[] { "-SELECCIONE-" }));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addComponent(cbOpciones, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txtDescripcion, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
					.addGap(16))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbOpciones, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(10, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
