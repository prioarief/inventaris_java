/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DaoPegawai;
import DAOImplements.ImplementsPegawai;
import Model.ModelPegawai;
//import model.TableModelBarang;
import view.admin.CrudPegawai;
import java.util.List;
import javax.swing.JOptionPane;
import tableModel.tb_pegawai;

/**
 *
 * @author ASUS
 */
public class PegawaiController {
    CrudPegawai frame;
    ImplementsPegawai implPegawai;
    List<ModelPegawai> lb;

    public PegawaiController(CrudPegawai frame) {
        this.frame = frame;
        implPegawai = new DaoPegawai();
        lb = implPegawai.getALL();
    }

    //mengosongkan field
    public void reset() {
        frame.getTxtID().setText("");
        frame.getNamaPeg().setText("");
        frame.getNIP().setText("");
        frame.getAlamat().setText("");
        
    }

    //menampilkan data ke dalam tabel
    public void isiTable() {
        lb = implPegawai.getALL();
        tb_pegawai tmb = new tb_pegawai(lb);
        frame.getTabelData().setModel(tmb);
    }

    //merupakan fungsi untuk menampilkan data yang dipilih dari tabel
    public void isiField(int row) {
        frame.getTxtID().setText(lb.get(row).getId().toString());
        frame.getNamaPeg().setText(lb.get(row).getNamaPegawai());
        frame.getNIP().setText(lb.get(row).getNipPegawai());
        frame.getAlamat().setText(lb.get(row).getAlamatPegawai());
    }

    //merupakan fungsi untuk insert data berdasarkan inputan user dari textfield di frame
    public void insert() {
        
      if (!frame.getNamaPeg().getText().trim().isEmpty()& !frame.getNIP().getText().trim().isEmpty() & !frame.getAlamat().getText().trim().isEmpty()) {
          
        ModelPegawai b = new ModelPegawai();
        b.setNamaPegawai(frame.getNamaPeg().getText());
        b.setNipPegawai(frame.getNIP().getText());
        b.setAlamatPegawai(frame.getAlamat().getText());

        implPegawai.insert(b);
        JOptionPane.showMessageDialog(null, "Simpan Data sukses");
        
        } else {
            JOptionPane.showMessageDialog(frame, "Data Tidak Boleh Kosong");
        }
    }

    //berfungsi untuk update data berdasarkan inputan user dari textfield di frame
    public void update() {
   if (!frame.getNamaPeg().getText().trim().isEmpty()& !frame.getNIP().getText().trim().isEmpty() & !frame.getAlamat().getText().trim().isEmpty()) {
             
        ModelPegawai b = new ModelPegawai();
        b.setNamaPegawai(frame.getNamaPeg().getText());
        b.setNipPegawai(frame.getNIP().getText());
        b.setAlamatPegawai(frame.getAlamat().getText());
        b.setId(Integer.parseInt(frame.getTxtID().getText()));
        implPegawai.update(b);
        
        JOptionPane.showMessageDialog(null, "Update Data  sukses");
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih data yang akan di ubah");
        }
    }

    //berfungsi menghapus data yang dipilih
    public void delete() {
//        if (!frame.getHB().getText().trim().isEmpty()) {
//            int id = Integer.parseInt(frame.getTxtID().getText());
//            implMahasiswa.delete(id);
//            
//            JOptionPane.showMessageDialog(null, "Hapus Data  sukses");
//        } else {
//            JOptionPane.showMessageDialog(frame, "Pilih data yang akan di hapus");
//        }
    }

    public void isiTableCariNama() {
//        lb = implMahasiswa.getCariNama(frame.getTxtCariBarang().getText());
//        TableModelBarang tmb = new TableModelBarang(lb);
//        frame.getTabelData().setModel(tmb);
    }

    public void carinama() {
//        if (!frame.getTxtCariBarang().getText().trim().isEmpty()) {
//            implMahasiswa.getCariNama(frame.getTxtCariBarang().getText());
//            isiTableCariNama();
//        } else {
//            JOptionPane.showMessageDialog(frame, "SILAHKAN PILIH DATA");
//        }
    }
}
