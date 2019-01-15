package com.migraciones.talentoHumano.vistas.formularios;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.migraciones.talentoHumano.app.TalentoHumano;
import com.migraciones.talentoHumano.modelos.FichaAsistencia;
import com.migraciones.talentoHumano.modelos.Personal;
import com.migraciones.talentoHumano.utilities.FechaUtil;
import com.migraciones.talentoHumano.utilities.GlobalUtil;
import com.migraciones.talentoHumano.views.componentesGUI.PanelDatosUtil;
import com.toedter.calendar.JDateChooser;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;

public class ReporteVista extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	public PanelDatosUtil panelDatos;
	private FechaUtil util = new FechaUtil();
	private TalentoHumano sistema = new TalentoHumano();
	@SuppressWarnings("rawtypes")
	private JComboBox cbMeses;
	private JSpinner spAnhos;
	private JTextField txtCedula;
	private JDateChooser dcFechaDesde = new JDateChooser();
	private JDateChooser dcFechaHasta = new JDateChooser();
	private JTextField txtNombres;
	private JPanel panelRegMensual;
	private JPanel panelRegPeriodo;
	private int opcionBusqueda = 1;
	private JLabel lblImagen;
	private Date fechaDesde = new Date();
	private Date fechaHasta = new Date();
	private ArrayList<Personal> personales = new ArrayList<Personal>();
	private ArrayList<FichaAsistencia> fichas = new ArrayList<FichaAsistencia>();
	private JButton btnFuncionario;
	private JButton btnPeriodo;
	private JButton btnMensual;
	private JTextField txtCodOficina;
	private JTextField txtOficina;
	private JButton btnOficina;
	@SuppressWarnings("rawtypes")
	private JList listConfiguracion;

	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	public ReporteVista() throws ClassNotFoundException, ParseException {
		getContentPane().setBackground(Color.WHITE);
		setTitle("REPORTES");
		setFrameIcon(new ImageIcon(AncestroVista.class.getResource(GlobalUtil.RUTA_FAVICON_MIGRACIONES)));
		setBounds(100, 100, 870, 549);
		panelDatos = new PanelDatosUtil();
		panelDatos.setBounds(0, 0, 875, 26);
		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(0, 484, 854, 35);
		panelBotones.setBackground(new Color(43, 70, 97));
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSalirActionPerformed(e);
			}
		});
		btnSalir.setPreferredSize(new Dimension(95, 27));

		panelRegMensual = new JPanel();
		panelRegMensual.setBounds(295, 196, 549, 71);
		panelRegMensual.setBackground(new Color(255, 222, 173));
		panelRegMensual.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mensual",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		panelRegPeriodo = new JPanel();
		panelRegPeriodo.setBounds(295, 278, 549, 71);
		panelRegPeriodo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Periodo de tiempo",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelRegPeriodo.setBackground(new Color(135, 206, 250));

		JPanel panelConfiguracion = new JPanel();
		panelConfiguracion.setBounds(10, 37, 275, 436);
		panelConfiguracion.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Selecci\u00F3n de reporte", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelConfiguracion.setBackground(Color.WHITE);

		JPanel panelParametros = new JPanel();
		panelParametros.setBounds(295, 37, 549, 160);
		panelParametros.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Parametro de b\u00FAsqueda", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelParametros.setBackground(Color.WHITE);

		lblImagen = new JLabel("");
		lblImagen.setBounds(672, 334, 172, 150);
		lblImagen.setIcon(
				new ImageIcon(ReporteVista.class.getResource("/com/migraciones/talentoHumano/graphics/busqueda.png")));
		panelConfiguracion.setLayout(null);

		txtCedula = new JTextField();
		txtCedula.setBounds(67, 37, 74, 20);
		txtCedula.setEnabled(false);
		txtCedula.setColumns(10);

		JLabel label_4 = new JLabel("Cedula:");
		label_4.setBounds(16, 40, 41, 14);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblOficina = new JLabel("Oficina:");
		lblOficina.setBounds(16, 97, 41, 14);
		lblOficina.setFont(new Font("Tahoma", Font.BOLD, 11));

		txtNombres = new JTextField();
		txtNombres.setBounds(67, 62, 472, 20);
		txtNombres.setEnabled(false);
		txtNombres.setColumns(10);

		btnFuncionario = new JButton("");
		btnFuncionario.setBounds(144, 37, 25, 21);
		btnFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnFuncionarioActionPerformed(arg0);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		panelParametros.setLayout(null);
		btnFuncionario.setIcon(new ImageIcon(ReporteVista.class.getResource(GlobalUtil.RUTA_IMAGEN_BIENES_CONSULTAS)));
		panelParametros.add(btnFuncionario);
		panelParametros.add(lblOficina);
		panelParametros.add(label_4);
		panelParametros.add(txtCedula);
		panelParametros.add(txtNombres);

		dcFechaDesde = new JDateChooser();
		dcFechaDesde.setBounds(64, 27, 109, 27);
		dcFechaDesde.setPreferredSize(new Dimension(140, 27));
		dcFechaDesde.setMinimumSize(new Dimension(125, 27));
		dcFechaDesde.setDateFormatString("dd/MM/yyyy");
		dcFechaDesde.setDate(FechaUtil.getFechaActualDate());

		dcFechaHasta = new JDateChooser();
		dcFechaHasta.setBounds(235, 27, 109, 27);
		dcFechaHasta.setPreferredSize(new Dimension(140, 27));
		dcFechaHasta.setDateFormatString("dd/MM/yyyy");
		dcFechaHasta.setDate(FechaUtil.getFechaActualDate());

		JLabel label_2 = new JLabel("Desde:");
		label_2.setBounds(16, 27, 44, 27);
		label_2.setPreferredSize(new Dimension(50, 27));
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("SansSerif", Font.BOLD, 12));

		JLabel label_3 = new JLabel("Hasta:");
		label_3.setBounds(183, 27, 44, 27);
		label_3.setPreferredSize(new Dimension(70, 27));
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("SansSerif", Font.BOLD, 12));

		cbMeses = new JComboBox();
		cbMeses.setBounds(56, 31, 123, 20);
		cbMeses.setModel(new DefaultComboBoxModel(new String[] { "-- Seleccione --", "Enero", "Febrero", "Marzo",
				"Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
		cbMeses.setPreferredSize(new Dimension(150, 27));
		cbMeses.setMinimumSize(new Dimension(150, 27));

		JLabel label = new JLabel("Mes:");
		label.setBounds(16, 27, 30, 27);
		label.setPreferredSize(new Dimension(50, 27));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("SansSerif", Font.BOLD, 12));

		JLabel label_1 = new JLabel("A\u00F1o:");
		label_1.setBounds(183, 27, 44, 27);
		label_1.setPreferredSize(new Dimension(70, 27));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("SansSerif", Font.BOLD, 12));

		spAnhos = new JSpinner();
		spAnhos.setBounds(231, 31, 74, 20);
		spAnhos.setModel(new SpinnerNumberModel(2017, null, 2100, 1));
		spAnhos.setPreferredSize(new Dimension(100, 27));
		spAnhos.setMinimumSize(new Dimension(150, 27));
		panelDatos.cargarDatos();
		getContentPane().setLayout(null);
		getContentPane().add(panelConfiguracion);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 255, 403);
		panelConfiguracion.add(scrollPane);

		listConfiguracion = new JList();
		scrollPane.setViewportView(listConfiguracion);
		listConfiguracion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (listConfiguracion.getSelectedIndex() == 0) {
					busquedaOpcion1();
				} else if (listConfiguracion.getSelectedIndex() == 1) {
					busquedaOpcion2();
				} else if (listConfiguracion.getSelectedIndex() == 2) {
					busquedaOpcion3();
				} else if (listConfiguracion.getSelectedIndex() == 3) {
					busquedaOpcion4();
				} else if (listConfiguracion.getSelectedIndex() == 4) {
					busquedaOpcion5();
				} else if (listConfiguracion.getSelectedIndex() == 5) {
					busquedaOpcion6();
				} else if (listConfiguracion.getSelectedIndex() == 6) {
					busquedaOpcion7();
				}
			}
		});
		listConfiguracion.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				listConfiguracionValueChanged();
			}
		});
		listConfiguracion.setBorder(null);
		listConfiguracion.setModel(new AbstractListModel() {
			String[] values = new String[] { "1- Asistencias particular", "2- Asistencias por oficina",
					"3- Inasistencias sin justificar particular",
					"4- Inasistencias sin justificar por oficina (d\u00EDas h\u00E1biles)",
					"5- Justificaciones particular", "6- Justificaciones por oficina", "7- Personales por abecedario*",
					"8- Personales por categor\u00EDa*", "9- Personales por condici\u00F3n*",
					"10- Personales por dependencia*", "11- Personales por oficina*", "12- Personales por sexo*" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listConfiguracion.setSelectedIndex(0);
		listConfiguracion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getContentPane().add(panelParametros);

		txtCodOficina = new JTextField();
		txtCodOficina.setEnabled(false);
		txtCodOficina.setBounds(67, 94, 74, 20);
		panelParametros.add(txtCodOficina);
		txtCodOficina.setColumns(10);

		btnOficina = new JButton("");
		btnOficina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnOficinaActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOficina.setEnabled(false);
		btnOficina.setIcon(new ImageIcon(ReporteVista.class.getResource(GlobalUtil.RUTA_IMAGEN_BIENES_CONSULTAS)));
		btnOficina.setBounds(144, 93, 25, 21);
		panelParametros.add(btnOficina);

		txtOficina = new JTextField();
		txtOficina.setEnabled(false);
		txtOficina.setBounds(67, 122, 472, 20);
		panelParametros.add(txtOficina);
		txtOficina.setColumns(10);
		getContentPane().add(panelDatos);
		getContentPane().add(panelBotones);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelBotones.add(btnSalir);
		getContentPane().add(lblImagen);
		getContentPane().add(panelRegPeriodo);
		panelRegPeriodo.setLayout(null);
		panelRegPeriodo.add(label_2);
		panelRegPeriodo.add(dcFechaDesde);
		panelRegPeriodo.add(label_3);
		panelRegPeriodo.add(dcFechaHasta);

		btnPeriodo = new JButton("Generar Reporte");
		btnPeriodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnPeriodoActionPerformed(e);
				} catch (HeadlessException | ClassNotFoundException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPeriodo.setBounds(371, 30, 127, 23);
		panelRegPeriodo.add(btnPeriodo);
		getContentPane().add(panelRegMensual);
		panelRegMensual.setLayout(null);
		panelRegMensual.add(label);
		panelRegMensual.add(cbMeses);
		panelRegMensual.add(label_1);
		panelRegMensual.add(spAnhos);

		btnMensual = new JButton("Generar Reporte");
		btnMensual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnMensualActionPerformed(arg0);
				} catch (HeadlessException | ClassNotFoundException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnMensual.setBounds(371, 30, 127, 23);
		panelRegMensual.add(btnMensual);

		JPanel panelGenerarReporte = new JPanel();
		panelGenerarReporte.setBackground(new Color(192, 192, 192));
		panelGenerarReporte.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Generar reporte",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelGenerarReporte.setBounds(295, 360, 253, 71);
		getContentPane().add(panelGenerarReporte);
		panelGenerarReporte.setLayout(null);

		JButton btnGenerarReporte = new JButton("Generar Reporte");
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnGenerarReporteActionPerformed(arg0);
				} catch (HeadlessException | ClassNotFoundException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnGenerarReporte.setBounds(70, 24, 127, 23);
		panelGenerarReporte.add(btnGenerarReporte);
		busquedaOpcion1();
	}

	// //////////////////// EVENTOS DEL SISTEMA //////////////

	protected void listConfiguracionValueChanged() {
		if (listConfiguracion.getValueIsAdjusting()) {
			if (listConfiguracion.getSelectedIndex() == 0) {
				busquedaOpcion1();
			} else if (listConfiguracion.getSelectedIndex() == 1) {
				busquedaOpcion2();
			} else if (listConfiguracion.getSelectedIndex() == 2) {
				busquedaOpcion3();
			} else if (listConfiguracion.getSelectedIndex() == 3) {
				busquedaOpcion4();
			} else if (listConfiguracion.getSelectedIndex() == 4) {
				busquedaOpcion5();
			} else if (listConfiguracion.getSelectedIndex() == 5) {
				busquedaOpcion6();
			} else if (listConfiguracion.getSelectedIndex() == 6) {
				busquedaOpcion7();
			}
		}

	}

	protected void btnSalirActionPerformed(ActionEvent e) {
		this.dispose();
	}

	// ################################################
	// ############ REPORTES MENSUALES ################
	// ################################################

	protected void btnMensualActionPerformed(ActionEvent e)
			throws HeadlessException, ClassNotFoundException, ParseException {
		if (this.opcionBusqueda == 1) {
			if (!txtCedula.getText().equals("") && cbMeses.getSelectedIndex() != 0
					&& !spAnhos.getValue().toString().equals("")) {
				sistema.imprimirFichaAsistencia(txtCedula.getText().trim(), cbMeses.getSelectedIndex(),
						(Integer) spAnhos.getValue());
			} else {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (this.opcionBusqueda == 2) {
			if (buscarFuncionarios()) {
				int mes = cbMeses.getSelectedIndex();
				int anho = (Integer) spAnhos.getValue();
				for (Personal per : this.personales) {
					FichaAsistencia ficha = this.sistema.obtenerFichaAsistencia(per.getCedula(), mes, anho);
					this.fichas.add(ficha);
				}
				if (!sistema.imprimirFichaAsistenciaLista(this.fichas)) {
					JOptionPane.showMessageDialog(null, GlobalUtil.MSG_REGISTRO_NOT_FOUND, "ATENCION",
							JOptionPane.ERROR_MESSAGE);
				}
				this.personales.clear();
				this.fichas.clear();
			}
		} else if (this.opcionBusqueda == 3) {
			if (!txtCedula.getText().equals("") && cbMeses.getSelectedIndex() != 0
					&& !spAnhos.getValue().toString().equals("")) {
				sistema.imprimirFaltasDisciplinarias(txtCedula.getText().trim(), cbMeses.getSelectedIndex(),
						(Integer) spAnhos.getValue());
			} else {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (this.opcionBusqueda == 4) {
			if (cbMeses.getSelectedIndex() != 0 && !spAnhos.getValue().toString().equals("")) {
				sistema.imprimirFaltasDisciplinarias(cbMeses.getSelectedIndex(), (Integer) spAnhos.getValue());
			} else {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (this.opcionBusqueda == 5) {
			if (cbMeses.getSelectedIndex() != 0 && !spAnhos.getValue().toString().equals("")) {
				if (sistema.imprimirJustificaciones(txtCedula.getText().trim(), cbMeses.getSelectedIndex(),
						(Integer) spAnhos.getValue()) == false) {
					JOptionPane.showMessageDialog(null, GlobalUtil.MSG_REGISTRO_NOT_FOUND, "ATENCION",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (this.opcionBusqueda == 6) {
			if (cbMeses.getSelectedIndex() != 0 && !spAnhos.getValue().toString().equals("")) {
				if (sistema.imprimirJustificacionesByOficina(txtCodOficina.getText().trim(), cbMeses.getSelectedIndex(),
						(Integer) spAnhos.getValue()) == false) {
					JOptionPane.showMessageDialog(null, GlobalUtil.MSG_REGISTRO_NOT_FOUND, "ATENCION",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	// ################################################
	// ############ REPORTES PERIODO ##################
	// ################################################

	protected void btnPeriodoActionPerformed(ActionEvent e)
			throws HeadlessException, ClassNotFoundException, ParseException {
		if (this.opcionBusqueda == 1) {
			if (txtCedula.getText().equals("") || dcFechaDesde.getDate() == null || dcFechaHasta.getDate() == null) {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			} else if (verificarPeriodo()) {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_VERIFICAR_PERIODO, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			} else {
				sistema.imprimirFichaAsistencia(txtCedula.getText().trim(), this.fechaDesde, this.fechaHasta);
			}

		} else if (this.opcionBusqueda == 2) {
			if (buscarFuncionarios()) {
				if (verificarPeriodo()) {
					JOptionPane.showMessageDialog(null, GlobalUtil.MSG_VERIFICAR_PERIODO, "ATENCION",
							JOptionPane.ERROR_MESSAGE);
				} else {
					for (Personal per : this.personales) {
						FichaAsistencia ficha = this.sistema.obtenerFichaAsistencia(per.getCedula(), this.fechaDesde,
								this.fechaHasta);
						this.fichas.add(ficha);
					}
					if (!sistema.imprimirFichaAsistenciaLista(this.fichas)) {
						JOptionPane.showMessageDialog(null, GlobalUtil.MSG_REGISTRO_NOT_FOUND, "ATENCION",
								JOptionPane.ERROR_MESSAGE);
					}
					this.personales.clear();
					this.fichas.clear();
				}
			}
		} else if (this.opcionBusqueda == 3) {
			if (txtCedula.getText().equals("") || dcFechaDesde.getDate() == null || dcFechaHasta.getDate() == null) {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			} else if (verificarPeriodo()) {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_VERIFICAR_PERIODO, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			} else {
				sistema.imprimirFaltasDisciplinarias(txtCedula.getText().trim(), this.fechaDesde, this.fechaHasta);
			}
		} else if (this.opcionBusqueda == 4) {
			if (dcFechaDesde.getDate() == null || dcFechaHasta.getDate() == null) {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			} else if (verificarPeriodo()) {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_VERIFICAR_PERIODO, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			} else {
				sistema.imprimirFaltasDisciplinarias(this.fechaDesde, this.fechaHasta);
			}
		} else if (this.opcionBusqueda == 5) {
			if (dcFechaDesde.getDate() == null || dcFechaHasta.getDate() == null) {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			} else if (verificarPeriodo()) {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_VERIFICAR_PERIODO, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (sistema.imprimirJustificaciones(txtCedula.getText().trim(), this.fechaDesde,
						this.fechaHasta) == false) {
					JOptionPane.showMessageDialog(null, GlobalUtil.MSG_REGISTRO_NOT_FOUND, "ATENCION",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (this.opcionBusqueda == 6) {
			if (dcFechaDesde.getDate() == null || dcFechaHasta.getDate() == null) {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			} else if (verificarPeriodo()) {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_VERIFICAR_PERIODO, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (sistema.imprimirJustificacionesByOficina(txtCodOficina.getText().trim(), this.fechaDesde,
						this.fechaHasta) == false) {
					JOptionPane.showMessageDialog(null, GlobalUtil.MSG_REGISTRO_NOT_FOUND, "ATENCION",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	// ################################################
	// ############ GENERAR REPORTE ###################
	// ################################################

	protected void btnGenerarReporteActionPerformed(ActionEvent e)
			throws HeadlessException, ClassNotFoundException, ParseException {
		if (this.opcionBusqueda == 1) {
			if (!txtCedula.getText().equals("") && cbMeses.getSelectedIndex() != 0
					&& !spAnhos.getValue().toString().equals("")) {
				sistema.imprimirFichaAsistencia(txtCedula.getText().trim(), cbMeses.getSelectedIndex(),
						(Integer) spAnhos.getValue());
			} else {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (this.opcionBusqueda == 2) {
			if (buscarFuncionarios()) {
				int mes = cbMeses.getSelectedIndex();
				int anho = (Integer) spAnhos.getValue();
				for (Personal per : this.personales) {
					FichaAsistencia ficha = this.sistema.obtenerFichaAsistencia(per.getCedula(), mes, anho);
					this.fichas.add(ficha);
				}
				if (!sistema.imprimirFichaAsistenciaLista(this.fichas)) {
					JOptionPane.showMessageDialog(null, GlobalUtil.MSG_REGISTRO_NOT_FOUND, "ATENCION",
							JOptionPane.ERROR_MESSAGE);
				}
				this.personales.clear();
				this.fichas.clear();
			}
		} else if (this.opcionBusqueda == 3) {
			if (!txtCedula.getText().equals("") && cbMeses.getSelectedIndex() != 0
					&& !spAnhos.getValue().toString().equals("")) {
				sistema.imprimirFaltasDisciplinarias(txtCedula.getText().trim(), cbMeses.getSelectedIndex(),
						(Integer) spAnhos.getValue());
			} else {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (this.opcionBusqueda == 4) {
			if (cbMeses.getSelectedIndex() != 0 && !spAnhos.getValue().toString().equals("")) {
				sistema.imprimirFaltasDisciplinarias(cbMeses.getSelectedIndex(), (Integer) spAnhos.getValue());
			} else {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (this.opcionBusqueda == 5) {
			if (cbMeses.getSelectedIndex() != 0 && !spAnhos.getValue().toString().equals("")) {
				if (sistema.imprimirJustificaciones(txtCedula.getText().trim(), cbMeses.getSelectedIndex(),
						(Integer) spAnhos.getValue()) == false) {
					JOptionPane.showMessageDialog(null, GlobalUtil.MSG_REGISTRO_NOT_FOUND, "ATENCION",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (this.opcionBusqueda == 6) {
			if (cbMeses.getSelectedIndex() != 0 && !spAnhos.getValue().toString().equals("")) {
				if (sistema.imprimirJustificacionesByOficina(txtCodOficina.getText().trim(), cbMeses.getSelectedIndex(),
						(Integer) spAnhos.getValue()) == false) {
					JOptionPane.showMessageDialog(null, GlobalUtil.MSG_REGISTRO_NOT_FOUND, "ATENCION",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (this.opcionBusqueda == 7) {
			if (cbMeses.getSelectedIndex() != 0 && !spAnhos.getValue().toString().equals("")) {
				// if (sistema.imprimirPersonales() == false) {
				// JOptionPane.showMessageDialog(null,
				// GlobalUtil.MSG_REGISTRO_NOT_FOUND, "ATENCION",
				// JOptionPane.ERROR_MESSAGE);
				// }
			} else {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	protected void btnFuncionarioActionPerformed(ActionEvent arg0) throws ClassNotFoundException {
		PersonalModal vista = new PersonalModal("0");
		vista.setVisible(true);
		cargarCamposFuncionario(vista);
	}

	protected void btnOficinaActionPerformed(ActionEvent arg0) throws ClassNotFoundException {
		OficinaModal vista = new OficinaModal();
		vista.setVisible(true);
		cargarCamposOficina(vista);
	}

	// //////////////////// METODOS DEL FORMULARIO //////////////

	private boolean buscarFuncionarios() throws ClassNotFoundException {
		boolean control = true;
		if (!txtCodOficina.getText().equals("")) {
			String codigo = txtCodOficina.getText();
			this.personales = this.sistema.obtenerFuncionariosByOficina(codigo);
		} else {
			this.personales = this.sistema.obtenerFuncionarios();
		}
		if (this.personales.size() == 0) {
			control = false;
		}
		return control;
	}

	private void cargarCamposFuncionario(PersonalModal vista) {
		txtCedula.setText(vista.getCedula());
		txtNombres.setText(vista.getPersonal());
	}

	private void cargarCamposOficina(OficinaModal vista) {
		txtCodOficina.setText(vista.getCodigo());
		txtOficina.setText(vista.getOficina());
	}

	private boolean verificarPeriodo() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.fechaDesde = sdf.parse(String.valueOf(sdf.format(dcFechaDesde.getDate())));
		this.fechaHasta = sdf.parse(String.valueOf(sdf.format(dcFechaHasta.getDate())));
		return this.fechaDesde.after(this.fechaHasta);
	}

	private void busquedaOpcion1() {
		this.opcionBusqueda = 1;
		btnFuncionario.setEnabled(true);
		txtCedula.setText("");
		txtNombres.setText("");
		btnOficina.setEnabled(false);
		txtCodOficina.setText("");
		txtOficina.setText("");
		cbMeses.setSelectedIndex(this.util.getDateToMes(FechaUtil.getFechaActualDate()));
		spAnhos.setValue(this.util.getDateToAnho(FechaUtil.getFechaActualDate()));
		dcFechaDesde.setDate(FechaUtil.getFechaActualDate());
		dcFechaHasta.setDate(FechaUtil.getFechaActualDate());
	}

	private void busquedaOpcion2() {
		this.opcionBusqueda = 2;
		btnFuncionario.setEnabled(false);
		txtCedula.setText("");
		txtNombres.setText("");
		btnOficina.setEnabled(true);
		cbMeses.setSelectedIndex(this.util.getDateToMes(FechaUtil.getFechaActualDate()));
		spAnhos.setValue(this.util.getDateToAnho(FechaUtil.getFechaActualDate()));
		dcFechaDesde.setDate(FechaUtil.getFechaActualDate());
		dcFechaHasta.setDate(FechaUtil.getFechaActualDate());
	}

	private void busquedaOpcion3() {
		this.opcionBusqueda = 3;
		btnFuncionario.setEnabled(true);
		txtCedula.setText("");
		txtNombres.setText("");
		btnOficina.setEnabled(false);
		txtCodOficina.setText("");
		txtOficina.setText("");
		cbMeses.setSelectedIndex(this.util.getDateToMes(FechaUtil.getFechaActualDate()));
		spAnhos.setValue(this.util.getDateToAnho(FechaUtil.getFechaActualDate()));
		dcFechaDesde.setDate(FechaUtil.getFechaActualDate());
		dcFechaHasta.setDate(FechaUtil.getFechaActualDate());
	}

	private void busquedaOpcion4() {
		this.opcionBusqueda = 4;
		btnFuncionario.setEnabled(false);
		txtCedula.setText("");
		txtNombres.setText("");
		btnOficina.setEnabled(false);
		txtCodOficina.setText("");
		txtOficina.setText("");
		cbMeses.setSelectedIndex(this.util.getDateToMes(FechaUtil.getFechaActualDate()));
		spAnhos.setValue(this.util.getDateToAnho(FechaUtil.getFechaActualDate()));
		dcFechaDesde.setDate(FechaUtil.getFechaActualDate());
		dcFechaHasta.setDate(FechaUtil.getFechaActualDate());
	}

	private void busquedaOpcion5() {
		this.opcionBusqueda = 5;
		btnFuncionario.setEnabled(true);
		txtCedula.setText("");
		txtNombres.setText("");
		btnOficina.setEnabled(false);
		txtCodOficina.setText("");
		txtOficina.setText("");
		cbMeses.setSelectedIndex(this.util.getDateToMes(FechaUtil.getFechaActualDate()));
		spAnhos.setValue(this.util.getDateToAnho(FechaUtil.getFechaActualDate()));
		dcFechaDesde.setDate(FechaUtil.getFechaActualDate());
		dcFechaHasta.setDate(FechaUtil.getFechaActualDate());
	}

	private void busquedaOpcion6() {
		this.opcionBusqueda = 6;
		btnFuncionario.setEnabled(false);
		txtCedula.setText("");
		txtNombres.setText("");
		btnOficina.setEnabled(true);
		cbMeses.setSelectedIndex(this.util.getDateToMes(FechaUtil.getFechaActualDate()));
		spAnhos.setValue(this.util.getDateToAnho(FechaUtil.getFechaActualDate()));
		dcFechaDesde.setDate(FechaUtil.getFechaActualDate());
		dcFechaHasta.setDate(FechaUtil.getFechaActualDate());
	}

	private void busquedaOpcion7() {
		this.opcionBusqueda = 7;
		btnFuncionario.setEnabled(false);
		txtCedula.setText("");
		txtNombres.setText("");
		btnOficina.setEnabled(false);
		txtCodOficina.setText("");
		txtOficina.setText("");
		cbMeses.setSelectedIndex(this.util.getDateToMes(FechaUtil.getFechaActualDate()));
		spAnhos.setValue(this.util.getDateToAnho(FechaUtil.getFechaActualDate()));
		dcFechaDesde.setDate(FechaUtil.getFechaActualDate());
		dcFechaHasta.setDate(FechaUtil.getFechaActualDate());
	}
}
