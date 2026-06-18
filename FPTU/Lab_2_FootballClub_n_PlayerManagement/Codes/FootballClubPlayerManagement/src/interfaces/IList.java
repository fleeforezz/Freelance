package interfaces;

import java.util.List;

public interface IList<T> {
    
    T addRec();
    
    T updateRec(String id);
    
    boolean deleteRec(String id);

    List<T> listAll();

    T searchRecById(String id);

    void displayRec();
}
