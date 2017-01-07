package br.edu.ifpb.ads.bdnc.alertador;

import br.edu.ifpb.ads.bdnc.alertador.dao.bddao.DenunciationBdDao;
import br.edu.ifpb.ads.bdnc.alertador.entity.Denunciation;
import java.util.List;

/**
 * @version 1.0
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 07/01/2017 - 12:01:31
 */
public class Loader {

    public static void main(String[] args) {
        DenunciationBdDao denunciationBdDao = new DenunciationBdDao();
        List<Denunciation> lis = denunciationBdDao.getAll();
        
        for (Denunciation denunciation : lis) {
            System.out.println(denunciation.toString());
        }
    }
}
