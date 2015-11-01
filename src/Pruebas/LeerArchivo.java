package Pruebas;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class LeerArchivo {
    public LeerArchivo(){
        try{
            String fila = "";
            String [] posiciones = null;
            Scanner scan = new Scanner(new File("src/Pruebas/Prueba.txt"));
            while(scan.hasNext()){
                fila = scan.nextLine();
                fila = fila.replace(", ", ","); //Quitamos los espacios en blanco despues de la coma
                posiciones = fila.split(",");
                for (int i = 0; i < posiciones.length; i++) {
                    System.out.println("posiciones["+i+"]: " + posiciones[i]);
                }
                posiciones = null;
            }
        }catch(FileNotFoundException x){
            System.out.println("No se pudo encontrar el archivo");
        }
    }
    public static void main(String args []){
        new LeerArchivo();
    }
}  