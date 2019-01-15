package com.migraciones.talentoHumano.vistas.formularios;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.migraciones.talentoHumano.app.TalentoHumano;
import com.migraciones.talentoHumano.modelos.Administrador;
import com.migraciones.talentoHumano.utilities.Encrypt;

import javax.swing.SwingConstants;

public class CambioPasswordVista extends AncestroVista {
	private Administrador administrador = new Administrador();
	private static final long serialVersionUID = 1L;
	private JTextField txtCedula;
	private JTextField txtFuncionario;
	private JPasswordField txtPasswd;
	private JPasswordField txtPasswdRepeat;

	public CambioPasswordVista(Administrador administrador) {
		this.administrador = administrador;
		setSize(new Dimension(452, 230));
		setTitle("ADMINISTRADOR - CAMBIO DE CONTRASE\u00D1A");

		JLabel lblFuncionario = new JLabel("Administrador:");
		lblFuncionario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuncionario.setBounds(18, 32, 98, 16);
		lblFuncionario.setFont(new Font("DejaVu Sans", Font.BOLD, 12));

		txtCedula = new JTextField();
		txtCedula.setBounds(130, 30, 86, 20);
		txtCedula.setEnabled(false);
		txtCedula.setColumns(10);

		txtFuncionario = new JTextField();
		txtFuncionario.setBounds(226, 30, 200, 20);
		txtFuncionario.setEnabled(false);
		txtFuncionario.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasea.setBounds(18, 58, 98, 16);
		lblContrasea.setFont(new Font("DejaVu Sans", Font.BOLD, 12));

		txtPasswd = new JPasswordField();
		txtPasswd.setBounds(131, 56, 295, 20);
		txtPasswd.setEnabled(false);

		JLabel lblRepitacontrasea = new JLabel("Repita Contrase\u00F1a:");
		lblRepitacontrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRepitacontrasea.setBounds(10, 85, 108, 16);
		lblRepitacontrasea.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		panelDatos.cargarDatos();
		panelBotones.btnAgregar.setVisible(false);
		panelBotones.btnEliminar.setVisible(false);
		panelBotones.btnVer.setVisible(false);
		panelBotones.btnImprimir.setVisible(false);
		txtCedula.setText(this.administrador.getCedula());
		txtFuncionario.setText(this.administrador.getLogin());
		txtPasswdRepeat = new JPasswordField();
		txtPasswdRepeat.setBounds(131, 82, 295, 20);
		txtPasswdRepeat.setEnabled(false);
		panelCampos.setLayout(null);
		panelCampos.add(lblFuncionario);
		panelCampos.add(txtCedula);
		panelCampos.add(txtFuncionario);
		panelCampos.add(lblRepitacontrasea);
		panelCampos.add(lblContrasea);
		panelCampos.add(txtPasswdRepeat);
		panelCampos.add(txtPasswd);
	}

	/**
	 * 
	 */
	// /////////////////// METODOS DEL SISTEMA ///////////////////////////
	@Override
	protected void agregarObjeto() {
		// Este metodo no se utiliza
	}

	@Override
	protected void editarObjeto() {
		// this.operacion = GlobalUtil.editarObjeto;
		this.setTitle("ADMINISTRADOR - CAMBIO DE CONTRASE\u00D1A (MODIFICANDO)");
		habilitarCamposEditar();
		panelBotones.habilitar();

	}

	@Override
	protected void borrarObjeto() {
		// Este metodo no se utiliza
	}

	@Override
	protected void cancelarOperacion() {
		this.setTitle("ADMINISTRADOR - CAMBIO DE CONTRASE\u00D1A");
		deshabilitarCamposEditar();
		this.panelBotones.deshabilitar();

	}

	@SuppressWarnings("deprecation")
	@Override
	protected void guardarObjeto() throws ClassNotFoundException {
		if (txtPasswd.getText().equals("") || txtPasswdRepeat.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "no puede haber campos vacios", "ATENCION", JOptionPane.ERROR_MESSAGE);
		} else {
			if (txtPasswd.getText().equals(txtPasswdRepeat.getText())) {
				TalentoHumano sistema = new TalentoHumano();
				String password = Encrypt.getStringMessageDigest(txtPasswd.getText(), Encrypt.MD5);
				this.administrador.setPassword(password);
				sistema.actualizarAdministrador(this.administrador);
				JOptionPane.showMessageDialog(null, "Contraseña guardada correctamente", "ATENCION",
						JOptionPane.INFORMATION_MESSAGE);
				deshabilitarCamposEditar();
				this.panelBotones.deshabilitar();
			} else {
				JOptionPane.showMessageDialog(null, "error al guardar verifique que las contraseÃ±as sean iguales ",
						"ATENCION", JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	private void habilitarCamposEditar() {
		txtPasswd.setEnabled(true);
		txtPasswdRepeat.setEnabled(true);
		txtPasswd.requestFocus();
	}

	private void deshabilitarCamposEditar() {
		txtPasswd.setText("");
		txtPasswdRepeat.setText("");
		txtPasswd.setEnabled(false);
		txtPasswdRepeat.setEnabled(false);

	}

	@Override
	protected void verObjeto() throws ClassNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void imprimirObjeto() throws ClassNotFoundException {
		// TODO Auto-generated method stub

	}
}
