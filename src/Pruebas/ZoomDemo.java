package Pruebas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ZoomDemo extends JPanel {

	AffineTransform tx = new AffineTransform();

	Rectangle2D.Double rect = new Rectangle2D.Double(100, 100, 20, 30);

	public ZoomDemo() {
		this.addMouseWheelListener(new ZoomHandler());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		Path2D.Double path;
		g2.setColor(Color.RED);
		path = new Path2D.Double(rect, tx);
		g2.draw(path);
	}

	private class ZoomHandler implements MouseWheelListener {

		Point oldPoint = null;

		double scale = 1.0;

		public void mouseWheelMoved(MouseWheelEvent e) {
			if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {

				scale += (.1 * e.getWheelRotation());
				scale = Math.max(0.1, scale);
				Point p = e.getPoint();

				tx = AffineTransform.getTranslateInstance(p.getX(), p.getY());
				tx.scale(scale, scale);
				tx.translate(-p.getX(), -p.getY());

				ZoomDemo.this.revalidate();
				ZoomDemo.this.repaint();
			}
		}
	}

	public static void main(String[] args) {

		JFrame f = new JFrame("ZoomDemo");
		ZoomDemo zoomDemo = new ZoomDemo();
		JScrollPane sp = new JScrollPane(zoomDemo);
		f.getContentPane().add(sp);
		f.setSize(500, 500);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}