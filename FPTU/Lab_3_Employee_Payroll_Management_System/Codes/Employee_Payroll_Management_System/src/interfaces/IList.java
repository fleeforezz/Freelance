package interfaces;

import java.util.List;

public interface IList<T> {
    T Add();
    T Update(T entity);
    boolean Delete(String id);
    List<T> ListAll();
    T FindById(String id);
}
