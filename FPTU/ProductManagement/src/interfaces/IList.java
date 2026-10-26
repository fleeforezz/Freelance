/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;

/**
 *
 * @author jso
 */
public interface IList<T> {
    T Add(T entity);
    
    T Update(String id, T entity);
    
    boolean Delete(String id);
    
    List<T> ListAll();
    
    T FindById(String id);
    
    void display();
    
    void save();
    
    boolean hasUnsavedChanges();
}
