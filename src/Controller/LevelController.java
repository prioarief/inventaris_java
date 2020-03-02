/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DaoLevel;
import DAOImplements.ImplementsLevel;
import Model.ModelLevel;
import java.util.List;
import javax.swing.JOptionPane;
import tableModel.tb_level;
import view.admin.CrudLevel;

/**
 *
 * @author ASUS
 */
public class LevelController {
    CrudLevel frame;
    ImplementsLevel implLevel;
    List<ModelLevel> lb;

    public LevelController(CrudLevel frame) {
        this.frame = frame;
        implLevel = new DaoLevel();
        lb = implLevel.getALL();
    }

    //mengosongkan field
    public void reset() {
        frame.getIdLevel().setText("");
        frame.getNamaLevel().setText("");
        
    }

    //menampilkan data ke dalam tabel
    public void isiTable() {
        lb = implLevel.getALL();
        tb_level tmb = new tb_level(lb);
        frame.getTabelData().setModel(tmb);
    }

    //merupakan fungsi untuk menampilkan data yang dipilih dari tabel
    public void isiField(int row) {
        frame.getIdLevel().setText(lb.get(row).getId_level().toString());
        frame.getNamaLevel().setText(lb.get(row).getNama_level());
    }

    //merupakan fungsi untuk insert data berdasarkan inputan user dari textfield di frame
    public void insert() {
        
      if (!frame.getNamaLevel().getText().trim().isEmpty()) {
          
        ModelLevel b = new ModelLevel();
        b.setNama_level(frame.getNamaLevel().getText());

        implLevel.insert(b);
        JOptionPane.showMessageDialog(null, "Simpan Data sukses");
        
        } else {
            JOptionPane.showMessageDialog(frame, "Data Tidak Boleh Kosong");
        }
    }

    //berfungsi untuk update data berdasarkan inputan user dari textfield di frame
    public void update() {
   if (!frame.getNamaLevel().getText().trim().isEmpty()) {
             
        ModelLevel b = new ModelLevel();
        b.setNama_level(frame.getNamaLevel().getText());
        b.setId_level(Integer.parseInt(frame.getIdLevel().getText()));
        implLevel.update(b);
        
        JOptionPane.showMessageDialog(null, "Update Data  sukses");
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih data yang akan di ubah");
        }
    }

    //berfungsi menghapus data yang dipilih
    public void delete() {
        if (!frame.getIdLevel().getText().trim().isEmpty()) {
            int id = Integer.parseInt(frame.getIdLevel().getText());
            implLevel.delete(id);
            
            JOptionPane.showMessageDialog(null, "Hapus Data  sukses");
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih data yang akan di hapus");
        }
    }

    public void isiTableCariLevel() {
        lb = implLevel.getCariLevel(frame.SearchJenis().getText());
        tb_level tmb = new tb_level(lb);
        frame.getTabelData().setModel(tmb);
    }

    public void cariJenis() {
        if (!frame.SearchJenis().getText().trim().isEmpty()) {
            implLevel.getCariLevel(frame.SearchJenis().getText());
            isiTableCariLevel();
        } else {
            JOptionPane.showMessageDialog(frame, "SILAHKAN PILIH DATA");
        }
    }
}
