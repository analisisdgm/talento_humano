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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

import com.migraciones.talentoHumano.app.TalentoHumano;
import com.migraciones.talentoHumano.modelos.Feriado;
import com.migraciones.talentoHumano.utilities.CellRendererUtil;
import com.migraciones.talentoHumano.utilities.FechaUtil;
import com.migraciones.talentoHumano.utilities.GlobalUtil;
import com.migraciones.talentoHumano.views.componentesGUI.ModeloTablaUtil;
import com.toedter.calendar.JDateChooser;

public class PermisoColectivoVista extends AncestroVista {
	private static final long serialVersionUID = 1L;
	private TalentoHumano sistema = new TalentoHumano();
	private ModeloTablaUtil modelo = new ModeloTablaUtil();
	private JTextField txtMotivo;
	private JTable tbFeriados;
	private JDateChooser dcDesde;
	private JDateChooser dcHasta;
	private FechaUtil util = new FechaUtil();
	private Date fechaDesde = new Date();
	private Date fechaHasta = new Date();
	private int opcion = 0;
	private int idActualizacion = 0;

	
	public PermisoColectivoVista() throws ClassNotFoundException {
		panelBotones.btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAgregarActionPerformed(e);
			}
		});
		setSize(new Dimension(830, 430));
		setPreferredSize(new Dimension(830, 430));
		setNormalBounds(new Rectangle(100, 100, 830, 430));
		panelCampos.setBackground(Color.WHITE);
		setTitle("PROCESOS - PERMISOS COLECTIVOS");
		panelDatos.cargarDatos();
		JPanel panelParametros = new JPanel();
		panelParametros
				.setBorder(new TitledBorder(null, "Parametros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelParametros.setBounds(10, 11, 646, 114);
		panelParametros.setBackground(Color.WHITE);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 151, 814, 180);
		scrollPane.setBackground(Color.WHITE);

		tbFeriados = new JTable();
		scrollPane.setViewportView(tbFeriados);

		JLabel lblMotivo = new JLabel("Motivo:");
		lblMotivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMotivo.setBounds(10, 63, 80, 20);
		lblMotivo.setFont(new Font("Tahoma", Font.BOLD, 11));

		txtMotivo = new JTextField();
		txtMotivo.setBounds(94, 63, 542, 20);
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
		panelParametros.add(lblMotivo);
		panelParametros.add(txtMotivo);
		
				JLabel lblDesde = new JLabel("Desde:");
				lblDesde.setBounds(52, 32, 38, 14);
				panelParametros.add(lblDesde);
				lblDesde.setFont(new Font("Tahoma", Font.BOLD, 11));
				
						dcDesde = new JDateChooser();
						dcDesde.setBounds(95, 30, 97, 20);
						panelParametros.add(dcDesde);
						dcDesde.setDateFormatString("dd/MM/yyyy");
						
								JLabel lblHasta = new JLabel("Hasta:");
								lblHasta.setBounds(229, 32, 36, 14);
								panelParametros.add(lblHasta);
								lblHasta.setFont(new Font("Tahoma", Font.BOLD, 11));
								dcHasta = new JDateChooser();
								dcHasta.setBounds(275, 30, 97, 20);
								panelParametros.add(dcHasta);
								dcHasta.setDateFormatString("dd/MM/yyyy");
		panelCampos.add(scrollPane);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(PermisoColectivoVista.class.getResource("/com/migraciones/talentoHumano/graphics/calendarioFeriado.png")));
		lblNewLabel.setBounds(657, 11, 157, 129);
		panelCampos.add(lblNewLabel);
		formatearTabla();
		desactivarFormulario();
		activarFechas();
		panelBotones.btnImprimir.setVisible(false);
	}

	// /////////////////// MANEJO DE EVENTOS ///////////////////////////

	protected void txtMotivoKeyTyped(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if (Character.isLowerCase(c)) {
			String cadena = ("" + c).toUpperCase();
			c = cadena.charAt(0);
			arg0.setKeyChar(c);
		}
	}

	// /////////////////// MANEJO DE METODOS ///////////////////////////

	@Override
	protected void agregarObjeto() {
		// opcion 1 es agregar nuevo feriado
		this.setTitle("PROCESOS - PERMISOS  (AGREGANDO)");
		this.opcion = 1;
		activarFormulario();
		limpiarTabla();
		this.panelBotones.habilitar();
		habilitarAgregar();
	}
	private void habilitarAgregar(){
		dcDesde.setEnabled(true);
		dcHasta.setEnabled(true);
		txtMotivo.setText("");
		txtMotivo.setEnabled(true);
	}
	@Override
	protected void editarObjeto() throws ClassNotFoundException {
		// opcion 2 es editar feriado
		if (tbFeriados.getSelectedRow() != -1
				&& tbFeriados.getValueAt(tbFeriados.getSelectedRow(), 0) != null) {
			this.setTitle("PROCESOS - PERMISOS COLECTIVOS (MODIFICANDO)");
			this.opcion = 2;
			activarFormulario();
			cargarDatosModificar();
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
		if (tbFeriados.getSelectedRow() != -1
				&& tbFeriados.getValueAt(tbFeriados.getSelectedRow(), 0) != null) {
			this.setTitle("PROCESOS - PERMISOS COLECTIVOS (ELIMINANDO)");
			this.opcion = 3;
			activarFormulario();
			cargarDatosModificar();
			limpiarTabla();
			this.panelBotones.habilitar();
		} else {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_ITEM_NO_SELECCIONADO, "ATENCION",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	protected void guardarObjeto() throws ClassNotFoundException, SQLException {
		try {
				if (this.opcion == 1) {
					// se agrega nuevo feriado
					guardarNuevoFeriado();
					desactivarFormulario();
					this.panelBotones.deshabilitar();
				} else if (this.opcion == 2) {
					// se modifica un feriado
					actualizarFeriado();
					desactivarFormulario();
					this.panelBotones.deshabilitar();
				} else if (this.opcion == 3) {
					// se elimina un feriado
					eliminarFeriado();
					desactivarFormulario();
					this.panelBotones.deshabilitar();
				}
			
		} catch (HeadlessException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void cancelarOperacion() {
		// se inicializa la opcion
		this.setTitle("PROCESOS - PERMISOS COLECTIVOS");
		desactivarFormulario();
		this.panelBotones.deshabilitar();

	}

	@Override
	protected void verObjeto() throws ClassNotFoundException {
		try {
			if (dcDesde.getDate() == null || dcHasta.getDate() == null || verificarPeriodo()) {
//				
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

	private void limpiarTabla() {
		while (tbFeriados.getRowCount() != 0) {
			modelo.removeRow(0);
		}
	}

	private void formatearTabla() throws ClassNotFoundException {
		modelo.addColumn("ID");
		modelo.addColumn("MOTIVO");
		modelo.addColumn("FECHA DESDE");
		modelo.addColumn("FECHA HASTA");
		tbFeriados.setModel(modelo);
		tbFeriados.getColumnModel().getColumn(0).setMinWidth(5);
		tbFeriados.getColumnModel().getColumn(1).setMinWidth(100);
		tbFeriados.getColumnModel().getColumn(2).setMinWidth(20);
		tbFeriados.getColumnModel().getColumn(3).setMinWidth(20);
	}
private Feriado cargarNuevoFeriado(){
	Feriado feriado = new Feriado();
	feriado.setFechaDesde(dcDesde.getDate());
	feriado.setFechaHasta(dcHasta.getDate());
	feriado.setDescripcion(txtMotivo.getText());
	feriado.setUsuarioAlta(MenuPrincipalVista.administrador.getLogin());
	return feriado;
}
	private void guardarNuevoFeriado() throws ClassNotFoundException, SQLException {
		Feriado fer = cargarNuevoFeriado();
		if (this.sistema.agregarNuevoFeriado(fer)){
			JOptionPane.showMessageDialog(null, "Información agregada correctamente", "ATENCION",
					JOptionPane.INFORMATION_MESSAGE);
			this.panelBotones.deshabilitar();
			this.opcion = 0;
		} else {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_GUARDADOS_FAIL, "ATENCION",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void actualizarFeriado() throws HeadlessException, ClassNotFoundException {
		Feriado feriado = new Feriado();
		feriado.setId(this.idActualizacion);
		feriado.setDescripcion(txtMotivo.getText());
		feriado.setFechaDesde(dcDesde.getDate());
		feriado.setFechaHasta(dcHasta.getDate());
		feriado.setUsuarioModificacion(MenuPrincipalVista.administrador.getLogin());

		if (sistema.actualizarFeriado(feriado)) {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_GUARDADOS_OK, "ATENCION",
					JOptionPane.INFORMATION_MESSAGE);
			cargarTabla();
		} else {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_GUARDADOS_FAIL, "ATENCION",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void eliminarFeriado() throws HeadlessException, ClassNotFoundException {
		Feriado feriado = new Feriado();
		feriado.setId(this.idActualizacion);
		feriado.setUsuarioAlta(MenuPrincipalVista.administrador.getLogin());
		if (sistema.eliminarFeriado(feriado)) {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_GUARDADOS_OK, "ATENCION",
					JOptionPane.INFORMATION_MESSAGE);
			cargarTabla();
		} else {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_GUARDADOS_FAIL, "ATENCION",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void cargarDatosModificar() {
		this.idActualizacion = (int) tbFeriados.getValueAt(tbFeriados.getSelectedRow(), 0);
		String fechaDesde = (String) tbFeriados.getValueAt(tbFeriados.getSelectedRow(), 2);
		String fechaHasta = (String) tbFeriados.getValueAt(tbFeriados.getSelectedRow(), 3);
		txtMotivo.setText((String) tbFeriados.getValueAt(tbFeriados.getSelectedRow(), 1));
		dcDesde.setDate(util.getStringToDate(fechaDesde));
		dcHasta.setDate(util.getStringToDate(fechaHasta));
	}

	private void cargarTabla() {
		// cargar en la tabla de visualizacion
		Object[] fila = new Object[7];
		fila[0] = this.idActualizacion;
		fila[1] = txtMotivo.getText();
		fila[2] = util.getDateToString(dcDesde.getDate());
		fila[3] = util.getDateToString(dcHasta.getDate());
		
		this.modelo.addRow(fila);
		tbFeriados.setModel(this.modelo);
	}

	private void cargarTablaHistorial() {
		
		try {
			for (Feriado feriado : this.sistema.obtenerFeriadoRango(
					util.getDateToStringSQL(dcDesde.getDate()), util.getDateToStringSQL(dcHasta.getDate()))) {
				// cargar en la tabla de visualizacion
				Object[] fila = new Object[7];
				fila[0] = feriado.getId();
				fila[1] = feriado.getDescripcion().toString();
				fila[2] = util.getDateToString(feriado.getFechaDesde());
				fila[3] = util.getDateToString(feriado.getFechaHasta());
				this.modelo.addRow(fila);
				tbFeriados.setModel(this.modelo);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

//	private boolean verificarCampos() throws HeadlessException, ParseException, ClassNotFoundException {
//		boolean control = true;
//		if (buscarFuncionario() == false) {
//			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_REGISTRO_NOT_FOUND, "ATENCION",
//					JOptionPane.ERROR_MESSAGE);
//			control = false;
//		}
//
//		if (txtCedula.getText().equals("") || cbTipo.getSelectedIndex() == 0 || txtMotivo.getText().equals("")) {
//			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_CAMPOS_NO_LLENOS, "ATENCION", JOptionPane.ERROR_MESSAGE);
//			control = false;
//		} else if (cbTipo.getSelectedIndex() == 1 || cbTipo.getSelectedIndex() == 2) {
//			if (dcDesde.getDate() == null) {
//				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_VERIFICAR_PERIODO, "ATENCION",
//						JOptionPane.ERROR_MESSAGE);
//				control = false;
//			}
//		} else if (cbTipo.getSelectedIndex() == 3 || cbTipo.getSelectedIndex() == 4 || cbTipo.getSelectedIndex() == 5
//				|| cbTipo.getSelectedIndex() == 6) {
//			if (dcDesde.getDate() == null || dcHasta.getDate() == null || verificarPeriodo()) {
//				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_VERIFICAR_PERIODO, "ATENCION",
//						JOptionPane.ERROR_MESSAGE);
//				control = false;
//			}
//		}
//		return control;
//	}

//	private boolean buscarFuncionario() throws ClassNotFoundException {
//		return sistema.existePersonal(txtCedula.getText());
//	}

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

	private void activarFechas() {
		dcDesde.setDate(new Date());
		dcHasta.setDate(new Date());
	}

	private void desactivarFormulario() {
		this.setTitle("PROCESOS - PERMISOS COLECTIVOS");
		txtMotivo.setEnabled(false);
		txtMotivo.setText("");
		activarFechas();
		this.opcion = 0;
		this.idActualizacion = 0;
	}

	private void activarFormulario() {
		txtMotivo.setEnabled(true);
		txtMotivo.setText("");
//		desactivarFechas();
	}
}
