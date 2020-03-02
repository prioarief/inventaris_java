/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOImplements.ImplementsRuang;
import Model.ModelRuang;
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
public class DaoRuang implements ImplementsRuang {
    Connection connection;
    final String insert = "INSERT INTO ruang (nama_ruang, kode_ruang, keterangan) VALUES (?, ?, ?);";
    final String update = "UPDATE ruang set nama_ruang=?, kode_ruang=?, keterangan=? where id_ruang=? ;";
    final String delete = "DELETE FROM ruang where id_ruang=? ;";
    final String select = "SELECT * FROM ruang;";
    final String search = "SELECT * FROM ruang where nama_ruang like ?";
    
    public DaoRuang() {
        connection = koneksi.connection();
    }

    @Override
    public void insert(ModelRuang b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, b.getNama_ruang());
            statement.setString(2, b.getKode_ruang());
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
    public void update(ModelRuang b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getNama_ruang());
            statement.setString(2, b.getKode_ruang());
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
    public List<ModelRuang> getALL() {
        List<ModelRuang> lb = null;
        try {
            lb = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                ModelRuang b = new ModelRuang();
                b.setId(rs.getInt("id_ruang"));
                b.setNama_ruang(rs.getString("nama_ruang"));
                b.setKode_ruang(rs.getString("kode_ruang"));
                b.setKeterangan(rs.getString("keterangan"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPegawai.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lb;
    }

    @Override
    public List<ModelRuang> getCariRuang(String nama_jenis) {
        List<ModelRuang> lb = null;
        try {
            lb = new ArrayList<ModelRuang>();
            PreparedStatement st = connection.prepareStatement(search);
            st.setString(1, "%" + nama_jenis + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ModelRuang b = new ModelRuang();
                b.setId(rs.getInt("id_ruang"));
                b.setNama_ruang(rs.getString("nama_ruang"));
                b.setKode_ruang(rs.getString("kode_ruang"));
                b.setKeterangan(rs.getString("keterangan"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }
}
