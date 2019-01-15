package com.migraciones.talentoHumano.utilities;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class SplashScreen extends JPanel {

	private static final long serialVersionUID = 8273933711205653216L;
	ImageIcon imagen;
	private JProgressBar pbProgreso = new JProgressBar();

	/**
	 * Create the panel.
	 */
	public SplashScreen() {

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(pbProgreso,
				Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup().addContainerGap(286, Short.MAX_VALUE).addComponent(pbProgreso,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
		setLayout(groupLayout);
		setFormat();
	}

	private void setFormat() {
		imagen = new ImageIcon(getClass().getResource("/com/migraciones/patrimonio/graphics/splash.gif"));
		this.setSize(imagen.getIconWidth(), imagen.getIconHeight());
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imagen.getImage(), 0, 0, imagen.getIconWidth(), imagen.getIconHeight(), this);
		this.setOpaque(false);
	}

	public void velocidadCarga() throws InterruptedException {
		for (int i = 0; i <= 100; i++) {
			Thread.sleep(40);
			pbProgreso.setForeground(Color.darkGray);
			pbProgreso.setValue(i);
		}
	}
}
