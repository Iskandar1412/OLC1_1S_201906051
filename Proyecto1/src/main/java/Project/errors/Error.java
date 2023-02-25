/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.errors;

/**
 *
 * @author Pacos
 */
public class Error {
    public String type;
    public String lexema;
    public int col;
    public int row;
    public Error(String type, String lexema, int row, int col) {
        this.type = type;
        this.lexema = lexema;
        this.row = row;
        this.col = col;
    }
}
