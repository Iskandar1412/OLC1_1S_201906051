/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.automatas.Thompson;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;
/**
 *
 * @author Pacos
 */
public class NodoT {
    public String ID;
    public String Nombre;
    public NodoT next1;
    public NodoT next2;
    public String tran1;
    public String tran2;
    public boolean constraint;
    
    public NodoT(){
        this.ID = null;
        this.Nombre = null;
        this.next1 = null;
        this.next1 = null;
        this.next2 = null;
        this.tran1 = null;
        this.tran2 = null;
        this.constraint = false;
    }
}
