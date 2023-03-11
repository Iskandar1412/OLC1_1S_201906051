/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Project.Automata;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Pacos
 */
public class Tipo_A {
    String valor;
    String tipo;
    Integer Estado;
		
    public Tipo_A(String valor, String tipo) {
        this.valor = valor;
        this.tipo = tipo;
        this.Estado = null;
    }

    public void show() {
        String data = "";
        data += "\nValor:" + this.valor;
        data += "\nTipo:" +  this.tipo;
        System.out.println(data);
    }
}
