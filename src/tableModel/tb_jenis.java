/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

import Model.ModelJenis;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ASUS
 */
public class tb_jenis extends AbstractTableModel {
    List<ModelJenis> lb;

    public tb_jenis(List<ModelJenis> lb) {
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
                return "Nama Jenis";
            case 2:
                return "Kode Jenis";
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
                return lb.get(row).getNama_jenis();
            case 2:
                return lb.get(row).getKode_jenis();
            case 3:
                return lb.get(row).getKeterangan();
            default:
                return null;
        }
    }
}
