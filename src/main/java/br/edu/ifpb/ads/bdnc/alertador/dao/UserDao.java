package br.edu.ifpb.ads.bdnc.alertador.dao;

/**
 * @version 1.0
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 07/01/2017 - 12:01:31
 */
public interface UserDao<T, J> {

    public T searchByEmail(J email);
}
