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
public enum SquealerType{
    VITIMA(1, "Vitima"),
    PRESENCIADOR(2, "Presenciador");

    private int id;
    private String tittle;

    private SquealerType( int id, String tittle ){
        this.id = id;
        this.tittle = tittle;
    }

    public int getId(){
        return id;
    }

    public String getTittle(){
        return tittle;
    }

    public static SquealerType get( int id ){
        for ( SquealerType st : SquealerType.values() ){
            if ( st.getId() == id ){
                return st;
            }
        }
        return null;
    }
}
