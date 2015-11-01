package ISIS.Code.Frame;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.edisoncor.gui.panel.PanelGlassGaussian;

import ISIS.Code.Class.Cargando;
import ISIS.Code.Class.DialogoCarreteras;
import ISIS.Code.Class.DialogoEdifComunitarios;
import ISIS.Code.Class.DialogoEdificaciones;
import ISIS.Code.Class.DialogoIndustria;
import ISIS.Code.Class.DialogoServiciosPublicos;
import ISIS.Code.Class.TransicionPanel;
import ISIS.Code.Matriz.Estados;
import ISIS.Code.Matriz.Mapa;
import ISIS.Code.Panels.LoadPanel;
import ISIS.Code.Panels.MapSelectionPanel;
import ISIS.Code.Panels.MayorSelectionPanel;
import ISIS.Code.Panels.PanelAtencion;
import ISIS.Code.Panels.PanelBienvenido;
import ISIS.Code.Panels.PanelNiveles;
import ISIS.Code.Panels.PanelPausa;
import ISIS.Code.Panels.StartPanel;
import Pruebas.Sonidos;

public class MainMapa extends JFrame implements KeyListener {

	private PanelPausa panelPausa;
	private static final long serialVersionUID = 1L;
	StartPanel starPanel = new StartPanel();
	MapSelectionPanel mapSelectionPanel = new MapSelectionPanel();
	public static MayorSelectionPanel mayorSelectionPanel = new MayorSelectionPanel();
	LoadPanel loadPanel = new LoadPanel();
	JPanel CurrentPanel = starPanel;
	Mapa gamePanel = new Mapa();
	PanelBienvenido panelbienvenido = new PanelBienvenido();
	PanelAtencion panelatencion = new PanelAtencion();
	PanelNiveles panelNiveles = new PanelNiveles();
	Cargando cargando = new Cargando();
	Sonidos sonidos = new Sonidos();

	public static TransicionPanel transicion = new TransicionPanel();
	public double f = 0, aux = 0;

	public MainMapa() {
		// GraphicsDevice grafica = GraphicsEnvironment
		// .getLocalGraphicsEnvironment().getDefaultScreenDevice();
		// transicion.addPanel(panelatencion);
		transicion.addPanel(starPanel);
		transicion.addPanel(mayorSelectionPanel);
		transicion.addPanel(mapSelectionPanel);
		transicion.addPanel(loadPanel);
		transicion.addPanel(gamePanel);
		Events();

		this.add(transicion);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// System.out.println(Toolkit.getDefaultToolkit().getScreenSize()
		// .getHeight());
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().getSize());
		this.setUndecorated(true);
		// grafica.setF ullScreenWindow(this);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		addKeyListener(this);
		felicidad();
		iniciarBasura();
		Lluvia();
	}

	private void felicidad() {
		Timer timer = new Timer(5000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				gamePanel.estadisticas();
				if (f == 0) {
					f = gamePanel.getFelicidad();
					aux = f;
				}
				if (aux != gamePanel.getFelicidad()) {
					System.out.printf("Anterior: %.2f \n", aux);
					System.out.printf("Actual: %.2f \n",
							gamePanel.getFelicidad());

					f += (gamePanel.getFelicidad() - aux);
					aux = gamePanel.getFelicidad();
				}
				gamePanel.setFelicidad(0);
			}
		});
		timer.start();
		gamePanel.setFelicidadEstatica(f);
	}

	private void iniciarBasura() {

		Timer timer = new Timer(15000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gamePanel.getConstrucciones() > 0) {
					gamePanel.basura = true;
					repaint();
				}
			}
		});
		timer.start();

	}

	private void Lluvia() {

		Timer timer = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.lluvia = true;
			}
		});
		timer.start();

	}

	private void Events() {

		panelatencion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getActionCommand().equals("NextPanel"))
					transicion.derecha();
			}

		});

		starPanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("MayorPanel")) {
					transicion.derecha();
				} else if (e.getActionCommand().equals("LoadGamePanel")) {
					transicion.derecha(3);
				} else if (e.getActionCommand().equals("ExitGame")) {
					System.exit(0);

				}
			}

		});

		mayorSelectionPanel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("PanelNext")) {
					transicion.derecha();
					sonidos.click.play();
				} else if (e.getActionCommand().equals("PanelPrev")) {
					transicion.izquierda();
				}
			}

		});

		mapSelectionPanel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getActionCommand().equals("PanelNext")) {
					transicion.derecha(2);
					sonidos.click.play();
				} else if (arg0.getActionCommand().equals("PanelPrev")) {
					transicion.izquierda();
					sonidos.click.play();
				}
			}

		});

		// cargando.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		//
		// if (arg0.getActionCommand().equals("NextPanel"))
		// transicion.derecha();
		// }
		//
		// });

		panelbienvenido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getActionCommand().equals("NextPanel"))
					transicion.derecha();
			}

		});

		loadPanel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Prev")) {
					transicion.izquierda(3);
				} else if (e.getActionCommand().equals("Load")) {
					gamePanel.cargar(loadPanel.getAlcalde());
					transicion.derecha();
				}
			}
		});

		gamePanel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getActionCommand().equals("Edificaciones")) {
					ISIS.Code.Class.PanelGlassGaussian glass = new ISIS.Code.Class.PanelGlassGaussian(
							MainMapa.this, 4);
					setGlassPane(glass);
					getGlassPane().setVisible(true);
					DialogoEdificaciones dialogo = new DialogoEdificaciones(
							MainMapa.this, true);
					dialogo = new ISIS.Code.Class.DialogoEdificaciones(
							MainMapa.this, true);
					dialogo.setLocationRelativeTo(null);
					dialogo.setVisible(true);
					getGlassPane().setVisible(false);

					// Panel Gaussiano Carretera
				} else if (arg0.getActionCommand().equals("Carretera")) {
					PanelGlassGaussian glassCarretera = new PanelGlassGaussian(
							MainMapa.this, 4);
					setGlassPane(glassCarretera);
					getGlassPane().setVisible(true);
					DialogoCarreteras dialogo2 = new DialogoCarreteras(
							MainMapa.this, true);
					dialogo2.setLocationRelativeTo(null);
					dialogo2.setVisible(true);
					getGlassPane().setVisible(false);

				} else if (arg0.getActionCommand().equals("Servicios")) {
					PanelGlassGaussian glassServicios = new PanelGlassGaussian(
							MainMapa.this, 4);
					setGlassPane(glassServicios);
					getGlassPane().setVisible(true);
					DialogoServiciosPublicos dialogo3 = new DialogoServiciosPublicos(
							MainMapa.this, true);
					dialogo3.setLocationRelativeTo(null);
					dialogo3.setVisible(true);
					getGlassPane().setVisible(false);

				} else if (arg0.getActionCommand().equals("Industrias")) {
					PanelGlassGaussian glassIndustria = new PanelGlassGaussian(
							MainMapa.this, 4);
					setGlassPane(glassIndustria);
					getGlassPane().setVisible(true);
					DialogoIndustria dialogo4 = new DialogoIndustria(
							MainMapa.this, true);
					dialogo4.setLocationRelativeTo(null);
					dialogo4.setVisible(true);
					getGlassPane().setVisible(false);
				} else if (arg0.getActionCommand().equals("Comunitarios")) {
					PanelGlassGaussian glassComunitarios = new PanelGlassGaussian(
							MainMapa.this, 4);
					setGlassPane(glassComunitarios);
					getGlassPane().setVisible(true);
					DialogoEdifComunitarios dialogo4 = new DialogoEdifComunitarios(
							MainMapa.this, true);
					dialogo4.setLocationRelativeTo(null);
					dialogo4.setVisible(true);
					getGlassPane().setVisible(false);
				} else if (arg0.getActionCommand().equals("Pause")) {
					ISIS.Code.Class.PanelGlassGaussian glass = new ISIS.Code.Class.PanelGlassGaussian(
							MainMapa.this, 4);
					setGlassPane(glass);
					getGlassPane().setVisible(true);
					panelPausa = new PanelPausa(MainMapa.this, true);
					panelPausa.setLocationRelativeTo(null);
					panelPausa.setVisible(true);
					panelPausa.setUndecorated(true);
					getGlassPane().setVisible(false);
				}

			}

		});
	}

	public void ChangePanel() {
		// this.remove(currentPanel);
		// this.add(newPanel);
		// this.setVisible(true);
		// this.revalidate();
		// this.repaint();
		// transicion.derecha();

		// transicion.derecha();
		// repaint();

	}

	public static void main(String[] args) {

		new MainMapa();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 27) {
			gamePanel.setClick(false);
			DialogoEdificaciones.setSeleccionada(null);
			DialogoEdifComunitarios.setSeleccionada(null);
			DialogoServiciosPublicos.setSeleccionada(null);
			DialogoCarreteras.setSeleccionada(null);
			DialogoIndustria.setSeleccionada(null);
			Mapa.ESTADO = Estados.NAVEGACION;
		}

		if (e.getKeyCode() == 80) {
			// System.out.println("PAUSE");
			ISIS.Code.Class.PanelGlassGaussian glass = new ISIS.Code.Class.PanelGlassGaussian(
					MainMapa.this, 4);
			setGlassPane(glass);
			getGlassPane().setVisible(true);
			panelPausa = new PanelPausa(MainMapa.this, true);
			panelPausa.setLocationRelativeTo(null);
			panelPausa.setVisible(true);
			panelPausa.setUndecorated(true);
			getGlassPane().setVisible(false);
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}