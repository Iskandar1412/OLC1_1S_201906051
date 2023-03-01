/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.automatas.AFD;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;
/**
 *
 * @author Pacos
 */
public class Automata {
    public String ID;
    public ArrayList<Estado> est;
    public Estado cabecera;
    
    public Automata(String id, ArrayList<Estado> stat, Estado head) {
        this.ID = id;
        this.est = stat;
        this.cabecera = head;
    }
    
    public boolean validar(String entrada) {
        Estado transition = cabecera;
        int ban = 0;
        //entrada = entrada.substring(1);
        entrada = entrada.substring(1, entrada.length() - 1);
        boolean val = false;
        
        for(int i = 0; i < entrada.length(); i++) {
            String temp = Character.toString(entrada.charAt(i));
            if(temp.equals("\\")) {
                if(Character.toString(entrada.charAt(i + 1)).equals("n") || Character.toString(entrada.charAt(i + 1)).equals("'") || Character.toString(entrada.charAt(i + 1)).equals("\"")) {
                   temp = Character.toString(entrada.charAt(i + 1));
                   i = i + 1;
                }
            }
            for(int j = 0; j < transition.tr.size(); j++) {
                Transicion tr = transition.tr.get(j);
                if(tr.car.contains(temp)) {
                    transition = tr.next;
                    ban = 1;
                    break;
                }
            }
            if(i == (entrada.length() - 1)) {
                if(ban == 1) {
                    if(transition.aceptar) {
                        val = true;
                    }
                }else{
                    break;
                }
            }else{
                if(ban == 1) {
                    ban = 0;
                }else{
                    break;
                }
            }
        }
        
        return val;
    }
    
    public String getdot() {
        String dot = "digraph G{\n\trankdir=LR;\n";
        
        
        return dot;
    }
    
    public void write(String path, String content) {
        FileWriter fichero = null;
        PrintWriter p = null;
        try{
            fichero = new FileWriter(path);
            p = new PrintWriter(fichero);
            p.write(content);
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
            String path = "AFD_" + this.ID + ".txt";
            String name = "AFD_" + this.ID + ".svg";
            write(path, getdot());
            ProcessBuilder tosvg;
            tosvg = new ProcessBuilder("dot", "-Tsvg", "-o", name, path);
            tosvg.redirectErrorStream(true);
            tosvg.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}