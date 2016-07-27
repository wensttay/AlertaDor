
import br.edu.ifpb.bdnc.alertador.dao.bddao.DenunciationBdDao;
import br.edu.ifpb.bdnc.alertador.entity.Denunciation;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author wensttay
 */
public class NovoClass{
    public static void main( String[] args ){
        DenunciationBdDao denunciationBdDao = new DenunciationBdDao();
        List<Denunciation> lis = denunciationBdDao.getAll();
        for ( Denunciation denunciation : lis ){
            System.out.println(denunciation.toString());
        }
    }
}
