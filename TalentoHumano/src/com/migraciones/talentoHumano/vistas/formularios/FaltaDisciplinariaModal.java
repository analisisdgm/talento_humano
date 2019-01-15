package com.migraciones.talentoHumano.vistas.formularios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.migraciones.talentoHumano.app.TalentoHumano;
import com.migraciones.talentoHumano.modelos.FaltaDisciplinaria;
import com.migraciones.talentoHumano.views.componentesGUI.ModeloTablaUtil;
import java.awt.Rectangle;

public class FaltaDisciplinariaModal extends AncestroModal {
	private int COLUMNA_CEDULA = 0;
	private int COLUMNA_NOMBRES_APELLIDOS = 1;
	private int COLUMNA_FECHA = 2;

	private static final long serialVersionUID = 1L;
	private JPanel panelBusqueda;
	private JPanel panelCampos;
	private JTextField txtBuscador;
	private JScrollPane scrollPane;
	public JTable tbConsultas;
	private ModeloTablaUtil modelo = new ModeloTablaUtil();
	private TableRowSorter<TableModel> sorter;
	private String personal;
	private Date fechaFin;
	private Date fechaInicio;
	private Date fecha;
	private String cedula;
	private TalentoHumano sistema = new TalentoHumano();
	private JPanel panel;
	private JLabel lblCantidad;
	private JLabel lblValor;

	public FaltaDisciplinariaModal(String cedula, Date fechaInicio, Date fechaFin) throws ClassNotFoundException {
		setBounds(new Rectangle(630, 313, 680, 466));
		setSize(new Dimension(680, 466));
		setTitle("SELECCIONE FALTA");
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.cedula = cedula;
		panelBusqueda = new JPanel();
		panelBusqueda.setPreferredSize(new Dimension(15, 50));
		panelBusqueda.setBackground(new Color(43, 70, 97));
		getContentPane().add(panelBusqueda, BorderLayout.NORTH);
		txtBuscador = new JTextField();
		txtBuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				txtBuscadorKeyPressed(arg0);
			}
		});
		txtBuscador.setColumns(10);
		GroupLayout gl_panelBusqueda = new GroupLayout(panelBusqueda);
		gl_panelBusqueda
				.setHorizontalGroup(gl_panelBusqueda.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_panelBusqueda.createSequentialGroup().addContainerGap(41, Short.MAX_VALUE)
								.addComponent(txtBuscador, GroupLayout.PREFERRED_SIZE, 569, GroupLayout.PREFERRED_SIZE)
								.addGap(44)));
		gl_panelBusqueda.setVerticalGroup(gl_panelBusqueda.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBusqueda
						.createSequentialGroup().addGap(13).addComponent(txtBuscador, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(17, Short.MAX_VALUE)));
		panelBusqueda.setLayout(gl_panelBusqueda);

		panelCampos = new JPanel();
		panelCampos.setBackground(Color.WHITE);
		getContentPane().add(panelCampos, BorderLayout.CENTER);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 674, 352);

		panel = new JPanel();
		panel.setBackground(new Color(43, 70, 97));
		panel.setBounds(0, 352, 674, 34);

		tbConsultas = new JTable();
		tbConsultas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					tbConsultasKeyPressed(e);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		tbConsultas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					tbConsultasMouseClicked(e);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(tbConsultas);
		panelCampos.setLayout(null);
		panelCampos.add(panel);
		panel.setLayout(null);

		lblCantidad = new JLabel("CANTIDAD:");
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantidad.setForeground(Color.WHITE);
		lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidad.setBounds(10, 11, 70, 20);
		panel.add(lblCantidad);

		lblValor = new JLabel("");
		lblValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblValor.setForeground(Color.WHITE);
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValor.setBounds(86, 11, 96, 20);
		panel.add(lblValor);
		panelCampos.add(scrollPane);
		cargarTabla();
	}

	// /////////////////// MANEJO DE EVENTOS ///////////////////////////

	protected void txtBuscadorKeyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == 40) {
			tbConsultas.requestFocus();
			tbConsultas.changeSelection(0, 0, false, false);
		} else if (arg0.getKeyCode() == 38) {
			tbConsultas.requestFocus();
			tbConsultas.changeSelection(tbConsultas.getRowCount() - 1, 0, false, false);
		}
	}

	protected void tbConsultasKeyPressed(KeyEvent e) throws ClassNotFoundException {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			cargarPersonal();
			salirVista();
		} else if (e.getKeyCode() == KeyEvent.VK_TAB) {
			txtBuscador.requestFocus();
		}
	}

	protected void salirVista() throws ClassNotFoundException {
		this.dispose();
	}

	protected void tbConsultasMouseClicked(MouseEvent e) throws ClassNotFoundException {
		if (e.getClickCount() == 2) {
			cargarPersonal();
			salirVista();
		}
	}

	// /////////////////// METODOS DEL SISTEMA ///////////////////////////

	private void cargarTabla() throws ClassNotFoundException {
		modelo.addColumn("CEDULA");
		modelo.addColumn("NOMBRES Y APELLIDOS");
		modelo.addColumn("FECHA");
		modelo.addColumn("FALTA");
		tbConsultas.setModel(modelo);
		TableColumn columna0 = tbConsultas.getColumn("CEDULA");
		TableColumn columna1 = tbConsultas.getColumn("NOMBRES Y APELLIDOS");
		columna0.setPreferredWidth(50);
		columna1.setPreferredWidth(400);
		txtBuscador.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				filtrar();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				filtrar();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				filtrar();
			}
		});
		ArrayList<FaltaDisciplinaria> lista;
		if (this.cedula.equals("")) {
			lista = sistema.obtenerInasistenciasSinJustificar(fechaInicio, fechaFin);
		} else {
			lista = sistema.obtenerInasistenciasSinJustificar(this.cedula, fechaInicio, fechaFin);
		}
		Object[] fila = new Object[6];
		for (FaltaDisciplinaria falta : lista) {
			fila[0] = falta.getCedula();
			fila[1] = falta.getNombres();
			fila[2] = falta.getFecha();
			fila[3] = falta.getDescripcion();

			modelo.addRow(fila);
			tbConsultas.setModel(modelo);
			sorter = new TableRowSorter<TableModel>(modelo);
			tbConsultas.setRowSorter(sorter);
			tbConsultas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		lblValor.setText(Integer.toString(lista.size()));
	}

	private void cargarPersonal() throws ClassNotFoundException {
		String cedula = (String) tbConsultas.getValueAt(tbConsultas.getSelectedRow(), COLUMNA_CEDULA);
		String nombresApellidos = (String) tbConsultas.getValueAt(tbConsultas.getSelectedRow(),
				COLUMNA_NOMBRES_APELLIDOS);
		this.fecha = (Date) tbConsultas.getValueAt(tbConsultas.getSelectedRow(), COLUMNA_FECHA);
		this.cedula = cedula;
		this.personal = nombresApellidos;
	}

	private void filtrar() {
		RowFilter<TableModel, Object> rf = null;
		rf = RowFilter.regexFilter(txtBuscador.getText().toUpperCase(), COLUMNA_CEDULA, COLUMNA_NOMBRES_APELLIDOS,
				COLUMNA_FECHA);
		sorter.setRowFilter(rf);
	}

	public String getPersonal() {
		return this.personal;
	}

	public String getCedula() {
		return this.cedula;
	}

	public Date getFecha() {
		return this.fecha;
	}

}
