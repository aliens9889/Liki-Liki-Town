package ISIS.Code.Matriz;

public class ModeloMapa {

	private int filas; // Total filas
	private int columnas; // Total columnas
	private Celda matriz[][]; // Datos de la matriz
	private boolean vacia = false;

	public ModeloMapa() {
		/* Vacio */
	}

	public ModeloMapa(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		matriz = new Celda[filas][columnas];
		vacia = true;
	}

	public ModeloMapa(Celda m[][]) {
		this.filas = m.length;
		this.columnas = m[0].length;

		matriz = new Celda[filas][columnas];
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				this.matriz[i][j] = m[i][j];
			}
		}
		vacia = false;
	}

	public Celda leerDato(int i, int j) {
		if ((i > this.filas && j > this.columnas) || (i > this.filas)
				|| (j > this.columnas)) {
			System.err
					.println("No coincide los datos con la dimension de la matriz");
		} else if (vacia) {
//			System.err.println("La matriz esta vacia!");
		}
		return matriz[i][j];
	}

	public void escribirDato(int i, int j, Celda dato) {
		if ((i > this.filas && j > this.columnas) || (i > this.filas)
				|| (j > this.columnas)) {
			System.err
					.println("No coincide los datos con la dimension de la matriz");
		}
		matriz[i][j] = dato;
		if (dato != null) {
			vacia = false;
		}
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public int getFilas() {
		return filas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

	public int getColumnas() {
		return columnas;
	}

	public boolean estaVacia() {
		return vacia;
	}

}
