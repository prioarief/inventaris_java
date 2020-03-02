/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import koneksi.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.ModelPegawai;
import DAOImplements.ImplementsPegawai;
//import tableModel.tb_pegawai;

/**
 *
 * @author ASUS
 */
public class DaoPegawai implements ImplementsPegawai {
    Connection connection;
    final String insert = "INSERT INTO pegawai (nama_pegawai, nip, alamat) VALUES (?, ?, ?);";
    final String update = "UPDATE pegawai set nama_pegawai=?, nip=?, alamat=? where id_pegawai=? ;";
    final String delete = "DELETE FROM pegawai where id_pegawai=? ;";
    final String select = "SELECT * FROM pegawai;";
    final String search = "SELECT * FROM pegawai where nama_pegawai like ?";
    
    public DaoPegawai() {
        connection = koneksi.connection();
    }

    public void insert(ModelPegawai b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, b.getNamaPegawai());
            statement.setString(2, b.getNipPegawai());
            statement.setString(3, b.getAlamatPegawai());
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
    public void update(ModelPegawai b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getNamaPegawai());
            statement.setString(2, b.getNipPegawai());
            statement.setString(3, b.getAlamatPegawai());
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
    public List<ModelPegawai> getALL() {
        List<ModelPegawai> lb = null;
        try {
            lb = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                ModelPegawai b = new ModelPegawai();
                b.setId(rs.getInt("id_pegawai"));
                b.setNamaPegawai(rs.getString("nama_pegawai"));
                b.setNipPegawai(rs.getString("nip"));
                b.setAlamatPegawai(rs.getString("alamat"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPegawai.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lb;
    }

    @Override
    public List<ModelPegawai> getCariNama(String nama_pegawai) {
        List<ModelPegawai> lb = null;
        try {
            lb = new ArrayList<ModelPegawai>();
            PreparedStatement st = connection.prepareStatement(search);
            st.setString(1, "%" + nama_pegawai + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ModelPegawai b = new ModelPegawai();
                b.setId(rs.getInt("id_pegawai"));
                b.setNamaPegawai(rs.getString("nama_pegawai"));
                b.setNipPegawai(rs.getString("nip"));
                b.setAlamatPegawai(rs.getString("alamat"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }
}
