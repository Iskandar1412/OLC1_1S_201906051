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
public class Conj {
    public String ID;
    public ArrayList<String> conj;
    public String notation;
    
    public Conj(String id, ArrayList<String> conjuntion, String notation) {
        this.ID = id;
        this.conj = conjuntion;
        this.notation = notation;
    }
}