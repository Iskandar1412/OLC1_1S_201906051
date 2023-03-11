/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Project.Automata;
/**
 *
 * @author Pacos
 */
public class Cadenas {
    public Boolean Encontrado = false;
    public String string;
    public String name;
    public boolean validacion;

    public Cadenas(String name , String string) {
        this.name = name;
        this.string = string;
        this.validacion = false;
    }
    
    public String show() {
        String data = "\nnombre:" + name + "\ncadena:" + string.length();
        return data;
    }
}
