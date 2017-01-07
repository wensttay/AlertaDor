package br.edu.ifpb.ads.bdnc.alertador.controll;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * @version 1.0
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 07/01/2017 - 12:01:31
 */
public class ZPointExclusionStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes arg0) {
        return arg0.getName().equals("z");
    }

    @Override
    public boolean shouldSkipClass(Class<?> arg0) {
        return false;
    }

}
