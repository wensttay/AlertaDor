/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.alertador.dao;

import br.edu.ifpb.bdnc.alertador.entity.User;

/**
 *
 * @author wensttay
 */
public interface UserDao<T, J>{
    
    public T searchByEmail(J email);
}