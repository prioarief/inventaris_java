/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOImplements.ImplementsLevel;
import Model.ModelLevel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import koneksi.koneksi;

/**
 *
 * @author ASUS
 */
public class DaoLevel implements ImplementsLevel {
    Connection connection;
    final String insert = "INSERT INTO level (nama_level) VALUES (?);";
    final String update = "UPDATE level set nama_level=? where id_level=? ;";
    final String delete = "DELETE FROM level where id_level=? ;";
    final String select = "SELECT * FROM level;";
    final String search = "SELECT * FROM level where nama_level like ?";
    
    public DaoLevel() {
        connection = koneksi.connection();
    }

    public void insert(ModelLevel b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, b.getNama_level());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                b.setId_level(rs.getInt(1));
            }
            
        } catch (SQLException ex) {
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    public void update(ModelLevel b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getNama_level());
            statement.setInt(2, b.getId_level());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException ex) {
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    public List<ModelLevel> getALL() {
        List<ModelLevel> lb = null;
        try {
            lb = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                ModelLevel b = new ModelLevel();
                b.setId_level(rs.getInt("id_level"));
                b.setNama_level(rs.getString("nama_level"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPegawai.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lb;
    }

    @Override
    public List<ModelLevel> getCariLevel(String nama_level) {
        List<ModelLevel> lb = null;
        try {
            lb = new ArrayList<ModelLevel>();
            PreparedStatement st = connection.prepareStatement(search);
            st.setString(1, "%" + nama_level + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ModelLevel b = new ModelLevel();
                b.setId_level(rs.getInt("id_level"));
                b.setNama_level(rs.getString("nama_level"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }
}
