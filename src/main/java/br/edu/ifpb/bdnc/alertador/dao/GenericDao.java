/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.alertador.dao;

import java.sql.ResultSet;

/**
 *
 * @author wensttay
 */
public interface GenericDao<T, J>{
    
    public boolean save(T object);
    public T search(J id);
    public T fill(ResultSet rs);
    
}
