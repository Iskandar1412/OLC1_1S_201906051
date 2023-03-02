/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.errors;

/**
 *
 * @author Pacos
 */

public class Errores {
    public String tipo;
    public String lexema;
    public int columna;
    public int fila;
    public Errores(String type, String lexema, int row, int col) {
        this.tipo = type;
        this.lexema = lexema;
        this.fila = row;
        this.columna = col;
    }
}
