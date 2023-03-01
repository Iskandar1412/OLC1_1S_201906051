/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.Siguientes;
//import java.io.FileWriter;
//import java.io.PrintWriter;
import java.util.ArrayList;
///import java.util.UUID;
/**
 *
 * @author Pacos
 */
public class Siguiente {
    public String simbolo;
    public String hoja;
    public ArrayList<String> terminal;
    //public ArrayList<String> NoTerminal;
    public ArrayList<String> LS;
    //public int ID;
    
    public Siguiente() {
        this.terminal = new ArrayList<>();
        this.hoja = "";
        //this.ID = 0;
        this.LS = new ArrayList<>();
    }
}