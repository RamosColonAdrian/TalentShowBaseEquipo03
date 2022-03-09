package view.utilities.es;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Adrian Ramos Colon
 */
public class UtilES implements Serializable{

    private boolean separadorUsado = false;
    
    /**
     * Metodo que muestra un mensaje sin salto de linea por terminal
     * @param mensaje
     */
    public  void mensaje(String mensaje) {
        System.out.print(mensaje);
    }

    /**
     * Metodo que muestra un mensaje con salto de linea por terminal
     * @param mensaje
     */
    public  void mensajeln(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Metodo que pide una cadena al usuario
     * @param mensaje Mensaje que se le muestra al usuario a la hora de pedir el numero
     * @return cadena
     */
    public  String pideCadena(String mensaje) {
        Scanner sc = new Scanner(System.in);
        mensaje(mensaje);
        String cadena;
        cadena = sc.nextLine();
        return cadena;
    }

    /**
     * Metodo que pide un numero al usuario
     * @param mensaje Mensaje que se le muestra al usuario a la hora de pedir el numero
     * @return numero
     * @throws InputMismatchException
     */
    public  int pideNumero(String mensaje) throws InputMismatchException {
        Scanner sc = new Scanner(System.in);
        int numero = 0;
        mensaje(mensaje);
        numero = sc.nextInt();
        return numero;
    }

    /**
     * Metodo que genera una linea como separador dentro del terminal
     */
    public void separador() {
            mensajeln("");
            if (this.separadorUsado == true){
                mensajeln("-----------------------");
                this.separadorUsado = false;
            }else{
                this.separadorUsado  = true;
            }
            mensajeln("");
    }
    
    /**
     * Metodo que pide un numero dentro de un rango
     * @param mensaje Mensaje que se le muestra al usuario a la hora de pedir el numero
     * @param mensajeError Mensaje que se muestra si se produce un error
     * @param max Numero maximo del rango
     * @param min Numero minimo del rango
     * @return numero
     */
    public  int pideNumero(String mensaje, String mensajeError, int max, int min) {
        Scanner sc = new Scanner(System.in);
        int numero = 0;
        do {
            try {
                mensaje(mensaje);
                numero = sc.nextInt();
                if (numero < min || numero > max) {
                    mensajeln(mensajeError);
                }
            } catch (NumberFormatException | InputMismatchException nfe) {
                mensajeln("Error inesperado. Se esperaba un entero");
                sc.next();
            }
        } while (numero < min || numero > max);
        return numero;
    }
}
