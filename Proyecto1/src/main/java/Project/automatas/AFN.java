/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.Automatas;
import java.util.ArrayList;
import java.util.HashSet;
import com.mycompany.proyecto1.Proyecto1.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;
/**
 *
 * @author Pacos
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class AFN<T> {
    private Automata AFN;
    private List<String> Elements;
    public AFN(List<String> Elements){
        this.Elements = Elements;
    }
    
    public void Generar() {
        try {
            
        }catch(Exception e){
            System.out.println("Error Automata no Generado: " + e.getMessage() );
        }
    }
    
    public String genDOT() {
        String dot  = "";
        
        return dot;
    }
    
    public String TipoDado(Type contenido) {
        String dot = "";
        
        return dot;
    }
    
    public Automata concatenar(Automata a1, Automata a2) {
        Automata automata = new Automata();
        int contador = 0;
        
        
        return automata;
    }
    
    public Automata generacion(String simbolo) {
        Automata automata = new Automata();
        
        return automata;
    }
    
    public Automata cerradura(Automata a1) {
        Automata automata = new Automata();
        
        return automata;
    }
    
    public Automata unir(Automata a1, Automata a2) {
        Automata automata = new Automata();
        
        return automata;
    }
    
    public Automata getDot() {
        return this.AFN;
    }
    
}