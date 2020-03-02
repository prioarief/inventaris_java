/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

import Model.ModelLevel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ASUS
 */
public class tb_level extends AbstractTableModel {
    List<ModelLevel> lb;

    public tb_level(List<ModelLevel> lb) {
        this.lb = lb;
    }

    @Override
    public int getColumnCount() {
        return 2;
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
                return "Nama Level";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return lb.get(row).getId_level();
            case 1:
                return lb.get(row).getNama_level();
            default:
                return null;
        }
    }
}
