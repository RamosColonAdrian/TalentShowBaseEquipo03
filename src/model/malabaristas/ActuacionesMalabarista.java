
package model.malabaristas;

/**
 * Interfaz donde se especifican los metodos abstractos necesarios para la ejecucion de las actuaciones de los malabaristas.
 * @author Adrian Ramos Colon
 */
public interface ActuacionesMalabarista {

    /**
     * Firma del metodo que crea objetos con los que el malabarista actuará
     */
    public void crearObjetosMalabares();
    
    /**
     * Firma del metodo que extrae de un contenedor de "objetos" uno aleatoriamente sin repeticion
     * @return String palabraAleatoria
     */
    public String extaerStringAleatorioSinRepeticion();
    
    /**
     * Firma del metodo que genera un numero aleatorio como porcentaje de error
     * @return <ul>
     *              <li> 2 si probabilidadError>80 </li>
     *              <li> 1 si probabilidadError>60 </li>
     *              <li> 0 si probabilidadError>40 </li>
     *              <li> -1 si probabilidadError>20 </li>
     *              <li> -2 si probabilidadError<20 </li>
     *          </ul>
     */
    public int porcentajeError();
    
    /** 
     * Firma del metodo que posibilita la ejecucion de la actuacion de los malabaristas
     */
    public void malabares();
}
