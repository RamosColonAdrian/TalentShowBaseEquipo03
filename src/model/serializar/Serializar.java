package model.serializar;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Adrian Ramnos Colon
 */
public interface Serializar {
    
    /**
     * Interfaz donde se especifican los metodos  necesarios para la Serializacion y deserializacion de objetos y/o archivos.
     * @throws java.io.IOException Se lanza cuando una IOException ocurre
     * @throws java.io.FileNotFoundException
     * @throws java.lang.NullPointerException Se lanza cuando el objeto o archivo que se pasa por parametro es nulo 
     * @param objeto
     * @param archivo 
     */
    public void serializar(Object objeto, String archivo) throws FileNotFoundException, IOException, NullPointerException;
    
    /**
     * @throws java.io.IOException Se lanza cuando una IOException ocurre
     * @throws java.lang.NullPointerException Se lanza cuando el nombre del archivo que se pasa por parametro es nulo 
     * @throws java.io.FileNotFoundException Se lanza cuando el archivo no se enceuntra o no existe 
     * @throws java.lang.ClassNotFoundException Se lanza cuando la clase especificada no se encuentra o no existe
     * @param nombreArchivo
     * @return object
     */
    public Object desSerializar(String nombreArchivo) throws FileNotFoundException, IOException, ClassNotFoundException, NullPointerException;
    
}

