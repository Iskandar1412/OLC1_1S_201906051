/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.Automatas;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.Queue;
/**
 *
 * @author Pacos
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Automata<T> {
    
    private HashSet Symbols;
    private int Automata_t;
    private String[] Regex;
    private String Lenguaje;
    public Automata(){
        this.Symbols = new HashSet();
        this.Regex = new String[3];
    }
    
    
    public void setRegex(String[] regex) { this.Regex = regex; };
    public String[] getRegex() { return Regex; };
    public void setSymbols(HashSet symbol) { this.Symbols = symbol; };
    public HashSet getSymbols() { return Symbols; };
    
}