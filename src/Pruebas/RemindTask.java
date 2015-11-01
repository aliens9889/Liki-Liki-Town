package Pruebas;

import java.util.TimerTask;

import ISIS.Code.Matriz.Mapa;
import ISIS.Code.Matriz.ModeloMapa;

public class RemindTask extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ModeloMapa mapa= new ModeloMapa();
		System.out.println(mapa.leerDato(52, 50).getConstRef().getID());

	}

}
