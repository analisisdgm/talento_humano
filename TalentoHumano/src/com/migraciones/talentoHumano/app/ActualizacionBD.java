package com.migraciones.talentoHumano.app;

import java.sql.SQLException;
import java.util.ArrayList;

public class ActualizacionBD {
	static ArrayList<String> listaFuncionariosPg = new ArrayList<String>();

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		TalentoHumano sistema = new TalentoHumano();
		// sistema.cargarRegistrosMensual();
		sistema.cargarRegistrosCompleto();
	}

}
