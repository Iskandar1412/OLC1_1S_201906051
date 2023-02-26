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
    public String Name;
    public ArrayList<Transicion> Transition;
    public ArrayList<String> Next;
    public boolean Acept;
    //public int ID;
    //public String Transition;
    public Estado() {
        this.ID = null;
        this.Name = null;
        //this.Name = "";
        this.Transition = new ArrayList<>();
        this.Next = new ArrayList<>();
        this.Acept = false;
    }
}