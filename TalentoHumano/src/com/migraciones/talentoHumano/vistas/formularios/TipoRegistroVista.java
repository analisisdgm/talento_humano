package com.migraciones.talentoHumano.vistas.formularios;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

import com.migraciones.talentoHumano.app.TalentoHumano;
import com.migraciones.talentoHumano.modelos.Registro;
import com.migraciones.talentoHumano.utilities.CellRendererUtil;
import com.migraciones.talentoHumano.utilities.FechaUtil;
import com.migraciones.talentoHumano.utilities.GlobalUtil;
import com.migraciones.talentoHumano.views.componentesGUI.ModeloTablaUtil;
import com.migraciones.talentoHumano.views.componentesGUI.PanelDatosUtil;
import com.toedter.calendar.JDateChooser;

public class TipoRegistroVista extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	public PanelDatosUtil panelDatos;
	private ModeloTablaUtil modelo = new ModeloTablaUtil();
	private TalentoHumano sistema = new TalentoHumano();
	private ArrayList<Registro> registros = new ArrayList<Registro>();
	private JTextField txtCedula;
	private JTextField txtNombres;
	private JDateChooser dcFecha;
	private JTable tbRegistros;

	/**
	 * Create the frame.
	 * 
	 * @throws ClassNotFoundException
	 * @throws ParseException
	 */
	public TipoRegistroVista() throws ClassNotFoundException, ParseException {
		getContentPane().setBackground(Color.WHITE);
		setTitle("PERSONAL - MODIFICACION ENTRADA / SALIDA");
		setFrameIcon(new ImageIcon(AncestroVista.class.getResource(GlobalUtil.RUTA_FAVICON_MIGRACIONES)));
		setBounds(100, 100, 563, 333);

		panelDatos = new PanelDatosUtil();

		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(new Color(43, 70, 97));
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSalirActionPerformed(e);
			}
		});
		btnSalir.setPreferredSize(new Dimension(95, 27));

		JPanel panelParametros = new JPanel();
		panelParametros.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Parametro de busqueda",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelParametros.setBackground(Color.WHITE);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelDatos, GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addGap(16)
								.addComponent(panelParametros, GroupLayout.PREFERRED_SIZE, 517,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(96, Short.MAX_VALUE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
						.addComponent(panelBotones, GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panelDatos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelParametros, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelBotones, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		tbRegistros = new JTable();
		tbRegistros.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tbRegistrosKeyPressed(e);
			}
		});
		tbRegistros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tbRegistrosMouseClicked(e);
			}
		});
		scrollPane.setViewportView(tbRegistros);

		txtCedula = new JTextField();
		txtCedula.setBounds(129, 65, 87, 20);
		txtCedula.setEnabled(false);
		txtCedula.setColumns(10);

		JLabel label_4 = new JLabel("Cedula:");
		label_4.setBounds(15, 68, 41, 14);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblOficina = new JLabel("Fecha de Registro:");
		lblOficina.setBounds(15, 33, 104, 14);
		lblOficina.setFont(new Font("Tahoma", Font.BOLD, 11));

		txtNombres = new JTextField();
		txtNombres.setBounds(258, 65, 244, 20);
		txtNombres.setEnabled(false);
		txtNombres.setColumns(10);

		dcFecha = new JDateChooser();
		dcFecha.setBounds(129, 27, 87, 20);
		dcFecha.setDateFormatString("dd/MM/yyyy");
		dcFecha.setDate(FechaUtil.getFechaActualDate());
		panelParametros.setLayout(null);
		panelParametros.add(lblOficina);
		panelParametros.add(label_4);
		panelParametros.add(txtCedula);
		panelParametros.add(txtNombres);
		panelParametros.add(dcFecha);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PersonalModal vista;
				try {
					vista = new PersonalModal("0");
					vista.setVisible(true);
					cargarCampoCedula(vista);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setIcon(
				new ImageIcon(TipoRegistroVista.class.getResource("/com/migraciones/talentoHumano/graphics/lupa.png")));
		button.setBounds(226, 62, 23, 23);
		panelParametros.add(button);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnBuscarActionPerformed(e);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBuscar.setPreferredSize(new Dimension(150, 27));
		btnBuscar.setMinimumSize(new Dimension(150, 27));
		btnBuscar.setMaximumSize(new Dimension(95, 27));
		GroupLayout gl_panelBotones = new GroupLayout(panelBotones);
		gl_panelBotones.setHorizontalGroup(gl_panelBotones.createParallelGroup(Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_panelBotones.createSequentialGroup().addContainerGap(176, Short.MAX_VALUE)
						.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnSalir,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(171)));
		gl_panelBotones.setVerticalGroup(gl_panelBotones.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelBotones.createSequentialGroup().addGap(8)
						.addGroup(gl_panelBotones.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSalir, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE).addComponent(
										btnBuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		panelBotones.setLayout(gl_panelBotones);
		getContentPane().setLayout(groupLayout);
		panelDatos.cargarDatos();
		formatearTabla();
		setCellRender(tbRegistros);

	}

	// //////////////////// MANEJO DE EVENTOS //////////////

	protected void tbRegistrosKeyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			formularioModificacion();
		}
	}

	protected void tbRegistrosMouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			formularioModificacion();
		}
	}

	protected void btnBuscarActionPerformed(ActionEvent e) throws ParseException, ClassNotFoundException {
		if (verificarCampos()) {
			cargarDatosTabla();
		}
	}

	protected void btnSalirActionPerformed(ActionEvent e) {
		this.dispose();
	}

	// //////////////////// METODOS DEL FORMULARIO //////////////

	private void formularioModificacion() {
		TipoRegistroModal vista;
		String tipo = (String) tbRegistros.getValueAt(tbRegistros.getSelectedRow(), 2);
		int id_aux = (int) tbRegistros.getValueAt(tbRegistros.getSelectedRow(), 0);
		if (tipo.equals("ENTRADA")) {
			tipo = "I";
		} else {
			tipo = "O";
		}
		vista = new TipoRegistroModal(tipo, id_aux);
		vista.setVisible(true);
		limpiarTabla();
		buscarFuncionario();
		cargarDatosTabla();
	}

	private void formatearTabla() throws ClassNotFoundException {
		modelo.addColumn("UUID");
		modelo.addColumn("REGISTRO");
		modelo.addColumn("TIPO DE REGISTRO");
		modelo.addColumn("RELOJ");
		tbRegistros.setModel(modelo);
		tbRegistros.getColumnModel().getColumn(0).setMinWidth(20);
		tbRegistros.getColumnModel().getColumn(1).setMinWidth(70);
		tbRegistros.getColumnModel().getColumn(2).setMinWidth(30);
		tbRegistros.getColumnModel().getColumn(3).setMinWidth(30);
	}

	private void cargarDatosTabla() {
		limpiarTabla();
		Object[] fila = new Object[6];
		SimpleDateFormat sdfReg = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String formatoFecha;
		this.registros = this.sistema.obtenerRegistroFecha(txtCedula.getText().trim(), dcFecha.getDate());
		for (Registro reg : this.registros) {
			formatoFecha = sdfReg.format(reg.getRegistro());
			fila[0] = reg.getId();
			fila[1] = formatoFecha;
			if (reg.getTipoRegistro().equals("I")) {
				fila[2] = "ENTRADA";
			} else if (reg.getTipoRegistro().equals("O")) {
				fila[2] = "SALIDA";
			} else {
				fila[2] = "OTRO ESTADO";
			}
			fila[3] = reg.getRelojCodigo();
			modelo.addRow(fila);
			tbRegistros.setModel(modelo);
		}

	}

	private void limpiarTabla() {
		while (tbRegistros.getRowCount() != 0) {
			modelo.removeRow(0);
		}
	}

	private void cargarCampoCedula(PersonalModal vista) {
		txtCedula.setText(vista.getCedula());
		txtNombres.setText(vista.getPersonal());
	}

	private boolean verificarCampos() throws ParseException {
		boolean control = true;
		if (txtCedula.getText().equals("") || dcFecha.getDate() == null) {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION", JOptionPane.ERROR_MESSAGE);
			control = false;
		}
		return control;
	}

	private boolean buscarFuncionario() {
		boolean control = false;
		this.registros = this.sistema.obtenerRegistroFecha(txtCedula.getText().trim(), dcFecha.getDate());
		return control;
	}

	public void setCellRender(JTable table) {
		Enumeration<TableColumn> en = table.getColumnModel().getColumns();
		while (en.hasMoreElements()) {
			TableColumn tc = en.nextElement();
			tc.setCellRenderer(new CellRendererUtil());
		}
	}
}
