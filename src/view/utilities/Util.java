package view.utilities;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Clase de utilidades
 * @author Adrian Ramos Colon
 */
public final class Util {

    /**
     * Metodo que finaliza la aplicacion
     */
    public static void finalizador() {
        System.exit(0);
    }

    /**
     * Metodo que convierte un string a una variable tipo Path
     * @param textPath
     * @return path Texto convertido a Path
     */
    public static Path convertirEnPath(String textPath) {
        Path path = Paths.get(textPath);
        return path;
    }
    
    /**
     * Metodo que genera numeros aleatorios dentro de un rango
     * @param max Numero maximo del rango
     * @param min Numero minimo del rango
     * @return value Numero aleatorio
     */
    public static int numeroAleatorio(int max, int min) {
        Random random = new Random();
        int value = random.nextInt(max + min) + min;
        return value;
    }
    
}
