package ISIS.Code.Matriz;

import java.awt.image.BufferedImage;

public class Imagenes {

	public static BufferedImage nuevaImagen, grama, seleccion, colision, borde,
			tierra1, tierra2, tierra3, tierra4, tierra5,tierra6, lluvia,agua;
	public static int i, j;

	public Imagenes() {
		IniciarComponentes();
	}

	public void IniciarComponentes() {

		grama = ImageCache.getInstance().getImage(
				"src/ISIS/Resource/HUDImages/grama.png");
//		seleccion = ImageCache.getInstance().getImage(
//				"src/ISIS/Resource/HUDImages/seleccion.png");
		borde = ImageCache.getInstance().getImage(
				"src/ISIS/Resource/HUDImages/agua.png");
		tierra1 = ImageCache.getInstance().getImage(
				"src/ISIS/Resource/HUDImages/tierra3.png");
		tierra2 = ImageCache.getInstance().getImage(
				"src/ISIS/Resource/HUDImages/mapa.png");
		tierra3 = ImageCache.getInstance().getImage(
				"src/ISIS/Resource/HUDImages/orilla.png");
		tierra4 = ImageCache.getInstance().getImage(
				"src/ISIS/Resource/HUDImages/arriba.png");
		tierra5 = ImageCache.getInstance().getImage(
				"src/ISIS/Resource/HUDImages/tierraizqd.png");
		tierra6 = ImageCache.getInstance().getImage(
				"src/ISIS/Resource/HUDImages/0,0.png");
		lluvia = ImageCache.getInstance().getImage(
				"src/ISIS/Resource/HUDImages/lluvia.gif");
		agua = ImageCache.getInstance().getImage(
				"src/ISIS/Resource/HUDImages/agua.png");
		nuevaImagen = ImageCache.getInstance().getImage(
				"src/ISIS/Resource/HUDImages/agua.png");
		colision = ImageCache.getInstance().getImage(
				"src/ISIS/Resource/HUDImages/border.png");

	}
}
