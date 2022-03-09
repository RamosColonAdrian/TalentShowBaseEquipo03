/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

import java.io.Serializable;
import java.util.InputMismatchException;
import view.utilities.es.UtilES;


/**
 *
 * @author raulm
 */
public class Menu implements Serializable {

    
    private OpcionesMenu opciones;
    private UtilES utiles;


    public Menu(OpcionesMenu opciones, UtilES utiles) {
        this.opciones = opciones;   
        this.utiles = utiles;
   }

    public int leeOpcion() {
        int opcionElegida = 0;
        int opcSize = opciones.size();
        try {
            while (opcionElegida < 1 || opcionElegida > opcSize) {
                opcionElegida = utiles.pideNumero("> Introduce la opción elegida: ");
                utiles.mensajeln("\n");
                if (opcionElegida < 1 || opcionElegida > opcSize) {
                    utiles.mensajeln("El dato introducido debe estar dentro de los limites requeridos.\n");
                }
            }

        } catch (InputMismatchException im) {
            utiles.mensajeln("Los datos introducidos deben estar dentro de los limites requeridos.");
            opcionElegida = leeOpcion();
        }
        return opcionElegida;
    }
  
    public void muestraMenu() {
        int indice = 1;
        for (String opcion : opciones) {
            utiles.mensajeln(indice + " " + opcion);
            indice++;
        }
    }

    public OpcionesMenu getOpciones() {
        return opciones;
    }


    

    
    
}
