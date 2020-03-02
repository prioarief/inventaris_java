/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplements;

import Model.ModelJenis;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ImplementsJenis {
    public void insert(ModelJenis b);

    public void update(ModelJenis b);

    public void delete(int id);

    public List<ModelJenis> getALL();

    public List<ModelJenis> getCariJenis(String nama_jenis);
}
