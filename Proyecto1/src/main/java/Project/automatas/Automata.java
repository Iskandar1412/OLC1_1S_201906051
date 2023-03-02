/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.Automatas;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
/**
 *
 * @author Pacos
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Automata {
    private Estado inicial;
    private final ArrayList<Estado> aceptacion;
    private final ArrayList<Estado> estados;
    private HashSet simbolos;
    private int tipo;
    private String[] regex;
    private String lenguaje;
    
    public Automata(){
        this.aceptacion = new ArrayList<>();
        this.estados = new ArrayList<>();
        this.simbolos = new HashSet();
        this.regex = new String[3];
    }
    
    public void setInicial(Estado inicial) { this.inicial = inicial; };
    public Estado getInicial() { return inicial; };
    public void addAceptacion(Estado acept) { this.aceptacion.add(acept); };
    public ArrayList<Estado> getAceptacion() { return aceptacion; };
    public void addEstados(Estado estado) { this.estados.add(estado); };
    public ArrayList<Estado> getEstados() { return estados; };
    public void setSimbolos(HashSet symbol) { this.simbolos = symbol; };
    public HashSet getSimbolos() { return simbolos; };
    public void setTipo(int type) { this.tipo = type; };
    public int getTipo(int type) { return tipo; };
    public void setRegex(String[] reg) { this.regex = reg; };
    public String[] getRegex() { return regex; };
    public void setLenguaje(String leng) { this.lenguaje = leng; };
    public String getLenguaje() { return lenguaje; };
    public void addRegex(int llave, String valor) { this.regex[llave] = valor; };
    public void crearSimbolo(List<String> elemento) {
        for(String valor: elemento) {
            if(!(valor.equals("|") || valor.equals(".") || valor.equals("*") || valor.equals("epsilon"))) {
                this.simbolos.add(valor);
            }
        }
    }
    
}