/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.Automatas;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Pacos
 */
public class Type {
    public String Valor;
    public Integer Estado;
    public String Tipo;
    
    public Type(String valor, String tipo){
        this.Valor = valor;
        this.Tipo = tipo;
        this.Estado = null;
    }
    
    public void Generar() {
        String data = "\nValor: " + this.Valor + "\nTipo: " + this.Tipo;
        System.out.println(data);
    }
}