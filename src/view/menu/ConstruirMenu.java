/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

/**
 * Interfaz donde se especifican los metodos necesarios para la creacion de
 * menus.
 *
 * @author Raul MV
 */
public interface ConstruirMenu {

    /**
     * @see view.menu.ConstruirMenuIMPL#llenaMenus()
     */
    public void llenaMenus();

    /**
     * @see view.menu.ConstruirMenuIMPL#llenaNuevo()
     */
    public void llenaNuevo();

    /**
     * @see view.menu.ConstruirMenuIMPL#llenaParado()
     */
    public void llenaParado();

    /**
     * @see view.menu.ConstruirMenuIMPL#llenaLanzado()
     */
    public void llenaLanzado();

    /**
     * @see view.menu.ConstruirMenuIMPL#llenaFinalizado()
     */
    public void llenaFinalizado();

    /**
     * @see view.menu.ConstruirMenuIMPL#llenaVolver()
     */
    public void llenaVolver();

    /**
     * @see view.menu.ConstruirMenuIMPL#llenaAsegurador()
     */
    public void llenaAsegurador();

    /**
     * @see view.menu.ConstruirMenuIMPL#llenaSalida()
     */
    public void llenaSalida();

}
