package com.migraciones.talentoHumano.vistas.formularios;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.migraciones.talentoHumano.app.TalentoHumano;
import com.migraciones.talentoHumano.utilities.GlobalUtil;

import java.awt.Toolkit;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TipoRegistroModal extends JDialog {

	private static final long serialVersionUID = 1L;
	private int registro_id = 0;
	private String tipo;
	private ButtonGroup btnGroup = new ButtonGroup();
	private JRadioButton rbEntrada;
	private JRadioButton rbSalida;

	public TipoRegistroModal(String tipo, int id) {
		this.registro_id = id;
		this.tipo = tipo;
		setIconImage(Toolkit.getDefaultToolkit().getImage(TipoRegistroModal.class
				.getResource("/com/migraciones/talentoHumano/graphics/faviconGobierno.png")));
		setTitle("TIPO DE REGISTRO");
		getContentPane().setBackground(Color.WHITE);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Seleccione tipo de registro", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel.setBackground(Color.WHITE);

		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(new Color(43, 70, 97));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
				.addComponent(panelBotones, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE));
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(panelBotones,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addContainerGap()));

		rbEntrada = new JRadioButton("Entrada");
		panel.add(rbEntrada);

		rbSalida = new JRadioButton("Salida");
		panel.add(rbSalida);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelarActionPerformed(e);
			}
		});
		panelBotones.add(btnCancelar);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnAceptarActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panelBotones.add(btnAceptar);
		getContentPane().setLayout(groupLayout);
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 334, 128);
		setLocationRelativeTo(null);
		System.out.println();
		btnGroup.add(rbEntrada);
		btnGroup.add(rbSalida);
		marcarTipoRegistro();
	}

	protected void btnAceptarActionPerformed(ActionEvent e) throws ClassNotFoundException {
		TalentoHumano sistema = new TalentoHumano();
		if (rbEntrada.isSelected()) {
			if (sistema.actualizarTipoRegistro(this.registro_id, "I")) {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_ACTUALIZADOS_OK, "ATENCION",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_ACTUALIZADOS_FAIL, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			if (sistema.actualizarTipoRegistro(this.registro_id, "O")) {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_ACTUALIZADOS_OK, "ATENCION",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_ACTUALIZADOS_FAIL, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		this.dispose();

	}

	protected void btnCancelarActionPerformed(ActionEvent e) {
		this.dispose();

	}

	private void marcarTipoRegistro() {
		if (this.tipo.equals("I")) {
			rbEntrada.setSelected(true);
		} else {
			rbSalida.setSelected(true);
		}
	}

}
