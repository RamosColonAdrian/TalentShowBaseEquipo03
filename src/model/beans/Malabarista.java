package model.beans;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import model.malabaristas.ActuacionesMalabaristaIMPL;

/**
 * Tipo de artista "Malabarista" que extiende de la clase Padre Artista
 * @author Adrian Ramos Colon
 */
public class Malabarista extends Artista {

    private ActuacionesMalabaristaIMPL malabarista;
    /**
     * Constructor de la clase Malabarista
     * @param nombre
     * @param apellidos
     * @param localidad
     * @param edad
     * @param sexo 
     * @param malabarista 
     */
    public Malabarista(String nombre, String apellidos, String localidad, int edad, boolean sexo, ActuacionesMalabaristaIMPL malabarista) {
        super(nombre, apellidos, localidad, edad, sexo);
        this.malabarista = malabarista;
    }

    
    /**
     * Metodo que anula el metodo de la clase Padre y que desarrolla la actuacion del Malabarista
     * @throws java.lang.InterruptedException Se lanza cuando la ejecucion ha sido interrumpida
     * @throws javax.sound.sampled.LineUnavailableException Se lanza cuandoel flujo de audio no se puede abrir 
     * @throws javax.sound.sampled.UnsupportedAudioFileException Se lanza cuando el archivo de audio no es compatible
     * @throws java.io.IOException Se lanza cuando una IOException ocurre
     * @throws java.lang.NullPointerException Se lanza cuando el artista que se pasa por parametro es nulo 
     * @param artista  
     */
    @Override
    public void actuar(Artista artista) throws NullPointerException, InterruptedException, LineUnavailableException, UnsupportedAudioFileException, IOException{
        malabarista.malabares();         
    }
}
