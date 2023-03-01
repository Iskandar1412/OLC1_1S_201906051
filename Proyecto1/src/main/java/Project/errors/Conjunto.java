/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.errors;
import java.util.ArrayList;
/**
 *
 * @author Pacos
 */
public class Conjunto {
    public String ID;
    public ArrayList<String> conjunto;
    public String notation;
    
    public Conjunto(String id, ArrayList<String> conjuntion, String notation) {
        this.ID = id;
        this.conjunto = conjuntion;
        this.notation = notation;
    }
}