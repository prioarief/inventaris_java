/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOImplements.ImplementsJenis;
import Model.ModelJenis;
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
public class DaoJenis implements ImplementsJenis {
    Connection connection;
    final String insert = "INSERT INTO jenis (nama_jenis, kode_jenis, keterangan) VALUES (?, ?, ?);";
    final String update = "UPDATE jenis set nama_jenis=?, kode_jenis=?, keterangan=? where id_jenis=? ;";
    final String delete = "DELETE FROM jenis where id_jenis=? ;";
    final String select = "SELECT * FROM jenis;";
    final String search = "SELECT * FROM jenis where nama_jenis like ?";
    
    public DaoJenis() {
        connection = koneksi.connection();
    }

    public void insert(ModelJenis b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, b.getNama_jenis());
            statement.setString(2, b.getKode_jenis());
            statement.setString(3, b.getKeterangan());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                b.setId(rs.getInt(1));
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
    public void update(ModelJenis b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getNama_jenis());
            statement.setString(2, b.getKode_jenis());
            statement.setString(3, b.getKeterangan());
            statement.setInt(4, b.getId());
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
    public List<ModelJenis> getALL() {
        List<ModelJenis> lb = null;
        try {
            lb = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                ModelJenis b = new ModelJenis();
                b.setId(rs.getInt("id_jenis"));
                b.setNama_jenis(rs.getString("nama_jenis"));
                b.setKode_jenis(rs.getString("kode_jenis"));
                b.setKeterangan(rs.getString("keterangan"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPegawai.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lb;
    }

    @Override
    public List<ModelJenis> getCariJenis(String nama_jenis) {
        List<ModelJenis> lb = null;
        try {
            lb = new ArrayList<ModelJenis>();
            PreparedStatement st = connection.prepareStatement(search);
            st.setString(1, "%" + nama_jenis + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ModelJenis b = new ModelJenis();
                b.setId(rs.getInt("id_jenis"));
                b.setNama_jenis(rs.getString("nama_jenis"));
                b.setKode_jenis(rs.getString("kode_jenis"));
                b.setKeterangan(rs.getString("keterangan"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }
}
