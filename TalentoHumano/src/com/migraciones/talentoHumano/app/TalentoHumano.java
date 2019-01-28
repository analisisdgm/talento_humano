package com.migraciones.talentoHumano.app;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import com.migraciones.talentoHumano.controladores.AdministradorCont;
import com.migraciones.talentoHumano.controladores.CargoCont;
import com.migraciones.talentoHumano.controladores.CategoriaCont;
import com.migraciones.talentoHumano.controladores.CredencialCont;
import com.migraciones.talentoHumano.controladores.FeriadoCont;
import com.migraciones.talentoHumano.controladores.OficinaCont;
import com.migraciones.talentoHumano.controladores.PerCarCont;
import com.migraciones.talentoHumano.controladores.PerCatCont;
import com.migraciones.talentoHumano.controladores.PerConCont;
import com.migraciones.talentoHumano.controladores.PerDepCont;
import com.migraciones.talentoHumano.controladores.PerDiaCont;
import com.migraciones.talentoHumano.controladores.PerEvaCont;
import com.migraciones.talentoHumano.controladores.PerHistoDisipCont;
import com.migraciones.talentoHumano.controladores.PerOfiCont;
import com.migraciones.talentoHumano.controladores.PerTipoCont;
import com.migraciones.talentoHumano.controladores.PerTurCont;
import com.migraciones.talentoHumano.controladores.PersonalCont;
import com.migraciones.talentoHumano.controllers.DependenciaController;
import com.migraciones.talentoHumano.controllers.PersonalController;
import com.migraciones.talentoHumano.controllers.PersonalHorarioController;
import com.migraciones.talentoHumano.controllers.TipoJustificativoController;
import com.migraciones.talentoHumano.controllers.TipoPersonalController;
import com.migraciones.talentoHumano.controllers.TurnoController;
import com.migraciones.talentoHumano.modelos.Administrador;
import com.migraciones.talentoHumano.modelos.Cargo;
import com.migraciones.talentoHumano.modelos.Categoria;
import com.migraciones.talentoHumano.modelos.Dependencia;
import com.migraciones.talentoHumano.modelos.Evaluacion;
import com.migraciones.talentoHumano.modelos.FaltaDisciplinaria;
import com.migraciones.talentoHumano.modelos.Feriado;
import com.migraciones.talentoHumano.modelos.FichaAsistencia;
import com.migraciones.talentoHumano.modelos.JustificacionLista;
import com.migraciones.talentoHumano.modelos.JustificacionPersonal;
import com.migraciones.talentoHumano.modelos.Oficina;
import com.migraciones.talentoHumano.modelos.PerCar;
import com.migraciones.talentoHumano.modelos.PerCat;
import com.migraciones.talentoHumano.modelos.PerCon;
import com.migraciones.talentoHumano.modelos.PerDep;
import com.migraciones.talentoHumano.modelos.PerDias;
import com.migraciones.talentoHumano.modelos.PerHistoDisip;
import com.migraciones.talentoHumano.modelos.PerOfi;
import com.migraciones.talentoHumano.modelos.PerTipos;
import com.migraciones.talentoHumano.modelos.PerTur;
import com.migraciones.talentoHumano.modelos.Personal;
import com.migraciones.talentoHumano.modelos.Registro;
import com.migraciones.talentoHumano.modelos.TipoPersonal;
import com.migraciones.talentoHumano.modelos.Turno;
import com.migraciones.talentoHumano.models.HorarioPersonal;
import com.migraciones.talentoHumano.models.TipoJustificativo;
import com.migraciones.talentoHumano.utilities.Encrypt;
import com.migraciones.talentoHumano.utilities.ImprimirReporte;

public class TalentoHumano {

	// ##############################################
	// ##### REGISTROS DE ENTRADAS Y SALIDAS ########
	// ##############################################

	public void cargarRegistrosCompleto() throws ClassNotFoundException, SQLException {
		PersonalCont registroCont = new PersonalCont();
		registroCont.cargarRegistrosCompleto();
	}

	public void cargarRegistrosMensual() throws ClassNotFoundException, SQLException {
		PersonalCont registroCont = new PersonalCont();
		registroCont.cargarRegistrosMensual();
	}

	public FichaAsistencia obtenerFichaAsistencia(String cedula, int mes, int anho)
			throws ClassNotFoundException, ParseException {
		PersonalCont personalCont = new PersonalCont();
		return personalCont.getFichaMensual(cedula, mes, anho);
	}

	public FichaAsistencia obtenerFichaAsistencia(String cedula, Date fechaInicio, Date fechaFin)
			throws ClassNotFoundException, ParseException {
		PersonalCont personalCont = new PersonalCont();
		return personalCont.getFichaRango(cedula, fechaInicio, fechaFin);
	}

	public ArrayList<Registro> obtenerRegistroFecha(String cedula, Date fecha) {
		PersonalCont personalCont = new PersonalCont();
		return personalCont.getRegistroFecha(cedula, fecha);
	}

	public boolean actualizarTipoRegistro(int id, String tipo) throws ClassNotFoundException {
		PersonalCont personalCont = new PersonalCont();
		return personalCont.actualizarTipoRegistro(id, tipo);
	}

	public ArrayList<FaltaDisciplinaria> obtenerInasistenciasSinJustificar(Date fechaInicio, Date fechaFin)
			throws ClassNotFoundException {
		PersonalCont perCont = new PersonalCont();
		return perCont.getInasistenciaSinJustificar(fechaInicio, fechaFin);
	}

	public ArrayList<FaltaDisciplinaria> obtenerInasistenciasSinJustificar(String cedula, Date fechaInicio,
			Date fechaFin) throws ClassNotFoundException {
		PersonalCont perCont = new PersonalCont();
		return perCont.getInasistenciaSinJustificarByCedula(cedula, fechaInicio, fechaFin);
	}

	// ##############################################
	// ################# PERSONALES #################
	// ##############################################

	public Personal obtenerFuncionario(String cedula) throws ClassNotFoundException {
		PersonalCont perCont = new PersonalCont();
		Personal personal = perCont.getByCedula(cedula);
		return personal;
	}

	public ArrayList<Personal> obtenerFuncionarios() throws ClassNotFoundException {
		PersonalCont perCont = new PersonalCont();
		return perCont.getAll();
	}

	public ArrayList<Personal> obtenerFuncionariosByOficina(String codigo) throws ClassNotFoundException {
		PersonalCont personalCont = new PersonalCont();
		return personalCont.getByOficina(codigo);
	}

	public boolean agregarNuevoPersonal(Personal nuevoPersonal) throws ClassNotFoundException, SQLException {
		PersonalCont perCont = new PersonalCont();
		return perCont.agregarNuevoPersonal(nuevoPersonal);
	}

	public boolean actualizarDatosPersonal(Personal nuevosDatos) throws ClassNotFoundException, SQLException {
		PersonalCont perCont = new PersonalCont();
		return perCont.update(nuevosDatos);
	}

	public boolean existePersonal(String cedula) throws ClassNotFoundException {
		boolean control = false;
		PersonalCont personalCont = new PersonalCont();
		if (personalCont.getByCedula(cedula).getCedula() != null) {
			control = true;
		}
		return control;
	}

	// METODOS OBSOLETOS - FALTA ACTUALIZAR
	public Object obtenerPersonal(String cedula) throws ClassNotFoundException {
		PersonalHorarioController horarioCont = new PersonalHorarioController();
		HorarioPersonal horarioPersonal = horarioCont.getHorarioActivo(cedula);
		if (horarioPersonal.getPersonalId() == 0) {
			PersonalController personalCont = new PersonalController();
			return personalCont.getByIdCompleto(cedula);
		} else {
			return horarioPersonal;
		}
	}

	public ArrayList<PerHistoDisip> obtenerHistorialDisciplinarioRango(String fecha_inicio, String fecha_fin)
			throws ClassNotFoundException {
		PerHistoDisipCont historialCont = new PerHistoDisipCont();
		return historialCont.getHistorialRango(fecha_inicio, fecha_fin);
	}
	
	public ArrayList<Feriado> obtenerFeriadoRango(String fecha_inicio, String fecha_fin)
			throws ClassNotFoundException {
		FeriadoCont feriadoCont = new FeriadoCont();
		return feriadoCont.getHistorialRango(fecha_inicio, fecha_fin);
	}

	public boolean agregarHistoriaDisciplinaria(PerHistoDisip historia) throws ClassNotFoundException {
		PerHistoDisipCont histiriaCont = new PerHistoDisipCont();
		return histiriaCont.create(historia);
	}

	public boolean agregarNuevoFeriado(Feriado nuevoFeriado) throws ClassNotFoundException, SQLException {
		FeriadoCont ferCont = new FeriadoCont();
		return ferCont.agregarNuevoFeriado(nuevoFeriado);
	}
	public boolean actualizarFeriado(Feriado feriado) throws ClassNotFoundException {
		FeriadoCont ferCont = new FeriadoCont();
		return ferCont.actualizarFeriado(feriado);
	}
	
	public boolean eliminarFeriado(Feriado feriado) throws ClassNotFoundException {
		FeriadoCont ferCont = new FeriadoCont();
		return ferCont.eliminarRegistro(feriado);
	}
	
	public boolean actualizarHistoriaDisciplinaria(PerHistoDisip historia) throws ClassNotFoundException {
		PerHistoDisipCont histiriaCont = new PerHistoDisipCont();
		return histiriaCont.actualizarHistorial(historia);
	}

	public boolean eliminarHistoriaDisciplinaria(PerHistoDisip historia) throws ClassNotFoundException {
		PerHistoDisipCont historiaCont = new PerHistoDisipCont();
		return historiaCont.update(historia);
	}

	// ##############################################
	// ############## ADMINISTRADORES ###############
	// ##############################################

	public Administrador validarIngreso(String login, String password) throws ClassNotFoundException {
		AdministradorCont administradorCont = new AdministradorCont();
		password = Encrypt.getStringMessageDigest(password, Encrypt.MD5);
		return administradorCont.getById(login, password);
	}

	public boolean actualizarAdministrador(Administrador administrador) throws ClassNotFoundException {
		AdministradorCont administradorCont = new AdministradorCont();
		return administradorCont.update(administrador);
	}

	// ###########################################
	// ############## EVALUACIONES ###############
	// ###########################################

	public ArrayList<Evaluacion> obtenerEvaluaciones(String cedula) throws ClassNotFoundException {
		PerEvaCont evaCont = new PerEvaCont();
		return evaCont.getByCedula(cedula);
	}
	
	// ###########################################
	// ############# HISTORIAL DEPENDENCIA #######
	// ###########################################
	public ArrayList<PerDep> obtenerHistorialDependencia(String cedula) throws ClassNotFoundException {
		//Llamar a la funcion de la base de datos
		PerDepCont DepCont = new PerDepCont();
		return DepCont.getHistorialDep(cedula);
	}
	// ###########################################
	// ############# HISTORIAL OFICINA #######
	// ###########################################
	public ArrayList<PerOfi> obtenerHistorialOficina(String cedula) throws ClassNotFoundException {
		//Llamar a la funcion de la base de datos
		PerOfiCont ofiCont = new PerOfiCont();
		return ofiCont.getHistorialOfi(cedula);
	}
	
	// ###########################################
	// ############# HISTORIAL TIPO PERSONAL #######
	// ###########################################
	public ArrayList<PerTipos> obtenerHistorialTipoPersonal(String cedula) throws ClassNotFoundException {
		//Llamar a la funcion de la base de datos
		PerTipoCont tipoCont = new PerTipoCont();
		return tipoCont.getHistorialTipo(cedula);
	}

	// ###########################################
	// ############# HISTORIAL TURNOS #######
	// ###########################################
	public ArrayList<PerTur> obtenerHistorialTurnos(String cedula) throws ClassNotFoundException {
		//Llamar a la funcion de la base de datos
		PerTurCont turCont = new PerTurCont();
		return turCont.getHistorialTurno(cedula);
	}
	
	// ###########################################
	// ############# HISTORIAL CATEGORIA #######
	// ###########################################
	public ArrayList<PerCat> obtenerHistorialCategoria(String cedula) throws ClassNotFoundException {
		//Llamar a la funcion de la base de datos
		PerCatCont catCont = new PerCatCont();
		return catCont.getHistorialCategoria(cedula);
	}
	
	// ###########################################
	// ############# HISTORIAL CATEGORIA #######
	// ###########################################
	public ArrayList<PerDias> obtenerHistorialDias(String cedula) throws ClassNotFoundException {
		//Llamar a la funcion de la base de datos
		PerDiaCont diaCont = new PerDiaCont();
		return diaCont.getHistorialDias(cedula);
	}

	// #######################################
	// ############### HORARIOS ##############
	// #######################################

	public boolean actualizarTurnoPersonal(PerTur nuevoTurno, String opcion)
			throws ClassNotFoundException, SQLException {
		PerTurCont perCont = new PerTurCont();
		if (opcion.equals("I")) {
			// insercion de nuevo turno
			return perCont.actualizarTurnoTransaccion(nuevoTurno);
		} else if (opcion.equals("A")) {
			// actualizacion de una observacion
			return perCont.update(nuevoTurno);
		} else {
			return false;
		}
	}

	// METODOS OBSOLETOS - FALTA ACTUALIZAR

	public ArrayList<Turno> obtenerHorarios() throws ClassNotFoundException {
		TurnoController tiposCont = new TurnoController();
		return tiposCont.getAll();
	}

	public Turno obtenerTurno(int id) throws ClassNotFoundException {
		TurnoController turnoCont = new TurnoController();
		return turnoCont.getById(id);
	}

	// #############################################
	// ############ TIPOS JUSTIFICATIVOS ###########
	// #############################################

	// METODOS OBSOLETOS - FALTA ACTUALIZAR
	public ArrayList<TipoJustificativo> obtenerTiposJustificativos() throws ClassNotFoundException {
		TipoJustificativoController tiposCont = new TipoJustificativoController();
		return tiposCont.getAll();
	}

	// #############################################
	// ############### OFICINAS ####################
	// #############################################

	public boolean actualizarOficinaPersonal(PerOfi nuevaOficina, String opcion)
			throws ClassNotFoundException, SQLException {
		PerOfiCont perCont = new PerOfiCont();
		if (opcion.equals("I")) {
			// insercion de nueva condicion
			return perCont.actualizarOficinaTransaccion(nuevaOficina);
		} else if (opcion.equals("A")) {
			// actualizacion de una observacion
			return perCont.update(nuevaOficina);
		} else {
			return false;
		}
	}

	public ArrayList<Oficina> obtenerOficinas() throws ClassNotFoundException {
		OficinaCont oficinasCont = new OficinaCont();
		return oficinasCont.getAll();
	}

	public Oficina obtenerOficina(String codigo) throws ClassNotFoundException {
		OficinaCont oficinasCont = new OficinaCont();
		return oficinasCont.getByCodigo(codigo);
	}
	
	//----------------------------------------------------------------
	public ArrayList<Categoria> obtenerCategorias() throws ClassNotFoundException{
		CategoriaCont categoriaCont = new CategoriaCont();
		return categoriaCont.getAll();
	}

	public boolean actualizarOficinaPersonal(PerOfi nuevaOficina) throws ClassNotFoundException, SQLException {
		PerOfiCont oficinaCont = new PerOfiCont();
		return oficinaCont.actualizarOficinaTransaccion(nuevaOficina);
	}

	// #############################################
	// ############### CARGOS ######################
	// #############################################

	public boolean actualizarCargoPersonal(PerCar nuevoCargo, String opcion)
			throws ClassNotFoundException, SQLException {
		PerCarCont perCont = new PerCarCont();
		if (opcion.equals("I")) {
			// insercion de nueva condicion
			return perCont.actualizarCargoTransaccion(nuevoCargo);
		} else if (opcion.equals("A")) {
			// actualizacion de una observacion
			return perCont.update(nuevoCargo);
		} else {
			return false;
		}
	}

	public ArrayList<Cargo> obtenerCargos() throws ClassNotFoundException {
		CargoCont cargosCont = new CargoCont();
		return cargosCont.getAll();
	}

	public Cargo obtenerCargo(String codigo) throws ClassNotFoundException {
		CargoCont cargoCont = new CargoCont();
		return cargoCont.getByCodigo(codigo);
	}

	// #############################################
	// ############# DEPENDENCIAS ##################
	// #############################################

	public boolean actualizarDependenciaPersonal(PerDep nuevaDependencia, String opcion)
			throws ClassNotFoundException, SQLException {
		PerDepCont perCont = new PerDepCont();
		if (opcion.equals("I")) {
			// insercion de nueva condicion
			return perCont.actualizarDependenciaTransaccion(nuevaDependencia);
		} else if (opcion.equals("A")) {
			// actualizacion de una observacion
			return perCont.update(nuevaDependencia);
		} else {
			return false;
		}
	}

	// METODOS OBSOLETOS - FALTA ACTUALIZAR
	public ArrayList<Dependencia> obtenerDependencias() throws ClassNotFoundException {
		DependenciaController dependenciaCont = new DependenciaController();
		return dependenciaCont.getAll();
	}

	public Dependencia obtenerDependencia(String codigo) throws ClassNotFoundException {
		DependenciaController depCont = new DependenciaController();
		return depCont.getByCodigo(codigo);
	}
	
	
	// #############################################
	// ############# CATEGORIAS   ##################
	// #############################################
	public Categoria obtenerCategoria(String codigo) throws ClassNotFoundException{
		CategoriaCont cat = new CategoriaCont();
		return cat.getByCodigo(codigo);
	}
	
	public boolean actualizarCategoriaPersonal(PerCat nuevaCategoria, String opcion) throws ClassNotFoundException, SQLException {
		PerCatCont perCont = new PerCatCont();
		if (opcion.equals("I")) {
			
			// insercion de nueva condicion
			return perCont.actualizarCategoriaTransaccion(nuevaCategoria);
		} else if (opcion.equals("A")) {
			
			// actualizacion de una observacion
			return perCont.update(nuevaCategoria);
		} else {
			return false;
		}
	}
	
	
	// ###############################################
	// ########## TIPOS DE PERSONALES ################
	// ###############################################

	public boolean actualizarCondicionPersonal(PerCon nuevaCondicion, String opcion)
			throws ClassNotFoundException, SQLException {
		PerConCont perCont = new PerConCont();
		if (opcion.equals("I")) {
			// insercion de nueva condicion
			return perCont.actualizarCondicionTransaccion(nuevaCondicion);
		} else if (opcion.equals("A")) {
			// actualizacion de una observacion
			return perCont.update(nuevaCondicion);
		} else {
			return false;
		}
	}

	// METODOS OBSOLETOS - FALTA ACTUALIZAR

	public ArrayList<TipoPersonal> obtenerCondiciones() throws ClassNotFoundException {
		TipoPersonalController tipoCont = new TipoPersonalController();
		return tipoCont.getAll();
	}

	public TipoPersonal obtenerCondicionById(String id) throws ClassNotFoundException {
		TipoPersonalController tipoCont = new TipoPersonalController();
		return tipoCont.getById(Integer.parseInt(id));
	}

	// ################################################
	// ################ REPORTES ######################
	// ################################################
//	public void imprimirPersonales() throws ClassNotFoundException, ParseException {
//		CredencialCont credCont = new CredencialCont();
//		ImprimirReporte reporte = new ImprimirReporte();
//		reporte.imprimirPersonales();
//	}

	public void imprimirCredencial(String cedula) throws ClassNotFoundException, ParseException {
		CredencialCont credCont = new CredencialCont();
		ImprimirReporte reporte = new ImprimirReporte();
		reporte.imprimirCredencial(credCont.getById(cedula));
	}

	public boolean imprimirFichaAsistencia(String cedula, int mes, int anho)
			throws ClassNotFoundException, ParseException {
		ImprimirReporte reporte = new ImprimirReporte();
		PersonalCont fichaCont = new PersonalCont();
		return reporte.imprimirFichaAsistencia(fichaCont.getFichaMensual(cedula, mes, anho));
	}

	public boolean imprimirFichaAsistencia(String cedula, Date fechaInicio, Date fechaFin)
			throws ClassNotFoundException, ParseException {
		ImprimirReporte reporte = new ImprimirReporte();
		PersonalCont perCont = new PersonalCont();
		return reporte.imprimirFichaAsistencia(perCont.getFichaRango(cedula, fechaInicio, fechaFin));
	}

	public boolean imprimirFichaAsistenciaLista(ArrayList<FichaAsistencia> fichas) throws ClassNotFoundException {
		ImprimirReporte reporte = new ImprimirReporte();
		return reporte.imprimirFichaAsistenciaLista(fichas);
	}

	public boolean imprimirFaltasDisciplinarias(String cedula, int mes, int anho)
			throws ClassNotFoundException, ParseException {
		ImprimirReporte reporte = new ImprimirReporte();
		PersonalCont perCont = new PersonalCont();
		return reporte.imprimirFaltasDisciplinarias(perCont.getInasistenciaSinJustificarByCedula(cedula, mes, anho));
	}

	public boolean imprimirFaltasDisciplinarias(String cedula, Date fechaInicio, Date fechaFin)
			throws ClassNotFoundException, ParseException {
		ImprimirReporte reporte = new ImprimirReporte();
		PersonalCont perCont = new PersonalCont();
		return reporte.imprimirFaltasDisciplinarias(
				perCont.getInasistenciaSinJustificarByCedula(cedula, fechaInicio, fechaFin));
	}

	public boolean imprimirFaltasDisciplinarias(int mes, int anho) throws ClassNotFoundException, ParseException {
		ImprimirReporte reporte = new ImprimirReporte();
		PersonalCont perCont = new PersonalCont();
		return reporte.imprimirFaltasDisciplinarias(perCont.getInasistenciaSinJustificar(mes, anho));
	}

	public boolean imprimirFaltasDisciplinarias(Date fechaInicio, Date fechaFin)
			throws ClassNotFoundException, ParseException {
		ImprimirReporte reporte = new ImprimirReporte();
		PersonalCont perCont = new PersonalCont();
		return reporte.imprimirFaltasDisciplinarias(perCont.getInasistenciaSinJustificar(fechaInicio, fechaFin));
	}

	public boolean imprimirJustificaciones(String cedula, int mes, int anho)
			throws ClassNotFoundException, ParseException {
		ImprimirReporte reporte = new ImprimirReporte();
		PersonalCont perCont = new PersonalCont();
		JustificacionPersonal jus = perCont.getHistorial(cedula, mes, anho);
		if (jus.isTieneRegistros()) {
			return reporte.imprimirJustificaciones(jus);
		} else {
			return false;
		}
	}

	public boolean imprimirJustificaciones(String cedula, Date fechaInicio, Date fechaFin)
			throws ClassNotFoundException, ParseException {

		ImprimirReporte reporte = new ImprimirReporte();
		PersonalCont perCont = new PersonalCont();
		JustificacionPersonal jus = perCont.getHistorial(cedula, fechaInicio, fechaFin);
		if (jus.isTieneRegistros()) {
			return reporte.imprimirJustificaciones(jus);
		} else {
			return false;
		}
	}

	public boolean imprimirJustificacionesByOficina(String oficina, int mes, int anho)
			throws ClassNotFoundException, ParseException {

		ImprimirReporte reporte = new ImprimirReporte();
		PersonalCont perCont = new PersonalCont();
		JustificacionLista jus = perCont.getHistorialByOficina(oficina, mes, anho);
	
		if (jus.isTieneRegistros()) {
			return reporte.imprimirJustificaciones(jus);
		} else {
			return false;
		}
	}

	public boolean imprimirJustificacionesByOficina(String oficina, Date fechaInicio, Date fechaFin)
			throws ClassNotFoundException, ParseException {
		ImprimirReporte reporte = new ImprimirReporte();
		PersonalCont perCont = new PersonalCont();
		JustificacionLista jus = perCont.getHistorialByOficina(oficina, fechaInicio, fechaFin);
		if (jus.isTieneRegistros()) {
			return reporte.imprimirJustificaciones(jus);
		} else {
			return false;
		}
	}
	//-----------------------------------------------------------------------
	public boolean imprimirPersonalABC() throws ClassNotFoundException, ParseException {
		ImprimirReporte reporte = new ImprimirReporte();
		PersonalCont personal = new PersonalCont();
		return reporte.imprimirPersonalesABC(personal.getABC());
	}
	
	public boolean imprimirPersonalCategorias(String codigo) throws ClassNotFoundException, ParseException {
		ImprimirReporte reporte = new ImprimirReporte();
		PersonalCont personal = new PersonalCont();
		if (codigo == "TODOS"){
			return reporte.imprimirPersonalesCat(personal.getAllPeronalCategoria());
		}else{
			return reporte.imprimirPersonalesCat(personal.getPeronalCategoria(codigo));
		}
	}
	
	public boolean imprimirPersonalCondicion(String codigo) throws ClassNotFoundException, ParseException {
		ImprimirReporte reporte = new ImprimirReporte();
		PersonalCont personal = new PersonalCont();
		if (codigo == "TODOS"){
			return reporte.imprimirPersonalesCondicion(personal.getAllPeronalCondicion(codigo));
		}else{
			return reporte.imprimirPersonalesCondicion(personal.getPersonalCondicion(codigo));
		}
	}
	public boolean imprimirPersonalDependencia(String codigo) throws ClassNotFoundException, ParseException {
		ImprimirReporte reporte = new ImprimirReporte();
		PersonalCont personal = new PersonalCont();
		if (codigo == "TODOS"){
			return reporte.imprimirPersonalesDependencia(personal.getAllPeronalDependencia(codigo));
		}else{
			return reporte.imprimirPersonalesDependencia(personal.getPersonalDependencia(codigo));
		}
	}
	public boolean imprimirPersonalOficina(String codigo) throws ClassNotFoundException, ParseException {
		ImprimirReporte reporte = new ImprimirReporte();
		PersonalCont personal = new PersonalCont();
		if (codigo == "TODOS"){
			return reporte.imprimirPersonalesOficina(personal.getAllPersonalOficina(codigo));
		}else{
			System.out.println("TODOS"+codigo);
			return reporte.imprimirPersonalesOficina(personal.getPersonalOficina(codigo));
		}
	}
	public boolean imprimirPersonalSexo(String codigo) throws ClassNotFoundException, ParseException {
		ImprimirReporte reporte = new ImprimirReporte();
		PersonalCont personal = new PersonalCont();
		if (codigo == "TODOS"){
			return reporte.imprimirPersonalesSexo(personal.getAllPeronalporSexo(codigo));
		}else{
			return reporte.imprimirPersonalesSexo(personal.getPeronalporSexo(codigo));
		}
	}
}
