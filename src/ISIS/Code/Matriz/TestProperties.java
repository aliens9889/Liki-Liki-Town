package ISIS.Code.Matriz;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class TestProperties {
	public static void main(String[] args) throws IOException {

		String ID = "majunche";
		String imagen = "majunche.png";
		int infsalud = 0;
		double sigmasalud = 0.2;
		String inf = "infsalud";
		String sigmas = "sigmasalud";

		// -------------------------------------------------------------------------
		Properties myProps = new Properties();
		FileInputStream MyInputStream = new FileInputStream(
				"src/ISIS/Code/Properties/rancho.properties");
		myProps.load(MyInputStream);
		// String myPropValue = myProps.getProperty("propKey");
		// -------------------------------------------------
		String key = "";
		String value = "";
		for (Map.Entry<Object, Object> propItem : myProps.entrySet()) {
			key = (String) propItem.getKey();
			value = (String) propItem.getValue();
			// if (value == ID) {
			// ID = value;
			// } else if (value == imagen) {
			// imagen = value;
			// } else if (key == inf) {
			// infsalud = Integer.parseInt(value);
			// } else if (key == sigmas) {
			// sigmasalud = Double.parseDouble(sigmas);
			// }
			System.out.println(key + " = " + value);
		}

		// System.out.println("Contenido de el .properties");
		// System.out.println("ID = " + ID);
		// System.out.println("imagen = " + imagen);
		// System.out.println(inf + " = " + infsalud);
		// System.out.println(sigmas + " = " + sigmasalud);
		// //-------------------------------------------------
		// MyInputStream.close(); // better in finally block
		// //-------------------------------------------------
		// myProps.setProperty("propKey", "myNewPropValue");
		// FileOutputStream MyOutputStream = new
		// FileOutputStream("src/testmatriz/yourPropertiesFile.properties");
		// myProps.store(MyOutputStream, "myAddedKey: myAddedValue");
		// //-------------------------------------------------
		// MyOutputStream.close(); // better in finally block
		// //-------------------------------------------------
	}
}
