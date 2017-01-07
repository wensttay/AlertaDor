package br.edu.ifpb.ads.bdnc.alertador.dao.bddao;

import br.edu.ifpb.ads.bdnc.alertador.dao.GenericBdDao;
import br.edu.ifpb.ads.bdnc.alertador.dao.GenericDao;
import br.edu.ifpb.ads.bdnc.alertador.dao.UserDao;
import br.edu.ifpb.ads.bdnc.alertador.entity.User;
import java.sql.ResultSet;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @version 1.0
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 07/01/2017 - 12:01:31
 */
public class UserBdDao extends GenericBdDao
        implements GenericDao<User, Integer>, UserDao<User, String> {

    @Override
    public boolean save(User object) {

        int rowsAffected = 0;

        try {
            conectar();
            String sql = "INSERT INTO USERS(id, email, name, password) VALUES(DEFAULT, ?, ?, ?)";

            PreparedStatement ps = getConnection().prepareStatement(sql);

            int i = 1;
            ps.setString(i++, object.getEmail());
            ps.setString(i++, object.getName());
            ps.setString(i++, object.getPassword());

            rowsAffected = ps.executeUpdate();
        } catch (URISyntaxException | IOException | SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        desconectar();

        return rowsAffected != 0;

    }

    @Override
    public User search(Integer id) {
        User d = null;

        try {
            conectar();
            String sql = "SELECT * FROM USERS WHERE id = ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            int i = 1;
            ps.setInt(i++, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                d = fill(rs);
            }

        } catch (URISyntaxException | IOException | SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        desconectar();

        return d;

    }

    @Override
    public User fill(ResultSet rs) {

        try {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setEmail(rs.getString("email"));
            u.setName(rs.getString("name"));
            u.setPassword(rs.getString("password"));

            return u;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    @Override
    public User searchByEmail(String email) {
        User d = null;

        try {
            conectar();
            String sql = "SELECT * FROM USERS WHERE email = ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            int i = 1;
            ps.setString(i++, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                d = fill(rs);
            }

        } catch (URISyntaxException | IOException | SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        desconectar();

        return d;
    }

}
