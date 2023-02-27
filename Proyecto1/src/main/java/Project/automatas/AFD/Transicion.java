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
    public Estado Next;
    public ArrayList<String> car;
    public ArrayList<String> Sig;
    public boolean bor;
    public boolean rev;
    
    public Transicion() {
        this.Next = null;
        this.car = new ArrayList<>();
        this.Sig = new ArrayList<>();
        this.bor = false;
        this.rev = false;
    }
}