package ISIS.Code.ParametrosJuego;

public class Nivel {
	
	private int nivel;
	private double lochas;
	private int habitantes;
	private int limite;
	
	public Nivel() {
		nivel = 1;
		lochas = 0;
		habitantes = 0;
		limite = 100;
	}
	
	public Nivel(int nivel) {
		this.nivel = nivel;
		cargarNivel();
	}

	private void cargarNivel() {
		switch(nivel) {
		case 1:
			// Cantidades q brinda el nivel 1
			break;
		case 2:
			// Cantidades q brinda el nivel 2
			break;
		case 3:
			// Cantidades q brinda el nivel 3
			break;
		case 4:
			// Cantidades q brinda el nivel 4
			break;
		case 5:
			// Cantidades q brinda el nivel 5
			break;
		}
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public double getLochas() {
		return lochas;
	}

	public void setLochas(double lochas) {
		this.lochas = lochas;
	}

	public int getHabitantes() {
		return habitantes;
	}

	public void setHabitantes(int habitantes) {
		this.habitantes = habitantes;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}
	
}
