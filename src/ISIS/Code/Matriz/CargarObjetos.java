package ISIS.Code.Matriz;

public class CargarObjetos {

	// Edificaciones
	public static Construccion PETROCASA;
	public static Construccion RANCHO;
	public static Construccion MAJUNCHE;
	public static Construccion PALAFITO;
	public static Construccion CABANA;
	
	// Servicios Publicos
	public static Construccion AMBULATORIO;
	public static Construccion ASEO;
	public static Construccion COMPANIA_ELECTRICA;
	public static Construccion ESCUELA;
	public static Construccion AGUAS_DE_MERIDA;
	
	// Vias
	public static Construccion CAMELLON;
	public static Construccion GRANZON;
	public static Construccion CONCRETO;
	
	// Industrias
	public static Construccion CARPINTERIA;
	public static Construccion CAFETIN;
	public static Construccion FRUTERIA;
	public static Construccion MERCALITO;
	public static Construccion ALMUERZO_POPULAR;
	
	// EdificiosComunitarios
	public static Construccion CONSEJO_COMUNAL;
	public static Construccion COMEDOR_POPULAR;

	public CargarObjetos() {
		iniciarComponentes();
	}

	public static void iniciarComponentes() {

		// Edificaciones
		PETROCASA = new Construccion("petrocasa");
		PETROCASA.setImagen(ImageCache.getInstance().getImage(
				"src/ISIS/Code/Matriz/petrocasa.png"));
		RANCHO = new Construccion("rancho");
		RANCHO.setImagen(ImageCache.getInstance().getImage(
				"src/ISIS/Code/Matriz/rancho.png"));
		MAJUNCHE = new Construccion("majunche");
		MAJUNCHE.setImagen(ImageCache.getInstance().getImage(
				"src/ISIS/Code/Matriz/majunche.png"));
		PALAFITO = new Construccion("palafito");
		PALAFITO.setImagen(ImageCache.getInstance().getImage(
				"src/ISIS/Code/Matriz/palafito.png"));
		CABANA = new Construccion("cabana");
		CABANA.setImagen(ImageCache.getInstance().getImage(
				"src/ISIS/Code/Matriz/cabana.png"));

		// Servicios Publicos
		// ambulatorio = new Vivienda("ambulatorio");
		// ambulatorio.setImagen(ImageCache.getInstance().getImage(
		// "src/ISIS/Code/Matriz/ambulatorio.png"));
		// aseo = new Vivienda("aseo");
		// aseo.setImagen(ImageCache.getInstance().getImage(
		// "src/ISIS/Code/Matriz/aseo.png"));
		// companiaElectrica = new Vivienda("companiaElectrica");
		// companiaElectrica.setImagen(ImageCache.getInstance().getImage(
		// "src/ISIS/Code/Matriz/companiaElectrica.png"));
		// escuela = new Vivienda("escuela");
		// escuela.setImagen(ImageCache.getInstance().getImage(
		// "src/ISIS/Code/Matriz/escuela.png"));
		// aguaDeMerida = new Vivienda("aguaDeMerida");
		// aguaDeMerida.setImagen(ImageCache.getInstance().getImage(
		// "src/ISIS/Code/Matriz/aguaDeMerida.png"));

		// Vias
		CAMELLON = new Construccion("camellon");
		CAMELLON.setImagen(ImageCache.getInstance().getImage(
				"src/ISIS/Code/Matriz/camellon.png"));
		GRANZON = new Construccion("granzon");
		GRANZON.setImagen(ImageCache.getInstance().getImage(
				"src/ISIS/Code/Matriz/granzon.png"));
		CONCRETO = new Construccion("concreto");
		CONCRETO.setImagen(ImageCache.getInstance().getImage(
				"src/ISIS/Code/Matriz/concreto.png"));

		// Industrias
		// carpinteria = new Vivienda("carpinteria");
		// carpinteria.setImagen(ImageCache.getInstance().getImage(
		// "src/ISIS/Code/Matriz/carpinteria.png"));
		// cafetin = new Vivienda("cafetin");
		// cafetin.setImagen(ImageCache.getInstance().getImage(
		// "src/ISIS/Code/Matriz/cafetin.png"));
		// fruteria = new Vivienda("fruteria");
		// fruteria.setImagen(ImageCache.getInstance().getImage(
		// "src/ISIS/Code/Matriz/fruteria.png"));
		// mercalito = new Vivienda("mercalito");
		// mercalito.setImagen(ImageCache.getInstance().getImage(
		// "src/ISIS/Code/Matriz/mercalito.png"));
		// almuerzoPopular = new Vivienda("mercalito");
		// almuerzoPopular.setImagen(ImageCache.getInstance().getImage(
		// "src/ISIS/Code/Matriz/almuerzoPopular.png"));

		// EdificiosComunitarios
		// consejoComunal = new Vivienda("consejoComunal");
		// consejoComunal.setImagen(ImageCache.getInstance().getImage(
		// "src/ISIS/Code/Matriz/consejoComunal.png"));
		// comedorPopular = new Vivienda("comedorPopular");
		// comedorPopular.setImagen(ImageCache.getInstance().getImage(
		// "src/ISIS/Code/Matriz/comedorPopular.png"));

	}
}
