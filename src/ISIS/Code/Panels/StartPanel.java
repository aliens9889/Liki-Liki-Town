/**
 * 
 */
package ISIS.Code.Panels;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import org.edisoncor.gui.panel.PanelGlassGaussian;

import ISIS.Code.Class.Button;
import ISIS.Code.Class.MainClass;
import ISIS.Code.Class.PanelCurves;
import Pruebas.Sonidos;

/**
 * @author ISIS
 * 
 */
public class StartPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EventListenerList eventListenerList = new EventListenerList();
	private org.edisoncor.gui.button.ButtonSeven btnNewGame;
	private org.edisoncor.gui.button.ButtonSeven btnLoadGame;
	private org.edisoncor.gui.button.ButtonSeven btnExitGame;
	private JLabel lblBackground1, lblBackground;
	public static AudioClip soundFondo;
	public static AudioClip click;
	public static AudioClip click2;
	public static AudioClip soundfinish;
	public static AudioClip soundFinal;
	public static AudioClip soundStadium;
	private PanelCurves panelCurves1;
	public Sonidos sonidos = new Sonidos();

	public StartPanel() {
		// TODO Auto-generated constructor stub
		initComponents();

	}

	private void initComponents() {
		// TODO Auto-generated method stub

		btnNewGame = new org.edisoncor.gui.button.ButtonSeven();
		btnLoadGame = new org.edisoncor.gui.button.ButtonSeven();
		btnExitGame = new org.edisoncor.gui.button.ButtonSeven();
		lblBackground = new JLabel();
		lblBackground1 = new JLabel();
		panelCurves1 = new PanelCurves();
		URL urlClick = MainClass.class.getResource("/ISIS/button.wav");
		URL urlClick2 = MainClass.class.getResource("/ISIS/Finish.wav");
		URL urlClick3 = MainClass.class.getResource("/ISIS/construyendo.wav");
		URL urlClick4 = MainClass.class.getResource("/ISIS/construyendo.wav");
//		URL urlClick5 = MainClass.class.getResource("/ISIS/stadium.wav");
		URL urlClick6 = MainClass.class.getResource("/ISIS/soundrain.wav");
		click = Applet.newAudioClip(urlClick);
		click2 = Applet.newAudioClip(urlClick2);
		soundfinish = Applet.newAudioClip(urlClick3);
		soundFinal = Applet.newAudioClip(urlClick4);
//		soundStadium= Applet.newAudioClip(urlClick5);
		soundFondo= Applet.newAudioClip(urlClick6);

		setMaximumSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		setLayout(null);

		add(panelCurves1);
		panelCurves1.setBounds(0, 0, Toolkit.getDefaultToolkit()
				.getScreenSize().getSize().width, Toolkit.getDefaultToolkit()
				.getScreenSize().getSize().height);
		btnNewGame.setBackground(new java.awt.Color(255, 255, 0));
		btnNewGame.setText("Nuevo Juego");
		btnNewGame.setToolTipText("");
		btnNewGame.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				fireActionEvent(new ActionEvent(this, 0, "MayorPanel"));
				sonidos.click.play();

			}
		});
		btnNewGame.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
//				sonidos.click.play();
				super.mouseMoved(e);
			}
		});

		add(btnNewGame);
		btnNewGame.setBounds(Toolkit.getDefaultToolkit()
				.getScreenSize().getSize().width-150, Toolkit.getDefaultToolkit()
				.getScreenSize().getSize().height-200, 150, 35);
		btnLoadGame.setBackground(new java.awt.Color(0, 0, 255));
		btnLoadGame.setText("Cargar Juego");
		btnLoadGame.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				fireActionEvent(new ActionEvent(this, 0, "LoadGamePanel"));
				sonidos.click.play();

			}
		});
		btnLoadGame.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
//				click2.play();
				super.mouseMoved(e);
			}
		});
		btnLoadGame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLoadGameActionPerformed(evt);
			}

		});

		add(btnLoadGame);
		btnLoadGame.setBounds(Toolkit.getDefaultToolkit()
				.getScreenSize().getSize().width-150, Toolkit.getDefaultToolkit()
				.getScreenSize().getSize().height-150, 150, 35);
		btnExitGame.setBackground(new java.awt.Color(255, 0, 0));
		btnExitGame.setText("Salir");
		btnExitGame.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				fireActionEvent(new ActionEvent(this, 0, "ExitGame"));
				sonidos.click.play();

			}
		});

		add(btnExitGame);
		btnExitGame.setBounds(Toolkit.getDefaultToolkit()
				.getScreenSize().getSize().width-150, Toolkit.getDefaultToolkit()
				.getScreenSize().getSize().height-100, 150, 35);
		btnExitGame.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				click2.play();
				super.mouseMoved(e);
			}
		});

		lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/ISIS/Resource/Backgrounds/BackHome.jpg"))); // NOI18N
		add(lblBackground);
		lblBackground.setBounds(0, 0, Toolkit.getDefaultToolkit()
				.getScreenSize().getSize().width, Toolkit.getDefaultToolkit()
				.getScreenSize().getSize().height);
		
	}

	public void addActionListener(ActionListener listener) {
		eventListenerList.add(ActionListener.class, listener);
	}

	public void removeActionListener(ActionListener listener) {
		eventListenerList.remove(ActionListener.class, listener);
	}

	public ActionListener[] getActionListeners() {
		return eventListenerList.getListeners(ActionListener.class);
	}

	private void fireActionEvent(ActionEvent evt) {
		ActionListener[] actionListeners = getActionListeners();

		for (ActionListener actionListener : actionListeners) {
			actionListener.actionPerformed(evt);
		}
	}

	private void btnLoadGameActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		// ISIS.Code.Class.PanelGlassGaussian glass= new
		// ISIS.Code.Class.PanelGlassGaussian(dialogo, 8);
		// System.out.println(spnGaussian.getValue());
		// setGlassPane(glass);
		// getGlassPane().setVisible(true);
		// dialogo = new Dialogo(parent, modal)
		// dialogo.setVisible(true);
		// //dialogo.setUndecorated(false);
		// // panelNice1.setVisible(true);
		// getGlassPane().setVisible(false);

	}

}
