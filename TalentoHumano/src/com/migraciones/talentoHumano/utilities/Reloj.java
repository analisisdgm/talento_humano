package com.migraciones.talentoHumano.utilities;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JLabel;

public class Reloj extends JLabel implements Runnable {

	private static final long serialVersionUID = 7841077895172874182L;
	@SuppressWarnings("unused")
	private String dia, mes, anho, hora, minutos, segundos;
	private Calendar calendario = new GregorianCalendar();
	Thread hilo;

	public Reloj(int x, int y, int p, int p1) { // Constructor
		setBounds(x, y, p, p1);
		hilo = new Thread(this);
		hilo.start();

	} // fin constructor

	@Override
	public void run() {
		Thread ct = Thread.currentThread();
		while (ct == hilo) {
			try {
				actualiza();
				int mesE;
				mesE = Integer.valueOf(mes) + 1;

				setText("<html><center>" + dia + " / " + mesE + " / " + anho);
				// + "<br>" + hora + ":" + minutos + ":" + segundos);

				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				System.out.println(ex.getMessage());
			}

		}

	}

	public void actualiza() {

		Date fechaHoraActual = new Date();
		calendario.setTime(fechaHoraActual);

		hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
		minutos = calendario.get(Calendar.MINUTE) > 9 ? ""
				+ calendario.get(Calendar.MINUTE) : "0"
				+ calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND) > 9 ? ""
				+ calendario.get(Calendar.SECOND) : "0"
				+ calendario.get(Calendar.SECOND);
		dia = calendario.get(Calendar.DATE) > 9 ? ""
				+ calendario.get(Calendar.DATE) : "0"
				+ calendario.get(Calendar.DATE);
		mes = calendario.get(Calendar.MONTH) > 9 ? ""
				+ calendario.get(Calendar.MONTH) : "0"
				+ calendario.get(Calendar.MONTH);
		anho = calendario.get(Calendar.YEAR) > 9 ? ""
				+ calendario.get(Calendar.YEAR) : "0"
				+ calendario.get(Calendar.YEAR);
	}
}