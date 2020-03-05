/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplements;

import Model.ModelPetugas;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ImplementsPetugas {
    public void insert(ModelPetugas b);

    public void update(ModelPetugas b);

    public void delete(int id);

    public List<ModelPetugas> getALL();

    public List<ModelPetugas> getCariPetugas(String nama_petugas);
}
