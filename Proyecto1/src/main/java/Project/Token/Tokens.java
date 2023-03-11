/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Project.Token;
/**
 *
 * @author Pacos
 */
public class tokens {
    String tipo, lexema, descripcion;
    int linea, columna;
	
    public tokens(String lexema , int linea, int columna ) {
        this.lexema=lexema;
        this.linea=linea+1;
        this.columna = columna+1;
    }

    public String show() {
        String data = "";
        data += "\nlexema:" + lexema;
        data += "\nlinea:" + String.valueOf(linea);
        data += "\ncolumna:" + String.valueOf(columna);
        return data;
    }
}
