package model.beans;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import model.audios.AudiosIMPL;
/**
 * Tipo de artista "Cantante" que extiende de la clase Padre Artista
 * @author Adrian Ramos Colon
 */
public class Cantante extends Artista {

    
    protected AudiosIMPL audios;
    
    /**
     * Constructor de la clase Cantante
     * @param nombre
     * @param apellidos
     * @param localidad
     * @param edad
     * @param sexo 
     * @param audios 
     */
    public Cantante(String nombre, String apellidos, String localidad, int edad, boolean sexo, AudiosIMPL audios) {
        super(nombre, apellidos, localidad, edad, sexo);
        this.audios=audios;
    }
        
    
    /**
     * Metodo que anula el metodo de la clase Padre y que desarrolla la actuacion del Cantante
     * @throws java.lang.InterruptedException Se lanza cuando la ejecucion ha sido interrumpida
     * @throws java.lang.NullPointerException Se lanza cuando el artista que se pasa por parametro es nulo 
     * @throws javax.sound.sampled.LineUnavailableException Se lanza cuandoel flujo de audio no se puede abrir 
     * @throws javax.sound.sampled.UnsupportedAudioFileException Se lanza cuando el archivo de audio no es compatible
     * @throws java.io.IOException Se lanza cuando una IOException ocurre
     * @param artista 
     */
    @Override
    public void actuar(Artista artista) throws NullPointerException, InterruptedException, LineUnavailableException, UnsupportedAudioFileException, IOException{
        audios.reproducirAudio(artista);
        
    }
}
