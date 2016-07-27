/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.alertador.dao.bddao;

import br.edu.ifpb.bdnc.alertador.dao.GenericBdDao;
import br.edu.ifpb.bdnc.alertador.entity.User;
import java.sql.ResultSet;
import br.edu.ifpb.bdnc.alertador.dao.GenericDao;
import br.edu.ifpb.bdnc.alertador.dao.UserDao;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author wensttay
 */
public class UserBdDao extends GenericBdDao
        implements GenericDao<User, Integer>, UserDao<User, String>{

    @Override
    public boolean save( User object ){

        int rowsAffected = 0;

        try{
            conectar();
            String sql = "INSERT INTO USERS(id, email, name, password) VALUES(DEFAULT, ?, ?, ?)";

            PreparedStatement ps = getConnection().prepareStatement(sql);

            int i = 1;
            ps.setString(i++, object.getEmail());
            ps.setString(i++, object.getName());
            ps.setString(i++, object.getPassword());

            rowsAffected = ps.executeUpdate();
        } catch ( URISyntaxException | IOException | SQLException | ClassNotFoundException ex ){
            ex.printStackTrace();
        }

        desconectar();
        return rowsAffected != 0;

    }

    @Override
    public User search( Integer id ){
        User d = null;

        try{
            conectar();
            String sql = "SELECT * FROM USERS WHERE id = ?";

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
    public User fill( ResultSet rs ){

        try{
            User u = new User();

            u.setId(rs.getInt("id"));
            u.setEmail(rs.getString("email"));
            u.setName(rs.getString("name"));
            u.setPassword(rs.getString("password"));

            return u;
        } catch ( SQLException ex ){
            System.out.println(ex.getMessage());
        }

        return null;
    }

    @Override
    public User searchByEmail( String email ){
        User d = null;

        try{
            conectar();
            String sql = "SELECT * FROM USERS WHERE email = ?";

            PreparedStatement ps = getConnection().prepareStatement(sql);

            int i = 1;
            ps.setString(i++, email);

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

}
