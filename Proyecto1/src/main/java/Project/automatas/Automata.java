/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Project.Automata;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
/**
 *
 * @author Pacos
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class Automata {	
    private Estado inicial;
    private final  ArrayList<Estado> aceptacion;
    private final ArrayList<Estado> estados;
    private HashSet simbolos;
    private int tipoAutomata;
    private String[] resultadoRegex;
    private String lenguaje;
    
    public Automata() {
        this.aceptacion = new ArrayList<>();
        this.estados = new ArrayList<>();
        this.simbolos = new HashSet();
        this.resultadoRegex = new String[3];
    }
    
    public void setInicial(Estado inicial) { this.inicial = inicial; }
    public Estado getInicial() { return inicial; }
    
    public void setSimbolos(HashSet simbolos) { this.simbolos = simbolos; }
    public HashSet getSimbolos() { return simbolos; }
    
    public void setTipoAutomata(int tipoAutomata) { this.tipoAutomata = tipoAutomata; }
    public int getTipoAutomata() { return tipoAutomata; }
    
    public void addResultadoRegex(int key, String value){ this.resultadoRegex[key] = value; }   
    public void setResultadoRegex(String[] resultadoRegex) { this.resultadoRegex = resultadoRegex; }
    public String[] getResultadoRegex() { return resultadoRegex; }
    
    public void setLenguaje(String lenguaje) { this.lenguaje = lenguaje; }
    public String getLenguaje() { return lenguaje; }
    
    public void addEstadoAceptacion(Estado estado){ this.aceptacion.add(estado); }
    public ArrayList<Estado> getAceptacion() { return aceptacion; }
    
    public void addEstados(Estado estado){ this.estados.add(estado); }
    public ArrayList<Estado> getEstados() { return estados; }
    public Estado getEstado(int index){ return estados.get(index); }
    
    public void  crearSimbolos(List<String> Elementos){
        for(String c: Elementos){
            if( !(c.equals("|") || c.equals(".") || c.equals("*") || c.equals("epsilon"))){ this.simbolos.add(c); }
        }
    }
}
