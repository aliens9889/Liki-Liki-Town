package ISIS.Code.Matriz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageCache {

	private static ImageCache instance;

	private Map<String, BufferedImage> imageMap = new HashMap<String, BufferedImage>();

	public ImageCache() {
	}

	public static synchronized ImageCache getInstance() {
		if (instance == null) {
			instance = new ImageCache();
		}

		return instance;
	}

	public BufferedImage getImage(String filename) {
		BufferedImage ret = imageMap.get(filename);

		if (ret == null) {
			try {
				ret = ImageIO.read(new File(filename));
				imageMap.put(filename, ret);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return ret;
	}
	
	public BufferedImage getImage(String filename, Class<?> clazz) {
		BufferedImage ret = imageMap.get(clazz.getName() + " : " + filename);
		if (ret == null) {
			try {
				ret = ImageIO.read(clazz.getResourceAsStream(filename));
			} catch (IOException e) {
				e.printStackTrace();
			}
			imageMap.put(filename, ret);
		}
		
		return ret;
	}

	
	public BufferedImage getSystemImage(String filename) {
		BufferedImage ret = imageMap.get(ClassLoader.class.getName() + ":" + filename);
		
		if (ret == null) {
			try {
				ret = ImageIO.read(ClassLoader.getSystemResourceAsStream(filename));
			} catch (IOException e) {
				e.printStackTrace();
			}
			imageMap.put(filename, ret);
		}
		
		return ret;
	}
}
