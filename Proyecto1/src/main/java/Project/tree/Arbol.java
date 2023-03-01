/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.tree;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;
/**
 *
 * @author Pacos
 */
public class Arbol {
    public String ID;
    public ArrayList<NodoA> Nodos;
    public NodoA Cabecera;
    
    public Arbol(String id, ArrayList<NodoA> nodos, NodoA cabecera) {
        this.ID = id;
        this.Nodos = nodos;
        this.Cabecera = cabecera;
    }
    
    public String grapho() {
        String dot = "digraph G{\n" + "\tnode[shape=record];\n";
        for(int i = 0; i < this.Nodos.size(); i++) {
            //Nodo temp = Nodo;
            
            NodoA temp = Nodos.get(i);
            
            temp.ID = "\t\"" + UUID.randomUUID().toString() + "\"";
            String first = "(" + temp.first.get(0);
            //for(int j = 0; j < temp.size(); j++) {
            for(int j = 1; j < temp.first.size(); j++) {
                //first = "," + temp.first;
                first += ", " + temp.first.get(j);
            }
            first += ")";
            String last = "(" + temp.last.get(0);
            for (int j = 1; j < temp.last.size(); j++) {
                last += ", " + temp.last.get(j);
            }
            last += ")";
            String anulable;
            if(temp.anulable) {
                anulable = "A";
            }else{
                anulable = "N.A";
            }
            String nombre = "";
            if(temp.nombre == null) {
               nombre = "-";
            }else{
                nombre = temp.nombre;
            }
            dot += "\t" + temp.ID + "[label=\"" + first + "| {" + anulable + "|" + temp.simbolo + "|" + nombre + "} |" + last + "\"];\n";
        }
        dot += "\n";
        
        for(int i = 0; i < this.Nodos.size(); i++){
            NodoA aux = Nodos.get(i);
            if(aux.hijo1 != null) {
                dot += "\t" + aux.ID + "->" + aux.hijo1.ID + "[minlen=2];\n";
            }
            if(aux.hijo2 != null) {
                //dot += aux.ID + "->" + aux.Child1.ID;
                dot += "\t" + aux.ID + "->" + aux.hijo2.ID + "[minlen=2];\n";
            }
        }
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
            String path = "Tree_" + this.ID + ".txt";
            String toString = "Tree_" + this.ID + ".png";
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
