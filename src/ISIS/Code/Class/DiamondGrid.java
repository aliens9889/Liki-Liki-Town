package ISIS.Code.Class;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


/*
 * Ejemplo de una aplicacion que usa una
 * red de diamantes o rombos
 */

/**
 * 
 * @author ISIS
 */

public class DiamondGrid extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private static final int ANCHO_TABLERO = 20;
	private static final int ALTO_TABLERO = 20;
	
	private static final int NUM_ESQUINAS_ROMBO = 4;
	private static final int RADIO_CELDA = 60;
	
    private static DiamondGridCell Gridarray = new DiamondGridCell(RADIO_CELDA); //vector de cuadricula

    public int initialized = 0;
    int prevx = 0;
    int prevy = 0;
    
    private BufferedImage grama;
    

    
    
	private int [][]matrizCeldas = {
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
			};
	
	private int []esquinasX = new int [NUM_ESQUINAS_ROMBO];
	private int []esquinasY = new int [NUM_ESQUINAS_ROMBO];
	
	private static DiamondGridCell celda = new DiamondGridCell(RADIO_CELDA);

	
	public void tamanoGrama(){

		try {
			grama = ImageIO.read(ClassLoader.getSystemResourceAsStream("grama.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		
		grama = gc.createCompatibleImage(this.getWidth(),
				this.getHeight(), Transparency.BITMASK);
		
	}
	

	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		for(int i = 0; i < ALTO_TABLERO; i++){
			for(int j = 0; j < ANCHO_TABLERO; j++){
				Gridarray.setIndexCelda(j, i);
				Gridarray.calcularEsquinas(esquinasX, esquinasY);
				g.setColor(Color.black);
				g.drawPolygon(esquinasX, esquinasY, NUM_ESQUINAS_ROMBO);
				//g.fillRect(i, j, ALTO_TABLERO, ALTO_TABLERO);
			}
		}
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		
//		int dx = 0;
//		int dy = 0;
		
		for(int i = 0; i < ALTO_TABLERO; i++){
			for(int j = 0; j < ANCHO_TABLERO; j++){
				
				
				celda.setIndexCelda(i, j);
				
				if (matrizCeldas[j][i] != 0){
					celda.calcularEsquinas(esquinasX, esquinasY);
					g.fillPolygon(esquinasX, esquinasY, NUM_ESQUINAS_ROMBO);
					g.setColor(Color.gray);
					g.drawPolygon(esquinasX, esquinasY, NUM_ESQUINAS_ROMBO);
				}
			}
		}
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	/*
	 * Retorna true si la celda esta dentro del tablero de juego
	 * i es el indice horizontal de la celda 
	 * j es el indice vertical de la celda
	 */
	private boolean estaDentroDeTablero(int i, int j){
		return (i >= 0 && i < ANCHO_TABLERO && j >= 0 && j < ALTO_TABLERO && matrizCeldas[j][i] != 0);
	}
	
	public DiamondGrid() {
		setPreferredSize(new Dimension(700, 600));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(MouseEvent.BUTTON1 == e.getButton()){
					Gridarray.setCeldaPorPunto(e.getX(), e.getY());
					int clickI = Gridarray.getIndexI();
					int clickJ = Gridarray.getIndexJ();
					prevx = clickI;
					prevy = clickJ;
					
					if(estaDentroDeTablero(clickI, clickJ)){
						System.out.println("Estas dentro del mapa");
						System.out.println("centro x: " + Gridarray.getCenterX());
						System.out.println("centro y: " + Gridarray.getCenterY());
					
//						prevy = clickI;
//						prevx = clickJ;
					}
				}
			}
		});
	}
	
	
//	public static void main(String[] args) {
//		
//		JFrame ventana = new JFrame("Mapa");
//		ventana.setBackground(null);
//		ventana.setSize(700, 600);
//		ventana.setVisible(true);
//		ventana.getContentPane().add(new DiamondGrid());
//		System.out.println("por aqui");
//		
//		ventana.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				System.exit(0);
//			}
//		});
//		Container contentPane = ventana.getContentPane();
//		contentPane.add(new DiamondGrid());
//		ventana.setVisible(true);
//	
//	}
	
}


















