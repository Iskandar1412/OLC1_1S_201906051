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
    public String descripcion;
    public int columna;
    public int fila;
    public Errores(String type, String lexema, String desc, int row, int col) {
        this.tipo = type;
        this.lexema = lexema;
        this.descripcion = desc;
        this.fila = row;
        this.columna = col;
    }
    
    public String mostrarErrrores() {
        String mostrar = "Error: {\n\t" + this.lexema + ", linea: " + this.fila + ", columna: " + this.columna + "\n\t\t" + "Error de tipo: " + this.tipo + " (" + this.descripcion + ")\n}\n";
        return mostrar;
    }
}
