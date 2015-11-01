package ISIS.Code.Frame;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.edisoncor.*;
import org.edisoncor.gui.Dialogo;
import org.edisoncor.gui.panel.NewJFrame;
import org.edisoncor.gui.panel.PanelGlassGaussian;

import ISIS.Code.Class.Cargando;
import ISIS.Code.Class.Principal;
import ISIS.Code.Matriz.Mapa;
import ISIS.Code.Panels.*;
import ISIS.Code.Class.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private ISIS.Code.Class.DialogoEdificaciones dialogo;
	private static final long serialVersionUID = 1L;
	StartPanel starPanel = new StartPanel();
	MapSelectionPanel mapSelectionPanel = new MapSelectionPanel();
	public static MayorSelectionPanel mayorSelectionPanel = new MayorSelectionPanel();
	LoadGamePanel loadGamePanel = new LoadGamePanel();
	JPanel CurrentPanel = starPanel;
	DiamondGrid diamond= new DiamondGrid();
	Mapa gamePanel = new Mapa();
	//Cargando cargando = new Cargando();
	Principal font = new Principal();
	PanelBienvenido panelbienvenido = new PanelBienvenido();
	PanelAtencion panelatencion = new PanelAtencion();
	private JLabel label;

	PanelNiveles panelNiveles = new PanelNiveles();
	public static TransicionPanel transicion = new TransicionPanel();

	public MainFrame() {
		// TODO Auto-generated constructor stub
		GraphicsDevice grafica = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		transicion.addPanel(panelatencion);
		transicion.addPanel(starPanel);
		transicion.addPanel(mayorSelectionPanel);
		transicion.addPanel(mapSelectionPanel);
		// transicion.addPanel(panelFinal);
		//transicion.addPanel(cargando);
		//transicion.addPanel(panelbienvenido);
		// transicion.addPanel(font);
//		transicion.addPanel(gamePanel);

		
		Events();
		this.add(transicion);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setUndecorated(true);
		// grafica.setFullScreenWindow(this);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void Events() {
		// TODO Auto-generated method stub
		starPanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getActionCommand().equals("MayorPanel")) {
					transicion.derecha();
				} else if (arg0.getActionCommand().equals("LoadGamePanel")) {
					
				} else if (arg0.getActionCommand().equals("ExitGame")) {
					dispose();

				}
			}

		});
		

		mayorSelectionPanel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getActionCommand().equals("PanelNext")) {
					transicion.derecha();
				} else if (arg0.getActionCommand().equals("PanelPrev")) {
					transicion.izquierda();
				}
			}

		});
		//
		mapSelectionPanel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getActionCommand().equals("PanelNext")) {
					Cargando cargando= new Cargando();
					transicion.addPanel(cargando);
//					transicion.addPanel(panelbienvenido);
//					transicion.addPanel(gamePanel);
//					System.out.println(mayorSelectionPanel.getAlcaldeSeleccionado());
					
					transicion.derecha();
				} else if (arg0.getActionCommand().equals("PanelPrev")) {
					transicion.izquierda();

				}
			}

		});
		
		panelbienvenido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getActionCommand().equals("NextPanel")) {
					transicion.addPanel(gamePanel);
					transicion.izquierda();
				}

			}
		});


//	gamePanel.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				if (arg0.getActionCommand().equals("Edificaciones")) {
//					ISIS.Code.Class.PanelGlassGaussian glass = new ISIS.Code.Class.PanelGlassGaussian(
//							MainFrame.this, 4);
//					setGlassPane(glass);
//					getGlassPane().setVisible(true);
//					dialogo = new ISIS.Code.Class.Dialogo(MainFrame.this, true);
//					dialogo.setLocationRelativeTo(null);
//					dialogo.setVisible(true);
//					getGlassPane().setVisible(false);
//					System.out.println("algo");
//				}
//
//			}
//		});



		panelatencion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getActionCommand().equals("NextPanel")) {
					transicion.derecha();
				}
			}

		});

	}

	public void ChangePanel() {
		// TODO Auto-generated method stub
		// this.remove(currentPanel);
		// this.add(newPanel);
		// this.setVisible(true);
		// this.revalidate();
		// this.repaint();
		// transicion.derecha();

		// transicion.derecha();
		// repaint();
		ISIS.Code.Class.PanelGlassGaussian glass = new ISIS.Code.Class.PanelGlassGaussian(
				MainFrame.this, 4);
		setGlassPane(glass);
		getGlassPane().setVisible(true);
		dialogo = new ISIS.Code.Class.DialogoEdificaciones(MainFrame.this, true);
		dialogo.setLocationRelativeTo(null);
		dialogo.setVisible(true);
		getGlassPane().setVisible(false);
		System.out.println("algo");

	}

	public static void main(String[] args) {

		new MainFrame();
	}
}
