/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.automatas.Thompson;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;
/**
 *
 * @author Pacos
 */
public class Thompson {
    public String ID;
    public ArrayList<NodoT> Nodos;
    public NodoT Cabecera;
    public Thompson(String id, ArrayList<NodoT> nodo, NodoT cabecera){
        this.ID = id;
        this.Nodos = nodo;
        this.Cabecera = cabecera;
    }
    
    public String grapho() {
        String dot = "digraph G{\n" + "\tnode[shape=record];\n";
        
        dot += "}\n";
        
        return dot;
    }
    
    public void write(String path, String content) {
        FileWriter fichero = null;
        PrintWriter p = null;
        try {
            fichero = new FileWriter(path);
            p = new PrintWriter(fichero);
            p.close();
            fichero.close();
        }catch(Exception e) {
            System.out.println("Error" + e.getMessage());
        }finally{
            if(p != null) {
                p.close();
            }
        }
    }
    
    public void Graph() {
        try {
            String path = "AFN_" + this.ID + ".txt";
            String toString = "AFN_" + this.ID + ".png";
            write(path, grapho());
            ProcessBuilder todot;
            todot = new ProcessBuilder("dot", "-Tpng", toString, path);
            todot.redirectErrorStream(true);
            todot.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

