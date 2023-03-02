/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.Automatas;
import java.util.ArrayList;
/**
 *
 * @author Pacos
 */
@SuppressWarnings({"rawtypes"})
public class Estado<T> {
    
    private T id;
    
    public Estado(T id){
        this.id = id;
    }
    
    public void setID(T id) { this.id = id; };
    public T getID() { return id; };
    
    
    
}