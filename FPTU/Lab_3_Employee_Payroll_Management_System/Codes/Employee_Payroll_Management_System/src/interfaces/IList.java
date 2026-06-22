package interfaces;

import java.util.List;

public interface IList<T> {
    T Add();
    T Update(String id);
    boolean Delete(String id);
    List<T> ListAll();
    T FindById(String id);
    void display();
    void save();
    boolean hasUnsavedChanges();
}
