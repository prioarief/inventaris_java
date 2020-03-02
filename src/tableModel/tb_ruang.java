/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

import Model.ModelRuang;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ASUS
 */
public class tb_ruang extends AbstractTableModel {
    List<ModelRuang> lb;

    public tb_ruang(List<ModelRuang> lb) {
        this.lb = lb;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }


    @Override
    public int getRowCount() {
        return lb.size();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Nama Ruang";
            case 2:
                return "Kode Ruang";
            case 3:
                return "Keterangan";
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
                return lb.get(row).getNama_ruang();
            case 2:
                return lb.get(row).getKode_ruang();
            case 3:
                return lb.get(row).getKeterangan();
            default:
                return null;
        }
    }
}
