/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.errors;

/**
 *
 * @author Pacos
 */
public class SyntaxError {
    public String lexema;
    public int row;
    public int col;
    public SyntaxError(String lexema, int row, int col) {
        this.lexema = lexema;
        this.row = row;
        this.col = col;
    }
}

