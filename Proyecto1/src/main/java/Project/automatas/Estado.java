/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.Automatas;
import java.util.ArrayList;
/**
 *
 * @author Pacos
 * @param <T>
 */
@SuppressWarnings({"rawtypes"})
public class Estado<T> {
    
    private T id;
    private ArrayList<Transicion> transiciones = new ArrayList<>();
    
    public Estado(T id, ArrayList<Transicion> transicion){
        this.id = id;
        this.transiciones = transicion;
    }
    
    public Estado(T id) {
        this.id = id;
    }
    
    public void setID(T id) { this.id = id; };
    public T getID() { return id; };
    public void addTransicion(Transicion trans) { this.transiciones.add(trans); };
    public ArrayList<Transicion> getTransicion() { return transiciones; };
    
    @Override
    public String toString() { return this.id.toString(); };
}