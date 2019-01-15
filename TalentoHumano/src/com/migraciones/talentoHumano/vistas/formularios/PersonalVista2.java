package com.migraciones.talentoHumano.vistas.formularios;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumn;

import com.migraciones.talentoHumano.app.TalentoHumano;
import com.migraciones.talentoHumano.modelos.Cargo;
import com.migraciones.talentoHumano.modelos.Dependencia;
import com.migraciones.talentoHumano.modelos.Evaluacion;
import com.migraciones.talentoHumano.modelos.Oficina;
import com.migraciones.talentoHumano.modelos.PerCar;
import com.migraciones.talentoHumano.modelos.PerCon;
import com.migraciones.talentoHumano.modelos.PerDep;
import com.migraciones.talentoHumano.modelos.PerOfi;
import com.migraciones.talentoHumano.modelos.PerTur;
import com.migraciones.talentoHumano.modelos.Personal;
import com.migraciones.talentoHumano.modelos.TipoPersonal;
import com.migraciones.talentoHumano.modelos.Turno;
import com.migraciones.talentoHumano.utilities.FechaUtil;
import com.migraciones.talentoHumano.utilities.GlobalUtil;
import com.migraciones.talentoHumano.views.componentesGUI.ModeloTablaUtil;
import com.toedter.calendar.JDateChooser;
import javax.swing.ScrollPaneConstants;

public class PersonalVista2 extends AncestroVista {
	private static final long serialVersionUID = 1L;
	private int OPCION = 0;// 1.- agregar; 2.- modificar; 3.- eliminar
	private TalentoHumano sistema = new TalentoHumano();
	private ModeloTablaUtil modelo = new ModeloTablaUtil();
	private Personal personal;
	private PerCon perCon;
	private PerCar perCar;
	private PerDep perDep;
	private PerOfi perOfi;
	private PerTur perTur;
	@SuppressWarnings("rawtypes")
	private JComboBox cbSexo;
	@SuppressWarnings("rawtypes")
	private JComboBox cbEstadoCivil;
	private JDateChooser dcFechaNacimiento;
	private JLabel lblEdad;
	private JTextArea txtDomicilio;
	private JTextArea txtObservacion;
	private JTextField txtApellidos;
	private JTextField txtNombres;
	private JTextField txtCedula;
	private JTextField txtEstado;
	private JTextField txtObsOficina;
	private JTextField txtObsDependencia;
	private JTextField txtOficina;
	private JTextField txtDependencia;
	private JTextField txtObsCondicion;
	private JTextField txtObsHorario;
	private JTextField txtEntrada;
	private JTextField txtSalida;
	private JTextField txtCondicion;
	private JTextField txtCodCond;
	private JTextField txtCodDep;
	private JTextField txtCodOfi;
	private JTextField txtCodHor;

	private JTabbedPane tbVentanas;
	private JPanel pnDatosLaborales;
	private JPanel pnDatosPersonales;
	private JPanel pnEvaluaciones;
	private File fichero;
	private JLabel lblFotoCarnet;
	private JPanel pnFuncionario;

	private JDateChooser dcVigCondicion;
	private JDateChooser dcVigDependencia;
	private JDateChooser dcVigOficina;
	private JDateChooser dcVigHorario;
	private JDateChooser dcVigCargo;

	private JButton btnCondicion;
	private JButton btnDependencia;
	private JButton btnOficina;
	private JButton btnCargo;
	private JButton btnHorario;

	private JButton btnDirectorio;
	private JButton btnImagen;
	private JButton btnCredencial;
	private JButton btnActualizarEstado;
	private JButton btnFuncionario;
	private JTable tbEvaluaciones;
	private JTextField txtTelefonos;
	private JTextField txtCorreo;

	private JLabel lblCondicion;
	private JLabel lblDependencia;
	private JLabel lblOficina;
	private JLabel lblHorario;
	private JLabel lblVigCondicion;
	private JLabel lblVigHorario;
	private JLabel lblVigOficina;
	private JLabel lblVigDependencia;
	private JLabel lblFechaNacimento;
	private JLabel lblCedula;
	private JLabel lblNombres;
	private JLabel lblApellidos;

	private FileInputStream entrada;
	private FileOutputStream salida;
	private byte[] bytesImg;
	private JTextField txtObsCargo;
	private JTextField txtCodCargo;
	private JTextField txtCargo;
	private JLabel lblCargo;
	private JLabel lblVigCargo;
	private JScrollPane scrollPane_1;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PersonalVista2() {
		setNormalBounds(new Rectangle(100, 100, 780, 622));
		panelBotones.btnEliminar.setVisible(false);
		panelBotones.btnVer.setEnabled(false);
		panelBotones.btnEliminar.setEnabled(false);
		panelBotones.btnModificar.setEnabled(false);
		setBounds(new Rectangle(100, 100, 770, 622));
		setPreferredSize(new Dimension(770, 622));
		panelBotones.btnVer.setVisible(false);
		setTitle("PERSONAL");
		panelBotones.btnImprimir.setEnabled(false);
		panelCampos.setLayout(null);

		pnFuncionario = new JPanel();
		pnFuncionario.setBounds(10, 11, 575, 143);
		pnFuncionario.setLayout(null);
		pnFuncionario.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		pnFuncionario.setBackground(Color.WHITE);
		panelCampos.add(pnFuncionario);

		txtApellidos = new JTextField();
		txtApellidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				txtApellidosKeyTyped(e);
			}
		});
		txtApellidos.setEnabled(false);
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(94, 75, 300, 20);
		pnFuncionario.add(txtApellidos);

		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApellidos.setBounds(10, 78, 80, 14);
		pnFuncionario.add(lblApellidos);

		lblNombres = new JLabel("Nombres:");
		lblNombres.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombres.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombres.setBounds(10, 47, 80, 14);
		pnFuncionario.add(lblNombres);

		txtNombres = new JTextField();
		txtNombres.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				txtNombresKeyTyped(arg0);
			}
		});
		txtNombres.setEnabled(false);
		txtNombres.setColumns(10);
		txtNombres.setBounds(94, 44, 300, 20);
		pnFuncionario.add(txtNombres);

		txtCedula = new JTextField();
		txtCedula.setEnabled(false);
		txtCedula.setColumns(10);
		txtCedula.setBounds(94, 13, 117, 20);
		pnFuncionario.add(txtCedula);

		lblCedula = new JLabel("Cedula:");
		lblCedula.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCedula.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCedula.setBounds(10, 16, 80, 14);
		pnFuncionario.add(lblCedula);

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
		btnFuncionario.setIcon(
				new ImageIcon(PersonalVista2.class.getResource("/com/migraciones/talentoHumano/graphics/lupa.png")));
		btnFuncionario.setBounds(221, 11, 25, 21);
		pnFuncionario.add(btnFuncionario);

		lblFotoCarnet = new JLabel("");
		lblFotoCarnet.setBounds(595, 11, 117, 118);
		lblFotoCarnet.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panelCampos.add(lblFotoCarnet);

		txtEstado = new JTextField();
		txtEstado.setBounds(595, 134, 117, 20);
		txtEstado.setHorizontalAlignment(SwingConstants.CENTER);
		txtEstado.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtEstado.setEnabled(false);
		txtEstado.setColumns(10);
		panelCampos.add(txtEstado);

		btnImagen = new JButton("");
		btnImagen.setBounds(716, 11, 25, 23);
		btnImagen.setIcon(new ImageIcon(
				PersonalVista2.class.getResource("/com/migraciones/talentoHumano/graphics/btnImagen.png")));
		btnImagen.setToolTipText("Capturar imagen");
		btnImagen.setEnabled(false);
		panelCampos.add(btnImagen);

		btnDirectorio = new JButton("");
		btnDirectorio.setBounds(716, 36, 25, 23);
		btnDirectorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnDirectorioActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDirectorio.setIcon(new ImageIcon(
				PersonalVista2.class.getResource("/com/migraciones/talentoHumano/graphics/btnDirectorio.png")));
		btnDirectorio.setToolTipText("Cambiar imagen");
		btnDirectorio.setEnabled(false);
		panelCampos.add(btnDirectorio);

		btnCredencial = new JButton("");
		btnCredencial.setBounds(716, 61, 25, 23);
		btnCredencial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnCredencialActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCredencial.setIcon(new ImageIcon(
				PersonalVista2.class.getResource("/com/migraciones/talentoHumano/graphics/btnCarnet.png")));
		btnCredencial.setToolTipText("Generar carnet");
		btnCredencial.setEnabled(false);
		panelCampos.add(btnCredencial);

		btnActualizarEstado = new JButton("");
		btnActualizarEstado.setBounds(716, 131, 25, 23);
		btnActualizarEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnEstadoActionPerformed(arg0);
			}
		});
		btnActualizarEstado.setIcon(
				new ImageIcon(PersonalVista2.class.getResource("/com/migraciones/talentoHumano/graphics/salir.png")));
		btnActualizarEstado.setToolTipText("Activar/Inactivar");
		btnActualizarEstado.setEnabled(false);
		panelCampos.add(btnActualizarEstado);

		tbVentanas = new JTabbedPane(JTabbedPane.TOP);
		tbVentanas.setBounds(10, 165, 731, 348);
		panelCampos.add(tbVentanas);

		pnDatosLaborales = new JPanel();
		tbVentanas.addTab("Datos Laborales", null, pnDatosLaborales, null);
		pnDatosLaborales.setLayout(null);
		pnDatosLaborales.setBackground(Color.WHITE);

		JLabel label_4 = new JLabel("Obs.:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(10, 160, 80, 14);
		pnDatosLaborales.add(label_4);

		txtObsOficina = new JTextField();
		txtObsOficina.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				txtObsOficinaKeyTyped(e);
			}
		});
		txtObsOficina.setEnabled(false);
		txtObsOficina.setColumns(10);
		txtObsOficina.setBounds(108, 157, 607, 20);
		pnDatosLaborales.add(txtObsOficina);

		JLabel label_5 = new JLabel("Obs.:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBounds(10, 101, 80, 14);
		pnDatosLaborales.add(label_5);

		txtObsDependencia = new JTextField();
		txtObsDependencia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				txtObsDependenciaKeyTyped(e);
			}
		});
		txtObsDependencia.setEnabled(false);
		txtObsDependencia.setColumns(10);
		txtObsDependencia.setBounds(108, 98, 607, 20);
		pnDatosLaborales.add(txtObsDependencia);

		lblOficina = new JLabel("Oficina Actual:");
		lblOficina.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOficina.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOficina.setBounds(10, 130, 80, 14);
		pnDatosLaborales.add(lblOficina);

		txtOficina = new JTextField();
		txtOficina.setEnabled(false);
		txtOficina.setColumns(10);
		txtOficina.setBounds(164, 127, 344, 20);
		pnDatosLaborales.add(txtOficina);

		lblVigOficina = new JLabel("Vigencia:");
		lblVigOficina.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVigOficina.setBounds(564, 132, 60, 14);
		pnDatosLaborales.add(lblVigOficina);

		dcVigOficina = new JDateChooser();
		dcVigOficina.setEnabled(false);
		dcVigOficina.setDateFormatString("dd/MM/yyyy");
		dcVigOficina.setBounds(628, 129, 87, 20);
		pnDatosLaborales.add(dcVigOficina);

		JLabel label_8 = new JLabel("Obs.:", SwingConstants.RIGHT);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setBounds(10, 45, 80, 14);
		pnDatosLaborales.add(label_8);

		lblCondicion = new JLabel("Condicion:");
		lblCondicion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCondicion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCondicion.setBounds(10, 15, 80, 14);
		pnDatosLaborales.add(lblCondicion);

		lblDependencia = new JLabel("Dependencia:");
		lblDependencia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDependencia.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDependencia.setBounds(10, 73, 80, 14);
		pnDatosLaborales.add(lblDependencia);

		txtDependencia = new JTextField();
		txtDependencia.setEnabled(false);
		txtDependencia.setColumns(10);
		txtDependencia.setBounds(164, 70, 344, 20);
		pnDatosLaborales.add(txtDependencia);

		lblVigDependencia = new JLabel("Vigencia:");
		lblVigDependencia.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVigDependencia.setBounds(564, 76, 60, 14);
		pnDatosLaborales.add(lblVigDependencia);

		dcVigDependencia = new JDateChooser();
		dcVigDependencia.setEnabled(false);
		dcVigDependencia.setDateFormatString("dd/MM/yyyy");
		dcVigDependencia.setBounds(628, 73, 87, 20);
		pnDatosLaborales.add(dcVigDependencia);

		lblVigCondicion = new JLabel("Vigencia:");
		lblVigCondicion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVigCondicion.setBounds(564, 15, 60, 14);
		pnDatosLaborales.add(lblVigCondicion);

		dcVigCondicion = new JDateChooser();
		dcVigCondicion.setEnabled(false);
		dcVigCondicion.setDateFormatString("dd/MM/yyyy");
		dcVigCondicion.setBounds(628, 15, 87, 20);
		pnDatosLaborales.add(dcVigCondicion);

		txtObsCondicion = new JTextField();
		txtObsCondicion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				txtObsCondicionKeyTyped(e);
			}
		});
		txtObsCondicion.setEnabled(false);
		txtObsCondicion.setColumns(10);
		txtObsCondicion.setBounds(109, 42, 606, 20);
		pnDatosLaborales.add(txtObsCondicion);

		JLabel label_13 = new JLabel("Obs.:");
		label_13.setHorizontalAlignment(SwingConstants.RIGHT);
		label_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_13.setBounds(10, 279, 80, 14);
		pnDatosLaborales.add(label_13);

		txtObsHorario = new JTextField();
		txtObsHorario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				txtObsHorarioKeyTyped(arg0);
			}
		});
		txtObsHorario.setEnabled(false);
		txtObsHorario.setColumns(10);
		txtObsHorario.setBounds(108, 276, 607, 20);
		pnDatosLaborales.add(txtObsHorario);

		JLabel label_14 = new JLabel("Entrada:");
		label_14.setHorizontalAlignment(SwingConstants.RIGHT);
		label_14.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_14.setBounds(179, 250, 60, 14);
		pnDatosLaborales.add(label_14);

		txtEntrada = new JTextField();
		txtEntrada.setEnabled(false);
		txtEntrada.setColumns(10);
		txtEntrada.setBounds(243, 247, 100, 20);
		pnDatosLaborales.add(txtEntrada);

		JLabel label_15 = new JLabel("Salida:");
		label_15.setHorizontalAlignment(SwingConstants.RIGHT);
		label_15.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_15.setBounds(347, 250, 56, 14);
		pnDatosLaborales.add(label_15);

		txtSalida = new JTextField();
		txtSalida.setEnabled(false);
		txtSalida.setColumns(10);
		txtSalida.setBounds(407, 247, 100, 20);
		pnDatosLaborales.add(txtSalida);

		lblVigHorario = new JLabel("Vigencia:");
		lblVigHorario.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVigHorario.setBounds(562, 250, 61, 14);
		pnDatosLaborales.add(lblVigHorario);

		dcVigHorario = new JDateChooser();
		dcVigHorario.setEnabled(false);
		dcVigHorario.setDateFormatString("dd/MM/yyyy");
		dcVigHorario.setBounds(628, 247, 87, 20);
		pnDatosLaborales.add(dcVigHorario);

		btnCondicion = new JButton("");
		btnCondicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnCondicionActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCondicion.setIcon(
				new ImageIcon(PersonalVista2.class.getResource("/com/migraciones/talentoHumano/graphics/lupa.png")));
		btnCondicion.setEnabled(false);
		btnCondicion.setBounds(519, 11, 25, 21);
		pnDatosLaborales.add(btnCondicion);

		btnDependencia = new JButton("");
		btnDependencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnDependenciaActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDependencia.setIcon(
				new ImageIcon(PersonalVista2.class.getResource("/com/migraciones/talentoHumano/graphics/lupa.png")));
		btnDependencia.setEnabled(false);
		btnDependencia.setBounds(518, 70, 25, 21);
		pnDatosLaborales.add(btnDependencia);

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
		btnOficina.setIcon(
				new ImageIcon(PersonalVista2.class.getResource("/com/migraciones/talentoHumano/graphics/lupa.png")));
		btnOficina.setEnabled(false);
		btnOficina.setBounds(519, 127, 25, 21);
		pnDatosLaborales.add(btnOficina);

		btnHorario = new JButton("");
		btnHorario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnHorarioActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnHorario.setIcon(
				new ImageIcon(PersonalVista2.class.getResource("/com/migraciones/talentoHumano/graphics/lupa.png")));
		btnHorario.setEnabled(false);
		btnHorario.setBounds(519, 246, 25, 21);
		pnDatosLaborales.add(btnHorario);

		lblHorario = new JLabel("Horario:");
		lblHorario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHorario.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHorario.setBounds(10, 244, 80, 24);
		pnDatosLaborales.add(lblHorario);

		txtCondicion = new JTextField();
		txtCondicion.setEnabled(false);
		txtCondicion.setColumns(10);
		txtCondicion.setBounds(164, 12, 344, 20);
		pnDatosLaborales.add(txtCondicion);

		txtCodCond = new JTextField();
		txtCodCond.setEnabled(false);
		txtCodCond.setColumns(10);
		txtCodCond.setBounds(108, 12, 49, 20);
		pnDatosLaborales.add(txtCodCond);

		txtCodDep = new JTextField();
		txtCodDep.setEnabled(false);
		txtCodDep.setColumns(10);
		txtCodDep.setBounds(108, 70, 49, 20);
		pnDatosLaborales.add(txtCodDep);

		txtCodOfi = new JTextField();
		txtCodOfi.setEnabled(false);
		txtCodOfi.setColumns(10);
		txtCodOfi.setBounds(108, 127, 49, 20);
		pnDatosLaborales.add(txtCodOfi);

		txtCodHor = new JTextField();
		txtCodHor.setEnabled(false);
		txtCodHor.setColumns(10);
		txtCodHor.setBounds(108, 245, 49, 20);
		pnDatosLaborales.add(txtCodHor);

		pnDatosPersonales = new JPanel();
		pnDatosPersonales.setBackground(Color.WHITE);
		tbVentanas.addTab("Datos Personales", null, pnDatosPersonales, null);
		lblFechaNacimento = new JLabel("<html><body>Fecha de<br> Nacimento:</body></html>");
		lblFechaNacimento.setVerticalAlignment(SwingConstants.BOTTOM);
		lblFechaNacimento.setBounds(20, 11, 67, 32);
		lblFechaNacimento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaNacimento.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSexo.setBounds(278, 17, 31, 21);
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblDomicilio = new JLabel("Domicilio:");
		lblDomicilio.setBounds(33, 115, 54, 14);
		lblDomicilio.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblTelefonos = new JLabel("Telefonos:");
		lblTelefonos.setBounds(29, 54, 58, 14);
		lblTelefonos.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(49, 82, 38, 14);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblObservacin = new JLabel("Observaci\u00F3n:");
		lblObservacin.setBounds(14, 182, 73, 14);
		lblObservacin.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblEstadoCivil = new JLabel("Estado civil:");
		lblEstadoCivil.setVerticalAlignment(SwingConstants.BOTTOM);
		lblEstadoCivil.setBounds(450, 18, 66, 20);
		lblEstadoCivil.setFont(new Font("Tahoma", Font.BOLD, 11));

		dcFechaNacimiento = new JDateChooser();
		dcFechaNacimiento.setDateFormatString("dd/MM/yyyy");
		dcFechaNacimiento.setEnabled(false);
		dcVigCondicion.setDateFormatString("dd/MM/yyyy");

		lblCargo = new JLabel("Cargo:");
		lblCargo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCargo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCargo.setBounds(10, 191, 80, 14);
		pnDatosLaborales.add(lblCargo);

		JLabel lblObs = new JLabel("Obs:");
		lblObs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObs.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObs.setBounds(10, 219, 80, 14);
		pnDatosLaborales.add(lblObs);

		txtObsCargo = new JTextField();
		txtObsCargo.setEnabled(false);
		txtObsCargo.setColumns(10);
		txtObsCargo.setBounds(108, 216, 607, 20);
		pnDatosLaborales.add(txtObsCargo);

		txtCodCargo = new JTextField();
		txtCodCargo.setEnabled(false);
		txtCodCargo.setColumns(10);
		txtCodCargo.setBounds(108, 188, 49, 20);
		pnDatosLaborales.add(txtCodCargo);

		txtCargo = new JTextField();
		txtCargo.setEnabled(false);
		txtCargo.setColumns(10);
		txtCargo.setBounds(165, 188, 344, 20);
		pnDatosLaborales.add(txtCargo);

		btnCargo = new JButton("");
		btnCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnCargoActionPerformed(arg0);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCargo.setIcon(
				new ImageIcon(PersonalVista2.class.getResource("/com/migraciones/talentoHumano/graphics/lupa.png")));
		btnCargo.setEnabled(false);
		btnCargo.setBounds(519, 188, 25, 21);
		pnDatosLaborales.add(btnCargo);

		lblVigCargo = new JLabel("Vigencia:");
		lblVigCargo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVigCargo.setBounds(564, 191, 60, 14);
		pnDatosLaborales.add(lblVigCargo);

		dcVigCargo = new JDateChooser();
		dcVigCargo.setEnabled(false);
		dcVigCargo.setDateFormatString("dd/MM/yyyy");
		dcVigCargo.setBounds(628, 188, 87, 20);
		pnDatosLaborales.add(dcVigCargo);
		dcFechaNacimiento.setBounds(104, 17, 95, 20);

		cbSexo = new JComboBox();
		cbSexo.setEnabled(false);
		cbSexo.setBounds(319, 18, 109, 20);
		cbSexo.setModel(new DefaultComboBoxModel(new String[] { "SELECCIONE", "FEMENINO", "MASCULINO" }));

		cbEstadoCivil = new JComboBox();
		cbEstadoCivil.setEnabled(false);
		cbEstadoCivil.setBounds(526, 17, 109, 20);
		cbEstadoCivil.setModel(new DefaultComboBoxModel(
				new String[] { "SELECCIONE", "SOLTERO/A", "CASADO/A", "DIVORCIADO/A", "VIUDO/A" }));
		pnDatosPersonales.setLayout(null);
		pnDatosPersonales.add(lblObservacin);
		pnDatosPersonales.add(lblTelefonos);
		pnDatosPersonales.add(lblDomicilio);
		pnDatosPersonales.add(lblEmail);
		pnDatosPersonales.add(lblFechaNacimento);
		pnDatosPersonales.add(dcFechaNacimiento);
		pnDatosPersonales.add(lblSexo);
		pnDatosPersonales.add(cbSexo);
		pnDatosPersonales.add(lblEstadoCivil);
		pnDatosPersonales.add(cbEstadoCivil);

		txtTelefonos = new JTextField();
		txtTelefonos.setEnabled(false);
		txtTelefonos.setBounds(104, 48, 612, 20);
		pnDatosPersonales.add(txtTelefonos);
		txtTelefonos.setColumns(10);

		txtCorreo = new JTextField();
		txtCorreo.setEnabled(false);
		txtCorreo.setBounds(104, 79, 612, 20);
		pnDatosPersonales.add(txtCorreo);
		txtCorreo.setColumns(10);

		txtDomicilio = new JTextArea();
		txtDomicilio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				txtDomicilioKeyTyped(arg0);
			}
		});
		txtDomicilio.setEnabled(false);
		txtDomicilio.setBorder(UIManager.getBorder("TextField.border"));
		txtDomicilio.setBackground(Color.WHITE);
		txtDomicilio.setBounds(104, 112, 612, 54);
		pnDatosPersonales.add(txtDomicilio);

		lblEdad = new JLabel("");
		lblEdad.setVerticalAlignment(SwingConstants.BOTTOM);
		lblEdad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEdad.setBounds(209, 17, 58, 21);
		pnDatosPersonales.add(lblEdad);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(104, 182, 612, 128);
		pnDatosPersonales.add(scrollPane_1);

		txtObservacion = new JTextArea();
		scrollPane_1.setViewportView(txtObservacion);
		txtObservacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				txtObservacionKeyTyped(e);
			}
		});
		txtObservacion.setEnabled(false);
		txtObservacion.setBorder(UIManager.getBorder("TextField.border"));
		txtObservacion.setBackground(Color.WHITE);

		pnEvaluaciones = new JPanel();
		pnEvaluaciones.setBackground(Color.WHITE);
		tbVentanas.addTab("Evaluaciones", null, pnEvaluaciones, null);
		pnEvaluaciones.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 726, 256);
		pnEvaluaciones.add(scrollPane);

		tbEvaluaciones = new JTable();
		scrollPane.setViewportView(tbEvaluaciones);

		JPanel pnHistorial = new JPanel();
		pnHistorial.setBackground(Color.WHITE);
		tbVentanas.addTab("Historial", null, pnHistorial, null);
		formatearTablaEvaluaciones();
		panelDatos.cargarDatos();

	}

	// /////////////////// MANEJO DE EVENTOS ///////////////////////////
	protected void txtApellidosKeyTyped(KeyEvent e) {
		convertirMayusculas(e);
	}

	protected void txtNombresKeyTyped(KeyEvent arg0) {
		convertirMayusculas(arg0);
	}

	protected void txtObservacionKeyTyped(KeyEvent arg0) {
		convertirMayusculas(arg0);
	}

	protected void txtDomicilioKeyTyped(KeyEvent arg0) {
		convertirMayusculas(arg0);
	}

	protected void btnEstadoActionPerformed(ActionEvent arg0) {
		String estado = txtEstado.getText().substring(0, 1);
		if (estado.equals("A")) {
			cambiarEstado("I");
		} else if (estado.equals("I")) {
			cambiarEstado("A");
		}
	}

	@SuppressWarnings("static-access")
	protected void btnDirectorioActionPerformed(ActionEvent e) throws ClassNotFoundException, IOException {
		int resultado = 0;
		this.bytesImg = null;
		this.fichero = null;
		CargaImagenModal vista = new CargaImagenModal();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG", "jpg");
		vista.jfchImagen.setFileFilter(filtro);
		resultado = vista.jfchImagen.showOpenDialog(null);
		if (JFileChooser.APPROVE_OPTION == resultado && vista.jfchImagen.getSelectedFile().length() < (1024 * 100)) {
			this.fichero = vista.jfchImagen.getSelectedFile();
			try {
				ImageIcon icon = new ImageIcon(fichero.toString());
				Icon icono = new ImageIcon(icon.getImage().getScaledInstance(lblFotoCarnet.getWidth(),
						lblFotoCarnet.getHeight(), Image.SCALE_DEFAULT));
				lblFotoCarnet.setText(null);
				lblFotoCarnet.setIcon(icono);
			} catch (Exception exc) {
				exc.printStackTrace();
				JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_GUARDADOS_FAIL + " Error cargando la Imagen",
						"ATENCION", JOptionPane.ERROR_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(null,
					GlobalUtil.MSG_DATOS_GUARDADOS_FAIL + " Error cargando la Imagen: exedio el tamaño permitido",
					"ATENCION", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void btnOficinaActionPerformed(ActionEvent e) throws ClassNotFoundException {
		OficinaModal vista = new OficinaModal();
		vista.setVisible(true);
		obtenerOficina(vista);
	}

	protected void btnCargoActionPerformed(ActionEvent arg0) throws ClassNotFoundException {
		CargoModal vista = new CargoModal();
		vista.setVisible(true);
		obtenerCargo(vista);
	}

	protected void btnHorarioActionPerformed(ActionEvent e) throws ClassNotFoundException {
		HorarioModal vista = new HorarioModal();
		vista.setVisible(true);
		obtenerHorario(vista);

	}

	protected void btnDependenciaActionPerformed(ActionEvent e) throws ClassNotFoundException {
		DependenciaModal vista = new DependenciaModal();
		vista.setVisible(true);
		obtenerDependencia(vista);
	}

	protected void btnCondicionActionPerformed(ActionEvent arg0) throws ClassNotFoundException {
		CondicionModal vista = new CondicionModal();
		vista.setVisible(true);
		obtenerCondicion(vista);

	}

	protected void btnFuncionarioActionPerformed(ActionEvent arg0) throws ClassNotFoundException {
		PersonalModal vista = new PersonalModal("0");
		vista.setVisible(true);
		obtenerFuncionario(vista);
	}

	protected void btnCredencialActionPerformed(ActionEvent arg0) throws ClassNotFoundException, ParseException {
		if (sistema.existePersonal(txtCedula.getText())) {
			sistema.imprimirCredencial(txtCedula.getText());
		} else {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_FUNCIONARIO_NOT_FOUND, "ATENCION",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void txtObsHorarioKeyTyped(KeyEvent arg0) {
		convertirMayusculas(arg0);
	}

	protected void txtObsOficinaKeyTyped(KeyEvent arg0) {
		convertirMayusculas(arg0);
	}

	protected void txtObsDependenciaKeyTyped(KeyEvent arg0) {
		convertirMayusculas(arg0);
	}

	protected void txtObsCondicionKeyTyped(KeyEvent e) {
		convertirMayusculas(e);
	}

	// /////////////////// MANEJO DE METODOS ///////////////////////////
	@Override
	protected void agregarObjeto() {
		this.OPCION = 1;
		habilitarAgregar();
	}

	@Override
	protected void editarObjeto() throws ClassNotFoundException {
		if (txtCedula.getText() != null) {
			this.OPCION = 2;
			habilitarModificacion();
		} else {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_FUNCIONARIO_NOT_FOUND, "ATENCION",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	protected void borrarObjeto() throws ClassNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void guardarObjeto() throws ClassNotFoundException, ParseException, SQLException {
		// si la opcion es 1 es para agregar personal nuevo
		// si la opcion es 2 es para modificar datos del personal
		if (this.OPCION == 1) {
			try {
				guardarNuevoPersonal();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (this.OPCION == 2) {
			try {
				guardarActualizacion();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void cancelarOperacion() {
		this.setTitle("PERSONAL");
		panelBotones.deshabilitar();
		String cedula = txtCedula.getText();
		inicializarCampos();
		if (this.OPCION == 1) {
			desmarcarCamposObligatorios();
		} else if (this.OPCION == 2) {
			try {
				cargarCampos(cedula);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		this.OPCION = 0;
	}

	@Override
	protected void verObjeto() throws ClassNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void imprimirObjeto() throws ClassNotFoundException {
		// TODO Auto-generated method stub

	}

	// /////////////////// METODOS DEL SISTEMA ///////////////////////////
	private void guardarActualizacion() throws ParseException, ClassNotFoundException, SQLException, IOException {
		boolean ctrlCond = false, ctrlDep = false, ctrlOfi = false, ctrlCar = false, ctrlHor = false;
		String opCond = "", opDep = "", opOfi = "", opCar = "", opHor = "";
		// ######## DATOS LABORALES ########
		// guardar datos de la condicion del personal
		cargarNuevaCondicion();
		if (modificoCondicion()) {
			if (verificarPeriodoCondicion() == true) {
				ctrlCond = true;
				opCond = "I";
			}
		} else {
			// modificacion de la observacion
			ctrlCond = true;
			opCond = "A";
		}
		// guardar datos de la dependencia del personal
		cargarNuevaDependencia();
		if (modificoDependencia()) {
			if (verificarPeriodoDependencia() == true) {
				ctrlDep = true;
				opDep = "I";
			}
		} else {
			// modificacion de la observacion
			ctrlDep = true;
			opDep = "A";
		}
		// guardar datos de la oficina del personal
		cargarNuevaOficina();
		if (modificoOficina()) {
			if (verificarPeriodoOficina() == true) {
				ctrlOfi = true;
				opOfi = "I";

			}
		} else {
			// modificacion de la observacion
			ctrlOfi = true;
			opOfi = "A";
		}
		// guardar datos de la oficina del personal
		cargarNuevoCargo();
		if (modificoCargo()) {
			if (verificarPeriodoCargo() == true) {
				ctrlCar = true;
				opCar = "I";

			}
		} else {
			// modificacion de la observacion
			ctrlCar = true;
			opCar = "A";
		}

		// guardar datos del horario del personal
		cargarNuevoTurno();
		if (modificoTurno()) {
			if (verificarPeriodoTurno() == true) {
				ctrlHor = true;
				opHor = "I";
			}
		} else {
			// modificacion de la observacion
			ctrlHor = true;
			opHor = "A";
		}

		// verificar las actualizaciones y desplegar mensaje
		if (ctrlCond && ctrlDep && ctrlOfi && ctrlCar && ctrlHor && verificarCamposObligatorios()) {
			this.sistema.actualizarCondicionPersonal(this.perCon, opCond);
			this.sistema.actualizarDependenciaPersonal(this.perDep, opDep);
			this.sistema.actualizarOficinaPersonal(this.perOfi, opOfi);
			this.sistema.actualizarCargoPersonal(this.perCar, opCar);
			this.sistema.actualizarTurnoPersonal(this.perTur, opHor);
			Personal per = cargarNuevosDatosPersonales();
			this.sistema.actualizarDatosPersonal(per);
			if (this.fichero != null) {
				this.bytesImg = abrirImagen(this.fichero);
				File file = new File(per.getRutaImagen());
				guardarImagen(file, this.bytesImg);

			}

			this.OPCION = 0;
			JOptionPane.showMessageDialog(null, "Información actualizada correctamente", "ATENCION",
					JOptionPane.INFORMATION_MESSAGE);
			panelBotones.deshabilitar();
			String cedula = txtCedula.getText();
			inicializarCampos();
			cargarCampos(cedula);
		} else {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_GUARDADOS_FAIL + "Verifique periodos de tiempo",
					"ATENCION", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void guardarNuevoPersonal() throws ClassNotFoundException, SQLException, IOException {
		if (verificarCamposObligatorios()) {
			Personal per = cargarNuevoPersonal();
			this.sistema.agregarNuevoPersonal(per);
			if (this.fichero != null) {
				this.bytesImg = abrirImagen(this.fichero);
				File file = new File(per.getRutaImagen());
				guardarImagen(file, this.bytesImg);
			}
			this.OPCION = 0;
			JOptionPane.showMessageDialog(null, "Información agregada correctamente", "ATENCION",
					JOptionPane.INFORMATION_MESSAGE);
			panelBotones.deshabilitar();
			String cedula = txtCedula.getText();
			inicializarCampos();
			desmarcarCamposObligatorios();
			cargarCampos(cedula);
		} else {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_GUARDADOS_FAIL + "Verifique Campos obligatorios",
					"ATENCION", JOptionPane.ERROR_MESSAGE);
		}
	}

	private Personal cargarNuevoPersonal() {
		Personal per = cargarNuevosDatosPersonales();
		per.setNombres(txtNombres.getText().trim());
		per.setApellidos(txtApellidos.getText().trim());
		per.setAdministradorAlta(MenuPrincipalVista.administrador.getLogin());

		// ######### CONDICION ##########
		per.setCodCondicion(Integer.parseInt(txtCodCond.getText()));
		per.setCondFechaIni(dcVigCondicion.getDate());
		per.setCondObservacion(txtObsCondicion.getText().trim());

		// ########### DEPENDENCIA ###########
		per.setCodDependencia(txtCodDep.getText());
		per.setDepFechaIni(dcVigDependencia.getDate());
		per.setDepObservacion(txtObsDependencia.getText().trim());

		// ########### OFICINA ###########
		per.setCodOficina(txtCodOfi.getText());
		per.setOfiFechaIni(dcVigOficina.getDate());
		per.setOfiObservacion(txtObsOficina.getText().trim());

		// ########### CARGO ###########
		per.setCodCargo(Integer.parseInt(txtCodCargo.getText()));
		per.setCarFechaIni(dcVigCargo.getDate());
		per.setCarObservacion(txtObsCargo.getText().trim());

		// ########### HORARIO ###########
		per.setCodHorario(Integer.parseInt(txtCodHor.getText()));
		per.setHorFechaIni(dcVigHorario.getDate());
		per.setHorObservacion(txtObsHorario.getText().trim());

		return per;
	}

	private boolean verificarCamposObligatorios() {
		boolean control = true;
		if (txtCedula.getText().trim().equals("")) {
			control = false;
		} else if (txtCedula.getText().trim().length() < 4) {
			control = false;
		}
		if (txtNombres.getText().trim().equals("")) {
			control = false;
		}
		if (txtApellidos.getText().trim().equals("")) {
			control = false;
		}
		if (lblFotoCarnet.getIcon() == null) {
			control = false;
		}
		if (txtCodCond.getText().equals("")) {
			control = false;
		}
		if (dcVigCondicion.getDate() == null) {
			control = false;
		}
		if (txtCodDep.getText().equals("")) {
			control = false;
		}
		if (dcVigDependencia.getDate() == null) {
			control = false;
		}
		if (txtCodOfi.getText().equals("")) {
			control = false;
		}
		if (dcVigOficina.getDate() == null) {
			control = false;
		}
		if (txtCodHor.getText().equals("")) {
			control = false;
		}
		if (dcVigHorario.getDate() == null) {
			control = false;
		}
		if (dcFechaNacimiento.getDate() == null) {
			control = false;
		}

		return control;
	}

	private void marcarCamposObligatorios() {
		lblCedula.setText("*Cedula:");
		lblCedula.setForeground(Color.RED);
		lblNombres.setText("*Nombres:");
		lblNombres.setForeground(Color.RED);
		lblApellidos.setText("*Apellidos:");
		lblApellidos.setForeground(Color.RED);
		lblFotoCarnet.setBorder(new LineBorder(Color.RED, 2));

		lblCondicion.setText("*Condicion:");
		lblCondicion.setForeground(Color.RED);
		lblVigCondicion.setText("*Vigencia:");
		lblVigCondicion.setForeground(Color.RED);

		lblDependencia.setText("*Dependencia:");
		lblDependencia.setForeground(Color.RED);
		lblVigDependencia.setText("*Vigencia:");
		lblVigDependencia.setForeground(Color.RED);

		lblOficina.setText("*Oficina:");
		lblOficina.setForeground(Color.RED);
		lblVigOficina.setText("*Vigencia:");
		lblVigOficina.setForeground(Color.RED);

		lblCargo.setText("*Cargo:");
		lblCargo.setForeground(Color.RED);
		lblVigCargo.setText("*Vigencia:");
		lblVigCargo.setForeground(Color.RED);

		lblHorario.setText("*Horario:");
		lblHorario.setForeground(Color.RED);
		lblVigHorario.setText("*Vigencia:");
		lblVigHorario.setForeground(Color.RED);

		lblFechaNacimento.setText("<html><body>*Fecha de<br> Nacimento:</body></html>");
		lblFechaNacimento.setForeground(Color.RED);
	}

	private void desmarcarCamposObligatorios() {
		lblCedula.setText("Cedula:");
		lblCedula.setForeground(Color.BLACK);
		lblNombres.setText("Nombres:");
		lblNombres.setForeground(Color.BLACK);
		lblApellidos.setText("Apellidos:");
		lblApellidos.setForeground(Color.BLACK);
		lblFotoCarnet.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));

		lblCondicion.setText("Condicion:");
		lblCondicion.setForeground(Color.BLACK);
		lblVigCondicion.setText("Vigencia:");
		lblVigCondicion.setForeground(Color.BLACK);

		lblDependencia.setText("Dependencia:");
		lblDependencia.setForeground(Color.BLACK);
		lblVigDependencia.setText("Vigencia:");
		lblVigDependencia.setForeground(Color.BLACK);

		lblOficina.setText("Oficina:");
		lblOficina.setForeground(Color.BLACK);
		lblVigOficina.setText("Vigencia:");
		lblVigOficina.setForeground(Color.BLACK);

		lblCargo.setText("Oficina:");
		lblCargo.setForeground(Color.BLACK);
		lblVigCargo.setText("Vigencia:");
		lblVigCargo.setForeground(Color.BLACK);

		lblHorario.setText("Horario:");
		lblHorario.setForeground(Color.BLACK);
		lblVigHorario.setText("Vigencia:");
		lblVigHorario.setForeground(Color.BLACK);

		lblFechaNacimento.setText("<html><body>Fecha de<br> Nacimento:</body></html>");
		lblFechaNacimento.setForeground(Color.BLACK);
	}

	// carga de datos del funcionario
	private void cargarCampos(String cedula) throws ClassNotFoundException {
		// ########### CABECERA ###########
		cargarCabecera(cedula);
		// ########### DATOS LABORALES ###########
		cargarDatosLaborales();
		// ########### DATOS PERSONALES ###########
		cargarDatosPersonales();
		// ########### EVALUACIONES ###########
		cargarEvaluaciones();
	}

	private void habilitarAgregar() {
		panelBotones.habilitar();
		inicializarCampos();
		this.setTitle("PERSONAL (AGREGANDO)");
		btnFuncionario.setEnabled(false);

		// habilitar datos a cargar
		txtCedula.setEnabled(true);
		txtApellidos.setEnabled(true);
		txtNombres.setEnabled(true);
		habilitarDatosLaborales();
		dcVigCondicion.setEnabled(true);
		dcVigDependencia.setEnabled(true);
		dcVigOficina.setEnabled(true);
		dcVigCargo.setEnabled(true);
		dcVigHorario.setEnabled(true);
		habilitarDatosPersonales();
		btnDirectorio.setEnabled(true);
		marcarCamposObligatorios();

	}

	private void habilitarModificacion() {
		this.setTitle("PERSONAL (MODIFICANDO)");
		// ########### CABECERA ###########
		habilitarCabecera();
		// ########### DATOS LABORALES ###########
		habilitarDatosLaborales();
		// ########### DATOS PERSONALES ###########
		habilitarDatosPersonales();
		// ########### EVALUACIONES ###########

		habilitarEvaluaciones(this.personal.getCedula());
		panelBotones.habilitar();
	}

	private void inicializarCampos() {
		// ########### CABECERA ###########
		inicializarCabecera();
		// ########### DATOS LABORALES ###########
		inicializarDatosLaborales();
		// ########### DATOS PERSONALES ###########
		inicailizarDatosPersonales();
		// ########### EVALUACIONES ###########
		inicializarEvaluaciones();
		this.panelBotones.btnModificar.setEnabled(false);
	}

	// datos de la cabecera
	private void cargarCabecera(String cedula) throws ClassNotFoundException {
		this.personal = sistema.obtenerFuncionario(cedula);
		txtCedula.setText(this.personal.getCedula());
		txtNombres.setText(this.personal.getNombres());
		txtApellidos.setText(this.personal.getApellidos());
		// ############# IMAGEN ###########
		ImageIcon image = new ImageIcon(this.personal.getRutaImagen());
		Icon icono = new ImageIcon(image.getImage().getScaledInstance(lblFotoCarnet.getWidth(),
				lblFotoCarnet.getHeight(), Image.SCALE_DEFAULT));
		lblFotoCarnet.setIcon(icono);
		panelBotones.btnModificar.setEnabled(true);
		this.btnCredencial.setEnabled(true);
		cambiarEstado(this.personal.getEstado());
	}

	private void habilitarCabecera() {
		btnFuncionario.setEnabled(false);
		btnDirectorio.setEnabled(true);
		btnCredencial.setEnabled(false);
		this.btnActualizarEstado.setEnabled(true);
	}

	private void inicializarCabecera() {
		this.setTitle("PERSONAL");
		txtCedula.setText("");
		txtCedula.setEnabled(false);
		txtNombres.setText("");
		txtNombres.setEnabled(false);
		txtApellidos.setText("");
		txtApellidos.setEnabled(false);
		btnFuncionario.setEnabled(true);
		btnDirectorio.setEnabled(false);
		btnActualizarEstado.setEnabled(false);
		btnCredencial.setEnabled(false);
		// ######## IMAGEN ##############
		lblFotoCarnet.setIcon(null);
		cambiarEstado("SIN ESTADO");
	}

	// ventana de datos laborales
	private void cargarDatosLaborales() {

		// ######### CONDICION ##########
		txtCodCond.setText(Integer.toString(this.personal.getCodCondicion()));
		txtCondicion.setText(this.personal.getCondicion());
		txtObsCondicion.setText(this.personal.getCondObservacion());
		dcVigCondicion.setDate(this.personal.getCondFechaIni());

		// ########### DEPENDENCIA ###########
		txtCodDep.setText(this.personal.getCodDependencia());
		txtDependencia.setText(this.personal.getDependencia());
		txtObsDependencia.setText(this.personal.getDepObservacion());
		dcVigDependencia.setDate(this.personal.getDepFechaIni());

		// ########### OFICINA ###########
		txtCodOfi.setText(this.personal.getCodOficina());
		txtOficina.setText(this.personal.getOficina());
		txtObsOficina.setText(this.personal.getOfiObservacion());
		dcVigOficina.setDate(this.personal.getOfiFechaIni());

		// ########### CARGO ###########
		txtCodCargo.setText(Integer.toString(this.personal.getCodCargo()));
		txtCargo.setText(this.personal.getCargo());
		txtObsCargo.setText(this.personal.getCarObservacion());
		dcVigCargo.setDate(this.personal.getCarFechaIni());

		// ########### HORARIO ###########
		txtCodHor.setText(Integer.toString(this.personal.getCodHorario()));
		txtEntrada.setText(this.personal.getHorEntrada().toString());
		txtSalida.setText(this.personal.getHorSalida().toString());
		txtObsHorario.setText(this.personal.getHorObservacion());
		dcVigHorario.setDate(this.personal.getHorFechaIni());
	}

	private void habilitarDatosLaborales() {
		// habilitacion de Condicion
		btnCondicion.setEnabled(true);
		txtObsCondicion.setEnabled(true);

		// habilitacion de dependencia
		btnDependencia.setEnabled(true);
		txtObsDependencia.setEnabled(true);

		// habilitacion de Cargos
		btnCargo.setEnabled(true);
		txtObsCargo.setEnabled(true);

		// habilitacion de oficina
		btnOficina.setEnabled(true);
		txtObsOficina.setEnabled(true);

		// habilitacion de horario
		btnHorario.setEnabled(true);
		txtObsHorario.setEnabled(true);

	}

	private void inicializarDatosLaborales() {
		// ########### CONDICION ###########
		txtCodCond.setText("");
		txtCondicion.setText("");
		dcVigCondicion.setEnabled(false);
		dcVigCondicion.setDate(null);
		txtObsCondicion.setEnabled(false);
		txtObsCondicion.setText("");
		btnCondicion.setEnabled(false);

		// ########### DEPENDENCIA ###########
		txtCodDep.setText("");
		txtDependencia.setText("");
		dcVigDependencia.setEnabled(false);
		dcVigDependencia.setDate(null);
		txtObsDependencia.setEnabled(false);
		txtObsDependencia.setText("");
		btnDependencia.setEnabled(false);

		// ########### OFICINA ###########
		txtCodOfi.setText("");
		txtOficina.setText("");
		dcVigOficina.setEnabled(false);
		dcVigOficina.setDate(null);
		txtObsOficina.setEnabled(false);
		txtObsOficina.setText("");
		btnOficina.setEnabled(false);

		// ########### CARGO ###########
		txtCodCargo.setText("");
		txtCargo.setText("");
		dcVigCargo.setEnabled(false);
		dcVigCargo.setDate(null);
		txtObsCargo.setEnabled(false);
		txtObsCargo.setText("");
		btnCargo.setEnabled(false);

		// ########### HORARIO ###########
		dcVigHorario.setEnabled(false);
		dcVigHorario.setDate(null);
		txtCodHor.setText("");
		txtCodHor.setEnabled(false);
		txtObsHorario.setText("");
		txtObsHorario.setEnabled(false);
		txtEntrada.setText("");
		txtSalida.setText("");
		btnHorario.setEnabled(false);

	}

	private boolean modificoCondicion() {
		boolean control = true;
		int codigo = this.personal.getCodCondicion();
		if (Integer.toString(codigo).equals(txtCodCond.getText())) {
			control = false;
		}
		return control;
	}

	private boolean verificarPeriodoCondicion() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean control = false;
		if (dcVigCondicion.getDate() != null) {
			Date fechaInicioActual = sdf.parse(String.valueOf(sdf.format(dcVigCondicion.getDate())));
			control = fechaInicioActual.after(this.personal.getCondFechaIni());
		}
		return control;
	}

	private boolean modificoDependencia() {
		boolean control = true;
		String codigo = this.personal.getCodDependencia();
		if (codigo.equals(txtCodDep.getText())) {
			control = false;
		}
		return control;
	}

	private boolean verificarPeriodoDependencia() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean control = false;
		if (dcVigDependencia.getDate() != null) {
			Date fechaInicioActual = sdf.parse(String.valueOf(sdf.format(dcVigDependencia.getDate())));
			control = fechaInicioActual.after(this.personal.getDepFechaIni());
		}
		return control;
	}

	private boolean modificoOficina() {
		boolean control = true;
		String codigo = this.personal.getCodOficina();
		if (codigo.equals(txtCodOfi.getText())) {
			control = false;
		}
		return control;
	}

	private boolean verificarPeriodoOficina() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean control = false;
		if (dcVigOficina.getDate() != null) {
			Date fechaInicioActual = sdf.parse(String.valueOf(sdf.format(dcVigOficina.getDate())));
			control = fechaInicioActual.after(this.personal.getOfiFechaIni());
		}
		return control;
	}

	private boolean modificoCargo() {
		boolean control = true;
		String codigo = Integer.toString(this.personal.getCodCargo());
		if (codigo.equals(txtCodCargo.getText())) {
			control = false;
		}
		return control;
	}

	private boolean verificarPeriodoCargo() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean control = false;
		if (dcVigCargo.getDate() != null) {
			Date fechaInicioActual = sdf.parse(String.valueOf(sdf.format(dcVigCargo.getDate())));
			control = fechaInicioActual.after(this.personal.getCarFechaIni());
		}
		return control;
	}

	private boolean modificoTurno() {
		boolean control = true;
		String codigo = Integer.toString(this.personal.getCodHorario());
		if (codigo.equals(txtCodHor.getText())) {
			control = false;
		}
		return control;
	}

	private boolean verificarPeriodoTurno() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean control = false;
		if (dcVigHorario.getDate() != null) {
			Date fechaInicioActual = sdf.parse(String.valueOf(sdf.format(dcVigHorario.getDate())));
			control = fechaInicioActual.after(this.personal.getHorFechaIni());
		}
		return control;
	}

	// ventana de datos personales
	private void cargarDatosPersonales() {
		dcFechaNacimiento.setDate(this.personal.getFechaNacimiento());
		FechaUtil fu = new FechaUtil();
		lblEdad.setText(fu.getEdad(this.personal.getFechaNacimiento()).substring(11));
		if (this.personal.getSexo() != null) {
			if (this.personal.getSexo().equals("M")) {
				cbSexo.setSelectedItem("MASCULINO");
			} else if (this.personal.getSexo().equals("F")) {
				cbSexo.setSelectedItem("FEMENINO");
			}
		} else {
			cbSexo.setSelectedIndex(0);
		}
		if (this.personal.getEstadoCivil() != null) {
			cbEstadoCivil.setSelectedItem(this.personal.getEstadoCivil());
		} else {
			cbEstadoCivil.setSelectedIndex(0);
		}
		txtTelefonos.setText(this.personal.getTelefonos());
		txtCorreo.setText(this.personal.getCorreo());
		txtDomicilio.setText(this.personal.getDomicilio());
		txtObservacion.setText(this.personal.getObservacion());
	}

	private void habilitarDatosPersonales() {
		dcFechaNacimiento.setEnabled(true);
		cbSexo.setEnabled(true);
		cbEstadoCivil.setEnabled(true);
		txtTelefonos.setEnabled(true);
		txtCorreo.setEnabled(true);
		txtDomicilio.setEnabled(true);
		txtObservacion.setEnabled(true);
	}

	private void inicailizarDatosPersonales() {
		dcFechaNacimiento.setEnabled(false);
		dcFechaNacimiento.setDate(null);
		cbSexo.setSelectedIndex(0);
		cbSexo.setEnabled(false);
		cbEstadoCivil.setSelectedIndex(0);
		cbEstadoCivil.setEnabled(false);
		txtTelefonos.setText("");
		txtTelefonos.setEnabled(false);
		txtCorreo.setText("");
		txtCorreo.setEnabled(false);
		txtDomicilio.setText("");
		txtDomicilio.setEnabled(false);
		txtObservacion.setText("");
		txtObservacion.setEnabled(false);
		lblEdad.setText("");
		// inicialización de la variable de imagen del funcionario
		this.fichero = null;
	}

	// ventana de evaluaciones

	private void cargarEvaluaciones() throws ClassNotFoundException {
		limpiarTabla(tbEvaluaciones);
		Object[] fila = new Object[6];
		for (Evaluacion eva : sistema.obtenerEvaluaciones(this.personal.getCedula())) {
			fila[0] = eva.getPeriodo();
			fila[1] = eva.getPuntajeDesempeno();
			fila[2] = eva.getPuntajeExamen();
			fila[3] = eva.getPuntajeTotal();
			fila[4] = eva.getNota();

			modelo.addRow(fila);
			tbEvaluaciones.setModel(modelo);
		}
	}

	private void habilitarEvaluaciones(String cedula) {
		// TODO Auto-generated method stub
	}

	private void inicializarEvaluaciones() {
		limpiarTabla(tbEvaluaciones);
	}

	// obtener objeto de una lista

	private void obtenerOficina(OficinaModal vista) throws ClassNotFoundException {
		if (vista.getCodigo() != null) {
			Oficina ofi = this.sistema.obtenerOficina(vista.getCodigo());
			txtCodOfi.setText(ofi.getCodigo());
			txtOficina.setText(ofi.getDescripcion());
			if (this.OPCION == 2) {
				if (!this.personal.getCodOficina().equals(txtCodOfi.getText())) {
					txtObsOficina.setText("");
					dcVigOficina.setEnabled(true);
				} else {
					txtObsOficina.setText(this.personal.getOfiObservacion());
					dcVigOficina.setDate(this.personal.getOfiFechaIni());
					dcVigOficina.setEnabled(false);
				}
			}
		}
	}

	private void obtenerCargo(CargoModal vista) throws ClassNotFoundException {
		if (vista.getCodigo() != null) {
			Cargo cargo = this.sistema.obtenerCargo(vista.getCodigo());
			txtCodCargo.setText(Integer.toString(cargo.getCodigo()));
			txtCargo.setText(cargo.getDescripcion());

			if (this.OPCION == 2) {
				if (!Integer.toString(this.personal.getCodCargo()).equals(txtCodCargo.getText())) {
					txtObsCargo.setText("");
					dcVigCargo.setEnabled(true);
				} else {
					txtObsCargo.setText(this.personal.getCarObservacion());
					dcVigCargo.setDate(this.personal.getCarFechaIni());
					dcVigCargo.setEnabled(false);
				}
			}

		}
	}

	private void obtenerCondicion(CondicionModal vista) throws ClassNotFoundException {
		if (vista.getCodigo() != null) {
			TipoPersonal con = this.sistema.obtenerCondicionById(vista.getCodigo());
			txtCodCond.setText(Integer.toString(con.getId()));
			txtCondicion.setText(con.getDescripcion());
			if (this.OPCION == 2) {
				if (!Integer.toString(this.personal.getCodCondicion()).equals(txtCodCond.getText())) {
					txtObsCondicion.setText("");
					dcVigCondicion.setEnabled(true);
				} else {
					txtObsCondicion.setText(this.personal.getCondObservacion());
					dcVigCondicion.setDate(this.personal.getCondFechaIni());
					dcVigCondicion.setEnabled(false);
				}

			}
		}
	}

	private void obtenerDependencia(DependenciaModal vista) throws ClassNotFoundException {
		if (vista.getCodigo() != null) {
			Dependencia dep = this.sistema.obtenerDependencia(vista.getCodigo());
			txtCodDep.setText(dep.getCodigo());
			txtDependencia.setText(dep.getDescripcion());
			if (this.OPCION == 2) {
				if (!this.personal.getCodDependencia().equals(txtCodDep.getText())) {
					txtObsDependencia.setText("");
					dcVigDependencia.setEnabled(true);
				} else {
					txtObsDependencia.setText(this.personal.getDepObservacion());
					dcVigHorario.setDate(this.personal.getDepFechaIni());
					dcVigDependencia.setEnabled(false);
				}
			}
		}
	}

	private void obtenerHorario(HorarioModal vista) throws ClassNotFoundException {
		if (vista.getHorarioId() != 0) {
			Turno turno = this.sistema.obtenerTurno(vista.getHorarioId());
			txtCodHor.setText(Integer.toString(turno.getId()));
			txtEntrada.setText(turno.getEntrada().toString());
			txtSalida.setText(turno.getSalida().toString());
			if (this.OPCION == 2) {
				if (!Integer.toString(this.personal.getCodHorario()).equals(txtCodHor.getText())) {
					txtObsHorario.setText("");
					dcVigHorario.setEnabled(true);
				} else {
					txtObsHorario.setText(this.personal.getHorObservacion());
					dcVigHorario.setDate(this.personal.getHorFechaIni());
					dcVigHorario.setEnabled(false);
				}
			}
		}
	}

	private void obtenerFuncionario(PersonalModal vista) throws ClassNotFoundException {
		if (vista.getCedula() != null) {
			String cedula = vista.getCedula();
			cargarCampos(cedula);
		}
	}

	// cargar nuevos objetos para guardar en la BD
	private void cargarNuevaCondicion() {
		this.perCon = new PerCon();
		this.perCon.setCedula(txtCedula.getText());
		this.perCon.setCondicionId(Integer.parseInt(txtCodCond.getText()));
		this.perCon.setFechaInicio(dcVigCondicion.getDate());
		this.perCon.setObservacion(txtObsCondicion.getText().trim());
		this.perCon.setAdministrador(MenuPrincipalVista.administrador.getLogin());
	}

	private void cargarNuevaDependencia() {
		this.perDep = new PerDep();
		this.perDep.setCedula(txtCedula.getText());
		this.perDep.setDependenciaCodigo(txtCodDep.getText());
		this.perDep.setFechaInicio(dcVigDependencia.getDate());
		this.perDep.setObservacion(txtObsDependencia.getText().trim());
		this.perDep.setAdministrador(MenuPrincipalVista.administrador.getLogin());
	}

	private void cargarNuevaOficina() {
		this.perOfi = new PerOfi();
		this.perOfi.setCedula(txtCedula.getText());
		this.perOfi.setOficinaCodigo(txtCodOfi.getText());
		this.perOfi.setFechaInicio(dcVigOficina.getDate());
		this.perOfi.setObservacion(txtObsOficina.getText().trim());
		this.perOfi.setAdministrador(MenuPrincipalVista.administrador.getLogin());
	}

	private void cargarNuevoCargo() {
		this.perCar = new PerCar();
		this.perCar.setCedula(txtCedula.getText());
		this.perCar.setCargoId(Integer.parseInt(txtCodCargo.getText()));
		this.perCar.setFechaInicio(dcVigCargo.getDate());
		this.perCar.setObservacion(txtObsCargo.getText().trim());
		this.perCar.setAdministrador(MenuPrincipalVista.administrador.getLogin());
	}

	private void cargarNuevoTurno() {
		this.perTur = new PerTur();
		this.perTur.setCedula(txtCedula.getText());
		this.perTur.setTurnoId(Integer.parseInt(txtCodHor.getText()));
		this.perTur.setFechaInicio(dcVigHorario.getDate());
		this.perTur.setObservacion(txtObsHorario.getText().trim());
		this.perTur.setAdministrador(MenuPrincipalVista.administrador.getLogin());
	}

	private Personal cargarNuevosDatosPersonales() {
		Personal per = new Personal();
		per.setCedula(txtCedula.getText().trim());
		if (!txtEstado.getText().equals("")) {
			per.setEstado(txtEstado.getText().substring(0, 1));
		}
		per.setFechaNacimiento(dcFechaNacimiento.getDate());
		if (cbSexo.getSelectedIndex() == 0) {
			per.setSexo("");
		} else {
			per.setSexo(cbSexo.getSelectedItem().toString().substring(0, 1));
		}
		if (cbEstadoCivil.getSelectedIndex() == 0) {
			per.setEstadoCivil("");
		} else {
			per.setEstadoCivil(cbEstadoCivil.getSelectedItem().toString());
		}
		per.setTelefonos(txtTelefonos.getText().trim());
		per.setObservacion(txtObservacion.getText().trim());
		per.setCorreo(txtCorreo.getText().trim());
		per.setDomicilio(txtDomicilio.getText().trim());
		return per;
	}

	// otros

	private void cambiarEstado(String estado) {
		if (estado.equals("A")) {
			txtEstado.setText("ACTIVO");
			txtEstado.setBackground(Color.GREEN);
			lblFotoCarnet.setBorder(BorderFactory.createLineBorder(Color.GREEN));
			pnFuncionario.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		} else if (estado.equals("I")) {
			txtEstado.setText("INACTIVO");
			txtEstado.setBackground(Color.RED);
			lblFotoCarnet.setBorder(BorderFactory.createLineBorder(Color.RED));
			pnFuncionario.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else {
			txtEstado.setText("");
			txtEstado.setBackground(Color.LIGHT_GRAY);
			lblFotoCarnet.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			pnFuncionario.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		}
		txtEstado.setEnabled(true);
		txtEstado.setEditable(false);
	}

	private void formatearTablaEvaluaciones() {
		modelo.addColumn("PERIODO");
		modelo.addColumn("PUNTAJE DESEMP.");
		modelo.addColumn("PUNTAJE EXAMEN");
		modelo.addColumn("PUNTAJE TOTAL");
		modelo.addColumn("NOTA");
		tbEvaluaciones.setModel(modelo);
		TableColumn columna0 = tbEvaluaciones.getColumn("PERIODO");
		columna0.setPreferredWidth(200);
	}

	private void limpiarTabla(JTable tabla) {
		while (tabla.getRowCount() != 0) {
			modelo.removeRow(0);
		}
	}

	private void convertirMayusculas(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		if (Character.isLowerCase(c)) {
			String cadena = ("" + c).toUpperCase();
			c = cadena.charAt(0);
			arg0.setKeyChar(c);
		}

	}

	private byte[] abrirImagen(File archivo) throws IOException {
		byte[] bytesImg = new byte[1024 * 100];
		if (archivo != null) {
			entrada = new FileInputStream(archivo);
			entrada.read(bytesImg);
		}
		return bytesImg;
	}

	private String guardarImagen(File archivo, byte[] bytesImg) throws IOException {
		String respuesta = null;
		salida = new FileOutputStream(archivo);
		salida.write(bytesImg);
		return respuesta;
	}
}
