package Pruebas;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.RepaintManager;

import ISIS.Code.Class.DialogoEdifComunitarios;
import ISIS.Code.Class.DialogoEdificaciones;
import ISIS.Code.Class.MainClass;
import ISIS.Code.Matriz.Celda;
import ISIS.Code.Matriz.Construccion;
import ISIS.Code.Matriz.Mapa;
import ISIS.Code.Matriz.ModeloMapa;
import ISIS.Code.Panels.StartPanel;

/**
 * Simple demo that uses java.util.Timer to schedule a task to execute once 5
 * seconds have passed.
 */

public class Reminder {
	Timer timer;
	int i;
	int j;
	// StartPanel panel= new StartPanel();
//	Mapa mapa = new Mapa();
	

	public Reminder(int seconds,int axisX,int axisY) {
		timer = new Timer();
		timer.schedule(new RemindTask(), seconds * 1000);
//		StartPanel.soundConstruyendo.loop();
		i=axisX;
		j=axisY;
		StartPanel.click2.stop();
	}

	class RemindTask extends TimerTask {
		public void run() {
			System.out.format("Time's up!%n");
			// mapa.matriz.escribirDato(52, 50, null);
//			Mapa.setTile_construyendo(true);
//			
//			Mapa.setAuxXs(Mapa.getXs());
//			Mapa.setAuxYs(Mapa.getYs());
//			System.err.println(Mapa.getAuxXs());

//			
//			StartPanel.click2.play();
//			timer.cancel(); // Terminate the timer thread
			
			
//			ModeloMapa mapa= new ModeloMapa();
//			System.out.println(mapa.leerDato(52, 50).getConstRef().getID());
//			Mapa map= new Mapa();
			System.out.println("La celda Lista es: "+i+","+j);
			Mapa.setAuxXs(i);
			Mapa.setAuxYs(j);
			i=0;
			j=0;
			
			StartPanel.soundFinal.play();
			
		}
	}

//	public static void main(String args[]) {
//		new Reminder(10);
//		System.out.format("Task scheduled.%n");
//	}
}
