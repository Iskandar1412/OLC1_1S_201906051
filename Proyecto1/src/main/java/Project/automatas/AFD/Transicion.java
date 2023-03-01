/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.automatas.AFD;
//import java.io.FileWriter;
//import java.io.PrintWriter;
import java.util.ArrayList;
///import java.util.UUID;
/**
 *
 * @author Pacos
 */
public class Transicion {
    public Estado next;
    public ArrayList<String> car;
    public ArrayList<String> sig;
    public boolean borrar;
    public boolean rev;
    
    public Transicion() {
        this.next = null;
        this.car = new ArrayList<>();
        this.sig = new ArrayList<>();
        this.borrar = false;
        this.rev = false;
    }
}