package Pruebas;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.JFrame;

import ISIS.Code.Class.MainClass;

/**
 * 
 * @author ISIS
 */

public class Sonidos extends JFrame {
	public static AudioClip click;
	public static AudioClip soundFondo;

	public static AudioClip soundAhh;
	public static AudioClip soundAngry;
	public static AudioClip soundMmm;
	public static AudioClip soundTada;
	public static AudioClip soundAseo;


	public static void main(String[] args) {
		new Sonidos();
	}

	public Sonidos() {
		// Sonidos Botones
		URL urlClick = Sonidos.class.getResource("/ISIS/click.wav");
		click = Applet.newAudioClip(urlClick);
		
		// Sonidos Efectos Metereologicos
		
		// Sonidos Musica
		URL urlClick12 = Sonidos.class.getResource("/ISIS/soundFondo.wav");
		soundFondo = Applet.newAudioClip(urlClick12);
		
		// Sonidos Estado Animo
		URL urlClick7 = MainClass.class.getResource("/ISIS/Ahhh.wav");
		URL urlClick8 = MainClass.class.getResource("/ISIS/Angry.wav");
		URL urlClick9 = MainClass.class.getResource("/ISIS/mmm.wav");
		URL urlClick11 = MainClass.class.getResource("/ISIS/Ta Da.wav");
		soundAhh = Applet.newAudioClip(urlClick7);
		soundAngry = Applet.newAudioClip(urlClick8);
		soundMmm = Applet.newAudioClip(urlClick9);
		soundTada = Applet.newAudioClip(urlClick11);
		
		
		// Sonidos Construcciones
		URL urlClick13 = MainClass.class.getResource("/ISIS/aseoUrbano.wav");
		soundAseo = Applet.newAudioClip(urlClick13);
	}

}