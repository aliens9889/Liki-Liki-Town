package ISIS.Code.Class;

import java.awt.FlowLayout;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author ISIS
 */

public class Principal extends JPanel {

	JLabel texto;

	hiloTransportador ht;

	public Principal() {
		// TODO Auto-generated constructor stub
		init();
	}

	public void init() {
		// setLayout(null);
		String str = new String("<html><p>Línea 1</p><p>Línea 2</p></html>");
		texto = new JLabel(str);

		ht = new hiloTransportador(this);
		ht.start();
		add(texto);
		System.out.println("entra");
	}

	public JLabel getTexto() {
		return texto;
	}

}
