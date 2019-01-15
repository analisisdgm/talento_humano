package com.migraciones.talentoHumano.utilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

// import com.migraciones.patrimonio.models.Administrador;

import javax.swing.LayoutStyle.ComponentPlacement;

import com.migraciones.talentoHumano.modelos.Administrador;

public class ImgBackground extends JPanel {

	private static final long serialVersionUID = 4354261269856052269L;
	private JLabel lblAdministrador = new JLabel("");

	/**
	 * Create the panel.
	 * 
	 * @throws UnknownHostException
	 */
	public ImgBackground(Administrador admin) throws UnknownHostException {
		lblAdministrador.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		lblAdministrador.setForeground(Color.WHITE);
		lblAdministrador.setText("USUARIO: " + admin.getLogin());
		String ip = InetAddress.getLocalHost().getHostAddress();
		JLabel lblIp = new JLabel("IP:" + ip);
		lblIp.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		lblIp.setForeground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(224, Short.MAX_VALUE).addGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING).addComponent(lblIp).addComponent(lblAdministrador))
						.addGap(77)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblAdministrador)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblIp).addContainerGap(246,
								Short.MAX_VALUE)));
		setLayout(groupLayout);
		// dimenciona la imagen segun la resolucion de pantalla
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		if (d.width > 1900) {
			this.setSize(1920, 1080 - 65);
		} else {
			this.setSize(d.width, d.height - 80);
		}

	}

	public void paintComponent(Graphics g) {
		Dimension tamanio = getSize();
		ImageIcon fondo = new ImageIcon(getClass().getResource(GlobalUtil.RUTA_IMAGEN_FONDO));
		g.drawImage(fondo.getImage(), 0, 0, tamanio.width, tamanio.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}
