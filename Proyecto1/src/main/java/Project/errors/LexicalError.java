/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.errors;

/**
 *
 * @author Pacos
 */
public class LexicalError {
    public String lexema;
    public int col;
    public int row;
    public LexicalError(String lexema, int row, int col) {
        this.lexema = lexema;
        this.row = row;
        this.col = col;
    }
}
