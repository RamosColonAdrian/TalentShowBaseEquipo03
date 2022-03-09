
package view.menu;

import java.io.Serializable;
import view.utilities.es.UtilES;


/**
 * Clase de implemetnacion de la interfaz ConstruirMenus. En esta clase se
 * desarrollan los metodos necesarios para la construccion de los menus que
 * van a utilizar."
 *
 * @author RaulMV
 */
public class ConstruirMenuIMPL implements ConstruirMenu, Serializable {
    
    private Menu menuParado;
    private Menu menuLanzado;
    private Menu menuFinalizado;
    private Menu menuNuevo;
    private Menu menuVolver;
    private Menu menuAsegurador;
    private Menu menuSalida;
    private UtilES utilidades;

    public ConstruirMenuIMPL(UtilES utilidades) {
        this.utilidades = utilidades;
        llenaMenus();
        
    }

    
    /**
     * Metodo que ejecuta los metodos de llenado objetos ArrayList que pertenecen a la clase OpcionesMenu
     * para asi definir las opciones que el menu contiene.
     */
    @Override
    public void llenaMenus() {

        llenaNuevo();

        //-------------
        llenaParado();

        //-------------
        llenaLanzado();

        //-------------
        llenaFinalizado();
        
        //-------------
        llenaVolver();
        
        //-------------
        llenaAsegurador();
        
        //-------------
        llenaSalida();

    }
    
    /**
     * Metodo que llena el la lista con las opciones de un nuevo concurso y crea el menu 
     * correspondiente con esa opciones.
     */
    @Override
    public void llenaNuevo(){
        OpcionesMenu opcionesNuevo = new OpcionesMenu();
        opcionesNuevo.add("(Pulsa 1) Registro de concursantes.");
        opcionesNuevo.add("(Pulsa 2) Salir del programa.");
        menuNuevo = new Menu(opcionesNuevo,utilidades);
    }

    /**
     * Metodo que llena el la lista con las opciones de un concurso parado y crea el menu 
     * correspondiente con esa opciones.
     */
    @Override
    public void llenaParado() {
        OpcionesMenu opcionesParado = new OpcionesMenu();
        opcionesParado.add("(Pulsa 1) Registro de concursantes.");
        opcionesParado.add("(Pulsa 2) Administracion del concurso.");
        opcionesParado.add("(Pulsa 3) Clasificacion actual.");
        opcionesParado.add("(Pulsa 4) Salir del programa.");
        menuParado = new Menu(opcionesParado,utilidades);
    }

    /**
     * Metodo que llena el la lista con las opciones de un concurso lanzado y crea el menu 
     * correspondiente con esa opciones.
     */
    @Override
    public void llenaLanzado(){
        OpcionesMenu opcionesLanzado = new OpcionesMenu();
        opcionesLanzado.add("(Pulsa 1) Concursar.");
        opcionesLanzado.add("(Pulsa 2) Volver al registro.");
        opcionesLanzado.add("(Pulsa 3) Consultar clasificacion actual.");  
        opcionesLanzado.add("(Pulsa 4) Finalizar concurso.");
        opcionesLanzado.add("(Pulsa 5) Salir del programa.");
        
        menuLanzado = new Menu(opcionesLanzado,utilidades);
    }
    
    /**
     * Metodo que llena el la lista con las opciones de un concurso finalizado y crea el menu 
     * correspondiente con esa opciones.
     */
    @Override
    public void llenaFinalizado() {
        OpcionesMenu opcionesFinalizado = new OpcionesMenu();
        opcionesFinalizado.add("(Pulsa 1) Iniciar nuevo concurso");
        opcionesFinalizado.add("(Pulsa 2) Clasificacion final.");
        opcionesFinalizado.add("(Pulsa 3) Salir del programa.");
        menuFinalizado = new Menu(opcionesFinalizado,utilidades);
    }
    
    
    /**
     * Metodo que llena el la lista con las opciones de un menu para volver al menu principal y crea el menu 
     * correspondiente con esa opciones.
     */
    @Override
    public void llenaVolver(){
        OpcionesMenu opcionesVolver = new OpcionesMenu();
        opcionesVolver.add("(Pulsa 1) Continuar con el proceso.");
        opcionesVolver.add("(Pulsa 2) Volver al inicio.");
        menuVolver = new Menu(opcionesVolver, utilidades);
    }
    
    /**
     * Metodo que llena el la lista con las opciones de un menu para volver al menu cuando se quiere salir del programa y crea el menu 
     * correspondiente con esa opciones.
     */
    @Override
    public void llenaAsegurador(){
        OpcionesMenu opcionesAsegurador = new OpcionesMenu();
        opcionesAsegurador.add("(Pulsa 1) Continuar con la eleccion.");
        opcionesAsegurador.add("(Pulsa 2) Volver al menu principal.");
        menuAsegurador = new Menu(opcionesAsegurador, utilidades);
    }
    
    /**
     * Metodo que llena el la lista con las opciones de un menu para volver al menu principal y crea el menu 
     * correspondiente con esa opciones.
     */
    @Override
    public void llenaSalida(){
        OpcionesMenu opcionesSalida = new OpcionesMenu();
        opcionesSalida.add("(Pulsa 1) Para salir");
        opcionesSalida.add("(Pulsa 2) Para volver al menu");
        menuSalida = new Menu(opcionesSalida, utilidades);
    }
    
    public Menu getMenuSalida(){
        return menuSalida;
    }
    
    public Menu getMenuVolver(){
        return menuVolver;
    }
    
    public Menu getMenuAsegurador(){
        return menuAsegurador;
    }
    
    public Menu getMenuParado() {
        return menuParado;
    }

    public Menu getMenuLanzado() {
        return menuLanzado;
    }
   

    public Menu getMenuFinalizado() {
        return menuFinalizado;
    }

    public Menu getMenuNuevo() {
        return menuNuevo;
    }

}
