package model.registros;

import java.io.IOException;
import java.io.Serializable;
import model.beans.Artista;
import model.beans.Cantante;
import model.beans.Malabarista;
import model.beans.Musico;
import model.beans.Poeta;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.audios.AudiosIMPL;
import model.malabaristas.ActuacionesMalabaristaIMPL;
import view.utilities.es.UtilES;

/**
 * Clase de implemetnacion de la interfaz Registros. En esta clase se
 * desarrollan los metodos necesarios para la construccion de los artistas que
 * van a concursar."
 *
 * @author RaulMV
 */
public class RegistrosIMPL implements Registros, Serializable {

    private UtilES utiles;
    private AudiosIMPL audios; 
    private ActuacionesMalabaristaIMPL malabaristas;
    
    public RegistrosIMPL(UtilES utiles){
        this.utiles = utiles;
        this.audios = inicializaAudios();
        this.malabaristas = inicializaMalabarista();
        
    }
    
    
    private AudiosIMPL inicializaAudios(){
        AudiosIMPL audiosInstancia = null;
        try {
            audiosInstancia = AudiosIMPL.getIntance();
        } catch (IOException ex) {
            Logger.getLogger(RegistrosIMPL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrosIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return audiosInstancia;
    }
    
    private ActuacionesMalabaristaIMPL inicializaMalabarista(){
        ActuacionesMalabaristaIMPL malabaristasInstancia = null;
        try {
            malabaristasInstancia = ActuacionesMalabaristaIMPL.getIntance();
        } catch (IOException ex) {
            utiles.mensajeln("Se ha producido un error fatal");        
        } catch (ClassNotFoundException ex) {
            utiles.mensajeln("Se ha producido un error inesperado,la clase especificada no se encuentra o no existe");        
        }
       return malabaristasInstancia;
    }
    
    /**
     * Metodo que llama al resto de metodos y termina generado un artista.
     * @return 
     */
    @Override
    public Artista pideDatos() {

        utiles.mensajeln("\n-- Registro de nuevo concursante --");
        return generaArtista();
    }

    /**
     * Metodo que recoge la informacion sobre la clase de artista que es el
     * nuevo concursante.
     * @return 
     */
    @Override
    public int pideTipoArtista() {
        int profesion = 0;
        boolean isOk = false;

        while (!isOk) {
            while (profesion < 1 || profesion > 4) {
                try {
                    utiles.mensajeln("Selecciona el tipo de artista:");
                    utiles.mensajeln("Escribe 1 si es un Cantante.");
                    utiles.mensajeln("Escribe 2 si es un Malabarista.");
                    utiles.mensajeln("Escribe 3 si es un Musico.");
                    utiles.mensajeln("Escribe 4 si es un Poeta.");
                    profesion = utiles.pideNumero("> Tipo: ");
                    if (profesion < 1 || profesion > 4) {
                        utiles.mensajeln("Dato introducido no valido.");
                        utiles.mensajeln("El proceso volvera a ejecutarse.");
                    } else {
                        isOk = true;
                    }
                } catch (InputMismatchException im) {
                    utiles.mensajeln("Es necesario introfucir un numero valido.");
                    utiles.mensajeln("El proceso se ejecutará de nuevo. \n");
                }
            }
        }

        return profesion;
    }

    /**
     * // Metodo que dependiendo de la opcion elegida con anterioridad // crea
     * un artista usando el metodo correspondiente.
     *
     * @return 
     */
    @Override
    public Artista generaArtista() {
        Artista artista = null;
        int profesion = pideTipoArtista();
        switch (profesion) {
            case 1:
                artista = creaCantante();
                break;
            case 2:
                artista = creaMalabarista();
                break;
            case 3:
                artista = creaMusico();
                break;

            case 4:
                artista = creaPoeta();
                break;
        }
        return artista;
    }

    /**
     * Metodo que genera un artista de tipo cantante.
     */
    public Cantante creaCantante() {
        Cantante cantante = new Cantante(pideNombre(), pideApellidos(), pideLocalidad(), pideEdad(), pideSexo(), audios);

        return cantante;
    }

    /**
     * Metodo que genera un artista de tipo malabarista.
     * @return 
     */
    public Malabarista creaMalabarista() {
        Malabarista malabarista = new Malabarista(pideNombre(), pideApellidos(), pideLocalidad(), pideEdad(), pideSexo(), malabaristas);

        return malabarista;
    }

    /**
     * Metodo que genera un artista de tipo musico.
     */
    public Musico creaMusico() {
        Musico musico = new Musico(pideNombre(), pideApellidos(), pideLocalidad(), pideEdad(), pideSexo(), audios);

        return musico;
    }

    /**
     * Metodo que genera un artista de tipo poeta.
     */
    public Poeta creaPoeta() {
        Poeta poeta = new Poeta(pideNombre(), pideApellidos(), pideLocalidad(), pideEdad(), pideSexo(), audios );
        return poeta;
    }

    /**
     * Metodo que recibe el dato numerico que decide el sexo del concursante a
     * registrar.
     */
    @Override
    public boolean pideSexo() {
        int respuestaSexo = -1;
        boolean sexo = true;
        boolean isOk = false;

        while (!isOk) {
            //Generacion de una variable que nos da el resultado de la eleccion del usuario
            utiles.mensajeln("\nIntroduce tu sexo: ");
            utiles.mensajeln("Pulsa 0 si eres mujer");
            utiles.mensajeln("Pulsa 1 si eres hombre");
            try {
                respuestaSexo = utiles.pideNumero("> Indica tu sexo: ");

                if (respuestaSexo == 0 || respuestaSexo == 1) {
                    if (respuestaSexo == 0) {
                        sexo = true;
                    } else {
                        sexo = false;
                    }
                    //Definicion de si el usuario es hombre o mujer en base a su eleccion dejando de lado un dato erroneo
                    isOk = true;
                } else {
                    utiles.mensajeln("El dato introducido está fuera de rango. \n");
                }

            } catch (NumberFormatException ex) {
                utiles.mensajeln("Los datos requeridos para el sexo se han introducido erroneamente, se esperaba un entero.");
                utiles.mensajeln("El proceso se ejecutará de nuevo. \n");

            } catch (InputMismatchException im) {
                utiles.mensajeln("Los datos requeridos para el sexo se han introducido erroneamente, se esperaba un numero.");
                utiles.mensajeln("El proceso se ejecutará de nuevo. \n");

            }

        }
        return sexo;
    }

    /**
     * Metodo que genera la propiedad sexo en base a la eleccion que recae sobre
     * un integer.
     */
    @Override
    public int pideEdad() {
        int edad = 0;
        boolean isOk = false;

        while (!isOk) {   //es lo mismo que isOk == false
            try {
                do {
                    edad = utiles.pideNumero("> Edad: ");
                    if (edad < 18 || edad > 130) {
                        utiles.mensajeln("La edad no está en el rango permitido");
                    } else {
                        isOk = true;
                    }
                } while (edad < 18 || edad > 120);

            } catch (InputMismatchException im) {
                utiles.mensajeln("Es necesario introducir un numero valido.");
                utiles.mensajeln("El proceso se ejecutará de nuevo. \n");
            }
        }

        return edad;
    }

    /**
     * Metodo que genera la propiedad localidad en base a la eleccion que recae
     * sobre un string.
     */
    @Override
    public String pideLocalidad() {
        String localidad = "";
        do {
            localidad = utiles.pideCadena("> Localidad de origen: ").trim();
            if (localidad.isEmpty()) {
                utiles.mensajeln("Este campo no puede quedar vacio.");
            }
        } while (localidad.isEmpty());
        return localidad;
    }

    /**
     * Metodo que genera la propiedad nombre en base a la eleccion que recae
     * sobre un string.
     */
    @Override
    public String pideNombre() {
        String nombre = "";
        do {
            nombre = utiles.pideCadena("> Nombre: ");
            if (nombre.isEmpty()) {
                utiles.mensajeln("Este campo no puede quedar vacio.");
            }
        } while (nombre.isEmpty());

        return nombre;
    }

    /**
     * Metodo que genera la propiedad apellidos en base a la eleccion que recae
     * sobre un string.
     */
    @Override
    public String pideApellidos() {
        String apellidos = "";
        do {
            apellidos = utiles.pideCadena("> Apellidos: ").trim();
            if (apellidos.isEmpty()) {
                utiles.mensajeln("Debe introducir al menos un apellido.");
            }
        } while (apellidos.isEmpty());
        return apellidos;
    }

}
