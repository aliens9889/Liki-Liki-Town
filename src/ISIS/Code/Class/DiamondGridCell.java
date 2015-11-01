package ISIS.Code.Class;

/*
 * Celda de Diamante (Rombo)
 */

/**
 * 
 * @author ISIS
 */

public class DiamondGridCell {

	private final int[] ESQUINAS_DX; // Vector de desplazamientos horizontales
										// de las esquinas de las celdas
	private final int[] ESQUINAS_DY; // Vector de desplazamientos vertitales de
										// las esquinas de las celdas

	private final int LADO;

	private int coordenadaX = 0;
	private int coordenadaY = 0;

	private int coordenadaI = 0;
	private int coordenadaJ = 0;

	public final int RADIO; // radio de la celda
	public final int ALTO; // altura de la celda
	public final int ANCHO; // ancho de la celda

	public static final int NUM_VECINOS = 4;

	public DiamondGridCell(int radio) {

		RADIO = radio;
		ANCHO = 3 * radio;
		ALTO = 2 * radio;
		LADO = ANCHO / 2;

		int cdx[] = { LADO, ANCHO, LADO, 0 };
		ESQUINAS_DX = cdx;

		int cdy[] = { 0, ALTO / 2, ALTO, ALTO / 2 };
		ESQUINAS_DY = cdy;
	}

	/*
	 * Retorna coordenada X de la esquina superior izquierda de la celda
	 */
	public int getCoordenadaX() {
		return coordenadaX;
	}

	/*
	 * Retorna coordenada Y de la esquina superior izquierda de la celda
	 */
	public int getCoordenadaY() {
		return coordenadaY;
	}

	/*
	 * Retorna coordenada X del centro de la celda
	 */
	public int getCenterX() {
		return coordenadaX + RADIO;
	}

	/*
	 * Retorna coordenada Y del centro de la celda
	 */
	public int getCenterY() {
		return coordenadaY + ALTO / 2;
	}

	/*
	 * Retorna red de coordenadas horizontales de la celda
	 */
	public int getIndexI() {
		return coordenadaI;
	}

	/*
	 * Retorna red de coordenadas verticales de la celda
	 */
	public int getIndexJ() {
		return coordenadaJ;
	}

	/*
	 * Retorna coordenadas horizontales para el vecino dado
	 */
	// public int getVecinoI(int vecino){
	// return coordenadaI + VECINOS_DI[vecino];
	// }

	/*
	 * Retorna coordenadas verticales para un vecino dado
	 */
	// public int getVecinoJ(int vecino){
	// return coordenadaJ + VECINOS_DJ[coordenadaI % 2][vecino];
	// }

	/*
	 * Calcula coordenadas X y Y para todas las esquinas de las celdas en
	 * sentido a las agujas del reloj a partir de la parte superior
	 */
	public void calcularEsquinas(int[] esquinasX, int[] esquinasY) {
		for (int i = 0; i < NUM_VECINOS; i++) {
			esquinasX[i] = coordenadaX + ESQUINAS_DX[i];
			esquinasY[i] = coordenadaY + ESQUINAS_DY[i];
		}
	}

	/*
	 * Establece las coordenadas verticales y horizontales de las celdas en la
	 * red
	 */
	public void setIndexCelda(int i, int j) {
		coordenadaI = i;
		coordenadaJ = j;
		coordenadaX = i * LADO;
		coordenadaY = ALTO * (2 * j + (i % 2)) / 2;
	}

	/*
	 * Establece las celdas para algunos puntos dentro de ella
	 */
	public void setCeldaPorPunto(int x, int y) {

		int ci = (int) Math.floor((float) x / (float) LADO);
		int cx = x - LADO * ci;

		int ty = y - (ci % 2) * ALTO / 2;
		int cj = (int) Math.floor((double) ty / (float) ALTO);
		int cy = ty - ALTO * cj;

		if (cx > Math.abs(RADIO) / 2 - RADIO * cy / ALTO) {
			setIndexCelda(ci, cj);
		} else {
			setCeldaPorPunto(ci - 1, cj + (ci % 2) - ((cy < ALTO / 2) ? 1 : 0));
		}
	}
}