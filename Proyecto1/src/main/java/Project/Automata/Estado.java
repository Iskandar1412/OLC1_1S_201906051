/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Project.Automata;
/**
 *
 * @author Pacos
 */
import java.util.ArrayList;
@SuppressWarnings({ "rawtypes" })
public class Estado<T> {
    private T id;
    private ArrayList<Transition> transiciones = new ArrayList<>();
    
    public Estado(T id, ArrayList<Transition> transiciones) {
        this.id = id;
        this.transiciones = transiciones;
    }

    public Estado(T id) {this.id = id;}
    
    public void setId(T id) {this.id = id;}
    public T getId() { return id;}
    
    public void setTransiciones(Transition tran) {this.transiciones.add(tran);}
    public ArrayList<Transition> getTransiciones() {return transiciones; }
    
    @Override
    public String toString(){ return this.id.toString(); }  
    
}
