/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Project.Automata;
import Project.Automata.Siguientes;
import Project.Automata.Tipo_A;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Pacos
 */
public class Struct_Nodo {
    Struct_Nodo next;
    Struct_Nodo previous;
    Integer estado;
    String info;
    String tipo;
    Integer Hoja;
    List<Integer> siguientes = new ArrayList<>();

    public Struct_Nodo(String info,String tipo,Integer Hoja) {
        this.next = null;
        this.previous = null;
        this.info = info;
        this.tipo = tipo;
        this.Hoja = Hoja;
    }
}
