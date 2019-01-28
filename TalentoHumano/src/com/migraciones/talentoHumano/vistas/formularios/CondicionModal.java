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
import com.migraciones.talentoHumano.modelos.TipoPersonal;
import com.migraciones.talentoHumano.views.componentesGUI.ModeloTablaUtil;

public class CondicionModal extends AncestroModal {
	private int COLUMNA_CODIGO = 0;
	private int COLUMNA_CONDICION = 1;

	private static final long serialVersionUID = 1L;
	private JPanel panelBusqueda;
	private JPanel panelCampos;
	private JTextField txtBuscador;
	private JScrollPane scrollPane;
	public JTable tbConsultas;
	private ModeloTablaUtil modelo = new ModeloTablaUtil();
	private TableRowSorter<TableModel> sorter;
	private String condicion;
	private String codigo;
	private TalentoHumano sistema = new TalentoHumano();

	public CondicionModal() throws ClassNotFoundException {
		setTitle("SELECCIONE CONDICION");
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
			cargarCondicion();
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
			cargarCondicion();
			salirVista();
		}
	}

	// /////////////////// METODOS DEL SISTEMA ///////////////////////////

	private void cargarTabla() throws ClassNotFoundException {
		modelo.addColumn("ID");
		modelo.addColumn("CONDICION");
		tbConsultas.setModel(modelo);
		TableColumn columna0 = tbConsultas.getColumn("ID");
		TableColumn columna1 = tbConsultas.getColumn("CONDICION");
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
		Object[] fila = new Object[6];
		for (TipoPersonal con : sistema.obtenerCondiciones()) {
			fila[0] = Integer.toString(con.getId());
			fila[1] = con.getDescripcion();
			modelo.addRow(fila);
			tbConsultas.setModel(modelo);
			sorter = new TableRowSorter<TableModel>(modelo);
			tbConsultas.setRowSorter(sorter);
			tbConsultas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
	}

	private void cargarCondicion() throws ClassNotFoundException {
		String codigo = (String) tbConsultas.getValueAt(tbConsultas.getSelectedRow(), COLUMNA_CODIGO);
		String condicion = (String) tbConsultas.getValueAt(tbConsultas.getSelectedRow(), COLUMNA_CONDICION);
		this.codigo = codigo;
		this.condicion = condicion;
	}

	private void filtrar() {
		RowFilter<TableModel, Object> rf = null;
		int indiceColumnaTabla = COLUMNA_CONDICION;
		try {
			rf = RowFilter.regexFilter(txtBuscador.getText().toUpperCase(), indiceColumnaTabla);
		} catch (java.util.regex.PatternSyntaxException e) {
		}
		sorter.setRowFilter(rf);
	}

	public String getCondicion() {
		return this.condicion;
	}

	public String getCodigo() {
		return this.codigo;
	}

}
