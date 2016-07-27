/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.alertador.entity;

import br.edu.ifpb.bdnc.alertador.enums.DenunciationType;
import br.edu.ifpb.bdnc.alertador.enums.SquealerType;
import com.vividsolutions.jts.geom.Point;

/**
 *
 * @author wensttay
 */
public class Denunciation{
    private int id;
    private User squealer;
    private SquealerType squealerType;
    private String description;
    private Point location;
    private DenunciationType denunciationType;
    private boolean anonymous;

    @Override
    public String toString(){
        return "Denunciation{" + "id=" + id + ", squealer=" + squealer + ", squealerType=" + squealerType + ", description=" + description + ", location=" + location + ", denunciationType=" + denunciationType + ", anonymous=" + anonymous + '}';
    }

    
    public int getId(){
        return id;
    }

    public void setId( int id ){
        this.id = id;
    }

    public User getSquealer(){
        return squealer;
    }

    public void setSquealer( User squealer ){
        this.squealer = squealer;
    }

    public SquealerType getSquealerType(){
        return squealerType;
    }

    public void setSquealerType( SquealerType squealerType ){
        this.squealerType = squealerType;
    }

    public Point getLocation(){
        return location;
    }

    public void setLocation( Point location ){
        this.location = location;
    }

    public DenunciationType getDenunciationType(){
        return denunciationType;
    }

    public void setDenunciationType( DenunciationType denunciationType ){
        this.denunciationType = denunciationType;
    }

    public boolean isAnonymous(){
        return anonymous;
    }

    public void setAnonymous( boolean anonymous ){
        this.anonymous = anonymous;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription( String description ){
        this.description = description;
    }
}
