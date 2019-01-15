package com.migraciones.talentoHumano.vistas.formularios;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.UnknownHostException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import com.migraciones.talentoHumano.app.TalentoHumano;
import com.migraciones.talentoHumano.modelos.Administrador;
import com.migraciones.talentoHumano.utilities.GlobalUtil;

public class AccesoVista {

	public JFrame frmIdentificarse;
	private JTextField txtLogin;
	private JPasswordField txtPassword;
	private JLabel lblMsg = new JLabel("");

	public void runAcceso() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AccesoVista.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccesoVista window = new AccesoVista();
					window.frmIdentificarse.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AccesoVista() {
		initialize();
		formatearVista();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIdentificarse = new JFrame();
		frmIdentificarse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIdentificarse.setResizable(false);
		frmIdentificarse.setTitle("IDENTIFICARSE");
		frmIdentificarse.setIconImage(
				Toolkit.getDefaultToolkit().getImage(AccesoVista.class.getResource(GlobalUtil.RUTA_FAVICON_SIGESTH)));
		frmIdentificarse.getContentPane().setBackground(Color.WHITE);
		frmIdentificarse.setBounds(100, 100, 439, 337);

		JPanel panelLogin = new JPanel();
		panelLogin.setBounds(40, 160, 354, 103);
		panelLogin.setBackground(Color.WHITE);

		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(23, 0, 400, 154);
		lblLogo.setIcon(new ImageIcon(AccesoVista.class.getResource("/com/migraciones/talentoHumano/graphics/logoMigraciones.png")));

		JPanel panelBoton = new JPanel();
		panelBoton.setBounds(0, 269, 433, 39);
		panelBoton.setBackground(new Color(43, 70, 97));

		JButton btnLogin = new JButton("Login");
		btnLogin.setPreferredSize(new Dimension(95, 27));
		panelBoton.add(btnLogin);
		btnLogin.setFont(new Font("Bitstream Vera Sans", Font.BOLD, 12));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));
		lblPassword.setIcon(null);

		txtPassword = new JPasswordField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					txtPasswordKeyPressed(e);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		txtLogin = new JTextField();
		txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtLoginKeyPressed(e);
			}
		});
		txtLogin.setColumns(10);

		JLabel lblLogin = new JLabel("LOGIN:");
		lblLogin.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));
		lblLogin.setIcon(null);
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);

		lblMsg.setForeground(Color.RED);
		GroupLayout gl_panelLogin = new GroupLayout(panelLogin);
		gl_panelLogin.setHorizontalGroup(gl_panelLogin.createParallelGroup(Alignment.TRAILING).addGroup(gl_panelLogin
				.createSequentialGroup()
				.addGroup(gl_panelLogin.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_panelLogin.createSequentialGroup().addContainerGap().addComponent(lblMsg,
										GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
						.addGroup(gl_panelLogin.createSequentialGroup()
								.addGap(16)
								.addGroup(gl_panelLogin.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelLogin.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblPassword))
										.addComponent(lblLogin))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panelLogin.createParallelGroup(Alignment.LEADING)
										.addComponent(txtLogin, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
										.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))))
				.addContainerGap()));
		gl_panelLogin.setVerticalGroup(gl_panelLogin.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLogin.createSequentialGroup().addContainerGap()
						.addComponent(lblMsg, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE).addGap(9)
						.addGroup(gl_panelLogin.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLogin))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelLogin.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword))
						.addContainerGap(21, Short.MAX_VALUE)));
		panelLogin.setLayout(gl_panelLogin);
		frmIdentificarse.getContentPane().setLayout(null);
		frmIdentificarse.getContentPane().add(lblLogo);
		frmIdentificarse.getContentPane().add(panelLogin);
		frmIdentificarse.getContentPane().add(panelBoton);

	}

	// /////////////////// MANEJO DE EVENTOS ///////////////////////////

	protected void txtLoginKeyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			txtPassword.requestFocus();
		}

	}

	private void btnActionPerformed(ActionEvent e) throws ClassNotFoundException, UnknownHostException {
		validarUsuario();
	}

	private void txtPasswordKeyPressed(KeyEvent evt) throws ClassNotFoundException, UnknownHostException {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			validarUsuario();
		}
	}

	// /////////////////// METODOS DEL SISTEMA ///////////////////////////

	private void formatearVista() {
		frmIdentificarse.setLocationRelativeTo(null);
		txtLogin.requestFocus();
	}

	@SuppressWarnings("deprecation")
	private void validarUsuario() throws ClassNotFoundException, UnknownHostException {
		TalentoHumano sistema = new TalentoHumano();
		Administrador administrador = new Administrador();
		if (validarCampos()) {
			administrador = sistema.validarIngreso(txtLogin.getText(), txtPassword.getText());
			if (administrador.getLogin() == null) {
				lblMsg.setText(GlobalUtil.MSG_NO_LOGIN);
			} else if (administrador.getTipoAdministrador() != 1) {
				lblMsg.setText("No tiene permisos de acceso.");
			} else {
				MenuPrincipalVista menuPrincipal = new MenuPrincipalVista(administrador);
				frmIdentificarse.dispose();
				menuPrincipal.setVisible(true);
			}
		} else {
			lblMsg.setText(GlobalUtil.MSG_CAMPOS_NO_LLENOS);
		}
	}

	@SuppressWarnings("deprecation")
	private boolean validarCampos() {
		boolean valido = true;
		if (txtLogin.getText().equals("")) {
			valido = false;
		} else if (txtPassword.getText().equals("")) {
			valido = false;
		}
		return valido;
	}

}
