/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOImplements.ImplementsPetugas;
import Model.ModelPetugas;
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
public class DaoPetugas implements ImplementsPetugas{
    Connection connection;
    final String insert = "INSERT INTO petugas (username, password, nama_petugas, id_level) VALUES (?, ?, ?, ?);";
    final String update = "UPDATE petugas set username=?, password=?, nama_petugas=?, id_level=?  where id_petugas=? ;";
    final String delete = "DELETE FROM petugas where id_petugas=? ;";
    final String select = "SELECT * FROM petugas;";
    final String search = "SELECT * FROM petugas where nama_petugas like ?";
    
    public DaoPetugas() {
        connection = koneksi.connection();
    }

    @Override
    public void insert(ModelPetugas b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, b.getUsername());
            statement.setString(2, b.getPassword());
            statement.setString(3, b.getNama_petugas());
            statement.setInt(4, b.getId__level());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                b.setId_petugas(rs.getInt(1));
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
    public void update(ModelPetugas b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getUsername());
            statement.setString(2, b.getPassword());
            statement.setString(3, b.getNama_petugas());
            statement.setInt(4, b.getId__level());
            statement.setInt(5, b.getId_petugas());
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
    public List<ModelPetugas> getALL() {
        List<ModelPetugas> lb = null;
        try {
            lb = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                ModelPetugas b = new ModelPetugas();
                b.setId_petugas(rs.getInt("id_petugas"));
                b.setUsername(rs.getString("username"));
                b.setPassword(rs.getString("password"));
                b.setNama_petugas(rs.getString("nama_Petugas"));
                b.setId__level(rs.getInt("id_level"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPegawai.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lb;
    }

    @Override
    public List<ModelPetugas> getCariPetugas(String nama_petugas) {
        List<ModelPetugas> lb = null;
        try {
            lb = new ArrayList<ModelPetugas>();
            PreparedStatement st = connection.prepareStatement(search);
            st.setString(1, "%" + nama_petugas + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ModelPetugas b = new ModelPetugas();
                b.setId_petugas(rs.getInt("id_petugas"));
                b.setUsername(rs.getString("username"));
                b.setPassword(rs.getString("password"));
                b.setNama_petugas(rs.getString("nama_Petugas"));
                b.setId__level(rs.getInt("id_level"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }
}
