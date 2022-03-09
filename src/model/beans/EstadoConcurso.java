    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.io.Serializable;

/**
 *
 * @author delta
 */
public class EstadoConcurso implements Serializable{
    private boolean lanzado;
    private boolean nuevoConcurso;
    private boolean finalizado;
    


    public EstadoConcurso() {
        this.lanzado = false;
        this.nuevoConcurso = true;//Crear metodo que asigne true o false.
        this.finalizado = false;
    }


    public boolean getNuevoConcurso() {
        return nuevoConcurso;
    }

    public void setNuevoConcurso(boolean nuevoConcurso) {
        this.nuevoConcurso = nuevoConcurso;
    }

    public boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
    

    public boolean getLanzado() {
        return lanzado;
    }

    public void setLanzado(boolean lanzado) {
        this.lanzado = lanzado;
    }
    
   
    
}
