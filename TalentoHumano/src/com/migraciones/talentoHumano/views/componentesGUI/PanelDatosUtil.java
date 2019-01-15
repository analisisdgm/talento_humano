package com.migraciones.talentoHumano.views.componentesGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.migraciones.talentoHumano.utilities.FechaUtil;
import com.migraciones.talentoHumano.vistas.formularios.MenuPrincipalVista;

public class PanelDatosUtil extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblUsuario = new JLabel("Usuario:");
	private JLabel lblFecha = new JLabel("Fecha:");
	private JLabel lblTerminal = new JLabel("Terminal:");
	private JLabel lblUsuarioVar = new JLabel("");
	private JLabel lblFechaVar = new JLabel("");
	private JLabel lblTerminalVar = new JLabel("");

	public PanelDatosUtil() {
		setBackground(new Color(43, 70, 97));
		lblUsuario.setFont(new Font("DejaVu Sans", Font.BOLD, 12));

		add(lblUsuario);
		lblUsuarioVar.setForeground(Color.WHITE);

		add(lblUsuarioVar);
		lblFecha.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		add(lblFecha);
		lblFechaVar.setForeground(Color.WHITE);

		add(lblFechaVar);
		lblTerminal.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		add(lblTerminal);

		lblUsuario.setForeground(Color.WHITE);
		lblFecha.setForeground(Color.WHITE);
		lblTerminal.setForeground(Color.WHITE);
		lblTerminalVar.setForeground(Color.WHITE);

		add(lblTerminalVar);

	}

	public void cargarDatos() {
		lblFechaVar.setText(FechaUtil.getFechaActualString());
		lblTerminalVar.setText("DESKTOP");
		lblUsuarioVar.setText(MenuPrincipalVista.administrador.getLogin());
	}

}
