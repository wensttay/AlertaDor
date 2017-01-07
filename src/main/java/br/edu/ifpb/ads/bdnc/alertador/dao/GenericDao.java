package br.edu.ifpb.ads.bdnc.alertador.dao;

import java.sql.ResultSet;

/**
 * @version 1.0
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 07/01/2017 - 12:01:31
 */
public interface GenericDao<T, J> {

    public boolean save(T object);

    public T search(J id);

    public T fill(ResultSet rs);

}
