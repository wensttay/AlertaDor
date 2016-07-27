/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.alertador.enums;

/**
 *
 * @author wensttay
 */
public enum DenunciationType{
    HARASSMENT(1, "Assédio"),
    RAPE(2, "Estrupo"),
    AGGRESSION(3, "Agressão");
    
    private int id;
    private String tittle;

    private DenunciationType(int id, String tittle ){
        this.id = id;
        this.tittle = tittle;
    }

    public int getId(){
        return id;
    }

    public String getTittle(){
        return tittle;
    }
    
    public static DenunciationType get( int id ){
        for ( DenunciationType dt : DenunciationType.values() ){
            if ( dt.getId() == id ){
                return dt;
            }
        }
        return null;
    }
}
