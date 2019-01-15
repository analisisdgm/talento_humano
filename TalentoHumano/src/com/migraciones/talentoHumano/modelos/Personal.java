package com.migraciones.talentoHumano.modelos;

import java.sql.Time;
import java.util.Date;

import com.migraciones.talentoHumano.utilities.GlobalUtil;

public class Personal {
	// DATOS PERSONALES DEL FUNCIONARIO
	private String cedula;
	private String nombres;
	private String apellidos;
	private Date fechaNacimiento;
	private String sexo;
	private String estadoCivil;
	private String telefonos;
	private String correo;
	private String domicilio;
	private String observacion;
	private String estado;
	private String administradorAlta;
	// CONDICION DEL FUNCIONARIO
	private int codCondicion;
	private String condicion;
	private String condObservacion;
	private Date condFechaIni;
	// DEPENDENCIA DEL FUNCIONARIO
	private String codDependencia;
	private String dependencia;
	private String depObservacion;
	private Date depFechaIni;
	// OFICINA DEL FUNCIONARIO
	private String codOficina;
	private String oficina;
	private String ofiObservacion;
	private Date ofiFechaIni;
	// HORARIO DEL FUNCIONARIO
	private int codHorario;
	private Time horEntrada;
	private Time horSalida;
	private String horObservacion;
	private Date horFechaIni;
	// CARGO DEL FUNCIONARIO
	private int codCargo;
	private String Cargo;
	private String carObservacion;
	private Date carFechaIni;
	private Date carFechaFin;
	// CATEGORIA DEL FUNCIONARIO
	private String codCategoria;
	private String categoria;
	private String catObservacion;
	private Date catFechaIni;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public String getCondObservacion() {
		return condObservacion;
	}

	public void setCondObservacion(String condObservacion) {
		this.condObservacion = condObservacion;
	}

	public Date getCondFechaIni() {
		return condFechaIni;
	}

	public void setCondFechaIni(Date condFechaIni) {
		this.condFechaIni = condFechaIni;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public String getDepObservacion() {
		return depObservacion;
	}

	public void setDepObservacion(String depObservacion) {
		this.depObservacion = depObservacion;
	}

	public Date getDepFechaIni() {
		return depFechaIni;
	}

	public void setDepFechaIni(Date depFechaIni) {
		this.depFechaIni = depFechaIni;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public String getOfiObservacion() {
		return ofiObservacion;
	}

	public void setOfiObservacion(String ofiObservacion) {
		this.ofiObservacion = ofiObservacion;
	}

	public Date getOfiFechaIni() {
		return ofiFechaIni;
	}

	public void setOfiFechaIni(Date ofiFechaIni) {
		this.ofiFechaIni = ofiFechaIni;
	}

	public Time getHorEntrada() {
		return horEntrada;
	}

	public void setHorEntrada(Time horEntrada) {
		this.horEntrada = horEntrada;
	}

	public Time getHorSalida() {
		return horSalida;
	}

	public void setHorSalida(Time horSalida) {
		this.horSalida = horSalida;
	}

	public String getHorObservacion() {
		return horObservacion;
	}

	public void setHorObservacion(String horObservacion) {
		this.horObservacion = horObservacion;
	}

	public Date getHorFechaIni() {
		return horFechaIni;
	}

	public void setHorFechaIni(Date horFechaIni) {
		this.horFechaIni = horFechaIni;
	}

	public int getCodCondicion() {
		return codCondicion;
	}

	public void setCodCondicion(int codCondicion) {
		this.codCondicion = codCondicion;
	}

	public String getCodDependencia() {
		return codDependencia;
	}

	public void setCodDependencia(String codDependencia) {
		this.codDependencia = codDependencia;
	}

	public String getCodOficina() {
		return codOficina;
	}

	public void setCodOficina(String codOficina) {
		this.codOficina = codOficina;
	}

	public int getCodHorario() {
		return codHorario;
	}

	public void setCodHorario(int codHorario) {
		this.codHorario = codHorario;
	}

	public int getCodCargo() {
		return codCargo;
	}

	public void setCodCargo(int codCargo) {
		this.codCargo = codCargo;
	}

	public String getCargo() {
		return Cargo;
	}

	public void setCargo(String cargo) {
		Cargo = cargo;
	}

	public String getCarObservacion() {
		return carObservacion;
	}

	public void setCarObservacion(String carObservacion) {
		this.carObservacion = carObservacion;
	}

	public Date getCarFechaIni() {
		return carFechaIni;
	}

	public void setCarFechaIni(Date carFechaIni) {
		this.carFechaIni = carFechaIni;
	}

	public Date getCarFechaFin() {
		return carFechaFin;
	}

	public void setCarFechaFin(Date carFechaFin) {
		this.carFechaFin = carFechaFin;
	}

	public String getAdministradorAlta() {
		return administradorAlta;
	}

	public void setAdministradorAlta(String administradorAlta) {
		this.administradorAlta = administradorAlta;
	}

	public String getRutaImagen() {
		String rutaImagen = GlobalUtil.PATH_CARNET + String.format("%010d", Integer.parseInt(this.cedula)) + ".jpg";
		return rutaImagen;
	}

}
