/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.Automatas;

/**
 *
 * @author Pacos
 */
@SuppressWarnings({"rawtypes"})
public class Transicion<T> {
    
    private Estado inicial;
    private Estado finality;
    private T Symbol;
    public Transicion(Estado inicial, Estado _final, T symbol){
        this.inicial = inicial;
        this.finality = _final;
        this.Symbol = symbol;
    }
    
    public void setInicial(Estado inicial) { this.inicial = inicial; };
    public Estado getInicial() { return inicial; };
    public void setFinality(Estado _final) { this.finality = _final; };
    public Estado getFinality() { return finality; };
    public void setSymbol(T symbol) { this.Symbol = symbol; };
    public T getSyymbol() { return Symbol; };
    
    @Override
    public String toString() { return "(" + inicial.getID() + "-" + Symbol + "-" + finality.getID() + ")"; };
    public String dot() { return (this.inicial + "->" + this.finality + "[label=\"" + this.Symbol + "\"];"); };
}