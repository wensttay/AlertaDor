/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.alertador.dao;

import java.util.List;

/**
 *
 * @author wensttay
 */
public interface DenunciationDao<T>{
    
    public List<T> getAll();
}
