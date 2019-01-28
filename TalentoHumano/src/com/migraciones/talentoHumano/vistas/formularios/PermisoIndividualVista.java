package com.migraciones.talentoHumano.vistas.formularios;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

import com.migraciones.talentoHumano.app.TalentoHumano;
import com.migraciones.talentoHumano.modelos.PerHistoDisip;
import com.migraciones.talentoHumano.models.HorarioPersonal;
import com.migraciones.talentoHumano.utilities.CellRendererUtil;
import com.migraciones.talentoHumano.utilities.FechaUtil;
import com.migraciones.talentoHumano.utilities.GlobalUtil;
import com.migraciones.talentoHumano.views.componentesGUI.ModeloTablaUtil;
import com.toedter.calendar.JDateChooser;

public class PermisoIndividualVista extends AncestroVista {
	private static final long serialVersionUID = 1L;
	private TalentoHumano sistema = new TalentoHumano();
	private ModeloTablaUtil modelo = new ModeloTablaUtil();
	private JTextField txtMotivo;
	private JTable tbJustificaciones;
	@SuppressWarnings("rawtypes")
	private JComboBox cbTipo;
	private JTextField txtCedula;
	private JTextField txtFuncionario;
	private JDateChooser dcDesde;
	private JDateChooser dcHasta;
	private FechaUtil util = new FechaUtil();
	private Date fechaDesde = new Date();
	private Date fechaHasta = new Date();
	private int opcion = 0;
	private int idActualizacion = 0;
	private JTextField txtCodOficina;
	private JTextField txtOficina;
	private JButton btnOficina;
	private JButton btnFuncionario;

	@SuppressWarnings({ "rawtypes" })
	public PermisoIndividualVista() throws ClassNotFoundException {
		panelBotones.btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAgregarActionPerformed(e);
			}
		});
		setSize(new Dimension(830, 588));
		setPreferredSize(new Dimension(800, 111));
		setNormalBounds(new Rectangle(100, 100, 800, 504));
		panelCampos.setBackground(Color.WHITE);
//		setTitle("PROCESOS - ASIGNACION DE FERIADOS Y PERMISOS COLECTIVOS");
		setTitle("PROCESOS - PERMISOS INDIVIDUALES");
		panelDatos.cargarDatos();
		JPanel panelParametros = new JPanel();
		panelParametros
				.setBorder(new TitledBorder(null, "Parametros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelParametros.setBounds(10, 2, 646, 127);
		panelParametros.setBackground(Color.WHITE);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 205, 814, 286);
		scrollPane.setBackground(Color.WHITE);

		JPanel panelRangoFechas = new JPanel();
		panelRangoFechas.setBounds(10, 129, 323, 65);
		panelRangoFechas.setBackground(Color.WHITE);
		panelRangoFechas.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Periodo",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		dcDesde = new JDateChooser();
		dcDesde.setBounds(64, 27, 97, 20);
		dcDesde.setDateFormatString("dd/MM/yyyy");
		dcHasta = new JDateChooser();
		dcHasta.setBounds(217, 27, 97, 20);
		dcHasta.setDateFormatString("dd/MM/yyyy");

		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setBounds(16, 33, 38, 14);
		lblDesde.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setBounds(171, 33, 36, 14);
		lblHasta.setFont(new Font("Tahoma", Font.BOLD, 11));

		tbJustificaciones = new JTable();
		scrollPane.setViewportView(tbJustificaciones);

		JLabel lblOficina = new JLabel("Oficina:");
		lblOficina.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOficina.setBounds(10, 17, 80, 20);
		lblOficina.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblFuncionario = new JLabel("Funcionario:");
		lblFuncionario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuncionario.setBounds(10, 42, 80, 20);
		lblFuncionario.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipo.setBounds(10, 67, 80, 20);
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblMotivo = new JLabel("Motivo:");
		lblMotivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMotivo.setBounds(10, 92, 80, 20);
		lblMotivo.setFont(new Font("Tahoma", Font.BOLD, 11));

		cbTipo = new JComboBox();
		cbTipo.setBounds(94, 67, 200, 20);
		cbTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbTipoActionPerformed(e);
			}
		});

		txtMotivo = new JTextField();
		txtMotivo.setBounds(94, 92, 542, 20);
		txtMotivo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				txtMotivoKeyTyped(arg0);
			}
		});
		txtMotivo.setColumns(10);
		panelCampos.setLayout(null);
		panelCampos.add(panelParametros);
		panelParametros.setLayout(null);
		panelParametros.add(lblOficina);
		panelParametros.add(lblTipo);
		panelParametros.add(lblFuncionario);
		panelParametros.add(lblMotivo);
		panelParametros.add(txtMotivo);
		panelParametros.add(cbTipo);

		txtCedula = new JTextField();
		txtCedula.setEnabled(false);
		txtCedula.setBounds(94, 42, 86, 20);
		panelParametros.add(txtCedula);
		txtCedula.setColumns(10);

		btnFuncionario = new JButton("");
		btnFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnFuncionarioActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFuncionario.setEnabled(false);
		btnFuncionario.setIcon(
				new ImageIcon(PermisoIndividualVista.class.getResource("/com/migraciones/talentoHumano/graphics/lupa.png")));
		btnFuncionario.setBounds(185, 42, 25, 21);
		panelParametros.add(btnFuncionario);

		txtFuncionario = new JTextField();
		txtFuncionario.setBounds(215, 42, 421, 20);
		panelParametros.add(txtFuncionario);
		txtFuncionario.setEnabled(false);
		txtFuncionario.setColumns(10);

		txtCodOficina = new JTextField();
		txtCodOficina.setBounds(94, 17, 86, 20);
		panelParametros.add(txtCodOficina);
		txtCodOficina.setEnabled(false);
		txtCodOficina.setColumns(10);

		btnOficina = new JButton("");
		btnOficina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnOficinaActionPerformed(arg0);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnOficina.setBounds(185, 17, 25, 21);
		panelParametros.add(btnOficina);
		btnOficina.setEnabled(false);
		btnOficina.setIcon(
				new ImageIcon(PermisoIndividualVista.class.getResource("/com/migraciones/talentoHumano/graphics/lupa.png")));
		txtOficina = new JTextField();
		txtOficina.setBounds(215, 17, 421, 20);
		panelParametros.add(txtOficina);
		txtOficina.setEnabled(false);
		txtOficina.setColumns(10);
		panelCampos.add(scrollPane);
		panelCampos.add(panelRangoFechas);
		panelRangoFechas.setLayout(null);
		panelRangoFechas.add(lblDesde);
		panelRangoFechas.add(dcDesde);
		panelRangoFechas.add(lblHasta);
		panelRangoFechas.add(dcHasta);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(PermisoIndividualVista.class.getResource("/com/migraciones/talentoHumano/graphics/busqueda.png")));
		lblNewLabel.setBounds(657, 44, 157, 150);
		panelCampos.add(lblNewLabel);
		formatearTabla();
		desactivarFormulario();
		activarFechas();
		panelBotones.btnImprimir.setVisible(false);
	}

	// /////////////////// MANEJO DE EVENTOS ///////////////////////////

	protected void btnFaltasActionPerformed(ActionEvent e)
			throws HeadlessException, ParseException, ClassNotFoundException {
		if (dcDesde.getDate() == null || dcHasta.getDate() == null || verificarPeriodo()) {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_VERIFICAR_PERIODO, "ATENCION",
					JOptionPane.ERROR_MESSAGE);
		} else {
			FaltaDisciplinariaModal vista = new FaltaDisciplinariaModal(txtCedula.getText(), this.fechaDesde,
					this.fechaHasta);
			vista.setVisible(true);
			cargarFalta(vista);
		}
	}

	protected void btnFuncionarioActionPerformed(ActionEvent e) throws ClassNotFoundException {
		String codigo_ofi = "";
		if (txtCodOficina.getText().equals("")) {
			codigo_ofi = "0";
		} else {
			codigo_ofi = txtCodOficina.getText();
		}
		PersonalModal vista = new PersonalModal(codigo_ofi);
		vista.setVisible(true);
		cargarPersonal(vista);
	}

	protected void btnOficinaActionPerformed(ActionEvent arg0) throws ClassNotFoundException {
		OficinaModal vista = new OficinaModal();
		vista.setVisible(true);
		cargarCamposOficina(vista);
	}

	protected void txtMotivoKeyTyped(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if (Character.isLowerCase(c)) {
			String cadena = ("" + c).toUpperCase();
			c = cadena.charAt(0);
			arg0.setKeyChar(c);
		}
	}

	protected void cbTipoActionPerformed(ActionEvent e) {
		if (cbTipo.getSelectedIndex() == 1 || cbTipo.getSelectedIndex() == 2) {
			inicializarFecha();
		} else if (cbTipo.getSelectedIndex() == 3 || cbTipo.getSelectedIndex() == 4 || cbTipo.getSelectedIndex() == 5
				|| cbTipo.getSelectedIndex() == 6) {
			inicializarRangoFechas();
		} else if (cbTipo.getSelectedIndex() == 7) {
			inicializarRangoFechas();
		} else if (cbTipo.getSelectedIndex() == 0) {
			desactivarFechas();
		}
	}

	// /////////////////// MANEJO DE METODOS ///////////////////////////

	@Override
	protected void agregarObjeto() {
		// opcion 1 es agregar nuevo justificativo
		this.setTitle("PROCESOS - PERMISOS INDIVIDUALES (AGREGANDO)");
		this.opcion = 1;
		activarFormulario();
		limpiarTabla();
		this.panelBotones.habilitar();
	}

	@Override
	protected void editarObjeto() throws ClassNotFoundException {
		// opcion 2 es editar nuevo justificativo
		if (tbJustificaciones.getSelectedRow() != -1
				&& tbJustificaciones.getValueAt(tbJustificaciones.getSelectedRow(), 0) != null) {
			this.setTitle("PROCESOS - PERMISOS INDIVIDUALES (MODIFICANDO)");
			this.opcion = 2;
			activarFormulario();
			cargarDatosModificar();
			txtCedula.setEnabled(false);
			limpiarTabla();
			this.panelBotones.habilitar();
		} else {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_ITEM_NO_SELECCIONADO, "ATENCION",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	protected void borrarObjeto() throws ClassNotFoundException {
		// opcion 3 es actualiza una justificacion a estado historico
		if (tbJustificaciones.getSelectedRow() != -1
				&& tbJustificaciones.getValueAt(tbJustificaciones.getSelectedRow(), 0) != null) {
			this.setTitle("PROCESOS - PERMISOS INDIVIDUALES (ELIMINANDO)");
			this.opcion = 3;
			activarFormulario();
			cargarDatosModificar();
			txtCedula.setEnabled(false);
			limpiarTabla();
			this.panelBotones.habilitar();
		} else {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_ITEM_NO_SELECCIONADO, "ATENCION",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	protected void guardarObjeto() throws ClassNotFoundException {
		try {
			if (verificarCampos()) {
				if (this.opcion == 1) {
					// se agrega nuevo justificativo
					guardarJustificativo();
					desactivarFormulario();
					this.panelBotones.deshabilitar();
				} else if (this.opcion == 2) {
					// se modifica un justificativo
					actualizarJustificativo();
					desactivarFormulario();
					this.panelBotones.deshabilitar();
				} else if (this.opcion == 3) {
					// se elimina un justificativo
					eliminarJustificativo();
					desactivarFormulario();
					this.panelBotones.deshabilitar();
				}
			}
		} catch (HeadlessException | ParseException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void cancelarOperacion() {
		// se inicializa la opcion
		this.setTitle("PROCESOS - PERMISOS INDIVIDUALES");
		desactivarFormulario();
		this.panelBotones.deshabilitar();

	}

	@Override
	protected void verObjeto() throws ClassNotFoundException {
		try {
			if (dcDesde.getDate() == null || dcHasta.getDate() == null || verificarPeriodo()) {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_VERIFICAR_PERIODO, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
			} else {
				limpiarTabla();
				cargarTablaHistorial();
				this.panelBotones.deshabilitar();
			}
		} catch (HeadlessException | ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void imprimirObjeto() throws ClassNotFoundException {

	}

	// /////////////////// METODOS DEL SISTEMA ///////////////////////////

	private void cargarFalta(FaltaDisciplinariaModal vista) {
		txtCodOficina.setText("");
		txtOficina.setText("");
		try {
			if (vista.getCedula().equals("") || vista.getPersonal().equals("")) {
				txtCedula.setText("");
				txtFuncionario.setText("");
			} else {
				txtCedula.setText(vista.getCedula());
				txtFuncionario.setText(vista.getPersonal());
			}
		} catch (Exception e) {
			txtCedula.setText("");
			txtFuncionario.setText("");
		}
		if (vista.getFecha() == null) {
			dcDesde.setDate(new Date());
			dcHasta.setDate(new Date());
		} else {
			dcDesde.setDate(vista.getFecha());
			dcHasta.setDate(vista.getFecha());
		}
	}

	private void cargarCamposOficina(OficinaModal vista) {
		txtCodOficina.setText(vista.getCodigo());
		txtOficina.setText(vista.getOficina());
		txtCedula.setText("");
		txtFuncionario.setText("");
	}

	private void limpiarTabla() {
		while (tbJustificaciones.getRowCount() != 0) {
			modelo.removeRow(0);
		}
	}

	private void cargarPersonal(PersonalModal vista) {
		txtCedula.setText(vista.getCedula());
		txtFuncionario.setText(vista.getPersonal());
	}

	private void formatearTabla() throws ClassNotFoundException {
		modelo.addColumn("ID");
		modelo.addColumn("CEDULA");
		modelo.addColumn("FUNCIONARIO");
		modelo.addColumn("DESDE");
		modelo.addColumn("HASTA");
		modelo.addColumn("TIPO");
		modelo.addColumn("MOTIVO");
		tbJustificaciones.setModel(modelo);
		tbJustificaciones.getColumnModel().getColumn(0).setMinWidth(10);
		tbJustificaciones.getColumnModel().getColumn(1).setMinWidth(10);
		tbJustificaciones.getColumnModel().getColumn(2).setMinWidth(100);
		tbJustificaciones.getColumnModel().getColumn(3).setMinWidth(20);
		tbJustificaciones.getColumnModel().getColumn(4).setMinWidth(20);
		tbJustificaciones.getColumnModel().getColumn(5).setMinWidth(50);
		tbJustificaciones.getColumnModel().getColumn(6).setMinWidth(200);
	}

	private void guardarJustificativo() throws ClassNotFoundException {
		PerHistoDisip historia = new PerHistoDisip();
		historia.setCedula(txtCedula.getText());
		historia.setFechaDesde(dcDesde.getDate());
		if (cbTipo.getSelectedIndex() == 1 || cbTipo.getSelectedIndex() == 2) {
			historia.setFechaHasta(dcDesde.getDate());
		} else {
			historia.setFechaHasta(dcHasta.getDate());
		}
		historia.setTipo(cbTipo.getSelectedIndex());
		historia.setObservacion(txtMotivo.getText().toUpperCase());
		historia.setAdministrador(MenuPrincipalVista.administrador.getLogin());

		if (sistema.agregarHistoriaDisciplinaria(historia)) {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_GUARDADOS_OK, "ATENCION",
					JOptionPane.INFORMATION_MESSAGE);
			cargarTabla();
		} else {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_GUARDADOS_FAIL, "ATENCION",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void actualizarJustificativo() throws HeadlessException, ClassNotFoundException {
		PerHistoDisip historia = new PerHistoDisip();
		historia.setId(this.idActualizacion);
		historia.setCedula(txtCedula.getText());
		historia.setFechaDesde(dcDesde.getDate());
		if (cbTipo.getSelectedIndex() == 1 || cbTipo.getSelectedIndex() == 2) {
			historia.setFechaHasta(dcDesde.getDate());
		} else {
			historia.setFechaHasta(dcHasta.getDate());
		}
		historia.setTipo(cbTipo.getSelectedIndex());
		historia.setObservacion(txtMotivo.getText().toUpperCase());
		historia.setAdministrador(MenuPrincipalVista.administrador.getLogin());
		if (sistema.actualizarHistoriaDisciplinaria(historia)) {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_GUARDADOS_OK, "ATENCION",
					JOptionPane.INFORMATION_MESSAGE);
			cargarTabla();
		} else {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_GUARDADOS_FAIL, "ATENCION",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void eliminarJustificativo() throws HeadlessException, ClassNotFoundException {
		PerHistoDisip historia = new PerHistoDisip();
		historia.setId(this.idActualizacion);
		historia.setAdministrador(MenuPrincipalVista.administrador.getLogin());
		if (sistema.eliminarHistoriaDisciplinaria(historia)) {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_GUARDADOS_OK, "ATENCION",
					JOptionPane.INFORMATION_MESSAGE);
			cargarTabla();
		} else {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_GUARDADOS_FAIL, "ATENCION",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void cargarDatosModificar() {
		this.idActualizacion = (int) tbJustificaciones.getValueAt(tbJustificaciones.getSelectedRow(), 0);
		txtCedula.setText((String) tbJustificaciones.getValueAt(tbJustificaciones.getSelectedRow(), 1));
		txtFuncionario.setText((String) tbJustificaciones.getValueAt(tbJustificaciones.getSelectedRow(), 2));
		String fechaDesde = (String) tbJustificaciones.getValueAt(tbJustificaciones.getSelectedRow(), 3);
		String fechaHasta = (String) tbJustificaciones.getValueAt(tbJustificaciones.getSelectedRow(), 4);
		cbTipo.setSelectedItem((String) tbJustificaciones.getValueAt(tbJustificaciones.getSelectedRow(), 5));
		txtMotivo.setText((String) tbJustificaciones.getValueAt(tbJustificaciones.getSelectedRow(), 6));
		dcDesde.setDate(util.getStringToDate(fechaDesde));
		if (cbTipo.getSelectedIndex() == 3 || cbTipo.getSelectedIndex() == 4 || cbTipo.getSelectedIndex() == 5
				|| cbTipo.getSelectedIndex() == 6 || cbTipo.getSelectedIndex() == 7) {
			dcHasta.setDate(util.getStringToDate(fechaHasta));
		}
	}

	private void cargarTabla() {
		// cargar en la tabla de visualizacion
		Object[] fila = new Object[7];
		fila[1] = txtCedula.getText();
		fila[2] = txtFuncionario.getText();
		fila[3] = util.getDateToString(dcDesde.getDate());
		if (cbTipo.getSelectedIndex() == 1 || cbTipo.getSelectedIndex() == 2) {
			fila[4] = util.getDateToString(dcDesde.getDate());
		} else {
			fila[4] = util.getDateToString(dcHasta.getDate());
		}
		fila[5] = cbTipo.getSelectedItem();
		fila[6] = txtMotivo.getText().toUpperCase();
		this.modelo.addRow(fila);
		tbJustificaciones.setModel(this.modelo);
	}

	private void cargarTablaHistorial() {
		try {
			for (PerHistoDisip historial : this.sistema.obtenerHistorialDisciplinarioRango(
					util.getDateToStringSQL(dcDesde.getDate()), util.getDateToStringSQL(dcHasta.getDate()))) {
				// cargar en la tabla de visualizacion
				Object[] fila = new Object[7];
				HorarioPersonal per = (HorarioPersonal) sistema.obtenerPersonal(historial.getCedula());
				fila[0] = historial.getId();
				fila[1] = historial.getCedula();
				fila[2] = per.getNombresApellidos();
				fila[3] = util.getDateToString(historial.getFechaDesde());
				fila[4] = util.getDateToString(historial.getFechaHasta());
				fila[5] = cbTipo.getItemAt(historial.getTipo());
				fila[6] = historial.getObservacion();
				this.modelo.addRow(fila);
				tbJustificaciones.setModel(this.modelo);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private boolean verificarCampos() throws HeadlessException, ParseException, ClassNotFoundException {
		boolean control = true;
		if (buscarFuncionario() == false) {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_REGISTRO_NOT_FOUND, "ATENCION",
					JOptionPane.ERROR_MESSAGE);
			control = false;
		}

		if (txtCedula.getText().equals("") || cbTipo.getSelectedIndex() == 0 || txtMotivo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION", JOptionPane.ERROR_MESSAGE);
			control = false;
		} else if (cbTipo.getSelectedIndex() == 1 || cbTipo.getSelectedIndex() == 2) {
			if (dcDesde.getDate() == null) {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_VERIFICAR_PERIODO, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
				control = false;
			}
		} else if (cbTipo.getSelectedIndex() == 3 || cbTipo.getSelectedIndex() == 4 || cbTipo.getSelectedIndex() == 5
				|| cbTipo.getSelectedIndex() == 6) {
			if (dcDesde.getDate() == null || dcHasta.getDate() == null || verificarPeriodo()) {
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_VERIFICAR_PERIODO, "ATENCION",
						JOptionPane.ERROR_MESSAGE);
				control = false;
			}
		}
		return control;
	}

	private boolean buscarFuncionario() throws ClassNotFoundException {
		return sistema.existePersonal(txtCedula.getText());
	}

	private boolean verificarPeriodo() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.fechaDesde = sdf.parse(String.valueOf(sdf.format(dcDesde.getDate())));
		this.fechaHasta = sdf.parse(String.valueOf(sdf.format(dcHasta.getDate())));
		return this.fechaDesde.after(this.fechaHasta);
	}

	public void setCellRender(JTable table) {
		Enumeration<TableColumn> en = table.getColumnModel().getColumns();
		while (en.hasMoreElements()) {
			TableColumn tc = en.nextElement();
			tc.setCellRenderer(new CellRendererUtil());
		}
	}

	private void inicializarFecha() {
		dcDesde.setEnabled(true);
		dcHasta.setEnabled(false);
		dcDesde.setDate(new Date());
		dcHasta.setDate(new Date());

	}

	private void inicializarRangoFechas() {
		dcDesde.setEnabled(true);
		dcHasta.setEnabled(true);
		dcDesde.setDate(new Date());
		dcHasta.setDate(new Date());
	}

	private void desactivarFechas() {
		dcDesde.setDate(new Date());
		dcDesde.setEnabled(false);
		dcHasta.setDate(new Date());
		dcHasta.setEnabled(false);
	}

	private void activarFechas() {
		dcDesde.setDate(new Date());
		dcDesde.setEnabled(true);
		dcHasta.setDate(new Date());
		dcHasta.setEnabled(true);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void desactivarFormulario() {
		this.setTitle("PROCESOS - PERMISOS INDIVIDUALES");
		btnFuncionario.setEnabled(false);
		btnOficina.setEnabled(false);
		cbTipo.setEnabled(false);
		txtMotivo.setEnabled(false);
		txtCedula.setText("");
		txtFuncionario.setText("");
		txtCodOficina.setText("");
		txtOficina.setText("");
		cbTipo.setModel(new DefaultComboBoxModel(new String[] { "-- SELECCIONE --", "LLEGADA TARDIA",
				"SALIDA ANTICIPADA", "AUSENCIA", "PERMISO", "COMISIONAMIENTO", "VACACIONES", "OTROS" }));
		cbTipo.setSelectedIndex(0);
		txtMotivo.setText("");
		activarFechas();
		this.opcion = 0;
		this.idActualizacion = 0;
	}

	private void activarFormulario() {
		btnOficina.setEnabled(true);
		btnFuncionario.setEnabled(true);
		// btnFaltas.setEnabled(true);
		// cbFaltas.setEnabled(true);
		cbTipo.setEnabled(true);
		txtMotivo.setEnabled(true);
		txtCedula.setText("");
		txtFuncionario.setText("");
		txtCodOficina.setText("");
		txtOficina.setText("");
		cbTipo.setSelectedIndex(0);
		txtMotivo.setText("");
		desactivarFechas();
	}
}
