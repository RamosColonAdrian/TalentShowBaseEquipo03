package model.controller;

import model.serializar.SerializarIMPL;
import model.malabaristas.ActuacionesMalabaristaIMPL;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.audios.AudiosIMPL;
import model.beans.EstadoConcurso;
import model.process.Aplicacion;
import view.menu.ConstruirMenuIMPL;
import view.menu.Menu;
import view.utilities.Util;
import view.utilities.es.UtilES;

/**
 * Clase que contiene los metodos dedicados a la controlacion de la aplicacion y
 * sus menus correspondientes.
 *
 * @author RaulMV
 */
public class Controlador implements Serializable {

    SerializarIMPL utilidadesSerializar;
    private EstadoConcurso concurso;
    private ConstruirMenuIMPL constructor;
    private Aplicacion app;
    private Menu menu;
    private Menu menuVolver;
    private Menu menuAsegurador;
    private Menu menuSalida;
    private int opcionElegida;
    private UtilES utilidades;

    /**
     * Constructor que comprueba la existencia de archivos guardados en el
     * directorio, en caso de que los archivos no sean encontrados se creara un
     * nuevo concurso.
     */
    public Controlador() {
        utilidadesSerializar = new SerializarIMPL();
        utilidades = new UtilES();
        compruebaArchivosGuardados();
    }



    private void compruebaArchivosGuardados(){
        opcionElegida = 0;
        if (Files.exists(Util.convertirEnPath("app.ser")) && Files.exists(Util.convertirEnPath("concurso.ser"))
        && Files.exists(Util.convertirEnPath("constructor.ser"))) {
            try {
                concurso = (EstadoConcurso) utilidadesSerializar.desSerializar("concurso.ser");
                constructor = (ConstruirMenuIMPL) utilidadesSerializar.desSerializar("constructor.ser");
                app = (Aplicacion) utilidadesSerializar.desSerializar("app.ser");
            } catch (NullPointerException npe) {
                utilidades.mensajeln("Error inesperado a la hora de leer los archivos serializados. Las variables se establecen a null.");
            } catch (IOException ex) {
                utilidades.mensajeln("Error fatal inesperado.");
            } catch (ClassNotFoundException ex) {
                utilidades.mensajeln("Error inesperado, la clase no exixste o no se encuentra");
            }
        } else {
            constructor = new ConstruirMenuIMPL(utilidades);
            concurso = new EstadoConcurso();
            app = new Aplicacion(utilidades);
            seleccionaMenu();
        }
    }
 
    
    /**
     * Metodo que carga hasta el momento de finalizacion del programa el menu.
     */
    public void ejecutar() {
        while (true) {
            cargaMenu();
        }
    }

    /**
     * Metodo que teniendo en cuenta las variables booleanas de la clase
     * EstadoConcurso decide cual es el menu que corresponde.
     */
    private void seleccionaMenu() {
        if (concurso.getFinalizado() == true) {
            this.menu = constructor.getMenuFinalizado();
        } else {
            if (concurso.getLanzado() == false) {
                if (concurso.getNuevoConcurso() == true) {
                    this.menu = constructor.getMenuNuevo();
                } else {
                    this.menu = constructor.getMenuParado();
                }
            } else {
                this.menu = constructor.getMenuLanzado();
            }
        }
        this.menuAsegurador = constructor.getMenuAsegurador();
        this.menuVolver = constructor.getMenuVolver();
        this.menuSalida = constructor.getMenuSalida();
    }

    /**
     * Metodo que dependiendo del menu generado redirecciona a unas opciones u
     * otras.
     */
    private void redireccionador() {

        if (menu.getOpciones().size() == constructor.getMenuNuevo().getOpciones().size()) {
            ejecutarOpcionesNuevo();
        } else if (menu.getOpciones().size() == constructor.getMenuParado().getOpciones().size()) {
            ejecutarOpcionesParado();
        } else if (menu.getOpciones().size() == constructor.getMenuLanzado().getOpciones().size()) {
            ejecutarOpcionesLanzado();
        } else if (menu.getOpciones().size() == constructor.getMenuFinalizado().getOpciones().size()) {
            ejecutarOpcionesFinalizado();
        }
    }

    /**
     * Metodo que redirecciona a las opciones del menu de un nuevo concurso.
     */
    private void ejecutarOpcionesNuevo() {
        if (this.opcionElegida == 1) {

            while (volver() != 2) {
                app.registraUsuario();
            }
            compruebaArtistas();

        } else {
            salir();
        }
    }

    /**
     * Metodo que redirecciona a las opciones del menu de un concurso parado.
     */
    private void ejecutarOpcionesParado() {
        switch (this.opcionElegida) {
            case 1:

                while (volver() != 2) {
                    app.registraUsuario();
                }

                break;
            case 2:
                concurso.setLanzado(true);
                break;
            case 3:
                app.consultarClasificacion();
                break;
            case 4:
                salir();
                break;
            default:
                break;
        }
    }

    /**
     * Metodo que redirecciona a las opciones del menu de un concurso lanzado.
     */
    private void ejecutarOpcionesLanzado() {
        seleccionaMenu();
        switch (this.opcionElegida) {
            case 1:
                if (asegurador() == 1) {
                    app.concursarYpuntuar();
                }
                break;
            case 2:
                concurso.setLanzado(false);
                //Metodo que detiene el concurso.
                break;

            case 3:
                //Muestra las posiciones actuales.
                app.consultarClasificacion();
                break;
            case 4:
                if (asegurador() == 1) {
                    concurso.setFinalizado(true);
                    concurso.setLanzado(false);
                }
                break;
            case 5:
                salir();
                break;
            default:
                break;
        }
    }

    /**
     * Metodo que redirecciona a las opciones del menu de un concurso
     * finalizado.
     */
    private void ejecutarOpcionesFinalizado() {
        switch (this.opcionElegida) {

            //Metodo que inicializa un nuevo concurso.
            case 1:
                concurso.setNuevoConcurso(true);
                concurso.setFinalizado(false);
                limpiarArchivos();
                app.reiniciarVariables();
                break;
            case 2:
                app.consultarClasificacion();
                break;
            case 3:
                salir();
                break;
            default:
                break;
        }
    }

    /**
     * Metodo que guarda en archivos serializados los datos de importancia para
     * el desarrollo del concurso.
     */
    private void guardar() {
        try {
            AudiosIMPL reproductorAudios = AudiosIMPL.getIntance();
            ActuacionesMalabaristaIMPL actuacionesMalabaristas = ActuacionesMalabaristaIMPL.getIntance();
            utilidadesSerializar.serializar(app, "app.ser");
            utilidadesSerializar.serializar(concurso, "concurso.ser");
            utilidadesSerializar.serializar(constructor, "constructor.ser");
            utilidadesSerializar.serializar(reproductorAudios.getCancionesHombre(), "cancionesHombre.ser");
            utilidadesSerializar.serializar(reproductorAudios.getCancionesMujer(), "cancionesMujer.ser");
            utilidadesSerializar.serializar(reproductorAudios.getPistasMusica(), "pistasMusica.ser");
            utilidadesSerializar.serializar(reproductorAudios.getPoemasHombre(), "poemasHombre.ser");
            utilidadesSerializar.serializar(reproductorAudios.getPoemasMujer(), "poemasMujer.ser");
            utilidadesSerializar.serializar(actuacionesMalabaristas.getObjetosMalabares(), "objetosMalabares.ser");
        } catch (IOException ex) {
            utilidades.mensajeln("Se ha producido un error fatal");
        } catch (NullPointerException ex) {
            utilidades.mensajeln("Se ha producido un error inesperado, uno de los parametros introducidos son nulos");
        } catch (ClassNotFoundException ex) {
            utilidades.mensajeln("Se ha producido un error fatal, la clase especificada no se encuentra");        }
    }

    /**
     * Metodo que realiza un conjunto de acciones que toma la opcione elegida
     * por el usuario mientras que muestra las opciones del menu
     * correspondiente.
     */
    private void cargaMenu() {
        utilidades.separador();
        seleccionaMenu();
        menu.muestraMenu();
        opcionElegida = menu.leeOpcion();
        redireccionador();
        utilidades.separador();
    }

    /**
     * Metodo que pregunta al usuario si desea continuar el proceso o volver al
     * menu inicial.
     *
     * @param opcVolver
     * @return opcVolver
     */
    private int volver() {
        int opcVolver;
        utilidades.mensajeln("\n¿Desea volver al inicio o continuar el proceso?");
        menuVolver.muestraMenu();
        opcVolver = menuVolver.leeOpcion();
        return opcVolver;
    }

    /**
     * Metodo que pregunta al usuario si desea continuar el proceso o volver al
     * menu inicial.
     *
     * @param opcSegura
     * @return opcSegura
     */
    private int asegurador() {
        int opcSegura;
        utilidades.mensajeln("\n¿Desea continuar el proceso o volver al inicio?");
        menuAsegurador.muestraMenu();
        opcSegura = menuAsegurador.leeOpcion();
        return opcSegura;
    }

    
    /**
     * Metodo que se asegura de que el usuario sabe que va a finalizar el 
     * programa.
     * 
     * @param confirmarSalida
     * @param opcSalida
     * @return confirmarSalida
     */
    private boolean asegurarSalida() {
        int opcSalida = 10;
        boolean confirmarSalida = false;
        while (!confirmarSalida) {
            try {
                while (opcSalida < 0 || opcSalida > 1) {
                    menuSalida.muestraMenu();
                    opcSalida = menuSalida.leeOpcion();

                    if (opcSalida < 0 || opcSalida > 1) {
                        utilidades.mensajeln("El dato introducido esta fuera de rango.");
                    } else {
                        if (opcSalida == 1) {
                            utilidades.mensajeln("El programa va a terminar");
                            confirmarSalida = true;
                        }
                    }
                }
            } catch (InputMismatchException im) {
                utilidades.mensajeln("El dato introducido no es valido.");
            }
        }
        return confirmarSalida;
    }

    /**
     * Metodo que finaliza el programa guardando antes los datos de importancia.
     */
    private void salir() {
        if (asegurarSalida() == true) {
            guardar();
        }
        Util.finalizador();
    }

    /**
     * Metodo que borra todos los archivos.
     */
    private void limpiarArchivos() {
        try {
            Files.deleteIfExists(Util.convertirEnPath("app.ser"));
            Files.deleteIfExists(Util.convertirEnPath("concurso.ser"));
            Files.deleteIfExists(Util.convertirEnPath("constructor.ser"));
            Files.deleteIfExists(Util.convertirEnPath("pistasMusica.ser"));
            Files.deleteIfExists(Util.convertirEnPath("cancionesHombre.ser"));
            Files.deleteIfExists(Util.convertirEnPath("cancionesMujer.ser"));
            Files.deleteIfExists(Util.convertirEnPath("poemasMujer.ser"));
            Files.deleteIfExists(Util.convertirEnPath("poemasHombre.ser"));
            Files.deleteIfExists(Util.convertirEnPath("cancionesMujer.ser"));
            Files.deleteIfExists(Util.convertirEnPath("objetosMalabares.ser"));
        } catch (IOException io) {
            utilidades.mensajeln("Se ha producido un error fatal inesperado.");
        } catch (SecurityException se) {
            utilidades.mensajeln("Se ha producido un error inesperado debido a los privilegios o la configuracion de seguridad.");
        } 
    }

    /**
     * Metodo que comprueba que el concurso este iniciado o no.
    */
    private void compruebaArtistas() {
        if (app.getArtistasRegistrados().isEmpty()) {
            concurso.setNuevoConcurso(true);
        } else {
            concurso.setNuevoConcurso(false);
        }
    }

}
