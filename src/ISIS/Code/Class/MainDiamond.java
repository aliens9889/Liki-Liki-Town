package ISIS.Code.Class;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 
 * @author ISIS
 */

public class MainDiamond extends JPanel{

	public static void main(String[] args) {
		JFrame frame = new JFrame();
	    DiamondGrid mapa=new DiamondGrid();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  
	   
	    JScrollPane jScrollPane = new JScrollPane(mapa);
	    
	    frame.add(jScrollPane, BorderLayout.CENTER);
	    frame.setSize(1000, 550);
	    frame.setVisible(true);
	}
}
