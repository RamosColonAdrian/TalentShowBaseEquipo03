package model.audios;

import java.io.IOException;
import model.beans.Artista;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Interfaz donde se especifican los metodos necesarios para la reproduccion de clips de audio.
 * @author Adrian Ramos Colon
 */
public interface Audios {
    
    /**
     * Firma del metodo que posibilita la reproducción de audios
     *
     * @throws java.lang.InterruptedException Se lanza cuando el flujo de audio ha sido interrumpido
     * @throws java.lang.NullPointerException Se lanza cuando el artista introducido por parametros es nulo
     * @throws javax.sound.sampled.LineUnavailableException Indica que una línea no se puede abrir porque no está disponible
     * @throws javax.sound.sampled.UnsupportedAudioFileException Se lanza cuando el formato de audio no es compatible
     * @throws java.io.IOException
     * @param artista
     */
    public void reproducirAudio(Artista artista) throws InterruptedException, LineUnavailableException, UnsupportedAudioFileException, IOException, NullPointerException;

    /**
     * Firma del metodo que genera una direccion de directorio aleatoria
     *
     * @throws java.lang.NullPointerException Se lanza cuando el artista introducido por parametros es nulo
     * @param artista
     * @return url
     */
    public String generaURLaleatorio(Artista artista) throws NullPointerException;

    /**
     * Firma del metodo que a partir de la direccion del directorio cuenta cuantos archivos hay en el mismo
     *
     * @throws java.lang.NullPointerException Se lanza cuando el artista introducido por parametros es nulo
     * @param tipoArtista
     * @return numeroDeArchivos
     */
    public int contarArchivosEnDirectorio(String tipoArtista) throws NullPointerException;


    /**
     * Firma del metodo que rellena la lista de archivos que existen en el directorio
     * 
     * @throws java.lang.NullPointerException Se lanza cuando el artista introducido por parametros es nulo
     * @param listaArchivos
     * @param tipoArtista 
     * @return listaArchivos
     */
    public ArrayList montarListaDeArchivos(ArrayList<Integer> listaArchivos, String tipoArtista) throws NullPointerException;
    
    /**
     * Firma del metodo que a partir de un contenedor de numeros, extrae uno de ellos aleatoriamente, cambiando su posicion por 0 para no repetir valores
     * 
     * @throws java.lang.NullPointerException Se lanza cuando el artista introducido por parametros es nulo
     * @param listaArchivos
     * @return numeroAleatorio
     */
    public int extraerNumAleatorioSinRepeticion(ArrayList<Integer> listaArchivos) throws NullPointerException;

}
