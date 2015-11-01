package ISIS.Code.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Map;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.EventListenerList;
import javax.swing.table.DefaultTableModel;

import org.edisoncor.gui.button.ButtonSeven;
import org.edisoncor.gui.label.LabelRound;

public class LoadPanel extends JPanel {

	private EventListenerList eventListenerList = new EventListenerList();
	private ButtonSeven btnAtras;
	private ButtonSeven btnCargar;
	private ButtonSeven btnBorrar;
	private JLabel lblBackground;
	private LabelRound lblSeleccion;
	private JTable table;
	private DefaultTableModel modelo;
	private String alcalde;
	private String ciudad;

	public LoadPanel() {
		initComponents();
	}

	private void initComponents() {

		String encabezado[] = { "Alcaldes", "Ciudades" };
		String datos[][] = cargarUsuarios();

		setPreferredSize(new java.awt.Dimension(Toolkit.getDefaultToolkit()
				.getScreenSize().getSize()));
		setLayout(null);
		lblBackground = new JLabel();

		setPreferredSize(new java.awt.Dimension(Toolkit.getDefaultToolkit()
				.getScreenSize().getSize()));

		btnAtras = new ButtonSeven("Pa' tras");
		btnAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireActionEvent(new ActionEvent(this, 0, "Prev"));
			}
		});
		add(btnAtras);
		btnAtras.setBounds(0,
				Toolkit.getDefaultToolkit().getScreenSize().height - 100, 85,
				26);

		lblSeleccion = new LabelRound();
		lblSeleccion.setText("Selecciona para Cargar Partida");
		lblSeleccion.setFont(new Font("Arial", 1, 24));
		add(lblSeleccion);
		lblSeleccion.setBounds(
				Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 253,
				10, 500, 29);
		btnCargar = new ButtonSeven("Cargar");
		btnCargar.setEnabled(false);
		btnCargar.setBounds(
				Toolkit.getDefaultToolkit().getScreenSize().width - 100,
				Toolkit.getDefaultToolkit().getScreenSize().height - 100, 97,
				26);
		btnCargar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireActionEvent(new ActionEvent(this, 0, "Load"));
			}
		});
		add(btnCargar);

		btnBorrar = new ButtonSeven("Borrar");
		btnBorrar.setEnabled(false);
		btnBorrar.setBounds(
				Toolkit.getDefaultToolkit().getScreenSize().width - 560,
				Toolkit.getDefaultToolkit().getScreenSize().height - 100, 97,
				26);
		btnBorrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				borrarItemTabla(e);
			}
		});

		btnBorrar.setEnabled(false);
		add(btnBorrar);
		modelo = new DefaultTableModel(datos, encabezado);
		table = new JTable(modelo);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		table.setBackground(Color.BLACK);
		table.setForeground(Color.WHITE);
		table.setFont(new Font("Arial", Font.BOLD, 18));
		table.getColumnModel()
				.getColumn(0)
				.setHeaderRenderer(
						new RenderLoadPanel(Color.LIGHT_GRAY, Color.BLUE,
								new Font("Arial", Font.BOLD, 20)));
		table.getColumnModel()
				.getColumn(1)
				.setHeaderRenderer(
						new RenderLoadPanel(Color.LIGHT_GRAY, Color.BLUE,
								new Font("Arial", Font.BOLD, 20)));
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tableMouseClicked(e);
			}
		});

		table.setRowHeight(35);

		JScrollPane scroll = new JScrollPane(table);
		add(scroll);
		scroll.setBounds(260, 80, 500, 400);

		lblBackground.setIcon(new ImageIcon(getClass().getResource(
				"/ISIS/Resource/Backgrounds/BackCityLoad.jpg")));
		add(lblBackground);
		lblBackground.setBounds(0, 0, Toolkit.getDefaultToolkit()
				.getScreenSize().getSize().width, Toolkit.getDefaultToolkit()
				.getScreenSize().getSize().height);
	}

	protected void borrarItemTabla(ActionEvent e) {
		modelo.removeRow(table.getSelectedRow());
	}

	protected void tableMouseClicked(MouseEvent e) {
		String cadena = "";
		int row = table.rowAtPoint(e.getPoint());
		if (row >= 0 && table.isEnabled()) {
			for (int i = 0; i < table.getColumnCount(); i++) {
				cadena = cadena + "," + table.getValueAt(row, i).toString();
			}
		}
		String alcalde = "";
		String ciudad = "";
		cadena = cadena.substring(1, cadena.length());
		int espacio = cadena.indexOf(',');
		for (int i = 0; i < espacio; i++) {
			alcalde += cadena.charAt(i);
		}
		for (int i = espacio + 1; i < cadena.length(); i++) {
			ciudad += cadena.charAt(i);
		}
		this.alcalde = alcalde;
		this.ciudad = ciudad;
		btnCargar.setEnabled(true);
		btnBorrar.setEnabled(true);
	}

	private int numeroDeFilas() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		int filas = 0;
		try {

			archivo = new File("src/ISIS/Code/Resource/Guardado/usuarios.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				filas++;
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

		return filas;

	}

	public String[][] cargarUsuarios() {

		String datos[][] = new String[numeroDeFilas()][2];

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		FileInputStream fis = null;
		Properties ppt = new Properties();

		String alcalde = "";
		String ciudad = "";

		int filas = 0;

		try {

			archivo = new File("src/ISIS/Code/Resource/Guardado/usuarios.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				String extension = "";
				int punto = 0;
				for (int i = linea.length() - 1; i > 0; i--) {
					if (linea.charAt(i) == '.') {
						punto = i;
						break;
					}
				}
				for (int i = punto; i < linea.length(); i++) {
					extension += linea.charAt(i);
				}

				if (extension.equals(".properties")) {
					fis = new FileInputStream(
							"src/ISIS/Code/Resource/Guardado/" + linea);
					ppt.load(fis);
					for (Map.Entry<Object, Object> linea_ppt : ppt.entrySet()) {
						if (linea_ppt.getKey().equals("alcalde")) {
							alcalde = linea_ppt.getValue().toString();
						} else if (linea_ppt.getKey().equals("ciudad")) {
							ciudad = linea_ppt.getValue().toString();
						}
					}
				}

				datos[filas][0] = alcalde;
				datos[filas][1] = ciudad;

				alcalde = new String("");
				ciudad = new String("");
				filas++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return datos;
	}

	public static void main(String[] args) {
		GraphicsDevice grafica = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		JFrame frame = new JFrame();
		LoadPanel pnl = new LoadPanel();
		frame.add(pnl);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		grafica.setFullScreenWindow(frame);
		frame.setLocationRelativeTo(null);
		// frame.setSize(800, 600);
		frame.setVisible(true);
	}

	public String getAlcalde() {
		return alcalde;
	}

	public void setAlcalde(String alcalde) {
		this.alcalde = alcalde;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
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
