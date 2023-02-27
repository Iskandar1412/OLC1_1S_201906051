/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.automatas.AFD;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
//import java.util.UUID;
/**
 *
 * @author Pacos
 */
public class State {
    public String ID;
    //public String Name;
    //public ArrayList<Transicion> Transition;
    public ArrayList<Estado> state;
    //public boolean Acept;
    //public int ID;
    //public String Transition;
    public State(String id, ArrayList<Estado> sta) {
        this.ID = id;
        this.state = sta;
    }
    
    public String getdot() {
        String dot = "digraph G{ \n\trankdir=LR;\n\tnode[shape=record];\n";
        dot += "a[label=\"{Estado|Transicion}";
        
        
        return dot;
    }
    
    public void write(String path, String content) {
        FileWriter fichero = null;
        PrintWriter p = null;
        try{
            fichero = new FileWriter(path);
            p = new PrintWriter(fichero);
            p.close();
            fichero.close();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }finally{
            if(p != null) {
                p.close();
            }
        }
    }
    
    public void Dot() {
        try{
            String path = this.ID + ".txt";
            String name = this.ID + ".svg";
            write(path, getdot());
            ProcessBuilder tosvg;
            tosvg = new ProcessBuilder("dot", "-Tsvg", "-o", name, path);
            tosvg.redirectErrorStream(true);
            tosvg.start();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}