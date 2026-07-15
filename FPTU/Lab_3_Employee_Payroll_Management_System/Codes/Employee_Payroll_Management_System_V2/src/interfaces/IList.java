package interfaces;

import java.util.List;

public interface IList<T> {
    T add();
    T update(String id);
    boolean delete(String id);
    List<T> listAll();
    T findById(String id);
    void display();
    void save();
    boolean hasUnsavedChanges();
}
