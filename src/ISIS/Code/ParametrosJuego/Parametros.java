package ISIS.Code.ParametrosJuego;

public class Parametros {

	// Parametros del juego

	public static int limite = 100; // Limite de poblacion del Juego
	public static int habitantes = 0; // Habitantes actuales del juego
	public static double xp = 0; // Experiencia del juego
	public static int delincuencia = 0; // Nivel de delincuencia y violencia
	public static int contaminacion = 0; // Nivel de contaminacion
	public static int nivel = 1; // Nivel actual
	public static double lochas = 1000;
	public static double felicidad = 0;
	public static String alcalde = "";
	public static String ciudad = "";
	public static String imagenAlcalde = "";

	public Parametros(int limite, int habitantes, double xp, int delincuencia,
			int contaminacion, int nivel, double lochas) {
		Parametros.limite = limite;
		Parametros.habitantes = habitantes;
		Parametros.xp = xp;
		Parametros.delincuencia = delincuencia;
		Parametros.contaminacion = contaminacion;
		Parametros.nivel = nivel;
		Parametros.lochas = lochas;
	}

}
