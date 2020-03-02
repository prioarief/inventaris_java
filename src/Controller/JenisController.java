/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DaoJenis;
import DAOImplements.ImplementsJenis;
import Model.ModelJenis;
import java.util.List;
import javax.swing.JOptionPane;
import tableModel.tb_jenis;
import view.admin.CrudJenis;

/**
 *
 * @author ASUS
 */
public class JenisController {
    CrudJenis frame;
    ImplementsJenis implJenis;
    List<ModelJenis> lb;

    public JenisController(CrudJenis frame) {
        this.frame = frame;
        implJenis = new DaoJenis();
        lb = implJenis.getALL();
    }

    //mengosongkan field
    public void reset() {
        frame.getIdJenis().setText("");
        frame.getNamaJenis().setText("");
        frame.getKodeJenis().setText("");
        frame.getKeterangan().setText("");
        
    }

    //menampilkan data ke dalam tabel
    public void isiTable() {
        lb = implJenis.getALL();
        tb_jenis tmb = new tb_jenis(lb);
        frame.getTabelData().setModel(tmb);
    }

    //merupakan fungsi untuk menampilkan data yang dipilih dari tabel
    public void isiField(int row) {
        frame.getIdJenis().setText(lb.get(row).getId().toString());
        frame.getNamaJenis().setText(lb.get(row).getNama_jenis());
        frame.getKodeJenis().setText(lb.get(row).getKode_jenis());
        frame.getKeterangan().setText(lb.get(row).getKeterangan());
    }

    //merupakan fungsi untuk insert data berdasarkan inputan user dari textfield di frame
    public void insert() {
        
      if (!frame.getNamaJenis().getText().trim().isEmpty()& !frame.getKodeJenis().getText().trim().isEmpty() & !frame.getKeterangan().getText().trim().isEmpty()) {
          
        ModelJenis b = new ModelJenis();
        b.setNama_jenis(frame.getNamaJenis().getText());
        b.setKode_jenis(frame.getKodeJenis().getText());
        b.setKeterangan(frame.getKeterangan().getText());

        implJenis.insert(b);
        JOptionPane.showMessageDialog(null, "Simpan Data sukses");
        
        } else {
            JOptionPane.showMessageDialog(frame, "Data Tidak Boleh Kosong");
        }
    }

    //berfungsi untuk update data berdasarkan inputan user dari textfield di frame
    public void update() {
   if (!frame.getNamaJenis().getText().trim().isEmpty()& !frame.getKodeJenis().getText().trim().isEmpty() & !frame.getKeterangan().getText().trim().isEmpty()) {
             
        ModelJenis b = new ModelJenis();
        b.setNama_jenis(frame.getNamaJenis().getText());
        b.setKode_jenis(frame.getKodeJenis().getText());
        b.setKeterangan(frame.getKeterangan().getText());
        b.setId(Integer.parseInt(frame.getIdJenis().getText()));
        implJenis.update(b);
        
        JOptionPane.showMessageDialog(null, "Update Data  sukses");
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih data yang akan di ubah");
        }
    }

    //berfungsi menghapus data yang dipilih
    public void delete() {
        if (!frame.getIdJenis().getText().trim().isEmpty()) {
            int id = Integer.parseInt(frame.getIdJenis().getText());
            implJenis.delete(id);
            
            JOptionPane.showMessageDialog(null, "Hapus Data  sukses");
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih data yang akan di hapus");
        }
    }

    public void isiTableCariNama() {
        lb = implJenis.getCariJenis(frame.SearchJenis().getText());
        tb_jenis tmb = new tb_jenis(lb);
        frame.getTabelData().setModel(tmb);
    }

    public void cariJenis() {
        if (!frame.SearchJenis().getText().trim().isEmpty()) {
            implJenis.getCariJenis(frame.SearchJenis().getText());
            isiTableCariNama();
        } else {
            JOptionPane.showMessageDialog(frame, "SILAHKAN PILIH DATA");
        }
    }
}
