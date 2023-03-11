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
public class Transicion_Nodo {
    Transicion_Nodo next;
    Integer estado;
    String info;
    String tipo;
    Integer Hoja;
    List<Integer> siguientes = new ArrayList<>();

    public Transicion_Nodo(String info,String tipo,Integer Hoja) {
        this.next = null;
        this.info = info;
        this.tipo = tipo;
        this.Hoja = Hoja;
    }
}