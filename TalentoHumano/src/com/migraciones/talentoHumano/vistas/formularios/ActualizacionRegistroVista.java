package com.migraciones.talentoHumano.vistas.formularios;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.migraciones.talentoHumano.app.TalentoHumano;
import com.migraciones.talentoHumano.utilities.GlobalUtil;

public class ActualizacionRegistroVista extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnActualizar;
	private JButton btnSalir;
	private JPanel panelBotones;
	private JLabel lblprogreso;
	private JProgressBar pbProgreso;
	private TalentoHumano sistema = new TalentoHumano();

	/**
	 * Create the frame.
	 */
	public ActualizacionRegistroVista() {
		setFrameIcon(new ImageIcon(ActualizacionRegistroVista.class.getResource(GlobalUtil.RUTA_FAVICON_MIGRACIONES)));
		setTitle("ACTUALIZACION RELOJ BIOMETRICO");
		setBackground(Color.WHITE);
		setBounds(100, 100, 435, 147);

		panelBotones = new JPanel();
		panelBotones.setBackground(new Color(43, 70, 97));

		pbProgreso = new JProgressBar();

		lblprogreso = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panelBotones, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup().addGap(10)
										.addComponent(pbProgreso, GroupLayout.PREFERRED_SIZE, 400,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(Alignment.TRAILING,
										groupLayout
												.createSequentialGroup().addContainerGap().addComponent(lblprogreso,
														GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
												.addContainerGap()));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap(13, Short.MAX_VALUE)
										.addComponent(lblprogreso, GroupLayout.PREFERRED_SIZE, 14,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(pbProgreso, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(panelBotones, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnActualizarActionPerformed(e);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnActualizar.setPreferredSize(new Dimension(95, 27));
		panelBotones.add(btnActualizar);

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSaliraActionPerformed(e);
			}
		});
		btnSalir.setPreferredSize(new Dimension(95, 27));
		panelBotones.add(btnSalir);
		getContentPane().setLayout(groupLayout);

	}

	// /////////////////// MANEJO DE EVENTOS ///////////////////////////

	private void btnActualizarActionPerformed(ActionEvent e) throws ClassNotFoundException, SQLException {
		inicio();
	}

	private void btnSaliraActionPerformed(ActionEvent e) {
		this.dispose();
	}

	private void inicio() {

		// Creamos un Thread para mejorar el ejemplo
		final Thread t;
		// Inicializamos
		t = new Thread(new Runnable() {
			// Implementamos el método run()
			@Override
			public void run() {
				// Permite mostrar el valor del progreso
				pbProgreso.setStringPainted(true);
				int x = 1;
				// Utilizamos un while para emular el valor mínimo y máximo
				// En este caso 0 - 100
				while (x <= 100) {
					// Asignamos valor a nuestro JProgressBar por cada siclo del
					// bucle
					if (x == 25) {
						try {
							sistema.cargarRegistrosCompleto();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					pbProgreso.setValue(x);
					// Valor que se mostrará en el JTextArea
					// salida.append("Progreso " + x + "%...\n");
					lblprogreso.setText("Progreso " + x + "%...\n");
					// Hacemos una parada de medio segundo por cada siclo while
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// Se incrementa el valor de x
					x++;
				}
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_ACTUALIZADOS_OK, "ATENCION",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		// Se ejecuta el Thread
		t.start();
	}
}
