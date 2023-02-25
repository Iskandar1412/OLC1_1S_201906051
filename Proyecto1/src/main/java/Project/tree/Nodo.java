
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.tree;
import java.util.ArrayList;
/**
 *
 * @author Pacos
 */
public class Nodo {
    public String ID;
    public String Symbol;
    public String Value;
    public Boolean Anulable;
    public String Name;
    public String Type;
    public Nodo Child1;
    public Nodo Child2;
    public ArrayList<Nodo> first;
    public ArrayList<Nodo> last;
    
    public Nodo() {
        this.Symbol = " ";
        this.Anulable = false;
        this.first = new ArrayList<>();
        this.last = new ArrayList<>();
        this.Name = null;
        this.Child1 = null;
        this.Child2 = null;
        this.Type = "";
    }
}