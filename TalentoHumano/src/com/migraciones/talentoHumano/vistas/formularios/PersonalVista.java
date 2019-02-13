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
import java.awt.event.MouseAdapter;
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
import com.migraciones.talentoHumano.modelos.Categoria;
import com.migraciones.talentoHumano.modelos.Dependencia;
import com.migraciones.talentoHumano.modelos.Evaluacion;

import com.migraciones.talentoHumano.modelos.PerCar;
import com.migraciones.talentoHumano.modelos.PerCat;
import com.migraciones.talentoHumano.modelos.PerCon;
import com.migraciones.talentoHumano.modelos.PerDep;
import com.migraciones.talentoHumano.modelos.PerDias;
import com.migraciones.talentoHumano.modelos.PerFechaDesvinculacion;
import com.migraciones.talentoHumano.modelos.PerFechaIngreso;
import com.migraciones.talentoHumano.modelos.PerOfi;
import com.migraciones.talentoHumano.modelos.PerTipos;
import com.migraciones.talentoHumano.modelos.PerTur;
import com.migraciones.talentoHumano.modelos.Personal;
import com.migraciones.talentoHumano.modelos.TipoPersonal;
import com.migraciones.talentoHumano.modelos.Turno;
import com.migraciones.talentoHumano.utilities.FechaUtil;
import com.migraciones.talentoHumano.utilities.GlobalUtil;
import com.migraciones.talentoHumano.views.componentesGUI.ModeloTablaUtil;
import com.toedter.calendar.JDateChooser;

import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseEvent;

public class PersonalVista extends AncestroVista {
	private static final long serialVersionUID = 1L;
	private int OPCION = 0;// 1.- agregar; 2.- modificar; 3.- eliminar
	private TalentoHumano sistema = new TalentoHumano();
	private ModeloTablaUtil modelo = new ModeloTablaUtil();
	private ModeloTablaUtil modeloCat = new ModeloTablaUtil();
	private ModeloTablaUtil modeloDep = new ModeloTablaUtil();
	private ModeloTablaUtil modeloOfi = new ModeloTablaUtil();
	private ModeloTablaUtil modeloTipo = new ModeloTablaUtil();
	private ModeloTablaUtil modeloTurnos = new ModeloTablaUtil();
	private ModeloTablaUtil modeloDias = new ModeloTablaUtil();
	private Personal personal;
	private PerCon perCon;
	private PerCar perCar;
	private PerDep perDep;
	private PerTur perTur;
	private PerCat perCat;
	private PerDias perDias; 
	private PerFechaIngreso perFechaIngreso;
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

	private JTabbedPane tbVentanas;
	private JPanel pnDatosLaborales;
	private JPanel pnDatosPersonales;
	private JPanel pnEvaluaciones;
	private File fichero;
	private JLabel lblFotoCarnet;
	private JPanel pnFuncionario;
	// ------------------------------
	private JPanel pnHistorialCategoria;
	private JPanel pnHistorialDependencia;
	private JPanel pnHistorialOficina;
	private JPanel pnHistorialTipo;
	private JPanel pnHistorialTurnos;
	private JPanel pnHistorialDias;

	private JButton btnDirectorio;
	private JButton btnImagen;
	private JButton btnCredencial;
	private JButton btnActualizarEstado;
	private JButton btnFuncionario;
	private JTable tbEvaluaciones;
	// -------------------------------------
	private JTable tbCategoria;
	private JTable tbDependencia;
	private JTable tbOficina;
	private JTable tbTipos;
	private JTable tbTurnos;
	private JTable tbDias;
	private JTextField txtTelefonos;
	private JTextField txtCorreo;
	private JLabel lblFechaNacimento;
	private JLabel lblCedula;
	private JLabel lblNombres;
	private JLabel lblApellidos;

	private FileInputStream entrada;
	private FileOutputStream salida;
	private byte[] bytesImg;
	private JScrollPane scrollPane_1;
	private JLabel label_13;
	private JTextField txtObsHorario;
	private JLabel label_14;
	private JTextField txtEntrada;
	private JLabel label_15;
	private JTextField txtSalida;
	private JLabel lblVigHorario;
	private JButton btnHorario;
	private JLabel lblHorario;
	private JTextField txtCodHor;
	private JLabel lblCargo;
	private JLabel lblObs;
	private JTextField txtObsCargo;
	private JTextField txtCodCargo;
	private JTextField txtCargo;
	private JButton btnCargo;
	private JLabel lblVigCargo;
	private JDateChooser dcVigCargo;
	private JDateChooser dcVigHorario;
	private JLabel lblSexo;
	private JTextField txtCondicion;
	private JTextField txtCodCond;
	private JDateChooser dcDesdeCondicion;
	private JLabel lblCondicion;
	private JLabel lblVigCondicion;
	private JButton btnCondicion;
	private JLabel lblHastaCondicion;
	private JDateChooser dcHastaCondicion;
	private JDateChooser dcFechaIngreso;
	private JLabel lblFechaIngreso;
	private JTextField txtObsCondicion;
	private JLabel lblObsIngreso;
	private JTextField txtObsIngreso;
	private String varCondicion;
	private JTextField txtCodDep;
	private JTextField txtDependencia;
	private JTextField txtObsDependencia;
	private JTextField txtCodOfi;
	private JTextField txtOficina;
	private JLabel lblDependencia;
	private JLabel lblOficina;
	private JDateChooser dcVigDependencia;
	private JLabel lblVigDependencia;
	private JButton btnDependencia;
	private JTextField txtObsDesvinculacion;
	private JDateChooser dcDesvinculacion;
	private JLabel lblDia;
	@SuppressWarnings("rawtypes")
	private JComboBox cbDia;
	private JTextField txtCodCategoria;
	private JTextField txtObsCategoria;
	private JLabel lblCategoria;
	private JDateChooser dcVigCat;
	private JLabel lblVigCategoria;
	private JTextField txtCategoria;
	private JButton btnCategoria;
	private JLabel label_1;
	private JTextField txtObsDia;
	private JDateChooser dcVigDia;
	private JPanel panelDependencia;
	private JPanel panelCredencial;
	private JPanel panelHorario;
	private JLabel lblVigDias;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PersonalVista() {
		panelBotones.btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		setNormalBounds(new Rectangle(100, 100, 780, 600));
		panelBotones.btnEliminar.setVisible(false);
		panelBotones.btnVer.setEnabled(false);
		panelBotones.btnEliminar.setEnabled(false);
		panelBotones.btnModificar.setEnabled(false);
		setBounds(new Rectangle(100, 100, 770, 600));
		setPreferredSize(new Dimension(770, 600));
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
				new ImageIcon(PersonalVista.class.getResource("/com/migraciones/talentoHumano/graphics/lupa.png")));
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
				PersonalVista.class.getResource("/com/migraciones/talentoHumano/graphics/btnImagen.png")));
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
				PersonalVista.class.getResource("/com/migraciones/talentoHumano/graphics/btnDirectorio.png")));
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
				PersonalVista.class.getResource("/com/migraciones/talentoHumano/graphics/btnCarnet.png")));
		btnCredencial.setToolTipText("Generar carnet");
		btnCredencial.setEnabled(false);
		panelCampos.add(btnCredencial);

		btnActualizarEstado = new JButton("");
		btnActualizarEstado.setBounds(716, 131, 25, 23);
		btnActualizarEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnEstadoActionPerformed(arg0);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnActualizarEstado.setIcon(
				new ImageIcon(PersonalVista.class.getResource("/com/migraciones/talentoHumano/graphics/salir.png")));
		btnActualizarEstado.setToolTipText("Activar/Inactivar");
		btnActualizarEstado.setEnabled(false);
		panelCampos.add(btnActualizarEstado);

		tbVentanas = new JTabbedPane(JTabbedPane.TOP);
		tbVentanas.setBounds(10, 165, 731, 340);
		panelCampos.add(tbVentanas);
		
				pnDatosPersonales = new JPanel();
				pnDatosPersonales.setBackground(Color.WHITE);
				tbVentanas.addTab("Datos Personales", null, pnDatosPersonales, null);
				lblFechaNacimento = new JLabel("<html><body>Fecha de<br> Nacimento:</body></html>");
				lblFechaNacimento.setBounds(3, 4, 89, 32);
				lblFechaNacimento.setHorizontalAlignment(SwingConstants.RIGHT);
				lblFechaNacimento.setFont(new Font("Tahoma", Font.BOLD, 11));
				
						lblSexo = new JLabel("Sexo:");
						lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
						lblSexo.setBounds(236, 11, 89, 25);
						lblSexo.setFont(new Font("Tahoma", Font.BOLD, 11));
						
								JLabel lblDomicilio = new JLabel("Domicilio:");
								lblDomicilio.setHorizontalAlignment(SwingConstants.RIGHT);
								lblDomicilio.setBounds(3, 115, 89, 14);
								lblDomicilio.setFont(new Font("Tahoma", Font.BOLD, 11));
								
										JLabel lblTelefonos = new JLabel("Telefonos:");
										lblTelefonos.setHorizontalAlignment(SwingConstants.RIGHT);
										lblTelefonos.setBounds(3, 50, 89, 14);
										lblTelefonos.setFont(new Font("Tahoma", Font.BOLD, 11));
										
												JLabel lblEmail = new JLabel("E-mail:");
												lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
												lblEmail.setBounds(3, 82, 89, 14);
												lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
												
														JLabel lblObservacin = new JLabel("Observaci\u00F3n:");
														lblObservacin.setHorizontalAlignment(SwingConstants.RIGHT);
														lblObservacin.setBounds(3, 182, 89, 14);
														lblObservacin.setFont(new Font("Tahoma", Font.BOLD, 11));
														
																JLabel lblEstadoCivil = new JLabel("Estado civil:");
																lblEstadoCivil.setHorizontalAlignment(SwingConstants.RIGHT);
																lblEstadoCivil.setBounds(489, 11, 89, 25);
																lblEstadoCivil.setFont(new Font("Tahoma", Font.BOLD, 11));
																
																		dcFechaNacimiento = new JDateChooser();
																		dcFechaNacimiento.setDateFormatString("dd/MM/yyyy");
																		dcFechaNacimiento.setEnabled(false);
																		
																				dcFechaNacimiento.setBounds(108, 14, 95, 20);
																				
																						cbSexo = new JComboBox();
																						cbSexo.setEnabled(false);
																						cbSexo.setBounds(335, 14, 109, 20);
																						cbSexo.setModel(new DefaultComboBoxModel(new String[] { "SELECCIONE", "FEMENINO", "MASCULINO" }));
																						
																								cbEstadoCivil = new JComboBox();
																								cbEstadoCivil.setEnabled(false);
																								cbEstadoCivil.setBounds(586, 14, 109, 20);
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
																										txtTelefonos.setBounds(108, 48, 612, 20);
																										pnDatosPersonales.add(txtTelefonos);
																										txtTelefonos.setColumns(10);
																										
																												txtCorreo = new JTextField();
																												txtCorreo.setEnabled(false);
																												txtCorreo.setBounds(108, 79, 612, 20);
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
																														txtDomicilio.setBounds(108, 112, 612, 54);
																														pnDatosPersonales.add(txtDomicilio);
																														
																																lblEdad = new JLabel("");
																																lblEdad.setVerticalAlignment(SwingConstants.BOTTOM);
																																lblEdad.setFont(new Font("Tahoma", Font.BOLD, 11));
																																lblEdad.setBounds(209, 14, 58, 21);
																																pnDatosPersonales.add(lblEdad);
																																
																																		scrollPane_1 = new JScrollPane();
																																		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
																																		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
																																		scrollPane_1.setBounds(108, 182, 612, 94);
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

		pnDatosLaborales = new JPanel();
		tbVentanas.addTab("Datos Laborales I", null, pnDatosLaborales, null);
		pnDatosLaborales.setLayout(null);
		pnDatosLaborales.setBackground(Color.WHITE);
		
		JPanel panelCondicion = new JPanel();
		panelCondicion.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panelCondicion.setBackground(Color.WHITE);
		panelCondicion.setBounds(0, 132, 726, 94);
		pnDatosLaborales.add(panelCondicion);
		panelCondicion.setLayout(null);
		
		lblCondicion = new JLabel("Condicion:");
		lblCondicion.setBounds(3, 4, 89, 14);
		lblCondicion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCondicion.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelCondicion.add(lblCondicion);
		
		txtCodCond = new JTextField();
		txtCodCond.setBounds(108, 4, 49, 20);
		txtCodCond.setEnabled(false);
		txtCodCond.setColumns(10);
		panelCondicion.add(txtCodCond);
		
		txtCondicion = new JTextField();
		txtCondicion.setBounds(167, 4, 344, 20);
		txtCondicion.setEnabled(false);
		txtCondicion.setColumns(10);
		panelCondicion.add(txtCondicion);
		
		btnCondicion = new JButton("");
		btnCondicion.setBounds(521, 4, 25, 21);
		btnCondicion.setEnabled(false);
		btnCondicion.setIcon(
				new ImageIcon(PersonalVista.class.getResource("/com/migraciones/talentoHumano/graphics/lupa.png")));
		panelCondicion.add(btnCondicion);
		btnCondicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnCondicionActionPerformed(e);
				} catch (ClassNotFoundException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		lblVigCondicion = new JLabel("Desde:");
		lblVigCondicion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVigCondicion.setVerticalAlignment(SwingConstants.TOP);
		lblVigCondicion.setBounds(3, 34, 89, 14);
		lblVigCondicion.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelCondicion.add(lblVigCondicion);
		
		dcHastaCondicion = new JDateChooser();
		dcHastaCondicion.setBounds(300, 34, 87, 20);
		dcHastaCondicion.setEnabled(false);
		dcHastaCondicion.setDateFormatString("dd/MM/yyyy");
		panelCondicion.add(dcHastaCondicion);
		
		lblHastaCondicion = new JLabel("Hasta:");
		lblHastaCondicion.setBounds(250, 34, 57, 14);
		lblHastaCondicion.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelCondicion.add(lblHastaCondicion);
		
		dcDesdeCondicion = new JDateChooser();
		dcDesdeCondicion.setBounds(108, 34, 87, 20);
		dcDesdeCondicion.setEnabled(false);
		dcDesdeCondicion.setDateFormatString("dd/MM/yyyy");
		panelCondicion.add(dcDesdeCondicion);
		
		JLabel label = new JLabel("Obs.:", SwingConstants.RIGHT);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(3, 64, 89, 14);
		panelCondicion.add(label);
		
		txtObsCondicion = new JTextField();
		txtObsCondicion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				txtObsCondicionKeyTyped(e);
			}
		});
		txtObsCondicion.setEnabled(false);
		txtObsCondicion.setColumns(10);
		txtObsCondicion.setBounds(108, 64, 606, 20);
		panelCondicion.add(txtObsCondicion);
		
		JPanel panelDesvinculacion = new JPanel();
		panelDesvinculacion.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panelDesvinculacion.setBackground(Color.WHITE);
		panelDesvinculacion.setBounds(0, 70, 726, 64);
		pnDatosLaborales.add(panelDesvinculacion);
		panelDesvinculacion.setLayout(null);
		
		JLabel lblfechaDeDesvinculacion = new JLabel("<html><body>Fecha de<br> Desvinculacion:</body></html>");
		lblfechaDeDesvinculacion.setBounds(3, 2, 89, 28);
		lblfechaDeDesvinculacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblfechaDeDesvinculacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelDesvinculacion.add(lblfechaDeDesvinculacion);
		
		dcDesvinculacion = new JDateChooser();
		dcDesvinculacion.setBounds(108, 4, 87, 20);
		dcDesvinculacion.setEnabled(false);
		dcDesvinculacion.setDateFormatString("dd/MM/yyyy");
		panelDesvinculacion.add(dcDesvinculacion);
		
		JLabel label_4 = new JLabel("Obs.:");
		label_4.setBounds(3, 42, 89, 14);
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelDesvinculacion.add(label_4);
		
		txtObsDesvinculacion = new JTextField();
		txtObsDesvinculacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				txtObsDesvinculacionKeyTyped(e);
			}
		});
		txtObsDesvinculacion.setBounds(108, 34, 607, 20);
		txtObsDesvinculacion.setEnabled(false);
		txtObsDesvinculacion.setColumns(10);
		panelDesvinculacion.add(txtObsDesvinculacion);
		panelCondicion.add(btnCondicion);
		
		JPanel panelIngreso = new JPanel();
		panelIngreso.setBackground(Color.WHITE);
		panelIngreso.setForeground(Color.WHITE);
		panelIngreso.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panelIngreso.setBounds(0, 0, 726, 71);
		pnDatosLaborales.add(panelIngreso);
		panelIngreso.setLayout(null);
		
		txtObsIngreso = new JTextField();
		txtObsIngreso.setBounds(108, 34, 605, 20);
		panelIngreso.add(txtObsIngreso);
		txtObsIngreso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				txtObsIngresoKeyTyped(arg0);
			}
		});
		txtObsIngreso.setEnabled(false);
		txtObsIngreso.setColumns(10);
		
		lblObsIngreso = new JLabel("Obs.:");
		lblObsIngreso.setBounds(3, 38, 89, 14);
		panelIngreso.add(lblObsIngreso);
		lblObsIngreso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObsIngreso.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblFechaIngreso = new JLabel("<html><body>Fecha de<br> Ingreso:</body></html>");
		lblFechaIngreso.setBounds(3, 2, 89, 28);
		panelIngreso.add(lblFechaIngreso);
		lblFechaIngreso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaIngreso.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		dcFechaIngreso = new JDateChooser();
		dcFechaIngreso.setBounds(108, 4, 87, 20);
		panelIngreso.add(dcFechaIngreso);
		dcFechaIngreso.setEnabled(false);
		dcFechaIngreso.setDateFormatString("dd/MM/yyyy");
		
		JPanel panelCategoria = new JPanel();
		panelCategoria.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panelCategoria.setBackground(Color.WHITE);
		panelCategoria.setBounds(0, 225, 726, 71);
		pnDatosLaborales.add(panelCategoria);
		panelCategoria.setLayout(null);
		
		txtCodCategoria = new JTextField();
		txtCodCategoria.setBounds(108, 4, 49, 20);
		panelCategoria.add(txtCodCategoria);
		txtCodCategoria.setEnabled(false);
		txtCodCategoria.setColumns(10);
		
		lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(3, 4, 89, 14);
		panelCategoria.add(lblCategoria);
		lblCategoria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel label_3 = new JLabel("Obs:");
		label_3.setBounds(3, 34, 89, 14);
		panelCategoria.add(label_3);
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtObsCategoria = new JTextField();
		txtObsCategoria.setBounds(108, 34, 597, 20);
		panelCategoria.add(txtObsCategoria);
		txtObsCategoria.setEnabled(false);
		txtObsCategoria.setColumns(10);
		
		txtCategoria = new JTextField();
		txtCategoria.setBounds(167, 4, 344, 20);
		panelCategoria.add(txtCategoria);
		txtCategoria.setEnabled(false);
		txtCategoria.setColumns(10);
		
		btnCategoria = new JButton("");
		btnCategoria.setBounds(521, 4, 25, 21);
		panelCategoria.add(btnCategoria);
		btnCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnCategoriaActionPerformed(arg0);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCategoria.setEnabled(false);
		btnCategoria.setIcon(
				new ImageIcon(PersonalVista.class.getResource("/com/migraciones/talentoHumano/graphics/lupa.png")));
		
		lblVigCategoria = new JLabel("Vigencia:");
		lblVigCategoria.setBounds(564, 4, 50, 14);
		panelCategoria.add(lblVigCategoria);
		lblVigCategoria.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		dcVigCat = new JDateChooser();
		dcVigCat.setBounds(628, 4, 87, 20);
		panelCategoria.add(dcVigCat);
		dcVigCat.setEnabled(false);
		dcVigCat.setDateFormatString("dd/MM/yyyy");
		
				JPanel pnDatosLaboralesII = new JPanel();
				pnDatosLaboralesII.setLayout(null);
				pnDatosLaboralesII.setBackground(Color.WHITE);
				tbVentanas.addTab("Datos Laborales II", null, pnDatosLaboralesII, null);
				
				txtObsDependencia = new JTextField();
				txtObsDependencia.setEnabled(false);
				txtObsDependencia.setColumns(10);
				txtObsDependencia.setBounds(108, 64, 607, 20);
				pnDatosLaboralesII.add(txtObsDependencia);
				
				panelDependencia = new JPanel();
				panelDependencia.setBorder(new LineBorder(Color.LIGHT_GRAY));
				panelDependencia.setBackground(Color.WHITE);
				panelDependencia.setBounds(0, 0, 726, 98);
				pnDatosLaboralesII.add(panelDependencia);
				panelDependencia.setLayout(null);
				
				lblDependencia = new JLabel("Dependencia:");
				lblDependencia.setBounds(3, 4, 89, 14);
				panelDependencia.add(lblDependencia);
				lblDependencia.setHorizontalAlignment(SwingConstants.RIGHT);
				lblDependencia.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				txtCodDep = new JTextField();
				txtCodDep.setBounds(108, 4, 49, 20);
				panelDependencia.add(txtCodDep);
				txtCodDep.setEnabled(false);
				txtCodDep.setColumns(10);
				
				txtDependencia = new JTextField();
				txtDependencia.setBounds(167, 4, 344, 20);
				panelDependencia.add(txtDependencia);
				txtDependencia.setEnabled(false);
				txtDependencia.setColumns(10);
				
				btnDependencia = new JButton("");
				btnDependencia.setBounds(521, 4, 25, 21);
				panelDependencia.add(btnDependencia);
				btnDependencia.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							btnDependenciaActionPerformed(arg0);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				btnDependencia.setEnabled(false);
				btnDependencia.setIcon(
						new ImageIcon(PersonalVista.class.getResource("/com/migraciones/talentoHumano/graphics/lupa.png")));
				
				lblVigDependencia = new JLabel("Vigencia:");
				lblVigDependencia.setBounds(564, 4, 60, 14);
				panelDependencia.add(lblVigDependencia);
				lblVigDependencia.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				dcVigDependencia = new JDateChooser();
				dcVigDependencia.setBounds(628, 4, 87, 20);
				panelDependencia.add(dcVigDependencia);
				dcVigDependencia.setEnabled(false);
				dcVigDependencia.setDateFormatString("dd/MM/yyyy");
				
				lblOficina = new JLabel("Oficina:");
				lblOficina.setBounds(3, 34, 89, 14);
				panelDependencia.add(lblOficina);
				lblOficina.setHorizontalAlignment(SwingConstants.RIGHT);
				lblOficina.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				txtCodOfi = new JTextField();
				txtCodOfi.setBounds(108, 34, 49, 20);
				panelDependencia.add(txtCodOfi);
				txtCodOfi.setEnabled(false);
				txtCodOfi.setColumns(10);
				
				txtOficina = new JTextField();
				txtOficina.setBounds(167, 34, 344, 20);
				panelDependencia.add(txtOficina);
				txtOficina.setEnabled(false);
				txtOficina.setColumns(10);
				
				JLabel label_6 = new JLabel("Obs.:");
				label_6.setBounds(3, 64, 89, 14);
				panelDependencia.add(label_6);
				label_6.setHorizontalAlignment(SwingConstants.RIGHT);
				label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				panelCredencial = new JPanel();
				panelCredencial.setBorder(new LineBorder(Color.LIGHT_GRAY));
				panelCredencial.setBackground(Color.WHITE);
				panelCredencial.setBounds(0, 97, 726, 68);
				pnDatosLaboralesII.add(panelCredencial);
				panelCredencial.setLayout(null);
				
						lblObs = new JLabel("Obs:");
						lblObs.setBounds(3, 34, 89, 14);
						panelCredencial.add(lblObs);
						lblObs.setHorizontalAlignment(SwingConstants.RIGHT);
						lblObs.setFont(new Font("Tahoma", Font.BOLD, 11));
						
								lblCargo = new JLabel("Credencial:");
								lblCargo.setBounds(3, 4, 89, 14);
								panelCredencial.add(lblCargo);
								lblCargo.setHorizontalAlignment(SwingConstants.RIGHT);
								lblCargo.setFont(new Font("Tahoma", Font.BOLD, 11));
								
										txtCodCargo = new JTextField();
										txtCodCargo.setBounds(108, 4, 49, 20);
										panelCredencial.add(txtCodCargo);
										txtCodCargo.setEnabled(false);
										txtCodCargo.setColumns(10);
										
												txtObsCargo = new JTextField();
												txtObsCargo.setBounds(108, 34, 607, 20);
												panelCredencial.add(txtObsCargo);
												txtObsCargo.addKeyListener(new KeyAdapter() {
													@Override
													public void keyTyped(KeyEvent e) {
														txtObsCargoKeyTyped(e);
													}
												});
												txtObsCargo.setEnabled(false);
												txtObsCargo.setColumns(10);
												
														txtCargo = new JTextField();
														txtCargo.setBounds(167, 4, 344, 20);
														panelCredencial.add(txtCargo);
														txtCargo.setEnabled(false);
														txtCargo.setColumns(10);
														
																btnCargo = new JButton("");
																btnCargo.setBounds(521, 4, 25, 21);
																panelCredencial.add(btnCargo);
																btnCargo.setEnabled(false);
																btnCargo.addActionListener(new ActionListener() {
																	public void actionPerformed(ActionEvent e) {
																		try {
																			btnCargoActionPerformed(e);
																		} catch (ClassNotFoundException e1) {
																			// TODO Auto-generated catch block
																			e1.printStackTrace();
																		}
																	}
																});
																btnCargo.setIcon(
																		new ImageIcon(PersonalVista.class.getResource("/com/migraciones/talentoHumano/graphics/lupa.png")));
																
																		lblVigCargo = new JLabel("Vigencia:");
																		lblVigCargo.setBounds(564, 4, 60, 14);
																		panelCredencial.add(lblVigCargo);
																		lblVigCargo.setFont(new Font("Tahoma", Font.BOLD, 11));
																		
																				dcVigCargo = new JDateChooser();
																				dcVigCargo.setBounds(628, 4, 87, 20);
																				panelCredencial.add(dcVigCargo);
																				dcVigCargo.setEnabled(false);
																				dcVigCargo.setDateFormatString("dd/MM/yyyy");
																				
																				panelHorario = new JPanel();
																				panelHorario.setBorder(new LineBorder(Color.LIGHT_GRAY));
																				panelHorario.setBackground(Color.WHITE);
																				panelHorario.setBounds(0, 164, 726, 131);
																				pnDatosLaboralesII.add(panelHorario);
																				panelHorario.setLayout(null);
																				
																						lblHorario = new JLabel("Horario:");
																						lblHorario.setBounds(3, 4, 89, 14);
																						panelHorario.add(lblHorario);
																						lblHorario.setHorizontalAlignment(SwingConstants.RIGHT);
																						lblHorario.setFont(new Font("Tahoma", Font.BOLD, 11));
																						
																								txtCodHor = new JTextField();
																								txtCodHor.setBounds(108, 4, 49, 20);
																								panelHorario.add(txtCodHor);
																								txtCodHor.setEnabled(false);
																								txtCodHor.setColumns(10);
																								
																										label_14 = new JLabel("Entrada:");
																										label_14.setBounds(153, 4, 80, 14);
																										panelHorario.add(label_14);
																										label_14.setHorizontalAlignment(SwingConstants.RIGHT);
																										label_14.setFont(new Font("Tahoma", Font.BOLD, 11));
																										
																												txtEntrada = new JTextField();
																												txtEntrada.setBounds(239, 4, 100, 20);
																												panelHorario.add(txtEntrada);
																												txtEntrada.setEnabled(false);
																												txtEntrada.setColumns(10);
																												
																														label_15 = new JLabel("Salida:");
																														label_15.setBounds(319, 4, 80, 14);
																														panelHorario.add(label_15);
																														label_15.setHorizontalAlignment(SwingConstants.RIGHT);
																														label_15.setFont(new Font("Tahoma", Font.BOLD, 11));
																														
																																txtSalida = new JTextField();
																																txtSalida.setBounds(409, 4, 100, 20);
																																panelHorario.add(txtSalida);
																																txtSalida.setEnabled(false);
																																txtSalida.setColumns(10);
																																
																																		btnHorario = new JButton("");
																																		btnHorario.setBounds(521, 4, 25, 21);
																																		panelHorario.add(btnHorario);
																																		btnHorario.setEnabled(false);
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
																																				new ImageIcon(PersonalVista.class.getResource("/com/migraciones/talentoHumano/graphics/lupa.png")));
																																		
																																				lblVigHorario = new JLabel("Vigencia:");
																																				lblVigHorario.setBounds(564, 4, 61, 14);
																																				panelHorario.add(lblVigHorario);
																																				lblVigHorario.setFont(new Font("Tahoma", Font.BOLD, 11));
																																				
																																						dcVigHorario = new JDateChooser();
																																						dcVigHorario.setBounds(628, 4, 87, 20);
																																						panelHorario.add(dcVigHorario);
																																						dcVigHorario.setEnabled(false);
																																						dcVigHorario.setDateFormatString("dd/MM/yyyy");
																																						
																																								label_13 = new JLabel("Obs.:");
																																								label_13.setBounds(3, 34, 89, 14);
																																								panelHorario.add(label_13);
																																								label_13.setHorizontalAlignment(SwingConstants.RIGHT);
																																								label_13.setFont(new Font("Tahoma", Font.BOLD, 11));
																																								
																																								lblDia = new JLabel("D\u00EDas:");
																																								lblDia.setBounds(3, 62, 89, 14);
																																								panelHorario.add(lblDia);
																																								lblDia.setHorizontalAlignment(SwingConstants.RIGHT);
																																								lblDia.setFont(new Font("Tahoma", Font.BOLD, 11));
																																								
																																								cbDia = new JComboBox();
																																								cbDia.setBounds(108, 64, 107, 20);
																																								panelHorario.add(cbDia);
																																								cbDia.setEnabled(false);
																																								cbDia.setModel(new DefaultComboBoxModel(new String[] {"SELECCIONE", "HABILES", "TODOS"}));
																																								
																																								dcVigDia = new JDateChooser();
																																								dcVigDia.setBounds(628, 64, 87, 20);
																																								panelHorario.add(dcVigDia);
																																								dcVigDia.setEnabled(false);
																																								dcVigDia.setDateFormatString("dd/MM/yyyy");
																																								
																																								label_1 = new JLabel("Obs.:");
																																								label_1.setBounds(3, 95, 89, 14);
																																								panelHorario.add(label_1);
																																								label_1.setHorizontalAlignment(SwingConstants.RIGHT);
																																								label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
																																								
																																								txtObsDia = new JTextField();
																																								txtObsDia.setBounds(108, 94, 607, 20);
																																								panelHorario.add(txtObsDia);
																																								txtObsDia.setEnabled(false);
																																								txtObsDia.setColumns(10);
																																								
																																								lblVigDias = new JLabel("Vigencia:");
																																								lblVigDias.setBounds(564, 64, 61, 14);
																																								panelHorario.add(lblVigDias);
																																								lblVigDias.setFont(new Font("Tahoma", Font.BOLD, 11));
																																								
																																										txtObsHorario = new JTextField();
																																										txtObsHorario.setBounds(108, 34, 607, 20);
																																										panelHorario.add(txtObsHorario);
																																										txtObsHorario.addKeyListener(new KeyAdapter() {
																																											@Override
																																											public void keyTyped(KeyEvent e) {
																																												txtObsHorarioKeyTyped(e);
																																											}
																																										});
																																										txtObsHorario.setEnabled(false);
																																										txtObsHorario.setColumns(10);

		pnEvaluaciones = new JPanel();
		pnEvaluaciones.setBackground(Color.WHITE);
		tbVentanas.addTab("Evaluaciones", null, pnEvaluaciones, null);
		pnEvaluaciones.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 726, 256);
		pnEvaluaciones.add(scrollPane);

		tbEvaluaciones = new JTable();
		scrollPane.setViewportView(tbEvaluaciones);

		formatearTablaEvaluaciones();
		panelDatos.cargarDatos();

		// Formato Historial Categoria
		pnHistorialCategoria = new JPanel();
		pnHistorialCategoria.setBackground(Color.WHITE);
		tbVentanas.addTab("Historial Categoria", null, pnHistorialCategoria, null);
		pnHistorialCategoria.setLayout(null);
		tbCategoria = new JTable();
		tbCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2){
					JOptionPane.showMessageDialog(null, tbCategoria.getValueAt(tbCategoria.getSelectedRow(), 4).toString());
				}
			}
		});
		
		
		JScrollPane scrollPaneCat = new JScrollPane();
		scrollPaneCat.setBounds(0, 0, 726, 256);
		pnHistorialCategoria.add(scrollPaneCat);
		scrollPaneCat.setViewportView(tbCategoria);
		formatearTablaHistorial(tbCategoria, "tbCategoria");

		// Formato Historial Dependencia
		pnHistorialDependencia = new JPanel();
		pnHistorialDependencia.setBackground(Color.WHITE);
		tbVentanas.addTab("Historla Dependencia", null, pnHistorialDependencia, null);
		pnHistorialDependencia.setLayout(null);
		tbDependencia = new JTable();
		tbDependencia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2){
					JOptionPane.showMessageDialog(null, tbDependencia.getValueAt(tbDependencia.getSelectedRow(), 4).toString());
				}
			}
		});
		JScrollPane scrollPaneDep = new JScrollPane();
		scrollPaneDep.setBounds(0, 0, 726, 256);
		pnHistorialDependencia.add(scrollPaneDep);
		scrollPaneDep.setViewportView(tbDependencia);
		formatearTablaHistorial(tbDependencia, "tbDependencia");

		// Formato Historial Oficina
		pnHistorialOficina = new JPanel();
		pnHistorialOficina.setBackground(Color.WHITE);
		tbVentanas.addTab("Historial Oficina", null, pnHistorialOficina, null);
		pnHistorialOficina.setLayout(null);
		tbOficina = new JTable();
		JScrollPane scrollPaneOfi = new JScrollPane();
		scrollPaneOfi.setBounds(0, 0, 726, 256);
		pnHistorialOficina.add(scrollPaneOfi);
		scrollPaneOfi.setViewportView(tbOficina);
		formatearTablaHistorial(tbOficina, "tbOficina");

		// FORMATO HISTORIAL TIPO
		pnHistorialTipo = new JPanel();
		pnHistorialTipo.setBackground(Color.WHITE);
		tbVentanas.addTab("Historial Tipo", null, pnHistorialTipo, null);
		pnHistorialTipo.setLayout(null);
		tbTipos = new JTable();
		JScrollPane scrollPaneTipo = new JScrollPane();
		scrollPaneTipo.setBounds(0, 0, 726, 256);
		pnHistorialTipo.add(scrollPaneTipo);
		scrollPaneTipo.setViewportView(tbTipos);
		formatearTablaHistorial(tbTipos, "tbTipos");

		// FORMATO HISTORIAL TURNOS
		pnHistorialTurnos = new JPanel();
		pnHistorialTurnos.setBackground(Color.WHITE);
		tbVentanas.addTab("Historial Turnos", null, pnHistorialTurnos, null);
		pnHistorialTurnos.setLayout(null);
		tbTurnos = new JTable();
		JScrollPane scrollPaneTurnos = new JScrollPane();
		scrollPaneTurnos.setBounds(0, 0, 726, 256);
		pnHistorialTurnos.add(scrollPaneTurnos);
		scrollPaneTurnos.setViewportView(tbTurnos);
		formatearTablaHistorial(tbTurnos, "tbTurnos");

		// FORMATO HISTORIAL DIAS
		pnHistorialDias = new JPanel();
		pnHistorialDias.setBackground(Color.WHITE);
		tbVentanas.addTab("Historial Días", null, pnHistorialDias, null);
		pnHistorialDias.setLayout(null);
		tbDias = new JTable();
		JScrollPane scrollPaneDias = new JScrollPane();
		scrollPaneDias.setBounds(0, 0, 726, 256);
		pnHistorialDias.add(scrollPaneDias);
		scrollPaneDias.setViewportView(tbDias);
		formatearTablaHistorial(tbDias, "tbDias");

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

	protected void btnEstadoActionPerformed(ActionEvent arg0) throws ClassNotFoundException {
		this.OPCION = 3;
		habilitarDesvinculacion();
		inhabilitarCampos();
		obtenerCondicionID();
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

//	protected void btnOficinaActionPerformed(ActionEvent e) throws ClassNotFoundException {
//		OficinaModal vista = new OficinaModal();
//		vista.setVisible(true);
//		obtenerOficina(vista);
//	}

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

	protected void btnCondicionActionPerformed(ActionEvent arg0) throws ClassNotFoundException, ParseException {
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

	// -----------Boton Categotia
	protected void btnCategoriaActionPerformed(ActionEvent e) throws ClassNotFoundException {
		CategoriaModal vista = new CategoriaModal();
		vista.setVisible(true);
		obtenerCategoria(vista);
	}

	protected void txtObsCondicionKeyTyped(KeyEvent arg0) {
		convertirMayusculas(arg0);
	}
	
	protected void txtObsIngresoKeyTyped(KeyEvent arg0) {
		convertirMayusculas(arg0);
	}
	protected void txtObsCargoKeyTyped(KeyEvent e) {
		convertirMayusculas(e);		
	}
	protected void txtObsHorarioKeyTyped(KeyEvent e){
		convertirMayusculas(e);
	}
	protected void txtObsDesvinculacionKeyTyped(KeyEvent e){
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
		}else if (this.OPCION == 3){
			try{
				guardarDesvinculacion();
			}catch (IOException e){
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
		boolean ctrlCond = false, ctrlDep = false, ctrlCar = false, ctrlHor = false, ctrlCat = false, ctrlIngreso = false, ctrlDia=false;
		String opCond = "", opDep = "", opCar = "", opHor = "", opCat = "", opIngreso = "", opDia = "";
		
		// ######## DATOS LABORALES ########
		// guardar datos de la condicion del personal
		cargarNuevaCondicion();
		if (modificoCondicion()) {
				ctrlCond = true;
				opCond = "I";
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
		
		
		// guardar datos de la categoria del personal
		cargarNuevaCategoria();
		if (modificoCategoria()) {
			if (verificarPeriodoCategoria() == true) {
				ctrlCat = true;
				opCat = "I";

			}
		} else {
			// modificacion de la observacion
			ctrlCat = true;
			opCat = "A";
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
		
		// guardar datos de la fecha de ingreso del personal
		cargarNuevoIngresoPersonal();
		if (verificarFechaIngreso() == true){
			if (verificarPeriodoInicioFin() == true){
				ctrlIngreso = true;
				opIngreso = "I";
			}
		} else {
			// modificacion de la observacion
			ctrlIngreso = true;
			opIngreso = "A";
		}
		
		// guardar datos de los dias de trabajo del personal
		cargarNuevoDia();
		if (modificoDia()) {
				ctrlDia = true;
				opDia = "I";
		} else {
			// modificacion de la observacion
			ctrlDia = true;
			opDia = "A";
		}
	
		// verificar las actualizaciones y desplegar mensaje
		if (ctrlCond && ctrlDep && ctrlCar && ctrlHor && ctrlCat && ctrlIngreso 
				&& verificarCamposObligatorios() && ctrlDia) {
			this.sistema.actualizarCondicionPersonal(this.perCon, opCond);
			this.sistema.actualizarDependenciaPersonal(this.perDep, opDep);
			this.sistema.actualizarCargoPersonal(this.perCar, opCar);
			this.sistema.actualizarTurnoPersonal(this.perTur, opHor);
			this.sistema.actualizarCategoriaPersonal(this.perCat, opCat);
			this.sistema.actualizarIngresoPersonal(this.perFechaIngreso, opIngreso);
			this.sistema.actualizarPersonalDias(this.perDias, opDia);
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
			try {
				//Verifica las fechas de ingreso y de condicion
				if (verificarFechaIngreso()){
					if (verificarPeriodoInicioFin()){
						Personal per = cargarNuevoPersonal();
						if (this.sistema.agregarNuevoPersonal(per)){
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
						}else{
							JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_GUARDADOS_FAIL + "Transacción no válida",
									"ATENCION", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_GUARDADOS_FAIL + "Verificar Fecha inicio y Fin de la condición",
								"ATENCION", JOptionPane.ERROR_MESSAGE);
					}//fin verificar fecha inicio y fin
				}else{
					//MENSA DE ERROR EN CASO DE QUE LA FECHA DE INGRESO Y LA DE CONDICION NO CUMPLAN CON EL REQUISITO
					JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_GUARDADOS_FAIL + "Verifique Fecha de ingreso y Fecha condición",
							"ATENCION", JOptionPane.ERROR_MESSAGE);
				}
			}catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, GlobalUtil.MSG_DATOS_GUARDADOS_FAIL + "Verifique Campos obligatorios",
					"ATENCION", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void guardarDesvinculacion() throws ParseException, ClassNotFoundException, SQLException, IOException {
		// verificar las actualizaciones y desplegar mensaje
		PerFechaDesvinculacion desvinculacion = cargarNuevosDatosDesvinculacion();
		if (this.sistema.actualizarDatosDesvinculacion(desvinculacion)){
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

	private Personal cargarNuevoPersonal() {
		Personal per = cargarNuevosDatosPersonales();
		per.setNombres(txtNombres.getText().trim());
		per.setApellidos(txtApellidos.getText().trim());
		per.setAdministradorAlta(MenuPrincipalVista.administrador.getLogin());

		// ######### FECHA INGRESO ########
		per.setPerFechaIngreso(dcFechaIngreso.getDate());
		per.setPerFechaIngObs(txtObsIngreso.getText());
		per.setPerFechaIngCondicion(varCondicion);
		
		// ######### CONDICION ##########
		per.setCodCondicion(Integer.parseInt(txtCodCond.getText()));
		per.setCondFechaIni(dcDesdeCondicion.getDate());
		per.setCondFechaFin(dcHastaCondicion.getDate());
		per.setCondObservacion(txtObsCondicion.getText().trim());

		// ########### DEPENDENCIA ###########
		per.setCodDependencia(txtCodDep.getText());
		per.setDepFechaIni(dcVigDependencia.getDate());
		per.setDepObservacion(txtObsDependencia.getText().trim());

		// ########### OFICINA ###########
		per.setCodOficina(txtCodOfi.getText());
		//Toma la fecha y la Observacion de la Dependencia
		per.setOfiFechaIni(dcVigDependencia.getDate());
		per.setOfiObservacion(txtObsDependencia.getText().trim());
		
		// ########### CARGO ###########
		per.setCodCargo(Integer.parseInt(txtCodCargo.getText()));
		per.setCarFechaIni(dcVigCargo.getDate());
		per.setCarObservacion(txtObsCargo.getText().trim());

		// ########### CATEGORIA ###########
		per.setCategoriaCodigo(txtCodCategoria.getText());
		per.setCatFechaInicio(dcVigCat.getDate());
		per.setCatObservacion(txtObsCategoria.getText().trim());

		// ########### HORARIO ###########
		per.setCodHorario(Integer.parseInt(txtCodHor.getText()));
		per.setHorFechaIni(dcVigHorario.getDate());
		per.setHorObservacion(txtObsHorario.getText().trim());
		
		//############ DIAS ##############
		per.setDias(cbDia.getSelectedItem().toString().substring(0,3));
		per.setDiaFechaInicio(dcVigDia.getDate());
		per.setDiaObservacion(txtObsDia.getText());
		
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
		if (dcDesdeCondicion.getDate() == null) {
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
		
		if (txtCodCategoria.getText().equals("")) {
			control = false;
		}
		if (dcVigCat.getDate() == null) {
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
		
		if (cbSexo.getSelectedItem().equals("") || cbSexo.getSelectedItem().equals("SELECCIONE")){
			control = false;
		}
		if (dcFechaIngreso.getDate() == null) {
			control = false;
		}
		if(cbDia.getSelectedItem().equals("") || cbDia.getSelectedItem().equals("SELECCIONE")){
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
		
		lblFechaIngreso.setText("<html><body>*Fecha de<br> Ingreso:</body></html>");
		lblFechaIngreso.setForeground(Color.RED);
		lblCondicion.setText("*Condicion:");
		lblCondicion.setForeground(Color.RED);
		lblVigCondicion.setText("*Desde:");
		lblVigCondicion.setForeground(Color.RED);
		lblHastaCondicion.setText("*Hasta:");
		lblHastaCondicion.setForeground(Color.RED);

		lblDependencia.setText("*Dependencia:");
		lblDependencia.setForeground(Color.RED);
		lblVigDependencia.setText("*Vigencia:");
		lblVigDependencia.setForeground(Color.RED);

		lblOficina.setText("*Oficina:");
		lblOficina.setForeground(Color.RED);
		
		lblCargo.setText("*Cargo:");
		lblCargo.setForeground(Color.RED);
		lblVigCargo.setText("*Vigencia:");
		lblVigCargo.setForeground(Color.RED);

		lblCategoria.setText("*Categoria:");
		lblCategoria.setForeground(Color.RED);
		lblVigCategoria.setText("*Vigencia:");
		lblVigCategoria.setForeground(Color.RED);

		lblHorario.setText("*Horario:");
		lblHorario.setForeground(Color.RED);
		lblVigHorario.setText("*Vigencia:");
		lblVigHorario.setForeground(Color.RED);

		lblFechaNacimento.setText("<html><body>*Fecha de<br> Nacimento:</body></html>");
		lblFechaNacimento.setForeground(Color.RED);
		
		lblSexo.setText("*Sexo:");
		lblSexo.setForeground(Color.RED);
		
		lblDia.setText("*Días:");
		lblDia.setForeground(Color.RED);
		lblVigDias.setText("*Vigencia:");
		lblVigDias.setForeground(Color.RED);
	}

	private void desmarcarCamposObligatorios() {
		lblCedula.setText("Cedula:");
		lblCedula.setForeground(Color.BLACK);
		lblNombres.setText("Nombres:");
		lblNombres.setForeground(Color.BLACK);
		lblApellidos.setText("Apellidos:");
		lblApellidos.setForeground(Color.BLACK);
		lblFotoCarnet.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		
		lblFechaIngreso.setText("<html><body>Fecha de<br> Ingreso:</body></html>");
		lblFechaIngreso.setForeground(Color.BLACK);
		lblCondicion.setText("Condicion:");
		lblCondicion.setForeground(Color.BLACK);
		lblVigCondicion.setText("Desde:");
		lblVigCondicion.setForeground(Color.BLACK);
		lblHastaCondicion.setText("Hasta:");
		lblHastaCondicion.setForeground(Color.BLACK);

		lblDependencia.setText("Dependencia:");
		lblDependencia.setForeground(Color.BLACK);
		lblVigDependencia.setText("Vigencia:");
		lblVigDependencia.setForeground(Color.BLACK);

		lblOficina.setText("Oficina:");
		lblOficina.setForeground(Color.BLACK);
		
		lblCargo.setText("Oficina:");
		lblCargo.setForeground(Color.BLACK);
		lblVigCargo.setText("Vigencia:");
		lblVigCargo.setForeground(Color.BLACK);

		lblCategoria.setText("Categoria:");
		lblCategoria.setForeground(Color.BLACK);
		lblVigCategoria.setText("Vigencia:");
		lblVigCategoria.setForeground(Color.BLACK);

		lblHorario.setText("Horario:");
		lblHorario.setForeground(Color.BLACK);
		lblVigHorario.setText("Vigencia:");
		lblVigHorario.setForeground(Color.BLACK);

		lblFechaNacimento.setText("<html><body>Fecha de<br> Nacimento:</body></html>");
		lblFechaNacimento.setForeground(Color.BLACK);
		
		lblSexo.setText("Sexo:");
		lblSexo.setForeground(Color.BLACK);
		
		lblDia.setText("Días:");
		lblDia.setForeground(Color.BLACK);
		lblVigDias.setText("Vigencia");
		lblVigDias.setForeground(Color.BLACK);
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
		// ########### HISTORIAL DEPENDENCIA #################
		cargarHistorialDependencia();
		// ########### HISTORIAL OFICINA #################
		cargarHistorialOficina();
		// ########### HISTORIAL TIPO PERSONAL #################
		cargarHistorialTipoPersonal();
		// ########### HISTORIAL TURNO #################
		cargarHistorialTurno();
		// ########### HISTORIAL CATEGORIA #################
		cargarHistorialCategoria();
		// ########### HISTORIAL CATEGORIA #################
		cargarHistorialDias();
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
		dcDesdeCondicion.setEnabled(true);
		dcVigDependencia.setEnabled(true);
		dcVigCargo.setEnabled(true);
		dcVigCat.setEnabled(true);
		dcVigHorario.setEnabled(true);
		dcFechaIngreso.setEnabled(true);
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
		// ########### HISTORIAL CATEGORIA ###########
		inicializarCategorias();
		// ########### HISTORIAL DEPENDENCIA ###########
		inicializarDependencias();
		// ########### HISTORIAL OFICINA ###########
		inicializarOficinas();
		// ########### HISTORIAL TIPO ###########
		inicializarTipos();
		// ########### HISTORIAL TURNOS ###########
		inicializarTurnos();
		// ########### HISTORIAL DIAS ###########
		inicializarDias();
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
		dcDesdeCondicion.setDate(this.personal.getCondFechaIni());
		dcHastaCondicion.setDate(this.personal.getCondFechaFin());

		// ########### DEPENDENCIA ###########
		txtCodDep.setText(this.personal.getCodDependencia());
		txtDependencia.setText(this.personal.getDependencia());
		txtObsDependencia.setText(this.personal.getDepObservacion());
		dcVigDependencia.setDate(this.personal.getDepFechaIni());

		// ########### OFICINA ###########
		txtCodOfi.setText(this.personal.getCodOficina());
		txtOficina.setText(this.personal.getOficina());

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

		// ########### CATEGORIA ###########
		txtCodCategoria.setText(this.personal.getCategoriaCodigo());
		txtCategoria.setText(this.personal.getCategoria());
		txtObsCategoria.setText(this.personal.getCatObservacion());
		dcVigCat.setDate(this.personal.getCatFechaInicio());
		
		// ########### FECHA INGRESO ###########
		dcFechaIngreso.setDate(this.personal.getPerFechaIngreso());
		txtObsIngreso.setText(this.personal.getPerFechaIngObs());
		
		// ########### FECHA DESVINCULACION ###########
		dcDesvinculacion.setDate(this.personal.getPerFechaDesvinculacion());
		txtObsDesvinculacion.setText(this.personal.getPerFechaDesvObs());
		
		//########### DIAS DE TRABAJO ################
		if (this.personal.getDias().equals("HAB")){
			cbDia.setSelectedItem("HABILES");
		}else{
			cbDia.setSelectedItem("TODOS");
		}
		dcVigDia.setDate(this.personal.getDiaFechaInicio());
		txtObsDia.setText(this.personal.getDiaObservacion());
	}

	private void habilitarDatosLaborales() {
		//habilita fecha de ingreso
		dcFechaIngreso.setEnabled(true);
		txtObsIngreso.setEnabled(true);
		
		// habilitacion de Condicion
		btnCondicion.setEnabled(true);
		txtObsCondicion.setEnabled(true);

		// habilitacion de dependencia
		btnDependencia.setEnabled(true);
		txtObsDependencia.setEnabled(true);

		// habilitacion de Cargos
		btnCargo.setEnabled(true);
		txtObsCargo.setEnabled(true);

		// habilitacion de horario
		btnHorario.setEnabled(true);
		txtObsHorario.setEnabled(true);

		// habilitacion de categoria
		btnCategoria.setEnabled(true);
		txtObsCategoria.setEnabled(true);
		
		//habilitacion de dias de trabajo
		cbDia.setEnabled(true);
		txtObsDia.setEnabled(true);
		dcVigDia.setEnabled(true);
	}
	
	private void inhabilitarCampos(){
		//####### Cabecera ###################
		txtCedula.setEnabled(false);
		txtNombres.setEnabled(false);
		txtApellidos.setEnabled(false);
		btnDirectorio.setEnabled(false);
		btnActualizarEstado.setEnabled(false);
		btnCredencial.setEnabled(false);
		//######## Datos personales ##############
		dcFechaNacimiento.setEnabled(false);
		cbSexo.setEnabled(false);
		cbEstadoCivil.setEnabled(false);
		txtTelefonos.setEnabled(false);
		txtCorreo.setEnabled(false);
		txtDomicilio.setEnabled(false);
		txtObservacion.setEnabled(false);
		dcFechaNacimiento.setEnabled(false);
		
		// ########### FECHA DE INGRESO ###########
		dcFechaIngreso.setEnabled(false);
		txtObsIngreso.setEnabled(false);
		// ########### CONDICION ###########
		dcDesdeCondicion.setEnabled(false);
		dcHastaCondicion.setEnabled(false);
		txtObsCondicion.setEnabled(false);
		btnCondicion.setEnabled(false);
		// ########### DEPENDENCIA ###########
		dcVigDependencia.setEnabled(false);
		txtObsDependencia.setEnabled(false);
		btnDependencia.setEnabled(false);
		// ########### CARGO ###########
		dcVigCargo.setEnabled(false);
		txtObsCargo.setEnabled(false);
		btnCargo.setEnabled(false);
		// ########### HORARIO ###########
		dcVigHorario.setEnabled(false);
		txtCodHor.setEnabled(false);
		txtObsHorario.setEnabled(false);
		btnHorario.setEnabled(false);
		// ########### CATEGORIA ###########
		dcVigCat.setEnabled(false);
		txtObsCategoria.setEnabled(false);
		btnCategoria.setEnabled(false);
		// ########### DIAS ###########
		cbDia.setEnabled(false);
		cbDia.setSelectedItem("SELECCIONE");
		txtObsDia.setEnabled(false);
		dcVigDia.setEnabled(false);
	}

	private void inicializarDatosLaborales() {
		// ########### FECHA DE INGRESO ###########
		dcFechaIngreso.setEnabled(false);
		dcFechaIngreso.setDate(null);
		txtObsIngreso.setEnabled(false);
		txtObsIngreso.setText("");
		
		// ########### CONDICION ###########
		txtCodCond.setText("");
		txtCondicion.setText("");
		dcDesdeCondicion.setEnabled(false);
		dcDesdeCondicion.setDate(null);
		dcHastaCondicion.setEnabled(false);
		dcHastaCondicion.setDate(null);
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

		// ########### CATEGORIA ###########
		txtCodCategoria.setText("");
		txtCategoria.setText("");
		dcVigCat.setEnabled(false);
		dcVigCat.setDate(null);
		txtObsCategoria.setEnabled(false);
		txtObsCategoria.setText("");
		btnCategoria.setEnabled(false);
		
		// ##############Dias#################
		cbDia.setEnabled(false);
		cbDia.setSelectedItem("SELECCIONE");
		txtObsDia.setText("");
		txtObsDia.setEnabled(false);
		dcVigDia.setEnabled(false);
		dcVigDia.setDate(null);
	}

	private boolean verificarFechaIngreso() throws ParseException{
		//verifica que la fecha desde de la condicion no sea antes que la de ingreso
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaDesde, fechaIngreso;
		boolean control = true;
			fechaDesde = sdf.parse(String.valueOf(sdf.format(dcDesdeCondicion.getDate())));
			fechaIngreso = sdf1.parse(String.valueOf(sdf1.format(dcFechaIngreso.getDate())));
			
			//Si arroja <0 entonces  es un dia antes; ==0 en el dia, >0 dias despues
			if (fechaIngreso.compareTo(fechaDesde)<0){
				if (this.OPCION==1){
					//Si se le da de alta al usuario entonces la fecha de ingreso 
					//no debe ser menor a la fecha condicion
					control = false;
				}else{
					control = true;
				}
			}else if (fechaIngreso.compareTo(fechaDesde) == 0){
				control = true;
			}else if (fechaIngreso.compareTo(fechaDesde)>0){
				control = false;
			}
			
		return control ;
	}
	//Ya no es necesario verificar esta condicion
	private boolean modificoCondicion() {
		boolean control = true;
		int codigo = this.personal.getCodCondicion();
		if (Integer.toString(codigo).equals(txtCodCond.getText())) {
			control = true;
		}
		return control;
	}
	
	private boolean verificarPeriodoInicioFin() throws ParseException {
		//Verifica que la fecha de fin no sea menor que la fecha de inicio
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean control = false;
		if (dcDesdeCondicion.getDate() != null) {
			Date fechaInicio = sdf.parse(String.valueOf(sdf.format(dcDesdeCondicion.getDate())));
			Date fechaFin = sdf.parse(String.valueOf(sdf.format(dcHastaCondicion.getDate())));
			//Si arroja <0 entonces  es un dia antes; ==0 en el dia, >0 dias despues
			if (fechaInicio.compareTo(fechaFin)<0){
				//Inicio anterior a fin
				control = true;
			}else if (fechaInicio.compareTo(fechaFin) == 0){
				//Inicio y fin iguales
				control = true;
			}else if (fechaInicio.compareTo(fechaFin)>0){
				//Inicio es posterior a fin
				control = false;
			}
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

//	private boolean modificoOficina() {
//		boolean control = true;
//		String codigo = this.personal.getCodOficina();
//		if (codigo.equals(txtCodOfi.getText())) {
//			control = false;
//		}
//		return control;
//	}

	private boolean modificoCargo() {
		boolean control = true;
		String codigo = Integer.toString(this.personal.getCodCargo());
		if (codigo.equals(txtCodCargo.getText())) {
			control = false;
		}
		return control;
	}
	
	private boolean modificoDia(){
		boolean control = true;
		String descripcion = this.personal.getDias().trim();
		if(descripcion.equals(cbDia.getSelectedItem().toString().substring(0, 3))){
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

	private boolean modificoCategoria() {
		boolean control = true;
		String codigo = this.personal.getCategoriaCodigo();
		if (codigo.equals(txtCodCategoria.getText())) {
			control = false;
		}
		return control;
	}

	private boolean verificarPeriodoCategoria() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean control = false;
		if (dcVigCat.getDate() != null) {
			Date fechaInicioActual = sdf.parse(String.valueOf(sdf.format(dcVigCat.getDate())));
			control = fechaInicioActual.after(this.personal.getCatFechaInicio());
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
		txtNombres.setEnabled(true);
		txtApellidos.setEnabled(true);
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
		limpiarTabla(tbEvaluaciones, modelo);
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
		limpiarTabla(tbEvaluaciones, modelo);
	}
	private void inicializarCategorias(){
		limpiarTabla(tbCategoria, modeloCat);
	}
	private void inicializarDependencias(){
		limpiarTabla(tbDependencia, modeloDep);
	}
	private void inicializarOficinas() {
		limpiarTabla(tbOficina, modeloOfi);

	}
	private void inicializarTipos(){
		limpiarTabla(tbTipos, modeloTipo);
	}
	private void inicializarTurnos(){
		limpiarTabla(tbTurnos, modeloTurnos);
	}
	private void inicializarDias(){
		limpiarTabla(tbDias, modeloDias);
	}

	// ----------------------------------------------------------------------------
	// ventana HISTORIAL OFICINA
	private void cargarHistorialOficina() throws ClassNotFoundException {
		FechaUtil fecha = new FechaUtil();
		limpiarTabla(tbOficina, modeloOfi);
		Object[] fila = new Object[6];
		for (PerOfi ofi : sistema.obtenerHistorialOficina(this.personal.getCedula())) {
			fila[0] = ofi.getOficinaCodigo();
			fila[1] = ofi.getOficinaDescripcion();
			fila[2] = fecha.getDateToString(ofi.getFechaInicio());
			fila[3] = fecha.getDateToString(ofi.getFechaFin());
			fila[4] = ofi.getObservacion();
			modeloOfi.addRow(fila);
			tbOficina.setModel(modeloOfi);
		}
	}

	// ventana HISTORIAL DEPENDENCIA
	private void cargarHistorialDependencia() throws ClassNotFoundException {
		FechaUtil fecha = new FechaUtil();
		limpiarTabla(tbDependencia, modeloDep);
		Object[] fila = new Object[6];
		for (PerDep dep : sistema.obtenerHistorialDependencia(this.personal.getCedula())) {
			fila[0] = dep.getDependenciaCodigo();
			fila[1] = dep.getDependenciaDescripcion();
			fila[2] = fecha.getDateToString(dep.getFechaInicio());
			fila[3] = fecha.getDateToString(dep.getFechaFin());
			fila[4] = dep.getObservacion();
			modeloDep.addRow(fila);
			tbDependencia.setModel(modeloDep);
		}
	}

	// ventana HISTORIAL TIPO PERSONAL
	private void cargarHistorialTipoPersonal() throws ClassNotFoundException {
		FechaUtil fecha = new FechaUtil();
		limpiarTabla(tbTipos, modeloTipo);
		Object[] fila = new Object[6];
		for (PerTipos tipos : sistema.obtenerHistorialTipoPersonal(this.personal.getCedula())) {
			fila[0] = tipos.getTipoCodigo();
			fila[1] = tipos.getTipoDescripcion();
			fila[2] = fecha.getDateToString(tipos.getFechaInicio());
			fila[3] = fecha.getDateToString(tipos.getFechaFin());
			fila[4] = tipos.getObservacion();
			modeloTipo.addRow(fila);
			tbTipos.setModel(modeloTipo);
		}
	}

	// ventana HISTORIAL TURNO
	private void cargarHistorialTurno() throws ClassNotFoundException {
		FechaUtil fecha = new FechaUtil();
		limpiarTabla(tbTurnos, modeloTurnos);
		Object[] fila = new Object[6];
		for (PerTur turnos : sistema.obtenerHistorialTurnos(this.personal.getCedula())) {
			fila[0] = turnos.getTurnoId();
			fila[1] = turnos.getDescripcionTurno();
			fila[2] = fecha.getDateToString(turnos.getFechaInicio());
			fila[3] = fecha.getDateToString(turnos.getFechaFin());
			fila[4] = turnos.getObservacion();
			modeloTurnos.addRow(fila);
			tbTurnos.setModel(modeloTurnos);
		}
	}

	// ventana HISTORIAL CATEGORIA
	private void cargarHistorialCategoria() throws ClassNotFoundException {
		FechaUtil fecha = new FechaUtil();
		limpiarTabla(tbCategoria, modeloCat);
		Object[] fila = new Object[6];
		for (PerCat categoria : sistema.obtenerHistorialCategoria(this.personal.getCedula())) {
			fila[0] = categoria.getCodigo();
			fila[1] = categoria.getDescripcion();
			fila[2] = fecha.getDateToString(categoria.getFechaInicio());
			fila[3] = fecha.getDateToString(categoria.getFechaFin());
			fila[4] = categoria.getObservacion();
			modeloCat.addRow(fila);
			tbCategoria.setModel(modeloCat);
		}
	}

	// ventana HISTORIAL DIAS
	private void cargarHistorialDias() throws ClassNotFoundException {
		FechaUtil fecha = new FechaUtil();
		limpiarTabla(tbDias, modeloDias);
		Object[] fila = new Object[6];
		for (PerDias dias : sistema.obtenerHistorialDias(this.personal.getCedula())) {
			fila[0] = dias.getDiaId();
			fila[1] = dias.getDescripciondia();
			fila[2] = fecha.getDateToString(dias.getFechaInicio());
			fila[3] = fecha.getDateToString(dias.getFechaFin());
			fila[4] = dias.getObservacion();
			modeloDias.addRow(fila);
			tbDias.setModel(modeloDias);
		}
	}

	// obtener objeto de una lista

//	private void obtenerOficina(OficinaModal vista) throws ClassNotFoundException {
//		if (vista.getCodigo() != null) {
//			Oficina ofi = this.sistema.obtenerOficina(vista.getCodigo());
//			txtCodOfi.setText(ofi.getCodigo());
//			txtOficina.setText(ofi.getDescripcion());
//			if (this.OPCION == 2) {
//				if (!this.personal.getCodOficina().equals(txtCodOfi.getText())) {
//					
//				} else {
//			
//				}
//			}
//		}
//	}

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
private void obtenerCondicionID() throws ClassNotFoundException{
	
}
	private void obtenerCondicion(CondicionModal vista) throws ClassNotFoundException, ParseException {
		if (vista.getCodigo() != null) {
			TipoPersonal condicion = this.sistema.obtenerCondicionById(vista.getCodigo());
			txtCodCond.setText(Integer.toString(condicion.getId()));
			txtCondicion.setText(condicion.getDescripcion());
			if (this.OPCION == 2) {
//				if (Integer.toString(this.personal.getCodCondicion()).equals(txtCodCond.getText())) {
					txtObsCondicion.setText("");
					dcDesdeCondicion.setEnabled(true);
					dcHastaCondicion.setEnabled(true);
//			} 
//				else {
//					txtObsCondicion.setText(this.personal.getCondObservacion());
//					dcDesdeCondicion.setDate(this.personal.getCondFechaIni());
//					dcDesdeCondicion.setDate(this.personal.getCondFechaIni());
//					dcDesdeCondicion.setEnabled(false);
//					dcHastaCondicion.setEnabled(false);
//				}

			}
			comprobarCondicion(condicion.getCondicion());
		}
	}
//Comprobar la dependencia para habilitar el campo fecha hasta
//Si  la dependencia esNOMBRADO o NOMBRADO COMISIONADO
//El campo fecha permanece deshabilitado
private void comprobarCondicion(String condicion) throws ParseException{
	
	java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2099-12-31");
	   	   
		varCondicion = condicion;
	if ((condicion.equals("C"))){
		dcHastaCondicion.setEnabled(true);
	}else{
		dcHastaCondicion.setEnabled(false);
		dcHastaCondicion.setDate(date);
		lblHastaCondicion.setText("Hasta:");
		lblHastaCondicion.setForeground(Color.BLACK);
	}
	
}
	private void obtenerDependencia(DependenciaModal vista) throws ClassNotFoundException {
		if (vista.getCodigo() != null) {
			Dependencia dep = this.sistema.obtenerDependencia(vista.getCodigo());
			txtCodDep.setText(dep.getCodigo());
			txtDependencia.setText(dep.getDescripcion());
			//######## DATOS DE LA OFICINA
			txtCodOfi.setText(dep.getCodigoOficina());
			txtOficina.setText(dep.getOficina());
			
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

	// ------------------------------------------------------------------------
	private void obtenerCategoria(CategoriaModal vista) throws ClassNotFoundException {
		if (vista.getCategoria() != null) {
			Categoria cat = this.sistema.obtenerCategoria(vista.getCodigo());
			txtCodCategoria.setText(cat.getCodigo());
			txtCategoria.setText(cat.getDescripcion());
			if (this.OPCION == 2) {
				if (!this.personal.getCategoriaCodigo().equals(txtCodCategoria.getText())) {
					txtObsDependencia.setText("");
					dcVigCat.setEnabled(true);
				} else {
					txtObsCategoria.setText(this.personal.getDepObservacion());
					dcVigHorario.setDate(this.personal.getDepFechaIni());
					dcVigDependencia.setEnabled(false);
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
		this.perCon.setFechaInicio(dcDesdeCondicion.getDate());
		this.perCon.setFechaFin(dcHastaCondicion.getDate());
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
	
	private void cargarNuevoIngresoPersonal(){
		this.perFechaIngreso = new PerFechaIngreso();
		this.perFechaIngreso.setCedula(txtCedula.getText());
		this.perFechaIngreso.setFecha(dcFechaIngreso.getDate());
		this.perFechaIngreso.setObservacion(txtObsIngreso.getText());
		this.perFechaIngreso.setAdministrador(MenuPrincipalVista.administrador.getLogin());
		this.perFechaIngreso.setCondicion(varCondicion);
	}

//	private void cargarNuevaOficina() {
//		this.perOfi = new PerOfi();
//		this.perOfi.setCedula(txtCedula.getText());
//		this.perOfi.setOficinaCodigo(txtCodOfi.getText());
//		this.perOfi.setObservacion(txtObsDependencia.getText());
//		this.perOfi.setAdministrador(MenuPrincipalVista.administrador.getLogin());
//	}

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

	private void cargarNuevaCategoria() {
		this.perCat = new PerCat();
		this.perCat.setCedula(txtCedula.getText());
		this.perCat.setCodigo(txtCodCategoria.getText());
		this.perCat.setFechaInicio(dcVigCat.getDate());
		this.perCat.setObservacion(txtObsCategoria.getText().trim());
		this.perCat.setAdministrador(MenuPrincipalVista.administrador.getLogin());
	}
	
	private void cargarNuevoDia(){
		this.perDias = new PerDias();
		this.perDias.setCedula(txtCedula.getText());
		this.perDias.setDescripciondia(cbDia.getSelectedItem().toString().substring(0,3));
		this.perDias.setFechaInicio(dcVigDia.getDate());
		this.perDias.setObservacion(txtObsDia.getText());
		this.perDias.setAdministrador(MenuPrincipalVista.administrador.getLogin());
	}
private PerFechaDesvinculacion cargarNuevosDatosDesvinculacion() throws ClassNotFoundException{
	PerFechaDesvinculacion perDesvinculacion = new PerFechaDesvinculacion();
	perDesvinculacion.setFecha(dcDesvinculacion.getDate());
	perDesvinculacion.setObservacion(txtObsDesvinculacion.getText());
	perDesvinculacion.setCedula(txtCedula.getText());
	perDesvinculacion.setAdministrador(MenuPrincipalVista.administrador.getLogin());
	
	//Obtener el id de la condicion
	TipoPersonal tipoPer = this.sistema.obtenerCondicionByCedula(txtCedula.getText());
	perDesvinculacion.setIdPersonalesTipos(Integer.toString(tipoPer.getId()));
	
	return perDesvinculacion;
}
	private Personal cargarNuevosDatosPersonales() {
		Personal per = new Personal();
		per.setCedula(txtCedula.getText().trim());
		per.setNombres(txtNombres.getText());
		per.setApellidos(txtApellidos.getText());
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

	private void habilitarDesvinculacion(){
		dcDesvinculacion.setEnabled(true);
		txtObsDesvinculacion.setEnabled(true);
		txtObsDesvinculacion.setText("");
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

	// ----------------------------------------------------------//
	private void formatearTablaHistorial(JTable tabla, String nombre) {

		if (nombre.equals("tbCategoria")) {
			modeloCat.addColumn("CODIGO");
			modeloCat.addColumn("DESCRIPCION");
			modeloCat.addColumn("FECHA INICIO");
			modeloCat.addColumn("FECHA FIN");
			modeloCat.addColumn("OBSERVACION");
			tabla.setModel(modeloCat);
			TableColumn columna0 = tabla.getColumn("CODIGO");
			TableColumn columna1 = tabla.getColumn("DESCRIPCION");
			TableColumn columna2 = tabla.getColumn("FECHA INICIO");
			TableColumn columna3 = tabla.getColumn("FECHA FIN");
			TableColumn columna4 = tabla.getColumn("OBSERVACION");
			columna0.setPreferredWidth(42);
			columna1.setPreferredWidth(220);
			columna2.setPreferredWidth(80);
			columna3.setPreferredWidth(80);
			columna4.setPreferredWidth(200);
		} else if (nombre.equals("tbDependencia")) {
			modeloDep.addColumn("CODIGO");
			modeloDep.addColumn("DESCRIPCION");
			modeloDep.addColumn("FECHA INICIO");
			modeloDep.addColumn("FECHA FIN");
			modeloDep.addColumn("OBSERVACION");
			tabla.setModel(modeloDep);
			TableColumn columna0 = tabla.getColumn("CODIGO");
			TableColumn columna1 = tabla.getColumn("DESCRIPCION");
			TableColumn columna2 = tabla.getColumn("FECHA INICIO");
			TableColumn columna3 = tabla.getColumn("FECHA FIN");
			TableColumn columna4 = tabla.getColumn("OBSERVACION");
			columna0.setPreferredWidth(42);
			columna1.setPreferredWidth(220);
			columna2.setPreferredWidth(80);
			columna3.setPreferredWidth(80);
			columna4.setPreferredWidth(200);
		} else if (nombre.equals("tbOficina")) {
			modeloOfi.addColumn("CODIGO");
			modeloOfi.addColumn("DESCRIPCION");
			modeloOfi.addColumn("FECHA INICIO");
			modeloOfi.addColumn("FECHA FIN");
			modeloOfi.addColumn("OBSERVACION");
			tabla.setModel(modeloOfi);
			TableColumn columna0 = tabla.getColumn("CODIGO");
			TableColumn columna1 = tabla.getColumn("DESCRIPCION");
			TableColumn columna2 = tabla.getColumn("FECHA INICIO");
			TableColumn columna3 = tabla.getColumn("FECHA FIN");
			TableColumn columna4 = tabla.getColumn("OBSERVACION");
			columna0.setPreferredWidth(42);
			columna1.setPreferredWidth(220);
			columna2.setPreferredWidth(80);
			columna3.setPreferredWidth(80);
			columna4.setPreferredWidth(200);
		} else if (nombre.equals("tbTipos")) {
			modeloTipo.addColumn("CODIGO");
			modeloTipo.addColumn("DESCRIPCION");
			modeloTipo.addColumn("FECHA INICIO");
			modeloTipo.addColumn("FECHA FIN");
			modeloTipo.addColumn("OBSERVACION");
			tabla.setModel(modeloTipo);
			TableColumn columna0 = tabla.getColumn("CODIGO");
			TableColumn columna1 = tabla.getColumn("DESCRIPCION");
			TableColumn columna2 = tabla.getColumn("FECHA INICIO");
			TableColumn columna3 = tabla.getColumn("FECHA FIN");
			TableColumn columna4 = tabla.getColumn("OBSERVACION");
			columna0.setPreferredWidth(42);
			columna1.setPreferredWidth(220);
			columna2.setPreferredWidth(80);
			columna3.setPreferredWidth(80);
			columna4.setPreferredWidth(200);
		} else if (nombre.equals("tbTurnos")) {
			modeloTurnos.addColumn("CODIGO");
			modeloTurnos.addColumn("DESCRIPCION");
			modeloTurnos.addColumn("FECHA INICIO");
			modeloTurnos.addColumn("FECHA FIN");
			modeloTurnos.addColumn("OBSERVACION");
			tabla.setModel(modeloTurnos);
			TableColumn columna0 = tabla.getColumn("CODIGO");
			TableColumn columna1 = tabla.getColumn("DESCRIPCION");
			TableColumn columna2 = tabla.getColumn("FECHA INICIO");
			TableColumn columna3 = tabla.getColumn("FECHA FIN");
			TableColumn columna4 = tabla.getColumn("OBSERVACION");
			columna0.setPreferredWidth(42);
			columna1.setPreferredWidth(220);
			columna2.setPreferredWidth(80);
			columna3.setPreferredWidth(80);
			columna4.setPreferredWidth(200);
		} else if (nombre.equals("tbDias")) {
			modeloDias.addColumn("CODIGO");
			modeloDias.addColumn("DESCRIPCION");
			modeloDias.addColumn("FECHA INICIO");
			modeloDias.addColumn("FECHA FIN");
			modeloDias.addColumn("OBSERVACION");
			tabla.setModel(modeloDias);
			TableColumn columna0 = tabla.getColumn("CODIGO");
			TableColumn columna1 = tabla.getColumn("DESCRIPCION");
			TableColumn columna2 = tabla.getColumn("FECHA INICIO");
			TableColumn columna3 = tabla.getColumn("FECHA FIN");
			TableColumn columna4 = tabla.getColumn("OBSERVACION");
			columna0.setPreferredWidth(42);
			columna1.setPreferredWidth(220);
			columna2.setPreferredWidth(80);
			columna3.setPreferredWidth(80);
			columna4.setPreferredWidth(200);
		}

	}

	private void limpiarTabla(JTable tabla, ModeloTablaUtil model) {
		while (tabla.getRowCount() != 0) {
			model.removeRow(0);
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
