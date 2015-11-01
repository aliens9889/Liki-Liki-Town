//package com.rush;
//
//
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//
//
// 
//     
//
//public class GridTest extends JPanel {
//	 private JScrollPane capa;
//	 private static final int CELL_RADIUS = 150;
//	 private static final int BOARD_WIDTH = 10;
//     private static final int BOARD_HEIGHT = 7;
//     private static final int NUM_HEX_CORNERS = 6;
//     private int[] mCornersX = new int[NUM_HEX_CORNERS];
//     private int[] mCornersY = new int[NUM_HEX_CORNERS];
//     private static HexGridCell Gridarray = new HexGridCell(CELL_RADIUS);
//     public Territorio z[][] = new Territorio[BOARD_HEIGHT][BOARD_WIDTH];
//     public Unit u1 = new Unit(1,0);
//     public Unit u = new Unit(1,0);
//     public int initialized=0;
// 	 int prevx=0;
// 	 int prevy=0;
// 	
//     
// 	 private int[][] mCells = {
//    		                  { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
//				              { 1, 0, 1, 1, 1, 1, 0, 0, 1, 0 }, 
//				              { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//				              { 8, 2, 0, 0, 0, 0, 0, 0, 2, 9 },
//				              { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//				              { 0, 0, 0, 0, 2, 0, 0, 0, 2, 0 },
//				              { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
//				              };
//	
//	
//	private static final long serialVersionUID = 1L;
//
//
//	public void fillter()
//	{
//		int w=0;
//	for (int j = 0; j < BOARD_HEIGHT; j++) {
//	    for (int i = 0; i < BOARD_WIDTH; i++) {
//	    	z[j][i] = new Territorio(mCells[j][i],w);
//	    	w++;
//	    		}
//	
//			}
//	}
//
//
//	
//	
//public void paintComponent(Graphics g) {
//    super.paintComponent(g);
//
//    if(initialized==0)
//    {
//    	this.fillter();
//    	initialized=1;
//    }
//    
//    	  	
//        for (int j = 0; j < BOARD_HEIGHT; j++) {
//          for (int i = 0; i < BOARD_WIDTH; i++) {
////        	    Gridarray.setCellIndex(i, j);
////                Gridarray.computeCorners(mCornersX, mCornersY);
////                
////                g.drawImage(z[j][i].imgt.getImage(),Gridarray.getLeft(),Gridarray.getTop(),null);
////                g.setColor(Color.black);
////                g.drawPolygon(mCornersX, mCornersY, NUM_HEX_CORNERS);
////                z[j][i].placeholders(Gridarray.getCenterX()-20,Gridarray.getCenterY()-20);
////                if(z[j][i].getunits()==true)
////                {
////                	g.drawImage(u.img.getImage(), u.locx, u.locy, null, null);
////                	
////                }
//        	  
//        	  System.out.println("i: "+i+" j: "+j);
//        	  
//            }
//        }
//        
//    
//   }
//
//private boolean isInsideBoard(int i, int j) {
//    return i >= 0 && i < BOARD_WIDTH && j >= 0 && j < BOARD_HEIGHT;
//}
//
//public GridTest() {
//	
//	//capa=new JScrollPane(this);
//	//this.setBackground(Color.BLACK);
//
//	
//	this.setPreferredSize(new Dimension(2000, 2000));
//	
//	
//	
//
//	addMouseListener(new MouseAdapter() { 
//      
//public void mousePressed(MouseEvent arg0) {
//
//		
//	if(MouseEvent.BUTTON1==arg0.getButton())
//	{
//    Gridarray.setCellByPoint(arg0.getX(), arg0.getY());
//    int clickI = Gridarray.getIndexI();
//    int clickJ = Gridarray.getIndexJ();
//    prevx=clickI;
//    prevy=clickJ;
//    
//    if (isInsideBoard(clickI, clickJ)) 
//    		{
//    	 	
//     		System.out.print("Estas en el Hexagono:");
//    		System.out.println(z[clickJ][clickI].getId());    		
//    		System.out.print("Terreno:");
//    		System.out.println(z[clickJ][clickI].getTipo());
//    		System.out.print("Defensa:");
//    		System.out.println(z[clickJ][clickI].getDefensa());
//    		System.out.print("Dificultad de Movimiento:");
//    		System.out.println(z[clickJ][clickI].getMovimiento());
//    		System.out.print("Unidades presentes:");
//    		if(z[clickJ][clickI].getunits()==true)
//    			System.out.println("SI");
//    		else
//    			System.out.println("NO");
//    		prevy=clickI;
//    	    prevx=clickJ;
//    	    		   		                           
//        }
//	}
//
//	if(MouseEvent.BUTTON3==arg0.getButton())
//	{
//		Gridarray.setCellByPoint(arg0.getX(), arg0.getY());
//		int clickI = Gridarray.getIndexI();
//	    int clickJ = Gridarray.getIndexJ();
//
//	    if (isInsideBoard(clickI, clickJ))
//	    	 if(z[prevx][prevy].getunits()==true)
//	    	 {
//	    {
//	    	System.out.println("CLICKEASTE CON EL BOTON DERECHO");
//            if(z[clickJ][clickI].tipo!=1)
//            {	
//            JOptionPane.showMessageDialog(new JFrame(), "Moveras el tanquesito");
//            u.play();
//            z[prevx][prevy].remunits();
//            z[clickJ][clickI].placeunits();
//            u.setlocation(z[clickJ][clickI].getphx(),z[clickJ][clickI].getphy());
//	    repaint();
//            }
//	    }
//	    	 }
//
//	}
//	
//	if(MouseEvent.BUTTON2==arg0.getButton())
//	{
//		Gridarray.setCellByPoint(arg0.getX(), arg0.getY());
//		int clickI = Gridarray.getIndexI();
//	    int clickJ = Gridarray.getIndexJ();
//
//	    if (isInsideBoard(clickI, clickJ))
//	    {
//
//            
//            if(z[clickJ][clickI].tipo==8)
//            {
//            JOptionPane.showMessageDialog(new JFrame(), "Colocaras un tanque");
//            u.setlocation(z[clickJ][clickI].getphx(), z[clickJ][clickI].getphy());
//            z[clickJ][clickI].placeunits();	    	
//            repaint();
//            }
//            }
//	    }
//
//	
//	
//
//
//}
//
//public void mouseReleased(MouseEvent arg0) {
//	
//}
//
//public void mouseClicked(MouseEvent arg0) {
//}
//
//public void mouseEntered(MouseEvent arg0) {
//}
//
//public void mouseExited(MouseEvent arg0) {
//}
//
//    });
//}
//
//
//public static void main(String[] args) {
//   
//	JFrame frame = new JFrame();
//    frame.setTitle("Mapa");
//    frame.setBackground(Color.black);
//    frame.setSize(782, 678);
//    frame.setResizable(false);
//    frame.getContentPane().add(new GridTest());
//   
//    
//    frame.addWindowListener(new WindowAdapter() {
//       	
//  public void windowClosing(WindowEvent e) {
//      System.exit(0);
//      }
//    });
//    Container contentPane = frame.getContentPane();
//    contentPane.add(new GridTest());
//
//    frame.setVisible(true);
//  }
//
//
//
//
//
//
//
//
//
//
//}