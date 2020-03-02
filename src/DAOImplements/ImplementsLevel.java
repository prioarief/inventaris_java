/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplements;

import Model.ModelLevel;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ImplementsLevel {
    public void insert(ModelLevel b);

    public void update(ModelLevel b);

    public void delete(int id);

    public List<ModelLevel> getALL();

    public List<ModelLevel> getCariLevel(String nama_level);
}
