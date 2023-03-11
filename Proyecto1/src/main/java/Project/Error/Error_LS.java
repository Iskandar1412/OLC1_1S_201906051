/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Project.Error;
/**
 *
 * @author Pacos
 */
public class Error_LS {
    public String tipo, lexema, descripcion;
    public int linea, columna;
	
    public Error_LS(String tipo,String lexema,String descripcion, int linea, int columna ) {
        this.tipo=tipo;
        this.lexema=lexema;
        this.descripcion=descripcion;
        this.linea=linea+1;
        this.columna = columna+1;
    }

    public String show() {
        String data = "";
        data += "Error::->{\n\ttipo:" + tipo;
        data += "\n\tlexema:" + lexema + "\n\tdescripcion:" + descripcion + "\n\tlinea:" + String.valueOf(linea) + "\n\tcolumna:" + String.valueOf(columna);
        data += "\n}\n";
        return data;
    }
}
