/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

import Model.ModelPegawai;
import Model.ModelPegawai;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ASUS
 */
public class tb_pegawai extends AbstractTableModel {
    List<ModelPegawai> lb;

    public tb_pegawai(List<ModelPegawai> lb) {
        this.lb = lb;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }


    public int getRowCount() {
        return lb.size();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Nama Pegawai";
            case 2:
                return "NIP";
            case 3:
                return "Alamat";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return lb.get(row).getId();
            case 1:
                return lb.get(row).getNamaPegawai();
            case 2:
                return lb.get(row).getNipPegawai();
            case 3:
                return lb.get(row).getAlamatPegawai();
            default:
                return null;
        }
    }
}
