/**
 * 
 */
package ISIS.Code.Panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import ISIS.Code.Frame.MainFrame;

/**
 * @author ISIS
 * 
 */
public class GamePanel extends JPanel implements MouseListener,
		MouseMotionListener {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private EventListenerList eventListenerList = new EventListenerList();
	private static final String ROOT = "ISIS/Resource/HUDImages/";
	private static final JFrame MainFrame = null;
	private String[] botones = { "edificaciones.png", "carreteras.png",
			"servicios.png", "industria.png", "comunitario.png" };
	private String[] labels = { "lochas.png", "felicidad.png",
			"habitantes.png", "xp.png" };
	private BufferedImage nuevaImagen, grama, seleccion, elementohud, casa;
	private Point posicionRaton, anterior, ubicacion, seleccionado;
	private Polygon poligono;
	private boolean arrastre;
	private int ancho, alto, tile;
	private int x = 0;
	private int y = 0;

	public GamePanel() {

		super();
		setOpaque(false);
		dibujarAcciones();
		tile = 30;
		ancho = grama.getWidth() * tile;
		alto = grama.getHeight() * tile;
		ubicacion = new Point(0, alto / 2);
		seleccionado = new Point(-1, -1);
		addMouseListener(this);
		addMouseMotionListener(this);

	}

	public void dibujarAcciones() {

		poligono = new Polygon();
		poligono.addPoint(0, 32);
		poligono.addPoint(64, 0);
		poligono.addPoint(128, 32);
		poligono.addPoint(64, 64);

		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();

		// grama = gc.createCompatibleImage(128, 64, Transparency.TRANSLUCENT);
		try {
			grama = ImageIO.read(ClassLoader.getSystemResourceAsStream(ROOT
					+ "grama.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Graphics g = grama.getGraphics();

		// g.setColor(Color.WHITE);
		// g.fillPolygon(poligono);
		// g.setColor(Color.BLACK);
		// g.drawPolygon(poligono);
		g.dispose();

		seleccion = gc.createCompatibleImage(128, 64, Transparency.BITMASK);

		g = seleccion.getGraphics();
		g.setColor(Color.GREEN);
		g.fillPolygon(poligono);
		g.drawPolygon(poligono);
		g.dispose();

	}

	public void paint(Graphics g) {
		// AffineTransform tx = new AffineTransform();
		// tx.translate(0, 0);
		// tx.scale(1, 1);
		// super.paint(g);
		chequearNuevaImagen();
		Graphics bg = nuevaImagen.getGraphics();
		dibujarGrilla(bg);
		crearHUD(bg);
		bg.dispose();
		g.drawImage(nuevaImagen, 0, 0, this);
	}

	public void chequearNuevaImagen() {
		if (nuevaImagen == null
				|| (nuevaImagen.getWidth() != this.getWidth() || nuevaImagen
						.getHeight() != this.getHeight())) {
			GraphicsConfiguration gc = GraphicsEnvironment
					.getLocalGraphicsEnvironment().getDefaultScreenDevice()
					.getDefaultConfiguration();
			nuevaImagen = gc.createCompatibleImage(this.getWidth(),
					this.getHeight(), Transparency.BITMASK);
		}
	}

	public void dibujarGrilla(Graphics g) {
		int dx = 0;
		int dy = 0;
		// Parte donde esta bloqueada el mapa
		g.setColor(Color.BLACK);
		// Rellenar con una grama sombreada
		g.fillRect(0, 0, nuevaImagen.getWidth(), nuevaImagen.getHeight());
		// Se crea el HUD
		// crearHUD(g);
		for (int x = 0; x < tile; x++) {
			for (int y = 0; y < tile; y++) {
				dx = x * grama.getWidth() / 2 - y * grama.getWidth() / 2;
				dy = x * grama.getHeight() / 2 + y * grama.getHeight() / 2;
				dx -= ubicacion.x;
				dy -= ubicacion.y;
				g.drawImage(grama, dx, dy, this);
				if ((x == seleccionado.x) && (y == seleccionado.y)) {
					g.drawImage(seleccion, dx, dy, this);
				}
				g.drawString("(" + x + "," + y + ")", dx,
						dy + grama.getHeight() / 2);
			}
		}

		// crearcasa(g);
	}

	public void crearHUD(Graphics g) {
		int x = 10, y = 40;
		// AffineTransform tx = new AffineTransform();
		// tx.translate(0, 0);
		// tx.scale(1, 1);

		// Dibuja los botones
		for (int i = 0; i < botones.length; i++) {
			try {
				elementohud = ImageIO.read(ClassLoader
						.getSystemResourceAsStream(ROOT + botones[i]));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(elementohud, x, y, this);
			x += 200;
		}
		x = 10;
		y = 140;

		// Dibuja los labels
		for (int i = 0; i < labels.length; i++) {
			try {
				elementohud = ImageIO.read(ClassLoader
						.getSystemResourceAsStream(ROOT + labels[i]));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(elementohud, x, y, this);
			y += 80;
		}

		try {
			elementohud = ImageIO.read(ClassLoader
					.getSystemResourceAsStream(ROOT + "alcalde.png"));
			g.drawImage(elementohud, 0, getHeight() - 60, this);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			elementohud = ImageIO.read(ClassLoader
					.getSystemResourceAsStream(ROOT + "minimapa.png"));
			g.drawImage(elementohud, getWidth() - 170, getHeight() - 140, this);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (!arrastre) {
			int pickX = e.getX() + ubicacion.x;
			int pickY = e.getY() + ubicacion.y;
			int tileW = grama.getWidth();
			int tileH = grama.getHeight();

			int hitx = (int) (((double) pickX / (double) tileW)
					+ ((double) pickY / (double) tileH) - 0.5);
			int hity = (int) (((double) pickY / (double) tileH)
					- ((double) pickX / (double) tileW) + 0.5);
			seleccionado.setLocation(hitx, hity);
			repaint();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if ((e.getX() < 150 && e.getX() > 10)
				&& (e.getY() < 80 && e.getY() > 42)) {
			System.out.println("Edificaciones");
			fireActionEvent(new ActionEvent(this, 0, "Edificaciones"));
			// crearcasa(g);
			// Graphics g = casa.getGraphics();
			// paint(g);

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if ((e.getButton() == MouseEvent.BUTTON1) && !arrastre) {
			posicionRaton = e.getPoint();
			anterior = new Point(ubicacion);
			arrastre = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		arrastre = false;
		posicionRaton = null;

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (arrastre) {
			ubicacion.x = anterior.x + posicionRaton.x - e.getX();
			ubicacion.y = anterior.y + posicionRaton.y - e.getY();
			if (ubicacion.x < -ancho / 2) {
				ubicacion.x = -ancho / 2;
			}
			if (ubicacion.y < -alto / 2 + this.getHeight()) {
				ubicacion.y = -alto / 2 + this.getHeight();
			}
			if (ubicacion.x > ancho / 2 - this.getWidth() + grama.getWidth()) {
				ubicacion.x = ancho / 2 - this.getWidth() + grama.getWidth();
			}
			if (ubicacion.y > alto / 2 + this.getHeight()) {
				ubicacion.y = alto / 2 + this.getHeight();
			}
			repaint();
		}

	}

	public void crearcasa(Graphics g) {
		System.out.println("creo la casa");

		try {
			casa = ImageIO.read(ClassLoader.getSystemResourceAsStream(ROOT
					+ "minimapa.png"));
			g.drawImage(casa, getWidth() - 120, getHeight() - 140, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint();
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

}
