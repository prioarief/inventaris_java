/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplements;

import Model.ModelPegawai;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ImplementsPegawai {
    public void insert(ModelPegawai b);

    public void update(ModelPegawai b);

    public void delete(int id);

    public List<ModelPegawai> getALL();

    public List<ModelPegawai> getCariNama(String nama_pegawai);
}
