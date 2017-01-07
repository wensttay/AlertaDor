package br.edu.ifpb.ads.bdnc.alertador.dao;

import java.util.List;

/**
 * @version 1.0
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 07/01/2017 - 12:01:31
 */
public interface DenunciationDao<T> {

    public List<T> getAll();
}
