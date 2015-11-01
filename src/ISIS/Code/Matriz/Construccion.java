package ISIS.Code.Matriz;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Map;
import java.util.Properties;

import javax.swing.Timer;

public class Construccion {

	private String ID; // Nombre de la construccion
	private BufferedImage imagen; // Imagen de la construccion, el id deberia
	// ser el mismo nombre de la imagen en carpeta + la extension
	private Point posicion = null; // Lista de posiciones por si la imagen es
									// mas grande
	private Properties properties;
	public Timer timer;
	private boolean cobrar = false;
	
	// Parametros
	public int tipo; // Tipo 0 Carreteras o los q no producen Tipo 1
						// Edificaciones Tipo 2 Otros
	public int salud;
	public int educacion;
	public int violencia;
	public int delincuencia;
	public int empleo;
	public int deporte;
	public int seguridad;
	public int entretenimiento;
	public int contaminacion;
	public int habitantes; // Si es casas aumenta cantidad si es otro el limite
	public double costo;
	public double alquiler;
	public double xp;
	public int tiempo;
	public double sigma;
	public int tamano;
	private String construido;
	private double normal = 0;
	private double mu = 0;

	public double normal(double x, double mu) {
		double a = 1 / (2 * sigma * Math.sqrt(2 * Math.PI));
		double z = (-1 / 2) * Math.pow((x - mu) / sigma, 2);
		normal = a * Math.exp(z);
		return normal;
	}

	public void calcularMu() {
		mu = ((delincuencia + deporte + educacion + empleo + entretenimiento
				+ salud + seguridad + violencia) / 8);
	}

	public Construccion() {

	}

	public Construccion(String ID) {
		this.ID = ID;
		cargarObjeto();
		iniciarAlquiler();
		timer.start();
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public BufferedImage getImagen() {
		return imagen;
	}

	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
	}

	public void fijarCoordenada(int x, int y) {
		posicion = new Point(x, y);

	}

	public Point obtenerCoordenadas() {
		return posicion;
	}

	public void cargarObjeto() {
		FileInputStream fis = null;
		properties = new Properties();
		String nombre_properties = buscarProperties();
		if (nombre_properties == null) {
			System.err.println("No se encontro el archivo");
		} else {

			try {
				fis = new FileInputStream("src/ISIS/Code/Properties/"
						+ nombre_properties);
				properties.load(fis);
				String key = null;
				String value = null;
				// key = value
				for (Map.Entry<Object, Object> linea : properties.entrySet()) {
					key = (String) linea.getKey();
					value = (String) linea.getValue();
					if (key.equals("imagen")) {
						this.imagen = ImageCache.getInstance().getImage(value);
					} else if (key.equals(ParametrosConstruccion.SALUD)) {
						this.setSalud(Integer.parseInt(value));
					} else if (key.equals(ParametrosConstruccion.EDUCACION)) {
						this.setEducacion(Integer.parseInt(value));
					} else if (key.equals(ParametrosConstruccion.VIOLENCIA)) {
						this.setViolencia(Integer.parseInt(value));
					} else if (key.equals(ParametrosConstruccion.DELINCUENCIA)) {
						this.setDelincuencia(Integer.parseInt(value));
					} else if (key.equals(ParametrosConstruccion.EMPLEO)) {
						this.setEmpleo(Integer.parseInt(value));
					} else if (key.equals(ParametrosConstruccion.DEPORTE)) {
						this.setDeporte(Integer.parseInt(value));
					} else if (key.equals(ParametrosConstruccion.SEGURIDAD)) {
						this.setSeguridad(Integer.parseInt(value));
					} else if (key.equals(ParametrosConstruccion.ENTRETENIMIENTO)) {
						this.setEntretenimiento(Integer.parseInt(value));
					} else if (key.equals(ParametrosConstruccion.CONTAMINACION)) {
						setContaminacion(Integer.parseInt(value));
					} else if (key.equals(ParametrosConstruccion.TAMANO)) {
						this.setTamano(Integer.parseInt(value));
					} else if (key.equals(ParametrosConstruccion.SIGMA)) {
						sigma = Double.parseDouble(value);
					} else if (key.equals(ParametrosConstruccion.TIPO)) {
						tipo = Integer.parseInt(value);
					} else if (key.equals(ParametrosConstruccion.ALQUILER)) {
						alquiler = Double.parseDouble(value);
					} else if (key.equals(ParametrosConstruccion.COSTO)) {
						costo = Double.parseDouble(value);
					} else if (key.equals(ParametrosConstruccion.HABITANTES)) {
						habitantes = Integer.parseInt(value);
					} else if (key.equals(ParametrosConstruccion.TIEMPO)) {
						tiempo = Integer.parseInt(value);
					} else if (key.equals(ParametrosConstruccion.XP)) {
						xp = Double.parseDouble(value);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private String buscarProperties() {

		File f = null;
		FileReader fr = null;
		BufferedReader br = null;

		String linea = null;
		try {
			f = new File("src/ISIS/Code/Properties/Construcciones.txt");
			fr = new FileReader(f);
			br = new BufferedReader(fr);

			String archivo = ID + ".properties";
			String aux;
			while ((aux = br.readLine()) != null) {
				if (aux.equals(archivo)) {
					linea = aux;
					break;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}
		}

		return linea;
	}

	private void iniciarAlquiler() {
		timer = new Timer(tiempo, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cobrar = true;
			}
		});
	}
	
	public int getEntretenimiento() {
		return entretenimiento;
	}

	public void setEntretenimiento(int entretenimiento) {
		this.entretenimiento = entretenimiento;
	}

	public int getTamano() {
		return tamano;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
	}

	public int getSeguridad() {
		return seguridad;
	}

	public void setSeguridad(int seguridad) {
		this.seguridad = seguridad;
	}

	public int getDeporte() {
		return deporte;
	}

	public void setDeporte(int deporte) {
		this.deporte = deporte;
	}

	public int getEmpleo() {
		return empleo;
	}

	public void setEmpleo(int empleo) {
		this.empleo = empleo;
	}

	public int getDelincuencia() {
		return delincuencia;
	}

	public void setDelincuencia(int delincuencia) {
		this.delincuencia = delincuencia;
	}

	public int getViolencia() {
		return violencia;
	}

	public void setViolencia(int violencia) {
		this.violencia = violencia;
	}

	public int getEducacion() {
		return educacion;
	}

	public void setEducacion(int educacion) {
		this.educacion = educacion;
	}

	public int getSalud() {
		return salud;
	}

	public void setSalud(int salud) {
		this.salud = salud;
	}

	public String getConstruido() {
		return construido;
	}

	public void setConstruido(String construido) {
		this.construido = construido;
	}

	public double getNormal() {
		return normal;
	}

	public double getMu() {
		if (mu == 0) {
			calcularMu();
		}
		return mu;
	}

	public boolean getCobrar() {
		return cobrar;
	}

	public void setCobrar(boolean cobrar) {
		this.cobrar = cobrar;
	}

	public int getContaminacion() {
		return contaminacion;
	}

	public void setContaminacion(int contaminacion) {
		this.contaminacion = contaminacion;
	}

}
