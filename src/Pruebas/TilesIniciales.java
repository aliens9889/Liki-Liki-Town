/**
 * 
 */
package Pruebas;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import ISIS.Code.Matriz.Mapa;

/**
 * @author ISIS - A-2012 - ULA
 * 
 */
public class TilesIniciales{

	public static int k = 0;
	public Image img10;

	public void dibujarEfectos(Graphics g) {

		img10 = Toolkit.getDefaultToolkit().getImage(
				"src/ISIS/Resource/HUDImages/nube3.png");
		// System.err.println(i);
//		g.drawImage(img10, k, 0, (ImageObserver) this);
		k++;
//		if (k == this.getWidth()) {
//			k = 0;
//		}

	}

}
