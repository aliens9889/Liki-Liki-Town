/**
 * 
 */
package ISIS.Code.Panels;

/**
 * @author ISIS 
 *
 */

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class AgarrarSoltarImagen extends JComponent implements MouseMotionListener {
	
	private  BufferedImage imagen;
	private  int x = 0;
	private  int y = 0;
	

	
	public AgarrarSoltarImagen(){
		initComponent();
		addMouseMotionListener(this);
	}

	private void initComponent() {
		try {
			 imagen = ImageIO.read(new File("C:/Users/Imagenes/test.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint (Graphics g){
		g.drawImage(imagen, x, y, this);
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		x = me.getX();
		y = me.getY();
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent me) {
	
	}
	

}
