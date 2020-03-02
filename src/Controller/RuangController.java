/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DaoRuang;
import DAOImplements.ImplementsRuang;
import Model.ModelRuang;
import java.util.List;
import javax.swing.JOptionPane;
import tableModel.tb_ruang;
import view.admin.CrudRuang;

/**
 *
 * @author ASUS
 */
public class RuangController {
    CrudRuang frame;
    ImplementsRuang implRuang;
    List<ModelRuang> lb;

    public RuangController(CrudRuang frame) {
        this.frame = frame;
        implRuang = new DaoRuang();
        lb = implRuang.getALL();
    }

    //mengosongkan field
    public void reset() {
        frame.getIdRuang().setText("");
        frame.getNamaRuang().setText("");
        frame.getKodeRuang().setText("");
        frame.getKeterangan().setText("");
        
    }

    //menampilkan data ke dalam tabel
    public void isiTable() {
        lb = implRuang.getALL();
        tb_ruang tmb = new tb_ruang(lb);
        frame.getTabelData().setModel(tmb);
    }

    //merupakan fungsi untuk menampilkan data yang dipilih dari tabel
    public void isiField(int row) {
        frame.getIdRuang().setText(lb.get(row).getId().toString());
        frame.getNamaRuang().setText(lb.get(row).getNama_ruang());
        frame.getKodeRuang().setText(lb.get(row).getKode_ruang());
        frame.getKeterangan().setText(lb.get(row).getKeterangan());
    }

    //merupakan fungsi untuk insert data berdasarkan inputan user dari textfield di frame
    public void insert() {
        
      if (!frame.getNamaRuang().getText().trim().isEmpty()& !frame.getKodeRuang().getText().trim().isEmpty() & !frame.getKeterangan().getText().trim().isEmpty()) {
          
        ModelRuang b = new ModelRuang();
        b.setNama_ruang(frame.getNamaRuang().getText());
        b.setKode_ruang(frame.getKodeRuang().getText());
        b.setKeterangan(frame.getKeterangan().getText());

        implRuang.insert(b);
        JOptionPane.showMessageDialog(null, "Simpan Data sukses");
        
        } else {
            JOptionPane.showMessageDialog(frame, "Data Tidak Boleh Kosong");
        }
    }

    //berfungsi untuk update data berdasarkan inputan user dari textfield di frame
    public void update() {
   if (!frame.getNamaRuang().getText().trim().isEmpty()& !frame.getKodeRuang().getText().trim().isEmpty() & !frame.getKeterangan().getText().trim().isEmpty()) {
             
        ModelRuang b = new ModelRuang();
        b.setNama_ruang(frame.getNamaRuang().getText());
        b.setKode_ruang(frame.getKodeRuang().getText());
        b.setKeterangan(frame.getKeterangan().getText());
        b.setId(Integer.parseInt(frame.getIdRuang().getText()));
        implRuang.update(b);
        
        JOptionPane.showMessageDialog(null, "Update Data  sukses");
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih data yang akan di ubah");
        }
    }

    //berfungsi menghapus data yang dipilih
    public void delete() {
        if (!frame.getIdRuang().getText().trim().isEmpty()) {
            int id = Integer.parseInt(frame.getIdRuang().getText());
            implRuang.delete(id);
            
            JOptionPane.showMessageDialog(null, "Hapus Data  sukses");
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih data yang akan di hapus");
        }
    }

    public void isiTableCariNama() {
        lb = implRuang.getCariRuang(frame.SearchJenis().getText());
        tb_ruang tmb = new tb_ruang(lb);
        frame.getTabelData().setModel(tmb);
    }

    public void cariJenis() {
        if (!frame.SearchJenis().getText().trim().isEmpty()) {
            implRuang.getCariRuang(frame.SearchJenis().getText());
            isiTableCariNama();
        } else {
            JOptionPane.showMessageDialog(frame, "SILAHKAN PILIH DATA");
        }
    }
}
