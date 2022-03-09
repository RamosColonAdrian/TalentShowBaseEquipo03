
package model.serializar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Clase de implementacion de la interfaz Serializar que desarrolla todos los metodos para posibilitar la serializacion y deserializacion de archivos y/u objetos
 * @author Adrian Ramos Colon
 */
public class SerializarIMPL implements Serializar, Serializable{
   
    /**
     * Metodo, que a partir de un objeto y un path de salida, serializa el objeto y lo guarda en el archivo y lugar especificado.
     * @param objeto Objeto a Serializar
     * @param pathArchivo Directorio donde se encuentra el archivo
     * @throws java.io.IOException Se lanza cuando una IOException ocurre
     * @throws java.io.FileNotFoundException
     * @throws java.lang.NullPointerException Se lanza cuando el objeto o archivo que se pasa por parametro es nulo 
     */
    @Override
    public void serializar(Object objeto, String pathArchivo) throws FileNotFoundException, IOException, NullPointerException{  
        
        File archivo = new File(pathArchivo);
        FileOutputStream fujoArchivoSalida = new FileOutputStream(pathArchivo);            
        ObjectOutputStream flujoObjetoSalida = new ObjectOutputStream(fujoArchivoSalida);
        if(flujoObjetoSalida!=null)
            flujoObjetoSalida.writeObject(objeto);
        flujoObjetoSalida.close();  
        
    }

    /**
     * Metodo, que a partir del path de un archivo serializado, lo desseializa y lo devuelve convertido en objeto
     * @param pathArchivo
     * @return objeto Objeto que se recupera despues de la desserializacion
     * @throws java.io.IOException Se lanza cuando una IOException ocurre
     * @throws java.lang.NullPointerException Se lanza cuando el nombre del archivo que se pasa por parametro es nulo 
     * @throws java.io.FileNotFoundException Se lanza cuando el archivo no se enceuntra o no existe 
     * @throws java.lang.ClassNotFoundException Se lanza cuando la clase especificada no se encuentra o no existe
     */
    @Override
    public Object desSerializar(String pathArchivo) throws FileNotFoundException, IOException, ClassNotFoundException, NullPointerException{
        Object objeto=null;

        File archivo = new File(pathArchivo);
        FileInputStream fujoArchivoEntrada= new FileInputStream(archivo);
        ObjectInputStream flujoObjetoEntrada = new ObjectInputStream(fujoArchivoEntrada);
        objeto = flujoObjetoEntrada.readObject();
        flujoObjetoEntrada.close();
        return objeto;
    }
}
    
