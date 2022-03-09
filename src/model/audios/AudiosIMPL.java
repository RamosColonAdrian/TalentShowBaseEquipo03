package model.audios;

import model.beans.Artista;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import model.serializar.SerializarIMPL;
import view.utilities.Util;

/**
 * 
 * Clase de implemetnacion de la interfaz Audios. 
 * En esta clase se desarrollan los metodos necesarios para la ejecucion de clips de audio dentro del "Talent Show"
 * @author Adrián Ramos Colon
 */
public class AudiosIMPL implements Audios, Serializable{

    private static AudiosIMPL audiosIMPL = null;
    
    /**
     * Contenedor donde se alamacena el numero de caciones de cada tipo de artista
     */
    private ArrayList<Integer> cancionesMujer, cancionesHombre, poemasMujer, poemasHombre, pistasMusica;
    SerializarIMPL utilidadesSerializar;
    

    public static AudiosIMPL getIntance() throws IOException, FileNotFoundException, ClassNotFoundException {
        if (audiosIMPL == null) {
            audiosIMPL = new AudiosIMPL();
        }
        return audiosIMPL;
    }
    
    
    private AudiosIMPL() throws IOException, FileNotFoundException, ClassNotFoundException {
        utilidadesSerializar = new SerializarIMPL();
        if(Files.exists(Util.convertirEnPath("cancionesHombre.ser"))&& Files.exists(Util.convertirEnPath("cancionesMujer.ser"))
        &&Files.exists(Util.convertirEnPath("pistasMusica.ser")) && Files.exists(Util.convertirEnPath("poemasHombre.ser"))
        &&Files.notExists(Util.convertirEnPath("poemasMujer.ser"))){
            cancionesHombre = (ArrayList<Integer>) utilidadesSerializar.desSerializar("cancionesHombre.ser");
            cancionesMujer = (ArrayList<Integer>) utilidadesSerializar.desSerializar("cancionesMujer.ser");
            poemasHombre = (ArrayList<Integer>) utilidadesSerializar.desSerializar("poemasHombre.ser");
            poemasMujer = (ArrayList<Integer>) utilidadesSerializar.desSerializar("poemasMujer.ser");
            pistasMusica = (ArrayList<Integer>) utilidadesSerializar.desSerializar("pistasMusica.ser");
        }else{
            cancionesHombre = montarListaDeArchivos(cancionesHombre, "CantanteH");
            cancionesMujer = montarListaDeArchivos(cancionesMujer, "CantanteM");
            poemasHombre = montarListaDeArchivos(poemasHombre, "PoetaH");
            poemasMujer = montarListaDeArchivos(poemasMujer, "CantanteM");
            pistasMusica = montarListaDeArchivos(pistasMusica, "Musico");
        }
    }

    /**
     * Metodo que rellena los contenedores dependiendo del numero de archivos que haya en el directorio correspondiente
     * @param listaArchivos 
     * @param tipoArtista 
     * @return listaArchivos
     */
    @Override
    public final ArrayList montarListaDeArchivos(ArrayList<Integer> listaArchivos, String tipoArtista) throws NullPointerException{
        
        listaArchivos = new ArrayList<>();
        for (int i = 1; i < contarArchivosEnDirectorio(tipoArtista) + 1; i++) {
            listaArchivos.add(i);
        }

        return listaArchivos;
    }

    /**
     * Metodo que efectua un punto de entrada de los recursos del sistema de audio.Obtiene un flujo de entrada de audio de la codificación indicada, al convertir el flujo de entrada de audio proporcionado.
     * @param artista
     * @throws java.lang.InterruptedException
     * @throws javax.sound.sampled.LineUnavailableException
     * @throws javax.sound.sampled.UnsupportedAudioFileException
     * @throws java.io.IOException
     * @throws java.lang.NullPointerException
     */
    @Override
    public void reproducirAudio(Artista artista) throws InterruptedException, LineUnavailableException, UnsupportedAudioFileException, IOException, NullPointerException{ 
        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(new File(generaURLaleatorio(artista))));
        clip.start();
        Thread.sleep(clip.getMicrosecondLength() / 1000);
        clip.close();
    }

    /**
     * Metodo, que dependiendo del tipo de artista que reciba, realiza el conteo de archivos que hay en su respectivo directorio
     * @throws java.lang.NullPointerException
     * @param tipoArtista
     * @return int numeroDeArchivos
     */
    @Override
    public int contarArchivosEnDirectorio(String tipoArtista) throws NullPointerException{
        File carpeta;
        File[] lista;
        int numeroDeArchivos = 0;

        
        switch (tipoArtista) {
            case "CantanteH":
                carpeta = new File("./pistas/PistasCantantes/hombre");
                lista = carpeta.listFiles();
                numeroDeArchivos = lista.length;
                break;
            case "CantanteM":
                carpeta = new File("./pistas/PistasCantantes/mujer");
                lista = carpeta.listFiles();
                numeroDeArchivos = lista.length;
                break;
            case "Musico":
                carpeta = new File("./pistas/PistasMusicos");
                lista = carpeta.listFiles();
                numeroDeArchivos = lista.length;
                break;
            case "PoetaH":
                carpeta = new File("./pistas/PistasPoetas/hombre");
                lista = carpeta.listFiles();
                numeroDeArchivos = lista.length;
                break;
            case "PoetaM":
                carpeta = new File("./pistas/PistasPoetas/mujer");
                lista = carpeta.listFiles();
                numeroDeArchivos = lista.length;
                break;
        }
        return numeroDeArchivos;
    }

    /**
     * Metodo que dependiendo del artista proporcionado crea un url aleatorio que se corresponte con la ubicación del archivo de audio
     * @throws java.lang.NullPointerException
     * @param artista
     * @return String url
     */
    @Override
    public String generaURLaleatorio(Artista artista) throws NullPointerException{
        String url = "";
        
        String claseArtista = artista.getClass().getSimpleName();
        switch (claseArtista) {
            case "Cantante":
                if (artista.getSexo() == true) {
                    url = "./pistas/PistasCantantes/mujer/cancion" + extraerNumAleatorioSinRepeticion(cancionesMujer) + ".wav";
                } else {
                    url = "./pistas/PistasCantantes/hombre/cancion" + extraerNumAleatorioSinRepeticion(cancionesHombre) + ".wav";
                }
                break;
            case "Musico":
                url = "./pistas/PistasMusicos/pista" + extraerNumAleatorioSinRepeticion(pistasMusica) + ".wav";
                break;
            case "Poeta":
                if (artista.getSexo() == true) {
                    url = "./pistas/PistasPoetas/mujer/poema" + extraerNumAleatorioSinRepeticion(poemasMujer) + ".wav";
                } else {
                    url = "./pistas/PistasPoetas/hombre/poema" + extraerNumAleatorioSinRepeticion(poemasHombre) + ".wav";
                }
                break;
        }
        
        return url;
    }

    /**
     *  Metodo que partiendo de un array, proporcionado genera un indice aleatorio y extrae el numero que se corresponde con dicho indice
     * @throws java.lang.NullPointerException
     * @param listaArchivos
     * @return int numeroDeArchivos
     * 
     */
    @Override
    public int extraerNumAleatorioSinRepeticion(ArrayList<Integer> listaArchivos) throws NullPointerException{
        int numeroAleatorio = -1;
        
        Random random = new Random();
        do {
            int indiceAleatorio = random.nextInt(listaArchivos.size());
            numeroAleatorio = listaArchivos.get(indiceAleatorio);

            if (numeroAleatorio != 0) {
                listaArchivos.set(indiceAleatorio, 0);
            }
        } while (numeroAleatorio == 0);
        
        return numeroAleatorio;
    }
    

    public ArrayList<Integer> getCancionesMujer() {
        return cancionesMujer;
    }

    public ArrayList<Integer> getCancionesHombre() {
        return cancionesHombre;
    }

    public ArrayList<Integer> getPoemasMujer() {
        return poemasMujer;
    }

    public ArrayList<Integer> getPoemasHombre() {
        return poemasHombre;
    }

    public ArrayList<Integer> getPistasMusica() {
        return pistasMusica;
    }
}
