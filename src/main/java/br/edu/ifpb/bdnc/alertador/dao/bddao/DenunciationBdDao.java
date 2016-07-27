/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.alertador.dao.bddao;

import br.edu.ifpb.bdnc.alertador.dao.GenericBdDao;
import br.edu.ifpb.bdnc.alertador.entity.Denunciation;
import br.edu.ifpb.bdnc.alertador.entity.User;
import br.edu.ifpb.bdnc.alertador.enums.DenunciationType;
import br.edu.ifpb.bdnc.alertador.enums.SquealerType;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.edu.ifpb.bdnc.alertador.dao.GenericDao;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifpb.bdnc.alertador.dao.DenunciationDao;

/**
 *
 * @author wensttay
 */
public class DenunciationBdDao extends GenericBdDao 
        implements GenericDao<Denunciation, Integer>, DenunciationDao<Denunciation>{
    @Override
    public boolean save( Denunciation object ){

        int rowsAffected = 0;

        try{
            conectar();
            StringBuilder sql = new StringBuilder("INSERT INTO DENUNCIATION(id, squealer_id, squaler_type, description, ");
            sql.append("location, denunciation_type, anonymous) VALUES(DEFAULT, ?, ?, ?, ?, ?, ?);");

            PreparedStatement ps = getConnection().prepareStatement(sql.toString());

            int i = 1;
            ps.setInt(i++, object.getSquealer().getId());
            ps.setInt(i++, object.getSquealerType().getId());
            ps.setString(i++, object.getDescription());
            ps.setString(i++, new WKTWriter().write(object.getLocation()));
            ps.setInt(i++, object.getDenunciationType().getId());
            ps.setBoolean(i++, object.isAnonymous());

            rowsAffected = ps.executeUpdate();
        } catch ( URISyntaxException | IOException | SQLException | ClassNotFoundException ex ){
            System.out.println(ex.getMessage());
        }

        desconectar();
        return rowsAffected != 0;

    }

    @Override
    public Denunciation search( Integer id ){
        Denunciation d = null;
        
        try{
            conectar();
            String sql = "SELECT * FROM DENUNCIATION WHERE id = ?";

            PreparedStatement ps = getConnection().prepareStatement(sql);

            int i = 1;
            ps.setInt(i++, id);

            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ){
                d = fill(rs);
            }

        } catch ( URISyntaxException | IOException | SQLException | ClassNotFoundException ex ){
            System.out.println(ex.getMessage());
        }
        desconectar();
        
        return d;

    }

    @Override
    public Denunciation fill( ResultSet rs ){

        try{
            Denunciation d = new Denunciation();
            
            d.setId(rs.getInt("id"));
            User squealer = new UserBdDao().search(rs.getInt("squealer_id"));
            d.setSquealer(squealer);

            d.setSquealerType(SquealerType.get(rs.getInt("squaler_type")));
            d.setDescription(rs.getString("description"));

            Point point = ( Point ) new WKTReader().read(rs.getString("location"));
            d.setLocation(point);

            d.setDenunciationType(DenunciationType.get(rs.getInt("denunciation_type")));

            d.setAnonymous(rs.getBoolean("anonymous"));

            return d;
        } catch ( SQLException | ParseException ex ){
            System.out.println(ex.getMessage());
        }

        return null;
    }

    @Override
    public List<Denunciation> getAll(){
        List<Denunciation> denunciations = new ArrayList<>();

        try{
            conectar();
            String sql = "SELECT * FROM DENUNCIATION";

            PreparedStatement ps = getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            
            while( rs.next() ){
                Denunciation d = fill(rs);
                if(d != null){
                    denunciations.add(d);
                }
            }
            
            return denunciations;
        } catch ( URISyntaxException | IOException | SQLException | ClassNotFoundException ex ){
            System.out.println(ex.getMessage());
        }
        desconectar();
        
        return new ArrayList<>();
    }

}
