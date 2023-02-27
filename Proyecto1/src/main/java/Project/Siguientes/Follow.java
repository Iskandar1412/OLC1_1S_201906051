/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.Siguientes;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;
/**
 *
 * @author Pacos
 */
public class Follow {
    //public String Symbol;
    //public String Hoja;
    //public ArrayList<String> Terminal;
    //public ArrayList<String> NoTerminal;
    //public ArrayList<String> Siguientes;
    //public int ID;
    public String ID;
    public ArrayList<Siguiente> Siguiente;
    
    
    public Follow(String id, ArrayList<Siguiente> siguiente) {
        //this.Terminal = new ArrayList<>();
        //this.Hoja = "";
        //this.ID = 0;
        //this.Siguientes = new ArrayList<>();
        this.ID = id;
        this.Siguiente = siguiente;
    }
    
    public String getdot() {
        String dot = "digraph G{ \n\trankdir=LR;\n\tnode[shape=record];\n";
        dot += "a[label=\"{Hoja|Terminal|Siguiente}";
        
        
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