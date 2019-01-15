package com.migraciones.talentoHumano.vistas.formularios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.migraciones.talentoHumano.app.TalentoHumano;
import com.migraciones.talentoHumano.modelos.Turno;
import com.migraciones.talentoHumano.views.componentesGUI.ModeloTablaUtil;

public class HorarioModal extends AncestroModal {
	private int COLUMNA_ID = 0;
	@SuppressWarnings("unused")
	private int COLUMNA_ENTRADA = 2;
	@SuppressWarnings("unused")
	private int COLUMNA_SALIDA = 3;
	private static final long serialVersionUID = 1L;
	private JPanel panelBusqueda;
	private JPanel panelCampos;
	private JTextField txtBuscador;
	private JScrollPane scrollPane;
	public JTable tbConsultas;
	private ModeloTablaUtil modelo = new ModeloTablaUtil();
	private TableRowSorter<TableModel> sorter;
	private int horarioId;
	private int maxHorarioId = 0;
	private TalentoHumano sistema = new TalentoHumano();

	public HorarioModal() throws ClassNotFoundException {
		setSize(new Dimension(544, 415));
		setTitle("SELECCIONE HORARIO");
		panelBusqueda = new JPanel();
		panelBusqueda.setPreferredSize(new Dimension(15, 50));
		panelBusqueda.setBackground(new Color(43, 70, 97));
		getContentPane().add(panelBusqueda, BorderLayout.NORTH);

		txtBuscador = new JTextField();
		txtBuscador.setColumns(10);
		GroupLayout gl_panelBusqueda = new GroupLayout(panelBusqueda);
		gl_panelBusqueda.setHorizontalGroup(gl_panelBusqueda.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelBusqueda.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtBuscador, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
						.addGap(103)));
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
		GroupLayout gl_panelCampos = new GroupLayout(panelCampos);
		gl_panelCampos.setHorizontalGroup(gl_panelCampos.createParallelGroup(Alignment.LEADING).addComponent(scrollPane,
				GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE));
		gl_panelCampos.setVerticalGroup(gl_panelCampos.createParallelGroup(Alignment.LEADING).addComponent(scrollPane,
				GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE));

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
		panelCampos.setLayout(gl_panelCampos);
		cargarTabla();
	}

	// /////////////////// MANEJO DE EVENTOS ///////////////////////////

	protected void tbConsultasKeyPressed(KeyEvent e) throws ClassNotFoundException {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			cargarPersonal();
			salirVista();
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
		modelo.addColumn("ID");
		modelo.addColumn("DESCRIPCION");
		modelo.addColumn("ENTRADA");
		modelo.addColumn("SALIDA");
		modelo.addColumn("TIEMPO DE TRABAJO");
		tbConsultas.setModel(modelo);
		TableColumn columna0 = tbConsultas.getColumn("ID");
		TableColumn columna1 = tbConsultas.getColumn("DESCRIPCION");
		TableColumn columna2 = tbConsultas.getColumn("ENTRADA");
		TableColumn columna3 = tbConsultas.getColumn("SALIDA");
		TableColumn columna4 = tbConsultas.getColumn("TIEMPO DE TRABAJO");
		columna0.setPreferredWidth(5);
		columna1.setPreferredWidth(50);
		columna2.setPreferredWidth(20);
		columna3.setPreferredWidth(20);
		columna4.setPreferredWidth(20);

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
		Object[] fila = new Object[6];
		for (Turno turno : sistema.obtenerHorarios()) {
			if (turno.getId() > this.maxHorarioId) {
				this.maxHorarioId = turno.getId();
			}

			fila[0] = turno.getId();
			fila[1] = turno.getDescripcion();
			fila[2] = turno.getEntrada();
			fila[3] = turno.getSalida();
			fila[4] = turno.getTiempoTrabajo();

			modelo.addRow(fila);
			tbConsultas.setModel(modelo);
			sorter = new TableRowSorter<TableModel>(modelo);
			tbConsultas.setRowSorter(sorter);
			tbConsultas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		}

	}

	private void cargarPersonal() throws ClassNotFoundException {
		int id = (int) tbConsultas.getValueAt(tbConsultas.getSelectedRow(), COLUMNA_ID);
		this.horarioId = id;
	}

	private void filtrar() {
		RowFilter<TableModel, Object> rf = null;
		int indiceColumnaTabla = COLUMNA_ID;
		try {
			rf = RowFilter.regexFilter(txtBuscador.getText().toUpperCase(), indiceColumnaTabla);
		} catch (java.util.regex.PatternSyntaxException e) {
		}
		sorter.setRowFilter(rf);
	}

	public int getHorarioId() {
		return this.horarioId;
	}

}
