
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
public class NodoA {
    public String ID;
    public String simbolo;
    public String valor;
    public Boolean anulable;
    public String nombre;
    public String tipo;
    public NodoA hijo1;
    public NodoA hijo2;
    public ArrayList<NodoA> first;
    public ArrayList<NodoA> last;
    
    public NodoA() {
        this.simbolo = " ";
        this.anulable = false;
        this.first = new ArrayList<>();
        this.last = new ArrayList<>();
        this.nombre = null;
        this.hijo1 = null;
        this.hijo2 = null;
        this.tipo = "";
    }
}