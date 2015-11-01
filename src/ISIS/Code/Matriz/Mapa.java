package ISIS.Code.Matriz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Timer;
import javax.swing.ToolTipManager;
import javax.swing.event.EventListenerList;

import ISIS.Code.Class.DialogoCarreteras;
import ISIS.Code.Class.DialogoEdifComunitarios;
import ISIS.Code.Class.DialogoEdificaciones;
import ISIS.Code.Class.DialogoIndustria;
import ISIS.Code.Class.DialogoServiciosPublicos;
import ISIS.Code.Panels.MapSelectionPanel;
import ISIS.Code.Panels.MayorSelectionPanel;
import ISIS.Code.Panels.PanelPausa;
import ISIS.Code.Panels.StartPanel;
import ISIS.Code.ParametrosJuego.Parametros;
import Pruebas.*;

public class Mapa extends JPanel implements MouseListener, MouseMotionListener,
		MouseWheelListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<BufferedImage> listBotones = null;
	private ArrayList<BufferedImage> listLabels = null;
	private ArrayList<BufferedImage> listindicadores = null;
	private ArrayList<BufferedImage> labelsderecha = null;
	private EventListenerList eventListenerList = new EventListenerList();
	private Point posicionRaton, anterior, ubicacion, seleccionado;
	public static boolean construir = false;
	private Polygon poligono;
	private boolean arrastre = false;
	public static boolean click = false;
	private static boolean sonido_tile = false;
	private double scale = 1.0;
	private double atx, aty;
	private int ancho, alto, tile;
	public static Parametros parametros;
	private double felicidadEstatica;
	public ModeloMapa matriz;
	private Construccion imagen;
	private MapaPersonalizado mapaGenerator = new MapaPersonalizado();
	private Imagenes imagenes = new Imagenes();
	public static boolean construyendo = false;
	public static int walk = 0;
	private static String objetoComprado;
	private static boolean tile_construyendo = false;
	private double felicidad = 0;
	public static boolean alquiler = false;
	public static boolean basura = false;
	public static boolean cobrarAlquiler = false;
	public static int incrementoX = 0, incrementoY = 0;
	private Timer timer;
	public static int indiceContaminacion = -1;
	public NombreConstrucciones nombres = new NombreConstrucciones();
	public static boolean iniciarCobroAseo = false;
	public static int indice_animo = 0;
	private int alquileres = 0;
	private int construcciones = 0;
	public static int aviso = 0;
	public static boolean lluvia = false;
	public static int m = 0;
	public Sonidos sonidos = new Sonidos();
	public static boolean habilitarNivel = false;

	private static final String ROOT = "src/ISIS/Resource/HUDImages/";
	private int incrementoX_down = 0;
	private String[] botones = { "barra.png", "edificaciones.png",
			"carreteras.png", "servicios.png", "industria.png",
			"comunitario.png" };
	private String[] labels = { "datos.png" };
	private String[] indicadores = { "dock.png", "minimapa2.png" };
	private String[] labelsDerecha = { "malandro.png", "actualidad3.png",
			"actualidad1.png", "actualidad4.png", "actualidad2.png" };

	public int w = 0;
	public static DialogoEdificaciones dialogo;
	public static DialogoEdifComunitarios dialogoEdif;
	private static DialogoIndustria dialogoIndustria;
	private static DialogoCarreteras dialogoCarretera;
	private static DialogoServiciosPublicos dialogoServicios;
	static JPopupMenu Pmenu;
	static JMenuItem menuItem;
	private int anchura = 384;
	private int altura = 192;
	private static int coordX = 0;
	private static int coordY = 0;
	private static int xs = 0;
	private static int ys = 0;
	private static int auxXs = 0;
	private static int auxYs = 0;
	private static int i = 0;
	private static int j = 0;
	private static int k = 0;
	public static int ESTADO;
	public static Construccion objeto = null;
	public boolean guardar = false;
	public int hora = 0;
	public int minutos = 0;
	public int segundos = 0;
	public int l = 0;
	private boolean itemMover;
	private boolean extras = true;
	private boolean in = false;
	private boolean out = true;
	private int incrementoY_down = 0;
	private int incrementoX_up = 0;
	private int incrementoY_up = 0;
	private boolean terremoto;
	private int petrocasas = 0;
	private int majunches = 0;
	private int indiceDelincuencia = -1;
	private boolean cargar = false;

	public Mapa() {

		super();
		i = 1200;
		setOpaque(false);
		dibujarAcciones();

		cargarImagenes();
		// this.setSize(200, 200);
		// StartPanel.soundFondo.loop();
		tile = 100;
		matriz = new ModeloMapa(tile, tile);
		// Inicializo la matriz en null
		for (int i = 0; i < tile; i++) {
			for (int j = 0; j < tile; j++) {
				matriz.escribirDato(i, j, null);
			}
		}
		ancho = imagenes.grama.getWidth() * tile;
		alto = imagenes.grama.getHeight() * tile;

		imagenes.IniciarComponentes();

		ubicacion = new Point(0, alto / 2);
		seleccionado = new Point(-1, -1);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		// sonidos.soundFondo.loop();

	}

	private void cargarImagenes() {

		listBotones = new ArrayList<BufferedImage>();
		for (int i = 0; i < botones.length; i++) {
			listBotones.add(ImageCache.getInstance()
					.getImage(ROOT + botones[i]));
		}

		listLabels = new ArrayList<BufferedImage>();
		for (int i = 0; i < labels.length; i++) {
			listLabels.add(ImageCache.getInstance().getImage(ROOT + labels[i]));
		}

		listindicadores = new ArrayList<BufferedImage>();
		for (int i = 0; i < indicadores.length; i++) {
			listindicadores.add(ImageCache.getInstance().getImage(
					ROOT + indicadores[i]));
		}

		labelsderecha = new ArrayList<BufferedImage>();
		for (int i = 0; i < labelsDerecha.length; i++) {
			labelsderecha.add(ImageCache.getInstance().getImage(
					ROOT + labelsDerecha[i]));
		}

	}

	public void dibujarAcciones() {

		imagen = new Construccion("imagen");
		imagen.setImagen(ImageCache.getInstance().getImage(
				"src/ISIS/Code/Matriz/grama.png"));
		poligono = new Polygon();
		// poligono.addPoint(0, 32);
		// poligono.addPoint(64, 0);
		// poligono.addPoint(128, 32);
		// poligono.addPoint(64, 64);
		poligono.addPoint(0, altura / 2);
		poligono.addPoint(anchura / 2, 0);
		poligono.addPoint(anchura, altura / 2);
		poligono.addPoint(anchura / 2, altura);

		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		imagenes.grama = gc.createCompatibleImage(384, 192,
				Transparency.TRANSLUCENT);

		Graphics g = imagenes.grama.getGraphics();
		Graphics g2 = imagenes.grama.getGraphics();

		g.setColor(Color.WHITE);
		g.fillPolygon(poligono);
		g.setColor(Color.BLACK);
		g.drawPolygon(poligono);
		g.dispose();

		imagenes.seleccion = gc.createCompatibleImage(768, 384,
				Transparency.TRANSLUCENT);
		imagenes.colision = gc.createCompatibleImage(768, 384,
				Transparency.BITMASK);

		g = imagenes.seleccion.getGraphics();
		g2 = imagenes.colision.getGraphics();
		g.setColor(Color.GREEN);
		g.fillPolygon(poligono);
		g.drawPolygon(poligono);
		g.dispose();

		g2.setColor(Color.RED);
		g2.fillPolygon(poligono);
		g2.drawPolygon(poligono);
		g2.dispose();

	}

	public void paint(Graphics g) {
		super.paint(g);

		chequearNuevaImagen();
		Graphics2D g2 = (Graphics2D) g;

		Graphics bg = imagenes.nuevaImagen.getGraphics();
		dibujarGrilla(bg);
		ConstruccionesIniciales();

		repintarMatriz(bg);

		habilitarNivel = true;
		Efectos(bg);

		toolTipText(bg);

		if (lluvia == true) {
			if (hora > 18 && hora < 24) {
				Image lluvia1 = Toolkit.getDefaultToolkit().getImage(
						"src/ISIS/Resource/HUDImages/lluvia2.png");
				bg.drawImage(lluvia1, 0, (int) Math.floor(m + 10), this);
				// Image lluvia1 = Toolkit.getDefaultToolkit().getImage(
				// "src/ISIS/Resource/HUDImages/noche.png");
				// bg.drawImage(lluvia1, 0, 0, this);
				m++;
				l++;
				if (m == 6)
					m = 0;

				if (l == 50) {
					l = 0;
					lluvia = false;
				}

			}
		}

		atx = (getWidth() - scale * imagenes.nuevaImagen.getWidth()) / 2;
		// System.err.println("atx: "+atx);
		aty = (getHeight() - scale * imagenes.nuevaImagen.getHeight()) / 2;
		AffineTransform at = AffineTransform.getTranslateInstance(atx, aty);
		// AffineTransform at = AffineTransform.getTranslateInstance(0, 0);
		at.scale(scale, scale);
		// at.translate(100, 100);
		// at.scale(0.5, 0.5);
		g2.drawRenderedImage(imagenes.nuevaImagen, at);

		bg.dispose();
		crearHUD(g2);
		// pintarhud.IniciarComponentes(g2);
		// g.drawImage(nuevaImagen, 0, 0, this);

	}

	private void Efectos(Graphics bg) {
		// Esta Funcion dibuja efectos sobre el Mapa (Nubes, Aviones,
		// Publicidad, etc)

		// Efecto Camion

		int dx1 = 52 * imagen.getImagen().getWidth() / 2 - 50
				* imagen.getImagen().getWidth() / 2;
		int dy1 = 52 * imagen.getImagen().getHeight() / 2 + 50
				* imagen.getImagen().getHeight() / 2;

		dx1 -= ubicacion.x;
		dy1 -= ubicacion.y;

		Image notificacion4 = Toolkit.getDefaultToolkit().getImage(
				"src/ISIS/Resource/HUDImages/camion2.png");
		bg.drawImage(notificacion4, dx1 + incrementoX_up, dy1 + incrementoY_up,
				this);
		incrementoX_up += 2;
		incrementoY_up -= 1;

		int dx = 52 * imagen.getImagen().getWidth() / 2 - 45
				* imagen.getImagen().getWidth() / 2;
		int dy = 52 * imagen.getImagen().getHeight() / 2 + 45
				* imagen.getImagen().getHeight() / 2;
		dx -= ubicacion.x;
		dy -= ubicacion.y;
		Image notificacion2 = Toolkit.getDefaultToolkit().getImage(
				"src/ISIS/Resource/HUDImages/camion.png");
		bg.drawImage(notificacion2, dx + incrementoX_down, dy
				+ incrementoY_down, this);

		// ----------------------
		// int dx3 = 55 * imagen.getImagen().getWidth() / 2 - 54
		// * imagen.getImagen().getWidth() / 2;
		// int dy3 = 55 * imagen.getImagen().getHeight() / 2 + 54
		// * imagen.getImagen().getHeight() / 2;
		// dx3 -= ubicacion.x;
		// dy3 -= ubicacion.y;
		//
		// Image notificacion5 = Toolkit.getDefaultToolkit().getImage(
		// "src/ISIS/Resource/HUDImages/teleferico.png");
		// bg.drawImage(notificacion5, dx3 + incrementoX_up, dy3 +
		// incrementoY_up,
		// this);
		//
		// incrementoX_down -= 2;
		// incrementoY_down += 1;

		// ---------------------------

		int dx4 = 55 * imagen.getImagen().getWidth() / 2 - 50
				* imagen.getImagen().getWidth() / 2;
		int dy4 = 55 * imagen.getImagen().getHeight() / 2 + 50
				* imagen.getImagen().getHeight() / 2;
		dx4 -= ubicacion.x;
		dy4 -= ubicacion.y;

		Image notificacion6 = Toolkit.getDefaultToolkit().getImage(
				"src/ISIS/Resource/HUDImages/teleferico1.png");
		bg.drawImage(notificacion6, dx4 + incrementoX_down, dy4
				+ incrementoY_down, this);

		Image cableTeleferico = Toolkit.getDefaultToolkit().getImage(
				"src/ISIS/Resource/HUDImages/cableTeleferico.png");
		bg.drawImage(cableTeleferico, dx4 - 1400, dy4 - 100, this);

		int dx3 = 55 * imagen.getImagen().getWidth() / 2 - 54
				* imagen.getImagen().getWidth() / 2;
		int dy3 = 55 * imagen.getImagen().getHeight() / 2 + 54
				* imagen.getImagen().getHeight() / 2;
		dx3 -= ubicacion.x;
		dy3 -= ubicacion.y;

		Image notificacion5 = Toolkit.getDefaultToolkit().getImage(
				"src/ISIS/Resource/HUDImages/teleferico.png");
		bg.drawImage(notificacion5, dx3 + incrementoX_up, dy3 + incrementoY_up,
				this);

		incrementoX_down -= 2;
		incrementoY_down += 1;

		// Image cableTeleferico1 = Toolkit.getDefaultToolkit().getImage(
		// "src/ISIS/Resource/HUDImages/cableTeleferico.png");
		// bg.drawImage(cableTeleferico1, dx4-190, dy4+100, this);
		//
		// Image cableTeleferico2 = Toolkit.getDefaultToolkit().getImage(
		// "src/ISIS/Resource/HUDImages/cableTeleferico.png");
		// bg.drawImage(cableTeleferico2, dx4-390, dy4+200, this);
		//
		// Image cableTeleferico3 = Toolkit.getDefaultToolkit().getImage(
		// "src/ISIS/Resource/HUDImages/cableTeleferico.png");
		// bg.drawImage(cableTeleferico3, dx4-500, dy4+240, this);

		//
		int dx2 = 52 * imagen.getImagen().getWidth() / 2 - 50
				* imagen.getImagen().getWidth() / 2;
		int dy2 = 52 * imagen.getImagen().getHeight() / 2 + 50
				* imagen.getImagen().getHeight() / 2;
		dx2 -= ubicacion.x;
		dy2 -= ubicacion.y;

		if (dx + incrementoX_down == dx2) {
			incrementoX_down = 0;
			incrementoY_down = 0;
			incrementoX_up = 0;
			incrementoY_up = 0;

		}

		// ---------------------------------------

		// Efecto Nubes y Avion

		Image img9 = Toolkit.getDefaultToolkit().getImage(
				"src/ISIS/Resource/HUDImages/avion.png");
		Image img10 = Toolkit.getDefaultToolkit().getImage(
				"src/ISIS/Resource/HUDImages/nube3.png");
		bg.drawImage(img10, (int) Math.floor(2 * k), 0, this);
		k++;
		if (k == this.getWidth()) {
			k = 0;
		}
		bg.drawImage(img9, (int) Math.floor(i * 1), (int) Math.floor(j * 0.5),
				this);
		i--;
		j++;
		if (i == 0) {
			i = 1200;
			j = 0;
		}
	}

	private void ConstruccionesIniciales() {
		matriz.escribirDato(53, 48, new Celda(null, new Construccion(
				"gobernacionMerida")));
		matriz.leerDato(53, 48).getConstRef().setConstruido("listo");

		matriz.escribirDato(49, 50, new Celda(null, new Construccion(
				"companiaAseoUrbano")));
		matriz.leerDato(49, 50).getConstRef().setConstruido("listo");

		matriz.escribirDato(51, 50, new Celda(null, new Construccion(
				"aguasDeMerida")));
		matriz.leerDato(51, 50).getConstRef().setConstruido("listo");

		for (int i = 45; i <= 50; i++) {
			matriz.escribirDato(52, i, new Celda(null, new Construccion(
					"carretera6")));
			matriz.leerDato(52, i).getConstRef().setConstruido("listo");
		}

		for (int i = 51; i <= 52; i++) {
			matriz.escribirDato(52, i, new Celda(null, new Construccion(
					"carretera")));
			matriz.leerDato(52, i).getConstRef().setConstruido("listo");
		}

		for (int i = 53; i <= 58; i++) {
			matriz.escribirDato(52, i, new Celda(null, new Construccion(
					"carretera6")));
			matriz.leerDato(52, i).getConstRef().setConstruido("listo");
		}

		matriz.escribirDato(55, 55,
				new Celda(null, new Construccion("montana")));
		matriz.leerDato(55, 55).getConstRef().setConstruido("listo");

		matriz.escribirDato(55, 49,
				new Celda(null, new Construccion("montana")));
		matriz.leerDato(55, 49).getConstRef().setConstruido("listo");

	}

	private void toolTipText(Graphics bg) {

		if (getCoordX() != 0 && getCoordY() != 0) {

			if (matriz.leerDato(getAuxXs(), getAuxYs()) != null) {

				ToolTipManager.sharedInstance().setInitialDelay(0);
				ToolTipManager.sharedInstance().setDismissDelay(100);

				int dx = getCoordX() * imagen.getImagen().getWidth() / 2
						- getCoordY() * imagen.getImagen().getWidth() / 2;
				int dy = getCoordX() * imagen.getImagen().getHeight() / 2
						+ getCoordY() * imagen.getImagen().getHeight() / 2;
				dx -= ubicacion.x;
				dy -= ubicacion.y;
				Image img1 = Toolkit.getDefaultToolkit().getImage(
						"src/ISIS/Resource/HUDImages/tooltip.png");
				if (matriz.leerDato(getCoordX(), getCoordY()).getConstRef().tipo != 0) {
					bg.drawImage(img1, dx, dy, this);
				}

				Font font = new Font("Arial", Font.BOLD, 12);
				AttributedString attributedString = new AttributedString(
						nombres.cambiarNombres(
								matriz.leerDato(getCoordX(), getCoordY())
										.getConstRef().getID()).toUpperCase());
				attributedString.addAttribute(
						TextAttribute.FONT,
						font,
						0,
						nombres.cambiarNombres(
								matriz.leerDato(getCoordX(), getCoordY())
										.getConstRef().getID()).length());
				attributedString.addAttribute(
						TextAttribute.FOREGROUND,
						Color.white,
						0,
						nombres.cambiarNombres(
								matriz.leerDato(getCoordX(), getCoordY())
										.getConstRef().getID()).length());

				if (matriz.leerDato(getCoordX(), getCoordY()).getConstRef().tipo != 0) {
					bg.drawString(attributedString.getIterator(), dx + 7,
							dy + 20);
				}
				bg.setFont(new Font("Calibri", Font.BOLD, 15));
				bg.setColor(Color.BLACK);
				if (matriz.leerDato(getCoordX(), getCoordY()).getConstRef().tipo == 1) {
					bg.drawString(
							"Alquiler: "
									+ matriz.leerDato(getCoordX(), getCoordY())
											.getConstRef().alquiler + "",
							dx + 7, dy + 40);
					bg.drawString(
							"Habitantes: "
									+ matriz.leerDato(getCoordX(), getCoordY())
											.getConstRef().habitantes + "",
							dx + 7, dy + 60);
				} else if (matriz.leerDato(getCoordX(), getCoordY())
						.getConstRef().tipo == 2) {
					bg.drawString(
							"Impuesto: "
									+ matriz.leerDato(getCoordX(), getCoordY())
											.getConstRef().alquiler + "",
							dx + 7, dy + 40);
					bg.drawString(
							"Experiencia: "
									+ matriz.leerDato(getCoordX(), getCoordY())
											.getConstRef().xp + "", dx + 7,
							dy + 60);
				}
				if (isSonido_tile() != false) {
					if (matriz.leerDato(getCoordX(), getCoordY()).getConstRef()
							.getID() == "plantaElectricidad") {

					}
					if (matriz.leerDato(getCoordX(), getCoordY()).getConstRef()
							.getID() == "estadioGigante") {
						// StartPanel.soundStadium.play();
					}
					setSonido_tile(false);
				}

			}
		}

	}

	public void crearCasa(int i, int j) {

		if (dialogo.getSeleccionada() != null) {

			matriz.escribirDato(
					i,
					j,
					new Celda(null, new Construccion(dialogo.getSeleccionada())));

			matriz.leerDato(i, j).getConstRef().setConstruido("construyendo");
			indiceContaminacion += matriz.leerDato(i, j).getConstRef()
					.getContaminacion();

			setXs(i);
			setYs(j);

			if (dialogo.getSeleccionada() == "petrocasa") {
				petrocasas++;
				if (petrocasas >= 5 && majunches < petrocasas) {
					aviso = 1;
				}
			}
			if (dialogo.getSeleccionada() == "majunche") {
				majunches++;
				if (majunches >= 5 && majunches > petrocasas) {
					aviso = 2;
				}
			}

			new Reminder(2, i, j);
			StartPanel.click2.play();

		}
		if (dialogoEdif.getSeleccionada() != null) {
			// setTile_construyendo(false);
			matriz.escribirDato(i, j, new Celda(null, new Construccion(
					dialogoEdif.getSeleccionada())));
			matriz.leerDato(i, j).getConstRef().setConstruido("construyendo");
			indiceContaminacion += matriz.leerDato(i, j).getConstRef()
					.getContaminacion();
			setXs(i);
			setYs(j);
			new Reminder(2, i, j);
			StartPanel.click2.play();

		}
		if (dialogoIndustria.getSeleccionada() != null) {
			matriz.escribirDato(i, j, new Celda(null, new Construccion(
					dialogoIndustria.getSeleccionada())));
			matriz.leerDato(i, j).getConstRef().setConstruido("construyendo");
			indiceContaminacion += matriz.leerDato(i, j).getConstRef()
					.getContaminacion();

			setXs(i);
			setYs(j);
			new Reminder(2, i, j);
			StartPanel.click2.play();
		}
		if (dialogoCarretera.getSeleccionada() != null) {
			matriz.escribirDato(i, j, new Celda(null, new Construccion(
					dialogoCarretera.getSeleccionada())));
			matriz.leerDato(i, j).getConstRef().setConstruido("construyendo");
			indiceContaminacion += matriz.leerDato(i, j).getConstRef()
					.getContaminacion();
			setXs(i);
			setYs(j);
			new Reminder(2, i, j);
			StartPanel.click2.play();
		}
		if (dialogoServicios.getSeleccionada() != null) {
			matriz.escribirDato(i, j, new Celda(null, new Construccion(
					dialogoServicios.getSeleccionada())));
			matriz.leerDato(i, j).getConstRef().setConstruido("construyendo");
			indiceContaminacion += matriz.leerDato(i, j).getConstRef()
					.getContaminacion();
			setXs(i);
			setYs(j);
			new Reminder(2, i, j);
			StartPanel.click2.play();
		}

	}

	public void repintarMatriz(Graphics bg) {

		if (PanelPausa.isGuardado()) {
			guardar = true;
			guardar();
			PanelPausa.setGuardado(false);
		}

		if (getAuxXs() != 0) {
			if (matriz.leerDato(getAuxXs(), getAuxYs()) != null) {
				matriz.leerDato(getAuxXs(), getAuxYs()).getConstRef()
						.setConstruido("listo");
			}
		}

		if (matriz != null) {

			for (int x = 0; x < tile; x++) {
				for (int y = 0; y < tile; y++) {
					if (matriz.leerDato(x, y) != null) {

						int dx = x * imagen.getImagen().getWidth() / 2 - y
								* imagen.getImagen().getWidth() / 2;
						int dy = x * imagen.getImagen().getHeight() / 2 + y
								* imagen.getImagen().getHeight() / 2;
						dx -= ubicacion.x;
						dy -= ubicacion.y;

						if (matriz.leerDato(x, y).getConstRef().getConstruido() == "construyendo") {

							Image img1 = Toolkit
									.getDefaultToolkit()
									.getImage(
											"src/ISIS/Resource/HUDImages/martillando.gif");
							Image img2 = Toolkit
									.getDefaultToolkit()
									.getImage(
											"src/ISIS/Resource/HUDImages/construir.png");

							bg.drawImage(img2, dx, dy, this);
							bg.drawImage(img1, dx + 50, dy + 50, this);
							bg.drawImage(img1, dx + 190, dy + 50, this);
						} else {

							if (matriz.leerDato(x, y).getConstRef().getTamano() == 1) {

								if (terremoto) {

									bg.drawImage(matriz.leerDato(x, y)
											.getConstRef().getImagen(), dx + m,
											dy + (int) Math.floor(m + 3), this);
									m++;
									if (m == 30)
										m = 0;
								} else if (matriz.leerDato(x, y).getConstRef()
										.getConstruido() == "listo")
									bg.drawImage(matriz.leerDato(x, y)
											.getConstRef().getImagen(), dx, dy,
											this);
								repaint();

							} else if (matriz.leerDato(x, y).getConstRef()
									.getTamano() == 2) {
								if (terremoto) {

									bg.drawImage(matriz.leerDato(x, y)
											.getConstRef().getImagen(), dx - 20
											+ m,
											dy - 300 + (int) Math.floor(0.3),
											this);
									m++;
									if (m == 30)
										m = 0;
								} else if (matriz.leerDato(x, y).getConstRef()
										.getConstruido() == "listo")
									bg.drawImage(matriz.leerDato(x, y)
											.getConstRef().getImagen(),
											dx - 20, dy - 300, this);
							} else if (matriz.leerDato(x, y).getConstRef()
									.getTamano() == 3) {
								if (terremoto) {

									bg.drawImage(matriz.leerDato(x, y)
											.getConstRef().getImagen(), dx - 25
											+ m,
											dy - 620 + (int) Math.floor(0.3),
											this);
									m++;
									if (m == 30)
										m = 0;
								} else if (matriz.leerDato(x, y).getConstRef()
										.getConstruido() == "listo")
									bg.drawImage(matriz.leerDato(x, y)
											.getConstRef().getImagen(),
											dx - 25, dy - 620, this);
							}

							if (matriz.leerDato(x, y).getConstRef().getID() == "gobernacionMerida") {
								if (cobrarAlquiler) {
									Image notificacionAlquiler = Toolkit
											.getDefaultToolkit()
											.getImage(
													"src/ISIS/Resource/HUDImages/seniat.png");
									bg.drawImage(notificacionAlquiler,
											dx + 200, dy, this);
								}
							}

							if (matriz.leerDato(x, y).getConstRef().getID() == "companiaAseoUrbano") {
								if (basura && indiceContaminacion > 0) {
									Image notificacionAlquiler = Toolkit
											.getDefaultToolkit()
											.getImage(
													"src/ISIS/Resource/HUDImages/aseocamion.png");
									bg.drawImage(notificacionAlquiler,
											dx + 300, dy, this);
								}

							}

							if (basura
									&& matriz.leerDato(x, y).getConstRef().tipo == 1
									&& construcciones > 0) {

								Image moscas = Toolkit
										.getDefaultToolkit()
										.getImage(
												"src/ISIS/Resource/HUDImages/mosca2.gif");
								Image potebasura;

								if (indiceContaminacion == 1) {
									potebasura = Toolkit
											.getDefaultToolkit()
											.getImage(
													"src/ISIS/Resource/HUDImages/basura_1.png");
									bg.drawImage(potebasura, dx + 170,
											dy + 110, this);
									bg.drawImage(moscas, dx + 180, dy + 120,
											this);
								} else if (indiceContaminacion == 2) {
									potebasura = Toolkit
											.getDefaultToolkit()
											.getImage(
													"src/ISIS/Resource/HUDImages/basura_2.png");
									bg.drawImage(potebasura, dx + 170,
											dy + 110, this);
									bg.drawImage(moscas, dx + 180, dy + 120,
											this);
								} else if (indiceContaminacion == 3) {
									potebasura = Toolkit
											.getDefaultToolkit()
											.getImage(
													"src/ISIS/Resource/HUDImages/basura_3.png");
									bg.drawImage(moscas, dx + 180, dy + 120,
											this);
									bg.drawImage(potebasura, dx + 170,
											dy + 110, this);
								}
							}

							if (matriz.leerDato(x, y).getConstRef().getCobrar()) {
								if (matriz.leerDato(x, y).getConstRef().tipo == 1
										|| matriz.leerDato(x, y).getConstRef().tipo == 2) {
									Image notificacion = Toolkit
											.getDefaultToolkit()
											.getImage(
													"src/ISIS/Resource/HUDImages/bolivar.png");
									bg.drawImage(notificacion, dx + 300, dy,
											this);
									alquileres++;
									if (alquileres > 4) {
										cobrarAlquiler = true;
									}
								}
							}

							if (matriz.leerDato(x, y).getConstRef().tipo == 1) {
								// Image camionAseo = Toolkit
								// .getDefaultToolkit()
								// .getImage(
								// "src/ISIS/Resource/HUDImages/camionAseo.png");
								// bg.drawImage(camionAseo, dx, dy,
								// this);
							}

						}

					}

				}

			}
		}

		alquileres = 0;

	}

	public void guardar() {
		if (construcciones > 0) {
			Construccion obj;
			FileWriter construcciones = null, parametros = null, usuarios = null;
			PrintWriter pc = null, pp = null, pu = null;

			// ------------------------------------------------------------------------
			try {
				// ------------------------------------------------------------------------

				construcciones = new FileWriter(
						"src/ISIS/Code/Resource/Guardado/"
								+ this.parametros.alcalde + ".txt");
				parametros = new FileWriter("src/ISIS/Code/Resource/Guardado/"
						+ this.parametros.alcalde + ".properties");
				usuarios = new FileWriter(
						"src/ISIS/Code/Resource/Guardado/usuarios.txt");

				// ------------------------------------------------------------------------

				pc = new PrintWriter(construcciones);
				pp = new PrintWriter(parametros);
				pu = new PrintWriter(usuarios);

				// ------------------------------------------------------------------------

				for (int i = 0; i < matriz.getFilas(); i++) {
					for (int j = 0; j < matriz.getColumnas(); j++) {
						if (matriz.leerDato(i, j) != null) {
							obj = matriz.leerDato(i, j).getConstRef();
							pc.println(obj.getID() + "," + i + "," + j);
						}
					}
				}

				// ------------------------------------------------------------------------

				pp.println("alcalde = " + this.parametros.alcalde);
				pp.println("ciudad = " + this.parametros.ciudad);
				pp.println("imagenAlcalde = " + this.parametros.imagenAlcalde);
				pp.println("felicidad = " + felicidadEstatica);
				pp.println("lochas = " + this.parametros.lochas);
				pp.println("habitantes = " + this.parametros.habitantes);
				pp.println("limite = " + this.parametros.limite);
				pp.println("contaminacion = " + this.parametros.contaminacion);
				pp.println("delincuencia = " + this.parametros.delincuencia);
				pp.println("nivel = " + this.parametros.nivel);
				pp.println("xp = " + this.parametros.xp);

				// ------------------------------------------------------------------------

				pu.println(this.parametros.alcalde + ".properties");
				pu.println(this.parametros.alcalde + ".txt");

				// ------------------------------------------------------------------------

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != construcciones) {
						construcciones.close();
					}
					if (null != parametros) {
						parametros.close();
					}
					if (null != usuarios) {
						usuarios.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	public void cargar(String usuario) {
		cargar = true;
		cargarParametros(usuario);
		cargarConstrucciones(usuario);
	}

	// ------------------------------------------------------------------------

	private void cargarParametros(String usuario) {
		FileInputStream fis = null;
		Properties ppt = new Properties();
		String properties = usuario + ".properties";

		try {

			fis = new FileInputStream("src/ISIS/Code/Resource/Guardado/"
					+ properties);
			ppt.load(fis);
			String key = null;
			String value = null;

			for (Map.Entry<Object, Object> linea : ppt.entrySet()) {
				key = (String) linea.getKey();
				value = (String) linea.getValue();
				if (key.equals("limite")) {
					this.parametros.limite = Integer.parseInt(value);
				} else if (key.equals("habitantes")) {
					this.parametros.habitantes = Integer.parseInt(value);
				} else if (key.equals("xp")) {
					this.parametros.xp = Double.parseDouble(value);
				} else if (key.equals("delincuencia")) {
					this.parametros.delincuencia = Integer.parseInt(value);
				} else if (key.equals("contaminacion")) {
					this.parametros.contaminacion = Integer.parseInt(value);
				} else if (key.equals("nivel")) {
					this.parametros.nivel = Integer.parseInt(value);
				} else if (key.equals("lochas")) {
					this.parametros.lochas = Double.parseDouble(value);
				} else if (key.equals("felicidad")) {
					this.parametros.felicidad = Double.parseDouble(value);
				} else if (key.equals("alcalde")) {
					this.parametros.alcalde = value;
				} else if (key.equals("ciudad")) {
					this.parametros.ciudad = value;
				} else if (key.equals("ImagenAlcalde")) {
					this.parametros.imagenAlcalde = value;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cargarConstrucciones(String usuario) {
		String id = null;
		int i, j;

		File f = null;
		FileReader fr = null;
		BufferedReader br = null;
		String constr = usuario + ".txt";

		try {
			f = new File("src/ISIS/Code/Resource/Guardado/" + constr);
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(linea, ",");
				id = st.nextToken();
				i = Integer.parseInt(st.nextToken());
				j = Integer.parseInt(st.nextToken());
				matriz.escribirDato(i, j, new Celda(null, new Construccion(id)));
				matriz.leerDato(i, j).getConstRef().setConstruido("listo");
				repaint();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != fr) {
				try {
					fr.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	public void chequearNuevaImagen() {
		if (imagenes.nuevaImagen == null
				|| (imagenes.nuevaImagen.getWidth() != this.getWidth() || imagenes.nuevaImagen
						.getHeight() != this.getHeight())) {
			GraphicsConfiguration gc = GraphicsEnvironment
					.getLocalGraphicsEnvironment().getDefaultScreenDevice()
					.getDefaultConfiguration();
			imagenes.nuevaImagen = gc.createCompatibleImage(this.getWidth(),
					this.getHeight(), Transparency.BITMASK);
		}
	}

	public void dibujarGrilla(Graphics g) {
		int dx = 0;
		int dy = 0;

		// Parte donde esta bloqueada el mapa
		g.setColor(Color.DARK_GRAY);
		// Rellenar con una grama sombreada
		g.fillRect(0, 0, imagenes.nuevaImagen.getWidth(),
				imagenes.nuevaImagen.getHeight());
		// Se crea el HUD
		// crearHUD(g);

		for (int x = 0; x < tile; x++) {
			for (int y = 0; y < tile; y++) {
				dx = x * imagenes.grama.getWidth() / 2 - y
						* imagenes.grama.getWidth() / 2;
				dy = x * imagenes.grama.getHeight() / 2 + y
						* imagenes.grama.getHeight() / 2;
				dx -= ubicacion.x;
				dy -= ubicacion.y;

				// System.out.println(mapaGenerator.devuelveTile(x, y));

				if (mapaGenerator.devuelveTile(x, y) == 1)
					g.drawImage(imagenes.grama, dx, dy, this);
				if (mapaGenerator.devuelveTile(x, y) == 2) {
					Image img1 = Toolkit.getDefaultToolkit().getImage(
							"src/ISIS/Resource/HUDImages/pes6.gif");
					g.drawImage(img1, dx + 100, dy + 10, this);
					g.drawImage(imagenes.agua, dx, dy, this);
				}
				if (mapaGenerator.devuelveTile(x, y) == 4)
					g.drawImage(imagenes.tierra3, dx, dy, this);
				if (mapaGenerator.devuelveTile(x, y) == 5)
					g.drawImage(imagenes.tierra4, dx, dy, this);

				dispararEventos(g, x, y, dx, dy);

			}
		}

	}

	public void dispararEventos(Graphics g, int x, int y, int dx, int dy) {

		switch (ESTADO) {
		case Estados.NAVEGACION:
			if ((x == seleccionado.x) && (y == seleccionado.y)) {
				g.drawImage(imagenes.seleccion, dx, dy, this);
			}
			break;

		case Estados.CONSTRUCCION:
			if ((x == seleccionado.x) && (y == seleccionado.y)) {
				if (objeto != null) {
					if (objeto.getTamano() == 1)
						g.drawImage(objeto.getImagen(), dx, dy, this);
					if (objeto.getTamano() == 2)
						g.drawImage(objeto.getImagen(), dx - 20, dy - 300, this);
					if (objeto.getTamano() == 3)
						g.drawImage(objeto.getImagen(), dx - 20, dy - 600, this);

				}
			}
			break;

		default:
			break;
		}

	}

	public void crearHUD(Graphics g) {
		float ANCHO = this.getWidth();
		float ALTO = this.getHeight();
		int x = (int) Math.floor(ANCHO / 2) - 375;
		int y = 40;

		// Dibuja los botones
		if (isConstruir() == true) {
			int i = 0;
			for (BufferedImage image : listBotones) {
				if (i == 0) {
					g.drawImage(image, x, y - 10, this);
					x += 15;
				} else {
					g.drawImage(image, x, y, this);
					x += 140;
				}
				i++;

			}

			repaint();

		}

		x = 10;
		y = (int) Math.floor(ALTO / 4);

		// Dibuja los labels

		for (BufferedImage image : listLabels) {
			if (extras) {
				g.drawImage(image, x, y, this);
				y += 55;
			}
		}

		if (indice_animo == 1) {
			Image img1 = Toolkit.getDefaultToolkit().getImage(
					"src/ISIS/Resource/HUDImages/smiley1.png");
			g.drawImage(img1, x + 100, y + 160, this);
		}

		if (indice_animo == 2) {
			Image img1 = Toolkit.getDefaultToolkit().getImage(
					"src/ISIS/Resource/HUDImages/smiley2.png");
			g.drawImage(img1, x + 100, y + 160, this);
		}

		if (indice_animo == 3) {
			Image img1 = Toolkit.getDefaultToolkit().getImage(
					"src/ISIS/Resource/HUDImages/smiley3.png");
			g.drawImage(img1, x + 100, y + 160, this);
		}

		if (indice_animo == 4) {
			Image img1 = Toolkit.getDefaultToolkit().getImage(
					"src/ISIS/Resource/HUDImages/smiley4.png");
			g.drawImage(img1, x + 100, y + 160, this);
		}

		x = 380;
		y = this.getHeight() - 90;

		if (extras) {
			for (BufferedImage image : listindicadores) {

				g.drawImage(image, x, y, this);
				x = this.getWidth() - 290;
				y -= 105;

			}

			x = 0;
			y = this.getHeight() - 140;

			if (!cargar) {
				this.parametros.imagenAlcalde = MayorSelectionPanel
						.getAlcaldeSeleccionado() + "";
			}

			Image alcalde = Toolkit.getDefaultToolkit().getImage(
					"src/ISIS/Resource/HUDImages/"
							+ this.parametros.imagenAlcalde + ".png");
			g.drawImage(alcalde, x, y, this);

		}

		x = this.getWidth() - 150;
		y = (int) Math.floor(ALTO / 4);
		int i = 0;
		if (extras) {
			for (BufferedImage image : labelsderecha) {

				if (i == 0) {
					g.drawImage(image, x, y, this);
				}
				if (i == 1 && aviso == 0) {
					g.drawImage(image, x, y, this);
				}
				if (i == 2 && aviso == 1) {
					g.drawImage(image, x, y, this);
				}
				if (i == 3 && aviso == 2) {
					g.drawImage(image, x, y, this);
				}
				if (i == 4 && aviso == 3) {
					g.drawImage(image, x, y, this);
				}
				i++;

			}
		}

		// JLabel lochas= new JLabel("200bolos");
		// add(lochas);
		// lochas.setBounds(10, 400, 60, 60);

		if (extras) {
			Graphics2D g2d = (Graphics2D) g;
			Font font = new Font("Calibri", Font.BOLD, 21);
			String aString = Double.toString(parametros.lochas);
			AttributedString attributedString = new AttributedString(aString);
			attributedString.addAttribute(TextAttribute.FONT, font, 0,
					aString.length());
			attributedString.addAttribute(TextAttribute.FOREGROUND,
					Color.white, 0, aString.length());
			g2d.drawString(attributedString.getIterator(), 110, 310);
			String aString2 = Integer.toString(parametros.habitantes) + "/"
					+ parametros.limite;
			AttributedString attributedString2 = new AttributedString(aString2);
			attributedString2.addAttribute(TextAttribute.FONT, font, 0,
					aString2.length());
			attributedString2.addAttribute(TextAttribute.FOREGROUND,
					Color.white, 0, aString2.length());
			g2d.drawString(attributedString2.getIterator(), 110, 375);

			if (parametros.nivel == 1) {
				String string = parametros.xp + "/100";
				AttributedString attributedString3 = new AttributedString(
						string);
				attributedString3.addAttribute(TextAttribute.FONT, font, 0,
						string.length());
				attributedString3.addAttribute(TextAttribute.FOREGROUND,
						Color.white, 0, string.length());
				g2d.drawString(attributedString3.getIterator(), 100, 510);
			} else if (parametros.nivel == 2) {
				String string = parametros.xp + "/300";
				AttributedString attributedString3 = new AttributedString(
						string);
				attributedString3.addAttribute(TextAttribute.FONT, font, 0,
						string.length());
				attributedString3.addAttribute(TextAttribute.FOREGROUND,
						Color.white, 0, string.length());
				g2d.drawString(attributedString3.getIterator(), 100, 510);
			} else if (parametros.nivel == 3) {
				String string = parametros.xp + "/600";
				AttributedString attributedString3 = new AttributedString(
						string);
				attributedString3.addAttribute(TextAttribute.FONT, font, 0,
						string.length());
				attributedString3.addAttribute(TextAttribute.FOREGROUND,
						Color.white, 0, string.length());
				g2d.drawString(attributedString3.getIterator(), 100, 510);
			} else if (parametros.nivel == 4) {
				String string = parametros.xp + "/900";
				AttributedString attributedString3 = new AttributedString(
						string);
				attributedString3.addAttribute(TextAttribute.FONT, font, 0,
						string.length());
				attributedString3.addAttribute(TextAttribute.FOREGROUND,
						Color.white, 0, string.length());
				g2d.drawString(attributedString3.getIterator(), 100, 510);
			} else if (parametros.nivel == 5) {
				String string = Double.toString(parametros.xp);
				AttributedString attributedString3 = new AttributedString(
						string);
				attributedString3.addAttribute(TextAttribute.FONT, font, 0,
						string.length());
				attributedString3.addAttribute(TextAttribute.FOREGROUND,
						Color.white, 0, string.length());
				g2d.drawString(attributedString3.getIterator(), 100, 510);
			}

			minutos = minutos + 3;

			if (minutos == 60) {
				minutos = 0;
				hora++;
			}

			if (hora == 24)
				hora = 0;

			Font font2 = new Font("Digital-7", Font.BOLD, 21);
			g.fillRect(this.getWidth() - 110, 10, 100, 25);
			String horaS;
			// System.out.println("Hora: "+hora+ ":" + minutos+ ":" +segundos);
			if (hora < 12)
				horaS = Integer.toString(hora) + ":"
						+ Integer.toString(minutos) + "  AM";
			else {
				horaS = Integer.toString(hora) + ":"
						+ Integer.toString(minutos) + "  PM";
			}
			AttributedString attributedHora = new AttributedString(horaS);
			attributedHora.addAttribute(TextAttribute.FONT, font2, 0,
					horaS.length());
			attributedHora.addAttribute(TextAttribute.FOREGROUND, Color.green,
					0, horaS.length());
			g2d.drawString(attributedHora.getIterator(), this.getWidth() - 100,
					32);

			if (hora >= 2) {
				// Image noche = Toolkit.getDefaultToolkit().getImage(
				// "src/ISIS/Resource/HUDImages/noche.png");
				// g.drawImage(noche, 0, 0, this);
				// repaint();
			}

			// Datos Alcaldes
			x = 130;
			y = this.getHeight() - 65;

			if (!cargar) {
				parametros.alcalde = MayorSelectionPanel.getNombreAlcalde()
						+ "";
				parametros.ciudad = MapSelectionPanel.getCiudad() + "";
			}
			font = new Font("Calibri", Font.BOLD, 21);
			String IDAlcalde = parametros.alcalde + "";
			AttributedString attributedStringAlcalde = new AttributedString(
					IDAlcalde);
			attributedStringAlcalde.addAttribute(TextAttribute.FONT, font, 0,
					IDAlcalde.length());
			attributedStringAlcalde.addAttribute(TextAttribute.FOREGROUND,
					Color.white, 0, IDAlcalde.length());
			g2d.drawString(attributedStringAlcalde.getIterator(), x, y);

			font = new Font("Calibri", Font.BOLD, 21);
			String IDCiudad = parametros.ciudad + "";
			AttributedString attributedStringCiudad = new AttributedString(
					IDCiudad);
			attributedStringCiudad.addAttribute(TextAttribute.FONT, font, 0,
					IDCiudad.length());
			attributedStringCiudad.addAttribute(TextAttribute.FOREGROUND,
					Color.white, 0, IDCiudad.length());
			g2d.drawString(attributedStringCiudad.getIterator(), x + 20, y + 30);

			// Niveles
			x = 10;
			y = this.getHeight() - 140;

			Image nivels = Toolkit.getDefaultToolkit().getImage(
					"src/ISIS/Resource/HUDImages/star" + parametros.nivel
							+ ".png");
			g.drawImage(nivels, x, y, this);

			repaint();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		float ANCHO = this.getWidth();
		float ALTO = this.getHeight();
		if (!arrastre) {
			int pickX = e.getX() + ubicacion.x;
			int pickY = e.getY() + ubicacion.y;
			int tileW = imagenes.grama.getWidth();
			int tileH = imagenes.grama.getHeight();

			// Lugar en el q se calcula la posicion de la matriz
			int i = (int) (((double) pickX / (double) tileW)
					+ ((double) pickY / (double) tileH) - 0.5);
			int j = (int) (((double) pickY / (double) tileH)
					- ((double) pickX / (double) tileW) + 0.5);
			seleccionado.setLocation(i, j);
			// System.out.println("x: " + i + "y: " + j);
			repaint();
		}
		int pickX = e.getX() + ubicacion.x;
		int pickY = e.getY() + ubicacion.y;
		int tileW = imagenes.grama.getWidth();
		int tileH = imagenes.grama.getHeight();

		// Lugar en el q se calcula la posicion de la matriz
		int i = (int) (((double) pickX / (double) tileW)
				+ ((double) pickY / (double) tileH) - 0.5);
		int j = (int) (((double) pickY / (double) tileH)
				- ((double) pickX / (double) tileW) + 0.5);

		if (matriz.leerDato(i, j) != null) {
			// seleccion = colision;
			repaint();
		}
		// colision=seleccion;

		if ((e.getX() < ANCHO / 2 - 375 + 15 + 132 && e.getX() > ANCHO / 2 - 375 + 15)) {
			// StartPanel.click2.play();
		}

		int dx = e.getX() * imagen.getImagen().getWidth() / 2 - e.getY()
				* imagen.getImagen().getWidth() / 2;
		int dy = e.getX() * imagen.getImagen().getHeight() / 2 + e.getY()
				* imagen.getImagen().getHeight() / 2;
		dx -= ubicacion.x;
		dy -= ubicacion.y;

		// if (matriz.leerDato(i,
		// j).getConstRef().getID()=="plantaElectricidad")
		// {StartPanel.soundElectricity.play();
		// repaint();
		// } else{
		// StartPanel.soundElectricity.stop();
		// }

		if (matriz.leerDato(i, j) != null) {

			setCoordX(i);
			setCoordY(j);
			// setSonido_tile(true);

			repaint();

		} else {

			setCoordX(0);
			setCoordY(0);
		}
		// StartPanel.soundElectricity.stop();
		// StartPanel.soundStadium.stop();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		float ANCHO = this.getWidth();
		float ALTO = this.getHeight();

		if (((e.getX() < this.getWidth() / 3 + 60) && e.getX() > this
				.getWidth() / 3 + 5)
				&& (e.getY() > this.getHeight() - 70 && e.getY() < this
						.getHeight() - 23)) {

			sonidos.click.play();

			if (!isConstruir()) {
				setConstruir(true);
				repaint();
			} else {
				setConstruir(false);
				repaint();
			}
		}

		if (((e.getX() < this.getWidth() / 3 + 350) && e.getX() > this
				.getWidth() / 3 + 285)
				&& (e.getY() > this.getHeight() - 70 && e.getY() < this
						.getHeight() - 23)) {

			// if (!extras) {
			// extras = true;
			// repaint();
			// } else {
			// extras = false;
			// repaint();
			// }

			// terremoto = true;

		}

		if (((e.getX() < this.getWidth() / 3 + 140) && e.getX() > this
				.getWidth() / 3 + 80)
				&& (e.getY() > this.getHeight() - 70 && e.getY() < this
						.getHeight() - 23)) {
			if (out) {
				StartPanel.click.play();
				in = true;
				out = false;
				scale = scale + 0.4;
				repaint();
			}

		}

		if (((e.getX() < this.getWidth() / 3 + 270) && e.getX() > this
				.getWidth() / 3 + 210)
				&& (e.getY() > this.getHeight() - 70 && e.getY() < this
						.getHeight() - 23)) {
			if (in) {
				StartPanel.click.play();
				out = true;
				in = false;
				scale = scale - 0.4;
				repaint();
			}
		}

		if (((e.getX() < this.getWidth() / 3 + 950) && e.getX() > this
				.getWidth() / 3 + 785)
				&& (e.getY() > this.getHeight() - 130 && e.getY() < this
						.getHeight() - 23)) {

			if (!extras) {
				extras = true;
				repaint();
			} else {
				extras = false;
				repaint();
			}

			terremoto = true;

		}

		int pickX = e.getX() + ubicacion.x;
		int pickY = e.getY() + ubicacion.y;
		int tileW = imagenes.grama.getWidth();
		int tileH = imagenes.grama.getHeight();

		// Lugar en el q se calcula la posicion de la matriz
		final int i = (int) (((double) pickX / (double) tileW)
				+ ((double) pickY / (double) tileH) - 0.5);
		final int j = (int) (((double) pickY / (double) tileH)
				- ((double) pickX / (double) tileW) + 0.5);

		if (matriz.leerDato(i, j) != null) {
			Pmenu = new JPopupMenu();

			// if (matriz.leerDato(i, j).getConstRef().getID() ==
			// "companiaAseoUrbano") {
			// menuItem = new JMenuItem("Limpiar Ciudad");
			// menuItem.addActionListener(new ActionListener() {
			//
			// @Override
			// public void actionPerformed(ActionEvent e) {
			// // indiceContaminacion = 0;
			// // iniciarCobroAseo = false;
			// basura = false;
			// sonidos.soundAseo.play();
			// }
			// });
			//
			// }

			if (matriz.leerDato(i, j).getConstRef().getID() == "gobernacionMerida") {
				if (cobrarAlquiler) {
					for (int j2 = 0; j2 < matriz.getFilas(); j2++) {
						for (int k = 0; k < matriz.getColumnas(); k++) {
							if (matriz.leerDato(j2, k) != null
									&& matriz.leerDato(j2, k).getConstRef()
											.getCobrar()) {
								matriz.leerDato(j2, k).getConstRef()
										.setCobrar(false);
								parametros.lochas += matriz.leerDato(j2, k)
										.getConstRef().alquiler;

							}
						}
					}
					cobrarAlquiler = false;

				}
				repaint();
			}
			if (matriz.leerDato(i, j).getConstRef().getID() == "companiaAseoUrbano") {
				basura = false;
				sonidos.soundAseo.play();
				for (int j2 = 0; j2 < 5; j2++) {
					if (parametros.nivel == (j2 + 1)) {
						parametros.lochas -= (j2 + 1) * 100;
						break;
					}
				}

				repaint();
			}
		}

		if (matriz.leerDato(i, j) != null
				&& matriz.leerDato(i, j).getConstRef().getCobrar()) {
			parametros.lochas += matriz.leerDato(i, j).getConstRef().alquiler;
			matriz.leerDato(i, j).getConstRef().setCobrar(false);
			matriz.leerDato(i, j).getConstRef().timer.restart();
		}

		if (matriz.leerDato(i, j) != null
				&& e.getButton() == MouseEvent.BUTTON3) {
			Pmenu = new JPopupMenu();
			menuItem = new JMenuItem("Mover Edificacion");
			menuItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					objeto = new Construccion(matriz.leerDato(i, j)
							.getConstRef().getID());
					matriz.escribirDato(i, j, null);
					Mapa.ESTADO = Estados.CONSTRUCCION;
					itemMover = true;
					setClick(true);
					repaint();
				}
			});
			Pmenu.add(menuItem);
			menuItem = new JMenuItem("Eliminar Edificacion");
			menuItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					matriz.escribirDato(i, j, null);
					repaint();
				}
			});
			Pmenu.add(menuItem);

			Pmenu.show(e.getComponent(), e.getX(), e.getY());
		}

		if ((e.getX() < ANCHO / 2 - 375 + 15 + 132 && e.getX() > ANCHO / 2 - 375 + 15)
				&& (e.getY() < 80 && e.getY() > 42) && isConstruir()) {

			StartPanel.click.play();
			// System.out.println("Edificaciones");
			fireActionEvent(new ActionEvent(this, 0, "Edificaciones"));

		}

		else if ((e.getX() < ANCHO / 2 - 375 + 285 && e.getX() > ANCHO / 2 - 375 + 159)
				&& (e.getY() < 80 && e.getY() > 42) && isConstruir()) {
			StartPanel.click.play();
			// System.out.println("Carretera");
			fireActionEvent(new ActionEvent(this, 0, "Carretera"));

		}

		else if ((e.getX() < ANCHO / 2 - 375 + 423 && e.getX() > ANCHO / 2 - 375 + 298)
				&& (e.getY() < 80 && e.getY() > 42) && isConstruir()) {
			StartPanel.click.play();
			// System.out.println("Servicios");
			fireActionEvent(new ActionEvent(this, 0, "Servicios"));

		}

		else if ((e.getX() < ANCHO / 2 - 375 + 563 && e.getX() > ANCHO / 2 - 375 + 438)
				&& (e.getY() < 80 && e.getY() > 42) && isConstruir()) {
			StartPanel.click.play();
			// System.out.println("Industrias");
			fireActionEvent(new ActionEvent(this, 0, "Industrias"));

		}

		else if ((e.getX() < ANCHO / 2 - 375 + 704 && e.getX() > ANCHO / 2 - 375 + 578)
				&& (e.getY() < 80 && e.getY() > 42) && isConstruir()) {
			StartPanel.click.play();
			// System.out.println("Comunitarios");
			fireActionEvent(new ActionEvent(this, 0, "Comunitarios"));

		} else if (((e.getX() < this.getWidth() / 3 + 200) && e.getX() > this
				.getWidth() / 3 + 150)
				&& (e.getY() > this.getHeight() - 70 && e.getY() < this
						.getHeight() - 23)) {
			StartPanel.click.play();
			fireActionEvent(new ActionEvent(this, 0, "Pause"));
		}

		else if (isClick() == true) {
			

			if (matriz.leerDato(i, j) != null) {

				// seleccion = colision;
				// System.out.println("no se puede construir casa");

				setSonido_tile(true);

				repaint();

			} else {
				// StartPanel.soundElectricity.stop();
				// StartPanel.soundStadium.stop();

				// System.out.println(i + " " + j);
				if (objeto.costo < parametros.lochas) {

					if (itemMover) {
						matriz.escribirDato(i, j, new Celda(null,
								new Construccion(objeto.getID())));

						matriz.leerDato(i, j).getConstRef()
								.setConstruido("listo");
						indiceContaminacion += matriz.leerDato(i, j)
								.getConstRef().getContaminacion();

						setXs(i);
						setYs(j);

						new Reminder(2, i, j);
						StartPanel.click2.play();
						setClick(false);
						ESTADO = Estados.NAVEGACION;
					}

					crearCasa(i, j);
					System.out.println("i: " + i + " j: " + j);
					construcciones++;
					if (matriz.leerDato(i, j).getConstRef().tipo == 1) {
						parametros.lochas -= matriz.leerDato(i, j)
								.getConstRef().costo;
						parametros.habitantes += matriz.leerDato(i, j)
								.getConstRef().habitantes;
						parametros.xp += matriz.leerDato(i, j).getConstRef().xp;
						parametros.contaminacion += matriz.leerDato(i, j)
								.getConstRef().contaminacion;
						parametros.delincuencia += matriz.leerDato(i, j)
								.getConstRef().delincuencia
								+ matriz.leerDato(i, j).getConstRef().violencia;
					} else if (matriz.leerDato(i, j).getConstRef().tipo == 2) {
						parametros.lochas -= matriz.leerDato(i, j)
								.getConstRef().costo;
						parametros.limite += matriz.leerDato(i, j)
								.getConstRef().habitantes;
						parametros.xp += matriz.leerDato(i, j).getConstRef().xp;
						parametros.contaminacion += matriz.leerDato(i, j)
								.getConstRef().contaminacion;
						parametros.delincuencia += matriz.leerDato(i, j)
								.getConstRef().delincuencia
								+ matriz.leerDato(i, j).getConstRef().violencia;
					} else if (matriz.leerDato(i, j).getConstRef().tipo == 0) {
						parametros.lochas -= matriz.leerDato(i, j)
								.getConstRef().costo;
						parametros.xp += matriz.leerDato(i, j).getConstRef().xp;
						parametros.contaminacion += matriz.leerDato(i, j)
								.getConstRef().contaminacion;
					}

					if (parametros.delincuencia > 0
							&& parametros.delincuencia < 20) {
						indiceDelincuencia = 1;
						felicidad -= 50;
					} else if (parametros.delincuencia >= 20
							&& parametros.delincuencia < 40) {
						indiceDelincuencia = 2;
						felicidad -= 150;
					} else if (parametros.delincuencia >= 40) {
						indiceDelincuencia = 3;
						aviso = 3;
						felicidad -= 300;
					}

					if (parametros.contaminacion > 0
							&& parametros.contaminacion < 10) {
						indiceContaminacion = 1;
						felicidad -= 50;
					} else if (parametros.contaminacion >= 10
							&& parametros.contaminacion < 20) {
						indiceContaminacion = 2;
						felicidad -= 150;
					} else if (parametros.contaminacion >= 20) {
						indiceContaminacion = 3;
						aviso = 3;
						felicidad -= 300;
					}

					if (parametros.habitantes <= parametros.limite * 0.2) {
						felicidad -= 25;
					}
					if (parametros.habitantes >= parametros.limite * 0.9) {
						felicidad -= 75;
					}

					if (parametros.xp > 10 && parametros.xp <= 20) {
						parametros.nivel = 2;
						parametros.limite += 50;
					} else if (parametros.xp > 20 && parametros.xp <= 50) {
						parametros.nivel = 3;
						parametros.limite += 40;
					} else if (parametros.xp > 50 && parametros.xp <= 80) {
						parametros.nivel = 4;
						parametros.limite += 30;
					} else if (parametros.xp > 70) {
						parametros.nivel = 5;
						parametros.limite += 25;
					}

				} else
					ESTADO = Estados.NAVEGACION;
			}
			repaint();
			// setClick(false);

		}

	}

	protected void itemMover() {
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

		// // Set cursor for the frame component
		// Toolkit toolkit = Toolkit.getDefaultToolkit();
		// Image image = toolkit.getImage("src/ISIS/Code/Matriz/43.gif");
		// Cursor c = toolkit.createCustomCursor(image , new
		// Point(this.getX(),this.getY()), "img");
		// setCursor (c);
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
			if (ubicacion.x > ancho / 2 - this.getWidth()
					+ imagenes.grama.getWidth()) {
				ubicacion.x = ancho / 2 - this.getWidth()
						+ imagenes.grama.getWidth();
			}
			if (ubicacion.y > alto / 2 + this.getHeight()) {
				ubicacion.y = alto / 2 + this.getHeight();
			}

			repaint();
		}

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

	public boolean isClick() {
		return click;
	}

	public void setClick(boolean click) {
		this.click = click;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		// if (e.getWheelRotation() < 0) {
		// scale = scale + 0.1;
		// repaint();
		// } else if (e.getWheelRotation() > 0) {
		// scale = scale - 0.1;
		// repaint();
		// }

	}

	public static boolean isConstruir() {
		return construir;
	}

	public static void setConstruir(boolean construir) {
		Mapa.construir = construir;
	}

	public static String getObjetoComprado() {
		return objetoComprado;
	}

	public static void setObjetoComprado(String objetoComprado) {
		Mapa.objetoComprado = objetoComprado;
	}

	public static int getCoordX() {
		return coordX;
	}

	public static void setCoordX(int coordX) {
		Mapa.coordX = coordX;
	}

	public static int getCoordY() {
		return coordY;
	}

	public static void setCoordY(int coordY) {
		Mapa.coordY = coordY;
	}

	public static boolean isTile_construyendo() {
		return tile_construyendo;
	}

	public static void setTile_construyendo(boolean tile_construyendo) {
		Mapa.tile_construyendo = tile_construyendo;
	}

	public static boolean isSonido_tile() {
		return sonido_tile;
	}

	public static void setSonido_tile(boolean sonido_tile) {
		Mapa.sonido_tile = sonido_tile;
	}

	public static int getXs() {
		return xs;
	}

	public static void setXs(int xs) {
		Mapa.xs = xs;
	}

	public static int getYs() {
		return ys;
	}

	public static void setYs(int ys) {
		Mapa.ys = ys;
	}

	public static int getAuxXs() {
		return auxXs;
	}

	public static void setAuxXs(int auxXs) {
		Mapa.auxXs = auxXs;
	}

	public static int getAuxYs() {
		return auxYs;
	}

	public static void setAuxYs(int auxYs) {
		Mapa.auxYs = auxYs;
	}

	public double getFelicidad() {
		return felicidad;
	}

	public double distancia(int x1, int x2, int y1, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	public void estadisticas() {
		for (int i = 0; i < matriz.getFilas(); i++) {
			for (int j = 0; j < matriz.getColumnas(); j++) {
				if (matriz.leerDato(i, j) != null) {
					// System.out.println(matriz.leerDato(i, j).getConstRef()
					// .getID());
					felicidad += acumular(matriz, i, j);

					if (felicidad < 150 * parametros.nivel && felicidad >= 0) {
						indice_animo = 1;
					}
					if (felicidad < 300 * parametros.nivel
							&& felicidad > 150 * parametros.nivel)
						indice_animo = 2;
					if (felicidad < 450 * parametros.nivel
							&& felicidad > 300 * parametros.nivel)
						indice_animo = 3;
					if (felicidad > 450 * parametros.nivel)
						indice_animo = 4;

					repaint();
				}
			}
		}
	}

	public double acumular(ModeloMapa m, int x, int y) {
		double acum = 0;

		for (int i = x; i < m.getFilas(); i++) {
			for (int j = y; j < m.getColumnas(); j++) {
				if (m.leerDato(i, j) != null) {
					double d = distancia(x, i, y, j);
					if (d != 0) {
						double mu1 = m.leerDato(x, y).getConstRef().getMu();
						double mu2 = m.leerDato(i, j).getConstRef().getMu();
						acum += m.leerDato(x, y).getConstRef().normal(d, mu1);
						acum += m.leerDato(i, j).getConstRef().normal(d, mu2);
					}
				}
			}
		}
		return acum;
	}

	public void setFelicidad(double felicidad) {
		this.felicidad = felicidad;
	}

	public void setFelicidadEstatica(double f) {
		felicidadEstatica = f;

	}

	public double getFelicidadEstatica() {
		return felicidadEstatica;
	}

	public int getConstrucciones() {
		return construcciones;
	}

	public void setConstrucciones(int construcciones) {
		this.construcciones = construcciones;
	}

	public boolean isCargar() {
		return cargar;
	}

	public void setCargar(boolean cargar) {
		this.cargar = cargar;
	}

}
