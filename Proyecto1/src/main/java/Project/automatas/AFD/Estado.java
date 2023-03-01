/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.automatas.AFD;
//import java.io.FileWriter;
//import java.io.PrintWriter;
import java.util.ArrayList;
//import java.util.UUID;
/**
 *
 * @author Pacos
 */
public class Estado {
    public String ID;
    public String nombre;
    public ArrayList<Transicion> tr;
    public ArrayList<String> sig;
    public boolean aceptar;
    //public int ID;
    //public String Transition;
    public Estado() {
        this.ID = null;
        this.nombre = null;
        //this.Name = "";
        this.tr = new ArrayList<>();
        this.sig = new ArrayList<>();
        this.aceptar = false;
    }
}