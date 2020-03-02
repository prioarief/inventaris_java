/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplements;
import Model.ModelRuang;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ImplementsRuang {
    public void insert(ModelRuang b);

    public void update(ModelRuang b);

    public void delete(int id);

    public List<ModelRuang> getALL();

    public List<ModelRuang> getCariRuang(String nama_ruang);
}
