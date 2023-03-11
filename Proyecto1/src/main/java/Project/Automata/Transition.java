/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Project.Automata;
import Project.Automata.Estado;
/**
 *
 * @author Pacos
 */
@SuppressWarnings({ "rawtypes"})
public class Transition<T> {
    private Estado inicial;
    private Estado fin;
    private T simbolo;

    public Transition(Estado inicial, Estado fin, T simbolo) {
        this.inicial = inicial;
        this.fin = fin;
        this.simbolo = simbolo;
    }
    
    public void setInicial(Estado inicial) { this.inicial = inicial; }
    public Estado getInicial() { return inicial; }
     
    public void setFin(Estado fin) { this.fin = fin; }
    public Estado getFin() { return fin; }

    public void setSimbolo(T simbolo) { this.simbolo = simbolo; }
    public T getSimbolo() { return simbolo; }

    @Override
    public String toString(){ return "(" + inicial.getId() + "-" + simbolo + "-" + fin.getId() + ")"; }
    public String DOT_String(){ return (this.inicial+" -> "+this.fin+" [label=\""+this.simbolo+"\" fontcolor=\"#f78a05\"];"); }
   
}
