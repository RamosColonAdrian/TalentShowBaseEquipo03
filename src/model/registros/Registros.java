/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.registros;

import model.beans.Artista;

/**
 *
 * @author delta
 */
public interface Registros {
    public Artista pideDatos();
    public int pideTipoArtista();
    public Artista generaArtista();
    public String pideNombre();
    public boolean pideSexo();
    public int pideEdad();
    public String pideLocalidad();
    public String pideApellidos();
}
