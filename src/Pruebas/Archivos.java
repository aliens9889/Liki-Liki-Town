package Pruebas;

import java.io.*;
import java.util.*;


public class Archivos {
      private File archivo; 
      private FileReader in;    
      private FileWriter out;     
      private FileOutputStream fos;    
      private ObjectOutputStream oos;     
      private FileInputStream fis;     
      private ObjectInputStream ois;    
      private static Archivos archivos;
    
 
      public Archivos() {
              super();
            
      }
 
      public static Archivos getInstance() {
              if(archivos == null){
                      archivos = new Archivos();    
              }
              return archivos;
      }
    
      public synchronized void escribirArchivo(String ruta, String contenido) throws IOException {
        getInstance();
              File file = new File(ruta);
              out = new FileWriter(file.getAbsolutePath(), true);
              out.write(contenido);
              out.close();           
      }
    
      public synchronized String leerArchivo(String ruta) throws IOException, FileNotFoundException {
              StringBuffer cadena = new StringBuffer();
              in = new FileReader(ruta);

              int c = 0;
              while((c = in.read()) != -1) {
                 cadena.append((char) c);
              }

              return cadena.toString();
      }
    
      public synchronized void copiarArchivo(String origen, String destino, boolean annadir) throws IOException, FileNotFoundException {
             File archivoOrigen = new File(origen);
             File archivoDestino = new File(destino);
 
          in = new FileReader(archivoOrigen.getAbsolutePath());
          out = new FileWriter(archivoDestino.getAbsolutePath(), annadir);
  
          int caracter = 0;
          while ((caracter = in.read()) != -1) {
                  out.write(caracter);
              }
  
          in.close();
          out.close();
      }

      public synchronized void guardarObjeto(String ruta, Object objeto) throws IOException {
              fos = new FileOutputStream(ruta);
              oos = new ObjectOutputStream(fos);
              oos.writeObject(objeto);
              fos.flush();
      }
    
      public synchronized Object recuperarObjeto(String ruta) throws IOException, FileNotFoundException, ClassNotFoundException {
              fis = new FileInputStream(ruta);
              ois = new ObjectInputStream(fis);
              return ois.readObject();
      }   
}