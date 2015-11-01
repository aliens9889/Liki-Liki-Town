package Pruebas;

import java.io.*;

public class EscribeFichero
{
    public static void main(String[] args) throws IOException
    {
//        FileWriter fichero = null;
//        PrintWriter pw = null;
//        try
//        {
//            fichero = new FileWriter("src/Pruebas/Prueba.txt");
//            pw = new PrintWriter(fichero);
//
//            for (int i = 0; i < 10; i++)
//                pw.println("Linea " + i);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//           try {
//           // Nuevamente aprovechamos el finally para 
//           // asegurarnos que se cierra el fichero.
//           if (null != fichero)
//              fichero.close();
//           } catch (Exception e2) {
//              e2.printStackTrace();
//           }
//        }
    	
    	File TextFile = new File("src/Pruebas/Prueba3.txt");
    	FileWriter TextOut = new FileWriter(TextFile, true);
    	TextOut.write("Prueba de Grabación de Archivo_4\r\n");
    	TextOut.close();
    }
}
