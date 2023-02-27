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
    public String Symbol;
    public String Hoja;
    public ArrayList<String> Terminal;
    //public ArrayList<String> NoTerminal;
    public ArrayList<String> Siguientes;
    //public int ID;
    
    public Siguiente() {
        this.Terminal = new ArrayList<>();
        this.Hoja = "";
        //this.ID = 0;
        this.Siguientes = new ArrayList<>();
    }
}