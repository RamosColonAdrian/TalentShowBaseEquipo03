package model.beans;

import java.io.IOException;
import java.io.Serializable;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Clase Padre que contiene las propiedades y metodos de un artista
 * @author Adrian Ramos colon
 */
public abstract class Artista implements Serializable{

    
    private String nombre;

    private String apellidos;

    private String localidad;
    
    private final int edad;
    
    private final boolean sexo;
    
    private int puntuacion;
    

    
    /**
     * Contructor de la clase Artista
     * @param nombre Nombre del artista
     * @param apellidos Apellidos del artista
     * @param localidad Localidad del artista
     * @param edad Edad del artista
     * @param sexo Genero del artista
     */
    public Artista (String nombre, String apellidos, String localidad, int edad, boolean sexo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.localidad = localidad;
        this.edad = edad;
        this.sexo = sexo;
        this.puntuacion=0;
    }
    
    
    
    /**
     * Metodo Abstracto que se desarrolla en cada uno de los tipos de artistas para llevar a cabo su actuacion
     * @param artista  
     * @throws java.lang.InterruptedException Se lanza cuando la ejecucion ha sido interrumpida
     * @throws java.lang.NullPointerException Se lanza cuando el artista que se pasa por parametro es nulo 
     * @throws javax.sound.sampled.LineUnavailableException Se lanza cuandoel flujo de audio no se puede abrir 
     * @throws javax.sound.sampled.UnsupportedAudioFileException Se lanza cuando el archivo de audio no es compatible
     * @throws java.io.IOException Se lanza cuando una IOException ocurre
     */
    public abstract void actuar(Artista artista) throws NullPointerException, InterruptedException, LineUnavailableException, UnsupportedAudioFileException, IOException;

    
    /**
     * Metodo que realiza la accion de saludar
     * @return saludo
     */
    public String saludar() {
        return("Buenas a todos! Mi nombre es: "+getNombre()+" "+getApellidos()+". Vengo de "+getLocalidad()+" y les voy a deleitar con mi actuación");
    }
    
    /**
     * Metodo que realiza la accion de despedirse
     * @return despido
     */
    public String despedirse() {
        return("Ha sido un placer, espero que lo hayan disfrutado, muchas gracias!!");
    }
    
    /**
     * 
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the localidad
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * @param localidad the localidad to set
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    
    
    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @return the sexo
     */
    public boolean getSexo() {
        return sexo;
    }
    
    
    /**
     * @return the puntuacion
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * @param puntuacion the puntuacion to set
     */
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    
    /**
     * 
     * @param art
     * @return <ul>
     *              <li><code> True </code> si el artista tiene los mismos atributos que el introducido</li>
     *              <li><code> False </code> si el artista no tiene los mismos atributos</li>
     *         </ul>
     */
    @Override
    public boolean equals(Object art) {
        Artista artista = (Artista) art;
        return artista != null && this.getNombre().equals(artista.getNombre()) && 
                this.getApellidos().equals(artista.getApellidos()) &
                this.getLocalidad().equals(artista.getLocalidad());
    }
		
    /**
     * 
     * @return Codigo unico de cada objeto artista
     */
    @Override
    public int hashCode() {
        return (this.getNombre()+this.getApellidos()+this.getLocalidad()).hashCode();
    }

    /**
     *
     *@return Las propiedades del artistas preformateadas
    **/
    @Override
    public String toString(){
        return "Nombre: "+this.nombre+", Apellidos: "+this.apellidos+", Localidad: "+this.localidad;
    }
    
    
}
