/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.Token;
/**
 *
 * @author Pacos
 */

public class Tokens {
    public String lexema;
    public int columna;
    public int fila;
    public Tokens(String lexema, int row, int col) {
        this.lexema = lexema;
        this.fila = row+1;
        this.columna = col+1;
    }
    
    public String mostrarToken() {
        String mostrar = "Token: {" + this.lexema + ", linea: " + this.fila + ", columna: " + this.columna + "}\n";
        return mostrar;
    }
}
