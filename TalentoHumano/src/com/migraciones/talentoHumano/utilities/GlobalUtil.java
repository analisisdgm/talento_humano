package com.migraciones.talentoHumano.utilities;

public class GlobalUtil {

	// ##############################
	// ######## OPERACIONES #########
	// ##############################

	public static final int agregarObjeto = 0;
	public static final int editarObjeto = 1;
	public static final int borrarObjeto = 2;

	// #############################
	// ######## PATH LOCALES #######
	// #############################

//	private static String PATH_REPORTE = System.getProperty("user.dir")
//			+ "/src/com/migraciones/talentoHumano/reportes/";
//	private static String PATH_REPORTE_CARNET = "C:\\TalentoHumano$\\src\\com\\migraciones\\talentoHumano\\carnets\\";
//	public static String PATH_CARNET = "C:\\TalentoHumano$\\src\\com\\migraciones\\talentoHumano\\carnets\\";
//	public static final String PUBLIC_REPORTE_CARNET = PATH_REPORTE_CARNET;

	// #############################
	// ######## PATH REMOTOS #######
	// #############################

	private static String SERVER = "192.168.1.245";
	public static String PATH_CARNET = "\\\\" + SERVER
			+ "\\TalentoHumano$\\src\\com\\migraciones\\talentoHumano\\carnets\\";
	private static String PATH_REPORTE = "\\\\" + SERVER
			+ "\\TalentoHumano$\\src\\com\\migraciones\\talentoHumano\\reportes\\";
	private static String PATH_REPORTE_CARNET = "\\\\" + SERVER
			+ "\\TalentoHumano$\\src\\com\\migraciones\\talentoHumano\\carnets\\";
	public static final String PUBLIC_REPORTE_CARNET = PATH_REPORTE_CARNET;

	// #############################
	// ######### IMAGENES ##########
	// #############################

	private static String PATH_IMG = "/com/migraciones/talentoHumano/graphics/";
	public static final String RUTA_LOGO_MIGRACIONES = PATH_IMG + "logoMigraciones.png";
	public static final String RUTA_FAVICON_MIGRACIONES = PATH_IMG + "faviconGobierno.png";
	public static final String RUTA_FAVICON_SIGESTH = PATH_IMG + "sigesth.png";
	public static final String RUTA_IMAGEN_FONDO = PATH_IMG + "fondo.png";
	public static final String RUTA_IMAGEN_CERRAR_SESION = PATH_IMG + "cerrarSesion.png";
	public static final String RUTA_IMAGEN_ACTUALIZAR = PATH_IMG + "actualizar.png";
	public static final String RUTA_IMAGEN_SALIR = PATH_IMG + "salir.png";
	public static final String RUTA_IMAGEN_ADMINISTRADORES = PATH_IMG + "administradores.png";
	public static final String RUTA_IMAGEN_FUNCIONARIOS = PATH_IMG + "funcionarios.png";
	public static final String RUTA_IMAGEN_PROVEEDORES = PATH_IMG + "proveedores.png";
	public static final String RUTA_IMAGEN_BIENES = PATH_IMG + "bienes.png";
	public static final String RUTA_IMAGEN_FACTURAS = PATH_IMG + "facturas.png";
	public static final String RUTA_IMAGEN_CODIGO_BARRAS = PATH_IMG + "codigoBarras.png";
	public static final String RUTA_IMAGEN_EXPORTAR = PATH_IMG + "exportar.png";
	public static final String RUTA_IMAGEN_DEPENDENCIAS = PATH_IMG + "dependencias.png";
	public static final String RUTA_IMAGEN_PLAN_CUENTAS = PATH_IMG + "planCuentas.png";
	public static final String RUTA_IMAGEN_BIENES_CONSULTAS = PATH_IMG + "lupa.png";
	public static final String RUTA_IMAGEN_HISTORICOS = PATH_IMG + "historicos.png";
	public static final String RUTA_IMAGEN_COMPRAS = PATH_IMG + "compras.png";
	public static final String RUTA_IMAGEN_MOVIMIENTOS = PATH_IMG + "movimientos.png";
	public static final String RUTA_IMAGEN_CODIFICACION = PATH_IMG + "patrimoniarBien.png";
	public static final String RUTA_IMAGEN_RESPONSABILIDAD = PATH_IMG + "responsabilidad.png";
	public static final String RUTA_IMAGEN_DOCUMENTOS = PATH_IMG + "documentos.png";
	public static final String RUTA_IMAGEN_HORARIOS = PATH_IMG + "horarios.png";
	public static final String RUTA_IMAGEN_ASIGNAR = PATH_IMG + "asignar.png";

	// #############################
	// ######### REPORTES ##########
	// #############################

	public static final String RUTA_REPORTE_JL = PATH_REPORTE + "JustificacionLista.jasper";
	public static final String RUTA_REPORTE_JP = PATH_REPORTE + "JustificacionPersonal.jasper";
	public static final String RUTA_REPORTE_FD = PATH_REPORTE + "FaltaDisciplinaria.jasper";
	public static final String RUTA_REPORTE_LP = PATH_REPORTE + "ListadoPersonal.jasper";
	public static final String RUTA_REPORTE_POFI = PATH_REPORTE + "ListadoPersonalOficina.jasper";
	public static final String RUTA_REPORTE_PSEXO = PATH_REPORTE + "ListadoPersonalSexo.jasper";
	public static final String RUTA_REPORTE_PCAT = PATH_REPORTE + "ListadoPersonalCategoria.jasper";
	public static final String RUTA_REPORTE_FA = PATH_REPORTE + "FichaAsistencia.jasper";
	public static final String RUTA_CREDENCIAL_FRONT = PATH_REPORTE + "CarnetFront.jasper";
	public static final String RUTA_CREDENCIAL_BACK = PATH_REPORTE + "CarnetBack.jasper";

	// #############################
	// ######### MENSAJES ##########
	// #############################

	public static final String MSG_CAMPOS_NO_LLENOS = "Debe completar todos los campos.";
	public static final String MSG_NO_EXISTE_FUNCIONARIO = "No se encuentra ningun funcionario con ese numero de cedula.";
	public static final String MSG_ITEM_NO_SELECCIONADO = "Debe seleccionar un item.";
	public static final String MSG_VERIFICAR_PERIODO = "Verifique periodo de tiempo.";
	public static final String MSG_DATOS_GUARDADOS_OK = "Los datos se guardaron correctamente.";
	public static final String MSG_DATOS_GUARDADOS_FAIL = "Ocurrio un error al agregar la informacion ";
	public static final String MSG_DATOS_ACTUALIZADOS_OK = "Los datos se actualizaron correctamente.";
	public static final String MSG_DATOS_ACTUALIZADOS_FAIL = "Ocurrio un error al actualizar la informacion.";
	public static final String MSG_DATOS_BORRADOS_OK = "Los datos se borraron correctamente.";
	public static final String MSG_DATOS_BORRADOS_FAIL = "Ocurrio un error al borrar la informacion.";
	public static final String MSG_NO_LOGIN = "Usuario o contrasena invalida.";
	public static final String MSG_FUNCIONARIO_NOT_FOUND = "No existe el funcionario.";
	public static final String MSG_REGISTRO_NOT_FOUND = "No existe registros.";
	public static final String MSG_GLOBAL_ERROR = "Ocurrio un error al procesar los datos. Por favor, intente de nuevo.";

}