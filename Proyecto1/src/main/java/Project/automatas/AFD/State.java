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
}