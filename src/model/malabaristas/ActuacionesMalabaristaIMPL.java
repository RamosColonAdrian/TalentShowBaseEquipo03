
package model.malabaristas;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;
import model.serializar.SerializarIMPL;
import view.utilities.Util;
import view.utilities.es.UtilES;

/**
 * Clase Singeltone de implementacion de la interface Audios que desarrolla todos los metodos para que la reproducción de clips de audios se lleven a cabo.
 * Una clase singleton debe tener visibilidad pública para que la aplicación completa la pueda usar
 * @author Adrian Ramos Colon
 * 
 */
public final class ActuacionesMalabaristaIMPL implements ActuacionesMalabarista, Serializable{

    /**
     *   Instancia estática de clase 
     */
    private static ActuacionesMalabaristaIMPL actuacionesMalabarista = null;
    private UtilES es;
    ArrayList<String> objetosMalabares;
    SerializarIMPL utilidadesSerializar = new SerializarIMPL();
    
    
    /**
     * Método público, si no existe una instancia de la clase, cree una nueva de lo contrario devuleve la propia instancia.
     * @return actuacionesMalabarista
     * @throws java.io.IOException Se lanza cuando una IOException ocurre
     * @throws java.io.FileNotFoundException Se lanza cuando el archivo no se encuentra o no existe
     * @throws java.lang.ClassNotFoundException Se lanza cuando la clase especificada no existe o no se encuentra
     */
    public static ActuacionesMalabaristaIMPL getIntance() throws IOException, FileNotFoundException, ClassNotFoundException{
        
        if(actuacionesMalabarista == null){
            actuacionesMalabarista = new ActuacionesMalabaristaIMPL();
        }
        return actuacionesMalabarista;
    }
    
    
    /**
     * Constructor privado que, antes de inicializar las propiedades, comprueba si existen alguna de estas guardadas en el sistema como archivo serializado 
     */
    private ActuacionesMalabaristaIMPL() throws IOException, FileNotFoundException, ClassNotFoundException{
        objetosMalabares = new ArrayList<>();
        if (Files.exists(Util.convertirEnPath("objetosMalabares.ser"))) {
            objetosMalabares = (ArrayList<String>) utilidadesSerializar.desSerializar("objetosMalabares.ser");
        }else
            crearObjetosMalabares();
    }

    

    /**
     * Metodo de ejecucion de la actuacion de los malabaristas
     */
    @Override
    public void malabares() {
        String mensaje;
        mensaje = "Estoy haciendo malabares con " + Util.numeroAleatorio(2, 7) + " " + extaerStringAleatorioSinRepeticion();
        switch (porcentajeError()) {
            case 2:
                System.out.println(mensaje + (" \"Se tropieza y se cae\""));
                break;
            case 1:
                System.out.println(mensaje + (" \"Se le resbala uno de los objetos\""));
                break;

            case 0:
                System.out.println(mensaje + (" \"Casi se le cae uno de los objetos\""));
                break;
            case -1:
            case -2:
                System.out.println(mensaje + (" \"Todo sale perfecto\""));
                break;
        }
    }

    /**
     * Metodo que añade al arrayList los objetos con lo que el malabarista puede actuar
     */
    @Override
    public void crearObjetosMalabares() {
        objetosMalabares.add("Motosierras");
        objetosMalabares.add("Latas de Coca-Cola");
        objetosMalabares.add("Bolos");
        objetosMalabares.add("Naranjas");
        objetosMalabares.add("Melones");
        objetosMalabares.add("Cuchillos");
        objetosMalabares.add("Extintores");
        objetosMalabares.add("Paragüas");
        objetosMalabares.add("Machetes");
        objetosMalabares.add("Aros");
        objetosMalabares.add("Platos");
        objetosMalabares.add("Diabolos");
        objetosMalabares.add("Mazas");
    }
    
    
    
    /**
     * Metodo que extrae una palabra (obejto de malabares) del arrayList sin repeticion.
     * @return palabraAleatoria Es el "objeto" con el que se realizan los malabares
     */
    @Override
    public String extaerStringAleatorioSinRepeticion() {
        String palabraAleatoria="";
        Random random = new Random();
        do {
            int indiceAleatorio = random.nextInt(objetosMalabares.size());
            palabraAleatoria = objetosMalabares.get(indiceAleatorio);

            if (!"".equals(palabraAleatoria)) {
                objetosMalabares.set(indiceAleatorio, "");
            }
        } while ("".equals(palabraAleatoria));

        return palabraAleatoria;
    }
    
    /**
     * Metodo que genera un numero entre 1 y 100
     * @return  <ul>
     *              <li> 2 si probabilidadError>80 </li>
     *              <li> 1 si probabilidadError>60 </li>
     *              <li> 0 si probabilidadError>40 </li>
     *              <li> -1 si probabilidadError>20 </li>
     *              <li> -2 si probabilidadError<20 </li>
     *          </ul>
     */
    @Override
    public int porcentajeError(){
        int probabilidadError =Util.numeroAleatorio(100, 0);
        if(probabilidadError>80){
            return 2;  
        }else if(probabilidadError>60){
            return 1;
        }else if(probabilidadError>40){
            return 0;
        }else if (probabilidadError>20){
            return-1;
        }else
            return -2;
    }
    
    /**
     * Metodo getter
     * @return objetosMalabares
     */
    public ArrayList<String> getObjetosMalabares() {
        return objetosMalabares;
    }
    
}
