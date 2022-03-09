
package model.process;

import java.io.IOException;
import view.utilities.es.UtilES;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import model.registros.RegistrosIMPL;
import model.beans.Artista;

/**
 * Clase de procesos donde se desarollan todos los procesos que utilizará controlador para realizar el flujo de ejecución de la aplicacion
 * @author Adrian Ramos Colon
 */
public class Aplicacion implements Serializable {
    
    private final RegistrosIMPL registros;
    private ArrayList <Artista> artistasRegistrados;
    private ArrayList <Artista> artistasPuntuados;
    private HashSet <String> nombresArtistas;
    private final UtilES utiles;
    
    /**
     * Constructor de la clase Aplicacion
     * @param utiles
     */
    public Aplicacion(UtilES utiles) {
        this.utiles = utiles;
        registros = new RegistrosIMPL(utiles);
        artistasRegistrados = new ArrayList<>();
        artistasPuntuados = new ArrayList<>();
        nombresArtistas = new HashSet<>();
    }

    /**
     * Metodo que solicita los datos del artista al usuario y crea un objeto del tipo de artista especificado
     */
    public void registraUsuario() {
        boolean salir = false;
        do {             
            Artista artista = registros.pideDatos();
            if(!nombresArtistas.contains((artista.getNombre()+artista.getApellidos()).toUpperCase().trim())){
                getArtistasRegistrados().add(artista);
                nombresArtistas.add((artista.getNombre()+artista.getApellidos()).toUpperCase().trim());
                utiles.mensajeln("\nEl artista ha sido registrado con exito");
                salir = false;
            }else{
                utiles.mensajeln("\nEl usuario introducido ya existe dentro del concurso, prueba a introducir una identificacion diferente");
                salir = true;
            }
        }while(salir == true);
        
    }

    /**
     * Metodo con el que se ejecutan las actuaciones y se les puntua a los artistas
     */
    public void concursarYpuntuar() {
        try {
            if(!artistasRegistrados.isEmpty()){
            boolean seguir = true;
            Iterator<Artista> iterador = getArtistasRegistrados().iterator();
            while (iterador.hasNext() && seguir == true ) {
                Artista artista = iterador.next();
                utiles.mensajeln(artista.saludar());
                artista.actuar(artista);
                utiles.mensajeln(artista.despedirse());
                artista.setPuntuacion(utiles.pideNumero("\n> Asigne una puntuacion entre 1 y 12: ", "Error.Valor introducido fuera de rando, vuelva a intentarlo", 12, 1));
                artistasPuntuados.add(artista);
                if (!artistasRegistrados.isEmpty()) {
                    iterador.remove();
                }
                int opcion = utiles.pideNumero("\n> Si desea seguir con el concurso pulse 1, de lo contrario pulse 0: ", "Error.Valor introducido fuera de rando, vuelva a intentarlo", 1, 0);
                if (opcion == 0) {
                    seguir = false;
                }
                if (getArtistasRegistrados().isEmpty() && opcion == 1) {
                    seguir = false;
                    utiles.mensajeln("\n No existen artistas registrados por concursar\n");
                }
            }
        }else
            utiles.mensajeln("\nNo existen artistas registrados por concursar\n");
        } catch (IOException e) {
            utiles.mensajeln("Se ha producido un error fatal inesperado");
        } catch (InterruptedException ie){
            utiles.mensajeln("Se ha producido un error inesperado, el subproceso ha sido interrumpido");
        } catch (LineUnavailableException lue){
            utiles.mensajeln("Se ha producido un error inesperado, la linea de audo especificadano se puede leer");
        } catch (NullPointerException npe){
            utiles.mensajeln("Se ha producido un error inesperado, alguno de los objetos proporcionados es nulo");
        } catch (UnsupportedAudioFileException uaf){
            utiles.mensajeln("Se ha producido un error inesperado, el archivo de audio no se puede leer debido a que no es un formato compatible ");
        }
    }
    
    /**
     * Metodo que muestra a los artistas puntuados 
     */
    public void consultarClasificacion() {
        if(!artistasPuntuados.isEmpty()){
            artistasPuntuados.sort(Comparator.comparing(Artista::getPuntuacion).reversed());
            int indice = 1;
            for (Artista artista : artistasPuntuados) {
                System.out.println(indice + "- " + artista.getNombre() + " " + artista.getApellidos() + ": " + artista.getPuntuacion());
                indice++;
            }   
        }else
            utiles.mensajeln("No hay artistas registrados");
    }
    
    /**
     * Metodo que reinicia alguna de las propiedades de la clase
     */
    public void reiniciarVariables(){
        artistasRegistrados = new ArrayList<>();
        artistasPuntuados = new ArrayList<>();
        nombresArtistas = new HashSet<>(); 
    }

    /**
     * @return the artistasRegistrados
     */
    public ArrayList <Artista> getArtistasRegistrados() {
        return artistasRegistrados;
    }

}
