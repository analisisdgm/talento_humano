package com.migraciones.talentoHumano.vistas.formularios;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.UnknownHostException;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.migraciones.talentoHumano.modelos.Administrador;
import com.migraciones.talentoHumano.utilities.GlobalUtil;
import com.migraciones.talentoHumano.utilities.ImgBackground;

public class MenuPrincipalVista extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 115669897228109045L;
	public static Administrador administrador;
	private JPanel contentPane;
	private JDesktopPane dpEscritorio;

	/**
	 * Create the frame.
	 * 
	 * @throws UnknownHostException
	 */
	public MenuPrincipalVista(Administrador administrador) throws UnknownHostException {
		setMinimumSize(new Dimension(800, 600));
		setTitle("SIGESTH - SISTEMA INFORMATICO DE GESTION DE TALENTO HUMANO");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(MenuPrincipalVista.class.getResource(GlobalUtil.RUTA_FAVICON_SIGESTH)));
		MenuPrincipalVista.administrador = administrador;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 300);

		JMenuBar mbBarraHerramientas = new JMenuBar();
		setJMenuBar(mbBarraHerramientas);

		// ###################### MENU SISTEMA ###########################

		JMenu mnSistema = new JMenu("Sistema");
		mbBarraHerramientas.add(mnSistema);

		JSeparator separator = new JSeparator();
		mnSistema.add(separator);

		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar Sesion");
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmCerrarSesionActionPerformed(e);
			}

		});

		JMenuItem mntmAdministradores = new JMenuItem("Administradores");
		mntmAdministradores.setEnabled(false);
		mnSistema.add(mntmAdministradores);

		JMenuItem mntmCambiarContrasea = new JMenuItem("Cambiar Contrase\u00F1a");
		mntmCambiarContrasea.setIcon(new ImageIcon(
				MenuPrincipalVista.class.getResource("/com/migraciones/talentoHumano/graphics/administradores.png")));
		mntmCambiarContrasea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmCambiarContraseaActionPerformed(e);
			}
		});
		mnSistema.add(mntmCambiarContrasea);

		JSeparator separator_1 = new JSeparator();
		mnSistema.add(separator_1);
		mntmCerrarSesion
				.setIcon(new ImageIcon(MenuPrincipalVista.class.getResource(GlobalUtil.RUTA_IMAGEN_CERRAR_SESION)));
		mnSistema.add(mntmCerrarSesion);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmSalirActionPerformed(e);
			}
		});
		mntmSalir.setIcon(new ImageIcon(MenuPrincipalVista.class.getResource(GlobalUtil.RUTA_IMAGEN_SALIR)));
		mnSistema.add(mntmSalir);

		JMenu mnDepartamentosYLegajos = new JMenu("Departamentos y Legajos");
		mbBarraHerramientas.add(mnDepartamentosYLegajos);

		JMenuItem mntmPersonales = new JMenuItem("Legajos");
		mntmPersonales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mntmPersonalesActionPerformed(e);
				} catch (ClassNotFoundException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		mntmPersonales.setIcon(new ImageIcon(
				MenuPrincipalVista.class.getResource("/com/migraciones/talentoHumano/graphics/funcionarios.png")));
		mnDepartamentosYLegajos.add(mntmPersonales);

		// ###################### MENU ASISTENCIA #########################

		JMenu mnAsistencia = new JMenu("Consultas");
		mbBarraHerramientas.add(mnAsistencia);

		JMenuItem mntmHorarios = new JMenuItem("Horarios");
		mntmHorarios.setEnabled(false);
		mntmHorarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mntmHorariosActionPerformed(e);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmHorarios.setIcon(new ImageIcon(MenuPrincipalVista.class.getResource(GlobalUtil.RUTA_IMAGEN_HORARIOS)));
		mnAsistencia.add(mntmHorarios);

		JSeparator separator_3 = new JSeparator();
		mnAsistencia.add(separator_3);

		JMenuItem mntmReporteDePersonales = new JMenuItem("Reportes");
		mntmReporteDePersonales.setIcon(new ImageIcon(
				MenuPrincipalVista.class.getResource("/com/migraciones/talentoHumano/graphics/planCuentas.png")));
		mntmReporteDePersonales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					mntmReportesActionPerformed(arg0);
				} catch (ClassNotFoundException | ParseException e) {
					e.printStackTrace();
				}
			}
		});
		mnAsistencia.add(mntmReporteDePersonales);

		JMenu mnJustificaciones = new JMenu("Procesos");
		mbBarraHerramientas.add(mnJustificaciones);

		JMenuItem mntmCorrecEntradasalida = new JMenuItem("Correci\u00F3n de estados");
		mntmCorrecEntradasalida.setIcon(new ImageIcon(
				MenuPrincipalVista.class.getResource("/com/migraciones/talentoHumano/graphics/actualizar.png")));
		mntmCorrecEntradasalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mntmCorrecEntradasalidaActionPerformed(e);
				} catch (ClassNotFoundException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JMenuItem mntmAsignarJustificativo = new JMenuItem("Asignar Justificativo");
		mntmAsignarJustificativo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					mntmAsignarJustificativoActionPerformed(arg0);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		mntmAsignarJustificativo.setIcon(new ImageIcon(
				MenuPrincipalVista.class.getResource("/com/migraciones/talentoHumano/graphics/asignar.png")));
		mnJustificaciones.add(mntmAsignarJustificativo);
		mnJustificaciones.add(mntmCorrecEntradasalida);

		JMenuItem mntmFeriadosYPermisos = new JMenuItem("Feriados y permisos colectivos");
		mntmFeriadosYPermisos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					mntmFeriadosYPermisosActionPerformed(arg0);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnJustificaciones.add(mntmFeriadosYPermisos);

		JMenu mnMantenimientoDatos = new JMenu("Mantenimiento de datos");
		mbBarraHerramientas.add(mnMantenimientoDatos);

		JMenuItem mntmActualizacionBD = new JMenuItem("Actualizacion de BD");
		mntmActualizacionBD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmActualizacionBDActionPerformed(e);
			}
		});
		mntmActualizacionBD
				.setIcon(new ImageIcon(MenuPrincipalVista.class.getResource(GlobalUtil.RUTA_IMAGEN_ACTUALIZAR)));
		mnMantenimientoDatos.add(mntmActualizacionBD);

		// ######################## MENU AYUDA ###########################

		JMenu mnAyuda = new JMenu("Ayuda");
		mbBarraHerramientas.add(mnAyuda);

		JMenuItem mntmManualDeFunciones = new JMenuItem("Manual de funciones");
		mntmManualDeFunciones.setEnabled(false);
		mntmManualDeFunciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					mntmManualDeFuncionesActionPerformed(arg0);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		mntmManualDeFunciones
				.setIcon(new ImageIcon(MenuPrincipalVista.class.getResource(GlobalUtil.RUTA_IMAGEN_DOCUMENTOS)));
		mnAyuda.add(mntmManualDeFunciones);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		dpEscritorio = new JDesktopPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(
				dpEscritorio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1515,
				Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(dpEscritorio,
				javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 860,
				Short.MAX_VALUE));
		contentPane.setLayout(gl_contentPane);
		formatearVista();
	}

	protected void mntmReportesActionPerformed(ActionEvent arg0) throws ClassNotFoundException, ParseException {
		ReporteVista vista = new ReporteVista();
		mostrarVista(vista);

	}

	// ######################## SISTEMA ###########################

	protected void mntmManualDeFuncionesActionPerformed(ActionEvent arg0) throws ClassNotFoundException {
		// CargarImagenModal vista = new CargarImagenModal();
		// vista.setVisible(true);
		// mostrarVista(vista);
	}

	protected void mntmCambiarContraseaActionPerformed(ActionEvent e) {
		CambioPasswordVista vista = new CambioPasswordVista(MenuPrincipalVista.administrador);
		mostrarVista(vista);
	}

	private void mntmCerrarSesionActionPerformed(ActionEvent e) {
		AccesoVista acceso = new AccesoVista();
		acceso.runAcceso();
		this.dispose();
	}

	private void mntmSalirActionPerformed(ActionEvent e) {
		this.dispose();
	}

	// ######################## DEPARTAMENTOS Y LEGAJOS ######################

	protected void mntmPersonalesActionPerformed(ActionEvent e) throws ClassNotFoundException, ParseException {
		PersonalVista2 vista = new PersonalVista2();
		mostrarVista(vista);
	}

	// ######################## ASISTENCIA ######################

	protected void mntmHorariosActionPerformed(ActionEvent e) throws ClassNotFoundException, ParseException {
		// HorarioView vista = new HorarioView();
		// mostrarVista(vista);

	}

	// ######################## PROCESOS ########################

	protected void mntmAsignarJustificativoActionPerformed(ActionEvent arg0) throws ClassNotFoundException {
		JustificativoVista vista = new JustificativoVista();
		mostrarVista(vista);
	}

	protected void mntmCorrecEntradasalidaActionPerformed(ActionEvent e) throws ClassNotFoundException, ParseException {
		TipoRegistroVista vista = new TipoRegistroVista();
		mostrarVista(vista);
	}

	protected void mntmFeriadosYPermisosActionPerformed(ActionEvent arg0) throws ClassNotFoundException {
		FeriadoVista vista = new FeriadoVista();
		mostrarVista(vista);
	}

	// ##################### MANTENIMIENTO DE DATOS #########################

	protected void mntmActualizacionBDActionPerformed(ActionEvent e) {
		ActualizacionRegistroVista vista = new ActualizacionRegistroVista();
		mostrarVista(vista);
	}

	// ######################## AYUDA ###########################

	// protected void mntmDocumentacionActionPerformed(ActionEvent e)
	// throws IOException {
	// File path = new File(GlobalUtil.RUTA_DOC_DECRETO20132);
	// Desktop.getDesktop().open(path);
	//
	// }

	// /////////////////// METODOS DEL SISTEMA ///////////////////////////

	private void formatearVista() throws UnknownHostException {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ImgBackground fondoEscritorio = new ImgBackground(MenuPrincipalVista.administrador);
		dpEscritorio.add(fondoEscritorio, BorderLayout.CENTER);
		dpEscritorio.setVisible(true);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}

	private void mostrarVista(JInternalFrame vista) {
		boolean mostrar = true;
		// verifica si es instancia de algun componente que ya esta
		for (int i = 0; i < dpEscritorio.getComponentCount(); i++) {
			if (vista.getClass().isInstance(dpEscritorio.getComponent(i))) {
				mostrar = false;
			}
		}
		if (mostrar) {
			dpEscritorio.add(vista);
			vista.show();
			centrarVista(vista);
		}
	}

	private void centrarVista(JInternalFrame vista) {
		Dimension desktopSize = dpEscritorio.getSize();
		Dimension jInternalFrameSize = vista.getSize();
		int width = (desktopSize.width - jInternalFrameSize.width) / 2;
		int height = (desktopSize.height - jInternalFrameSize.height) / 2;
		vista.setLocation(width, height);
		vista.setVisible(true);
	}
}
